package com.noxcrew.noxesium.showdium

import com.noxcrew.noxesium.paper.feature.ListeningNoxesiumFeature
import com.noxcrew.noxesium.showdium.network.ShowdiumPackets
import com.noxcrew.noxesium.showdium.network.serverbound.ServerboundKeybindTriggeredPacket
import org.bukkit.Bukkit

public class KeybindListener : ListeningNoxesiumFeature() {
    init {
        ShowdiumPackets.SERVER_KEYBIND_TRIGGERED.addListener(
            this,
            ServerboundKeybindTriggeredPacket::class.java,
        ) { reference, packet, playerId ->
            if (!reference.isRegistered) return@addListener
            val player = Bukkit.getPlayer(playerId) ?: return@addListener
            NoxesiumPlayerServerKeybindTriggerEvent(player, packet.KeybindTriggered, packet.pressedIn)
        }
    }
}
