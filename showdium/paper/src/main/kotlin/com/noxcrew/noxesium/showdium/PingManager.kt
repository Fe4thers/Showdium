package com.noxcrew.noxesium.showdium

import com.noxcrew.noxesium.api.player.NoxesiumServerPlayer
import com.noxcrew.noxesium.showdium.network.clientbound.ClientboundPingAddPacket
import com.noxcrew.noxesium.showdium.network.clientbound.ClientboundPingRemovePacket
import org.joml.Vector3f
import java.util.UUID

public object PingManager {
    public fun addPingForPlayer(
        player: NoxesiumServerPlayer?,
        color: Int,
        uuid: UUID,
        loc: Vector3f,
    ) {
        player?.sendPacket(
            ClientboundPingAddPacket(
                color,
                uuid.toString(),
                loc,
            ),
        )
    }

    public fun removePingForPlayer(
        player: NoxesiumServerPlayer?,
        uuid: UUID,
    ) {
        player?.sendPacket(
            ClientboundPingRemovePacket(
                uuid.toString(),
            ),
        )
    }
}
