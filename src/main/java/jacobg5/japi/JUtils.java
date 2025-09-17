package jacobg5.japi;

import jacobg5.japi.compat.apoli.JApoli;
import jacobg5.japi.compat.trinkets.JTrinkets;
import jacobg5.japi.networking.s2c.FloatingItemPacket;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class JUtils {
    /**
     * {@return Searches all of an entity's hand, armor, and custom slots for an item matching the predicate}
     *
     * @param living entity to check slots from
     * @param check stack being queried for
     */
    public static Optional<ItemStack> getEquippedOrHeld(LivingEntity living, Predicate<ItemStack> check) {
        Optional<ItemStack> optional = getHeld(living, check);
        return optional.isPresent() ? optional : getEquipped(living, check);
    }

    /**
     * {@return Searches all of an entity's armor, and custom slots for an item matching the predicate}
     *
     * @param living entity to check slots from
     * @param check stack being queried for
     */
    public static Optional<ItemStack> getEquipped(LivingEntity living, Predicate<ItemStack> check) {
        for (ItemStack stack : living.getArmorItems()) {
            if (check.test(stack)) return Optional.of(stack);
        }
        if(JLoaderData.TRINKETS) {
            Optional<ItemStack> trinket = JTrinkets.getEquipped(living, check, null);
            if (trinket.isPresent()) return trinket;
        }
        return Optional.empty();
    }

    /**
     * {@return Searches both of hands for an item matching the predicate}
     *
     * @param living entity to check slots from
     * @param check stack being queried for
     */
    public static Optional<ItemStack> getHeld(LivingEntity living, Predicate<ItemStack> check) {
        for (ItemStack stack : living.getHandItems()) {
            if (check.test(stack)) return Optional.of(stack);
        }
        return Optional.empty();
    }

    /**
     * {@return Returns a total number of items matching the predicate within an entity's hand, armor, and custom slots}
     *
     * @param living entity to check slots from
     * @param check stack being queried for
     */
    public static int equipmentOrHeldCount(LivingEntity living, Predicate<ItemStack> check) {
        int count = equipmentCount(living, check);
        for (ItemStack stack : living.getHandItems()) {
            if (check.test(stack)) count++;
        }
        return count;
    }

    /**
     * {@return Returns a total number of items matching the predicate within an entity's armor, and custom slots}
     *
     * @param living entity to check slots from
     * @param check stack being queried for
     */
    public static int equipmentCount(LivingEntity living, Predicate<ItemStack> check) {
        int count = 0;
        for (ItemStack stack : living.getArmorItems()) {
            if (check.test(stack)) count++;
        }
        if(JLoaderData.TRINKETS) {
            if (JTrinkets.getEquipped(living, check, null).isPresent()) count++;
        }
        return count;
    }

    /**
     * {@return Returns the highest level of a specified enchantment equipped on an entity}
     *
     * @param living entity to check slots from
     * @param enchantment enchantment being queried for
     */
    public static int highestLevel(LivingEntity living, RegistryEntry<Enchantment> enchantment) {
        int highest = 0;
        for (ItemStack stack : living.getArmorItems()) {
            int level = stack.getEnchantments().getLevel(enchantment);
            if (highest > level) highest = level;
        }
        if (JLoaderData.TRINKETS) {
            int trinketLevel = JTrinkets.highestLevel(living, enchantment, null);
            if (trinketLevel > highest) highest = trinketLevel;
        }
        return highest;
    }

    /**
     * {@return Returns the combined level of a specified enchantment equipped on an entity}
     *
     * @param living entity to check slots from
     * @param enchantment enchantment being queried for
     */
    public static int totalLevel(LivingEntity living, RegistryEntry<Enchantment> enchantment) {
        int level = 0;
        for (ItemStack stack : living.getArmorItems()) {
            level += stack.getEnchantments().getLevel(enchantment);
        }
        if (JLoaderData.TRINKETS) {
            level += JTrinkets.totalLevel(living, enchantment, null);
        }
        return level;
    }

    /**
     * {@return Checks if an entity is wearing gold or otherwise considered friendly towards Piglins}
     *
     * @param entity entity to check slots from
     */
    public static boolean wearingGold(LivingEntity entity) {
        if (JLoaderData.APOLI) {
            if (JApoli.checkPiglinPower(entity)) {
                return true;
            }
        }
        return JUtils.getEquipped(entity, stack -> {
            if (stack.getItem() instanceof ArmorItem armorItem) {
                if (armorItem.getMaterial() == ArmorMaterials.GOLD) return true;
            }
            return stack.isIn(JKeys.PIGLIN_FRIENDLY_EQUIPMENT);
        }).isPresent();
    }

    /**
     * {@return Returns a new list that is a combination of all provided lists}
     */
    @SafeVarargs
    public static <T> List<T> mixLists(List<T>... lists) {
        int size = 0;
        for (List<T> list : lists) { size += list.size(); }
        ArrayList<T> arrayList = new ArrayList<>(size);
        Arrays.stream(lists).forEach(arrayList::addAll);
        return arrayList;
    }

    /**
     * Converts a stream of {@link ItemConvertible}s to a list of their default item stacks filtering out null entries
     */
    public static @NotNull List<ItemStack> convertToDefaultStacks(Stream<ItemConvertible> convertibles) {
        return convertibles.filter(Objects::nonNull).map(convertible -> convertible.asItem().getDefaultStack()).filter(Objects::nonNull).toList();
    }

    /**
     * Displays a floating item display on the client's screen.
     * This uses the Totem of Undying animation.
     */
    public static void sendFloatingItem(ServerPlayerEntity player, ItemStack stack) {
        ServerPlayNetworking.send(player, new FloatingItemPacket(stack));
    }
}
