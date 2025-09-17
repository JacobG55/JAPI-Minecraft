package jacobg5.japi.containers;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class JDamageSource {
    public final Identifier identifier;
    public final RegistryKey<DamageType> key;

    public JDamageSource(Identifier identifier) {
        this.identifier = identifier;
        this.key = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, identifier);
    }

    public DamageSource create(World world) {
        return new DamageSource(world.getDamageSources().registry.entryOf(key));
    }

    public DamageSource create(World world, @Nullable Entity attacker) {
        return new DamageSource(world.getDamageSources().registry.entryOf(key), attacker);
    }

    public DamageSource create(World world, @Nullable Entity source, @Nullable Entity attacker) {
        return new DamageSource(world.getDamageSources().registry.entryOf(key), source, attacker);
    }
}
