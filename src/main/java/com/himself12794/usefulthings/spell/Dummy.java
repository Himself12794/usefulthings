package com.himself12794.usefulthings.spell;

import com.himself12794.usefulthings.UsefulThings;
import com.himself12794.usefulthings.entity.EntitySpell;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class Dummy extends SpellRanged {
	
	public Dummy() {
		setPower(0.0F);
		setCoolDown(20);
		setDuration(0);
		//setType(SpellType.RANGED);
		setUnlocalizedName("dummy");
	}
	
	public boolean onStrike(World world, MovingObjectPosition target, EntityLivingBase caster, float modifier ) {

		if (target.entityHit != null) {
			target.entityHit.attackEntityFrom(DamageSource.magic, getPower());
		}
		return true;
	}
	
	public float getSpellSpeed(){
		return 0.1F;
	}
	
	@Override
	public void onUpdate(EntitySpell spell) {
		World world = spell.worldObj;
		world.spawnParticle(EnumParticleTypes.FLAME, spell.posX, spell.posY, spell.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
	}
}
