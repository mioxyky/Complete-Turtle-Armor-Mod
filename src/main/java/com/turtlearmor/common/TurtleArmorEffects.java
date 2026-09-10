package com.turtlearmor.common;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

/**
 * Handles armor effects: Dolphin's Grace for leggings, Speed for boots.
 * Applied per-tick when the corresponding armor piece is worn.
 */
public class TurtleArmorEffects {

    // Effect parameters
    private static final int EFFECT_DURATION = 200; // 10 seconds in ticks (re-applied each tick)
    private static final int EFFECT_AMPLIFIER = 0;  // Level I (0 = level 1)

    /**
     * Called each tick on the server to apply armor effects.
     * Should be called from a server tick event handler.
     */
    public static void applyArmorEffects(Player player) {
        if (player.level().isClientSide()) return;

        // Check leggings -> Dolphin's Grace I
        ItemStack leggings = player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.LEGS);
        if (isTurtleLeggings(leggings)) {
            player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, EFFECT_DURATION, EFFECT_AMPLIFIER, false, false, true));
        }

        // Check boots -> Speed I
        ItemStack boots = player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.FEET);
        if (isTurtleBoots(boots)) {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, EFFECT_DURATION, EFFECT_AMPLIFIER, false, false, true));
        }
    }

    /**
     * Checks if the ItemStack is our turtle leggings.
     */
    private static boolean isTurtleLeggings(ItemStack stack) {
        if (stack.isEmpty()) return false;
        /*@if LOADER == "fabric"*/
        return stack.is(TurtleArmorItems.TURTLE_LEGGINGS_ITEM);
        /*@else*/
        return stack.getItem() == TurtleArmorItems.TURTLE_LEGGINGS.get();
        /*@endif*/
    }

    /**
     * Checks if the ItemStack is our turtle boots.
     */
    private static boolean isTurtleBoots(ItemStack stack) {
        if (stack.isEmpty()) return false;
        /*@if LOADER == "fabric"*/
        return stack.is(TurtleArmorItems.TURTLE_BOOTS_ITEM);
        /*@else*/
        return stack.getItem() == TurtleArmorItems.TURTLE_BOOTS.get();
        /*@endif*/
    }

    /**
     * Fabric-specific: register server tick event.
     * Called from Fabric entrypoint.
     */
    /*@if LOADER == "fabric"*/
    public static void registerFabricTickHandler() {
        net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (Player player : server.getPlayerList().getPlayers()) {
                applyArmorEffects(player);
            }
        });
    }
    /*@endif*/

    /**
     * Forge/NeoForge-specific: register tick event.
     * Called from Forge/NeoForge entrypoint.
     */
    /*@if LOADER != "fabric"*/
    public static void registerForgeTickHandler() {
        net.minecraftforge.eventbus.api.SubscribeEvent
        public static void onPlayerTick(net.minecraftforge.event.TickEvent.PlayerTickEvent event) {
            if (event.phase == net.minecraftforge.event.TickEvent.Phase.END && !event.player.level().isClientSide()) {
                applyArmorEffects(event.player);
            }
        }
    }
    /*@endif*/
}