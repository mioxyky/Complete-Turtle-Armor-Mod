package com.turtlearmor.fabric;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Fabric client entrypoint.
 * For client-side only initialization (rendering, keybindings, etc.).
 */
public class TurtleArmorFabricClient implements ClientModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("turtle_armor_client");

    @Override
    public void onInitializeClient() {
        LOGGER.info("Initializing Complete Turtle Armor Client (Fabric)");
        // Client-side initialization (if needed)
    }
}