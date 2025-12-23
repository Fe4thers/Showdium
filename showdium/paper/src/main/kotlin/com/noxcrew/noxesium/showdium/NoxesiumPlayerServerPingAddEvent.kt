package com.noxcrew.noxesium.showdium

import org.bukkit.entity.Player
import org.bukkit.event.HandlerList
import org.bukkit.event.player.PlayerEvent
import org.joml.Vector3f
import java.util.UUID

public class NoxesiumPlayerServerPingAddEvent(
    player: Player,
    public val color: Int,
    public val uuid: UUID,
    public val location: Vector3f,
) : PlayerEvent(player) {
    public companion object {
        @JvmStatic
        public val HANDLER_LIST: HandlerList = HandlerList()

        @JvmStatic
        public fun getHandlerList(): HandlerList = HANDLER_LIST
    }

    override fun getHandlers(): HandlerList = HANDLER_LIST
}
