package com.github.curtainf2f.translateMod;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.curtainf2f.translateMod.config.ConfigLoader;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ListenChatBar {
	public ListenChatBar() {
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	boolean checkBlack(String msg) throws Exception{
		int i = 0;
		try {
			for(i = 0; i < ConfigLoader.shieldList.length; i ++) {
				Pattern p = Pattern.compile(ConfigLoader.shieldList[i]);
				Matcher m = p.matcher(msg);
				if(m.find()) return true;
			}
		}catch(Exception e) {
			throw new Exception("屏蔽列表第" + String.valueOf(i+1) + "条正则表达式有误");
		}
		return false;
	}
	
	Matcher matchString(String msg) throws Exception{
		int i = 0;
		try {
			for(i = 0; i < ConfigLoader.findList.length; i ++) {
				Pattern p = Pattern.compile(ConfigLoader.findList[i]);
				Matcher m = p.matcher(msg);
				if(m.find()) return m;
			}
		}catch(Exception e) {
			throw new Exception("匹配列表第" + String.valueOf(i+1) + "条正则表达式有误");
		}
		return null;
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
			if(checkBlack(need)) return ;
			Matcher f = matchString(need);
			if(f == null) return;
			need = need.replace(f.group(0), "");
			event.setMessage(new TextComponentString("翻译中..."));
			event.setMessage(getHoverMessage(f.group(0) + BaiduTranslator.translate(need, "auto", "zh"), st));
		}catch(Exception e) {
			event.setMessage(new TextComponentString(msg + "   " + e.getMessage()));
		}
	}
}
