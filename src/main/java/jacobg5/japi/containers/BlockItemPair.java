package jacobg5.japi.containers;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public interface BlockItemPair extends ItemConvertible {
    Block asBlock();
    @NotNull Iterator<Item> getItems();
    @NotNull Iterator<Block> getBlocks();
}
