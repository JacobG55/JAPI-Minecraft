package jacobg5.japi;

import jacobg5.japi.item.JItem;
import jacobg5.japi.item.group.CyclingIcon;
import jacobg5.japi.item.group.JItemGroup;
import jacobg5.japi.containers.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.*;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.component.ComponentType;
import net.minecraft.datafixer.TypeReferences;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.*;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Root of a JAPI Mod.
 * Make your {@link ModInitializer} class implement from this instead.
 */
public abstract class JMod implements ModInitializer {
    public final Map<Identifier, Item> ModItems = new HashMap<>();
    public final Map<Identifier, Block> ModBlocks = new HashMap<>();
    public final Map<Identifier, EntityType<?>> ModEntities = new HashMap<>();

    private final Map<String, JModModule> MOD_MODULES = new HashMap<>();

    public @Nullable ItemGroup debugTab;

    public abstract String ModID();
    public abstract Logger Logger();
    public void logDebug(Consumer<Logger> log) {
        if (JAPI.Config.logDebug) log.accept(Logger());
    }

    @Override
    public void onInitialize() {
        JAPI.first();
        init();

        if (JAPI.Config.logDebug) {
            Logger().info("Loaded Modules:\n{}\nRegistered: {} Items, {} Blocks, {} Entities.", String.join(", ", MOD_MODULES.keySet()), ModItems.size(), ModBlocks.size(), ModEntities.size());
        }

        if (JAPI.Config.createModTabs && !ModItems.isEmpty()) {
            debugTab = Registry.register(Registries.ITEM_GROUP, JAPI.identifier(ModID()),
                FabricItemGroup.builder()
                    .displayName(Text.literal(ModID().toUpperCase() + " Developer Tab"))
                    .icon(JAPI.DefaultGroupIcon)
                    .entries(((displayContext, entries) -> entries.addAll(ModItems.values().stream().map(Item::getDefaultStack).toList())))
                .build()
            );
            JItemGroup jItemGroup = (JItemGroup)debugTab;
            jItemGroup.JAPI$setCustomIcon(new CyclingIcon());
            jItemGroup.JAPI$markDebugTab();
        }
    }

    public Identifier ID(String path) {
        return Identifier.of(ModID(), path);
    }

    public abstract void init();

    /**
     * Creates a module to contain parts of your mod
     *
     * @param name identifier for the module
     * @param supplier supplier for creating the module
     */
    public void addModule(String name, Supplier<JModModule> supplier) {
        if (MOD_MODULES.containsKey(name)) {
            Logger().error("Duplicate Module Names: " + name);
            return;
        }
        JModModule module = supplier.get();
        module.identifier = ID(name);

        try {
            module.init(this);
            MOD_MODULES.put(name, module);
        }
        catch (Exception e) {
            Logger().error("Caught Error Loading: {}\n{}\n\nContinuing game load but may cause problems with this mod.", module.identifier.toString(), e.getMessage());
        }
    }

    /**
     * Creates a module to contain parts of your mod
     *
     * @param name identifier for the module
     * @param supplier supplier for creating the module
     * @param dependencies string of mod ids required for the module to load
     */
    public void addModule(String name, Supplier<JModModule> supplier, String... dependencies) {
        for (String modName : dependencies) {
            if (!JLoaderData.contains(modName)) return;
        }
        addModule(name, supplier);
    }

    /**
     * Creates a module to contain parts of your mod
     *
     * @param name identifier for the module
     * @param supplier supplier for creating the module
     * @param prerequisite condition for creating the module
     */
    public void addModule(String name, Supplier<JModModule> supplier, Boolean prerequisite) {
        if (prerequisite) addModule(name, supplier);
    }

    /**
     * {@return registers the provided item under this mod's namespace}
     *
     * @param name name to use as the path for the {@link Item}'s {@link Identifier}
     * @param item {@link  net.minecraft.item.Item} to be registered
     */
    public Item registerItem(String name, Item item) {
        Identifier id = ID(name);
        ModItems.put(id, item);
        return Registry.register(Registries.ITEM, id, item);
    }

    /**
     * {@return registers the provided block under this mod's namespace}
     *
     * @param name name to use as the path for the {@link Block}'s {@link Identifier}
     * @param block {@link net.minecraft.block.Block} to be registered
     */
    public Block registerBlock(String name, Block block) {
        Identifier id = ID(name);
        ModBlocks.put(id, block);
        return Registry.register(Registries.BLOCK, id, block);
    }

    /**
     * {@return registers default attributes for an entity}
     *
     * @param name name to use as the path for the {@link EntityType}'s {@link Identifier}
     * @param width width of the entity's hitbox
     * @param height height of the entity's hitbox
     * @param factory instructions for creating an instance of the entity
     * @param spawnGroup what group the entity is a part of
     * @param attributeBuilder default attributes of the entity in the form of a builder
     */
    @SuppressWarnings("ConstantValue")
    public <T extends LivingEntity> EntityType<T> registerEntity(String name, float width, float height, EntityType.EntityFactory<T> factory,
                                                                 SpawnGroup spawnGroup, DefaultAttributeContainer.Builder attributeBuilder) {
        EntityType<T> entityType = EntityType.Builder.create(factory, spawnGroup).dimensions(width, height).build();
        FabricDefaultAttributeRegistry.register(entityType, attributeBuilder);

        Identifier id = ID(name);
        ModEntities.put(id, entityType);
        return Registry.register(Registries.ENTITY_TYPE, id, entityType);
    }

    /**
     * {@return registers default attributes for an entity}
     *
     * @param name name to use as the path for the {@link EntityType}'s {@link Identifier}
     * @param width width of the entity's hitbox
     * @param height height of the entity's hitbox
     * @param factory instructions for creating an instance of the entity
     * @param spawnGroup what group the entity is a part of
     * @param attributeBuilder default attributes of the entity in the form of a builder
     * @param consumer extra instructions for the entity builder
     */
    @SuppressWarnings("ConstantValue")
    public <T extends LivingEntity> EntityType<T> registerEntity(String name, float width, float height, EntityType.EntityFactory<T> factory,
                                                                 SpawnGroup spawnGroup, DefaultAttributeContainer.Builder attributeBuilder,
                                                                 Consumer<EntityType.Builder<T>> consumer) {
        EntityType.Builder<T> builder = EntityType.Builder.create(factory, spawnGroup).dimensions(width, height);
        consumer.accept(builder);
        EntityType<T> entityType = builder.build();
        FabricDefaultAttributeRegistry.register(entityType, attributeBuilder);

        Identifier id = ID(name);
        ModEntities.put(id, entityType);
        return Registry.register(Registries.ENTITY_TYPE, id, entityType);
    }

    /**
     * {@return registers an entry to the provided registry under this mod's namespace}
     *
     * @param registry registry to register content to
     * @param name name to use as the path for the {@link Identifier}
     * @param entry entry to be registered to the provided registry
     */
    public <V, T extends V> T register(Registry<V> registry, String name, T entry) {
        Identifier id = ID(name);
        switch (entry) {
            case Item item -> ModItems.put(id, item);
            case Block block -> ModBlocks.put(id, block);
            case EntityType<?> entityType -> ModEntities.put(id, entityType);
            default -> {}
        }
        return Registry.register(registry, id, entry);
    }

    /**
     * {@return registers the Block and Item contained in the provided object under this mod's namespace}
     *
     * @param full {@link jacobg5.japi.containers.FullBlock} or {@link FullWallBlock} to be registered
     */
    public FullBlock registerFull(FullBlock full) {
        registerBlock(full.name(), full.block());
        registerItem(full.name(), full.item());
        return full;
    }

    /**
     * {@return registers the Block and Item contained in the provided object under this mod's namespace}
     *
     * @param full {@link jacobg5.japi.containers.FullBlock} or {@link FullWallBlock} to be registered
     */
    public FullWallBlock registerFull(FullWallBlock full) {
        registerBlock(full.name(), full.block());
        registerItem(full.name(), full.item());
        registerBlock(full.name() + "_wall", full.wall());
        return full;
    }

    /**
     * {@return creates and registers a block entity type using the the factory and list of blocks}
     *
     * @param id name to use as the path for the {@link BlockEntityType}'s {@link Identifier}
     * @param factory factory for creating an instance of the block entity
     * @param blocks list of blocks valid to contain the block entity
     */
    public  <T extends BlockEntity> BlockEntityType<T> registerBlockEntityType(String id, BlockEntityType.BlockEntityFactory<? extends T> factory, @NotNull Block... blocks) {
        if (blocks.length == 0) {
            Logger().warn("Block entity type {} requires at least one valid block to be defined!", id);
        }
        BlockEntityType.Builder<T> builder = BlockEntityType.Builder.create(factory, blocks);
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, ID(id), builder.build(Util.getChoiceType(TypeReferences.BLOCK_ENTITY, id)));
    }

    /**
     * {@return registers all Blocks & Items in pre-defined block group}
     *
     * @param group block group to be registered (See: {@link BlockSet}, {@link ColoredBlockSet}, or {@link WoodSet})
     */
    public <T extends BlockGroup> T registerGroup(T group) {
        group.Set.values().forEach(this::registerPair);
        return group;
    }

    /**
     * {@return creates & registers all pissing parts of a BlockGroup}
     *
     * @param group block group to be registered (See: {@link BlockSet}, {@link ColoredBlockSet}, or {@link WoodSet})
     * @param name name used for registering missing items
     * @param blockSettings block settings used for newly created blocks
     * @param itemSettings item settings used for newly created items
     */
    public <T extends BlockGroup> T completeAndRegister(T group, String name, AbstractBlock.Settings blockSettings, Item.Settings itemSettings) {
        group.complete(name, blockSettings, itemSettings).forEach(this::registerPair);
        return group;
    }

    private void registerPair(BlockItemPair pair) {
        switch (pair) {
            case null -> Logger().warn("Cannot register null BlockItemPair.");
            case FullBlock block -> registerFull(block);
            case FullWallBlock wall -> registerFull(wall);
            case BlockGroup group -> registerGroup(group);
            default -> Logger().warn("BlockItemPair {} is unknown and not able to be registered.", pair.asItem().getName().getString());
        }
    }

    /**
     * {@return creates & registers a potted plant block for a given plant}
     *
     * @param plant block that can be planted in flower pots
     * @param name name used to register the potted block variant
     */
    public Block registerPottedPlant(Block plant, String name) {
        return registerBlock("potted_" + name, new FlowerPotBlock(plant, AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION).breakInstantly().nonOpaque()));
    }

    /**
     * {@return registers a full set of equipment}
     *
     * @param gearSet object containing the full set of equipment to be registered
     */
    public GearSet registerGear(GearSet gearSet) {
        String prefix = gearSet.identifier.getPath();
        gearSet.Equipment.keySet().forEach(key -> registerItem(prefix + "_" + key, gearSet.Equipment.get(key)));
        return gearSet;
    }

    /**
     * {@return Creates spawn egg for MobEntity}
     *
     * @param type of mob entity to register
     * @param name of the spawn egg item
     * @param primaryColor primary color of spawn egg
     * @param secondaryColor secondary color of spawn egg
     * @param addToTab if the egg should be added to the vanilla spawn eggs creative tab
     */
    public Item registerSpawnEgg(EntityType<? extends MobEntity> type, String name, int primaryColor, int secondaryColor, boolean addToTab) {
        Item egg = registerItem(name, new SpawnEggItem(type, primaryColor, secondaryColor, JSettings.item()));
        JItem.setColors(egg, primaryColor, secondaryColor);
        if (addToTab) ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(content -> content.add(egg));
        return egg;
    }

    /**
     * {@return registers a creative tab}
     *
     * @param name name of the item group
     * @param group creative tab to register
     */
    public ItemGroup registerItemGroup(String name, ItemGroup group) {
        return Registry.register(Registries.ITEM_GROUP, ID(name), group);
    }

    /**
     * {@return registers a creative tab and gives it a custom icon}
     *
     * @param name name of the item group
     * @param group creative tab to register
     * @param customIcon custom icon instance
     */
    public ItemGroup registerItemGroup(String name, ItemGroup group, JItemGroup.Icon customIcon) {
        JItemGroup jGroup = (JItemGroup)group;
        jGroup.JAPI$setCustomIcon(customIcon);
        return Registry.register(Registries.ITEM_GROUP, ID(name), group);
    }

    /**
     * {@return registers a status effect}
     *
     * @param name name of the effect
     * @param effect status effect to register
     */
    public RegistryEntry<StatusEffect> registerEffect(String name, StatusEffect effect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, ID(name), effect);
    }

    /**
     * {@return registers a potion}
     *
     * @param name name of the potion
     * @param item potion item to register
     */
    public RegistryEntry<Potion> registerPotion(String name, Potion item) {
        return Registry.registerReference(Registries.POTION, ID(name), item);
    }

    /**
     * {@return creates & registers a potion}
     *
     * @param name name of the potion
     * @param effects status effects given from consuming the potion
     */
    public RegistryEntry<Potion> registerPotion(String name, StatusEffectInstance... effects) {
        return Registry.registerReference(Registries.POTION, ID(name), new Potion(name, effects));
    }

    /**
     * {@return creates & registers a sound with no fixed range}
     *
     * @param id path for {@link Identifier}
     */
    public SoundEvent registerSound(String id) {
        Identifier i = ID(id);
        return Registry.register(Registries.SOUND_EVENT, i, SoundEvent.of(i));
    }

    /**
     * {@return creates & registers a sound with a fixed range}
     *
     * @param id path for {@link Identifier}
     * @param range distance sound can be heard from
     */
    public SoundEvent registerSound(String id, float range) {
        Identifier i = ID(id);
        return Registry.register(Registries.SOUND_EVENT, i, SoundEvent.of(i, range));
    }

    /**
     * {@return creates & registers a game event with a range of 16}
     *
     * @param id path for {@link Identifier}
     */
    public GameEvent registerEvent(String id) {
        return registerEvent(id, 16);
    }

    /**
     * {@return creates & registers a game event}
     *
     * @param id path for {@link Identifier}
     * @param range distance this event can be detected from
     */
    public GameEvent registerEvent(String id, int range) {
        return Registry.register(Registries.GAME_EVENT, ID(id), new GameEvent(range));
    }

    /**
     * {@return creates & registers a point of interest}
     *
     * @param id path for {@link Identifier}
     * @param ticketCount amount of ticks
     * @param searchDistance search distance for location
     * @param blocks all blocks that count as valid for this poi
     */
    public JPointOfInterest registerPoi(String id, int ticketCount, int searchDistance, Block... blocks) {
        Identifier identifier = ID(id);
        return new JPointOfInterest(PointOfInterestHelper.register(identifier, ticketCount, searchDistance, blocks), identifier);
    }

    /**
     * {@return creates & registers a damage source}
     *
     * @param id path for {@link Identifier}
     */
    public JDamageSource registerDamageSource(String id) {
        return new JDamageSource(ID(id));
    }

    /**
     * {@return creates & registers a player statistic}
     *
     * @param id path for {@link Identifier}
     * @param name statistic name
     * @param formatter formatter to generate statistic
     */
    public void registerStat(String id, String name, StatFormatter formatter) {
        Identifier identifier = ID(id);
        Registry.register(Registries.CUSTOM_STAT, name, identifier);
        Stats.CUSTOM.getOrCreateStat(identifier, formatter);
    }

    /**
     * {@return creates & registers a player statistic}
     *
     * @param id path for {@link Identifier}
     * @param name statistic name
     */
    public void registerStat(String id, String name) {
        registerStat(id, name, StatFormatter.DEFAULT);
    }

    /**
     * {@return creates & registers a player statistic}
     *
     * @param id path for {@link Identifier}
     */
    public void registerStat(String id) {
        registerStat(id, id, StatFormatter.DEFAULT);
    }

    /**
     * {@return creates & registers an item component}
     *
     * @param id path for {@link Identifier}
     * @param componentType component type to register
     */
    private static <T> ComponentType<T> registerComponentType(String id, ComponentType<T> componentType) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, JAPI.identifier(id), componentType);
    }
}
