package jacobg5.japi.compat.apoli.core;

import io.github.apace100.apoli.Apoli;
import io.github.apace100.apoli.action.ActionConfiguration;
import io.github.apace100.apoli.action.type.EntityActionTypes;
import io.github.apace100.apoli.action.type.entity.HealEntityActionType;
import io.github.apace100.apoli.condition.ConditionConfiguration;
import io.github.apace100.apoli.condition.type.EntityConditionTypes;
import io.github.apace100.apoli.power.PowerConfiguration;
import io.github.apace100.apoli.power.type.PowerTypes;
import io.github.apace100.apoli.power.type.PreventBlockUsePowerType;
import jacobg5.japi.JAPI;
import jacobg5.japi.JMod;
import jacobg5.japi.JModModule;
import jacobg5.japi.compat.apoli.core.actions.JumpEntityActionType;
import jacobg5.japi.compat.apoli.core.conditions.EnchantmentEntityConditionType;
import jacobg5.japi.compat.apoli.core.conditions.EquippedItemEntityConditionType;
import jacobg5.japi.compat.apoli.core.powers.EnemyOfPowerType;
import jacobg5.japi.compat.apoli.core.powers.IgnoredByPowerType;
import jacobg5.japi.compat.apoli.core.powers.PiglinAcceptedPowerType;
import jacobg5.japi.compat.apoli.core.powers.PreventBlockBreakPowerType;

public class JApoliCore extends JModModule {

    //region Entity Powers
    public static final PowerConfiguration<PiglinAcceptedPowerType> PIGLIN_ACCEPTED_POWER = PowerTypes.register(PowerConfiguration.conditionedSimple(JAPI.identifier("piglin_accepted"), PiglinAcceptedPowerType::new));
    public static final PowerConfiguration<IgnoredByPowerType> IGNORED_BY_POWER = PowerTypes.register(PowerConfiguration.of(JAPI.identifier("ignored_by"), IgnoredByPowerType.DATA_FACTORY));
    public static final PowerConfiguration<EnemyOfPowerType> ENEMY_OF_POWER = PowerTypes.register(PowerConfiguration.of(JAPI.identifier("enemy_of"), EnemyOfPowerType.DATA_FACTORY));
    public static final PowerConfiguration<PreventBlockBreakPowerType> PREVENT_BLOCK_BREAK_POWER = PowerTypes.register(PowerConfiguration.of(JAPI.identifier("prevent_block_break"), PreventBlockBreakPowerType.DATA_FACTORY));
    //endregion

    //region Entity Conditions
    public static final ConditionConfiguration<EquippedItemEntityConditionType> EQUIPPED_ITEM_CONDITION = EntityConditionTypes.register(ConditionConfiguration.of(JAPI.identifier("equipped_item"), EquippedItemEntityConditionType.DATA_FACTORY));
    public static final ConditionConfiguration<EnchantmentEntityConditionType> ENCHANTMENT_CONDITION = EntityConditionTypes.register(ConditionConfiguration.of(JAPI.identifier("enchantment"), EnchantmentEntityConditionType.DATA_FACTORY));
    //endregion

    //region Entity Actions
    public static final ActionConfiguration<JumpEntityActionType> JUMP_ACTION = EntityActionTypes.register(ActionConfiguration.simple(JAPI.identifier("jump"), JumpEntityActionType::new));
    //endregion

    @Override
    public void init(JMod mod) {}
}
