package com.github.curtainf2f.translateMod.common;

import com.github.curtainf2f.translateMod.ListenChatBar;
import com.github.curtainf2f.translateMod.cilent.KeyLoader;
import com.github.curtainf2f.translateMod.cilent.KeyMenuEvent;
import com.github.curtainf2f.translateMod.config.ConfigSyncHandler;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy
{
    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event)
    {
        super.init(event);
        new KeyLoader();
        new KeyMenuEvent();
        new ConfigSyncHandler();
        new ListenChatBar();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event)
    {
        super.postInit(event);
    }
}
