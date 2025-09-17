package jacobg5.japi.mixin.client;

import jacobg5.japi.item.ReplaceCountText;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DrawContext.class)
public class DrawContextMixin {

    @Inject(method = "drawItemInSlot(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/item/ItemStack;IILjava/lang/String;)V", at = @At("HEAD"), cancellable = true )
    private void drawItemInSlot(TextRenderer textRenderer, ItemStack stack, int x, int y, String countOverride, CallbackInfo info) {
        DrawContext drawContext = (DrawContext)(Object)this;
        if (stack.getItem() instanceof ReplaceCountText rct) {
            String countText = rct.getCountText(stack);
            if (countText != null) {
                drawContext.drawItemInSlot(textRenderer, stack, x, y, countText);
                info.cancel();
                return;
            }
        }
    }
}