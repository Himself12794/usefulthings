package com.himself12794.usefulthings.spell;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

import com.himself12794.usefulthings.UsefulThings;
import com.himself12794.usefulthings.network.CastSpellServer;
import com.himself12794.usefulthings.util.Reference;
import com.himself12794.usefulthings.util.UsefulMethods;

public class SpellInstant extends Spell {
	
	public boolean cast(World world, EntityLivingBase caster, ItemStack tome, float modifier) {
		//return type.action(this, world, caster, tome, modifier);
		
		boolean successful = false;
		boolean hasEffect = onCast(world, caster, tome, modifier);
			
		
		if (world.isRemote) {
			
			MovingObjectPosition pos = UsefulMethods.getMouseOverExtended(50);
			
			if (pos.entityHit != null && pos.entityHit instanceof EntityLivingBase) {
				
				successful = onStrike(world, pos, caster, modifier);
				
				if(successful) {
					
					IMessage msg = new CastSpellServer( pos.entityHit.getEntityId(), modifier, this, tome);
					UsefulThings.proxy.network.sendToServer(msg);
					
				}
				
			} 
			
			UsefulThings.print("Spell success client side: " + successful);
			
		} else  {
			
			successful = caster.getEntityData().getBoolean(Reference.MODID + ".spell.success");
			UsefulThings.print("Spell success server side: " + successful);
			caster.getEntityData().setBoolean(Reference.MODID + ".spell.success", false);
			
		}
		
		return hasEffect && successful;
	}
	
	public String getTypeDescriptor(ItemStack stack, EntityPlayer player) {
		return "Instant";
	}

}
