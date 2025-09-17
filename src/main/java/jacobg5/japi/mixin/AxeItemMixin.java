package jacobg5.japi.mixin;

import jacobg5.japi.block.JBlockMaps;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Pair;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(AxeItem.class)
public abstract class AxeItemMixin {
    @Shadow
    private static boolean shouldCancelStripAttempt(ItemUsageContext context) {
        return false;
    }
    @Inject(method = "useOnBlock", at = @At("HEAD"), cancellable = true )
    private void useOnBlock(ItemUsageContext context, CallbackInfoReturnable<ActionResult> info) {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        Direction side = context.getSide();
        ItemStack stack = context.getStack();

        if (!shouldCancelStripAttempt(context)) {
            PlayerEntity playerEntity = context.getPlayer();
            Pair<Boolean, Optional<BlockState>> pair = JBlockMaps.SCRAPE.getConversionAndDrop(world, blockPos, side, stack, playerEntity, null);
            Optional<BlockState> output = pair.getRight();
            boolean found = false;

            if (pair.getLeft()) {
                world.playSound(playerEntity, blockPos, SoundEvents.ITEM_AXE_SCRAPE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.syncWorldEvent(playerEntity, WorldEvents.BLOCK_SCRAPED, blockPos, 0);
                found = true;
            }

            pair = JBlockMaps.STRIP.getConversionAndDrop(world, blockPos, side, stack, playerEntity, null);

            if (pair.getLeft()) {
                world.playSound(playerEntity, blockPos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
                if (output.isEmpty()) output = pair.getRight();
                found = true;
            }

            if (found) {
                if (!world.isClient && output.isPresent()) {
                    BlockState path = output.get();
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
