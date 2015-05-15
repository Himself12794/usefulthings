package com.himself12794.usefulthings.proxy;

import com.himself12794.usefulthings.ModRecipes;
import com.himself12794.usefulthings.UsefulThings;
import com.himself12794.usefulthings.blocks.ModBlocks;
import com.himself12794.usefulthings.entity.EntitySpellBolt;
import com.himself12794.usefulthings.entity.EntitySpellHitScan;
import com.himself12794.usefulthings.events.CommonEvents;
import com.himself12794.usefulthings.events.EagleVision;
import com.himself12794.usefulthings.items.ModItems;
import com.himself12794.usefulthings.worldgen.WorldGen;
import com.himself12794.usefulthings.network.MessageClient;
import com.himself12794.usefulthings.network.MessageServer;

import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class CommonProxy {
	
	public static SimpleNetworkWrapper network;
	
	public void preinit(FMLPreInitializationEvent event) {

       network = NetworkRegistry.INSTANCE.newSimpleChannel("MyChannel");
       network.registerMessage(MessageServer.Handler.class, MessageServer.class, 0, Side.SERVER);
       network.registerMessage(MessageClient.Handler.class, MessageClient.class, 1, Side.CLIENT);
		// register stuff
		ModItems.addItems();
		ModBlocks.addBlocks();
		
		// register entities
		EntityRegistry.registerModEntity(EntitySpellHitScan.class, "spellHitscan", 1, UsefulThings.instance, 80, 3, true);
		EntityRegistry.registerModEntity(EntitySpellBolt.class, "spellBolt", 1, UsefulThings.instance, 80, 3, true);
	}

	public void init(FMLInitializationEvent event){
		CommonEvents handler = new CommonEvents();
		EagleVision eagleVision = new EagleVision();
		
    	//FMLCommonHandler.instance().bus().register(handler);
    	FMLCommonHandler.instance().bus().register(handler);
    	//FMLCommonHandler.instance().bus().register(eagleVision);
    	MinecraftForge.EVENT_BUS.register(handler);
    	//MinecraftForge.EVENT_BUS.register(eagleVision);
    	//MinecraftForge.TERRAIN_GEN_BUS.register(handler);
    	//MinecraftForge.ORE_GEN_BUS.register(handler);
		 
		ModRecipes.addRecipes();
        WorldGen.addOreGenerators();

	}
}
