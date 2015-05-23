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
		boolean flag = false;
		EntityLivingBase entity = null;
		if (target.entityHit instanceof EntityLivingBase ) {
			 entity = (EntityLivingBase) target.entityHit;
			 EntityLightningBolt bolt = new EntityLightningBolt(world, entity.posX, entity.posY, entity.posZ);
			 flag = world.addWeatherEffect(bolt);
			 if (flag && !entity.isDead) entity.setRevengeTarget(caster); 
		}// else if (target.entityHit instanceof EntityLivingBase && world.isRemote)
		return flag;
	}
	
	public String getInfo() {
		return "Strikes an enemy with thunder";
	}

}
