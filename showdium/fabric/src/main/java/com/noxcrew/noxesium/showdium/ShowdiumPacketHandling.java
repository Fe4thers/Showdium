package com.noxcrew.noxesium.showdium;

import com.noxcrew.noxesium.api.feature.NoxesiumFeature;
import com.noxcrew.noxesium.showdium.network.ShowdiumPackets;
import com.noxcrew.noxesium.showdium.network.clientbound.*;
import com.noxcrew.noxesium.showdium.pingsystem.PingManager;
import java.util.UUID;
import net.minecraft.world.phys.Vec3;

public class ShowdiumPacketHandling extends NoxesiumFeature {
    private static final org.apache.logging.log4j.Logger logger =
            org.apache.logging.log4j.LogManager.getLogger(ShowdiumPacketHandling.class);

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
        ShowdiumPackets.CLIENT_PING_ADD.addListener(
                this, ClientboundPingAddPacket.class, (reference, packet, ignored3) -> {
                    if (!reference.isRegistered()) return;
                    Vec3 location = new Vec3(
                            packet.location().x(),
                            packet.location().y(),
                            packet.location().z());
                    UUID uuid = UUID.fromString(packet.uuid());
                    PingManager.createRemotePing(location, packet.color(), uuid);
                });
        ShowdiumPackets.CLIENT_PING_REMOVE.addListener(
                this, ClientboundPingRemovePacket.class, (reference, packet, ignored3) -> {
                    if (!reference.isRegistered()) return;
                    PingManager.removeLocalPlayerPings(UUID.fromString(packet.uuid()));
                });
        ShowdiumPackets.CLIENT_KEYBIND_DISABLE.addListener(
                this, ClientboundKeybindDisable.class, (reference, packet, ignored3) -> {
                    logger.info("Disabling keybind: " + packet.KeyBindName());
                    if (!reference.isRegistered()) return;
                    logger.info("Keybind disabled: " + packet.KeyBindName());
                    ShowdiumEntrypoint.keyBindHandler.disableKeybinds(packet.KeyBindName());
                });
    }
}
