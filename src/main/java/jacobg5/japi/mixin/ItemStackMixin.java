package jacobg5.japi.mixin;

import jacobg5.japi.component.DamageEffectsComponent;
import jacobg5.japi.component.JComponents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Inject(method = "postHit", at = @At("RETURN"), cancellable = false )
    private void postHit(LivingEntity target, PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        if (target == null) return;
        ItemStack stack = (ItemStack)(Object)this;
        DamageEffectsComponent damageEffects = stack.get(JComponents.DAMAGE_EFFECTS);
        if (damageEffects != null) {
            damageEffects.forEachEffect(target::addStatusEffect);
        }
    }
}
