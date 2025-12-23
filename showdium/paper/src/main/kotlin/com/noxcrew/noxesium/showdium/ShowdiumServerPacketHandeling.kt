package com.noxcrew.noxesium.showdium

import com.noxcrew.noxesium.paper.feature.ListeningNoxesiumFeature
import com.noxcrew.noxesium.showdium.network.ShowdiumPackets
import com.noxcrew.noxesium.showdium.network.serverbound.ServerboundKeybindTriggeredPacket
import com.noxcrew.noxesium.showdium.network.serverbound.ServerboundPingAddPacket
import com.noxcrew.noxesium.showdium.network.serverbound.ServerboundPingRemovePacket
import org.bukkit.Bukkit
import java.util.UUID

public class ShowdiumServerPacketHandeling : ListeningNoxesiumFeature() {
    init {
        ShowdiumPackets.SERVER_KEYBIND_TRIGGERED.addListener(
            this,
            ServerboundKeybindTriggeredPacket::class.java,
        ) { reference, packet, playerId ->
            if (!reference.isRegistered) return@addListener
            val player = Bukkit.getPlayer(playerId) ?: return@addListener
            val event = NoxesiumPlayerServerKeybindTriggerEvent(player, packet.KeybindTriggered, packet.pressedIn)
            Bukkit.getPluginManager().callEvent(event)
        }

        ShowdiumPackets.SERVER_PING_ADD.addListener(
            this,
            ServerboundPingAddPacket::class.java,
        ) { reference, packet, playerId ->
            if (!reference.isRegistered) return@addListener
            val player = Bukkit.getPlayer(playerId) ?: return@addListener
            val event = NoxesiumPlayerServerPingAddEvent(player, packet.color(), UUID.fromString(packet.uuid()), packet.location())
            Bukkit.getPluginManager().callEvent(event)
        }

        ShowdiumPackets.SERVER_PING_REMOVE.addListener(
            this,
            ServerboundPingRemovePacket::class.java,
        ) { reference, packet, playerId ->
            if (!reference.isRegistered) return@addListener
            val player = Bukkit.getPlayer(playerId) ?: return@addListener
            val event = NoxesiumPlayerServerPingRemoveEvent(player, UUID.fromString(packet.uuid()))
            Bukkit.getPluginManager().callEvent(event)
        }
    }
}
