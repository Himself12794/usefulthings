package com.himself12794.usefulthings.network;

import com.himself12794.usefulthings.util.UsefulMethods;

import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;


public class MessageServer implements IMessage {
   
    private NBTTagCompound value;

    public MessageServer() { }

    public MessageServer(NBTTagCompound value) {
        this.value = value;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        value = ByteBufUtils.readTag(buf); // this class is very useful in general for writing more complex objects
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, value);
    }

    public static class Handler implements IMessageHandler<MessageServer, IMessage> {
       
        @Override
        public IMessage onMessage(MessageServer message, MessageContext ctx) {
        	if (message.value.getBoolean("lightning")) {
        		double x = message.value.getDouble("x");
        		double y = message.value.getDouble("y");
        		double z = message.value.getDouble("z");
        		EntityLightningBolt bolt = new EntityLightningBolt(ctx.getServerHandler().playerEntity.worldObj, x, y, z);
        		bolt.getEntityData().setString("shooter", ctx.getServerHandler().playerEntity.getUniqueID().toString());
        		System.out.println("fired by id: " + bolt.getEntityData().getString("shooter"));
        		ctx.getServerHandler().playerEntity.worldObj.addWeatherEffect(bolt);
        	}
        	
        	if (message.value.getBoolean("incinerate")) {
        		System.out.println("incinerate!");
        		double x = message.value.getDouble("x");
        		double y = message.value.getDouble("y");
        		double z = message.value.getDouble("z");
        		EntitySmallFireball incinerate = new EntitySmallFireball(ctx.getServerHandler().playerEntity.worldObj);
        		incinerate.setPosition(x, y, z);
        		ctx.getServerHandler().playerEntity.worldObj.spawnEntityInWorld(incinerate);
        		
        	}
        	boolean isEagleVisionActivated = message.value.getBoolean("eagleVision");
	        if (isEagleVisionActivated) {
	        	EntityPlayer player = ctx.getServerHandler().playerEntity;
	        	player.getEntityData().setBoolean("eagleVision", isEagleVisionActivated);
        	}
            return null; // no response in this case
        }
    }
}