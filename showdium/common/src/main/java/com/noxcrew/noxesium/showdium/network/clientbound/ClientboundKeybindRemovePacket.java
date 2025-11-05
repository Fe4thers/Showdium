package com.noxcrew.noxesium.showdium.network.clientbound;

import com.noxcrew.noxesium.api.network.NoxesiumPacket;

public record ClientboundKeybindRemovePacket(String KeyBindName) implements NoxesiumPacket {
    /**
     * remove a keybind from the listener
     */
}
