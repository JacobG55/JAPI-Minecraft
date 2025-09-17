package jacobg5.japi.mixin.client;

import jacobg5.japi.item.group.JItemGroup;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(CreativeInventoryScreen.class)
public class CreativeInventoryScreenMixin {
    @Inject(method = "drawBackground", at = @At("HEAD"), cancellable = false)
    private void drawBackground(DrawContext context, float delta, int mouseX, int mouseY, CallbackInfo info) {
        for (ItemGroup itemGroup : ItemGroups.getGroupsToDisplay()) {
            Optional<JItemGroup.Icon> customIcon = ((JItemGroup)itemGroup).JAPI$getCustomIcon();
            customIcon.ifPresent(icon -> icon.displayTick(delta));
        }
    }
}
