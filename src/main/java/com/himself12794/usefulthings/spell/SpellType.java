package com.himself12794.usefulthings.spell;

import com.himself12794.usefulthings.UsefulThings;
import com.himself12794.usefulthings.entity.EntitySpell;
import com.himself12794.usefulthings.network.MessageServer;
import com.himself12794.usefulthings.network.CastSpellServer;
import com.himself12794.usefulthings.util.Reference;
import com.himself12794.usefulthings.util.UsefulMethods;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public enum SpellType {
	
	/**
	 * Only effects living entities.
	 */
	HIT_SCAN,
	/**
	 * Can affect anything it hits.
	 */
	RANGED,
	/**
	 * Only affects the player or the world.
	 */
	BUFF;
	
	public boolean action(Spell spell, World world, EntityLivingBase caster, ItemStack tome, float modifier) {
		boolean hasEffect = false;
		if (this == RANGED) {
			
			boolean flag1 = spell.onCast(world, caster, tome, modifier);
			EntitySpell casting = new EntitySpell(world, caster, spell, modifier );
			boolean flag2 = world.spawnEntityInWorld(casting);
			hasEffect = flag1 && flag2;
			
		} else if (this == BUFF) {
			
			hasEffect = spell.onCast(world, caster, tome, modifier);
			MovingObjectPosition movPOS = new MovingObjectPosition(caster);
			hasEffect = hasEffect && spell.onStrike(world, movPOS, caster, modifier);
			
		} else if (this == HIT_SCAN) {
			
			boolean successful = false;
			hasEffect = spell.onCast(world, caster, tome, modifier);
				
			
			if (world.isRemote && hasEffect) {
				
				MovingObjectPosition pos = UsefulMethods.getMouseOverExtended(50);
				
				if (pos.entityHit != null && pos.entityHit instanceof EntityLivingBase) {
					
					successful = spell.onStrike(world, pos, caster, modifier);
					
					if(hasEffect) {
						
						IMessage msg = new CastSpellServer( pos.entityHit.getEntityId(), modifier, spell, tome);
						UsefulThings.proxy.network.sendToServer(msg);
						
					}
					
				} 
				
				UsefulThings.print("Spell success client side: " + hasEffect);
				
			} else if (hasEffect && !world.isRemote) {
				
				successful = caster.getEntityData().getBoolean(Reference.MODID + ".spell.success");
				UsefulThings.print("Spell success server side: " + hasEffect);
				caster.getEntityData().setBoolean(Reference.MODID + ".spell.success", false);
				
			}
			hasEffect = hasEffect && successful;
		}
		return hasEffect;
		
	}
}
