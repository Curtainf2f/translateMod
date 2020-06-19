package com.github.curtainf2f.translateMod;

import java.io.UnsupportedEncodingException;

import com.baidu.translate.demo.TransApi;
import com.github.curtainf2f.translateMod.config.ConfigLoader;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraft.util.text.event.HoverEvent.Action;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ListenChatBar {
	public ListenChatBar() {
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	public ITextComponent getHoverMessage(String show, String hover) {
		TextComponentString tx = new TextComponentString(show);
		Style style = new Style();
		HoverEvent he = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentString(hover));
		style.setHoverEvent(he);
		tx.setStyle(style);
		return tx;
	}
	
	@SubscribeEvent
	public void getMessage(ClientChatReceivedEvent event) {
		String st = "类型: " + event.getType().name() + "  消息: " + event.getMessage().getFormattedText();
		String msg = event.getMessage().getFormattedText();
		String need = event.getMessage().getUnformattedText();
		try {
			event.setMessage(new TextComponentString("翻译中..."));
			event.setMessage(getHoverMessage(BaiduTranslator.translate(need, "auto", "zh"), st));
		}catch(Exception e) {
			event.setMessage(new TextComponentString(msg + "   " + e.getMessage()));
		}
	}
}
