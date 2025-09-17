package jacobg5.japi.mixin;

import java.util.Optional;

import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Pair;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import jacobg5.japi.block.JBlockMaps;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

@Mixin(HoeItem.class)
public abstract class HoeItemMixin {
    @Inject(method = "useOnBlock", at = @At("HEAD"), cancellable = true )
    private void useOnBlock(ItemUsageContext context, CallbackInfoReturnable<ActionResult> info) {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        Direction side = context.getSide();
        ItemStack stack = context.getStack();

        if (side != Direction.DOWN && world.getBlockState(blockPos.up()).isAir()) {
            PlayerEntity playerEntity = context.getPlayer();
            Pair<Boolean, Optional<BlockState>> pair = JBlockMaps.TILL.getConversionAndDrop(world, blockPos, side, stack, playerEntity, new ItemPlacementContext(context));
            if (pair.getLeft()) {
                world.playSound(playerEntity, blockPos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);

                if (!world.isClient && pair.getRight().isPresent()) {
                    BlockState path = pair.getRight().get();
                    world.setBlockState(blockPos, path, Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
                    world.emitGameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Emitter.of(playerEntity, path));
                    if (playerEntity != null) {
                        stack.damage(1, playerEntity, LivingEntity.getSlotForHand(context.getHand()));
                    }
                }
                info.setReturnValue(ActionResult.success(world.isClient));
            }
        }
    }
}
