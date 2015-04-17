package com.himself12794.usefulthings.proxy;

import com.himself12794.usefulthings.blocks.ModBlocks;
import com.himself12794.usefulthings.events.ClientEvents;
import com.himself12794.usefulthings.items.ModItems;
import com.himself12794.usefulthings.player.EagleVision;
import com.himself12794.usefulthings.util.KeyBindings;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy 
{   
	public ClientEvents handler = new ClientEvents(Minecraft.getMinecraft());
	public EagleVision eagleVision = new EagleVision();
	
    @Override
    public void preinit(FMLPreInitializationEvent event) {
    	super.preinit(event);
    }


    @Override
    public void init(FMLInitializationEvent event)
    {
    	super.init(event);    	
    	
    	FMLCommonHandler.instance().bus().register(handler);
    	//FMLCommonHandler.instance().bus().register(eagleVision);
    	//MinecraftForge.EVENT_BUS.register(eagleVision);
    	MinecraftForge.EVENT_BUS.register(handler);

        // do client-specific stuff
    	ModItems.registerTextures(event);
    	ModBlocks.registerTextures(event);
    	//KeyBindings.init();
    }
}

