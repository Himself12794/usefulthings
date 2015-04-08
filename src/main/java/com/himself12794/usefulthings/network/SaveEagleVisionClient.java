package com.himself12794.usefulthings.network;

import com.himself12794.usefulthings.items.ModItems;
import com.himself12794.usefulthings.util.UsefulMethods;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;


public class SaveEagleVisionClient implements IMessage {
   
    private NBTTagCompound value;

    public SaveEagleVisionClient() { }

    public SaveEagleVisionClient(NBTTagCompound value) {
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

    public static class Handler implements IMessageHandler<SaveEagleVisionClient, IMessage> {
       
        @Override
        public IMessage onMessage(SaveEagleVisionClient message, MessageContext ctx) {
        	Minecraft mc = Minecraft.getMinecraft();
        	boolean isEagleVisionActivated = message.value.getBoolean("eagleVision");
        	if (/*UsefulMethods.canActivateEagleVision(mc.thePlayer) &&*/ isEagleVisionActivated ) {
        		UsefulMethods.setEagleVision(true,false);
        	}
        	else if (isEagleVisionActivated && !UsefulMethods.canActivateEagleVision(mc.thePlayer)) UsefulMethods.setEagleVision(false,false);
            return null;
        }
    }
}