package com.himself12794.usefulthings.proxy;

import com.himself12794.usefulthings.blocks.ModBlocks;
import com.himself12794.usefulthings.events.ClientEvents;
import com.himself12794.usefulthings.events.EagleVision;
import com.himself12794.usefulthings.items.ModItems;
import com.himself12794.usefulthings.util.KeyBindings;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy 
{   
	public ClientEvents handler = new ClientEvents();
	
    @Override
    public void preinit(FMLPreInitializationEvent event) {
    	super.preinit(event);
    }


    @Override
    public void init(FMLInitializationEvent event)
    {
    	super.init(event);    	
    	//EagleVision.init();
    	FMLCommonHandler.instance().bus().register(handler);
    	MinecraftForge.EVENT_BUS.register(handler);

        // do client-specific stuff
    	ModItems.registerTextures(event);
    	ModBlocks.registerTextures(event);
    	KeyBindings.init();
    }
}

