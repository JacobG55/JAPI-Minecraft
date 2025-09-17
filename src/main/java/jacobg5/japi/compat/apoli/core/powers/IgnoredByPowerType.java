package jacobg5.japi.compat.apoli.core.powers;

import io.github.apace100.apoli.condition.BiEntityCondition;
import io.github.apace100.apoli.condition.EntityCondition;
import io.github.apace100.apoli.data.TypedDataObjectFactory;
import io.github.apace100.apoli.power.PowerConfiguration;
import io.github.apace100.apoli.power.type.PowerType;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import jacobg5.japi.compat.apoli.core.JApoliCore;
import net.minecraft.entity.Entity;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class IgnoredByPowerType extends PowerType {
    public static final TypedDataObjectFactory<IgnoredByPowerType> DATA_FACTORY = PowerType.createConditionedDataFactory(
            new SerializableData()
                    .add("entity_condition", EntityCondition.DATA_TYPE.optional(), Optional.empty())
                    .add("bientity_condition", BiEntityCondition.DATA_TYPE.optional(), Optional.empty())
                    .add("applies_to_revenge", SerializableDataTypes.BOOLEAN, false),
            (data, condition) -> new IgnoredByPowerType(
                    data.get("entity_condition"),
                    data.get("bientity_condition"),
                    data.get("applies_to_revenge"),
                    condition
            ),
            (powerType, serializableData) -> serializableData.instance()
                    .set("entity_condition", powerType.entityCondition)
                    .set("bientity_condition", powerType.biEntityCondition)
                    .set("applies_to_revenge", powerType.appliesToRevenge)
    );

    private final Optional<EntityCondition> entityCondition;
    private final Optional<BiEntityCondition> biEntityCondition;
    private final Boolean appliesToRevenge;

    public IgnoredByPowerType(Optional<EntityCondition> entityCondition, Optional<BiEntityCondition> biEntityCondition, Boolean appliesToRevenge, Optional<EntityCondition> condition) {
        super(condition);
        this.entityCondition = entityCondition;
        this.biEntityCondition = biEntityCondition;
        this.appliesToRevenge = appliesToRevenge;
    }

    @Override
    public @NotNull PowerConfiguration<?> getConfig() {
        return JApoliCore.IGNORED_BY_POWER;
    }

    public boolean doesApply(Entity entity, Boolean fromRevenge) {
        if (fromRevenge && !appliesToRevenge) return false;
        return entityCondition.map(condition -> condition.test(entity)).orElse(true)
                && biEntityCondition.map(condition -> condition.test(getHolder(), entity)).orElse(true);
    }
}