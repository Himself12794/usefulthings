package com.himself12794.usefulthings.proxy;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;

import com.himself12794.usefulthings.ModRecipes;
import com.himself12794.usefulthings.UsefulThings;
import com.himself12794.usefulthings.blocks.ModBlocks;
import com.himself12794.usefulthings.entity.EntitySpell;
import com.himself12794.usefulthings.events.CommonEvents;
import com.himself12794.usefulthings.events.EagleVision;
import com.himself12794.usefulthings.events.SpellCoolDownHook;
import com.himself12794.usefulthings.events.SpellEffectHandler;
import com.himself12794.usefulthings.items.ModItems;
import com.himself12794.usefulthings.network.CastSpellClient;
import com.himself12794.usefulthings.network.CastSpellServer;
import com.himself12794.usefulthings.network.MessageClient;
import com.himself12794.usefulthings.network.MessageServer;
import com.himself12794.usefulthings.spell.Spell;
import com.himself12794.usefulthings.util.Reference;
import com.himself12794.usefulthings.worldgen.WorldGen;

public class CommonProxy {
	
	public static SimpleNetworkWrapper network;
	
	public void preinit(FMLPreInitializationEvent event) {

		//side = Side.SERVER;
		
		network = NetworkRegistry.INSTANCE.newSimpleChannel("[" + Reference.MODID + "] NetChannel");
		network.registerMessage(MessageServer.Handler.class, MessageServer.class, 0, Side.SERVER);
		network.registerMessage(MessageClient.Handler.class, MessageClient.class, 1, Side.CLIENT);
		network.registerMessage(CastSpellServer.Handler.class, CastSpellServer.class, 2, Side.SERVER);
		network.registerMessage(CastSpellClient.Handler.class, CastSpellClient.class, 3, Side.CLIENT);
	
		// register items
		ModItems.addItems();
		if (ModItems.NUMBER > 0) UsefulThings.logger.info("Added [" + ModItems.NUMBER + "] new items");
       
		// register blocks
		ModBlocks.addBlocks();
		if (ModBlocks.NUMBER > 0) UsefulThings.logger.info("Added [" + ModBlocks.NUMBER + "] new blocks");
       
		// register spells
		Spell.registerSpells();
		
		// register entities
		EntityRegistry.registerModEntity(EntitySpell.class, "spell", 1, UsefulThings.instance, 80, 3, true);
		UsefulThings.logger.info("Registered [1] new entity");
	}

	public void init(FMLInitializationEvent event){
		CommonEvents handler = new CommonEvents();
		EagleVision eagleVision = new EagleVision();
		SpellCoolDownHook scdh = new SpellCoolDownHook();
		SpellEffectHandler spfx = new SpellEffectHandler();
		
    	FMLCommonHandler.instance().bus().register(handler);
    	MinecraftForge.EVENT_BUS.register(handler);
    	MinecraftForge.EVENT_BUS.register(scdh);
    	MinecraftForge.EVENT_BUS.register(spfx);
		 
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
