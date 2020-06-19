package com.github.curtainf2f.translateMod;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.lwjgl.input.Keyboard;

import com.baidu.translate.demo.TransApi;
import com.github.curtainf2f.translateMod.cilent.KeyLoader;
import com.github.curtainf2f.translateMod.config.ConfigLoader;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.text.TextComponentString;

public class Sender extends GuiScreen{
    private GuiScreen parentScreen;
    
    private GuiTextField message;
    private GuiButton send;
    private GuiButton close;
    
    public Sender(GuiScreen parent)
    {
    	parentScreen = parent;
    }
    
    @Override
    public void initGui()
    {
    	message = new GuiTextField(1111, fontRenderer, 0, (int)(height*0.4), width, (int)(height*0.1));
    	message.setMaxStringLength(111);
    	message.setFocused(true);
    	message.setCanLoseFocus(true);
    	buttonList.add(send = new GuiButton(1112, (int)(width*0.2), (int)(height*0.6), 80, 20, "发送"));
    	buttonList.add(close = new GuiButton(1113, (int)(width*0.7), (int)(height*0.6), 80, 20, "关闭"));
    }
    
    @Override
    protected void actionPerformed(GuiButton button) {
        if(button == send){
    		try {
				Minecraft.getMinecraft().player.sendChatMessage(BaiduTranslator.translate(message.getText(), "zh", "en"));
			} catch (Exception e) {
				mc.ingameGUI.getChatGUI().printChatMessage(new TextComponentString(e.getMessage()));
			}
        	mc.displayGuiScreen(parentScreen);
        }
        else if(button == close) {
        	mc.displayGuiScreen(parentScreen);
        }
    }
    
    @Override
    protected void keyTyped(char par1, int par2) throws IOException {
    	if(par2 == org.lwjgl.input.Keyboard.KEY_RETURN || par2 == org.lwjgl.input.Keyboard.KEY_NUMPADENTER) {
    		try {
				Minecraft.getMinecraft().player.sendChatMessage(BaiduTranslator.translate(message.getText(), "zh", "en"));
			} catch (Exception e) {
				mc.ingameGUI.getChatGUI().printChatMessage(new TextComponentString(e.getMessage()));
			}
        	mc.displayGuiScreen(parentScreen);
        	return;
    	}
        if(message.textboxKeyTyped(par1, par2)) return;
        if(mc.world != null && par2 == KeyLoader.sendTranslatedMessage.getKeyCode()) {
        	mc.displayGuiScreen(parentScreen);
        	return ;
        }
        super.keyTyped(par1, par2);
    }
     
    @Override
    protected void mouseClicked(int par1, int par2, int par3) throws IOException {
    	message.mouseClicked(par1, par2, par3);
    	if(message.isFocused()) Keyboard.enableRepeatEvents(true);
    	else Keyboard.enableRepeatEvents(false);
        super.mouseClicked(par1, par2, par3);
    }
    
    @Override
    public void drawScreen(int par1, int par2, float par3)
    {
        drawDefaultBackground();
        super.drawScreen(par1,par2,par3);
        message.drawTextBox();
    }
    
    @Override
    public void onGuiClosed() {
    	super.onGuiClosed();
    }
}
