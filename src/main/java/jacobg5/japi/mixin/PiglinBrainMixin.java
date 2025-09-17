package jacobg5.japi.mixin;

import jacobg5.japi.JKeys;
import jacobg5.japi.JLoaderData;
import jacobg5.japi.JUtils;
import jacobg5.japi.compat.apoli.JApoli;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PiglinBrain.class)
public abstract class PiglinBrainMixin {
    @Inject(method = "wearsGoldArmor", at = @At("RETURN"), cancellable = true )
    private static void wearsGoldArmor(LivingEntity entity, CallbackInfoReturnable<Boolean> info) {
        if (info.getReturnValue()) return;
        if (JLoaderData.APOLI) {
            if (JApoli.checkPiglinPower(entity)) {
                info.setReturnValue(true);
                return;
            }
        }
        if (JUtils.getEquipped(entity, stack -> stack.isIn(JKeys.PIGLIN_FRIENDLY_EQUIPMENT)).isPresent()) {
            info.setReturnValue(true);
            return;
        }
    }

    @Inject(method = "acceptsForBarter", at = @At("RETURN"), cancellable = true )
    private static void acceptsForBarter(ItemStack stack, CallbackInfoReturnable<Boolean> info) {
        if (info.getReturnValue()) return;
        if (stack.isIn(JKeys.PIGLIN_BARTER_ITEM)) {
            info.setReturnValue(true);
            return;
        }
    }
}
