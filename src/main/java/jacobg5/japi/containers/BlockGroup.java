package jacobg5.japi.containers;

import jacobg5.japi.JUtils;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Stream;

public abstract class BlockGroup implements BlockItemPair {
    public final Map<String, BlockItemPair> Set = new HashMap<>();
    public final Identifier identifier;

    public BlockGroup(Identifier identifier) {
        this.identifier = identifier;
    }

    public abstract FullBlock baseBlock();

    @Override
    public Item asItem() {
        return baseBlock().item();
    }

    @Override
    public Block asBlock() {
        return baseBlock().block();
    }

    public BlockItemPair get(String id) {
        return Set.get(id);
    }

    public Collection<BlockItemPair> complete(String name, AbstractBlock.Settings blockSettings, Item.Settings itemSettings) {
        return List.of();
    }

    public <T extends  BlockItemPair> T add(String id, T block) {
        Set.put(id, block);
        return block;
    }

    public FullBlock add(String id, Block block, Item.Settings settings) {
        FullBlock full = FullBlock.create(id, block, settings);
        Set.put(id, full);
        return full;
    }

    public FullBlock add(String id, String blockId, Block block, Item.Settings settings) {
        FullBlock full = FullBlock.create(blockId, block, settings);
        Set.put(id, full);
        return full;
    }

    @Override
    public @NotNull Iterator<Block> getBlocks() {
        ArrayList<Block> blocks = new ArrayList<>(getSize());
        Set.values().forEach(full -> full.getBlocks().forEachRemaining(blocks::add));
        return blocks.iterator();
    }

    @Override
    public @NotNull Iterator<Item> getItems() {
        ArrayList<Item> items = new ArrayList<>(getSize());
        Set.values().forEach(full -> full.getItems().forEachRemaining(items::add));
        return items.iterator();
    }

    public abstract int getSize();

    public Map.Entry<Identifier, BlockGroup> entry() {
        return Map.entry(identifier, this);
    }

    public void makeFlammable() {
        makeFlammable(5, 20);
    }

    public void makeFlammable(int burn, int spread) {
        FlammableBlockRegistry registry = FlammableBlockRegistry.getDefaultInstance();
        Set.values().forEach(full -> full.getBlocks().forEachRemaining(block -> registry.add(block, burn, spread)));
    }

    public abstract void autoGroup(RegistryKey<ItemGroup> group, boolean after, ItemConvertible... positions);

    public void autoGroup(RegistryKey<ItemGroup> group, ItemConvertible... positions) {
        autoGroup(group, true, positions);
    }

    public static void group(RegistryKey<ItemGroup> group, boolean after, ItemConvertible pos, Stream<ItemConvertible> items) {
        ItemGroupEvents.modifyEntriesEvent(group).register(after ?
                content -> content.addAfter(pos, JUtils.convertToDefaultStacks(items)) :
                content -> content.addBefore(pos, JUtils.convertToDefaultStacks(items)));
    }
}
