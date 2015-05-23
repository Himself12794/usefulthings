package com.himself12794.usefulthings.spell;

import com.himself12794.usefulthings.UsefulThings;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class Lightning extends SpellInstant {
	
	public Lightning() {
		setPower(5.0F);
		setCoolDown(10);
		setDuration(0);
		setUnlocalizedName("lightning");
	}
	
	@Override
	public boolean onStrike(World world, MovingObjectPosition target, EntityLivingBase caster, float modifier ) {
		
		EntityLivingBase entity = (EntityLivingBase) target.entityHit;
		EntityLightningBolt bolt = new EntityLightningBolt(world, entity.posX, entity.posY, entity.posZ);
		boolean flag = world.addWeatherEffect(bolt);
		
		if (flag && !entity.isDead) entity.setLastAttacker(caster);
		
		return flag;
	}

}
