package jacobg5.japi.mixin;

import jacobg5.japi.JKeys;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(ItemEntity.class)
public class ItemEntityMixin {
    @Unique
    private Optional<Boolean> isInFireTag = Optional.empty();

    @Inject(method = "isFireImmune", at = @At("RETURN"), cancellable = true )
    private void isFireImmune(CallbackInfoReturnable<Boolean> info) {
        if (info.getReturnValue()) return;

        if (isInFireTag.isEmpty()) {
            ItemEntity itemEntity = (ItemEntity)(Object)this;
            ItemStack stack = itemEntity.getStack();
            isInFireTag = Optional.of(stack.isIn(JKeys.FIREPROOF));
        }
        if (isInFireTag.get()) info.setReturnValue(true);
    }
}
