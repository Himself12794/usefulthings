package com.himself12794.usefulthings.spell;

import com.himself12794.usefulthings.UsefulThings;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class Incinerate extends Spell {
	
	public Incinerate() {
		setPower(0.0F);
		setCoolDown(60);
		setDuration(15);
		setUnlocalizedName("incinerate");
	}
	
	@Override
	public boolean onStrike(World world, MovingObjectPosition target, EntityLivingBase caster, float modifier ) {
		boolean flag = false;
		if (!target.entityHit.isImmuneToFire()) {
			flag = true;
			target.entityHit.setFire(MathHelper.ceiling_float_int(10 * modifier));
			if (!target.entityHit.isDead) ((EntityLivingBase)target.entityHit).setRevengeTarget(caster);
		}
		return flag;
	}
	
	public String getInfo() {
		return "Sets an enemy on fire";
	}
	

}
