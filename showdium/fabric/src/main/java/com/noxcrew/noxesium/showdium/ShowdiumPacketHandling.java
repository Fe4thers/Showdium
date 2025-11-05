package com.noxcrew.noxesium.showdium;

import com.noxcrew.noxesium.api.feature.NoxesiumFeature;
import com.noxcrew.noxesium.showdium.network.ShowdiumPackets;
import com.noxcrew.noxesium.showdium.network.clientbound.ClientboundKeybindAddPacket;
import com.noxcrew.noxesium.showdium.network.clientbound.ClientboundKeybindRemovePacket;

public class ShowdiumPacketHandling extends NoxesiumFeature {

    public ShowdiumPacketHandling() {
        ShowdiumPackets.CLIENT_KEYBIND_ADD.addListener(
                this, ClientboundKeybindAddPacket.class, (reference, packet, ignored3) -> {
                    if (!reference.isRegistered()) return;
                    Integer delay = packet.delay().orElse(0);
                    String qibBehavior = packet.clientQibImplementation().orElse("");
                    ShowdiumEntrypoint.keyBindHandler.registerNewKeybind(
                            packet.KeyBindName(), packet.Client(), packet.singlePressTrigger(), delay, qibBehavior);
                });
        ShowdiumPackets.CLIENT_KEYBIND_REMOVE.addListener(
                this, ClientboundKeybindRemovePacket.class, (reference, packet, ignored3) -> {
                    ShowdiumEntrypoint.keyBindHandler.removeKeybind(packet.KeyBindName());
                });
    }
}
