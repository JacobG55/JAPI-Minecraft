package jacobg5.japi;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;

public class JSettings {
    /**
     * {@return creates an empty item settings}
     */
    public static Item.Settings item() {
        return new Item.Settings();
    }

    /**
     * {@return creates an item settings with a max count}
     *
     * @param size max stack count
     */
    public static Item.Settings stack(int size) {
        return new Item.Settings().maxCount(size);
    }

    /**
     * {@return creates food item settings}
     *
     * @param component food component for the item
     */
    public static Item.Settings food(FoodComponent component) {
        return new Item.Settings().food(component);
    }

    /**
     * {@return clones a block's settings}
     *
     * @param block block to copy settings from
     */
    public static AbstractBlock.Settings copy(Block block) {
        return AbstractBlock.Settings.copy(block);
    }

    /**
     * {@return creates a standard leaf block settings}
     *
     * @param soundGroup sounds for the block
     */
    public static AbstractBlock.Settings leafBlock(BlockSoundGroup soundGroup) {
        return AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).sounds(soundGroup);
    }
}
