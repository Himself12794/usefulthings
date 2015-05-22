package com.himself12794.usefulthings.spell;

import com.himself12794.usefulthings.SpellEffects;
import com.himself12794.usefulthings.UsefulThings;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class Heal extends Spell {
	
	public Heal() {
		this.setDuration(0);
		this.setPower(0.25F);
		this.setCoolDown(1);
		this.setType(SpellType.BUFF);
		setUnlocalizedName("heal");
		SpellRegistry.registerSpell(this);
	} 
	
	public boolean onCast(World world, EntityLivingBase caster, ItemStack stack, float modifier) {
		boolean flag = false;
		SpellEffects.setSpellEffect(caster, SpellEffects.spontaneousRegeneration, -1);
		if (caster.getHealth() < caster.getMaxHealth()) {
			flag = true;
			caster.heal(getPower() * modifier);
		}
		return flag;
	}
	
	public boolean onStrike(World world, MovingObjectPosition target, EntityLivingBase caste, float modifier ) {
		boolean flag = false;
		EntityLivingBase entity = null;
		if (target.entityHit instanceof EntityLivingBase)
			 entity = ((EntityLivingBase)target.entityHit);
			if(entity.getHealth() < entity.getMaxHealth()) {
				flag = true;
				entity.heal(getPower() * modifier);
			}
		
		return flag;
	}
	
	public String getInfo() {
		return "Heals caster";
	}
	
	public String getCastSound() {
		return null;
	}
	
	
}
