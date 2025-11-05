package com.noxcrew.noxesium.showdium.mixin;

import com.noxcrew.noxesium.api.registry.GameComponents;
import com.noxcrew.noxesium.showdium.registry.ShowdiumGameComponent;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.LoadingOverlay;
import net.minecraft.util.ARGB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LoadingOverlay.class)
public abstract class LoadingOverlayProgressBarMixin {
    @Shadow
    protected abstract void drawProgressBar(GuiGraphics guiGraphics, int i, int j, int k, int l, float f);

    @Inject(method = "drawProgressBar", at = @At("HEAD"), cancellable = true)
    private void onDrawProgressBar(
            GuiGraphics guiGraphics, int left, int top, int right, int bottom, float alpha, CallbackInfo ci) {
        if (!GameComponents.getInstance().noxesium$hasComponent(ShowdiumGameComponent.ShowdiumLoadingScreen)) {
            return; // let original run
        }
        ci.cancel(); // Cancel original

        // Use your custom color
        int alphaColor = (int) (alpha * 255);
        int customColor = ARGB.color(alphaColor, 255, 215, 0);

        // Custom progress bar drawing logic here, simplified:
        // For example, draw a filled rectangle with your color scaled by progress
        int width = (int) ((right - left) * alpha);
        guiGraphics.fill(left, top, left + width, bottom, customColor);
    }
}
