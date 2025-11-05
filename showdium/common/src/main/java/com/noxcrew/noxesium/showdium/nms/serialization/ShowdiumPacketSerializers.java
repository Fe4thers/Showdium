package com.noxcrew.noxesium.showdium.nms.serialization;

import static com.noxcrew.noxesium.api.nms.serialization.PacketSerializerRegistry.registerSerializer;

import com.noxcrew.noxesium.showdium.network.clientbound.ClientboundKeybindAddPacket;
import com.noxcrew.noxesium.showdium.network.clientbound.ClientboundKeybindRemovePacket;
import com.noxcrew.noxesium.showdium.network.serverbound.ServerboundKeybindTriggeredPacket;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

/**
 * Defines all common Showdium serializers.
 */
public class ShowdiumPacketSerializers {
    /**
     * Registers all serializers.
     */
    public static void register() {
        registerSerializer(
                ClientboundKeybindAddPacket.class,
                StreamCodec.composite(
                        ByteBufCodecs.STRING_UTF8,
                        ClientboundKeybindAddPacket::KeyBindName,
                        ByteBufCodecs.BOOL,
                        ClientboundKeybindAddPacket::Client,
                        ByteBufCodecs.BOOL,
                        ClientboundKeybindAddPacket::singlePressTrigger,
                        ByteBufCodecs.optional(ByteBufCodecs.VAR_INT),
                        ClientboundKeybindAddPacket::delay,
                        ByteBufCodecs.optional(ByteBufCodecs.STRING_UTF8),
                        ClientboundKeybindAddPacket::clientQibImplementation,
                        ClientboundKeybindAddPacket::new));

        registerSerializer(
                ClientboundKeybindRemovePacket.class,
                StreamCodec.composite(
                        ByteBufCodecs.STRING_UTF8,
                        ClientboundKeybindRemovePacket::KeyBindName,
                        ClientboundKeybindRemovePacket::new));

        registerSerializer(
                ServerboundKeybindTriggeredPacket.class,
                StreamCodec.composite(
                        ByteBufCodecs.STRING_UTF8,
                        ServerboundKeybindTriggeredPacket::KeybindTriggered,
                        ByteBufCodecs.BOOL,
                        ServerboundKeybindTriggeredPacket::pressedIn,
                        ServerboundKeybindTriggeredPacket::new));
    }
}
