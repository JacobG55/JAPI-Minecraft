package jacobg5.japi.component;

import com.google.common.collect.Iterables;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.potion.Potion;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Util;
import net.minecraft.util.math.ColorHelper;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public record DamageEffectsComponent(Optional<RegistryEntry<Potion>> potion, Optional<Integer> customColor, List<StatusEffectInstance> customEffects, boolean showInTooltip) {
    private static final Codec<DamageEffectsComponent> BASE_CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            Potion.CODEC.optionalFieldOf("potion").forGetter(DamageEffectsComponent::potion),
                            Codec.INT.optionalFieldOf("custom_color").forGetter(DamageEffectsComponent::customColor),
                            StatusEffectInstance.CODEC.listOf().optionalFieldOf("custom_effects", List.of()).forGetter(DamageEffectsComponent::customEffects),
                            Codec.BOOL.optionalFieldOf("show_in_tooltip", true).forGetter(DamageEffectsComponent::showInTooltip)
                    )
                    .apply(instance, DamageEffectsComponent::new)
    );
    public static final Codec<DamageEffectsComponent> CODEC = Codec.withAlternative(BASE_CODEC, Potion.CODEC, DamageEffectsComponent::new);
    public static final PacketCodec<RegistryByteBuf, DamageEffectsComponent> PACKET_CODEC = PacketCodec.tuple(
            Potion.PACKET_CODEC.collect(PacketCodecs::optional),
            DamageEffectsComponent::potion,
            PacketCodecs.INTEGER.collect(PacketCodecs::optional),
            DamageEffectsComponent::customColor,
            StatusEffectInstance.PACKET_CODEC.collect(PacketCodecs.toList()),
            DamageEffectsComponent::customEffects,
            PacketCodecs.BOOL,
            DamageEffectsComponent::showInTooltip,
            DamageEffectsComponent::new
    );

    public DamageEffectsComponent(RegistryEntry<Potion> potion) {
        this(Optional.of(potion), Optional.empty(), List.of(), true);
    }

    public DamageEffectsComponent(List<StatusEffectInstance> customEffects) {
        this(Optional.empty(), Optional.empty(), customEffects, true);
    }

    public static ItemStack createStack(Item item, List<StatusEffectInstance> customEffects) {
        ItemStack itemStack = new ItemStack(item);
        itemStack.set(JComponents.DAMAGE_EFFECTS, new DamageEffectsComponent(customEffects));
        return itemStack;
    }

    public Iterable<StatusEffectInstance> getEffects() {
        if (this.potion.isEmpty()) return this.customEffects;
        else return this.customEffects.isEmpty() ? this.potion.get().value().getEffects() : Iterables.concat(this.potion.get().value().getEffects(), this.customEffects);
    }

    public void forEachEffect(Consumer<StatusEffectInstance> effectConsumer) {
        if (this.potion.isPresent()) {
            for (StatusEffectInstance statusEffectInstance : this.potion.get().value().getEffects()) {
                effectConsumer.accept(new StatusEffectInstance(statusEffectInstance));
            }
        }
        for (StatusEffectInstance statusEffectInstance : this.customEffects) {
            effectConsumer.accept(new StatusEffectInstance(statusEffectInstance));
        }
    }

    public DamageEffectsComponent with(RegistryEntry<Potion> potion) {
        return new DamageEffectsComponent(Optional.of(potion), this.customColor, this.customEffects, true);
    }

    public DamageEffectsComponent with(StatusEffectInstance customEffect) {
        return new DamageEffectsComponent(this.potion, this.customColor, Util.withAppended(this.customEffects, customEffect), true);
    }

    public int getColor() {
        return this.customColor.orElseGet(() -> getColor(this.getEffects()));
    }

    public static int getColor(RegistryEntry<Potion> potion) {
        return getColor(potion.value().getEffects());
    }

    public static int getColor(Iterable<StatusEffectInstance> effects) {
        return mixColors(effects).orElse(-13083194);
    }

    public static OptionalInt mixColors(Iterable<StatusEffectInstance> effects) {
        int i = 0;
        int j = 0;
        int k = 0;
        int l = 0;

        for (StatusEffectInstance statusEffectInstance : effects) {
            if (statusEffectInstance.shouldShowParticles()) {
                int m = statusEffectInstance.getEffectType().value().getColor();
                int n = statusEffectInstance.getAmplifier() + 1;
                i += n * ColorHelper.Argb.getRed(m);
                j += n * ColorHelper.Argb.getGreen(m);
                k += n * ColorHelper.Argb.getBlue(m);
                l += n;
            }
        }

        return l == 0 ? OptionalInt.empty() : OptionalInt.of(ColorHelper.Argb.getArgb(i / l, j / l, k / l));
    }

    public boolean hasEffects() {
        return !this.customEffects.isEmpty() || this.potion.isPresent() && !this.potion.get().value().getEffects().isEmpty();
    }

    public List<StatusEffectInstance> customEffects() {
        return this.customEffects.stream().map(StatusEffectInstance::new).collect(Collectors.toList());
    }

    public void buildTooltip(Consumer<Text> textConsumer, float durationMultiplier, float tickRate) {
        if (!showInTooltip) return;
        textConsumer.accept(Text.translatable("japi.item.component.tooltip").withColor(Colors.LIGHT_GRAY));
        net.minecraft.component.type.PotionContentsComponent.buildTooltip(this.getEffects(), textConsumer, durationMultiplier, tickRate);
    }
}
