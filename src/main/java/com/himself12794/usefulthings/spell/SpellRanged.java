package com.himself12794.usefulthings.spell;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import com.himself12794.usefulthings.entity.EntitySpell;

public class SpellRanged extends Spell {
	
	protected boolean piercingSpell = false;
	
	public final boolean cast(World world, EntityLivingBase caster, ItemStack tome, float modifier) {
		boolean flag1 = onCast(world, caster, tome, modifier);
		EntitySpell casting = new EntitySpell(world, caster, this, modifier );
		boolean flag2 = world.spawnEntityInWorld(casting);
		return flag1 && flag2;
	}
	
	public boolean onStrike(World world, MovingObjectPosition target, EntityLivingBase caster, float modifier) {
		return true;
	}
	
	public String getTypeDescriptor(ItemStack stack, EntityPlayer player) {
		return "Ranged";
	}
	
	/**
	 * Called for ranged spells while they are traveling through the world, before striking anything.
	 * 
	 * @param entitySpell
	 */
	public void onUpdate(EntitySpell entitySpell) {
		World world = entitySpell.worldObj;
		world.spawnParticle(EnumParticleTypes.SPELL, entitySpell.posX, entitySpell.posY, entitySpell.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
		
	}
	
	public float getSpellVelocity() {
		return 2.0F;
	}
	
	public boolean isPiercingSpell() {
		return piercingSpell;
	}
	
	protected void setPiercingSpell(boolean status){
		piercingSpell = status;
	}

}
