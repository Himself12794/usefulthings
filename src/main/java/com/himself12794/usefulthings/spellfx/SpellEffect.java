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
	public void onUpdate(EntityLivingBase entity, int timeLeft, EntityLivingBase caster){}
	
	/**
	 * Called when the effect is removed.
	 * <p>
	 * Intended mostly for cleanup after effect removal. 
	 * 
	 * @param entity
	 * @param world
	 */
	public void onRemoval(EntityLivingBase entity){}
	
	/**
	 * Applies the spell effect to the specific entity, for a specific time.
	 * <p>
	 * Setting the duration to less than 0 makes it last until removed.
	 * 
	 * @param target 
	 * @param duration
	 */
	public final void addTo(EntityLivingBase target, int duration, EntityLivingBase caster) {
		NBTTagCompound activeEffects = target.getEntityData().getCompoundTag(Reference.MODID + ".spell.spellEffects");
		int[] data = {duration, caster.getEntityId()};
		activeEffects.setIntArray(Integer.toString(id), data);
		target.getEntityData().setTag(Reference.MODID + ".spell.spellEffects", activeEffects);
		
	}
	
	/**
	 * Clears any traces of the effect from the entity, if they have it.
	 * <p>
	 * If the duration left is not 0, also triggers {@link SpellEffect#onRemoval(EntityLivingBase)}
	 * 
	 * @param target
	 */
	public final void clearFrom(EntityLivingBase target) {
		NBTTagCompound activeEffects = target.getEntityData().getCompoundTag(Reference.MODID + ".spell.spellEffects");
		
		onRemoval(target);
		
		activeEffects.removeTag(Integer.toString(id));
	}
	
	/**
	 * Returns the time remaining for this effect on the target. If the target does not 
	 * have the effect, it returns 0.
	 * 
	 * @param target
	 * @return
	 */
	public final int getEffectTimeRemainingOn(EntityLivingBase target){
		NBTTagCompound activeEffects = target.getEntityData().getCompoundTag(Reference.MODID + ".spell.spellEffects");
		//System.out.println(activeEffects);
		return activeEffects.getIntArray(String.valueOf(id))[0];
	}
	
	/**
	 * Checks if the effect is on the target
	 * 
	 * @param entity
	 * @return
	 */
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
