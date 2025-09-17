package jacobg5.japi.compat.apoli.core.actions;

import io.github.apace100.apoli.action.ActionConfiguration;
import io.github.apace100.apoli.action.context.EntityActionContext;
import io.github.apace100.apoli.action.type.EntityActionType;
import jacobg5.japi.compat.apoli.core.JApoliCore;
import net.minecraft.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public class JumpEntityActionType extends EntityActionType {
    @Override
    public void accept(EntityActionContext context) {
        if (context.entity() instanceof LivingEntity livingEntity) {
            livingEntity.jump();
        }
    }

    @Override
    public @NotNull ActionConfiguration<?> getConfig() {
        return JApoliCore.JUMP_ACTION;
    }

}