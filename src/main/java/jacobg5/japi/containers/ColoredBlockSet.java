package jacobg5.japi.containers;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

public class ColoredBlockSet<T extends Block> extends BlockGroup {
    @Nullable public FullBlock white = null;
    @Nullable public FullBlock light_gray = null;
    @Nullable public FullBlock gray = null;
    @Nullable public FullBlock black = null;
    @Nullable public FullBlock brown = null;
    @Nullable public FullBlock red = null;
    @Nullable public FullBlock orange = null;
    @Nullable public FullBlock yellow = null;
    @Nullable public FullBlock lime = null;
    @Nullable public FullBlock green = null;
    @Nullable public FullBlock cyan = null;
    @Nullable public FullBlock light_blue = null;
    @Nullable public FullBlock blue = null;
    @Nullable public FullBlock purple = null;
    @Nullable public FullBlock magenta = null;
    @Nullable public FullBlock pink = null;

    public final Function<AbstractBlock.Settings, T> defaultSupplier;

    public ColoredBlockSet(Identifier identifier, Function<AbstractBlock.Settings, T> supplier,
                           @Nullable FullBlock white, @Nullable FullBlock light_gray, @Nullable FullBlock gray, @Nullable FullBlock black,
                           @Nullable FullBlock brown, @Nullable FullBlock red, @Nullable FullBlock orange, @Nullable FullBlock yellow,
                           @Nullable FullBlock lime, @Nullable FullBlock green, @Nullable FullBlock cyan, @Nullable FullBlock light_blue,
                           @Nullable FullBlock blue, @Nullable FullBlock purple, @Nullable FullBlock magenta, @Nullable FullBlock pink) {
        super(identifier);
        defaultSupplier = supplier;
        if (white != null) this.white = add("white", white);
        if (light_gray != null) this.light_gray = add("light_gray", light_gray);
        if (gray != null) this.gray = add("gray", gray);
        if (black != null) this.black = add("black", black);
        if (brown != null) this.brown = add("brown", brown);
        if (red != null) this.red = add("red", red);
        if (orange != null) this.orange = add("orange", orange);
        if (yellow != null) this.yellow = add("yellow", yellow);
        if (lime != null) this.lime = add("lime", lime);
        if (green != null) this.green = add("green", green);
        if (cyan != null) this.cyan = add("cyan", cyan);
        if (light_blue != null) this.light_blue = add("light_blue", light_blue);
        if (blue != null) this.blue = add("blue", blue);
        if (purple != null) this.purple = add("purple", purple);
        if (magenta != null) this.magenta = add("magenta", magenta);
        if (pink != null) this.pink = add("pink", pink);
    }

    public ColoredBlockSet(Identifier identifier, Function<AbstractBlock.Settings, T> supplier, List<FullBlock> blockList) {
        super(identifier);
        defaultSupplier = supplier;
        int max = Math.min(blockList.size(), DyeColor.values().length);
        for (int i = 0; i < max; i++) {{
            DyeColor color = DyeColor.values()[i];
            set(color, blockList.get(i));
        }}
    }

    public static <T extends Block> ColoredBlockSet<T> create(Identifier identifier, Function<AbstractBlock.Settings, T> supplier, AbstractBlock.Settings blockSettings, Item.Settings settings) {
        String name = identifier.getPath();
        return new ColoredBlockSet<>(identifier, supplier, Arrays.stream(DyeColor.values())
                .map(color -> FullBlock.create(color.name() + "_" + name, supplier.apply(blockSettings), settings)).toList());
    }

    @Override
    public FullBlock baseBlock() {
        return white;
    }

    @Override
    public int getSize() { return 16; }

    public @Nullable FullBlock get(DyeColor color) {
        return switch (color) {
            case LIGHT_GRAY -> light_gray;
            case GRAY -> gray;
            case BLACK -> black;
            case BROWN -> brown;
            case RED -> red;
            case ORANGE -> orange;
            case YELLOW -> yellow;
            case LIME -> lime;
            case GREEN -> green;
            case CYAN -> cyan;
            case LIGHT_BLUE -> light_blue;
            case BLUE -> blue;
            case PURPLE -> purple;
            case MAGENTA -> magenta;
            case PINK -> pink;
            default -> white;
        };
    }

    public FullBlock set(DyeColor color, FullBlock value) {
        String colorId = color.name();

        if (Set.containsKey(colorId)) Set.replace(colorId, value);
        else Set.put(colorId, value);

        return switch (color) {
            case LIGHT_GRAY -> light_gray = value;
            case GRAY -> gray = value;
            case BLACK -> black = value;
            case BROWN -> brown = value;
            case RED -> red = value;
            case ORANGE -> orange = value;
            case YELLOW -> yellow = value;
            case LIME -> lime = value;
            case GREEN -> green = value;
            case CYAN -> cyan = value;
            case LIGHT_BLUE -> light_blue = value;
            case BLUE -> blue = value;
            case PURPLE -> purple = value;
            case MAGENTA -> magenta = value;
            case PINK -> pink = value;
            default -> white = value;
        };
    }

    @Override
    public Collection<BlockItemPair> complete(String name, AbstractBlock.Settings blockSettings, Item.Settings itemSettings) {
        ArrayList<BlockItemPair> list = new ArrayList<>();
        for (DyeColor color : DyeColor.values()) {
            String colorId = color.name();
            if (!Set.containsKey(colorId)) list.add(set(color, FullBlock.create(colorId + "_" + name, defaultSupplier.apply(blockSettings), itemSettings)));
        }
        return list;
    }

    @Override
    public void autoGroup(RegistryKey<ItemGroup> group, boolean after, ItemConvertible... positions) {
        ItemConvertible pos;
        Stream<ItemConvertible> items;

        if (positions.length == 0) {
            pos = white;
            items = Stream.of(light_gray, gray, black, brown, red, orange, yellow, lime, green, cyan, light_blue, blue, purple, magenta, pink);
        } else {
            pos = positions[0];
            items = Stream.of(white, light_gray, gray, black, brown, red, orange, yellow, lime, green, cyan, light_blue, blue, purple, magenta, pink);
        }

        group(group, after, pos, items);
    }
}
