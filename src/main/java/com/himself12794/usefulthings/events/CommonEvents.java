package com.himself12794.usefulthings.events;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings.Options;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

import com.himself12794.usefulthings.UsefulThings;
import com.himself12794.usefulthings.items.ModItems;
import com.himself12794.usefulthings.items.armor.AssassinBoots;
import com.himself12794.usefulthings.network.SaveEagleVisionClient;
import com.himself12794.usefulthings.network.SaveEagleVisionServer;
import com.himself12794.usefulthings.util.Reference;
import com.himself12794.usefulthings.util.UsefulMethods;

public class CommonEvents {
	
	@SubscribeEvent
	public void noHayFallDamage(LivingHurtEvent event) {
		if (event.source.getDamageType() == "fall") {
			Block block = UsefulMethods.getBlockAtPos(event.entityLiving.getPosition().down(),event.entityLiving.worldObj);
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
	
	@SubscribeEvent
	public void eagleVisionHandler( PlayerTickEvent event ) {
		NBTTagCompound playerData = event.player.getEntityData();
		boolean eagleVisionFlag = playerData.getBoolean("eagleVision");
		boolean hoodFlag = UsefulMethods.canActivateEagleVision(event.player);

		if ( !hoodFlag  && eagleVisionFlag ) {
			UsefulMethods.setEagleVision(false,false);
		}
	}
	
	@SubscribeEvent
	public void applySavedChanges( PlayerLoggedInEvent event ) {
		UsefulThings.proxy.network.sendTo(new SaveEagleVisionClient(event.player.getEntityData()), (EntityPlayerMP) event.player);
	}
	
	/*@SubscribeEvent
	public void loading(PlayerEvent.SaveToFile event) {
		System.out.println("saving data for " + event.entityPlayer.getName() + " " + event.entityPlayer.getEntityData().getBoolean("eagleVision"));
	}*/

}
