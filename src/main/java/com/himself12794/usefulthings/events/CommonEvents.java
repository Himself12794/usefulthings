package com.himself12794.usefulthings.events;

import net.minecraft.block.Block;
import net.minecraft.client.settings.GameSettings.Options;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
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
	
	/*
	@SubscribeEvent
	public void assassinArmorStealth( LivingSetAttackTargetEvent event ) {
		//System.out.println("someoe wants to attack someone");
		if ( event.target instanceof EntityPlayer ) {
			long time = MinecraftServer.getCurrentTimeMillis();
			float distance = event.entityLiving.getDistanceToEntity(event.target);
			if ( distance >= 8 ) {
				((EntityPlayer)event.target).setInvisible(true);
			} else {
				((EntityPlayer)event.target).setInvisible(false);
			}
			//if (event.isCancelable()) {event.setCanceled(true);System.out.println("they cannot see you!");}
		}
	}*/

}
