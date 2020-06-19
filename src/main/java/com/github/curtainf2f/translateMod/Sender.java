package com.github.curtainf2f.translateMod;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;

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
    	Keyboard.enableRepeatEvents(true);
    	message = new GuiTextField(1111, fontRenderer, 0, height/2, width, (int)(height*0.1));
    	message.setMaxStringLength(111);
    	message.setFocused(true);
    	message.setCanLoseFocus(true);
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
    	Keyboard.enableRepeatEvents(false);
    }
}
