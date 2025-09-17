package jacobg5.japi.containers;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.block.WallBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Stream;

public class BlockSet extends BlockGroup {
    @Nullable public FullBlock block;
    @Nullable public FullBlock stair;
    @Nullable public FullBlock slab;
    @Nullable public FullBlock wall;

    public BlockSet(Identifier identifier, @Nullable FullBlock block, @Nullable FullBlock stair, @Nullable FullBlock slab) {
        this(identifier, block, stair, slab, null);
    }

    public BlockSet(Identifier identifier, @Nullable FullBlock block, @Nullable FullBlock stair, @Nullable FullBlock slab, @Nullable FullBlock wall) {
        super(identifier);
        this.block = block;
        if (block != null) Set.put("block", block);
        this.stair = stair;
        if (stair != null) Set.put("stair", stair);
        this.slab = slab;
        if (slab != null) Set.put("slab", slab);
        this.wall = wall;
        if (wall != null) Set.put("wall", wall);
    }

    public BlockSet(Identifier identifier, String base, AbstractBlock.Settings blockSettings, Item.Settings itemSettings, Boolean hasWall)
    {
        super(identifier);
        String name = identifier.getPath();
        FullBlock baseBlock = FullBlock.create(base, new Block(blockSettings), itemSettings);
        this.block = add("block", baseBlock);
        this.stair = add("stair", name + "_stairs", new StairsBlock(baseBlock.block().getDefaultState(), blockSettings), itemSettings);
        this.slab = add("slab", name + "_slab", new SlabBlock(blockSettings), itemSettings);

        if (hasWall) this.wall = add("wall", name + "_wall", new WallBlock(blockSettings), itemSettings);
        else this.wall = null;
    }

    public static BlockSet of(Identifier identifier, String base, AbstractBlock.Settings blockSettings, Item.Settings itemSettings) {
        return new BlockSet(identifier, base, blockSettings, itemSettings, false);
    }

    public static BlockSet full(Identifier identifier, String name, AbstractBlock.Settings blockSettings, Item.Settings itemSettings) {
        return new BlockSet(identifier, name, blockSettings, itemSettings, true);
    }

    @Override
    public FullBlock baseBlock() {
        return block;
    }

    @Override
    public int getSize() { return 4; }

    @Override
    public Collection<BlockItemPair> complete(String name, AbstractBlock.Settings blockSettings, Item.Settings itemSettings) {
        ArrayList<BlockItemPair> list = new ArrayList<>();

        if (block == null) list.add(this.block = add("block", name, new Block(blockSettings), itemSettings));
        if (stair == null) list.add(this.stair = add("stair", name + "_stairs", new StairsBlock(block.block().getDefaultState(), blockSettings), itemSettings));
        if (slab == null) list.add(this.slab = add("slab", name + "_slab", new SlabBlock(blockSettings), itemSettings));
        if (wall == null) list.add(this.wall = add("wall", name + "_wall", new WallBlock(blockSettings), itemSettings));

        return list;
    }

    @Override
    public void autoGroup(RegistryKey<ItemGroup> group, boolean after, ItemConvertible... positions) {
        ItemConvertible pos;
        Stream<ItemConvertible> items;

        if (positions.length == 0) {
            pos = block;
            items = Stream.of(stair, slab, wall);
        } else {
            pos = positions[0];
            items = Stream.of(block, stair, slab, wall);
        }

        group(group, after, pos, items);
    }
}
