package com.himself12794.usefulthings.events;

import net.minecraft.client.Minecraft;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.himself12794.usefulthings.items.ModItems;
import com.himself12794.usefulthings.items.armor.AssassinBoots;
import com.himself12794.usefulthings.util.UsefulMethods;

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

}
