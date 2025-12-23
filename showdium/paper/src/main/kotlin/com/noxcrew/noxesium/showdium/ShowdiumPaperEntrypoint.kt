package com.noxcrew.noxesium.showdium

import com.noxcrew.noxesium.api.NoxesiumEntrypoint
import com.noxcrew.noxesium.api.feature.NoxesiumFeature
import com.noxcrew.noxesium.api.network.PacketCollection
import com.noxcrew.noxesium.api.registry.RegistryCollection
import com.noxcrew.noxesium.showdium.network.ShowdiumPackets
import com.noxcrew.noxesium.showdium.nms.serialization.ShowdiumPacketSerializers
import com.noxcrew.noxesium.showdium.registry.ShowdiumGameComponent
import java.net.URL

/**
 * Implements an example Noxesium entrypoint on Paper.
 */
public class ShowdiumPaperEntrypoint : NoxesiumEntrypoint {
    init {
        ShowdiumPacketSerializers.register()
    }

    override fun getId(): String = "showdium"

    override fun getRegistryCollections(): Collection<RegistryCollection<*>> =
        listOf(
            ShowdiumGameComponent.INSTANCE,
        )

    override fun getAllFeatures(): Collection<NoxesiumFeature> =
        listOf(
            ShowdiumServerPacketHandeling(),
        )

    override fun getPacketCollections(): Collection<PacketCollection> =
        listOf(
            ShowdiumPackets.INSTANCE,
        )

    override fun getEncryptionKey(): URL = ShowdiumPaperEntrypoint::class.java.getClassLoader().getResource("encryption-key.aes")
}
