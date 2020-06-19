package com.github.curtainf2f.translateMod.cilent;

import com.github.curtainf2f.translateMod.Sender;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class KeyMenuEvent {
	
	public KeyMenuEvent() {
		MinecraftForge.EVENT_BUS.register(this);
	}
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (KeyLoader.sendTranslatedMessage.isPressed())
        {
        	Minecraft mc = Minecraft.getMinecraft();
        	mc.displayGuiScreen(new Sender(mc.currentScreen));
        }
    }
}
