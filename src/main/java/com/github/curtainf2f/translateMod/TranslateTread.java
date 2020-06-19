package com.github.curtainf2f.translateMod;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.curtainf2f.translateMod.config.ConfigLoader;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.event.HoverEvent;

public class TranslateTread implements Runnable{
	private ITextComponent msg;
	
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
	
	public TranslateTread(ITextComponent msg) {
		this.msg = msg;
	}
	
	@Override
	public void run() {
		try {
			if(checkBlack(msg.getUnformattedText())) throw new Exception("");
			Matcher f = matchString(msg.getUnformattedText());
			if(f == null) throw new Exception("");
			msg.getStyle().setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentString(f.group(0) + BaiduTranslator.translate(msg.getUnformattedText().replace(f.group(0), ""), "auto", "zh"))));
		}catch(Exception e) {
			msg.getStyle().setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentString(e.getMessage())));
		}finally{
			Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(msg);			
		}
	}
}