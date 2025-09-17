package jacobg5.japi.mixin;

import jacobg5.japi.effect.JStatusEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StatusEffect.class)
public class StatusEffectMixin implements JStatusEffect {
    @Shadow @Final
    private int color;
    @Unique
    private int customColor;
    @Unique
    private boolean useCustomColor = false;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void constructor(StatusEffectCategory category, int color, CallbackInfo info) {
        customColor = color;
    }

    @Override
    public void JAPI$setCustomColor(int value) {
        customColor = value;
    }

    @Inject(method = "getColor", at = @At("HEAD"), cancellable = true)
    public void getColor(CallbackInfoReturnable<Integer> info) {
        if (useCustomColor) info.setReturnValue(customColor);
    }

    @Override
    public void JAPI$toggleCustomColor(boolean value) {
        useCustomColor = value;
    }

    @Override
    public boolean JAPI$usesCustomColor() {
        return useCustomColor;
    }
}