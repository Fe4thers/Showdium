package com.noxcrew.noxesium.showdium.network;

import static com.noxcrew.noxesium.api.network.PacketCollection.client;
import static com.noxcrew.noxesium.api.network.PacketCollection.server;

import com.noxcrew.noxesium.api.network.PacketCollection;
import com.noxcrew.noxesium.api.network.payload.NoxesiumPayloadGroup;
import com.noxcrew.noxesium.showdium.network.clientbound.*;
import com.noxcrew.noxesium.showdium.network.serverbound.ServerboundKeybindTriggeredPacket;
import com.noxcrew.noxesium.showdium.network.serverbound.ServerboundPingAddPacket;
import com.noxcrew.noxesium.showdium.network.serverbound.ServerboundPingRemovePacket;

public class ShowdiumPackets {
    public static final PacketCollection INSTANCE = new PacketCollection();
    public static final NoxesiumPayloadGroup SERVER_KEYBIND_TRIGGERED =
            server(INSTANCE, "serverbound_keybind_triggered").add(ServerboundKeybindTriggeredPacket.class);
    public static final NoxesiumPayloadGroup SERVER_PING_ADD =
            server(INSTANCE, "serverbound_ping_add").add(ServerboundPingAddPacket.class);
    public static final NoxesiumPayloadGroup SERVER_PING_REMOVE =
            server(INSTANCE, "serverbound_ping_remove").add(ServerboundPingRemovePacket.class);
    public static final NoxesiumPayloadGroup CLIENT_KEYBIND_ADD =
            client(INSTANCE, "clientbound_keybind_add").add(ClientboundKeybindAddPacket.class);
    public static final NoxesiumPayloadGroup CLIENT_KEYBIND_REMOVE =
            client(INSTANCE, "clientbound_keybind_remove").add(ClientboundKeybindRemovePacket.class);
    public static final NoxesiumPayloadGroup CLIENT_PING_ADD =
            client(INSTANCE, "clientbound_ping_add").add(ClientboundPingAddPacket.class);
    public static final NoxesiumPayloadGroup CLIENT_PING_REMOVE =
            client(INSTANCE, "clientbound_ping_remove").add(ClientboundPingRemovePacket.class);
    public static final NoxesiumPayloadGroup CLIENT_KEYBIND_DISABLE =
            client(INSTANCE, "clientbound_keybind_disable").add(ClientboundKeybindDisable.class);
}
