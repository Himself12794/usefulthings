package com.himself12794.usefulthings.proxy;

import com.himself12794.usefulthings.UsefulThings;
import com.himself12794.usefulthings.blocks.ModBlocks;
import com.himself12794.usefulthings.events.ClientEvents;
import com.himself12794.usefulthings.events.EagleVision;
import com.himself12794.usefulthings.items.ModItems;
import com.himself12794.usefulthings.spells.Spells;
import com.himself12794.usefulthings.util.KeyBindings;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

public class ClientProxy extends CommonProxy 
{   
	public ClientEvents handler = new ClientEvents();
	public static Side side;
	
    @Override
    public void preinit(FMLPreInitializationEvent event) {
    	side = Side.CLIENT;
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
    
    public double getReverseRendering(ItemStack stack) {
    	super.getReverseRendering(stack);
    	if (!Spells.hasSpell(stack)) return 1.0D;
    	EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        return ((double)Spells.getCoolDownRemaining(player, Spells.getSpell(stack)) ) / (double)Spells.getSpell(stack).getCoolDown();
    }
    
    public boolean showDamage(ItemStack stack) {
    	super.showDamage(stack);
    	
	    EntityPlayer player = Minecraft.getMinecraft().thePlayer;	    	
	    return Spells.hasSpell(stack) && Spells.getCoolDownRemaining(player, Spells.getSpell(stack)) > 0;
    }
    
    
}

