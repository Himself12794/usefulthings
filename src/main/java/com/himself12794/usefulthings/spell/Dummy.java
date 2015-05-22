package com.himself12794.usefulthings.spell;

import com.himself12794.usefulthings.UsefulThings;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class Dummy extends Spell {
	
	public Dummy() {
		setPower(0.0F);
		setCoolDown(150);
		setDuration(0);
		setType(SpellType.RANGED);
		setUnlocalizedName("dummy");
		SpellRegistry.registerSpell(this);
	}
	
	public boolean onStrike(World world, MovingObjectPosition target, EntityLivingBase caster, float modifier ) {
		return true;
	}
}
