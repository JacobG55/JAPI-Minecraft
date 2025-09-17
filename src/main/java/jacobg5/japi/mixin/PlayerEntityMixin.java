package jacobg5.japi.mixin;

import jacobg5.japi.JKeys;
import jacobg5.japi.JLoaderData;
import jacobg5.japi.compat.apoli.JApoli;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    @Inject(method = "isBlockBreakingRestricted", at = @At("HEAD"), cancellable = true )
    public void isBlockBreakingRestricted(World world, BlockPos pos, GameMode gameMode, CallbackInfoReturnable<Boolean> info) {
        PlayerEntity player = (PlayerEntity)(Object)this;
        BlockState blockState = world.getBlockState(pos);
        if (gameMode == GameMode.ADVENTURE && blockState.isIn(JKeys.ADVENTURE_BREAKABLE)) {
            info.setReturnValue(player.canModifyBlocks());
            return;
        }
        if (gameMode != GameMode.CREATIVE) {
            if (blockState.isIn(JKeys.INDESTRUCTIBLE)) {
                info.setReturnValue(true);
                return;
            }
            if (JLoaderData.APOLI) {
                if (JApoli.preventsBreaking(player, world, pos)) {
                    info.setReturnValue(true);
                    return;
                }
            }
        }
    }
}
