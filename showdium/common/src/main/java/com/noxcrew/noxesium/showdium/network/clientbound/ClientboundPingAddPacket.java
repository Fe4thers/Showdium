package com.noxcrew.noxesium.showdium.network.clientbound;

import com.noxcrew.noxesium.api.network.NoxesiumPacket;
import org.joml.Vector3f;

import java.util.Optional;
import java.util.UUID;
import java.util.Vector;

public record ClientboundPingAddPacket(
        Integer color,
        String uuid,
        Vector3f location)
        implements NoxesiumPacket {
    /**
     * add a keybind to the listener
     */
}
