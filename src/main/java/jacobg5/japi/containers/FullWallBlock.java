package jacobg5.japi.containers;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

public record FullWallBlock(String name, Block block, Block wall, Item item) implements BlockItemPair {

    public static FullWallBlock create(String name, Block block, Block wall, Item.Settings settings) {
        return new FullWallBlock(name, block, wall, new BlockItem(block, settings));
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
        return List.of(block, wall).iterator();
    }
}
