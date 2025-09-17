package jacobg5.japi.mixin;

import jacobg5.japi.JKeys;
import jacobg5.japi.JUtils;
import jacobg5.japi.item.JSmithingTemplate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

@Mixin(SmithingTemplateItem.class)
public abstract class SmithingTemplateItemMixin {

    @Inject(method = "getEmptyBaseSlotTextures", at = @At("HEAD"), cancellable = true )
    private void getEmptyBaseSlotTextures(CallbackInfoReturnable<List<Identifier>> info) {
        if (((SmithingTemplateItem)(Object)this).getDefaultStack().isIn(JKeys.UPGRADE_TEMPLATE)) {
            info.setReturnValue(JUtils.mixLists(info.getReturnValue(), JSmithingTemplate.ADDITIONAL_ARMOR_TEXTURES, JSmithingTemplate.ADDITIONAL_TOOL_TEXTURES));
        }
    }

    @Inject(method = "getEmptyAdditionsSlotTextures", at = @At("HEAD"), cancellable = true )
    private void getEmptyAdditionsSlotTextures(CallbackInfoReturnable<List<Identifier>> info) {
        if (((SmithingTemplateItem)(Object)this).getDefaultStack().isIn(ItemTags.TRIM_TEMPLATES)) {
            info.setReturnValue(JUtils.mixLists(info.getReturnValue(), JSmithingTemplate.ADDITIONAL_MATERIAL_TEXTURES));
        }
    }
}