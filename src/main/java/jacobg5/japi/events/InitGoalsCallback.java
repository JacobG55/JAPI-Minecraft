package jacobg5.japi.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.mob.MobEntity;

public interface InitGoalsCallback {
    Event<InitGoalsCallback> EVENT = EventFactory.createArrayBacked(InitGoalsCallback.class,
        (listeners) -> ((entity, goalSelector, targetSelector) -> {
            for (InitGoalsCallback listener : listeners) {
                listener.modify(entity, goalSelector, targetSelector);
            }
        }));

    void modify(MobEntity mob, GoalSelector goalSelector, GoalSelector targetSelector);
}
