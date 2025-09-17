package jacobg5.japi.mixin;

import jacobg5.japi.JLoaderData;
import jacobg5.japi.compat.apoli.JApoli;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.TrackTargetGoal;
import net.minecraft.entity.mob.MobEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Predicate;

@Mixin(ActiveTargetGoal.class)
public abstract class ActiveTargetGoalMixin<T extends LivingEntity> extends TrackTargetGoal {
    @Nullable @Shadow
    protected LivingEntity targetEntity;
    @Shadow
    protected TargetPredicate targetPredicate;

    public ActiveTargetGoalMixin(MobEntity mob, boolean checkVisibility) {
        super(mob, checkVisibility);
    }

    @Inject(method = "<init>(Lnet/minecraft/entity/mob/MobEntity;Ljava/lang/Class;IZZLjava/util/function/Predicate;)V", at = @At(value = "RETURN"), cancellable = false )
    public void constructorReturn(MobEntity mob, Class<T> targetClass, int reciprocalChance, boolean checkVisibility, boolean checkCanNavigate, @Nullable Predicate<LivingEntity> targetPredicate, CallbackInfo info) {
        if (JLoaderData.APOLI) {
            Predicate<LivingEntity> predicate = entity -> !JApoli.ignoreEntity(mob, entity, false);

            @Nullable Predicate<LivingEntity> original = ((TargetPredicateAccessor)this.targetPredicate).getPredicate();
            if (original != null) predicate = predicate.and(original);

            ((TargetPredicateAccessor)this.targetPredicate).setPredicate(predicate);
        }
    }

    @Inject(method = "canStart", at = @At("RETURN"), cancellable = true )
    public void canStart(CallbackInfoReturnable<Boolean> info) {
        if (JLoaderData.APOLI && targetEntity != null) {
            if (JApoli.ignoreEntity(mob, targetEntity, false)) info.setReturnValue(false);
        }
    }
}
