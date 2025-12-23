package com.noxcrew.noxesium.showdium

import org.bukkit.entity.Player
import org.bukkit.event.HandlerList
import org.bukkit.event.player.PlayerEvent
import java.util.UUID

public class NoxesiumPlayerServerPingRemoveEvent(
    player: Player,
    public val uuid: UUID,
) : PlayerEvent(player) {
    public companion object {
        @JvmStatic
        public val HANDLER_LIST: HandlerList = HandlerList()

        @JvmStatic
        public fun getHandlerList(): HandlerList = HANDLER_LIST
    }

    override fun getHandlers(): HandlerList = HANDLER_LIST
}
