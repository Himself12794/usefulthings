package com.himself12794.usefulthings.proxy;

import com.himself12794.usefulthings.ModRecipes;
import com.himself12794.usefulthings.blocks.ModBlocks;
import com.himself12794.usefulthings.events.CommonEvents;
import com.himself12794.usefulthings.items.ModItems;
import com.himself12794.usefulthings.worldgen.WorldGen;
import com.himself12794.usefulthings.network.SaveEagleVisionClient;
import com.himself12794.usefulthings.network.SaveEagleVisionServer;

import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class CommonProxy {
	
	public static SimpleNetworkWrapper network;
	
	public void preinit(FMLPreInitializationEvent event) {

       network = NetworkRegistry.INSTANCE.newSimpleChannel("MyChannel");
       network.registerMessage(SaveEagleVisionServer.Handler.class, SaveEagleVisionServer.class, 0, Side.SERVER);
       network.registerMessage(SaveEagleVisionClient.Handler.class, SaveEagleVisionClient.class, 1, Side.CLIENT);
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
