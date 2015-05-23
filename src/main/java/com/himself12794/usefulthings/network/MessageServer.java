package com.himself12794.usefulthings.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import com.himself12794.usefulthings.spell.Spell;
import com.himself12794.usefulthings.util.Reference;


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
        	String prefix = Reference.MODID + ":";
        	if (message.value.getBoolean("lightning")) {
        		double x = message.value.getDouble("x");
        		double y = message.value.getDouble("y");
        		double z = message.value.getDouble("z");
        		EntityLightningBolt bolt = new EntityLightningBolt(ctx.getServerHandler().playerEntity.worldObj, x, y, z);
        		bolt.getEntityData().setString("shooter", ctx.getServerHandler().playerEntity.getUniqueID().toString());
        		ctx.getServerHandler().playerEntity.worldObj.addWeatherEffect(bolt);
        	} 
        	
        	/*if (message.value.getBoolean(prefix + "hitScanSpell")) {
        		boolean valid = false;
        		
	        	EntityLivingBase caster = ctx.getServerHandler().playerEntity;
	        	EntityLivingBase target = (EntityLivingBase) ctx.getServerHandler().playerEntity.worldObj.getEntityByID(message.value.getInteger(prefix + "targetId"));
	        	String spellName = message.value.getString(prefix + "spellUsed");
	        	int modifier = message.value.getInteger(prefix + "spellModifier");
	        	
	        	valid = (caster != null && target != null && spellName != ""  );
	        		
        		ItemStack currentItem = ((EntityPlayer)caster).getCurrentEquippedItem();
        		boolean hasSpell = spellName.isSpellOnStack(currentItem);
        		
        		Spell spell = Spell.lookupSpell(spellName);
        		
        		valid = valid && hasSpell && (spell != null);
        		
        		if(valid) valid = spell.onStrike(target.getEntityWorld(), new MovingObjectPosition(target), caster, modifier);
        		
        		
        		caster.getEntityData().setBoolean("hitScanOutcome", valid);
        	}*/
        	
        	boolean isEagleVisionActivated = message.value.getBoolean("eagleVision");
	        if (isEagleVisionActivated) {
	        	EntityPlayer player = ctx.getServerHandler().playerEntity;
	        	player.getEntityData().setBoolean("eagleVision", isEagleVisionActivated);
        	}
            return null; 
        }
    }
}