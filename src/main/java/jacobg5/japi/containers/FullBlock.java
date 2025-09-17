package jacobg5.japi.containers;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.PlaceableOnWaterItem;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

public record FullBlock(String name, Block block, Item item) implements BlockItemPair {

    public static FullBlock create(String name, Block block, Item.Settings settings) {
        return new FullBlock(name, block, new BlockItem(block, settings));
    }

    public static FullBlock water(String name, Block block, Item.Settings settings) {
        return new FullBlock(name, block, new PlaceableOnWaterItem(block, settings));
    }

    @Override
    public Item asItem() {
        return item;
    }

    @Override
    public Block asBlock() {
        return block;
    }

    @Override
    public @NotNull Iterator<Item> getItems() {
        return List.of(item).iterator();
    }

    @Override
    public @NotNull Iterator<Block> getBlocks() {
        return List.of(block).iterator();
    }
}
