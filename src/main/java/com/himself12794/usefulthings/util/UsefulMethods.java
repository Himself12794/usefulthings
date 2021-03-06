package com.himself12794.usefulthings.util;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;

import com.himself12794.usefulthings.UsefulThings;

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
	
	public static MovingObjectPosition getEntityLookEntity(Entity theRenderViewEntity, float dist)
	{

	    AxisAlignedBB theViewBoundingBox = new AxisAlignedBB(
	            theRenderViewEntity.posX-0.5D,
	            theRenderViewEntity.posY-0.0D,
	            theRenderViewEntity.posZ-0.5D,
	            theRenderViewEntity.posX+0.5D,
	            theRenderViewEntity.posY+1.5D,
	            theRenderViewEntity.posZ+0.5D
	            );
	    MovingObjectPosition returnMOP = null;
	    if (theRenderViewEntity.worldObj != null)
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
	        List<Entity> list = theRenderViewEntity.worldObj.getEntitiesWithinAABBExcludingEntity(
	
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

	/*public static void lightningStrike(World world, EntityPlayer player, double x, double y, double z) {
		
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
		
	}*/
	
	public static ItemStack setStackOwner(ItemStack stack, Entity entity) {
		NBTTagCompound nbt = null;
    	int newOwnerId = entity.getEntityId();
    	
    	if (stack.getTagCompound() == null ) {
    		nbt = new NBTTagCompound();
    	} else nbt = stack.getTagCompound();
    	
    	int currentOwnerId = nbt.getInteger(Reference.MODID + ".spell.owner");
		if ( newOwnerId != currentOwnerId ) {
			UsefulThings.print("Stack owner for stack " + stack.toString() + " is now entity with id: " + newOwnerId);
			nbt.setInteger(Reference.MODID + ".spell.owner", newOwnerId);
			
		}
		
		stack.setTagCompound(nbt);
		return stack;
	}
	
	public static int getStackOwner(ItemStack stack) {
		if (stack.getTagCompound() != null) {
			return stack.getTagCompound().getInteger(Reference.MODID + ".spell.owner");
		}
		return 0;
	}
	
	public static BlockPos getBlockFromSide( BlockPos pos, EnumFacing side) {

		switch(side) {
		
		case DOWN: 	pos.up();
		case UP:	pos.down();
		case NORTH:	pos.south();
		case SOUTH:	pos.north();
		case EAST:  pos.west();
		case WEST:  pos.east();
		}
		
		return pos;
		
	}
}
