package com.noxcrew.noxesium.showdium.network.clientbound;

import com.noxcrew.noxesium.api.network.NoxesiumPacket;
import java.util.Optional;

public record ClientboundKeybindAddPacket(
        String KeyBindName,
        Boolean Client,
        Boolean singlePressTrigger,
        Optional<Integer> delay,
        Optional<String> clientQibImplementation)
        implements NoxesiumPacket {
    /**
     * add a keybind to the listener
     */
}
