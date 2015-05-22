package com.himself12794.usefulthings.proxy;

import com.himself12794.usefulthings.Spells;
import com.himself12794.usefulthings.UsefulThings;
import com.himself12794.usefulthings.blocks.ModBlocks;
import com.himself12794.usefulthings.events.ClientEvents;
import com.himself12794.usefulthings.events.EagleVision;
import com.himself12794.usefulthings.items.ModItems;
import com.himself12794.usefulthings.spell.Spell;
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
    
    public double getReverseRendering(ItemStack stack) {
    	super.getReverseRendering(stack);
    	if (!Spell.hasSpell(stack)) return 1.0D;
    	EntityPlayer player = Minecraft.getMinecraft().thePlayer;
    	Spell spell = Spell.getSpell(stack);
        return ((double)spell.getCoolDownRemaining(player) ) / (double)spell.getCoolDown();
    }
    
    public boolean showDamage(ItemStack stack) {
    	super.showDamage(stack);
    	
	    EntityPlayer player = Minecraft.getMinecraft().thePlayer;	    	
	    return Spell.hasSpell(stack) && Spell.getSpell(stack).getCoolDownRemaining(player) > 0;
    }
    
    public Side getSide() {
    	super.getSide();
    	
    	return Side.CLIENT;
    }
    
    
}

