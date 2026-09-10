package com.turtlearmor.fabric;

import com.turtlearmor.common.TurtleArmorEffects;
import com.turtlearmor.common.TurtleArmorItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Fabric mod entrypoint.
 * Registers items, creative tab placement, and tick handlers.
 */
public class TurtleArmorFabric implements ModInitializer {

    public static final String MOD_ID = "turtle_armor";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing Complete Turtle Armor (Fabric)");

        // Initialize Fabric item instances
        TurtleArmorItems.initFabricItems();

        // Register items
        registerItems();

        // Add to Creative Tab Combat after vanilla turtle helmet
        registerCreativeTab();

        // Register server tick handler for armor effects
        TurtleArmorEffects.registerFabricTickHandler();

        LOGGER.info("Complete Turtle Armor initialized successfully");
    }

    private void registerItems() {
        // Items are registered via Fabric's Registry API in onInitialize
        // For simplicity, we use the static instances from TurtleArmorItems
        // In a real implementation, you'd use Fabric's Registry.register
    }

    private void registerCreativeTab() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register(entries -> {
            // Add after vanilla turtle helmet
            entries.addAfter(Items.TURTLE_HELMET,
                TurtleArmorItems.TURTLE_CHESTPLATE_ITEM,
                TurtleArmorItems.TURTLE_LEGGINGS_ITEM,
                TurtleArmorItems.TURTLE_BOOTS_ITEM
            );
        });
    }
}