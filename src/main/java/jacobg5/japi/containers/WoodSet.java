package jacobg5.japi.containers;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

public class WoodSet extends BlockGroup {
    public final WoodType woodType;
    public final BlockSetType blockSetType;

    @Nullable public FullBlock plank;
    @Nullable public FullBlock stair;
    @Nullable public FullBlock slab;
    @Nullable public FullBlock log;
    @Nullable public FullBlock wood;
    @Nullable public FullBlock stripped_log;
    @Nullable public FullBlock stripped_wood;
    @Nullable public FullBlock fence;
    @Nullable public FullBlock fenceGate;
    @Nullable public FullBlock door;
    @Nullable public FullBlock trapdoor;
    @Nullable public FullBlock plate;
    @Nullable public FullBlock button;
    @Nullable public FullWallBlock sign;
    @Nullable public FullWallBlock hangingSign;

    public final TagKey<Item> logItems;
    public final TagKey<Block> logBlocks;

    public WoodSet(Identifier identifier, WoodType woodType, BlockSetType blockSetType,
                   @Nullable FullBlock plank, @Nullable FullBlock stair, @Nullable FullBlock slab, @Nullable FullBlock log,
                   @Nullable FullBlock wood, @Nullable FullBlock stripped_log, @Nullable FullBlock stripped_wood,
                   @Nullable FullBlock fence, @Nullable FullBlock fenceGate, @Nullable FullBlock door,
                   @Nullable FullBlock trapdoor, @Nullable FullBlock plate, @Nullable FullBlock button,
                   @Nullable FullWallBlock sign, @Nullable FullWallBlock hangingSign) {
        super(identifier);
        this.woodType = woodType;
        this.blockSetType = blockSetType;

        this.plank = plank;
        if (plank != null) Set.put("plank", plank);
        this.stair = stair;
        if (stair != null) Set.put("stair", stair);
        this.slab = slab;
        if (slab != null) Set.put("slab", slab);
        this.log = log;
        if (log != null) Set.put("log", log);
        this.wood = wood;
        if (wood != null) Set.put("wood", wood);
        this.stripped_log = stripped_log;
        if (stripped_log != null) Set.put("stripped_log", stripped_log);
        this.stripped_wood = stripped_wood;
        if (stripped_wood != null) Set.put("stripped_wood", stripped_wood);
        this.fence = fence;
        if (fence != null) Set.put("fence", fence);
        this.fenceGate = fenceGate;
        if (fenceGate != null) Set.put("fenceGate", fenceGate);
        this.door = door;
        if (door != null) Set.put("door", door);
        this.trapdoor = trapdoor;
        if (trapdoor != null) Set.put("trapdoor", trapdoor);
        this.plate = plate;
        if (plate != null) Set.put("plate", plate);
        this.button = button;
        if (button != null) Set.put("button", button);
        this.sign = sign;
        if (sign != null) Set.put("sign", sign);
        this.hangingSign = hangingSign;
        if (hangingSign != null) Set.put("hangingSign", hangingSign);

        Identifier logTag = Identifier.of(identifier.getNamespace(), identifier.getNamespace() + "_logs");
        logItems = TagKey.of(RegistryKeys.ITEM, logTag);
        logBlocks = TagKey.of(RegistryKeys.BLOCK, logTag);
    }

    public static WoodSet create(Identifier id, WoodType woodType, BlockSetType blockSetType, AbstractBlock.Settings blockSettings, Item.Settings itemSettings, String logName, String woodName) {
        String name = id.getPath();
        FullBlock planks = FullBlock.create(name + "_planks", new Block(blockSettings), itemSettings);
        return new WoodSet(id, woodType, blockSetType, planks,
            FullBlock.create(name + "_stairs", new StairsBlock(planks.block().getDefaultState(), blockSettings), itemSettings), FullBlock.create(name + "_slab", new SlabBlock(blockSettings), itemSettings),
            FullBlock.create(name + "_" + logName, new PillarBlock(blockSettings), itemSettings), FullBlock.create(name + "_" + woodName, new PillarBlock(blockSettings), itemSettings),
            FullBlock.create(name + "_stripped_" + logName, new PillarBlock(blockSettings), itemSettings), FullBlock.create(name + "_stripped_" + woodName, new PillarBlock(blockSettings), itemSettings),
            FullBlock.create(name + "_fence", new FenceBlock(blockSettings), itemSettings), FullBlock.create(name + "_fence_gate", new FenceGateBlock(woodType, blockSettings), itemSettings),
            FullBlock.create(name + "_door", new DoorBlock(blockSetType, blockSettings), itemSettings), FullBlock.create(name + "_trapdoor", new TrapdoorBlock(blockSetType, blockSettings), itemSettings),
            FullBlock.create(name + "_pressure_plate", new PressurePlateBlock(blockSetType, blockSettings), itemSettings), FullBlock.create(name + "_button", new ButtonBlock(blockSetType, 30, blockSettings), itemSettings),
            null, null);
    }

    public static WoodSet create(Identifier id, WoodType woodType, BlockSetType blockSetType, AbstractBlock.Settings blockSettings, Item.Settings itemSettings) {
        return create(id, woodType, blockSetType, blockSettings, itemSettings, "log", "wood");
    }

    @Override
    public FullBlock baseBlock() {
        return plank;
    }
    @Override
    public int getSize() { return 14; }

    @Override
    public Collection<BlockItemPair> complete(String name, AbstractBlock.Settings blockSettings, Item.Settings itemSettings) {
        ArrayList<BlockItemPair> list = new ArrayList<>();

        if (plank == null) list.add(this.plank = add("plank", name + "_planks", new Block(blockSettings), itemSettings));
        if (stair == null) list.add(this.stair = add("stair", name + "_stairs", new StairsBlock(plank.block().getDefaultState(), blockSettings), itemSettings));
        if (slab == null) list.add(this.slab = add("slab", name + "_slab", new SlabBlock(blockSettings), itemSettings));
        if (log == null) list.add(this.log = add("log", name + "_log", new PillarBlock(blockSettings), itemSettings));
        if (wood == null) list.add(this.wood = add("wood", name + "_wood", new PillarBlock(blockSettings), itemSettings));
        if (stripped_log == null) list.add(this.stripped_log = add("stripped_log", name + "_stripped_log", new PillarBlock(blockSettings), itemSettings));
        if (stripped_wood == null) list.add(this.stripped_wood = add("stripped_wood", name + "_stripped_wood", new PillarBlock(blockSettings), itemSettings));
        if (fence == null) list.add(this.fence = add("fence", name + "_fence", new FenceBlock(blockSettings), itemSettings));
        if (fenceGate == null) list.add(this.fenceGate = add("fence_gate", name + "_fence_gate", new FenceGateBlock(woodType, blockSettings), itemSettings));
        if (door == null) list.add(this.door = add("door", name + "_door", new DoorBlock(blockSetType, blockSettings), itemSettings));
        if (trapdoor == null) list.add(this.trapdoor = add("trapdoor", name + "_trapdoor", new TrapdoorBlock(blockSetType, blockSettings), itemSettings));
        if (plate == null) list.add(this.plate = add("pressure_plate", name + "_pressure_plate", new PressurePlateBlock(blockSetType, blockSettings), itemSettings));
        if (button == null) list.add(this.button = add("button", name + "_button", new ButtonBlock(blockSetType, 30, blockSettings), itemSettings));

        return list;
    }

    @Override
    public void makeFlammable(int burn, int spread) {
        FlammableBlockRegistry registry = FlammableBlockRegistry.getDefaultInstance();

        if (plank != null) registry.add(plank.block(), burn, spread);
        if (stair != null) registry.add(stair.block(), burn, spread);
        if (slab != null) registry.add(slab.block(), burn, spread);
        if (fence != null) registry.add(fence.block(), burn, spread);
        if (fenceGate != null) registry.add(fenceGate.block(), burn, spread);

        int qs = spread / 4;

        if (log != null) registry.add(log.block(), burn, qs);
        if (wood != null) registry.add(wood.block(), burn, qs);
        if (stripped_log != null) registry.add(stripped_log.block(), burn, qs);
        if (stripped_wood != null) registry.add(stripped_wood.block(), burn, qs);
    }

    @Override
    public void autoGroup(RegistryKey<ItemGroup> group, boolean after, ItemConvertible... positions) {
        if (positions.length > 0) {
            Stream<ItemConvertible> items = Stream.of(log, wood, stripped_log, stripped_wood, plank, stair, slab, fence, fenceGate, door, trapdoor, plate, button);
            if (items.findAny().isPresent()) {
                group(group, after, positions[0], items);
            }
        }
        if (positions.length > 1) {
            Stream<ItemConvertible> signs = Stream.of(sign, hangingSign);
            if (signs.findAny().isPresent()) {
                group(ItemGroups.FUNCTIONAL, after, positions[1], signs);
            }
        }
    }
}
