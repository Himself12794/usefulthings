package com.himself12794.usefulthings.util;

import com.himself12794.usefulthings.UsefulThings;
import com.himself12794.usefulthings.items.ModItems;
import com.himself12794.usefulthings.network.SaveEagleVisionServer;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings.Options;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class UsefulMethods {

    /**
     * Gets block at world position.
     *  
     * @param pos The position of the block
     * @param worldIn The world where the block is located
     * @return The block at location {@code pos} in world {@code worldIn}
     */    
    public static Block getBlockAtPos(BlockPos pos, World worldIn ){
    	
        IBlockState iblockstate1 = worldIn.getBlockState(pos);
    	return iblockstate1.getBlock();
    }
    
    /**
     * Activates eagle vision for the specified player
     * 
     * @param mc The minecraft instance to which we are applying this
     */
    
    public static void activateEagleVision( Minecraft mc ) {
    	
    	NBTTagCompound playerData = mc.thePlayer.getEntityData();
    	mc.thePlayer.getEntityData().setBoolean("eagleVision", playerData.getBoolean("eagleVision"));
		mc.gameSettings.setOptionFloatValue(Options.GAMMA, 1000.0F);
		mc.thePlayer.playSound(Reference.MODID + ":eagleVisionActivate", 1, 1);
		playerData.setBoolean("eagleVision", true);
		UsefulThings.proxy.network.sendToServer(new SaveEagleVisionServer(playerData));
		//mc.thePlayer.writeToNBT(playerData);
    	
    }

	/**
	 * Deactivates eagle vision for the specified player
	 * 
	 * @param mc The minecraft instance to which we are applying this
	 */
	
	public static void deactivateEagleVision( Minecraft mc ) {
		
		NBTTagCompound playerData = mc.thePlayer.getEntityData();
		mc.gameSettings.setOptionFloatValue(Options.GAMMA, 0.0F);
		mc.thePlayer.playSound(Reference.MODID + ":eagleVisionDeactivate", 1, 1);
		playerData.setBoolean("eagleVision", false);
		UsefulThings.proxy.network.sendToServer(new SaveEagleVisionServer(playerData));
		//mc.thePlayer.writeToNBT(playerData);
		
	}
	
	public static boolean canActivateEagleVision( EntityPlayer player ) {
		return (Boolean) (player.inventory.armorItemInSlot(0) == null ? false : player.inventory.armorItemInSlot(3).getItem() == ModItems.assassinHood);
		
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
