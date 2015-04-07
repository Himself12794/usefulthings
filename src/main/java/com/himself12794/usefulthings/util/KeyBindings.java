package com.himself12794.usefulthings.util;
import org.lwjgl.input.Keyboard;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraft.client.settings.KeyBinding;

public class KeyBindings {

    // Declare two KeyBindings, ping and pong
    public static KeyBinding eagleVision;

    public static void init() {
        // Define the "ping" binding, with (unlocalized) name "key.ping" and
        // the category with (unlocalized) name "key.categories.mymod" and
        // key code 24 ("O", LWJGL constant: Keyboard.KEY_O)
        eagleVision = new KeyBinding("key.eagleVision", Keyboard.KEY_V, "key.categories." + Reference.MODID);
        // Register both KeyBindings to the ClientRegistry
        ClientRegistry.registerKeyBinding(eagleVision);
    }

}