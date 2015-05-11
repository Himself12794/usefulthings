package com.himself12794.usefulthings.events;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings.Options;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.EnumSkyBlock;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.himself12794.usefulthings.UsefulThings;
import com.himself12794.usefulthings.items.AssassinArmor;
import com.himself12794.usefulthings.items.ModItems;
import com.himself12794.usefulthings.items.armor.AssassinBoots;
import com.himself12794.usefulthings.network.MessageClient;
import com.himself12794.usefulthings.network.MessageServer;
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
	
	@SubscribeEvent
	public void applySavedChanges( PlayerLoggedInEvent event ) {
		UsefulThings.proxy.network.sendTo(new MessageClient(event.player.getEntityData()), (EntityPlayerMP) event.player);
	}
	
	
	
	/*//Cleans up Strange Mirror Flying
	@SubscribeEvent
	public void strangeMirrorCancelFlying( PlayerTickEvent event ){
		if ( !event.player.inventory.hasItem(ModItems.strangeMirror) && !event.player.capabilities.isCreativeMode && !event.player.capabilities.&& event.player.capabilities.allowFlying) {
			event.player.capabilities.isFlying = false;
			event.player.capabilities.allowFlying = false;

		}
		
	}*/
	
	@SubscribeEvent
	public void stealth(PlayerTickEvent event) {
		if (event.player.isSneaking() && !event.player.isInvisible() && UsefulMethods.isArmorSetEquipped(event.player, AssassinArmor.assassinMaterial))//UsefulMethods.hasEquipped(event.player, ModItems.assassinRobes))
			event.player.setInvisible(true);
		else if (!event.player.isSneaking() && event.player.isInvisible() && event.player.getActivePotionEffect(Potion.invisibility) == null )
			event.player.setInvisible(false);
	}
	
	/**
	 * Gives an entity wearing assassin robes a chance to dodge an enemy attack,
	 *  or to confuse an enemy, preventing them from attacking at all.
	 */
	@SubscribeEvent
	public void avoidDamage(LivingAttackEvent event) {
		EntityLivingBase dodger = event.entityLiving;
		if (UsefulMethods.hasEquipped(dodger, ModItems.assassinRobes)) {
			//if (!event.entityLiving.worldObj.isRemote)
			System.out.println("you are at: x:" + dodger.posX + " y:" + dodger.posY + " z:" + dodger.posZ);
			event.entityLiving.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, dodger.posX, dodger.posY /*+ (double)(event.entityLiving.height / 2.0F)*/, dodger.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
			//System.out.println("Smoke should have appeared");
			int noDamageChance = event.entityLiving.worldObj.rand.nextInt(4);
			noDamageChance = 1;
			if (noDamageChance == 1) {
				event.setCanceled(true);
				//if (event.entityLiving.worldObj.isRemote)
				//	event.entityLiving.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, event.entityLiving.posX, event.entityLiving.posY+1, event.entityLiving.posZ, 0.0D, 0.0D, 0.0D);
			}
		}
	}
	
	@SubscribeEvent
	public void usePoweredOilToTame( EntityInteractEvent event ) {
		if (event.target instanceof EntityWolf ) {
			EntityWolf wolf = (EntityWolf) event.target;
			if (UsefulMethods.isCurrentItem(event.entityPlayer, ModItems.poweredOil) && !wolf.worldObj.isRemote && !wolf.isTamed()) {
                if (wolf.worldObj.rand.nextInt(3) == 0)
                {
                    wolf.setTamed(true);
                    wolf.getNavigator().clearPathEntity();
                    wolf.setAttackTarget((EntityLivingBase)null);
                    wolf.setSitting(true);
                    wolf.setHealth(20.0F);
                    wolf.setOwnerId(event.entityPlayer.getUniqueID().toString());
                    UsefulMethods.playTameEffect(true, wolf);
                    wolf.worldObj.setEntityState(wolf, (byte)7);
                }
                else
                {
                    UsefulMethods.playTameEffect(false,wolf);
                    wolf.worldObj.setEntityState(wolf, (byte)6);
                }
                UsefulMethods.removeOneCurrentItem(event.entityPlayer);
            }
		}
	}

}
