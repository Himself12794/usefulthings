package com.himself12794.usefulthings.spell;

import com.himself12794.usefulthings.entity.EntitySpell;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class Flames extends SpellRanged {
	
	Flames() {
		setPower(0.5F);
		setCoolDown(20);
		setDuration(5 * 20);
		setUnlocalizedName("flames");
	}
	
	public boolean onStrike(World world, MovingObjectPosition target, EntityLivingBase caster, float modifier ) {

		if (target.entityHit != null) {
			if (!target.entityHit.isImmuneToFire()) {
				target.entityHit.attackEntityFrom(DamageSource.inFire, getPower());
				target.entityHit.setFire(getDuration() / 20 );
			}
			
		}
		return true;
	}
	
	public void onUpdate(EntitySpell spell) {
		World world = spell.worldObj;
		float distTraveled = getSpellVelocity() * spell.getTicksInAir();
		//float distPerTick = ;
		
		//for (float i = 0.0F; i < getSpellSpeed(); i += getSpellSpeed() * 0.1)
			//world.spawnParticle(EnumParticleTypes.FLAME, spell.posX - spell.motionX + i, spell.posY - spell.motionY + i, spell.posZ - spell.motionZ + i, 0.0D, 0.0D, 0.0D, new int[0]);		
		world.spawnParticle(EnumParticleTypes.FLAME, spell.posX, spell.posY, spell.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
		
		if (distTraveled > 3) spell.setDead();
		if (spell.getTicksInGround() > 0) spell.setDead();
		
		
	}
	
	public float getSpellVelocity(){
		return 0.2F;
	}

}
