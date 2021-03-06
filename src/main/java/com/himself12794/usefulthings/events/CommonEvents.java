package com.himself12794.usefulthings.events;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

import com.himself12794.usefulthings.UsefulThings;
import com.himself12794.usefulthings.items.AssassinArmor;
import com.himself12794.usefulthings.items.ModItems;
import com.himself12794.usefulthings.network.MessageClient;
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
	
	/*@SubscribeEvent
	public void applySavedChanges( PlayerLoggedInEvent event ) {
		UsefulThings.proxy.network.sendTo(new MessageClient(event.player.getEntityData()), (EntityPlayerMP) event.player);
	}*/
	
	@SubscribeEvent
	public void stealth(PlayerTickEvent event) {
		if (event.player.isSneaking() && !event.player.isInvisible() && UsefulMethods.isArmorSetEquipped(event.player, AssassinArmor.assassinMaterial))//UsefulMethods.hasEquipped(event.player, ModItems.assassinRobes))
			event.player.setInvisible(true);
		else if (!event.player.isSneaking() && event.player.isInvisible() && event.player.getActivePotionEffect(Potion.invisibility) == null )
			event.player.setInvisible(false);
	}
	
	/**
	 * Gives a player wearing assassin robes a 1/4 chance to dodge an enemy attack.
	 * Wearing the full assassin armor increases the chance to 1/2.
	 */
	@SubscribeEvent
	public void avoidDamage(LivingAttackEvent event) {
		EntityLivingBase dodger = event.entityLiving;
		boolean flag = event.source.isProjectile()
				|| "mob".equals(event.source.getDamageType())
				|| "player".equals(event.source.getDamageType());
		
		if (UsefulMethods.hasEquipped(dodger, ModItems.assassinRobes) && dodger instanceof EntityPlayerMP && flag) {
			
			int chance = 0;
			if (UsefulMethods.isArmorSetEquipped((EntityPlayer) dodger, AssassinArmor.assassinMaterial)) chance = event.entityLiving.worldObj.rand.nextInt(2);
			else chance = event.entityLiving.worldObj.rand.nextInt(4);
			
			if (chance == 0) {
				event.setCanceled(true);
				event.entityLiving.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, dodger.posX, dodger.posY + (double)(event.entityLiving.height / 2.0F), dodger.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
				NBTTagCompound data = new NBTTagCompound();
				data.setBoolean("doSmoke", true);
				UsefulThings.proxy.network.sendTo(new MessageClient(data), (EntityPlayerMP) dodger);
			}
		} //else if (event.entityLiving.getActivePotionEffect(Potion.resistance) != null) event.setCanceled(true);
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
