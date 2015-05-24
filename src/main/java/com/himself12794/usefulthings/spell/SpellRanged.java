package com.himself12794.usefulthings.spell;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import com.himself12794.usefulthings.entity.EntitySpell;

public class SpellRanged extends Spell implements IProjectileSpell {
	
	@Override
	public boolean cast(World world, EntityLivingBase caster, ItemStack tome, float modifier) {
		boolean flag1 = onCast(world, caster, tome, modifier);
		EntitySpell casting = new EntitySpell(world, caster, this, modifier );
		boolean flag2 = world.spawnEntityInWorld(casting);
		return flag1 && flag2;
	}

	@Override
	public void onUpdate(EntitySpell entitySpell, MovingObjectPosition target) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean onStrike(World world, MovingObjectPosition target, EntityLivingBase caster, float modifier) {
		return true;
	}
	
	public String getTypeDescriptor(ItemStack stack, EntityPlayer player) {
		return "Ranged";
	}

	@Override
	public MovingObjectPosition getTarget(World world, EntityLivingBase caster) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getSpellVelocity() {
		// TODO Auto-generated method stub
		return 2.0F;
	}

	@Override
	public boolean isPiercingSpell() {
		// TODO Auto-generated method stub
		return false;
	}

}
