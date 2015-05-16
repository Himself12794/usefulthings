package com.himself12794.usefulthings.util;

import java.util.List;

import com.himself12794.usefulthings.UsefulThings;
import com.himself12794.usefulthings.items.ModItems;
import com.himself12794.usefulthings.items.armor.AssassinBoots;
import com.himself12794.usefulthings.network.MessageClient;
import com.himself12794.usefulthings.network.MessageServer;
import com.himself12794.usefulthings.spells.BuffSpells;
import com.himself12794.usefulthings.spells.DamagingSpells;
import com.himself12794.usefulthings.spells.SpellRegistry;
import com.himself12794.usefulthings.spells.UnregisteredSpellException;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.settings.GameSettings.Options;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;

public class UsefulMethods {

    /**
     * Gets block at world position.
     *  
     * @param pos The position of the block
     * @param worldIn The world where the block is located
     * @return The block at location {@code pos} in world {@code worldIn}
     */    
    public static Block getBlockAtPos(BlockPos pos, World worldIn ){
    	
        return worldIn.getBlockState(pos).getBlock();
    }
    
   /* /**
     * Activates eagle vision for the specified player
     * 
     * @param mc The minecraft instance to which we are applying this
     *
    
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
	 *
	
	public static void deactivateEagleVision( Minecraft mc ) {
		
		NBTTagCompound playerData = mc.thePlayer.getEntityData();
		mc.gameSettings.setOptionFloatValue(Options.GAMMA, 0.0F);
		mc.thePlayer.playSound(Reference.MODID + ":eagleVisionDeactivate", 1, 1);
		playerData.setBoolean("eagleVision", false);
		UsefulThings.proxy.network.sendToServer(new SaveEagleVisionServer(playerData));
		//mc.thePlayer.writeToNBT(playerData);
		
	}*/
	
	/*public static void setEagleVision(boolean state, boolean playSound ) {
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		if (state) {
			player.getEntityData().setBoolean("eagleVision", true);
			if (playSound) player.playSound(Reference.MODID + ":eagleVisionActivate", 1, 1);
			if (player.worldObj.isRemote) UsefulThings.proxy.network.sendToServer(new SaveEagleVisionServer(player.getEntityData())); 
		} else {
			player.getEntityData().setBoolean("eagleVision", false);
			if (playSound) player.playSound(Reference.MODID + ":eagleVisionActivate", 1, 1);
			if (player.worldObj.isRemote) UsefulThings.proxy.network.sendToServer(new SaveEagleVisionServer(player.getEntityData())); 
		}
	}*/
	
	public static void playTameEffect(boolean outcome, Entity entity) {
        EnumParticleTypes enumparticletypes = EnumParticleTypes.HEART;

        if (!outcome)
        {
            enumparticletypes = EnumParticleTypes.SMOKE_NORMAL;
        }

        for (int i = 0; i < 7; ++i)
        {
            double d0 = entity.worldObj.rand.nextGaussian() * 0.02D;
            double d1 = entity.worldObj.rand.nextGaussian() * 0.02D;
            double d2 = entity.worldObj.rand.nextGaussian() * 0.02D;
            entity.worldObj.spawnParticle(enumparticletypes, entity.posX + (double)(entity.worldObj.rand.nextFloat() * entity.width * 2.0F) - (double)entity.width, entity.posY + 0.5D + (double)(entity.worldObj.rand.nextFloat() * entity.height), entity.posZ + (double)(entity.worldObj.rand.nextFloat() * entity.width * 2.0F) - (double)entity.width, d0, d1, d2, new int[0]);
        }
	}
	
	public static void removeOneCurrentItem(EntityPlayer player) {
		ItemStack itemstack = player.getItemInUse();
        if (!player.capabilities.isCreativeMode && player.worldObj.isRemote)
        {
            --itemstack.stackSize;
        }

        if (itemstack.stackSize <= 0)
        {
            player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
        }
	}
	
	public static boolean isCurrentItem(EntityPlayer player, Item item ){
		return player.inventory.getCurrentItem() != null ? player.inventory.getCurrentItem().getItem() == item : false;
	}
	
	public static boolean hasEquipped(EntityLivingBase entity, Item item){
        ItemStack[] aitemstack = entity.getInventory();
        for (int i = 0; i < aitemstack.length; i++)
	        if (aitemstack[i] != null && aitemstack[i].getItem() == item && aitemstack[i].getItem() instanceof ItemArmor) {
	        	return true;
	        }
		return false;
	}
	
	public static boolean isEagleVisionActive(EntityPlayer player ) {
		return player.getEntityData().getBoolean("eagleVision");
	}
	
	public static MovingObjectPosition getMouseOverExtended(float dist)
	{
	    Minecraft mc = FMLClientHandler.instance().getClient();
	    Entity theRenderViewEntity = mc.getRenderViewEntity();
	    AxisAlignedBB theViewBoundingBox = new AxisAlignedBB(
	            theRenderViewEntity.posX-0.5D,
	            theRenderViewEntity.posY-0.0D,
	            theRenderViewEntity.posZ-0.5D,
	            theRenderViewEntity.posX+0.5D,
	            theRenderViewEntity.posY+1.5D,
	            theRenderViewEntity.posZ+0.5D
	            );
	    MovingObjectPosition returnMOP = null;
	    if (mc.theWorld != null)
	    {
	        double var2 = dist;
	        returnMOP = theRenderViewEntity.rayTrace(var2, 0);
	        double calcdist = var2;
	        Vec3 pos = theRenderViewEntity.getPositionEyes(0);
	        var2 = calcdist;
	        if (returnMOP != null)
	        {
	            calcdist = returnMOP.hitVec.distanceTo(pos);
	        }
	         
	        Vec3 lookvec = theRenderViewEntity.getLook(0);
	        Vec3 var8 = pos.addVector(lookvec.xCoord * var2, 
	
	              lookvec.yCoord * var2, 
	
	              lookvec.zCoord * var2);
	        Entity pointedEntity = null;
	        float var9 = 1.0F;
	        @SuppressWarnings("unchecked")
	        List<Entity> list = mc.theWorld.getEntitiesWithinAABBExcludingEntity(
	
	              theRenderViewEntity, 
	
	              theViewBoundingBox.addCoord(
	
	                    lookvec.xCoord * var2, 
	
	                    lookvec.yCoord * var2, 
	
	                    lookvec.zCoord * var2).expand(var9, var9, var9));
	        double d = calcdist;
	            
	        for (Entity entity : list)
	        {
	            if (entity.canBeCollidedWith())
	            {
	                float bordersize = entity.getCollisionBorderSize();
	                AxisAlignedBB aabb = new AxisAlignedBB(
	
	                      entity.posX-entity.width/2, 
	
	                      entity.posY, 
	
	                      entity.posZ-entity.width/2, 
	
	                      entity.posX+entity.width/2, 
	
	                      entity.posY+entity.height, 
	
	                      entity.posZ+entity.width/2);
	                aabb.expand(bordersize, bordersize, bordersize);
	                MovingObjectPosition mop0 = aabb.calculateIntercept(pos, var8);
	                    
	                if (aabb.isVecInside(pos))
	                {
	                    if (0.0D < d || d == 0.0D)
	                    {
	                        pointedEntity = entity;
	                        d = 0.0D;
	                    }
	                } else if (mop0 != null)
	                {
	                    double d1 = pos.distanceTo(mop0.hitVec);
	                        
	                    if (d1 < d || d == 0.0D)
	                    {
	                        pointedEntity = entity;
	                        d = d1;
	                    }
	                }
	            }
	        }
	           
	        if (pointedEntity != null && (d < calcdist || returnMOP == null))
	        {
	             returnMOP = new MovingObjectPosition(pointedEntity);
	        }
	
	    }
	    return returnMOP;
	}
	
	public static boolean isArmorSetEquipped( EntityPlayer player, ArmorMaterial material ){
		
		ItemStack[] armor = player.inventory.armorInventory;
		
		for (int i = 0; i < armor.length; i++) {
			if (armor[i] == null )
				return false;
			else if (((ItemArmor) armor[i].getItem()).getArmorMaterial() != material)
				return false;
		}
		
		return true;
	}

	public static void lightningStrike(World world, EntityPlayer player, double x, double y, double z) {
		
		EntityLightningBolt bolt = new EntityLightningBolt(world, x, y, z);
		bolt.getEntityData().setString("shooter", player.getUniqueID().toString());
		world.addWeatherEffect(bolt);				
		NBTTagCompound msg = new NBTTagCompound();
		msg.setBoolean("lightning", true);
		msg.setDouble("x", x);
		msg.setDouble("y", y);
		msg.setDouble("z", z);
		
		if (world.isRemote){
			UsefulThings.proxy.network.sendToServer(new MessageServer(msg));
		} else {
			//UsefulThings.proxy.network.sendTo(new MessageClient(msg), (EntityPlayerMP) player);
		}
		
	}
	
	public static boolean hasSpell(ItemStack tome) {
		/*if (tome.hasTagCompound()) {
			if (tome.getTagCompound().hasKey("spell"))
		}*/
    	return tome.hasTagCompound() ? (tome.getTagCompound().hasKey("spell")) : false; 
	}
	
	public static boolean hasSpellName(ItemStack tome, String name) {
		boolean flag = false;
		if (hasSpell(tome)) {
			flag = getSpellName(tome) == name; 
		}
		return flag;
	}
	
	public static ItemStack setSpell(ItemStack stack, String spell) {
		NBTTagCompound nbt = null;
		if (!stack.hasTagCompound()) {
			nbt = new NBTTagCompound();
		} else {
			nbt = stack.getTagCompound();
		}
		if (SpellRegistry.lookupSpell(spell) == null) {
			try {
				throw new UnregisteredSpellException("Spell by name \"" + spell + "\" does not exist in registry" );
			} catch (UnregisteredSpellException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Setting spell to " + spell);
			nbt.setString("spell", spell);
			stack.setTagCompound(nbt);
		}
		return stack;
	}
	
	public static String getSpellName(ItemStack tome) {
		String spell = null;
		if (hasSpell(tome)) {
			spell = tome.getTagCompound().getString("spell");
		}
		return spell;
		
	}
}
