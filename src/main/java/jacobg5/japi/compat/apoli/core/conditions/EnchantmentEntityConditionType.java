package jacobg5.japi.compat.apoli.core.conditions;

import io.github.apace100.apoli.condition.ConditionConfiguration;
import io.github.apace100.apoli.condition.context.EntityConditionContext;
import io.github.apace100.apoli.condition.type.EntityConditionType;
import io.github.apace100.apoli.data.ApoliDataTypes;
import io.github.apace100.apoli.data.TypedDataObjectFactory;
import io.github.apace100.apoli.util.Comparison;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import jacobg5.japi.JUtils;
import jacobg5.japi.compat.apoli.core.JApoliCore;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import org.jetbrains.annotations.NotNull;

public class EnchantmentEntityConditionType extends EntityConditionType {
    public static final TypedDataObjectFactory<EnchantmentEntityConditionType> DATA_FACTORY = TypedDataObjectFactory.simple(
            new SerializableData()
                    .add("enchantment", SerializableDataTypes.ENCHANTMENT)
                    .add("use_modifications", SerializableDataTypes.BOOLEAN, true)
                    .add("combine", SerializableDataTypes.BOOLEAN, true)
                    .add("comparison", ApoliDataTypes.COMPARISON)
                    .add("compare_to", SerializableDataTypes.INT),
            data -> new EnchantmentEntityConditionType(
                    data.get("enchantment"),
                    data.get("use_modifications"),
                    data.get("combine"),
                    data.get("comparison"),
                    data.get("compare_to")
            ),
            (conditionType, serializableData) -> serializableData.instance()
                    .set("enchantment", conditionType.enchantmentKey)
                    .set("use_modifications", conditionType.useModifications)
                    .set("combine", conditionType.combine)
                    .set("comparison", conditionType.comparison)
                    .set("compare_to", conditionType.compareTo)
    );
    private final RegistryKey<Enchantment> enchantmentKey;
    private final boolean useModifications;
    private final boolean combine;
    private final Comparison comparison;
    private final int compareTo;

    public EnchantmentEntityConditionType(RegistryKey<Enchantment> enchantmentKey, boolean useModifications, boolean combine, Comparison comparison, int compareTo) {
        this.enchantmentKey = enchantmentKey;
        this.useModifications = useModifications;
        this.combine = combine;
        this.comparison = comparison;
        this.compareTo = compareTo;
    }

    @Override
    public boolean test(EntityConditionContext context) {
        if (!(context.entity() instanceof LivingEntity livingEntity)) return false;
        RegistryEntry<Enchantment> entry = livingEntity.getRegistryManager().get(RegistryKeys.ENCHANTMENT).entryOf(enchantmentKey);

        int lvl = combine ? JUtils.totalLevel(livingEntity, entry) : JUtils.highestLevel(livingEntity, entry);
        return comparison.compare(lvl, compareTo);
    }

    @Override
    public @NotNull ConditionConfiguration<?> getConfig() {
        return JApoliCore.ENCHANTMENT_CONDITION;
    }
}
