package com.noxcrew.noxesium.showdium

import org.bukkit.entity.Player
import org.bukkit.event.HandlerList
import org.bukkit.event.player.PlayerEvent

public class NoxesiumPlayerServerKeybindTriggerEvent(
    player: Player,
    public val keybindTriggered: String,
    public val pressedIn: Boolean,
) : PlayerEvent(player) {
    public companion object {
        @JvmStatic
        public val HANDLER_LIST: HandlerList = HandlerList()

        @JvmStatic
        public fun getHandlerList(): HandlerList = HANDLER_LIST
    }

    override fun getHandlers(): HandlerList = HANDLER_LIST
}
