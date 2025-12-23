package com.noxcrew.noxesium.showdium.pingsystem;

import com.mojang.blaze3d.opengl.GlStateManager;
import com.noxcrew.noxesium.showdium.ShowdiumEntrypoint;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;
import org.joml.Matrix3x2fStack;

/**
 * Helper class for rendering ping-related UI elements.
 */
public class PingRenderHelper {

    private static final int LABEL_BACKGROUND_COLOR = ARGB.color(64, 0, 0, 0);
    private static final int ARROW_ICON_SIZE = 75;
    private static final float PING_ICON_SCALE = 3.5f;
    private static final float HEAD_ICON_SCALE = 2.0f;

    private final GuiGraphics graphics;
    private final Matrix3x2fStack matrixStack;

    public PingRenderHelper(GuiGraphics graphics) {
        this.graphics = graphics;
        this.matrixStack = graphics.pose();
    }

    public Matrix3x2fStack getMatrixStack() {
        return matrixStack;
    }

    /**
     * Renders a text label with background.
     */
    public void renderTextLabel(Component text, float yOffset, int textColor) {
        var font = ShowdiumEntrypoint.GAME.font;

        float textWidth = font.width(text);
        float textHeight = font.lineHeight;

        float offsetX = -textWidth * 0.5f;
        float offsetY = textHeight * yOffset;

        matrixStack.pushMatrix();
        matrixStack.translate(offsetX, offsetY);

        // Background
        graphics.fill(-2, -2, (int) textWidth + 1, (int) textHeight, LABEL_BACKGROUND_COLOR);

        // Text
        graphics.drawString(font, text, 0, 0, textColor, false);

        matrixStack.popMatrix();
    }

    /**
     * Renders a player head texture.
     */
    public void renderPlayerHead(PlayerInfo player) {
        if (player == null) {
            return;
        }

        ResourceLocation skinTexture = player.getSkin().texture();

        GlStateManager._enableBlend();

        // Base head layer (8x8 from texture)
        graphics.blit(RenderPipelines.GUI_TEXTURED, skinTexture, -4, -4, 8, 8, 8, 8, 64, 64);

        // Hat overlay layer
        graphics.blit(RenderPipelines.GUI_TEXTURED, skinTexture, -4, -4, 40, 8, 8, 8, 64, 64);

        GlStateManager._disableBlend();
    }

    /**
     * Renders a texture at the current position.
     */
    public void renderTexture(ResourceLocation texture, int size, int color) {
        int offset = -size / 2;

        GlStateManager._enableBlend();
        graphics.blit(RenderPipelines.GUI_TEXTURED, texture, offset, offset, 0, 0, size, size, size, size, color);
        GlStateManager._disableBlend();
    }

    /**
     * Renders the directional arrow icon.
     */
    public void renderDirectionalArrow(int color) {
        renderTexture(PingResources.ARROW_ICON_TEXTURE, ARROW_ICON_SIZE, color);
    }

    /**
     * Renders the default ping icon (simple square).
     */
    public void renderPingIcon(int color) {
        matrixStack.pushMatrix();
        matrixStack.translate(-3f, -3f);
        graphics.fill(0, 0, 6, 6, color);
        matrixStack.popMatrix();
    }

    /**
     * Renders a complete ping marker with player head.
     */
    public void renderFullPingMarker(int pingColor, PlayerInfo creator) {
        // Main ping icon
        matrixStack.pushMatrix();
        matrixStack.scale(PING_ICON_SCALE, PING_ICON_SCALE);
        renderPingIcon(pingColor);
        matrixStack.popMatrix();

        // Player head overlay
        if (creator != null) {
            matrixStack.pushMatrix();
            matrixStack.scale(HEAD_ICON_SCALE, HEAD_ICON_SCALE);
            renderPlayerHead(creator);
            matrixStack.popMatrix();
        }
    }
}
