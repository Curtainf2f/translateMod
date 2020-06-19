package com.github.curtainf2f.translateMod;

import org.apache.logging.log4j.Logger;

import com.github.curtainf2f.translateMod.common.CommonProxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;


@Mod(modid = TranslateMod.MODID, name = TranslateMod.NAME, version = TranslateMod.VERSION, guiFactory = "com.github.curtainf2f.translateMod.config.ConfigGuiFactory")
public class TranslateMod
{
    public static final String MODID = "translatemod";
    public static final String NAME = "Translate Mod";
    public static final String VERSION = "1.0";

    @SidedProxy(clientSide = "com.github.curtainf2f.translateMod.common.ClientProxy", 
            serverSide = "com.github.curtainf2f.translateMod.common.CommonProxy")
    public static CommonProxy proxy;
    
    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	proxy.init(event);
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	proxy.postInit(event);
    }
}
