package jacobg5.japi.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.function.Predicate;

@Mixin(TargetPredicate.class)
public interface TargetPredicateAccessor {
    @Accessor("predicate")
    void setPredicate(@Nullable Predicate<LivingEntity> predicate);

    @Accessor("predicate")
    @Nullable Predicate<LivingEntity> getPredicate();
}
