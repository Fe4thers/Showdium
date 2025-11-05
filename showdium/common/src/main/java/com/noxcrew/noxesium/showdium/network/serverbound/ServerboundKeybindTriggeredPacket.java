package com.noxcrew.noxesium.showdium.network.serverbound;

import com.noxcrew.noxesium.api.network.NoxesiumPacket;

public record ServerboundKeybindTriggeredPacket(String KeybindTriggered, Boolean pressedIn) implements NoxesiumPacket {
    /**
     * The type of keybind triggered
     */
}
