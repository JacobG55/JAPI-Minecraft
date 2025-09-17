package jacobg5.japi.compat.apoli.core.powers;

import io.github.apace100.apoli.condition.BiEntityCondition;
import io.github.apace100.apoli.condition.EntityCondition;
import io.github.apace100.apoli.data.TypedDataObjectFactory;
import io.github.apace100.apoli.power.PowerConfiguration;
import io.github.apace100.apoli.power.type.PowerType;
import io.github.apace100.calio.data.SerializableData;
import jacobg5.japi.compat.apoli.core.JApoliCore;
import net.minecraft.entity.mob.MobEntity;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class EnemyOfPowerType extends PowerType {
    public static final TypedDataObjectFactory<EnemyOfPowerType> DATA_FACTORY = PowerType.createConditionedDataFactory(
            new SerializableData()
                    .add("entity_condition", EntityCondition.DATA_TYPE.optional(), Optional.empty())
                    .add("bientity_condition", BiEntityCondition.DATA_TYPE.optional(), Optional.empty()),
            (data, condition) -> new EnemyOfPowerType(
                    data.get("entity_condition"),
                    data.get("bientity_condition"),
                    condition
            ),
            (powerType, serializableData) -> serializableData.instance()
                    .set("entity_condition", powerType.entityCondition)
                    .set("bientity_condition", powerType.biEntityCondition)
    );

    private final Optional<EntityCondition> entityCondition;
    private final Optional<BiEntityCondition> biEntityCondition;

    public EnemyOfPowerType(Optional<EntityCondition> entityCondition, Optional<BiEntityCondition> biEntityCondition, Optional<EntityCondition> condition) {
        super(condition);
        this.entityCondition = entityCondition;
        this.biEntityCondition = biEntityCondition;
    }

    @Override
    public @NotNull PowerConfiguration<?> getConfig() {
        return JApoliCore.ENEMY_OF_POWER;
    }

    public boolean doesApply(MobEntity mob) {
        return entityCondition.map(condition -> condition.test(mob)).orElse(true)
                && biEntityCondition.map(condition -> condition.test(getHolder(), mob)).orElse(true);
    }
}
