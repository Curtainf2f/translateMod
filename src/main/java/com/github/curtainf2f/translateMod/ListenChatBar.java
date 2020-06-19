package com.github.curtainf2f.translateMod;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ListenChatBar {
	public ListenChatBar() {
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SubscribeEvent
	public void getMessage(ClientChatReceivedEvent event) {
		event.setCanceled(true);
		TranslateTread t = new TranslateTread(event.getMessage());
		Thread t1 = new Thread(t);
		t1.start();
	}
}
