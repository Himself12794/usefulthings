package com.himself12794.usefulthings.network;

import com.himself12794.usefulthings.items.ModItems;
import com.himself12794.usefulthings.player.EagleVision;
import com.himself12794.usefulthings.util.UsefulMethods;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;


public class MessageClient implements IMessage {
   
    private NBTTagCompound value;

    public MessageClient() { }

    public MessageClient(NBTTagCompound value) {
        this.value = value;
        //System.out.println("This is what's getting sent to the server: " + value.getBoolean("eagleVision"));
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        value = ByteBufUtils.readTag(buf); // this class is very useful in general for writing more complex objects
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, value);
    }

    public static class Handler implements IMessageHandler<MessageClient, IMessage> {
       
        @Override
        public IMessage onMessage(MessageClient message, MessageContext ctx) {
        	Minecraft mc = Minecraft.getMinecraft();
        	boolean isEagleVisionActivated = message.value.getBoolean("eagleVision");
        	boolean canUseEagleVision = message.value.getBoolean("canUseEagleVision");
        	EagleVision.setEagleVision(isEagleVisionActivated,false);
        	mc.thePlayer.getEntityData().setBoolean("canUseEagleVision", canUseEagleVision);
            return null;
        }
    }
}