package jacobg5.japi.compat.apoli.core.powers;

import io.github.apace100.apoli.condition.BlockCondition;
import io.github.apace100.apoli.condition.EntityCondition;
import io.github.apace100.apoli.data.TypedDataObjectFactory;
import io.github.apace100.apoli.power.PowerConfiguration;
import io.github.apace100.apoli.power.type.*;
import io.github.apace100.calio.data.SerializableData;
import jacobg5.japi.compat.apoli.core.JApoliCore;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class PreventBlockBreakPowerType extends PowerType {

    public static final TypedDataObjectFactory<PreventBlockBreakPowerType> DATA_FACTORY = PowerType.createConditionedDataFactory(
            new SerializableData()
                    .add("block_condition", BlockCondition.DATA_TYPE.optional(), Optional.empty()),
            (data, condition) -> new PreventBlockBreakPowerType(
                    data.get("block_condition"),
                    condition
            ),
            (powerType, serializableData) -> serializableData.instance()
                    .set("block_condition", powerType.blockCondition)
    );

    private final Optional<BlockCondition> blockCondition;

    public PreventBlockBreakPowerType(Optional<BlockCondition> blockCondition, Optional<EntityCondition> condition) {
        super(condition);
        this.blockCondition = blockCondition;
    }

    @Override
    public @NotNull PowerConfiguration<?> getConfig() {
        return JApoliCore.PREVENT_BLOCK_BREAK_POWER;
    }

    public boolean doesPrevent(World world, BlockPos pos) {
        return blockCondition.map(condition -> condition.test(world, pos)).orElse(true);
    }
}