package jacobg5.japi;

import net.minecraft.block.Block;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class JKeys {
    // Item Tags
    public static TagKey<Item> PIGLIN_FRIENDLY_EQUIPMENT = getTag(RegistryKeys.ITEM, "piglin_friendly_equipment");
    public static TagKey<Item> PIGLIN_BARTER_ITEM = getTag(RegistryKeys.ITEM, "piglin_barter_item");
    public static TagKey<Item> CONDUCTIVE_EQUIPMENT = getTag(RegistryKeys.ITEM, "conductive_equipment");
    public static TagKey<Item> BOWS = getTag(RegistryKeys.ITEM, "bows");
    public static TagKey<Item> CROSSBOWS = getTag(RegistryKeys.ITEM, "crossbows");
    public static TagKey<Item> NUGGETS = getTag(RegistryKeys.ITEM, "nuggets");
    public static TagKey<Item> UPGRADE_TEMPLATE = getTag(RegistryKeys.ITEM, "upgrade_template");
    public static TagKey<Item> FIREPROOF = getTag(RegistryKeys.ITEM, "fireproof");

    // Block Tags
    public static TagKey<Block> CARVED_PUMPKINS = getTag(RegistryKeys.BLOCK, "carved_pumpkins");
    public static TagKey<Block> INDESTRUCTIBLE = getTag(RegistryKeys.BLOCK, "indestructible");
    public static TagKey<Block> ADVENTURE_BREAKABLE = getTag(RegistryKeys.BLOCK, "adventure_breakable");
    public static TagKey<Block> BLAST_PROOF = getTag(RegistryKeys.BLOCK, "blast_proof");
    public static TagKey<Block> FRAGILE = getTag(RegistryKeys.BLOCK, "fragile");

    // Damage Type Tags
    public static TagKey<DamageType> GAME_DESIGN = getTag(RegistryKeys.DAMAGE_TYPE, "is_game_design");

    /**
     * {@return gets a tag from the provided registry}
     *
     * @param registryRef the registry to get tag from
     * @param id tag identifier
     */
    public static <T> TagKey<T> getTag(RegistryKey<? extends Registry<T>> registryRef, Identifier id) {
        return TagKey.of(registryRef, id);
    }

    private static <T> TagKey<T> getTag(RegistryKey<? extends Registry<T>> registryRef, String name) {
        return getTag(registryRef, JAPI.identifier(name));
    }
}
