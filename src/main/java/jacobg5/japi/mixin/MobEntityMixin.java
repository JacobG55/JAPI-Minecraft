package jacobg5.japi.mixin;

import jacobg5.japi.entity.JMobEntity;
import jacobg5.japi.events.InitGoalsCallback;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MobEntity.class)
public abstract class MobEntityMixin implements JMobEntity {
    @Shadow @Final
    protected GoalSelector goalSelector;
    @Shadow @Final
    protected GoalSelector targetSelector;

    @Inject(method = "<init>", at = @At(value = "RETURN"), cancellable = false )
    public void constructorReturn(EntityType<? extends MobEntity> entityType, World world, CallbackInfo info) {
        if (world != null && !world.isClient) {
            MobEntity mob = (MobEntity)(Object)this;
            JMobEntity accessor = (JMobEntity)mob;
            InitGoalsCallback.EVENT.invoker().modify(mob, accessor.JAPI$getGoalSelector(), accessor.JAPI$getTargetSelector());
        }
    }

    @Override
    public GoalSelector JAPI$getGoalSelector() {
        return goalSelector;
    }
    @Override
    public GoalSelector JAPI$getTargetSelector() {
        return targetSelector;
    }
}
