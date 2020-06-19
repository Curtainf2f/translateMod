package com.github.curtainf2f.translateMod.config;

import com.github.curtainf2f.translateMod.TranslateMod;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigSyncHandler {
	public ConfigSyncHandler() {
		MinecraftForge.EVENT_BUS.register(this);
	}
	
    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        System.out.println("test");
        if (event.getModID().equals(TranslateMod.MODID)) {
            ConfigManager.sync(TranslateMod.MODID, Config.Type.INSTANCE);
        }
    }
}
