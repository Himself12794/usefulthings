package com.himself12794.usefulthings.events;

import com.himself12794.usefulthings.UsefulThings;
import com.himself12794.usefulthings.items.ModItems;
import com.himself12794.usefulthings.items.armor.AssassinBoots;
import com.himself12794.usefulthings.network.SaveEagleVisionServer;
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
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ClientEvents {
	
	private Minecraft mc;
	
	public ClientEvents( Minecraft mc ) {
		this.mc = mc;
	}
	
	//Adds assassin boots jump boost
	@SubscribeEvent
	public void assassinBootsJumpBoost( LivingJumpEvent event ) {
        if (UsefulMethods.hasEquipped(event.entityLiving, ModItems.assassinBoots)) {
			event.entityLiving.motionY += ((AssassinBoots)ModItems.assassinBoots).jumpBoost;
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
	
	/*@SubscribeEvent
	public void renderingStuff(EntityViewRenderEvent event) {
		//event.renderer.disableLightmap();
		//event.renderer.anaglyphField;
		//event.renderer.itemRenderer.
		System.out.println("The entity view render event is happening");
	}*/

}
