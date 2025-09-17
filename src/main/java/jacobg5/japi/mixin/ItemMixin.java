package jacobg5.japi.mixin;

import jacobg5.japi.JAPI;
import jacobg5.japi.component.DamageEffectsComponent;
import jacobg5.japi.component.JComponents;
import jacobg5.japi.item.JItem;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Item.class)
public class ItemMixin implements JItem.Interface {
    @Unique
    private float baseConductivity = 0F;

    @Inject(method = "appendTooltip", at = @At("RETURN"), cancellable = false )
    private void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type, CallbackInfo ci) {
        DamageEffectsComponent damageEffects = stack.get(JComponents.DAMAGE_EFFECTS);
        if (damageEffects != null) {
            damageEffects.buildTooltip(tooltip::add, 1.0F, context.getUpdateTickRate());
        }
    }

    @Override
    public void JAPI$setConductivity(float conductivity) {
        baseConductivity = conductivity;
    }

    @Override
    public float JAPI$getConductivity() {
        return baseConductivity;
    }
}
