/*package com.himself12794.usefulthings;

import java.util.List;
import java.util.ListIterator;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings.Options;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.MathHelper;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.RenderPlayerEvent.Post;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.himself12794.usefulthings.Reference;
import com.himself12794.usefulthings.blocks.ModBlocks;
import com.himself12794.usefulthings.items.ModItems;
import com.himself12794.usefulthings.items.armor.AssassinBoots;

import net.minecraftforge.event.terraingen.OreGenEvent;

public class UsefulThingsEventHandler {
	
	private Minecraft mc;
	
	public UsefulThingsEventHandler( Minecraft mc) {
		this.mc = mc;
	}
	
	@SubscribeEvent
	//@SideONly(Side.CLIENT)
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
	//@SideOnly(Side.CLIENT)
	public void strangeMirrorCancelFlying( net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent event ){
		if ( !event.player.inventory.hasItem(ModItems.strangeMirror) && !event.player.capabilities.isCreativeMode && event.player.capabilities.allowFlying) {
			event.player.capabilities.isFlying = false;
			event.player.capabilities.allowFlying = false;

		}
		
	}
	
	//Adds assassin boots jump boost
	@SubscribeEvent
	//@SideOnly(Side.CLIENT)
	public void assassinBootsJumpBoost( LivingJumpEvent event ) {
		if ( event.entityLiving instanceof EntityPlayer ) {
	        ItemStack[] aitemstack = event.entityLiving.getInventory();
	        if (aitemstack[0] != null && aitemstack[0].getItem() == ModItems.assassinBoots) {
				event.entityLiving.motionY += ((AssassinBoots)ModItems.assassinBoots).jumpBoost;
	        }

		}
	}
	
	@SubscribeEvent
	//@SideOnly(Side.CLIENT)
	public void assassinHoodEagleVisionDisable( PlayerTickEvent event ) {
			boolean flag = event.player.inventory.armorItemInSlot(3) == null ? false : event.player.inventory.armorItemInSlot(3).getItem() == ModItems.assassinHood;
			
			if ( !flag && event.player.getActivePotionEffect(Potion.nightVision) == null && mc.gameSettings.gammaSetting >= 10000.0F) {
				mc.gameSettings.setOptionFloatValue(Options.GAMMA, 0.0F);
			}
				
		
	}
	
    @SubscribeEvent
    //@SideOnly(Side.CLIENT)
    public void onKeyInput(InputEvent.KeyInputEvent event) {
    	
        if(KeyBindings.eagleVision.isPressed()) {
			boolean hoodFlag = mc.thePlayer.inventory.armorItemInSlot(3) == null ? false : mc.thePlayer.inventory.armorItemInSlot(3).getItem() == ModItems.assassinHood;
			boolean potionFlag = mc.thePlayer.getActivePotionEffect(Potion.nightVision) == null;
        	if (hoodFlag && potionFlag && mc.gameSettings.gammaSetting < 10000.0F ) {
				mc.gameSettings.setOptionFloatValue(Options.GAMMA, 10000.0F);
        	} else if (mc.gameSettings.gammaSetting >= 10000.0F) {
        		mc.gameSettings.setOptionFloatValue(Options.GAMMA, 0.0F);
        	}
        }
    }
}*/
