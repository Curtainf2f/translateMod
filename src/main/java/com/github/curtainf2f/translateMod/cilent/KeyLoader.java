package com.github.curtainf2f.translateMod.cilent;

import net.java.games.input.Keyboard;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class KeyLoader {
	public static KeyBinding setTranslation;
	public KeyLoader() {
		KeyLoader.setTranslation = new KeyBinding("key.curtainf2f.translation.setTranslation", org.lwjgl.input.Keyboard.KEY_Y, "key.curtainf2f.translation");
		ClientRegistry.registerKeyBinding(KeyLoader.setTranslation);
	}
}
