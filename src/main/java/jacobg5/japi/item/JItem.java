package jacobg5.japi.item;

import com.google.common.collect.ImmutableMap;
import jacobg5.japi.component.ConductivityComponent;
import jacobg5.japi.component.JComponents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Colors;

import java.util.HashMap;
import java.util.Map;

public class JItem {
    private static final Map<Item, int[]> COLOR_VALUES = new HashMap<>(new ImmutableMap.Builder<Item, int[]>()
            .put(Items.ALLAY_SPAWN_EGG, new int[] {0x00DAFF, 0x00ADFF})
            .put(Items.ARMADILLO_SPAWN_EGG, new int[] {0xAD716D, 0x824848})
            .put(Items.AXOLOTL_SPAWN_EGG, new int[] {0xFBC1E3, 0xA62D74})
            .put(Items.BAT_SPAWN_EGG, new int[] {0x4C3E30, 0x0F0F0F})
            .put(Items.BEE_SPAWN_EGG, new int[] {0xEDC343, 0x43241B})
            .put(Items.BLAZE_SPAWN_EGG, new int[] {0xF6B201, 0xFFF87E})
            .put(Items.BOGGED_SPAWN_EGG, new int[] {0x8A9C72, 0x314D1B})
            .put(Items.BREEZE_SPAWN_EGG, new int[] {0xAF94DF, 0x9166DF})
            .put(Items.CAMEL_SPAWN_EGG, new int[] {0xFCC369, 0xCB9337})
            .put(Items.CAT_SPAWN_EGG, new int[] {0xEFC88E, 0x957256})
            .put(Items.CAVE_SPIDER_SPAWN_EGG, new int[] {0x0C424E, 0xA80E0E})
            .put(Items.CHICKEN_SPAWN_EGG, new int[] {0xA1A1A1, 0xFF0000})
            .put(Items.COD_SPAWN_EGG, new int[] {0xC1A76A, 0xE5C48B})
            .put(Items.COW_SPAWN_EGG, new int[] {0x443626, 0xA1A1A1})
            //.put(Items.CREAKING_SPAWN_EGG, new int[] {0x5F5F5F, 0xFC7812})
            .put(Items.CREEPER_SPAWN_EGG, new int[] {0x0DA70B, 0x000000})
            .put(Items.DOLPHIN_SPAWN_EGG, new int[] {0x223B4D, 0xF9F9F9})
            .put(Items.DONKEY_SPAWN_EGG, new int[] {0x534539, 0x867566})
            .put(Items.DROWNED_SPAWN_EGG, new int[] {0x8FF1D7, 0x799C65})
            .put(Items.ELDER_GUARDIAN_SPAWN_EGG, new int[] {0xCECCBA, 0x747693})
            .put(Items.ENDER_DRAGON_SPAWN_EGG, new int[] {0x1C1C1C, 0xE079FA})
            .put(Items.ENDERMAN_SPAWN_EGG, new int[] {0x161616, 0x000000})
            .put(Items.ENDERMITE_SPAWN_EGG, new int[] {0x161616, 0x6E6E6E})
            .put(Items.EVOKER_SPAWN_EGG, new int[] {0x959B9B, 0x1E1C1A})
            .put(Items.FOX_SPAWN_EGG, new int[] {0xD5B69F, 0xCC6920})
            .put(Items.FROG_SPAWN_EGG, new int[] {0xD07444, 0xFFC77C})
            .put(Items.GHAST_SPAWN_EGG, new int[] {0xF9F9F9, 0xBCBCBC})
            .put(Items.GLOW_SQUID_SPAWN_EGG, new int[] {0x095656, 0x85F1BC})
            .put(Items.GOAT_SPAWN_EGG, new int[] {0xA5947C, 0x55493E})
            .put(Items.GUARDIAN_SPAWN_EGG, new int[] {0x5A8272, 0xF17D30})
            .put(Items.HOGLIN_SPAWN_EGG, new int[] {0xC66E55, 0x5F6464})
            .put(Items.HORSE_SPAWN_EGG, new int[] {0xC09E7D, 0xEEE500})
            .put(Items.HUSK_SPAWN_EGG, new int[] {0x797061, 0xE6CC94})
            .put(Items.IRON_GOLEM_SPAWN_EGG, new int[] {0xDBCDC2, 0x74A332})
            .put(Items.LLAMA_SPAWN_EGG, new int[] {0xC09E7D, 0x995F40})
            .put(Items.MAGMA_CUBE_SPAWN_EGG, new int[] {0x340000, 0xFCFC00})
            .put(Items.MOOSHROOM_SPAWN_EGG, new int[] {0xA00F10, 0xB7B7B7})
            .put(Items.OCELOT_SPAWN_EGG, new int[] {0xEFDE7D, 0x564434})
            .put(Items.PANDA_SPAWN_EGG, new int[] {0xE7E7E7, 0x1B1B22})
            .put(Items.PARROT_SPAWN_EGG, new int[] {0x0DA70B, 0xFF0000})
            .put(Items.PHANTOM_SPAWN_EGG, new int[] {0x43518A, 0x88FF00})
            .put(Items.PIG_SPAWN_EGG, new int[] {0xF0A5A2, 0xDB635F})
            .put(Items.PIGLIN_SPAWN_EGG, new int[] {0x995F40, 0xF9F3A4})
            .put(Items.PIGLIN_BRUTE_SPAWN_EGG, new int[] {0x592A10, 0xF9F3A4})
            .put(Items.PILLAGER_SPAWN_EGG, new int[] {0x532F36, 0x959B9B})
            .put(Items.POLAR_BEAR_SPAWN_EGG, new int[] {0xEEEEDE, 0xD5D6CD})
            .put(Items.PUFFERFISH_SPAWN_EGG, new int[] {0xF6B201, 0x37C3F2})
            .put(Items.RABBIT_SPAWN_EGG, new int[] {0x995F40, 0x734831})
            .put(Items.RAVAGER_SPAWN_EGG, new int[] {0x757470, 0x5B5049})
            .put(Items.SALMON_SPAWN_EGG, new int[] {0xA00F10, 0x0E8474})
            .put(Items.SHEEP_SPAWN_EGG, new int[] {0xE7E7E7, 0xFFB5B5})
            .put(Items.SHULKER_SPAWN_EGG, new int[] {0x946794, 0x4D3852})
            .put(Items.SILVERFISH_SPAWN_EGG, new int[] {0x6E6E6E, 0x303030})
            .put(Items.SKELETON_SPAWN_EGG, new int[] {0xC1C1C1, 0x494949})
            .put(Items.SKELETON_HORSE_SPAWN_EGG, new int[] {0x68684F, 0xE5E5D8})
            .put(Items.SLIME_SPAWN_EGG, new int[] {0x51A03E, 0x7EBF6E})
            .put(Items.SNIFFER_SPAWN_EGG, new int[] {0x871E09, 0x25AB70})
            .put(Items.SNOW_GOLEM_SPAWN_EGG, new int[] {0xD9F2F2, 0x81A4A4})
            .put(Items.SPIDER_SPAWN_EGG, new int[] {0x342D27, 0xA80E0E})
            .put(Items.SQUID_SPAWN_EGG, new int[] {0x223B4D, 0x708899})
            .put(Items.STRAY_SPAWN_EGG, new int[] {0x617677, 0xDDEAEA})
            .put(Items.STRIDER_SPAWN_EGG, new int[] {0x9C3436, 0x4D494D})
            .put(Items.TADPOLE_SPAWN_EGG, new int[] {0x6D533D, 0x160A00})
            .put(Items.TRADER_LLAMA_SPAWN_EGG, new int[] {0xEAA430, 0x456296})
            .put(Items.TROPICAL_FISH_SPAWN_EGG, new int[] {0xEF6915, 0xFFF9EF})
            .put(Items.TURTLE_SPAWN_EGG, new int[] {0xE7E7E7, 0x00AFAF})
            .put(Items.VEX_SPAWN_EGG, new int[] {0x7A90A4, 0xE8EDF1})
            .put(Items.VILLAGER_SPAWN_EGG, new int[] {0x563C33, 0xBD8B72})
            .put(Items.VINDICATOR_SPAWN_EGG, new int[] {0x959B9B, 0x275E61})
            .put(Items.WANDERING_TRADER_SPAWN_EGG, new int[] {0x456296, 0xEAA430})
            .put(Items.WARDEN_SPAWN_EGG, new int[] {0x0F4649, 0x39D6E0})
            .put(Items.WITCH_SPAWN_EGG, new int[] {0x340000, 0x51A03E})
            .put(Items.WITHER_SPAWN_EGG, new int[] {0x141414, 0x4D72A0})
            .put(Items.WITHER_SKELETON_SPAWN_EGG, new int[] {0x141414, 0x474D4D})
            .put(Items.WOLF_SPAWN_EGG, new int[] {0xD7D3D3, 0xCEAF96})
            .put(Items.ZOGLIN_SPAWN_EGG, new int[] {0xC66E55, 0xE6E6E6})
            .put(Items.ZOMBIE_SPAWN_EGG, new int[] {0x00AFAF, 0x799C65})
            .put(Items.ZOMBIE_HORSE_SPAWN_EGG, new int[] {0x315234, 0x97C284})
            .put(Items.ZOMBIE_VILLAGER_SPAWN_EGG, new int[] {0x563C33, 0x799C65})
            .put(Items.ZOMBIFIED_PIGLIN_SPAWN_EGG, new int[] {0xEA9393, 0x4C7129})
            .build());


    public static int getColor(Item item, int index) {
        if (index < 0) return Colors.WHITE;

        int[] values = COLOR_VALUES.get(item);
        if (values != null) {
            return values[Math.min(index, values.length-1)];
        }
        return Colors.WHITE;
    }

    public static void setColors(Item item, int... colors) {
        if (COLOR_VALUES.containsKey(item) || colors.length == 0) return;
        COLOR_VALUES.put(item, colors);
    }

    public static float getConductivity(ItemStack stack) {
        ConductivityComponent component = stack.get(JComponents.CONDUCTIVITY);
        if (component != null) {
            return component.amount();
        }
        return ((Interface)stack.getItem()).JAPI$getConductivity();
    }

    public static void setBaseConductivity(Item item, float conductivity) {
        ((Interface)item).JAPI$setConductivity(conductivity);
    }

    public interface Interface {
        void JAPI$setConductivity(float conductivity);
        float JAPI$getConductivity();
    }
}
