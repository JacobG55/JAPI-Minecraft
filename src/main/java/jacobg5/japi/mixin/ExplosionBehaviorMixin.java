package jacobg5.japi.mixin;

import jacobg5.japi.JKeys;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(ExplosionBehavior.class)
public class ExplosionBehaviorMixin {

    @Inject(method = "getBlastResistance", at = @At("HEAD"), cancellable = true )
    public void getBlastResistance(Explosion explosion, BlockView world, BlockPos pos, BlockState blockState, FluidState fluidState, CallbackInfoReturnable<Optional<Float>> cir) {
        Optional<Float> op = Optional.empty();
        if (blockState.isIn(JKeys.BLAST_PROOF)) {
            op = Optional.of(3600000.0F);
        }
        else if (blockState.isIn(JKeys.FRAGILE)) {
            op = Optional.of(0f);
        }
        if (op.isPresent()) {
            cir.setReturnValue(op.map(val -> Math.max(val, fluidState.getBlastResistance())));
        }
    }
}
