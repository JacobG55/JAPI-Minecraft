package jacobg5.japi.component;

import jacobg5.japi.JAPI;
import jacobg5.japi.JMod;
import jacobg5.japi.JModModule;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class JComponents extends JModModule {
    public static final ComponentType<DamageEffectsComponent> DAMAGE_EFFECTS = register("damage_effects",
            ComponentType.<DamageEffectsComponent>builder().codec(DamageEffectsComponent.CODEC).packetCodec(DamageEffectsComponent.PACKET_CODEC).build());

    public static final ComponentType<ConductivityComponent> CONDUCTIVITY = register("conductivity",
            ComponentType.<ConductivityComponent>builder().codec(ConductivityComponent.CODEC).packetCodec(ConductivityComponent.PACKET_CODEC).build());

    private static <T> ComponentType<T> register(String id, ComponentType<T> componentType) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, JAPI.identifier(id), componentType);
    }

    @Override
    public void init(JMod mod) {}
}
