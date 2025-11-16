package com.noxcrew.noxesium.showdium;

import com.noxcrew.noxesium.api.ClientNoxesiumEntrypoint;
import com.noxcrew.noxesium.api.feature.NoxesiumFeature;
import com.noxcrew.noxesium.api.network.PacketCollection;
import com.noxcrew.noxesium.api.registry.RegistryCollection;
import com.noxcrew.noxesium.showdium.network.ShowdiumPackets;
import com.noxcrew.noxesium.showdium.nms.serialization.ShowdiumPacketSerializers;
import com.noxcrew.noxesium.showdium.registry.ShowdiumGameComponent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.jetbrains.annotations.Nullable;

/**
 * Sets up an entrypoint into Noxesium's APIs.
 */
public class ShowdiumEntrypoint implements ClientNoxesiumEntrypoint {

    @Override
    public String getId() {
        return "showdium";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    @Nullable
    public URL getEncryptionKey() {
        return ShowdiumEntrypoint.class.getClassLoader().getResource("encryption-key.aes");
    }

    public static KeyBindHandler keyBindHandler;
    public static QibBehaviorExecutor qibBehaviorExecutor;
    public static ShowdiumPacketHandling packetHandling;
    public static ElytraListener elytraListener;

    @Override
    public void preInitialize() {
        ShowdiumPacketSerializers.register();
    }

    @Override
    public void initialize() {
        qibBehaviorExecutor = new QibBehaviorExecutor();
        keyBindHandler = new KeyBindHandler();
        packetHandling = new ShowdiumPacketHandling();
        elytraListener = new ElytraListener();
    }

    @Override
    public Collection<NoxesiumFeature> getAllFeatures() {
        var features = new ArrayList<NoxesiumFeature>();
        features.add(keyBindHandler);
        features.add(packetHandling);
        features.add(qibBehaviorExecutor);
        features.add(elytraListener);
        return features;
    }

    @Override
    public Collection<PacketCollection> getPacketCollections() {
        return List.of(ShowdiumPackets.INSTANCE);
    }

    @Override
    public Collection<RegistryCollection<?>> getRegistryCollections() {
        return List.of(ShowdiumGameComponent.INSTANCE);
    }
}
