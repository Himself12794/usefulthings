package com.himself12794.usefulthings.util;
import org.lwjgl.input.Keyboard;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraft.client.settings.KeyBinding;

public class KeyBindings {

    public static KeyBinding eagleVision;

    public static void init() {
        eagleVision = new KeyBinding("key.eagleVision", Keyboard.KEY_V, "key.categories." + Reference.MODID);
        ClientRegistry.registerKeyBinding(eagleVision);
    }

}