package jacobg5.japi.item;

import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public interface ReplaceCountText {
    @Nullable
    String getCountText(ItemStack stack);
}
