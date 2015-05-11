package com.himself12794.usefulthings.events;

import com.himself12794.usefulthings.UsefulThings;
import com.himself12794.usefulthings.items.ModItems;
import com.himself12794.usefulthings.network.MessageClient;
import com.himself12794.usefulthings.network.MessageServer;
import com.himself12794.usefulthings.util.KeyBindings;
import com.himself12794.usefulthings.util.Reference;
import com.himself12794.usefulthings.util.UsefulMethods;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings.Options;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EagleVision {
    
    public static void init(){
    	MinecraftForge.EVENT_BUS.register(new EagleVision());
    	FMLCommonHandler.instance().bus().register(new EagleVision());    	
    }
	
	/**
	 * Allows eagle vision to be used for the specified player
	 * 
	 * @param player
	 */

	public static void allowEagleVision(EntityPlayer player) {
		//System.out.println("Remote: " + player.worldObj.isRemote);
		player.getEntityData().setBoolean("canUseEagleVision", true);
		//UsefulThings.proxy.network.sendTo(new SaveEagleVisionClient(player.getEntityData()), (EntityPlayerMP) player);
		
	}
	
    @SubscribeEvent
   // @SideOnly(Side.CLIENT)
    public void activateEagleVision(InputEvent.KeyInputEvent event) {
    	//Setting eagle vision flag
    	try {
        if(KeyBindings.eagleVision.isPressed()) {
			NBTTagCompound playerData = Minecraft.getMinecraft().thePlayer.getEntityData();
			//System.out.println(canActivateEagleVision(Minecraft.getMinecraft().thePlayer));
			
			boolean eagleVisionFlag = playerData.getBoolean("eagleVision");
			System.out.println(eagleVisionFlag);
        	if ( !eagleVisionFlag ) {
        		//setEagleVision(true,true);
        		playerData.setBoolean("eagleVision", true);
        	} else {
        		//setEagleVision(false,true);
        		playerData.setBoolean("eagleVision", false);
        	}
        }
    	} catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
    }
	
	@SubscribeEvent
	public void eagleVisionHandler( EntityViewRenderEvent.FogDensity event) {
		
		//System.out.println(event.density);
		if (Minecraft.getMinecraft().thePlayer.getEntityData().getBoolean("eagleVision")) {
			event.density = 0.5F;
			event.setCanceled(true);
		}
	}
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void eagleVisionHandler( EntityViewRenderEvent.FogColors event ) {

		
		//System.out.println("R:" + event.red + " G:" +event.green + " B:" + event.blue);
		if (Minecraft.getMinecraft().thePlayer.getEntityData().getBoolean("eagleVision")) {
			event.blue = 1.0F;
			event.green = 1.0F;
			event.red = 0.0F;
			//Minecraft.getMinecraft().gameSettings.gammaSetting = 750.0F;//setOptionFloatValue(Options.GAMMA, 750.0F);
		}

		//System.out.println("R:" + event.red + " G:" +event.green + " B:" + event.blue);
		//if (event.player.getName() == "Himself12794") event.player.heal(5.0F);
		/*if ( !EagleVision.canActivateEagleVision(event.player)  && UsefulMethods.isEagleVisionActive(event.player) ) {
			setEagleVision(false,false);
		} else if (Minecraft.getMinecraft().gameSettings.gammaSetting <= 0 && event.side.isClient() ) {
			setEagleVision(false,false);
		}*/
	}
	
	/*public static boolean canActivateEagleVision( EntityPlayer player ) {
		/*return UsefulMethods.hasEquipped(player, ModItems.assassinHood);
		return player.getEntityData().getBoolean("canUseEagleVision");
		
	}
	
	public static void setEagleVision(boolean state, boolean playSound ) {
		Minecraft mc = Minecraft.getMinecraft();
		NBTTagCompound playerData = mc.thePlayer.getEntityData();
		if (state) {
			mc.gameSettings.setOptionFloatValue(Options.GAMMA, 1000.0F);
			if (playSound) mc.thePlayer.playSound(Reference.MODID + ":eagleVisionActivate", 1, 1);
			playerData.setBoolean("eagleVision", true);
			UsefulThings.proxy.network.sendToServer(new MessageServer(playerData));
		} else {
			mc.gameSettings.setOptionFloatValue(Options.GAMMA, 0.0F);
			if (playSound) mc.thePlayer.playSound(Reference.MODID + ":eagleVisionDeactivate", 1, 1);
			playerData.setBoolean("eagleVision", false);
			UsefulThings.proxy.network.sendToServer(new MessageServer(playerData));			
		}
	}*/

}
