package jacobg5.japi.compat.apoli.core.conditions;

import io.github.apace100.apoli.condition.ConditionConfiguration;
import io.github.apace100.apoli.condition.ItemCondition;
import io.github.apace100.apoli.condition.context.EntityConditionContext;
import io.github.apace100.apoli.condition.type.EntityConditionType;
import io.github.apace100.apoli.data.TypedDataObjectFactory;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import jacobg5.japi.JLoaderData;
import jacobg5.japi.JUtils;
import jacobg5.japi.compat.apoli.core.JApoliCore;
import jacobg5.japi.compat.trinkets.JTrinkets;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public class EquippedItemEntityConditionType extends EntityConditionType {

    public static final TypedDataObjectFactory<EquippedItemEntityConditionType> DATA_FACTORY = TypedDataObjectFactory.simple(
            new SerializableData()
                    .add("item_condition", ItemCondition.DATA_TYPE)
                    .add("target_slot", SerializableDataTypes.STRING, "any"),
            data -> new EquippedItemEntityConditionType(
                    data.get("item_condition"),
                    data.get("target_slot")
            ),
            (conditionType, serializableData) -> serializableData.instance()
                    .set("item_condition", conditionType.itemCondition)
                    .set("target_slot", conditionType.targetSlot)
    );

    private final ItemCondition itemCondition;
    private final String targetSlot;

    public EquippedItemEntityConditionType(ItemCondition itemCondition, String targetSlot) {
        this.itemCondition = itemCondition;
        this.targetSlot = targetSlot;
    }

    @Override
    public boolean test(EntityConditionContext context) {
        if (!(context.entity() instanceof LivingEntity livingEntity)) return false;

        Predicate<ItemStack> predicate = stack -> itemCondition.test(livingEntity.getWorld(), stack);

        if (targetSlot.equalsIgnoreCase("any")) {
            return JUtils.getEquippedOrHeld(livingEntity, predicate).isPresent();
        }
        else if (targetSlot.equalsIgnoreCase("held")) {
            return JUtils.getHeld(livingEntity, predicate).isPresent();
        }
        else if (targetSlot.equalsIgnoreCase("equipped")) {
            return JUtils.getEquipped(livingEntity, predicate).isPresent();
        }

        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (slot.toString().equalsIgnoreCase(targetSlot)) {
                return predicate.test(livingEntity.getEquippedStack(slot));
            }
        }

        if (JLoaderData.TRINKETS) {
            JTrinkets.getEquipped(livingEntity, predicate, targetSlot);
        }

        return false;
    }

    @Override
    public @NotNull ConditionConfiguration<?> getConfig() {
        return JApoliCore.EQUIPPED_ITEM_CONDITION;
    }

}