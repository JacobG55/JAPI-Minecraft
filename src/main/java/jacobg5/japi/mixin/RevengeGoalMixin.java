package jacobg5.japi.mixin;

import jacobg5.japi.JLoaderData;
import jacobg5.japi.compat.apoli.JApoli;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.ai.goal.TrackTargetGoal;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RevengeGoal.class)
public abstract class RevengeGoalMixin extends TrackTargetGoal {

    public RevengeGoalMixin(MobEntity mob, boolean checkVisibility) {
        super(mob, checkVisibility);
    }

    @Inject(method = "canStart", at = @At("HEAD"), cancellable = true )
    public void canStart(CallbackInfoReturnable<Boolean> info) {
        LivingEntity attacker = this.mob.getAttacker();
        if (JLoaderData.APOLI && attacker != null) {
            if (JApoli.ignoreEntity(mob, attacker, true)) info.setReturnValue(false);
        }
    }
}
