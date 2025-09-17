package jacobg5.japi.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;

public record ConductivityComponent(float amount, boolean protectsLightning) {
    public static final Codec<ConductivityComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.FLOAT.optionalFieldOf("amount", 1F).forGetter(ConductivityComponent::amount),
            Codec.BOOL.optionalFieldOf("protects_when_equipped", false).forGetter(ConductivityComponent::protectsLightning)
    ).apply(instance, ConductivityComponent::new));
    public static final PacketCodec<ByteBuf, ConductivityComponent> PACKET_CODEC = PacketCodec.tuple(
            PacketCodecs.FLOAT,
            ConductivityComponent::amount,
            PacketCodecs.BOOL,
            ConductivityComponent::protectsLightning,
            ConductivityComponent::new
    );
}