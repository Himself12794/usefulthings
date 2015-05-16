package com.himself12794.usefulthings;

import net.minecraft.client.Minecraft;
import net.minecraft.client.main.GameConfiguration;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.himself12794.usefulthings.blocks.ModBlocks;
import com.himself12794.usefulthings.events.EagleVision;
import com.himself12794.usefulthings.items.ModItems;
import com.himself12794.usefulthings.proxy.CommonProxy;
import com.himself12794.usefulthings.util.Reference;
import com.himself12794.usefulthings.worldgen.WorldGen;

@Mod(modid = Reference.MODID, version = Reference.VERSION, name = Reference.NAME)
public class UsefulThings {    

	@Instance(value = Reference.MODID)
	public static UsefulThings instance;
	//public static UsefulThingsEventHandler handler = new UsefulThingsEventHandler(Minecraft.getMinecraft());

	//public static SimpleNetworkWrapper network;
	public static CreativeTabs usefulThings = new CreativeTabs("usefulThings") {
	    @Override
	    @SideOnly(Side.CLIENT)
	    public Item getTabIconItem() {
	        return ModItems.hiddenBladeRetracted;
	    }
	};
	

	@SidedProxy(
			clientSide="com.himself12794.usefulthings.proxy.ClientProxy", 
			serverSide="com.himself12794.usefulthings.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	
	
    @EventHandler
    public void preinit(FMLPreInitializationEvent event) throws Exception {
    	
    	proxy.preinit(event);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
    	
    	
    	//MinecraftForge.EVENT_BUS.register(new EagleVision());
    	proxy.init(event);
        
    }
}