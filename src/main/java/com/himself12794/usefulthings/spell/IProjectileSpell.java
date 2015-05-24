package com.himself12794.usefulthings.spell;

import com.himself12794.usefulthings.entity.EntitySpell;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public interface IProjectileSpell {
	/**
	 * Gets the target for the spell.
	 * 
	 * @param caster
	 */
	MovingObjectPosition getTarget(World world, EntityLivingBase caster);
	
	/**
	 * Called for ranged spells while they are traveling through the world, before striking anything.
	 * 
	 * @param entitySpell
	 */
	void onUpdate(EntitySpell entitySpell, MovingObjectPosition target);
	
	float getSpellVelocity();
	
	boolean isPiercingSpell();

	float getBrightness();
	
}
