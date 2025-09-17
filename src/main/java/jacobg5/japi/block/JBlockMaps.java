package jacobg5.japi.block;

import com.google.common.collect.ImmutableMap;
import jacobg5.japi.JAPI;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.item.*;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.ReloadableRegistries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Pair;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public enum JBlockMaps {
    /**
     * Block Input/Output for Tilling with a {@link HoeItem}
     * Similar to {@link FarmlandBlock}
     */
    TILL(new ImmutableMap.Builder<Block, MapData>()
            .build()),
    /**
     * Block Input/Output for creating Paths with a {@link ShovelItem}
     * Similar to {@link DirtPathBlock}
     */
    PATH(new ImmutableMap.Builder<Block, MapData>()
            .build()),
    /**
     * Block Input/Output for Stripping with an {@link AxeItem}
     * Similar to Logs
     */
    STRIP(new ImmutableMap.Builder<Block, MapData>()
            .build()),
    /**
     * Block Input/Output for Scraping with a {@link AxeItem}
     * Similar to Copper
     */
    SCRAPE(new ImmutableMap.Builder<Block, MapData>()
            .build()),
    /**
     * Block Input/Output for Smashing
     * Maps blocks to their Cobbled/Cracked counterparts
     */
    SMASH(new ImmutableMap.Builder<Block, MapData>()
            .put(Blocks.STONE, new MapData(Blocks.COBBLESTONE))
            .put(Blocks.STONE_STAIRS, new MapData(Blocks.COBBLESTONE_STAIRS))
            .put(Blocks.STONE_SLAB, new MapData(Blocks.COBBLESTONE_SLAB))
            .put(Blocks.INFESTED_STONE, new MapData(Blocks.INFESTED_COBBLESTONE))
            .put(Blocks.DEEPSLATE, new MapData(Blocks.COBBLED_DEEPSLATE))
            .put(Blocks.STONE_BRICKS, new MapData(Blocks.CRACKED_STONE_BRICKS))
            .put(Blocks.INFESTED_STONE_BRICKS, new MapData(Blocks.INFESTED_CRACKED_STONE_BRICKS))
            .put(Blocks.DEEPSLATE_BRICKS, new MapData(Blocks.CRACKED_DEEPSLATE_BRICKS))
            .put(Blocks.DEEPSLATE_TILES, new MapData(Blocks.CRACKED_DEEPSLATE_TILES))
            .put(Blocks.NETHER_BRICKS, new MapData(Blocks.CRACKED_NETHER_BRICKS))
            .put(Blocks.POLISHED_BLACKSTONE_BRICKS, new MapData(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS))
            .build()),
    /**
     * Block Input/Output for Extracting
     * Maps ore blocks to their base stone while dropping their loot
     */
    EXTRACT(new ImmutableMap.Builder<Block, MapData>()
            .put(Blocks.COAL_ORE, new MapData(Blocks.STONE, Blocks.COAL_ORE.getLootTableKey()))
            .put(Blocks.DEEPSLATE_COAL_ORE, new MapData(Blocks.DEEPSLATE, Blocks.DEEPSLATE_COAL_ORE.getLootTableKey()))
            .put(Blocks.IRON_ORE, new MapData(Blocks.STONE, Blocks.IRON_ORE.getLootTableKey()))
            .put(Blocks.DEEPSLATE_IRON_ORE, new MapData(Blocks.DEEPSLATE, Blocks.DEEPSLATE_IRON_ORE.getLootTableKey()))
            .put(Blocks.COPPER_ORE, new MapData(Blocks.STONE, Blocks.COPPER_ORE.getLootTableKey()))
            .put(Blocks.DEEPSLATE_COPPER_ORE, new MapData(Blocks.DEEPSLATE, Blocks.DEEPSLATE_COPPER_ORE.getLootTableKey()))
            .put(Blocks.GOLD_ORE, new MapData(Blocks.STONE, Blocks.GOLD_ORE.getLootTableKey()))
            .put(Blocks.DEEPSLATE_GOLD_ORE, new MapData(Blocks.DEEPSLATE, Blocks.DEEPSLATE_GOLD_ORE.getLootTableKey()))
            .put(Blocks.REDSTONE_ORE, new MapData(Blocks.STONE, Blocks.REDSTONE_ORE.getLootTableKey()))
            .put(Blocks.DEEPSLATE_REDSTONE_ORE, new MapData(Blocks.DEEPSLATE, Blocks.DEEPSLATE_REDSTONE_ORE.getLootTableKey()))
            .put(Blocks.EMERALD_ORE, new MapData(Blocks.STONE, Blocks.EMERALD_ORE.getLootTableKey()))
            .put(Blocks.DEEPSLATE_EMERALD_ORE, new MapData(Blocks.DEEPSLATE, Blocks.DEEPSLATE_EMERALD_ORE.getLootTableKey()))
            .put(Blocks.LAPIS_ORE, new MapData(Blocks.STONE, Blocks.LAPIS_ORE.getLootTableKey()))
            .put(Blocks.DEEPSLATE_LAPIS_ORE, new MapData(Blocks.DEEPSLATE, Blocks.DEEPSLATE_LAPIS_ORE.getLootTableKey()))
            .put(Blocks.DIAMOND_ORE, new MapData(Blocks.STONE, Blocks.DIAMOND_ORE.getLootTableKey()))
            .put(Blocks.DEEPSLATE_DIAMOND_ORE, new MapData(Blocks.DEEPSLATE, Blocks.DEEPSLATE_DIAMOND_ORE.getLootTableKey()))
            .put(Blocks.NETHER_GOLD_ORE, new MapData(Blocks.NETHERRACK, Blocks.NETHER_GOLD_ORE.getLootTableKey()))
            .put(Blocks.NETHER_QUARTZ_ORE, new MapData(Blocks.NETHERRACK, Blocks.NETHER_QUARTZ_ORE.getLootTableKey()))
            .build()),
    /**
     * Block Input/Output for Shattering
     * Maps ore blocks to their smashed state
     */
    SHATTER(new ImmutableMap.Builder<Block, MapData>()
            .put(Blocks.GLASS, new MapData(Blocks.AIR))
            .put(Blocks.GLASS_PANE, new MapData(Blocks.AIR))
            .put(Blocks.WHITE_STAINED_GLASS, new MapData(Blocks.AIR))
            .put(Blocks.WHITE_STAINED_GLASS_PANE, new MapData(Blocks.AIR))
            .put(Blocks.LIGHT_GRAY_STAINED_GLASS, new MapData(Blocks.AIR))
            .put(Blocks.LIGHT_GRAY_STAINED_GLASS_PANE, new MapData(Blocks.AIR))
            .put(Blocks.GRAY_STAINED_GLASS, new MapData(Blocks.AIR))
            .put(Blocks.GRAY_STAINED_GLASS_PANE, new MapData(Blocks.AIR))
            .put(Blocks.BLACK_STAINED_GLASS, new MapData(Blocks.AIR))
            .put(Blocks.BLACK_STAINED_GLASS_PANE, new MapData(Blocks.AIR))
            .put(Blocks.BROWN_STAINED_GLASS, new MapData(Blocks.AIR))
            .put(Blocks.BROWN_STAINED_GLASS_PANE, new MapData(Blocks.AIR))
            .put(Blocks.RED_STAINED_GLASS, new MapData(Blocks.AIR))
            .put(Blocks.RED_STAINED_GLASS_PANE, new MapData(Blocks.AIR))
            .put(Blocks.ORANGE_STAINED_GLASS, new MapData(Blocks.AIR))
            .put(Blocks.ORANGE_STAINED_GLASS_PANE, new MapData(Blocks.AIR))
            .put(Blocks.YELLOW_STAINED_GLASS, new MapData(Blocks.AIR))
            .put(Blocks.YELLOW_STAINED_GLASS_PANE, new MapData(Blocks.AIR))
            .put(Blocks.LIME_STAINED_GLASS, new MapData(Blocks.AIR))
            .put(Blocks.LIME_STAINED_GLASS_PANE, new MapData(Blocks.AIR))
            .put(Blocks.GREEN_STAINED_GLASS, new MapData(Blocks.AIR))
            .put(Blocks.GREEN_STAINED_GLASS_PANE, new MapData(Blocks.AIR))
            .put(Blocks.CYAN_STAINED_GLASS, new MapData(Blocks.AIR))
            .put(Blocks.CYAN_STAINED_GLASS_PANE, new MapData(Blocks.AIR))
            .put(Blocks.LIGHT_BLUE_STAINED_GLASS, new MapData(Blocks.AIR))
            .put(Blocks.LIGHT_BLUE_STAINED_GLASS_PANE, new MapData(Blocks.AIR))
            .put(Blocks.BLUE_STAINED_GLASS, new MapData(Blocks.AIR))
            .put(Blocks.BLUE_STAINED_GLASS_PANE, new MapData(Blocks.AIR))
            .put(Blocks.PURPLE_STAINED_GLASS, new MapData(Blocks.AIR))
            .put(Blocks.PURPLE_STAINED_GLASS_PANE, new MapData(Blocks.AIR))
            .put(Blocks.MAGENTA_STAINED_GLASS, new MapData(Blocks.AIR))
            .put(Blocks.MAGENTA_STAINED_GLASS_PANE, new MapData(Blocks.AIR))
            .put(Blocks.PINK_STAINED_GLASS, new MapData(Blocks.AIR))
            .put(Blocks.PINK_STAINED_GLASS_PANE, new MapData(Blocks.AIR))
            .build());

    private final Map<Block, MapData> blockMap = new HashMap<>();

    JBlockMaps(Map<Block, MapData> blockMap) {
        this.blockMap.putAll(blockMap);
    }

    /**
     * Registers conversion from input block to output block
     *
     * @param input input block
     * @param output output block
     */
    public void addConversion(Block input, Block output) {
        MapData data = blockMap.get(input);
        if (data != null) {
            if (data.output != null) {
                JAPI.LOGGER.warn("Conversion type: {} already contains output for {}", toString(), input.getName().getString());
                return;
            }
            data.output = output;
        }
        else blockMap.put(input, new MapData(output));
    }

    /**
     * Registers conversion from input block to output block with item drops
     *
     * @param input input block
     * @param output output block
     * @param lootTable loot table key for item drops on conversion
     */
    public void addConversion(Block input, Block output, RegistryKey<LootTable> lootTable) {
        MapData data = blockMap.get(input);
        if (data != null) {
            if (data.output != null) {
                JAPI.LOGGER.warn("Conversion type: {} already contains output for {}", toString(), input.getName().getString());
                return;
            }
            data.output = output;
            data.dropLoot.add(lootTable);
        }
        else blockMap.put(input, new MapData(output, lootTable));
    }

    /**
     * Registers conversion from input block to output block with item drops
     *
     * @param input input block
     * @param output output block
     * @param stacks item stacks to drop on conversion
     */
    public void addConversion(Block input, Block output, ItemStack... stacks) {
        MapData data = blockMap.get(input);
        if (data != null) {
            if (data.output != null) {
                JAPI.LOGGER.warn("Conversion type: {} already contains output for {}", toString(), input.getName().getString());
                return;
            }
            data.output = output;
            data.dropStacks.addAll(List.of(stacks));
        }
        else blockMap.put(input, new MapData(output, new ArrayList<>(List.of(stacks))));
    }

    /**
     * Adds extra item drops to the specified block input
     *
     * @param input input block
     * @param lootTable output block
     */
    public void addDrop(Block input, RegistryKey<LootTable> lootTable) {
        MapData data = blockMap.get(input);
        if (data == null) {
            blockMap.put(input, data = new MapData(null));
        }
        if (data.output == null) {
            JAPI.LOGGER.warn("Conversion type: {} for {} has been given drops but no block output", toString(), input.getName().getString());
        }
        data.dropLoot.add(lootTable);
    }

    /**
     * Adds extra item drops to the specified block input
     *
     * @param input input block
     * @param stacks item stacks to drop on conversion
     */
    public void addDrop(Block input, ItemStack... stacks) {
        MapData data = blockMap.get(input);
        if (data == null) {
            blockMap.put(input, data = new MapData(null));
        }
        if (data.output == null) {
            JAPI.LOGGER.warn("Conversion type: {} for {} has been given drops but no block output", toString(), input.getName().getString());
        }
        data.dropStacks.addAll(List.of(stacks));
    }

    /**
     * {@return optional block output for the provided input}
     *
     * @param input input block
     */
    public Optional<Block> getConversion(Block input) {
        Optional<MapData> optional = Optional.ofNullable(blockMap.get(input));
        return optional.map(data -> data.output);
    }

    /**
     * {@return optional block state output for the provided input preserving the state from the original block}
     *
     * @param state input block state
     */
    public Optional<BlockState> getConversionState(BlockState state) {
        Optional<MapData> optional = Optional.ofNullable(blockMap.get(state.getBlock()));
        return optional.map(data -> {
            if (data.output == null) return null;
            return data.output.getStateWithProperties(state);
        });
    }

    /**
     * {@return optional block state output for the provided input state is determined by placement context}
     *
     * @param block input block
     * @param placementContext context used to create the block state
     */
    public Optional<BlockState> getConversionState(Block block, ItemPlacementContext placementContext) {
        Optional<MapData> optional = Optional.ofNullable(blockMap.get(block));
        return optional.map(data -> {
            if (data.output == null) return null;
            return data.output.getPlacementState(placementContext);
        });
    }

    /**
     * {@return optional block state output from the block at the provided position also spawning drops at the block's face}
     *
     * @param world world the block is located in
     * @param pos position of the block to check
     * @param direction side of the block interacted with
     * @param tool item responsible for the conversion
     * @param entity entity responsible for the conversion
     * @param placementContext used to determine the block state (null value preserves the state from the original block)
     */
    public Pair<Boolean, Optional<BlockState>> getConversionAndDrop(World world, BlockPos pos, Direction direction, ItemStack tool, @Nullable Entity entity, @Nullable ItemPlacementContext placementContext) {
        BlockState state = world.getBlockState(pos);
        Optional<MapData> optional = Optional.ofNullable(blockMap.get(state.getBlock()));

        if (optional.isPresent() && world instanceof ServerWorld serverWorld) {
            LootContextParameterSet context = new LootContextParameterSet.Builder(serverWorld)
                    .add(LootContextParameters.ORIGIN, Vec3d.ofCenter(pos))
                    .add(LootContextParameters.TOOL, tool)
                    .addOptional(LootContextParameters.THIS_ENTITY, entity)
                    .add(LootContextParameters.BLOCK_STATE, state)
                    .build(LootContextTypes.BLOCK);

            ReloadableRegistries.Lookup lookup = serverWorld.getServer().getReloadableRegistries();

            optional.get().getAllDrops(context, lookup).forEach(stack -> {
                Block.dropStack(world, pos, direction, stack);
            });
        }
        return new Pair<>(optional.filter(mapData -> !mapData.isEmpty()).isPresent(), optional.map(data -> {
            if (data.output == null) return null;
            if (placementContext != null) return data.output.getPlacementState(placementContext);
            return data.output.getStateWithProperties(state);
        }));
    }

    /**
     * {@return gets a list of items dropped by the provided block state}
     *
     * @param serverWorld world the block is located in
     * @param state state used as the input
     * @param pos when provided is added as context for the loot table generation
     * @param stack when provided is added as context for the loot table generation
     * @param entity when provided is added as context for the loot table generation
     */
    public List<ItemStack> getDrops(ServerWorld serverWorld, BlockState state, @Nullable Vec3d pos, @Nullable ItemStack stack, @Nullable Entity entity) {
        Optional<MapData> optional = Optional.ofNullable(blockMap.get(state.getBlock()));
        if (optional.isPresent()) {
            LootContextParameterSet context = new LootContextParameterSet.Builder(serverWorld)
                    .addOptional(LootContextParameters.ORIGIN, pos)
                    .addOptional(LootContextParameters.TOOL, stack)
                    .addOptional(LootContextParameters.THIS_ENTITY, entity)
                    .add(LootContextParameters.BLOCK_STATE, state)
                    .build(LootContextTypes.BLOCK);

            return optional.get().getAllDrops(context, serverWorld.getServer().getReloadableRegistries());
        }
        return List.of();
    }

    private static class MapData {
        public Block output;
        public ArrayList<ItemStack> dropStacks;
        public ArrayList<RegistryKey<LootTable>> dropLoot;

        public MapData(Block output, List<ItemStack> dropStacks, List<RegistryKey<LootTable>> dropLoot) {
            this.output = output;
            this.dropStacks = new ArrayList<>(dropStacks);
            this.dropLoot = new ArrayList<>(dropLoot);
        }
        public MapData(Block output, List<ItemStack> dropStacks) {
            this.output = output;
            this.dropStacks = new ArrayList<>(dropStacks);
            this.dropLoot = new ArrayList<>();
        }
        public MapData(Block output, RegistryKey<LootTable> dropLoot) {
            this.output = output;
            this.dropStacks = new ArrayList<>();
            this.dropLoot = new ArrayList<>(List.of(dropLoot));
        }
        public MapData(Block output) {
            this.output = output;
            this.dropStacks = new ArrayList<>();
            this.dropLoot = new ArrayList<>();
        }

        public List<ItemStack> getAllDrops(LootContextParameterSet lootContext, ReloadableRegistries.Lookup serverRegistryLookup) {
            ArrayList<ItemStack> stacks = new ArrayList<>(dropStacks.size());
            for (ItemStack stack : dropStacks) {
                stacks.add(stack.copy());
            }
            for (var key : dropLoot) {
                stacks.addAll(serverRegistryLookup.getLootTable(key).generateLoot(lootContext));
            }
            return stacks;
        }

        public Boolean isEmpty() {
            return output == null && dropStacks.isEmpty() && dropLoot.isEmpty();
        }
    }
}
