package com.himself12794.usefulthings.events;

import com.himself12794.usefulthings.KeyBindings;
import com.himself12794.usefulthings.Reference;
import com.himself12794.usefulthings.items.ModItems;
import com.himself12794.usefulthings.items.armor.AssassinBoots;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings.Options;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
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
			boolean flag = event.player.inventory.armorItemInSlot(3) == null ? false : event.player.inventory.armorItemInSlot(3).getItem() == ModItems.assassinHood;
			
			if ( !flag && event.player.getActivePotionEffect(Potion.nightVision) == null && mc.gameSettings.gammaSetting >= 10000.0F) {
				mc.gameSettings.setOptionFloatValue(Options.GAMMA, 0.0F);
			}
				
		
	}
	
    @SubscribeEvent
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

}
