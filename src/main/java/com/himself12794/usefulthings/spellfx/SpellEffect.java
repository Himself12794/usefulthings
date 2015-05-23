package com.himself12794.usefulthings.spellfx;

import com.himself12794.usefulthings.util.Reference;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class SpellEffect {
	
	public static final SpellEffect[] spellEffectIds = new SpellEffect[32];
	public static final SpellEffect spontaneousRegeneration = new SpontaneousRegeneration(0);
	
	private static int spellEffectCount = 0;
	
	
	public final int id;
	
	SpellEffect(int id) {
		this.id = id;
		spellEffectIds[id] = this;
	}
	
	/**
	 * This function is called every tick the spell is in effect on the target.
	 * 
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
	public boolean onRemoval(EntityLivingBase entity){
		
		return true;
	}
	
	public final void addTo(EntityLivingBase target, int duration) {
		NBTTagCompound activeEffects = target.getEntityData().getCompoundTag(Reference.MODID + ".spell.spellEffects");
		activeEffects.setInteger(Integer.toString(id), duration);
		target.getEntityData().setTag(Reference.MODID + ".spell.spellEffects", activeEffects);
		
	}
	
	public final void clearFrom(EntityLivingBase target) {
		NBTTagCompound activeEffects = target.getEntityData().getCompoundTag(Reference.MODID + ".spell.spellEffects");
		
		if (getEffectTimeRemainingOn(target) != 0) onRemoval(target);
		
		activeEffects.removeTag(Integer.toString(id));
	}
	
	public final int getEffectTimeRemainingOn(EntityLivingBase target){
		NBTTagCompound activeEffects = target.getEntityData().getCompoundTag(Reference.MODID + ".spell.spellEffects");
		//System.out.println(activeEffects);
		return activeEffects.getInteger(String.valueOf(id));
	}
	
	public final boolean isEffecting(EntityLivingBase entity) {
		//System.out.println(getEffectTimeRemaining(entity, id));
		return getEffectTimeRemainingOn(entity) != 0;
	}
	
	public static SpellEffect[] getRegisteredSpellEffects() {
		return spellEffectIds;
	}
	
	public static SpellEffect getEffectById(int id) {
		//String id = Integer.toString(theId);
		if (spellEffectIds[id] != null) return spellEffectIds[id];
		return null;
	}
	
	public static NBTTagCompound getActiveEffects(EntityLivingBase entity) {

		return entity.getEntityData().getCompoundTag(Reference.MODID + ".spell.spellEffects");
	}

}
