package com.himself12794.usefulthings.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import com.himself12794.usefulthings.UsefulThings;
import com.himself12794.usefulthings.util.Reference;

public class CastSpellClient implements IMessage {
	
	private int entityId;
	private boolean success;

    public CastSpellClient() { }
    
    public CastSpellClient(boolean success, int id) {
    	this.success = success;
    	this.entityId = id;
    }
	
	@Override
	public void fromBytes(ByteBuf buf) {
		
		entityId = ByteBufUtils.readVarInt(buf, 4);
		success = ByteBufUtils.readVarInt(buf, 1) == 1 ? true : false;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		
		ByteBufUtils.writeVarInt(buf, entityId, 4);
		ByteBufUtils.writeVarInt(buf, success ? 1 : 0, 1);

	}
	
	public static class Handler implements IMessageHandler<CastSpellClient, IMessage> {
    	
		String prefix = Reference.MODID + ".";
       
        @Override
        public IMessage onMessage(CastSpellClient message, MessageContext ctx) {
        	
        	if (ctx.side.isClient()) {
        		Minecraft mc = Minecraft.getMinecraft();
        		int storedId = mc.thePlayer.getEntityData().getInteger(Reference.MODID + ".spell.target");
        		UsefulThings.print("Stored id: " + storedId + ", Recieved id: " + message.entityId);
        		int success = (storedId == message.entityId ? 1 : 2);
        		mc.thePlayer.getEntityData().setInteger(Reference.MODID + ".spell.target.success", success);
        		
        		UsefulThings.print("Received message from server. The entity ID is: " + message.entityId + " (" + 
        		mc.theWorld.getEntityByID(message.entityId) + ")");		
        	}
        	
        	return null;
        }
	}
}
