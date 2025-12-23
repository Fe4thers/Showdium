package com.noxcrew.noxesium.showdium.mixin;

import com.noxcrew.noxesium.showdium.ShowdiumEntrypoint;
import net.minecraft.client.KeyMapping;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(KeyMapping.class)
public class KeyMappingMixin {

    /**
     * Intercepts isDown() to return false for cancelled keys.
     */
    @Inject(method = "isDown", at = @At("HEAD"), cancellable = true)
    private void onIsDown(CallbackInfoReturnable<Boolean> cir) {
        KeyMapping self = (KeyMapping) (Object) this;

        if (ShowdiumEntrypoint.keyBindHandler != null && ShowdiumEntrypoint.keyBindHandler.isKeyCancelled(self)) {
            cir.setReturnValue(false);
        }
    }

    /**
     * Intercepts consumeClick() to return false for cancelled keys.
     */
    @Inject(method = "consumeClick", at = @At("HEAD"), cancellable = true)
    private void onConsumeClick(CallbackInfoReturnable<Boolean> cir) {
        KeyMapping self = (KeyMapping) (Object) this;

        if (ShowdiumEntrypoint.keyBindHandler != null && ShowdiumEntrypoint.keyBindHandler.isKeyCancelled(self)) {
            cir.setReturnValue(false);
        }
    }
}
