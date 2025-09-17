package jacobg5.japi.item.group;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import java.util.Optional;
import java.util.function.Predicate;

public interface JItemGroup {
    void JAPI$setCustomIcon(Icon icon);
    Optional<Icon> JAPI$getCustomIcon();

    void JAPI$setIcon(ItemStack stack);
    ItemStack JAPI$getIcon();

    void JAPI$markDebugTab();
    Boolean JAPI$isDebugTab();

    abstract class Icon {
        private final Predicate<ItemGroup> displayCondition;
        public ItemGroup group;
        public JItemGroup jGroup;

        public Icon(Predicate<ItemGroup> displayCondition) {
            this.displayCondition = displayCondition;
        }

        public abstract ItemStack getIcon();
        public void displayTick(float delta) {}
        public Boolean shouldDisplay() { return displayCondition.test(group); }

        public static Boolean always(ItemGroup group) { return true; }
    }
}
