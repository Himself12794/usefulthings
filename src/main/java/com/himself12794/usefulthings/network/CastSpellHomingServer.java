package com.himself12794.usefulthings.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import com.himself12794.usefulthings.UsefulThings;
import com.himself12794.usefulthings.spell.Spell;
import com.himself12794.usefulthings.util.Reference;

public class CastSpellHomingServer implements IMessage {
	
	//private int id;
	//private String spell;
	//private ItemStack stack;
	//private NBTTagCompound modifier;
	private MovingObjectPosition target;
	private int entityId = -1;

    public CastSpellHomingServer() {  }
	
	public CastSpellHomingServer(MovingObjectPosition pos) {
		this.target = pos;
		if(pos.entityHit != null) entityId = pos.entityHit.getEntityId();
	}

	@Override
	public void fromBytes(ByteBuf buf) {
	    NBTTagCompound nbt = ByteBufUtils.readTag(buf);

    	int[] pos = nbt.getIntArray("blockPos");
    	BlockPos blockPos = new BlockPos(pos[0], pos[1], pos[2]);
    	
    	MovingObjectPosition.MovingObjectType typeOfHit = null;
    	byte type = nbt.getByte("typeOfHit");
    	switch (type) {
    	case 1: typeOfHit = MovingObjectPosition.MovingObjectType.ENTITY;
    	case 2: typeOfHit = MovingObjectPosition.MovingObjectType.BLOCK;
    	case 0: typeOfHit = MovingObjectPosition.MovingObjectType.MISS;
    	}
    	
        EnumFacing sideHit = null;
        byte side = nbt.getByte("sideHit");
        switch (side) {
        case 0:	sideHit = EnumFacing.UP;
        case 1:	sideHit = EnumFacing.DOWN;
        case 12:sideHit = EnumFacing.NORTH;
        case 6:	sideHit = EnumFacing.SOUTH;
        case 3:	sideHit = EnumFacing.EAST;
        case 9:	sideHit = EnumFacing.WEST;
        }
        
    	
    	NBTTagCompound coords = (NBTTagCompound) nbt.getTag("hitVec");
    	double x = coords.getDouble("x");
    	double y = coords.getDouble("y");
    	double z = coords.getDouble("z");
    	Vec3 hitVec = new Vec3(x, y, z);
    	
    	target = new MovingObjectPosition(typeOfHit, hitVec, sideHit, blockPos);
    	entityId = nbt.getInteger("entityHit");
    	
	}

	@Override
	public void toBytes(ByteBuf buf) {
		
		NBTTagCompound nbt = new NBTTagCompound();
	    {
	    	BlockPos blockPos = target.getBlockPos();
	    	int[] pos = {blockPos.getX(), blockPos.getY(), blockPos.getZ()};
	    	nbt.setIntArray("blockPos", pos);
	    	
	    	MovingObjectPosition.MovingObjectType typeOfHit = target.typeOfHit;
	    	byte type = 0;
	    	switch (typeOfHit) {
	    	case ENTITY: type = 1;
	    	case BLOCK: type = 2;
	    	case MISS: type = 0;
	    	}
	    	nbt.setByte("typeOfHit", type);
	    	

	        EnumFacing sideHit = target.sideHit;
	        byte side = 0;
	        switch (sideHit) {
	        case UP:	side = 0;
	        case DOWN:	side = 1;
	        case NORTH:	side = 12;
	        case SOUTH:	side = 6;
	        case EAST:	side = 3;
	        case WEST:	side = 9;
	        }
	        nbt.setByte("sideHit", side);
	    	
	    	Vec3 hitVec = target.hitVec;
	    	NBTTagCompound coords = new NBTTagCompound();
	    	coords.setDouble("x", hitVec.xCoord);
	    	coords.setDouble("y", hitVec.yCoord);
	    	coords.setDouble("z", hitVec.zCoord);
	    	nbt.setTag("hitVec", coords);
	    	
	    	nbt.setInteger("entityHit", entityId);
	    }
	    
	    ByteBufUtils.writeTag(buf, nbt);

	}
	
	public static class Handler implements IMessageHandler<CastSpellHomingServer, IMessage> {
    	
		String prefix = Reference.MODID + ".";
       
        @Override
        public IMessage onMessage(CastSpellHomingServer message, MessageContext ctx) {
        	
        	if (ctx.side.isServer()) {
        		
        		UsefulThings.print("Got message from client to cast a spell");
        		MovingObjectPosition target = message.target;
        		Entity entityHit = message.entityId == -1 ? null : ctx.getServerHandler().playerEntity.worldObj.getEntityByID(message.entityId);
        		target.entityHit = entityHit;
        		
        		ServerMessageQueue.MovingObjectsQueue.queuePosition(target);
        		
        	}
        	
        	return null;
        }
	}
}
