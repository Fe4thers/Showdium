package com.noxcrew.noxesium.showdium

import com.noxcrew.noxesium.api.player.NoxesiumServerPlayer
import com.noxcrew.noxesium.showdium.network.clientbound.ClientboundKeybindAddPacket
import com.noxcrew.noxesium.showdium.network.clientbound.ClientboundKeybindDisable
import com.noxcrew.noxesium.showdium.network.clientbound.ClientboundKeybindRemovePacket
import java.util.Optional

public object KeybindManager {
    public fun registerKeybindForPlayer(
        player: NoxesiumServerPlayer?,
        keyBindName: String,
        client: Boolean,
        singlePressTrigger: Boolean,
        delay: Int,
        qib: String,
    ) {
        player?.sendPacket(
            ClientboundKeybindAddPacket(
                keyBindName,
                client,
                singlePressTrigger,
                Optional.of(delay),
                Optional
                    .of(qib),
            ),
        )
    }

    public fun removeKeybindForPlayer(
        player: NoxesiumServerPlayer?,
        keyBindName: String,
    ) {
        player?.sendPacket(
            ClientboundKeybindRemovePacket(
                keyBindName,
            ),
        )
    }

    public fun disableKeybindForPlayer(
        player: NoxesiumServerPlayer?,
        keyBindName: String,
    ) {
        player?.sendPacket(
            ClientboundKeybindDisable(
                keyBindName,
            ),
        )
    }
}
