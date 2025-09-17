package jacobg5.japi.mixin;

import jacobg5.japi.JKeys;
import net.minecraft.block.BlockState;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerInteractionManager.class)
public class ServerPlayerInteractionManagerMixin {
    @Shadow
    protected ServerWorld world;

    @Inject(method = "tryBreakBlock", at = @At("HEAD"), cancellable = true )
    public void tryBreakBlock(BlockPos pos, CallbackInfoReturnable<Boolean> info) {
        BlockState blockState = this.world.getBlockState(pos);
        if (blockState.isIn(JKeys.INDESTRUCTIBLE)) {
            info.setReturnValue(false);
            return;
        }
    }
}
