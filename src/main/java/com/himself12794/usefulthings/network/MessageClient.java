package com.himself12794.usefulthings.network;

import com.himself12794.usefulthings.events.EagleVision;
import com.himself12794.usefulthings.items.ModItems;
import com.himself12794.usefulthings.util.UsefulMethods;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
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
        	
        	//Eagle vision handler
        	if (message.value.getBoolean("activateEagleVision")) {
        		mc.thePlayer.getEntityData().setBoolean("canUseEagleVision", message.value.getBoolean("canUseEagleVision"));
        	}
        	
        	//Smoke cloud handler
        	if (message.value.getBoolean("doSmoke")) {
        		EntityPlayerSP p = mc.thePlayer;
        		mc.theWorld.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, p.posX, p.posY + (double)(p.height / 2.0F), p.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
        	}
            return null;
        }
    }
}