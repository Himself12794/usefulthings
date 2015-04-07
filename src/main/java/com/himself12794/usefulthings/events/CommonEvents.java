package com.himself12794.usefulthings.events;

import net.minecraft.block.Block;
import net.minecraft.client.settings.GameSettings.Options;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

import com.himself12794.usefulthings.Reference;
import com.himself12794.usefulthings.items.ModItems;
import com.himself12794.usefulthings.items.armor.AssassinBoots;

public class CommonEvents {
	
	@SubscribeEvent
	public void noHayFallDamage(LivingHurtEvent event) {
		if (event.source.getDamageType() == "fall") {
			Block block = Reference.getBlockAtPos(event.entityLiving.getPosition().down(),event.entityLiving.worldObj);
			if ( block == Blocks.hay_block) {
				event.setCanceled(true);
			}
		}
	}
	
	//Cleans up Strange Mirror Flying
	@SubscribeEvent
	public void strangeMirrorCancelFlying( net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent event ){
		if ( !event.player.inventory.hasItem(ModItems.strangeMirror) && !event.player.capabilities.isCreativeMode && event.player.capabilities.allowFlying) {
			event.player.capabilities.isFlying = false;
			event.player.capabilities.allowFlying = false;

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

}
