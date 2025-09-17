package jacobg5.japi.item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import jacobg5.japi.JAPI;
import jacobg5.japi.JUtils;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

public class JSmithingTemplate extends SmithingTemplateItem {
    private final SlotType BASE;
    private final SlotType ADDITION;

    public JSmithingTemplate(Text appliesToText, Text ingredientsText, Text titleText, Text baseSlotDescriptionText, Text additionsSlotDescriptionText, List<Identifier> emptyBaseSlotTextures, List<Identifier> emptyAdditionsSlotTextures) {
        super(appliesToText, ingredientsText, titleText, baseSlotDescriptionText, additionsSlotDescriptionText, emptyBaseSlotTextures, emptyAdditionsSlotTextures);
        this.BASE = SlotType.DEFAULT;
        this.ADDITION = SlotType.DEFAULT;
    }

    public JSmithingTemplate(Text appliesToText, Text ingredientsText, Text titleText, Text baseSlotDescriptionText, Text additionsSlotDescriptionText, SlotType base, List<Identifier> emptyAdditionsSlotTextures) {
        super(appliesToText, ingredientsText, titleText, baseSlotDescriptionText, additionsSlotDescriptionText, null, emptyAdditionsSlotTextures);
        this.BASE = base;
        this.ADDITION = SlotType.DEFAULT;
    }

    public JSmithingTemplate(Text appliesToText, Text ingredientsText, Text titleText, Text baseSlotDescriptionText, Text additionsSlotDescriptionText, List<Identifier> emptyBaseSlotTextures, SlotType addition) {
        super(appliesToText, ingredientsText, titleText, baseSlotDescriptionText, additionsSlotDescriptionText, emptyBaseSlotTextures, null);
        this.BASE = SlotType.DEFAULT;
        this.ADDITION = addition;
    }

    public JSmithingTemplate(Text appliesToText, Text ingredientsText, Text titleText, Text baseSlotDescriptionText, Text additionsSlotDescriptionText, SlotType base, SlotType addition) {
        super(appliesToText, ingredientsText, titleText, baseSlotDescriptionText, additionsSlotDescriptionText, null, null);
        this.BASE = base;
        this.ADDITION = addition;
    }

    private static final Formatting TITLE_FORMATTING = Formatting.GRAY;
    private static final Formatting DESCRIPTION_FORMATTING = Formatting.BLUE;
    private static final Text DIAMOND_UPGRADE_APPLIES_TO_TEXT = Text.translatable(Util.createTranslationKey("item", Identifier.of("smithing_template.netherite_upgrade.applies_to"))).formatted(DESCRIPTION_FORMATTING);
    private static final Text DIAMOND_BASE_SLOT_DESCRIPTION_TEXT = Text.translatable(Util.createTranslationKey("item", Identifier.of("smithing_template.netherite_upgrade.base_slot_description")));

    public static final Identifier EMPTY_ARMOR_SLOT_HELMET_TEXTURE = Identifier.of("item/empty_armor_slot_helmet");
    public static final Identifier EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE = Identifier.of("item/empty_armor_slot_chestplate");
    public static final Identifier EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE = Identifier.of("item/empty_armor_slot_leggings");
    public static final Identifier EMPTY_ARMOR_SLOT_BOOTS_TEXTURE = Identifier.of("item/empty_armor_slot_boots");
    public static final Identifier EMPTY_SLOT_HOE_TEXTURE = Identifier.of("item/empty_slot_hoe");
    public static final Identifier EMPTY_SLOT_AXE_TEXTURE = Identifier.of("item/empty_slot_axe");
    public static final Identifier EMPTY_SLOT_SWORD_TEXTURE = Identifier.of("item/empty_slot_sword");
    public static final Identifier EMPTY_SLOT_SHOVEL_TEXTURE = Identifier.of("item/empty_slot_shovel");
    public static final Identifier EMPTY_SLOT_PICKAXE_TEXTURE = Identifier.of("item/empty_slot_pickaxe");
    public static final Identifier EMPTY_SLOT_INGOT_TEXTURE = Identifier.of("item/empty_slot_ingot");
    public static final Identifier EMPTY_SLOT_REDSTONE_DUST_TEXTURE = Identifier.of("item/empty_slot_redstone_dust");
    public static final Identifier EMPTY_SLOT_QUARTZ_TEXTURE = Identifier.of("item/empty_slot_quartz");
    public static final Identifier EMPTY_SLOT_EMERALD_TEXTURE = Identifier.of("item/empty_slot_emerald");
    public static final Identifier EMPTY_SLOT_DIAMOND_TEXTURE = Identifier.of("item/empty_slot_diamond");
    public static final Identifier EMPTY_SLOT_LAPIS_LAZULI_TEXTURE = Identifier.of("item/empty_slot_lapis_lazuli");
    public static final Identifier EMPTY_SLOT_AMETHYST_SHARD_TEXTURE = Identifier.of("item/empty_slot_amethyst_shard");
    public static final Identifier EMPTY_SLOT_NUGGET_TEXTURE = Identifier.of("japi", "item/empty_slot_nugget");
    public static final Identifier EMPTY_SLOT_BLOCK_TEXTURE = Identifier.of("japi", "item/empty_slot_block");
    public static final Identifier EMPTY_SLOT_STAIR_TEXTURE = Identifier.of("japi", "item/empty_slot_stair");
    public static final Identifier EMPTY_SLOT_SLAB_TEXTURE = Identifier.of("japi", "item/empty_slot_slab");
    public static final Identifier EMPTY_SLOT_WALL_TEXTURE = Identifier.of("japi", "item/empty_slot_wall");
    public static final Identifier EMPTY_SLOT_ROD_TEXTURE = Identifier.of("japi", "item/empty_slot_rod");

    public static final List<Identifier> ADDITIONAL_ARMOR_TEXTURES = new ArrayList<Identifier>();
    public static final List<Identifier> ADDITIONAL_TOOL_TEXTURES = new ArrayList<Identifier>();
    public static final List<Identifier> ADDITIONAL_MATERIAL_TEXTURES = new ArrayList<Identifier>();

    public static final List<Identifier> DEFAULT_ARMOR_TEXTURES = List.of(EMPTY_ARMOR_SLOT_HELMET_TEXTURE, EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE, EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE, EMPTY_ARMOR_SLOT_BOOTS_TEXTURE);
    public static final List<Identifier> DEFAULT_TOOL_TEXTURES = List.of(EMPTY_SLOT_HOE_TEXTURE, EMPTY_SLOT_AXE_TEXTURE, EMPTY_SLOT_SWORD_TEXTURE, EMPTY_SLOT_SHOVEL_TEXTURE, EMPTY_SLOT_PICKAXE_TEXTURE);
    public static final List<Identifier> DEFAULT_MATERIAL_TEXTURES = List.of(EMPTY_SLOT_INGOT_TEXTURE, EMPTY_SLOT_REDSTONE_DUST_TEXTURE, EMPTY_SLOT_QUARTZ_TEXTURE, EMPTY_SLOT_EMERALD_TEXTURE, EMPTY_SLOT_DIAMOND_TEXTURE, EMPTY_SLOT_LAPIS_LAZULI_TEXTURE, EMPTY_SLOT_AMETHYST_SHARD_TEXTURE);

    public static List<Identifier> getArmorTextures() {
        return JUtils.mixLists(ADDITIONAL_ARMOR_TEXTURES, DEFAULT_ARMOR_TEXTURES);
    }

    public static List<Identifier> getToolTextures() {
        return JUtils.mixLists(ADDITIONAL_TOOL_TEXTURES, DEFAULT_TOOL_TEXTURES);
    }

    public static List<Identifier> getMaterialTextures() {
        return JUtils.mixLists(ADDITIONAL_MATERIAL_TEXTURES, DEFAULT_MATERIAL_TEXTURES);
    }

    public static List<Identifier> getGearTextures() {
        return JUtils.mixLists(getArmorTextures(), getToolTextures());
    }

    public static void addArmorTextures(Identifier... path) {
        ADDITIONAL_ARMOR_TEXTURES.addAll(Arrays.asList(path));
        Collections.shuffle(ADDITIONAL_ARMOR_TEXTURES);
    }

    public static void addToolTextures(Identifier... path) {
        ADDITIONAL_TOOL_TEXTURES.addAll(Arrays.asList(path));
        Collections.shuffle(ADDITIONAL_TOOL_TEXTURES);
    }

    public static void addMaterialTextures(Identifier... path) {
        ADDITIONAL_MATERIAL_TEXTURES.addAll(Arrays.asList(path));
        Collections.shuffle(ADDITIONAL_MATERIAL_TEXTURES);
    }

    public static SmithingTemplateItem createDiamondUpgradeTemplate(String templateKey, List<Identifier> ingredientTexture) {
        templateKey = templateKey.toLowerCase();
        return new JSmithingTemplate(DIAMOND_UPGRADE_APPLIES_TO_TEXT,
                Text.translatable(Util.createTranslationKey("item", Identifier.of(JAPI.MOD_ID, "smithing_template." + templateKey + ".ingredients"))).formatted(DESCRIPTION_FORMATTING),
                Text.translatable(Util.createTranslationKey("upgrade", Identifier.of(JAPI.MOD_ID, templateKey))).formatted(TITLE_FORMATTING),
                DIAMOND_BASE_SLOT_DESCRIPTION_TEXT,
                Text.translatable(Util.createTranslationKey("item", Identifier.of(JAPI.MOD_ID, "smithing_template." + templateKey + ".additions_slot_description"))),
                SlotType.GEAR, ingredientTexture);
    }

    public static SmithingTemplateItem createDiamondUpgradeTemplate(String templateKey) {
        return createDiamondUpgradeTemplate(templateKey, List.of(EMPTY_SLOT_INGOT_TEXTURE));
    }

    public static SmithingTemplateItem createComboPattern(String templateKey, List<Identifier> baseTexture, List<Identifier> ingredientTexture) {
        templateKey = templateKey.toLowerCase();
        return new JSmithingTemplate(Text.translatable(Util.createTranslationKey("item", Identifier.of(JAPI.MOD_ID, "smithing_template." + templateKey + ".applies_to"))).formatted(DESCRIPTION_FORMATTING),
                Text.translatable(Util.createTranslationKey("item", Identifier.of(JAPI.MOD_ID, "smithing_template." + templateKey + ".ingredients"))).formatted(DESCRIPTION_FORMATTING),
                Text.translatable(Util.createTranslationKey("upgrade", Identifier.of(JAPI.MOD_ID, templateKey))).formatted(TITLE_FORMATTING),
                Text.translatable(Util.createTranslationKey("item", Identifier.of(JAPI.MOD_ID, "smithing_template." + templateKey + ".base_slot_description"))),
                Text.translatable(Util.createTranslationKey("item", Identifier.of(JAPI.MOD_ID, "smithing_template." + templateKey + ".additions_slot_description"))),
                baseTexture, ingredientTexture);
    }

    public static enum SlotType {
        DEFAULT,
        ARMOR,
        TOOL,
        GEAR,
        MATERIAL
    }

    @Override
    public List<Identifier> getEmptyBaseSlotTextures() {
        return switch (BASE) {
            case ARMOR -> getArmorTextures();
            case TOOL -> getToolTextures();
            case GEAR -> getGearTextures();
            case MATERIAL -> getMaterialTextures();
            default -> super.getEmptyBaseSlotTextures();
        };
    }

    @Override
    public List<Identifier> getEmptyAdditionsSlotTextures() {
        return switch (ADDITION) {
            case ARMOR -> getArmorTextures();
            case TOOL -> getToolTextures();
            case GEAR -> getGearTextures();
            case MATERIAL -> getMaterialTextures();
            default -> super.getEmptyBaseSlotTextures();
        };
    }
}
