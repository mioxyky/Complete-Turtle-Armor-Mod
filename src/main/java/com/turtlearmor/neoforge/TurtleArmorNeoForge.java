package com.turtlearmor.neoforge;

import com.turtlearmor.common.TurtleArmorEffects;
import com.turtlearmor.common.TurtleArmorItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * NeoForge mod entrypoint.
 * Same as Forge but for NeoForge loader.
 */
@Mod("turtle_armor")
public class TurtleArmorNeoForge {

    public static final String MOD_ID = "turtle_armor";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public TurtleArmorNeoForge() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register items
        TurtleArmorItems.ITEMS.register(modEventBus);

        // Register creative tab handler
        modEventBus.addListener(this::addCreative);

        // Register tick handler for armor effects
        MinecraftForge.EVENT_BUS.register(TurtleArmorEffects.class);

        LOGGER.info("Complete Turtle Armor initialized (NeoForge)");
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            // Add after vanilla turtle helmet
            event.acceptAfter(Items.TURTLE_HELMET, TurtleArmorItems.TURTLE_CHESTPLATE);
            event.acceptAfter(TurtleArmorItems.TURTLE_CHESTPLATE, TurtleArmorItems.TURTLE_LEGGINGS);
            event.acceptAfter(TurtleArmorItems.TURTLE_LEGGINGS, TurtleArmorItems.TURTLE_BOOTS);
        }
    }
}