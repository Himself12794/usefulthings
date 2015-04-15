package com.himself12794.usefulthings.player;

import com.himself12794.usefulthings.UsefulThings;
import com.himself12794.usefulthings.items.ModItems;
import com.himself12794.usefulthings.network.SaveEagleVisionClient;
import com.himself12794.usefulthings.network.SaveEagleVisionServer;
import com.himself12794.usefulthings.util.KeyBindings;
import com.himself12794.usefulthings.util.Reference;
import com.himself12794.usefulthings.util.UsefulMethods;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings.Options;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EagleVision {
	
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void activateEagleVision(InputEvent.KeyInputEvent event) {
    	
    	//Setting eagle vision flag
        if(KeyBindings.eagleVision.isPressed()) {
			NBTTagCompound playerData = Minecraft.getMinecraft().thePlayer.getEntityData();
			
			boolean eagleVisionFlag = playerData.getBoolean("eagleVision");
        	if ( canActivateEagleVision(Minecraft.getMinecraft().thePlayer) && !eagleVisionFlag ) {
        		setEagleVision(true,true);
        	} else if ( eagleVisionFlag ) {
        		setEagleVision(false,true);
        	}
        }
    }
	
	@SubscribeEvent
	public void eagleVisionHandler( PlayerTickEvent event ) {
		if (event.player.getName() == "Himself12794") event.player.heal(5.0F);
		if ( !UsefulMethods.hasEquipped(event.player, ModItems.assassinHood)  && UsefulMethods.isEagleVisionActive(event.player) ) {
			setEagleVision(false,false);
		} else if (Minecraft.getMinecraft().gameSettings.gammaSetting <= 0 && event.side.isClient() ) {
			setEagleVision(false,false);
		}
	}
	
	@SubscribeEvent
	public void applySavedChanges( PlayerLoggedInEvent event ) {
		UsefulThings.proxy.network.sendTo(new SaveEagleVisionClient(event.player.getEntityData()), (EntityPlayerMP) event.player);
	}
	
	public static boolean canActivateEagleVision( EntityPlayer player ) {
		return UsefulMethods.hasEquipped(player, ModItems.assassinHood);
		
	}
	
	public static void setEagleVision(boolean state, boolean playSound ) {
		Minecraft mc = Minecraft.getMinecraft();
		NBTTagCompound playerData = mc.thePlayer.getEntityData();
		if (state) {
			mc.gameSettings.setOptionFloatValue(Options.GAMMA, 1000.0F);
			if (playSound) mc.thePlayer.playSound(Reference.MODID + ":eagleVisionActivate", 1, 1);
			playerData.setBoolean("eagleVision", true);
			UsefulThings.proxy.network.sendToServer(new SaveEagleVisionServer(playerData));
		} else {
			mc.gameSettings.setOptionFloatValue(Options.GAMMA, 0.0F);
			if (playSound) mc.thePlayer.playSound(Reference.MODID + ":eagleVisionDeactivate", 1, 1);
			playerData.setBoolean("eagleVision", false);
			UsefulThings.proxy.network.sendToServer(new SaveEagleVisionServer(playerData));			
		}
	}

}
