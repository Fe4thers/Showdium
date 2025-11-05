package com.noxcrew.noxesium.showdium.network;

import static com.noxcrew.noxesium.api.network.PacketCollection.client;
import static com.noxcrew.noxesium.api.network.PacketCollection.server;

import com.noxcrew.noxesium.api.network.PacketCollection;
import com.noxcrew.noxesium.api.network.payload.NoxesiumPayloadGroup;
import com.noxcrew.noxesium.showdium.network.clientbound.ClientboundKeybindAddPacket;
import com.noxcrew.noxesium.showdium.network.clientbound.ClientboundKeybindRemovePacket;
import com.noxcrew.noxesium.showdium.network.serverbound.ServerboundKeybindTriggeredPacket;

public class ShowdiumPackets {
    public static final PacketCollection INSTANCE = new PacketCollection();
    public static final NoxesiumPayloadGroup SERVER_KEYBIND_TRIGGERED =
            server(INSTANCE, "serverbound_keybind_triggered").add(ServerboundKeybindTriggeredPacket.class);
    public static final NoxesiumPayloadGroup CLIENT_KEYBIND_ADD =
            client(INSTANCE, "clientbound_keybind_add").add(ClientboundKeybindAddPacket.class);
    public static final NoxesiumPayloadGroup CLIENT_KEYBIND_REMOVE =
            client(INSTANCE, "clientbound_keybind_remove").add(ClientboundKeybindRemovePacket.class);
}
