package jacobg5.japi;

import jacobg5.japi.containers.*;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class JDataGen implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {}

    public static void offerGearCrafting(RecipeExporter exporter, GearSet gearSet, ItemConvertible ingredient, ItemConvertible handle) {
        if (gearSet.shovel != null) {
            ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, gearSet.shovel)
                    .pattern("I")
                    .pattern("H")
                    .pattern("H")
                    .input('I', ingredient)
                    .input('H', handle)
                    .criterion(RecipeProvider.hasItem(ingredient), RecipeProvider.conditionsFromItem(ingredient))
                    .offerTo(exporter);
        }
        if (gearSet.sword != null) {
            ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, gearSet.sword)
                    .pattern("I")
                    .pattern("I")
                    .pattern("H")
                    .input('I', ingredient)
                    .input('H', handle)
                    .criterion(RecipeProvider.hasItem(ingredient), RecipeProvider.conditionsFromItem(ingredient))
                    .offerTo(exporter);
        }
        if (gearSet.pickaxe != null) {
            ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, gearSet.pickaxe)
                    .pattern("III")
                    .pattern(" H ")
                    .pattern(" H ")
                    .input('I', ingredient)
                    .input('H', handle)
                    .criterion(RecipeProvider.hasItem(ingredient), RecipeProvider.conditionsFromItem(ingredient))
                    .offerTo(exporter);
        }
        if (gearSet.axe != null) {
            ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, gearSet.axe)
                    .pattern("II")
                    .pattern("HI")
                    .pattern("H ")
                    .input('I', ingredient)
                    .input('H', handle)
                    .criterion(RecipeProvider.hasItem(ingredient), RecipeProvider.conditionsFromItem(ingredient))
                    .offerTo(exporter);
        }
        if (gearSet.hoe != null) {
            ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, gearSet.hoe)
                    .pattern("II")
                    .pattern("H ")
                    .pattern("H ")
                    .input('I', ingredient)
                    .input('H', handle)
                    .criterion(RecipeProvider.hasItem(ingredient), RecipeProvider.conditionsFromItem(ingredient))
                    .offerTo(exporter);
        }

        if (gearSet.helmet != null) {
            ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, gearSet.helmet)
                    .pattern("III")
                    .pattern("I I")
                    .input('I', ingredient)
                    .criterion(RecipeProvider.hasItem(ingredient), RecipeProvider.conditionsFromItem(ingredient))
                    .offerTo(exporter);
        }
        if (gearSet.chestplate != null) {
            ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, gearSet.chestplate)
                    .pattern("I I")
                    .pattern("III")
                    .pattern("III")
                    .input('I', ingredient)
                    .criterion(RecipeProvider.hasItem(ingredient), RecipeProvider.conditionsFromItem(ingredient))
                    .offerTo(exporter);
        }
        if (gearSet.leggings != null) {
            ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, gearSet.leggings)
                    .pattern("III")
                    .pattern("I I")
                    .pattern("I I")
                    .input('I', ingredient)
                    .criterion(RecipeProvider.hasItem(ingredient), RecipeProvider.conditionsFromItem(ingredient))
                    .offerTo(exporter);
        }
        if (gearSet.boots != null) {
            ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, gearSet.boots)
                    .pattern("I I")
                    .pattern("I I")
                    .input('I', ingredient)
                    .criterion(RecipeProvider.hasItem(ingredient), RecipeProvider.conditionsFromItem(ingredient))
                    .offerTo(exporter);
        }
    }

    public static void offerGearSmelting(RecipeExporter exporter, GearSet gearSet, ItemConvertible output, boolean blasting) {
        offerGearSmelting(exporter, gearSet, output, blasting, 0.1F);
    }

    public static void offerGearSmelting(RecipeExporter exporter, GearSet gearSet, ItemConvertible output, boolean blasting, float experience) {
        List<ItemConvertible> items = List.copyOf(gearSet.Equipment.values());
        RecipeProvider.offerSmelting(exporter, items, RecipeCategory.MISC, output, experience, 200, gearSet.identifier.getPath());
        if (blasting) {
            RecipeProvider.offerBlasting(exporter, items, RecipeCategory.MISC, output, experience, 100, gearSet.identifier.getPath());
        }
    }

    public static void offerCrafting(RecipeExporter exporter, BlockSet blockSet) {
        if (blockSet.block == null) return;
        offerCrafting(exporter, blockSet, Ingredient.ofItems(blockSet));
    }

    public static void offerCrafting(RecipeExporter exporter, BlockSet blockSet, Ingredient ingredient) {
        if (blockSet.stair != null) {
            RecipeProvider.createStairsRecipe(blockSet.stair, ingredient)
                    .criterion(RecipeProvider.hasItem(blockSet), RecipeProvider.conditionsFromItem(blockSet))
                    .offerTo(exporter);
        }
        if (blockSet.slab != null) {
            RecipeProvider.createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, blockSet.slab, ingredient)
                    .criterion(RecipeProvider.hasItem(blockSet), RecipeProvider.conditionsFromItem(blockSet))
                    .offerTo(exporter);
        }
        if (blockSet.wall != null) {
            RecipeProvider.getWallRecipe(RecipeCategory.BUILDING_BLOCKS, blockSet.wall, ingredient)
                    .criterion(RecipeProvider.hasItem(blockSet), RecipeProvider.conditionsFromItem(blockSet))
                    .offerTo(exporter);
        }
    }

    public static void offerCrafting(RecipeExporter exporter, WoodSet woodSet) {
        offerCrafting(exporter, woodSet, 4);
    }

    public static void offerCrafting(RecipeExporter exporter, WoodSet woodSet, int plankCount) {
        if (woodSet.plank == null) return;

        Ingredient plank = Ingredient.ofItems(woodSet.plank);
        RecipeProvider.offerPlanksRecipe(exporter, woodSet.plank, woodSet.logItems, plankCount);

        if (woodSet.stair != null) {
            RecipeProvider.createStairsRecipe(woodSet.stair, plank)
                    .criterion(RecipeProvider.hasItem(woodSet), RecipeProvider.conditionsFromItem(woodSet))
                    .offerTo(exporter);
        }
        if (woodSet.slab != null) {
            RecipeProvider.createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, woodSet.slab, plank)
                    .criterion(RecipeProvider.hasItem(woodSet), RecipeProvider.conditionsFromItem(woodSet))
                    .offerTo(exporter);
        }
        if (woodSet.wood != null && woodSet.log != null) {
            offerWoodBlockRecipe(exporter, woodSet.wood, woodSet.log);
        }
        if (woodSet.stripped_wood != null && woodSet.stripped_log != null) {
            offerWoodBlockRecipe(exporter, woodSet.stripped_wood, woodSet.stripped_log);
        }
        if (woodSet.fence != null) {
            RecipeProvider.createFenceRecipe(woodSet.fence, plank)
                    .criterion(RecipeProvider.hasItem(woodSet), RecipeProvider.conditionsFromItem(woodSet))
                    .offerTo(exporter);
        }
        if (woodSet.fenceGate != null) {
            RecipeProvider.createFenceGateRecipe(woodSet.fenceGate, plank)
                    .criterion(RecipeProvider.hasItem(woodSet), RecipeProvider.conditionsFromItem(woodSet))
                    .offerTo(exporter);
        }
        if (woodSet.door != null) {
            RecipeProvider.createDoorRecipe(woodSet.door, plank)
                    .criterion(RecipeProvider.hasItem(woodSet), RecipeProvider.conditionsFromItem(woodSet))
                    .offerTo(exporter);
        }
        if (woodSet.trapdoor != null) {
            RecipeProvider.createDoorRecipe(woodSet.trapdoor, plank)
                    .criterion(RecipeProvider.hasItem(woodSet), RecipeProvider.conditionsFromItem(woodSet))
                    .offerTo(exporter);
        }
        if (woodSet.plate != null) {
            RecipeProvider.createPressurePlateRecipe(RecipeCategory.REDSTONE, woodSet.plate, plank)
                    .criterion(RecipeProvider.hasItem(woodSet), RecipeProvider.conditionsFromItem(woodSet))
                    .offerTo(exporter);
        }
        if (woodSet.button != null) {
            ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, woodSet.button, 1)
                    .input('#', woodSet)
                    .pattern("#")
                    .criterion(RecipeProvider.hasItem(woodSet), RecipeProvider.conditionsFromItem(woodSet))
                    .group("wooden_button")
                    .offerTo(exporter);
        }
        if (woodSet.sign != null) {
            RecipeProvider.createSignRecipe(woodSet.sign, plank)
                    .criterion(RecipeProvider.hasItem(woodSet), RecipeProvider.conditionsFromItem(woodSet))
                    .offerTo(exporter);
        }
        if (woodSet.hangingSign != null) {
            RecipeProvider.offerHangingSignRecipe(exporter, woodSet.hangingSign, woodSet);
        }
    }

    public static void offerWoodBlockRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, output, 3)
                .input('#', input)
                .pattern("##")
                .pattern("##")
                .criterion(RecipeProvider.hasItem(input), RecipeProvider.conditionsFromItem(input))
                .group("bark")
                .offerTo(exporter);
    }

    public static void offerDyeRecipe(RecipeExporter exporter, ColoredBlockSet<?> coloredSet, String group) {
        var dyeItemList = getDyeItemPairs(coloredSet);
        RecipeProvider.offerDyeableRecipes(exporter, dyeItemList.stream().map(Pair::getLeft).toList(), dyeItemList.stream().map(Pair::getRight).toList(), group);
    }

    public static void offerDyeRecipe(RecipeExporter exporter, ColoredBlockSet<?> coloredSet, String group, Ingredient ingredient, AdvancementCriterion<?> criterion) {
        for (Pair<Item, Item> pair : getDyeItemPairs(coloredSet)) {
            ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, pair.getRight(), 8)
                    .input('#', ingredient)
                    .input('X', pair.getLeft())
                    .pattern("###")
                    .pattern("#X#")
                    .pattern("###")
                    .group("group")
                    .criterion("has_" + group, criterion)
                    .offerTo(exporter);
        }
    }

    private static List<Pair<Item, Item>> getDyeItemPairs(ColoredBlockSet<?> coloredSet) {
        List<Pair<Item, Item>> pairList = new ArrayList<>();
        for (DyeColor color : DyeColor.values()) {
            FullBlock block = coloredSet.get(color);
            if (block != null) {
                pairList.add(new Pair<>(DyeItem.byColor(color), block.item()));
            }
        }
        return pairList;
    }

    public static void addDrops(FabricBlockLootTableProvider provider, BlockGroup group) {
        for (BlockItemPair pair : group.Set.values()) {
            provider.addDrop(pair.asBlock());
        }
    }
}
