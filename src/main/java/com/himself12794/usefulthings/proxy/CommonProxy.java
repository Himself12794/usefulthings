package com.himself12794.usefulthings.proxy;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

import com.himself12794.usefulthings.ModRecipes;
import com.himself12794.usefulthings.UsefulThings;
import com.himself12794.usefulthings.blocks.ModBlocks;
import com.himself12794.usefulthings.events.CommonEvents;
import com.himself12794.usefulthings.events.EagleVision;
import com.himself12794.usefulthings.items.ModItems;
import com.himself12794.usefulthings.network.MessageClient;
import com.himself12794.usefulthings.util.Reference;
import com.himself12794.usefulthings.worldgen.WorldGen;

public class CommonProxy {
	
	public static SimpleNetworkWrapper network;
	
	public void preinit(FMLPreInitializationEvent event) {

		//side = Side.SERVER;
		
		network = NetworkRegistry.INSTANCE.newSimpleChannel("[" + Reference.MODID + "] NetChannel");
		network.registerMessage(MessageClient.Handler.class, MessageClient.class, 0, Side.CLIENT);
	
		// register items
		ModItems.addItems();
		if (ModItems.NUMBER > 0) UsefulThings.logger.info("Added [" + ModItems.NUMBER + "] new items");
       
		// register blocks
		ModBlocks.addBlocks();
		if (ModBlocks.NUMBER > 0) UsefulThings.logger.info("Added [" + ModBlocks.NUMBER + "] new blocks");
	}

	public void init(FMLInitializationEvent event){
		CommonEvents handler = new CommonEvents();
		EagleVision eagleVision = new EagleVision();
		
    	FMLCommonHandler.instance().bus().register(handler);
    	MinecraftForge.EVENT_BUS.register(handler);
		 
		ModRecipes.addRecipes();
        WorldGen.addOreGenerators();

	}
	
	public Side getSide() {
		return Side.SERVER;
	}

	public double getReverseRendering(ItemStack stack) {
		return 2.0D;
		
	}

	public boolean showDamage(ItemStack stack) {
		return false;
	}
}
