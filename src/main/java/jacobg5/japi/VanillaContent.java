package jacobg5.japi;

import jacobg5.japi.containers.*;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.Blocks;
import net.minecraft.block.WoodType;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Identifier;

import java.util.Map;

public  class VanillaContent {
    public static final GearSet WoodenGear = new GearSet(Identifier.of("wooden"), ToolMaterials.WOOD, Items.WOODEN_SHOVEL, Items.WOODEN_SWORD, Items.WOODEN_PICKAXE, Items.WOODEN_AXE, Items.WOODEN_HOE);
    public static final GearSet LeatherGear = new GearSet(Identifier.of("leather"), ArmorMaterials.LEATHER.value(), Items.LEATHER_HELMET, Items.LEATHER_CHESTPLATE, Items.LEATHER_LEGGINGS, Items.LEATHER_BOOTS, Items.LEATHER_HORSE_ARMOR);
    public static final GearSet StoneGear = new GearSet(Identifier.of("stone"), ToolMaterials.STONE, Items.STONE_SHOVEL, Items.STONE_SWORD, Items.STONE_PICKAXE, Items.STONE_AXE, Items.STONE_HOE);
    public static final GearSet ChainmailGear = new GearSet(Identifier.of("chainmail"), ArmorMaterials.CHAIN.value(), Items.CHAINMAIL_HELMET, Items.CHAINMAIL_CHESTPLATE, Items.CHAINMAIL_LEGGINGS, Items.CHAINMAIL_BOOTS, null);
    public static final GearSet IronGear = new GearSet(Identifier.of("iron"), ToolMaterials.IRON, Items.IRON_SHOVEL, Items.IRON_SWORD, Items.IRON_PICKAXE, Items.IRON_AXE, Items.IRON_HOE,
            ArmorMaterials.IRON.value(), Items.IRON_HELMET, Items.IRON_CHESTPLATE, Items.IRON_LEGGINGS, Items.IRON_BOOTS, Items.IRON_HORSE_ARMOR);
    public static final GearSet GoldenGear = new GearSet(Identifier.of("golden"), ToolMaterials.GOLD, Items.GOLDEN_SHOVEL, Items.GOLDEN_SWORD, Items.GOLDEN_PICKAXE, Items.GOLDEN_AXE, Items.GOLDEN_HOE,
            ArmorMaterials.GOLD.value(), Items.GOLDEN_HELMET, Items.GOLDEN_CHESTPLATE, Items.GOLDEN_LEGGINGS, Items.GOLDEN_BOOTS, Items.GOLDEN_HORSE_ARMOR);
    public static final GearSet DiamondGear = new GearSet(Identifier.of("diamond"), ToolMaterials.DIAMOND, Items.DIAMOND_SHOVEL, Items.DIAMOND_SWORD, Items.DIAMOND_PICKAXE, Items.DIAMOND_AXE, Items.DIAMOND_HOE,
            ArmorMaterials.DIAMOND.value(), Items.DIAMOND_HELMET, Items.DIAMOND_CHESTPLATE, Items.DIAMOND_LEGGINGS, Items.DIAMOND_BOOTS, Items.DIAMOND_HORSE_ARMOR);
    public static final GearSet NetheriteGear = new GearSet(Identifier.of("netherite"), ToolMaterials.NETHERITE, Items.NETHERITE_SHOVEL, Items.NETHERITE_SWORD, Items.NETHERITE_PICKAXE, Items.NETHERITE_AXE, Items.NETHERITE_HOE,
            ArmorMaterials.NETHERITE.value(), Items.NETHERITE_HELMET, Items.NETHERITE_CHESTPLATE, Items.NETHERITE_LEGGINGS, Items.NETHERITE_BOOTS, null);
    public static final GearSet TurtleGear = new GearSet(Identifier.of("turtle"), ArmorMaterials.TURTLE.value(), Items.TURTLE_HELMET, null, null, null, null);
    public static final GearSet ArmadilloGear = new GearSet(Identifier.of("armadillo"), ArmorMaterials.ARMADILLO.value(), null, null, null, null, Items.WOLF_ARMOR);
    public static final Map<Identifier, GearSet> GearSets = Map.ofEntries(WoodenGear.entry(), LeatherGear.entry(), StoneGear.entry(), ChainmailGear.entry(), IronGear.entry(),
            GoldenGear.entry(), DiamondGear.entry(), NetheriteGear.entry(), TurtleGear.entry(), ArmadilloGear.entry());

    public static final WoodSet Oak = new WoodSet(Identifier.of("oak"), WoodType.OAK, BlockSetType.OAK, new FullBlock("oak_planks", Blocks.OAK_PLANKS, Items.OAK_PLANKS),
            new FullBlock("oak_stairs", Blocks.OAK_STAIRS, Items.OAK_STAIRS), new FullBlock("oak_slab", Blocks.OAK_SLAB, Items.OAK_SLAB),
            new FullBlock("oak_log", Blocks.OAK_LOG, Items.OAK_LOG), new FullBlock("oak_wood", Blocks.OAK_WOOD, Items.OAK_WOOD),
            new FullBlock("stripped_oak_log", Blocks.STRIPPED_OAK_LOG, Items.STRIPPED_OAK_LOG), new FullBlock("stripped_oak_wood", Blocks.STRIPPED_OAK_WOOD, Items.STRIPPED_OAK_WOOD),
            new FullBlock("oak_fence", Blocks.OAK_FENCE, Items.OAK_FENCE), new FullBlock("oak_fence_gate", Blocks.OAK_FENCE_GATE, Items.OAK_FENCE_GATE),
            new FullBlock("oak_door", Blocks.OAK_DOOR, Items.OAK_DOOR), new FullBlock("oak_trapdoor", Blocks.OAK_TRAPDOOR, Items.OAK_TRAPDOOR),
            new FullBlock("oak_pressure_plate", Blocks.OAK_PRESSURE_PLATE, Items.OAK_PRESSURE_PLATE), new FullBlock("oak_button", Blocks.OAK_BUTTON, Items.OAK_BUTTON),
            new FullWallBlock("oak_sign", Blocks.OAK_SIGN, Blocks.OAK_WALL_SIGN, Items.OAK_SIGN), new FullWallBlock("oak_hanging_sign", Blocks.OAK_HANGING_SIGN, Blocks.OAK_WALL_HANGING_SIGN, Items.OAK_HANGING_SIGN));
    public static final WoodSet Birch = new WoodSet(Identifier.of("birch"), WoodType.BIRCH, BlockSetType.BIRCH, new FullBlock("birch_planks", Blocks.BIRCH_PLANKS, Items.BIRCH_PLANKS),
            new FullBlock("birch_stairs", Blocks.BIRCH_STAIRS, Items.BIRCH_STAIRS), new FullBlock("birch_slab", Blocks.BIRCH_SLAB, Items.BIRCH_SLAB),
            new FullBlock("birch_log", Blocks.BIRCH_LOG, Items.BIRCH_LOG), new FullBlock("birch_wood", Blocks.BIRCH_WOOD, Items.BIRCH_WOOD),
            new FullBlock("stripped_birch_log", Blocks.STRIPPED_BIRCH_LOG, Items.STRIPPED_BIRCH_LOG), new FullBlock("stripped_birch_wood", Blocks.STRIPPED_BIRCH_WOOD, Items.STRIPPED_BIRCH_WOOD),
            new FullBlock("birch_fence", Blocks.BIRCH_FENCE, Items.BIRCH_FENCE), new FullBlock("birch_fence_gate", Blocks.BIRCH_FENCE_GATE, Items.BIRCH_FENCE_GATE),
            new FullBlock("birch_door", Blocks.BIRCH_DOOR, Items.BIRCH_DOOR), new FullBlock("birch_trapdoor", Blocks.BIRCH_TRAPDOOR, Items.BIRCH_TRAPDOOR),
            new FullBlock("birch_pressure_plate", Blocks.BIRCH_PRESSURE_PLATE, Items.BIRCH_PRESSURE_PLATE), new FullBlock("birch_button", Blocks.BIRCH_BUTTON, Items.BIRCH_BUTTON),
            new FullWallBlock("birch_sign", Blocks.BIRCH_SIGN, Blocks.BIRCH_WALL_SIGN, Items.BIRCH_SIGN), new FullWallBlock("birch_hanging_sign", Blocks.BIRCH_HANGING_SIGN, Blocks.BIRCH_WALL_HANGING_SIGN, Items.BIRCH_HANGING_SIGN));
    public static final WoodSet Spruce = new WoodSet(Identifier.of("spruce"), WoodType.SPRUCE, BlockSetType.SPRUCE, new FullBlock("spruce_planks", Blocks.SPRUCE_PLANKS, Items.SPRUCE_PLANKS),
            new FullBlock("spruce_stairs", Blocks.SPRUCE_STAIRS, Items.SPRUCE_STAIRS), new FullBlock("spruce_slab", Blocks.SPRUCE_SLAB, Items.SPRUCE_SLAB),
            new FullBlock("spruce_log", Blocks.SPRUCE_LOG, Items.SPRUCE_LOG), new FullBlock("spruce_wood", Blocks.SPRUCE_WOOD, Items.SPRUCE_WOOD),
            new FullBlock("stripped_spruce_log", Blocks.STRIPPED_SPRUCE_LOG, Items.STRIPPED_SPRUCE_LOG), new FullBlock("stripped_spruce_wood", Blocks.STRIPPED_SPRUCE_WOOD, Items.STRIPPED_SPRUCE_WOOD),
            new FullBlock("spruce_fence", Blocks.SPRUCE_FENCE, Items.SPRUCE_FENCE), new FullBlock("spruce_fence_gate", Blocks.SPRUCE_FENCE_GATE, Items.SPRUCE_FENCE_GATE),
            new FullBlock("spruce_door", Blocks.SPRUCE_DOOR, Items.SPRUCE_DOOR), new FullBlock("spruce_trapdoor", Blocks.SPRUCE_TRAPDOOR, Items.SPRUCE_TRAPDOOR),
            new FullBlock("spruce_pressure_plate", Blocks.SPRUCE_PRESSURE_PLATE, Items.SPRUCE_PRESSURE_PLATE), new FullBlock("spruce_button", Blocks.SPRUCE_BUTTON, Items.SPRUCE_BUTTON),
            new FullWallBlock("spruce_sign", Blocks.SPRUCE_SIGN, Blocks.SPRUCE_WALL_SIGN, Items.SPRUCE_SIGN), new FullWallBlock("spruce_hanging_sign", Blocks.SPRUCE_HANGING_SIGN, Blocks.SPRUCE_WALL_HANGING_SIGN, Items.SPRUCE_HANGING_SIGN));
    public static final WoodSet Jungle = new WoodSet(Identifier.of("jungle"), WoodType.JUNGLE, BlockSetType.JUNGLE, new FullBlock("jungle_planks", Blocks.JUNGLE_PLANKS, Items.JUNGLE_PLANKS),
            new FullBlock("jungle_stairs", Blocks.JUNGLE_STAIRS, Items.JUNGLE_STAIRS), new FullBlock("jungle_slab", Blocks.JUNGLE_SLAB, Items.JUNGLE_SLAB),
            new FullBlock("jungle_log", Blocks.JUNGLE_LOG, Items.JUNGLE_LOG), new FullBlock("jungle_wood", Blocks.JUNGLE_WOOD, Items.JUNGLE_WOOD),
            new FullBlock("stripped_jungle_log", Blocks.STRIPPED_JUNGLE_LOG, Items.STRIPPED_JUNGLE_LOG), new FullBlock("stripped_jungle_wood", Blocks.STRIPPED_JUNGLE_WOOD, Items.STRIPPED_JUNGLE_WOOD),
            new FullBlock("jungle_fence", Blocks.JUNGLE_FENCE, Items.JUNGLE_FENCE), new FullBlock("jungle_fence_gate", Blocks.JUNGLE_FENCE_GATE, Items.JUNGLE_FENCE_GATE),
            new FullBlock("jungle_door", Blocks.JUNGLE_DOOR, Items.JUNGLE_DOOR), new FullBlock("jungle_trapdoor", Blocks.JUNGLE_TRAPDOOR, Items.JUNGLE_TRAPDOOR),
            new FullBlock("jungle_pressure_plate", Blocks.JUNGLE_PRESSURE_PLATE, Items.JUNGLE_PRESSURE_PLATE), new FullBlock("jungle_button", Blocks.JUNGLE_BUTTON, Items.JUNGLE_BUTTON),
            new FullWallBlock("jungle_sign", Blocks.JUNGLE_SIGN, Blocks.JUNGLE_WALL_SIGN, Items.JUNGLE_SIGN), new FullWallBlock("jungle_hanging_sign", Blocks.JUNGLE_HANGING_SIGN, Blocks.JUNGLE_WALL_HANGING_SIGN, Items.JUNGLE_HANGING_SIGN));
    public static final WoodSet Acacia = new WoodSet(Identifier.of("acacia"), WoodType.ACACIA, BlockSetType.ACACIA, new FullBlock("acacia_planks", Blocks.ACACIA_PLANKS, Items.ACACIA_PLANKS),
            new FullBlock("acacia_stairs", Blocks.ACACIA_STAIRS, Items.ACACIA_STAIRS), new FullBlock("acacia_slab", Blocks.ACACIA_SLAB, Items.ACACIA_SLAB),
            new FullBlock("acacia_log", Blocks.ACACIA_LOG, Items.ACACIA_LOG), new FullBlock("acacia_wood", Blocks.ACACIA_WOOD, Items.ACACIA_WOOD),
            new FullBlock("stripped_acacia_log", Blocks.STRIPPED_ACACIA_LOG, Items.STRIPPED_ACACIA_LOG), new FullBlock("stripped_acacia_wood", Blocks.STRIPPED_ACACIA_WOOD, Items.STRIPPED_ACACIA_WOOD),
            new FullBlock("acacia_fence", Blocks.ACACIA_FENCE, Items.ACACIA_FENCE), new FullBlock("acacia_fence_gate", Blocks.ACACIA_FENCE_GATE, Items.ACACIA_FENCE_GATE),
            new FullBlock("acacia_door", Blocks.ACACIA_DOOR, Items.ACACIA_DOOR), new FullBlock("acacia_trapdoor", Blocks.ACACIA_TRAPDOOR, Items.ACACIA_TRAPDOOR),
            new FullBlock("acacia_pressure_plate", Blocks.ACACIA_PRESSURE_PLATE, Items.ACACIA_PRESSURE_PLATE), new FullBlock("acacia_button", Blocks.ACACIA_BUTTON, Items.ACACIA_BUTTON),
            new FullWallBlock("acacia_sign", Blocks.ACACIA_SIGN, Blocks.ACACIA_WALL_SIGN, Items.ACACIA_SIGN), new FullWallBlock("acacia_hanging_sign", Blocks.ACACIA_HANGING_SIGN, Blocks.ACACIA_WALL_HANGING_SIGN, Items.ACACIA_HANGING_SIGN));
    public static final WoodSet DarkOak = new WoodSet(Identifier.of("dark_oak"), WoodType.DARK_OAK, BlockSetType.DARK_OAK, new FullBlock("dark_oak_planks", Blocks.DARK_OAK_PLANKS, Items.DARK_OAK_PLANKS),
            new FullBlock("dark_oak_stairs", Blocks.DARK_OAK_STAIRS, Items.DARK_OAK_STAIRS), new FullBlock("dark_oak_slab", Blocks.DARK_OAK_SLAB, Items.DARK_OAK_SLAB),
            new FullBlock("dark_oak_log", Blocks.DARK_OAK_LOG, Items.DARK_OAK_LOG), new FullBlock("dark_oak_wood", Blocks.DARK_OAK_WOOD, Items.DARK_OAK_WOOD),
            new FullBlock("stripped_dark_oak_log", Blocks.STRIPPED_DARK_OAK_LOG, Items.STRIPPED_DARK_OAK_LOG), new FullBlock("stripped_dark_oak_wood", Blocks.STRIPPED_DARK_OAK_WOOD, Items.STRIPPED_DARK_OAK_WOOD),
            new FullBlock("dark_oak_fence", Blocks.DARK_OAK_FENCE, Items.DARK_OAK_FENCE), new FullBlock("dark_oak_fence_gate", Blocks.DARK_OAK_FENCE_GATE, Items.DARK_OAK_FENCE_GATE),
            new FullBlock("dark_oak_door", Blocks.DARK_OAK_DOOR, Items.DARK_OAK_DOOR), new FullBlock("dark_oak_trapdoor", Blocks.DARK_OAK_TRAPDOOR, Items.DARK_OAK_TRAPDOOR),
            new FullBlock("dark_oak_pressure_plate", Blocks.DARK_OAK_PRESSURE_PLATE, Items.DARK_OAK_PRESSURE_PLATE), new FullBlock("dark_oak_button", Blocks.DARK_OAK_BUTTON, Items.DARK_OAK_BUTTON),
            new FullWallBlock("dark_oak_sign", Blocks.DARK_OAK_SIGN, Blocks.DARK_OAK_WALL_SIGN, Items.DARK_OAK_SIGN), new FullWallBlock("dark_oak_hanging_sign", Blocks.DARK_OAK_HANGING_SIGN, Blocks.DARK_OAK_WALL_HANGING_SIGN, Items.DARK_OAK_HANGING_SIGN));
    public static final WoodSet Mangrove = new WoodSet(Identifier.of("mangrove"), WoodType.MANGROVE, BlockSetType.MANGROVE, new FullBlock("mangrove_planks", Blocks.MANGROVE_PLANKS, Items.MANGROVE_PLANKS),
            new FullBlock("mangrove_stairs", Blocks.MANGROVE_STAIRS, Items.MANGROVE_STAIRS), new FullBlock("mangrove_slab", Blocks.MANGROVE_SLAB, Items.MANGROVE_SLAB),
            new FullBlock("mangrove_log", Blocks.MANGROVE_LOG, Items.MANGROVE_LOG), new FullBlock("mangrove_wood", Blocks.MANGROVE_WOOD, Items.MANGROVE_WOOD),
            new FullBlock("stripped_mangrove_log", Blocks.STRIPPED_MANGROVE_LOG, Items.STRIPPED_MANGROVE_LOG), new FullBlock("stripped_mangrove_wood", Blocks.STRIPPED_MANGROVE_WOOD, Items.STRIPPED_MANGROVE_WOOD),
            new FullBlock("mangrove_fence", Blocks.MANGROVE_FENCE, Items.MANGROVE_FENCE), new FullBlock("mangrove_fence_gate", Blocks.MANGROVE_FENCE_GATE, Items.MANGROVE_FENCE_GATE),
            new FullBlock("mangrove_door", Blocks.MANGROVE_DOOR, Items.MANGROVE_DOOR), new FullBlock("mangrove_trapdoor", Blocks.MANGROVE_TRAPDOOR, Items.MANGROVE_TRAPDOOR),
            new FullBlock("mangrove_pressure_plate", Blocks.MANGROVE_PRESSURE_PLATE, Items.MANGROVE_PRESSURE_PLATE), new FullBlock("mangrove_button", Blocks.MANGROVE_BUTTON, Items.MANGROVE_BUTTON),
            new FullWallBlock("mangrove_sign", Blocks.MANGROVE_SIGN, Blocks.MANGROVE_WALL_SIGN, Items.MANGROVE_SIGN), new FullWallBlock("mangrove_hanging_sign", Blocks.MANGROVE_HANGING_SIGN, Blocks.MANGROVE_WALL_HANGING_SIGN, Items.MANGROVE_HANGING_SIGN));
    public static final WoodSet Bamboo = new WoodSet(Identifier.of("bamboo"), WoodType.BAMBOO, BlockSetType.BAMBOO, new FullBlock("bamboo_planks", Blocks.BAMBOO_PLANKS, Items.BAMBOO_PLANKS),
            new FullBlock("bamboo_stairs", Blocks.BAMBOO_STAIRS, Items.BAMBOO_STAIRS), new FullBlock("bamboo_slab", Blocks.BAMBOO_SLAB, Items.BAMBOO_SLAB),
            new FullBlock("bamboo_block", Blocks.BAMBOO_BLOCK, Items.BAMBOO_BLOCK), null,
            new FullBlock("stripped_bamboo_block", Blocks.STRIPPED_BAMBOO_BLOCK, Items.STRIPPED_BAMBOO_BLOCK), null,
            new FullBlock("bamboo_fence", Blocks.BAMBOO_FENCE, Items.BAMBOO_FENCE), new FullBlock("bamboo_fence_gate", Blocks.BAMBOO_FENCE_GATE, Items.BAMBOO_FENCE_GATE),
            new FullBlock("bamboo_door", Blocks.BAMBOO_DOOR, Items.BAMBOO_DOOR), new FullBlock("bamboo_trapdoor", Blocks.BAMBOO_TRAPDOOR, Items.BAMBOO_TRAPDOOR),
            new FullBlock("bamboo_pressure_plate", Blocks.BAMBOO_PRESSURE_PLATE, Items.BAMBOO_PRESSURE_PLATE), new FullBlock("bamboo_button", Blocks.BAMBOO_BUTTON, Items.BAMBOO_BUTTON),
            new FullWallBlock("bamboo_sign", Blocks.BAMBOO_SIGN, Blocks.BAMBOO_WALL_SIGN, Items.BAMBOO_SIGN), new FullWallBlock("bamboo_hanging_sign", Blocks.BAMBOO_HANGING_SIGN, Blocks.BAMBOO_WALL_HANGING_SIGN, Items.BAMBOO_HANGING_SIGN));
    public static final WoodSet Cherry = new WoodSet(Identifier.of("cherry"), WoodType.CHERRY, BlockSetType.CHERRY, new FullBlock("cherry_planks", Blocks.CHERRY_PLANKS, Items.CHERRY_PLANKS),
            new FullBlock("cherry_stairs", Blocks.CHERRY_STAIRS, Items.CHERRY_STAIRS), new FullBlock("cherry_slab", Blocks.CHERRY_SLAB, Items.CHERRY_SLAB),
            new FullBlock("cherry_log", Blocks.CHERRY_LOG, Items.CHERRY_LOG), new FullBlock("cherry_wood", Blocks.CHERRY_WOOD, Items.CHERRY_WOOD),
            new FullBlock("stripped_cherry_log", Blocks.STRIPPED_CHERRY_LOG, Items.STRIPPED_CHERRY_LOG), new FullBlock("stripped_cherry_wood", Blocks.STRIPPED_CHERRY_WOOD, Items.STRIPPED_CHERRY_WOOD),
            new FullBlock("cherry_fence", Blocks.CHERRY_FENCE, Items.CHERRY_FENCE), new FullBlock("cherry_fence_gate", Blocks.CHERRY_FENCE_GATE, Items.CHERRY_FENCE_GATE),
            new FullBlock("cherry_door", Blocks.CHERRY_DOOR, Items.CHERRY_DOOR), new FullBlock("cherry_trapdoor", Blocks.CHERRY_TRAPDOOR, Items.CHERRY_TRAPDOOR),
            new FullBlock("cherry_pressure_plate", Blocks.CHERRY_PRESSURE_PLATE, Items.CHERRY_PRESSURE_PLATE), new FullBlock("cherry_button", Blocks.CHERRY_BUTTON, Items.CHERRY_BUTTON),
            new FullWallBlock("cherry_sign", Blocks.CHERRY_SIGN, Blocks.CHERRY_WALL_SIGN, Items.CHERRY_SIGN), new FullWallBlock("cherry_hanging_sign", Blocks.CHERRY_HANGING_SIGN, Blocks.CHERRY_WALL_HANGING_SIGN, Items.CHERRY_HANGING_SIGN));
    /*
    public static final WoodSet PaleOak = new WoodSet(Identifier.of("pale_oak"), WoodType.PALE_OAK, BlockSetType.PALE_OAK, new FullBlock("pale_oak_planks", Blocks.PALE_OAK_PLANKS, Items.PALE_OAK_PLANKS),
            new FullBlock("pale_oak_stairs", Blocks.PALE_OAK_STAIRS, Items.PALE_OAK_STAIRS), new FullBlock("pale_oak_slab", Blocks.PALE_OAK_SLAB, Items.PALE_OAK_SLAB),
            new FullBlock("pale_oak_log", Blocks.PALE_OAK_LOG, Items.PALE_OAK_LOG), new FullBlock("pale_oak_wood", Blocks.PALE_OAK_WOOD, Items.PALE_OAK_WOOD),
            new FullBlock("stripped_pale_oak_log", Blocks.STRIPPED_PALE_OAK_LOG, Items.STRIPPED_PALE_OAK_LOG), new FullBlock("stripped_pale_oak_wood", Blocks.STRIPPED_PALE_OAK_WOOD, Items.STRIPPED_PALE_OAK_WOOD),
            new FullBlock("pale_oak_fence", Blocks.PALE_OAK_FENCE, Items.PALE_OAK_FENCE), new FullBlock("pale_oak_fence_gate", Blocks.PALE_OAK_FENCE_GATE, Items.PALE_OAK_FENCE_GATE),
            new FullBlock("pale_oak_door", Blocks.PALE_OAK_DOOR, Items.PALE_OAK_DOOR), new FullBlock("pale_oak_trapdoor", Blocks.PALE_OAK_TRAPDOOR, Items.PALE_OAK_TRAPDOOR),
            new FullBlock("pale_oak_pressure_plate", Blocks.PALE_OAK_PRESSURE_PLATE, Items.PALE_OAK_PRESSURE_PLATE), new FullBlock("pale_oak_button", Blocks.PALE_OAK_BUTTON, Items.PALE_OAK_BUTTON),
            new FullWallBlock("pale_oak_sign", Blocks.PALE_OAK_SIGN, Blocks.PALE_OAK_WALL_SIGN, Items.PALE_OAK_SIGN), new FullWallBlock("pale_oak_hanging_sign", Blocks.PALE_OAK_HANGING_SIGN, Blocks.PALE_OAK_WALL_HANGING_SIGN, Items.PALE_OAK_HANGING_SIGN));

     */
    public static final WoodSet Crimson = new WoodSet(Identifier.of("crimson"), WoodType.CRIMSON, BlockSetType.CRIMSON, new FullBlock("crimson_planks", Blocks.CRIMSON_PLANKS, Items.CRIMSON_PLANKS),
            new FullBlock("crimson_stairs", Blocks.CRIMSON_STAIRS, Items.CRIMSON_STAIRS), new FullBlock("crimson_slab", Blocks.CRIMSON_SLAB, Items.CRIMSON_SLAB),
            new FullBlock("crimson_stem", Blocks.CRIMSON_STEM, Items.CRIMSON_STEM), new FullBlock("crimson_hyphae", Blocks.CRIMSON_HYPHAE, Items.CRIMSON_HYPHAE),
            new FullBlock("stripped_crimson_stem", Blocks.STRIPPED_CRIMSON_STEM, Items.STRIPPED_CRIMSON_STEM), new FullBlock("stripped_crimson_hyphae", Blocks.STRIPPED_CRIMSON_HYPHAE, Items.STRIPPED_CRIMSON_HYPHAE),
            new FullBlock("crimson_fence", Blocks.CRIMSON_FENCE, Items.CRIMSON_FENCE), new FullBlock("crimson_fence_gate", Blocks.CRIMSON_FENCE_GATE, Items.CRIMSON_FENCE_GATE),
            new FullBlock("crimson_door", Blocks.CRIMSON_DOOR, Items.CRIMSON_DOOR), new FullBlock("crimson_trapdoor", Blocks.CRIMSON_TRAPDOOR, Items.CRIMSON_TRAPDOOR),
            new FullBlock("crimson_pressure_plate", Blocks.CRIMSON_PRESSURE_PLATE, Items.CRIMSON_PRESSURE_PLATE), new FullBlock("crimson_button", Blocks.CRIMSON_BUTTON, Items.CRIMSON_BUTTON),
            new FullWallBlock("crimson_sign", Blocks.CRIMSON_SIGN, Blocks.CRIMSON_WALL_SIGN, Items.CRIMSON_SIGN), new FullWallBlock("crimson_hanging_sign", Blocks.CRIMSON_HANGING_SIGN, Blocks.CRIMSON_WALL_HANGING_SIGN, Items.CRIMSON_HANGING_SIGN));
    public static final WoodSet Warped = new WoodSet(Identifier.of("warped"), WoodType.WARPED, BlockSetType.WARPED, new FullBlock("warped_planks", Blocks.WARPED_PLANKS, Items.WARPED_PLANKS),
            new FullBlock("warped_stairs", Blocks.WARPED_STAIRS, Items.WARPED_STAIRS), new FullBlock("warped_slab", Blocks.WARPED_SLAB, Items.WARPED_SLAB),
            new FullBlock("warped_stem", Blocks.WARPED_STEM, Items.WARPED_STEM), new FullBlock("warped_hyphae", Blocks.WARPED_HYPHAE, Items.WARPED_HYPHAE),
            new FullBlock("stripped_warped_stem", Blocks.STRIPPED_WARPED_STEM, Items.STRIPPED_WARPED_STEM), new FullBlock("stripped_warped_hyphae", Blocks.STRIPPED_WARPED_HYPHAE, Items.STRIPPED_WARPED_HYPHAE),
            new FullBlock("warped_fence", Blocks.WARPED_FENCE, Items.WARPED_FENCE), new FullBlock("warped_fence_gate", Blocks.WARPED_FENCE_GATE, Items.WARPED_FENCE_GATE),
            new FullBlock("warped_door", Blocks.WARPED_DOOR, Items.WARPED_DOOR), new FullBlock("warped_trapdoor", Blocks.WARPED_TRAPDOOR, Items.WARPED_TRAPDOOR),
            new FullBlock("warped_pressure_plate", Blocks.WARPED_PRESSURE_PLATE, Items.WARPED_PRESSURE_PLATE), new FullBlock("warped_button", Blocks.WARPED_BUTTON, Items.WARPED_BUTTON),
            new FullWallBlock("warped_sign", Blocks.WARPED_SIGN, Blocks.WARPED_WALL_SIGN, Items.WARPED_SIGN), new FullWallBlock("warped_hanging_sign", Blocks.WARPED_HANGING_SIGN, Blocks.WARPED_WALL_HANGING_SIGN, Items.WARPED_HANGING_SIGN));
    public static final Map<Identifier, BlockGroup> WoodSets = Map.ofEntries(Oak.entry(), Birch.entry(), Spruce.entry(), Jungle.entry(), Acacia.entry(), DarkOak.entry(), Mangrove.entry(),
            Bamboo.entry(), Cherry.entry(), Crimson.entry(), Warped.entry());
}
