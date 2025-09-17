package jacobg5.japi.mixin;

import jacobg5.japi.block.JBlockEntityType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(BlockEntityType.class)
public class BlockEntityTypeMixin implements JBlockEntityType {
    @Unique
    private final ArrayList<Block> additionalBlocks = new ArrayList<>(1);

    @Inject(method = "supports", at = @At("RETURN"), cancellable = true )
    public void supports(BlockState state, CallbackInfoReturnable<Boolean> info) {
        if (info.getReturnValue()) return;
        if (additionalBlocks.contains(state.getBlock())) {
            info.setReturnValue(true);
            return;
        }
    }

    @Override
    public void JAPI$addBlocks(Block... blocks) { additionalBlocks.addAll(Arrays.asList(blocks)); }
}
