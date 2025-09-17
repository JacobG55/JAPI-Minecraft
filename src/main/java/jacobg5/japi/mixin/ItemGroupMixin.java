package jacobg5.japi.mixin;

import jacobg5.japi.item.group.JItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;
import java.util.function.Supplier;

@Mixin(ItemGroup.class)
public class ItemGroupMixin implements JItemGroup {
    @Shadow @Nullable
    private ItemStack icon;
    @Shadow @Final
    private Supplier<ItemStack> iconSupplier;
    @Unique
    private Optional<JItemGroup.Icon> customIcon = Optional.empty();
    @Unique
    private boolean isDebugTab = false;

    @Override
    public void JAPI$setCustomIcon(@Nullable Icon icon) {
        if (icon != null) {
            icon.group = (ItemGroup)(Object)this;
            icon.jGroup = this;
        }
        customIcon = Optional.ofNullable(icon);
    }

    @Override
    public Optional<Icon> JAPI$getCustomIcon() {
        return customIcon;
    }

    @Override
    public void JAPI$setIcon(ItemStack stack) {
        icon = stack;
    }
    @Override
    public ItemStack JAPI$getIcon() {
        if (icon == null) icon = iconSupplier.get();
        return icon;
    }

    @Override
    public void JAPI$markDebugTab() {
        isDebugTab = true;
    }

    @Override
    public Boolean JAPI$isDebugTab() {
        return isDebugTab;
    }

    @Inject(method = "shouldDisplay", at = @At("RETURN"), cancellable = true )
    public void shouldDisplay(CallbackInfoReturnable<Boolean> info) {
        if (info.getReturnValue() && isDebugTab) {
            info.setReturnValue(true);
        }
        else if (customIcon.isPresent()) {
            info.setReturnValue(customIcon.get().shouldDisplay());
        }
    }

    @Inject(method = "getIcon", at = @At("HEAD"), cancellable = true )
    private void getIcon(CallbackInfoReturnable<ItemStack> info) {
        if (customIcon.isPresent()) {
            info.setReturnValue(customIcon.get().getIcon());
        }
    }
}
