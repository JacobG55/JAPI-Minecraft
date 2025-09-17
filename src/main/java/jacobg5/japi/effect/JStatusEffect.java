package jacobg5.japi.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.entry.RegistryEntry;

public interface JStatusEffect {
    void JAPI$setCustomColor(int value);
    void JAPI$toggleCustomColor(boolean value);
    boolean JAPI$usesCustomColor();

    static void setCustomColor(RegistryEntry<StatusEffect> effect, int color) {
        ((JStatusEffect)effect.value()).JAPI$setCustomColor(color);
    }

    static void toggleCustomColor(RegistryEntry<StatusEffect> effect, boolean value) {
        ((JStatusEffect)effect.value()).JAPI$toggleCustomColor(value);
    }

    static void toggleCustomColor(RegistryEntry<StatusEffect> effect) {
        JStatusEffect jEffect = (JStatusEffect)effect.value();
        jEffect.JAPI$toggleCustomColor(!jEffect.JAPI$usesCustomColor());
    }
}
