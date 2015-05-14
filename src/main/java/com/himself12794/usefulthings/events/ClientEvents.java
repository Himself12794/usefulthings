package com.himself12794.usefulthings.events;

import com.himself12794.usefulthings.UsefulThings;
import com.himself12794.usefulthings.items.ModItems;
import com.himself12794.usefulthings.items.armor.AssassinBoots;
import com.himself12794.usefulthings.network.MessageServer;
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
	
	public ClientEvents() {
		this.mc = Minecraft.getMinecraft();
	}
	
	//Adds assassin boots jump boost
	@SubscribeEvent
	public void assassinBootsJumpBoost( LivingJumpEvent event ) {
        if (UsefulMethods.hasEquipped(event.entityLiving, ModItems.assassinBoots)) {
			event.entityLiving.motionY += ((AssassinBoots)ModItems.assassinBoots).jumpBoost;
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
