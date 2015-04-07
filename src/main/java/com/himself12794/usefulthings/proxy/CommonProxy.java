package com.himself12794.usefulthings.proxy;

import com.himself12794.usefulthings.ModRecipes;
import com.himself12794.usefulthings.blocks.ModBlocks;
import com.himself12794.usefulthings.events.CommonEvents;
import com.himself12794.usefulthings.items.ModItems;
import com.himself12794.usefulthings.worldgen.WorldGen;

import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
	

	
	public void preinit(FMLPreInitializationEvent event) { 
		// register stuff
		ModItems.addItems();
		ModBlocks.addBlocks();
	}

	public void init(FMLInitializationEvent event){
		CommonEvents handler = new CommonEvents();
		
    	//FMLCommonHandler.instance().bus().register(handler);
    	FMLCommonHandler.instance().bus().register(handler);
    	MinecraftForge.EVENT_BUS.register(handler);
    	//MinecraftForge.TERRAIN_GEN_BUS.register(handler);
    	//MinecraftForge.ORE_GEN_BUS.register(handler);
		 
		ModRecipes.addRecipes();
        WorldGen.addOreGenerators();

	}
}
