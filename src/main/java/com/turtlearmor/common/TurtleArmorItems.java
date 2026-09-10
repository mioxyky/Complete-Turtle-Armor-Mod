package com.turtlearmor.common;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * Common item definitions for Turtle Armor pieces.
 * Stonecutter will preprocess this for each version group.
 */
public class TurtleArmorItems {

    // Deferred register for Forge/NeoForge
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "turtle_armor");

    // Armor material - will be defined per version group via Stonecutter
    public static final ArmorMaterial TURTLE_ARMOR_MATERIAL = createTurtleArmorMaterial();

    // The three missing armor pieces
    /*@if MC_VERSION >= "1.21"*/
    // 1.21+ uses DataComponent-based armor
    public static final RegistryObject<Item> TURTLE_CHESTPLATE = ITEMS.register("turtle_chestplate",
        () -> new ArmorItem(TURTLE_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final RegistryObject<Item> TURTLE_LEGGINGS = ITEMS.register("turtle_leggings",
        () -> new ArmorItem(TURTLE_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Properties()));

    public static final RegistryObject<Item> TURTLE_BOOTS = ITEMS.register("turtle_boots",
        () -> new ArmorItem(TURTLE_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Properties()));
    /*@else*/
    // 1.20 and below uses ArmorMaterial enum-style
    public static final RegistryObject<Item> TURTLE_CHESTPLATE = ITEMS.register("turtle_chestplate",
        () -> new ArmorItem(TURTLE_ARMOR_MATERIAL, EquipmentSlot.CHEST, new Item.Properties()));

    public static final RegistryObject<Item> TURTLE_LEGGINGS = ITEMS.register("turtle_leggings",
        () -> new ArmorItem(TURTLE_ARMOR_MATERIAL, EquipmentSlot.LEGS, new Item.Properties()));

    public static final RegistryObject<Item> TURTLE_BOOTS = ITEMS.register("turtle_boots",
        () -> new ArmorItem(TURTLE_ARMOR_MATERIAL, EquipmentSlot.FEET, new Item.Properties()));
    /*@endif*/

    /**
     * Creates the turtle armor material matching vanilla turtle helmet stats.
     * Vanilla turtle helmet: durability 275, protection 2, toughness 0, enchantability 9
     */
    private static ArmorMaterial createTurtleArmorMaterial() {
        /*@if MC_VERSION >= "1.21"*/
        // 1.21+: ArmorMaterial is a registry entry with DataComponents
        // This is a placeholder - actual implementation uses Bootstrap/Registries
        return null; // Will be overridden in version-specific code
        /*@else*/
        // 1.20-: Traditional ArmorMaterial
        return new ArmorMaterial() {
            @Override
            public int getDurabilityForType(ArmorItem.Type type) {
                // Base durability multipliers: BOOTS=13, LEGGINGS=15, CHESTPLATE=16, HELMET=11
                int[] multipliers = {13, 15, 16, 11};
                return multipliers[type.ordinal()] * 25; // 25 = base durability (turtle helmet = 275/11 = 25)
            }

            @Override
            public int getDefenseForType(ArmorItem.Type type) {
                // Protection values: BOOTS=1, LEGGINGS=2, CHESTPLATE=3, HELMET=2 (turtle helmet = 2)
                int[] protection = {1, 2, 3, 2};
                return protection[type.ordinal()];
            }

            @Override
            public int getEnchantmentValue() {
                return 9; // Same as turtle helmet
            }

            @Override
            public net.minecraft.sounds.SoundEvent getEquipSound() {
                return net.minecraft.sounds.SoundEvents.ARMOR_EQUIP_TURTLE;
            }

            @Override
            public net.minecraft.tags.TagKey<Item> getRepairIngredient() {
                return net.minecraft.tags.ItemTags.create(new net.minecraft.resources.ResourceLocation("minecraft", "scutes"));
            }

            @Override
            public String getName() {
                return "turtle_armor";
            }

            @Override
            public float getToughness() {
                return 0.0f; // Same as turtle helmet
            }

            @Override
            public float getKnockbackResistance() {
                return 0.0f;
            }
        };
        /*@endif*/
    }

    // Fabric-only: static instances for direct registration
    /*@if LOADER == "fabric"*/
    public static Item TURTLE_CHESTPLATE_ITEM;
    public static Item TURTLE_LEGGINGS_ITEM;
    public static Item TURTLE_BOOTS_ITEM;

    public static void initFabricItems() {
        /*@if MC_VERSION >= "1.21"*/
        TURTLE_CHESTPLATE_ITEM = new ArmorItem(TURTLE_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Properties());
        TURTLE_LEGGINGS_ITEM = new ArmorItem(TURTLE_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Properties());
        TURTLE_BOOTS_ITEM = new ArmorItem(TURTLE_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Properties());
        /*@else*/
        TURTLE_CHESTPLATE_ITEM = new ArmorItem(TURTLE_ARMOR_MATERIAL, EquipmentSlot.CHEST, new Item.Properties());
        TURTLE_LEGGINGS_ITEM = new ArmorItem(TURTLE_ARMOR_MATERIAL, EquipmentSlot.LEGS, new Item.Properties());
        TURTLE_BOOTS_ITEM = new ArmorItem(TURTLE_ARMOR_MATERIAL, EquipmentSlot.FEET, new Item.Properties());
        /*@endif*/
    }
    /*@endif*/
}