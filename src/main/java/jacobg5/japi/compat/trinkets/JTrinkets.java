package jacobg5.japi.compat.trinkets;

import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * Helper methods for {@link dev.emi.trinkets.api.TrinketsApi}
 */
public class JTrinkets {
    /**
     * {@return Searches all of an entity's trinket slots for an item matching the predicate}
     *
     * @param entity entity to check slots from
     * @param check stack being queried for
     * @param slotId trinket slot id to check within
     */
    public static Optional<ItemStack> getEquipped(LivingEntity entity, Predicate<ItemStack> check, @Nullable String slotId) {
        Optional<TrinketComponent> component = TrinketsApi.getTrinketComponent(entity);
        if (component.isPresent()) {
            for (var equipped : component.get().getAllEquipped()) {
                if (slotId != null) if (!slotId.equalsIgnoreCase(equipped.getLeft().getId())) continue;
                ItemStack stack = equipped.getRight();
                if (check.test(stack)) return Optional.of(stack);
            }
        }
        return Optional.empty();
    }

    /**
     * {@return Searches all of an entity's trinket slots for an item matching the predicate}
     *
     * @param entity entity to check slots from
     * @param check stack being queried for
     */
    public static Optional<ItemStack> getEquipped(LivingEntity entity, Predicate<ItemStack> check) {
        return getEquipped(entity, check, null);
    }

    /**
     * {@return Returns a total number of items matching the predicate within an entity's trinket slots}
     *
     * @param entity entity to check slots from
     * @param check stack being queried for
     */
    public static int equipmentCount(LivingEntity entity, Predicate<ItemStack> check, @Nullable String slotId) {
        Optional<TrinketComponent> component = TrinketsApi.getTrinketComponent(entity);
        int count = 0;
        if (component.isPresent()) {
            for (var equipped : component.get().getAllEquipped()) {
                if (slotId != null) if (!slotId.equals(equipped.getLeft().getId())) continue;
                ItemStack stack = equipped.getRight();
                if (check.test(stack)) count++;
            }
        }
        return count;
    }

    /**
     * {@return Returns the highest level of a specified enchantment equipped on an entity}
     *
     * @param entity entity to check slots from
     * @param enchantment enchantment being queried for
     */
    public static int highestLevel(LivingEntity entity, RegistryEntry<Enchantment> enchantment, @Nullable String slotId) {
        Optional<TrinketComponent> component = TrinketsApi.getTrinketComponent(entity);
        int highest = 0;
        if (component.isPresent()) {
            for (var equipped : component.get().getAllEquipped()) {
                if (slotId != null) if (!slotId.equals(equipped.getLeft().getId())) continue;
                int level = equipped.getRight().getEnchantments().getLevel(enchantment);
                if (highest > level) highest = level;
            }
        }
        return highest;
    }

    /**
     * {@return Returns the combined level of a specified enchantment equipped on an entity}
     *
     * @param entity entity to check slots from
     * @param enchantment enchantment being queried for
     */
    public static int totalLevel(LivingEntity entity, RegistryEntry<Enchantment> enchantment, @Nullable String slotId) {
        Optional<TrinketComponent> component = TrinketsApi.getTrinketComponent(entity);
        int level = 0;
        if (component.isPresent()) {
            for (var equipped : component.get().getAllEquipped()) {
                if (slotId != null) if (!slotId.equals(equipped.getLeft().getId())) continue;
                level += equipped.getRight().getEnchantments().getLevel(enchantment);
            }
        }
        return level;
    }

    public static int equipmentCount(LivingEntity entity, Predicate<ItemStack> check) {
        return equipmentCount(entity, check, null);
    }
}
