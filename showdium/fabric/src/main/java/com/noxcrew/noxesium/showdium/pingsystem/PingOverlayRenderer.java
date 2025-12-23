package com.noxcrew.noxesium.showdium.pingsystem;

import com.noxcrew.noxesium.showdium.config.PingSystemConfig;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

/**
 * Handles rendering of the ping overlay on the HUD.
 * Renders both on-screen pings and off-screen indicators.
 */
public final class PingOverlayRenderer {

    private static final float TEXT_SCALE_FACTOR = 1.0f;
    private static final float TEXT_Y_OFFSET = 2.0f;

    private PingOverlayRenderer() {}

    /**
     * Main render method called each frame.
     */
    public static void render(GuiGraphics graphics, DeltaTracker tickCounter) {
        // Check if ping system is enabled
        if (!PingSystemConfig.isEnabled()) {
            return;
        }

        Minecraft mc = Minecraft.getInstance();

        if (mc.player == null || mc.level == null) {
            return;
        }

        if (!RenderContext.hasValidData()) {
            return;
        }

        var pings = PingManager.getActivePings();

        if (pings.isEmpty()) {
            return;
        }

        PingRenderHelper renderer = new PingRenderHelper(graphics);
        OffscreenIndicatorRenderer.initializeSafeZone();

        var modelView = RenderContext.getModelViewMatrix();
        var projection = RenderContext.getProjectionMatrix();

        int screenWidth = mc.getWindow().getGuiScaledWidth();
        int screenHeight = mc.getWindow().getGuiScaledHeight();

        for (PingEntry ping : pings) {
            ScreenPosition screenPos = ProjectionHelper.projectToScreen(ping.getPosition(), modelView, projection);
            ping.setScreenPosition(screenPos);
            ping.setDistanceToPlayer(mc.player.position().distanceTo(ping.getPosition()));

            ping.updateState((int) mc.level.getGameTime());

            if (screenPos == null) {
                continue;
            }

            boolean isOffscreen = !screenPos.isVisibleOnScreen(screenWidth, screenHeight);

            if (isOffscreen) {
                OffscreenIndicatorRenderer.renderIndicator(renderer, screenPos, ping);
            } else {
                renderOnscreenPing(renderer, ping, screenPos);
            }
        }
    }

    /**
     * Renders a ping that is visible on screen.
     */
    private static void renderOnscreenPing(PingRenderHelper renderer, PingEntry ping, ScreenPosition screenPos) {
        var matrices = renderer.getMatrixStack();

        matrices.pushMatrix();
        matrices.translate(screenPos.getX(), screenPos.getY());

        float pingScale = ping.getRenderScale();

        matrices.pushMatrix();
        matrices.scale(pingScale, pingScale);

        int pingColor = ping.getDisplayColor();
        renderer.renderFullPingMarker(pingColor, ping.getCreatorInfo());

        matrices.popMatrix();

        matrices.pushMatrix();
        float textScale = pingScale * TEXT_SCALE_FACTOR;
        matrices.scale(textScale, textScale);

        int distanceRounded = (int) Math.round(ping.getDistanceToPlayer());
        String distanceText = distanceRounded + "m";
        renderer.renderTextLabel(Component.literal(distanceText), TEXT_Y_OFFSET, 0xFFFFFFFF);

        matrices.popMatrix();

        matrices.popMatrix();
    }
}
