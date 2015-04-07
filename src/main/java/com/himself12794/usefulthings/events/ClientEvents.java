package com.himself12794.usefulthings.events;

import com.himself12794.usefulthings.items.ModItems;
import com.himself12794.usefulthings.items.armor.AssassinBoots;
import com.himself12794.usefulthings.util.KeyBindings;
import com.himself12794.usefulthings.util.Reference;
import com.himself12794.usefulthings.util.UsefulMethods;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings.Options;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class ClientEvents extends CommonEvents {
	
	private Minecraft mc;
	
	public ClientEvents( Minecraft mc ) {
		this.mc = mc;
	}
	
	@SubscribeEvent
	public void assassinHoodEagleVisionDisable( PlayerTickEvent event ) {
		NBTTagCompound playerData = event.player.getEntityData();
		boolean eagleVisionFlag = playerData.getBoolean("eagleVision"); 
		boolean hoodFlag = event.player.inventory.armorItemInSlot(3) == null ? false : event.player.inventory.armorItemInSlot(3).getItem() == ModItems.assassinHood;

		if ( !hoodFlag  && eagleVisionFlag ) {
			UsefulMethods.deactivateEagleVision(mc);
		}
			//blah blah
	}
	
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
    	
    	//Setting eagle vision flag
        if(KeyBindings.eagleVision.isPressed()) {
			NBTTagCompound playerData = mc.thePlayer.getEntityData(); 
			boolean hoodFlag = mc.thePlayer.inventory.armorItemInSlot(3) == null ? false : mc.thePlayer.inventory.armorItemInSlot(3).getItem() == ModItems.assassinHood;
			boolean eagleVisionFlag = playerData.getBoolean("eagleVision");
        	if ( hoodFlag && !eagleVisionFlag ) {
        		UsefulMethods.activateEagleVision(mc);
        	} else if ( eagleVisionFlag ) {
        		UsefulMethods.deactivateEagleVision(mc);
        	}
        }
    }
	
	//Adds assassin boots jump boost
	@SubscribeEvent
	public void assassinBootsJumpBoost( LivingJumpEvent event ) {
		if ( event.entityLiving instanceof EntityPlayer ) {
	        ItemStack[] aitemstack = event.entityLiving.getInventory();
	        if (aitemstack[0] != null && aitemstack[0].getItem() == ModItems.assassinBoots) {
				event.entityLiving.motionY += ((AssassinBoots)ModItems.assassinBoots).jumpBoost;
	        }

		}
	}
	
	//Adds crash cushion for assassin boots
	@SubscribeEvent
	public void assassinBootsCushionFall( LivingFallEvent event ) {
		if ( event.entityLiving instanceof EntityPlayer ) {
			if (((EntityPlayer)event.entityLiving).inventory.armorItemInSlot(0) == null ? false : ((EntityPlayer)event.entityLiving).inventory.armorItemInSlot(0).getItem() == ModItems.assassinBoots) {
			
				if ( event.distance <= 5.0F ) event.distance = 0.0F;
				if ( event.distance > 5.0F ){
					event.distance -= 2.0F;
					event.damageMultiplier *= 0.5F;
				}
			}
		}
	}

}
