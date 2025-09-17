package jacobg5.japi.mixin;

import jacobg5.japi.JKeys;
import jacobg5.japi.JLoaderData;
import jacobg5.japi.compat.trinkets.JTrinkets;
import jacobg5.japi.component.ConductivityComponent;
import jacobg5.japi.component.JComponents;
import jacobg5.japi.item.JItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Predicate;

@Mixin(Entity.class)
public class EntityMixin {
    @Inject(method = "onStruckByLightning", at = @At("HEAD"), cancellable = true )
    public void onStruckByLightning(ServerWorld world, LightningEntity lightning, CallbackInfo ci) {
        Entity entity = (Entity)(Object)this;

        if (entity instanceof LivingEntity living) {
            Predicate<ItemStack> predicate = stack -> {
                ConductivityComponent component = stack.get(JComponents.CONDUCTIVITY);
                if (component != null) return component.protectsLightning();
                return stack.isIn(JKeys.CONDUCTIVE_EQUIPMENT);
            };

            if (JLoaderData.TRINKETS) {
                if (JTrinkets.getEquipped(living, predicate, null).isPresent()) {
                    ci.cancel();
                    return;
                }
            }
            for (ItemStack stack : living.getArmorItems()) {
                if (stack.isEmpty() || !predicate.test(stack)) {
                    ci.cancel();
                    return;
                }
            }
        }
    }
}
