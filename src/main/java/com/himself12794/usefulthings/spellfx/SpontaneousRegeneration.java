package com.himself12794.usefulthings.spellfx;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class SpontaneousRegeneration extends SpellEffect {
	
	public SpontaneousRegeneration(int id) {
		this.id = id;
	}
	
	public void onUpdate(EntityLivingBase entity, int timeRemaining ) {
		
		if (entity.getHealth() < entity.getMaxHealth()) {
			entity.heal(10.0F);
		}
	}
	
	
	
}
