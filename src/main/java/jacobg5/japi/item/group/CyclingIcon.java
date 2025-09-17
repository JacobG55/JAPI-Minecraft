package jacobg5.japi.item.group;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class CyclingIcon extends JItemGroup.Icon {
    public final Optional<List<ItemStack>> customPool;
    public int index = 0;
    public float cycleRate;
    public float cycleTime = 0;

    public CyclingIcon() {
        this(4f, JItemGroup.Icon::always);
    }
    public CyclingIcon(float cycleRate) {
        this(cycleRate, JItemGroup.Icon::always);
    }
    public CyclingIcon(float cycleRate, Predicate<ItemGroup> displayCondition) {
        super(displayCondition);
        this.cycleRate = cycleRate;
        customPool = Optional.empty();
    }
    public CyclingIcon(ItemStack... icons) {
        this(4f, JItemGroup.Icon::always, icons);
    }
    public CyclingIcon(float cycleRate, ItemStack... icons) {
        this(cycleRate, JItemGroup.Icon::always, icons);
    }
    public CyclingIcon(float cycleRate, Predicate<ItemGroup> displayCondition, ItemStack... icons) {
        super(displayCondition);
        this.cycleRate = cycleRate;
        List<ItemStack> list = List.of(icons);
        customPool = Optional.of(list);
    }

    @Override
    public ItemStack getIcon() {
        List<ItemStack> pool = getPool();
        if (pool.isEmpty()) return jGroup.JAPI$getIcon();
        return getPool().get(index);
    }

    @Override
    public void displayTick(float delta) {
        cycleTime += delta;
        if (cycleTime > cycleRate) {
            cycleTime -= cycleRate;
            List<ItemStack> pool = getPool();
            index++;
            if (index >= pool.size()) index = 0;
        }
    }

    public List<ItemStack> getPool() {
        return customPool.orElse(group.getDisplayStacks().stream().toList());
    }
}
