package jacobg5.japi.containers;

import jacobg5.japi.JUtils;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class GearSet implements Iterable<Item> {
    public final Map<String, Item> Equipment = new HashMap<>();

    public final Identifier identifier;

    @Nullable public Item shovel;
    @Nullable public Item sword;
    @Nullable public Item pickaxe;
    @Nullable public Item axe;
    @Nullable public Item hoe;
    @Nullable public ToolMaterial toolMaterial;

    @Nullable public Item helmet;
    @Nullable public Item chestplate;
    @Nullable public Item leggings;
    @Nullable public Item boots;
    @Nullable public Item body;
    @Nullable public ArmorMaterial armorMaterial;

    public Scope scope;

    public GearSet(Identifier identifier,
                   @Nullable ToolMaterial toolMaterial,
                   @Nullable Item shovel, @Nullable Item sword, @Nullable Item pickaxe, @Nullable Item axe, @Nullable Item hoe,
                   @Nullable ArmorMaterial armorMaterial,
                   @Nullable Item helmet, @Nullable Item chestplate, @Nullable Item leggings, @Nullable Item boots, @Nullable Item body) {
        this.shovel = shovel;
        if (shovel != null) Equipment.put("shovel", shovel);
        this.sword = sword;
        if (sword != null) Equipment.put("sword", sword);
        this.pickaxe = pickaxe;
        if (pickaxe != null) Equipment.put("pickaxe", pickaxe);
        this.axe = axe;
        if (axe != null) Equipment.put("axe", axe);
        this.hoe = hoe;
        if (hoe != null) Equipment.put("hoe", hoe);

        this.helmet = helmet;
        if (helmet != null) Equipment.put("helmet", helmet);
        this.chestplate = chestplate;
        if (chestplate != null) Equipment.put("chestplate", chestplate);
        this.leggings = leggings;
        if (leggings != null) Equipment.put("leggings", leggings);
        this.boots = boots;
        if (boots != null) Equipment.put("boots", boots);
        this.body = body;
        if (body != null) Equipment.put("body", body);

        scope = Scope.Full;
        this.identifier = identifier;
        this.toolMaterial = toolMaterial;
        this.armorMaterial = armorMaterial;
    }

    public GearSet(Identifier identifier, @Nullable ToolMaterial toolMaterial,
                   @Nullable Item shovel, @Nullable Item sword, @Nullable Item pickaxe, @Nullable Item axe, @Nullable Item hoe) {
        this(identifier, toolMaterial, shovel, sword, pickaxe, axe, hoe, null, null, null, null, null, null);
        scope = Scope.Tools;
    }

    public GearSet(Identifier identifier, @Nullable ArmorMaterial armorMaterial,
                   @Nullable Item helmet, @Nullable Item chestplate, @Nullable Item leggings, @Nullable Item boots, @Nullable Item body) {
        this(identifier, null, null, null, null, null, null, armorMaterial, helmet, chestplate, leggings, boots, body);
        scope = Scope.Armor;
    }

    public GearSet(Identifier identifier) {
        this.shovel = null;
        this.sword = null;
        this.pickaxe = null;
        this.axe = null;
        this.hoe = null;

        this.helmet = null;
        this.chestplate = null;
        this.leggings = null;
        this.boots = null;
        this.body = null;

        scope = Scope.Unknown;
        this.identifier = identifier;
    }
    public static GearSet tools(Identifier name, ToolMaterial material, Supplier<Item.Settings> itemSettings, float... values) {
        return new GearSet(name, material,
                values.length < 2 ? null : new SwordItem(material, itemSettings.get().attributeModifiers(SwordItem.createAttributeModifiers(material, (int)values[0], values[1]))),
                values.length < 4 ? null : new ShovelItem(material, itemSettings.get().attributeModifiers(ShovelItem.createAttributeModifiers(material, (int)values[2], values[3]))),
                values.length < 6 ? null : new PickaxeItem(material, itemSettings.get().attributeModifiers(PickaxeItem.createAttributeModifiers(material, (int)values[4], values[5]))),
                values.length < 8 ? null : new AxeItem(material, itemSettings.get().attributeModifiers(AxeItem.createAttributeModifiers(material, (int)values[6], values[7]))),
                values.length < 10 ? null : new HoeItem(material, itemSettings.get().attributeModifiers(HoeItem.createAttributeModifiers(material, (int)values[8], values[9]))));
    }

    public static GearSet armor(Identifier name, RegistryEntry<ArmorMaterial> material, Supplier<Item.Settings> itemSettings, int armorMultiplier) {
        return new GearSet(name, material.value(),
                new ArmorItem(material, ArmorItem.Type.HELMET, itemSettings.get().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(armorMultiplier))),
                new ArmorItem(material, ArmorItem.Type.CHESTPLATE, itemSettings.get().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(armorMultiplier))),
                new ArmorItem(material, ArmorItem.Type.LEGGINGS, itemSettings.get().maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(armorMultiplier))),
                new ArmorItem(material, ArmorItem.Type.BOOTS, itemSettings.get().maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(armorMultiplier))),
                null);
    }

    public static GearSet full(Identifier name, ToolMaterial toolMaterial, RegistryEntry<ArmorMaterial> armorMaterial, Supplier<Item.Settings> itemSettings, int armorMultiplier, float... values) {
        return new GearSet(name, toolMaterial,
                values.length < 2 ? null : new SwordItem(toolMaterial, itemSettings.get().attributeModifiers(SwordItem.createAttributeModifiers(toolMaterial, (int)values[0], values[1]))),
                values.length < 4 ? null : new ShovelItem(toolMaterial, itemSettings.get().attributeModifiers(ShovelItem.createAttributeModifiers(toolMaterial, (int)values[2], values[3]))),
                values.length < 6 ? null : new PickaxeItem(toolMaterial, itemSettings.get().attributeModifiers(PickaxeItem.createAttributeModifiers(toolMaterial, (int)values[4], values[5]))),
                values.length < 8 ? null : new AxeItem(toolMaterial, itemSettings.get().attributeModifiers(AxeItem.createAttributeModifiers(toolMaterial, (int)values[6], values[7]))),
                values.length < 10 ? null : new HoeItem(toolMaterial, itemSettings.get().attributeModifiers(HoeItem.createAttributeModifiers(toolMaterial, (int)values[8], values[9]))),
                armorMaterial.value(),
                new ArmorItem(armorMaterial, ArmorItem.Type.HELMET, itemSettings.get().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(armorMultiplier))),
                new ArmorItem(armorMaterial, ArmorItem.Type.CHESTPLATE, itemSettings.get().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(armorMultiplier))),
                new ArmorItem(armorMaterial, ArmorItem.Type.LEGGINGS, itemSettings.get().maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(armorMultiplier))),
                new ArmorItem(armorMaterial, ArmorItem.Type.BOOTS, itemSettings.get().maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(armorMultiplier))),
                null);
    }

    /*
    // THIS IS FOR FUTURE VERSIONS OF MINECRAFT
    public static GearSet Tools(Identifier name, ToolMaterial material, Item.Settings itemSettings, float... values) {
        return new GearSet(name, material,
                new Item(new Item.Settings().sword(material, values[0], values[1])),
                new ShovelItem(material, values[2], values[3], new Item.Settings()),
                new Item(new Item.Settings().pickaxe(material, values[4], values[5])),
                new AxeItem(material, values[6], values[7], new Item.Settings()),
                new HoeItem(material, values[8], values[9], new Item.Settings()));
    }

    public static GearSet Armor(Identifier name, ArmorMaterial material, Item.Settings itemSettings) {
        return new GearSet(name, material,
                new Item(new Item.Settings().armor(material, EquipmentType.HELMET)),
                new Item(new Item.Settings().armor(material, EquipmentType.CHESTPLATE)),
                new Item(new Item.Settings().armor(material, EquipmentType.LEGGINGS)),
                new Item(new Item.Settings().armor(material, EquipmentType.BOOTS)),
                new Item(new Item.Settings().armor(material, EquipmentType.BODY)));
    }

    public static GearSet Full(Identifier name, ToolMaterial toolMaterial, ArmorMaterial armorMaterial, Item.Settings itemSettings, float... values) {
        return new GearSet(name, toolMaterial,
                new Item(new Item.Settings().sword(toolMaterial, values[0], values[1])),
                new ShovelItem(toolMaterial, values[2], values[3], new Item.Settings()),
                new Item(new Item.Settings().pickaxe(toolMaterial, values[4], values[5])),
                new AxeItem(toolMaterial, values[6], values[7], new Item.Settings()),
                new HoeItem(toolMaterial, values[8], values[9], new Item.Settings()),
                armorMaterial, new Item(new Item.Settings().armor(armorMaterial, EquipmentType.HELMET)),
                new Item(new Item.Settings().armor(armorMaterial, EquipmentType.CHESTPLATE)),
                new Item(new Item.Settings().armor(armorMaterial, EquipmentType.LEGGINGS)),
                new Item(new Item.Settings().armor(armorMaterial, EquipmentType.BOOTS)),
                new Item(new Item.Settings().armor(armorMaterial, EquipmentType.BODY)));
    }
    */

    @Override
    public @NotNull Iterator<Item> iterator() {
        return Equipment.values().iterator();
    }

    public Map.Entry<Identifier, GearSet> entry() {
        return Map.entry(identifier, this);
    }

    public void autoGroup(@Nullable ItemConvertible boots, @Nullable ItemConvertible hoe, @Nullable ItemConvertible sword, @Nullable ItemConvertible axe) {
        autoGroup(ItemGroups.TOOLS, ItemGroups.COMBAT, boots, hoe, sword, axe);
    }
    public void autoGroup(RegistryKey<ItemGroup> gearTab, @Nullable ItemConvertible boots, @Nullable ItemConvertible hoe, @Nullable ItemConvertible sword, @Nullable ItemConvertible axe) {
        autoGroup(gearTab, gearTab, boots, hoe, sword, axe);
    }
    public void autoGroup(RegistryKey<ItemGroup> toolsTab, RegistryKey<ItemGroup> combatTab, @Nullable ItemConvertible boots, @Nullable ItemConvertible hoe, @Nullable ItemConvertible sword, @Nullable ItemConvertible axe) {
        if (hoe != null) ItemGroupEvents.modifyEntriesEvent(toolsTab).register(content -> {
            content.addAfter(hoe, JUtils.convertToDefaultStacks(Stream.of(this.shovel, this.pickaxe, this.axe, this.hoe)));
        });
        if (sword != null || axe != null || boots != null) ItemGroupEvents.modifyEntriesEvent(combatTab).register(content -> {
            if (this.sword != null && sword != null) content.addAfter(sword, this.sword);
            if (this.axe != null && axe != null) content.addAfter(axe, this.axe);
            if (boots != null) content.addAfter(boots, JUtils.convertToDefaultStacks(Stream.of(this.helmet, this.chestplate, this.leggings, this.boots)));
        });
    }

    public enum Scope
    {
        Tools,
        Armor,
        Full,
        Unknown
    }
}
