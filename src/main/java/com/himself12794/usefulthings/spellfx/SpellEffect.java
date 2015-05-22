package com.himself12794.usefulthings.spellfx;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class SpellEffect {
	
	
	public int id = 0;
	
	/**
	 * This function is called every tick the spell is in effect on the target.
	 * Return false to cancel the effect update that tick 
	 * 
	 * @param entity
	 * @param world
	 * @param timeLeft
	 * @return
	 */
	public void onUpdate(EntityLivingBase entity, int timeLeft){}
	
	/**
	 * Called if the effect is removed before the time remaining is complete.
	 * 
	 * @param entity
	 * @param world
	 * @return
	 */
	public boolean onRemoval(EntityLivingBase entity, World world){
		
		return true;
	}

}
