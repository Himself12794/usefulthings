package com.himself12794.usefulthings.network;

import com.himself12794.usefulthings.util.UsefulMethods;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;


public class SaveEagleVisionServer implements IMessage {
   
    private NBTTagCompound value;

    public SaveEagleVisionServer() { }

    public SaveEagleVisionServer(NBTTagCompound value) {
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

    public static class Handler implements IMessageHandler<SaveEagleVisionServer, IMessage> {
       
        @Override
        public IMessage onMessage(SaveEagleVisionServer message, MessageContext ctx) {
        	boolean isEagleVisionActivated = message.value.getBoolean("eagleVision");
        	EntityPlayer player = ctx.getServerHandler().playerEntity;
        	player.getEntityData().setBoolean("eagleVision", isEagleVisionActivated);
            return null; // no response in this case
        }
    }
}