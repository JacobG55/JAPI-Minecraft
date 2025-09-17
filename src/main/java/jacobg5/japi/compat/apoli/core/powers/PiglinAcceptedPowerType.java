package jacobg5.japi.compat.apoli.core.powers;

import io.github.apace100.apoli.condition.EntityCondition;
import io.github.apace100.apoli.power.PowerConfiguration;
import io.github.apace100.apoli.power.type.PowerType;
import jacobg5.japi.compat.apoli.core.JApoliCore;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class PiglinAcceptedPowerType extends PowerType {
    public PiglinAcceptedPowerType(Optional<EntityCondition> condition) {
        super(condition);
    }

    @Override
    public @NotNull PowerConfiguration<?> getConfig() {
        return JApoliCore.PIGLIN_ACCEPTED_POWER;
    }
}
