package jacobg5.japi.compat.apoli;

import io.github.apace100.apoli.component.PowerHolderComponent;
import jacobg5.japi.JMod;
import jacobg5.japi.JModModule;
import jacobg5.japi.compat.apoli.core.JApoliCore;
import jacobg5.japi.compat.apoli.core.powers.EnemyOfPowerType;
import jacobg5.japi.compat.apoli.core.powers.IgnoredByPowerType;
import jacobg5.japi.compat.apoli.core.powers.PiglinAcceptedPowerType;
import jacobg5.japi.compat.apoli.core.powers.PreventBlockBreakPowerType;
import jacobg5.japi.events.InitGoalsCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Contains custom power data for {@link io.github.apace100.apoli.Apoli}
 */
public class JApoli extends JModModule {
    @Override
    public void init(JMod mod) {
        mod.addModule("apoli_powers", JApoliCore::new);

        InitGoalsCallback.EVENT.register((mob, goalSelector, targetSelector) -> {
            targetSelector.add(3, new ActiveTargetGoal<>(mob, LivingEntity.class, true, (living) -> JApoli.enemyOfEntity(mob, living)));
        });
    }

    public static boolean checkPiglinPower(Entity entity) {
        return PowerHolderComponent.hasPowerType(entity, PiglinAcceptedPowerType.class);
    }

    public static boolean ignoreEntity(LivingEntity entity, Entity target, Boolean isRevenge) {
        return PowerHolderComponent.hasPowerType(target, IgnoredByPowerType.class, p -> p.doesApply(entity, isRevenge));
    }

    public static boolean enemyOfEntity(MobEntity mob, LivingEntity target) {
        return PowerHolderComponent.hasPowerType(target, EnemyOfPowerType.class, p -> p.doesApply(mob));
    }

    public static boolean preventsBreaking(Entity entity, World world, BlockPos pos) {
        return PowerHolderComponent.hasPowerType(entity, PreventBlockBreakPowerType.class, p -> p.doesPrevent(world, pos));
    }
}
