package com.himself12794.usefulthings.spell;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.himself12794.usefulthings.spellfx.SpellEffect;

public class Immortalize extends SpellBuff {
	
	Immortalize() {
		setUnlocalizedName("immortalize");
	}
	
	public boolean onCast(World world, EntityLivingBase caster, ItemStack stack, float modifier) {
		SpellEffect.spontaneousRegeneration.addTo(caster, -1);
		EntityPlayer player = (EntityPlayer)caster;
		
		return true;
	}

}
