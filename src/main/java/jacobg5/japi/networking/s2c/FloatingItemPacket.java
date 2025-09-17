package jacobg5.japi.networking.s2c;

import jacobg5.japi.JAPI;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;

public record FloatingItemPacket(ItemStack stack) implements CustomPayload {
    public static final CustomPayload.Id<FloatingItemPacket> PACKET_ID = new CustomPayload.Id<>(JAPI.identifier("floating_item_display"));
    public static final PacketCodec<RegistryByteBuf, FloatingItemPacket> PACKET_CODEC = ItemStack.PACKET_CODEC.xmap(FloatingItemPacket::new, FloatingItemPacket::stack);
    @Override
    public Id<? extends CustomPayload> getId() {
        return PACKET_ID;
    }
}