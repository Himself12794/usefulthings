package com.himself12794.usefulthings.spells;

import com.himself12794.usefulthings.UsefulThings;
import com.himself12794.usefulthings.util.Reference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class Spells {
	
	public static Spell damage;
	public static Spell incinerate;
	public static Spell lightning;
	public static Spell heal;
	public static Spell death;
	public static Spell dummy;
	
	public static void registerSpells() {

		damage = new Spell().setName("spellDamage");
		SpellRegistry.registerSpell(damage);
		
		incinerate = new SpellIncinerate().setName("spellIncinerate");
		SpellRegistry.registerSpell(incinerate);
		
		lightning = new SpellLightning().setName("spellLightning");
		SpellRegistry.registerSpell(lightning);
		
		heal = new SpellHeal().setName("spellHeal");
		SpellRegistry.registerSpell(heal);
		
		death = new Spell().setName("spellDeath").setDuration(100).setPower(1000.0F).setCoolDown(178);
		SpellRegistry.registerSpell(death);
		
		dummy = new Spell().setPower(0.0F).setCoolDown(150).setDuration(0).setType(SpellType.RANGED).setName("spellDummy");
		SpellRegistry.registerSpell(dummy);
	}

	public static boolean hasSpell(ItemStack stack) {
		return stack.hasTagCompound() && stack.getTagCompound().hasKey(Reference.MODID + ".spell.currentSpell");
	}
	
	public static ItemStack setSpell(ItemStack stack, Spell spell) {
		return SpellRegistry.setSpell(stack, spell);
	}
	
	public static Spell getSpell(ItemStack stack) {
		return SpellRegistry.lookupSpell(stack);
	}
	
	public static boolean canCastSpell( EntityPlayer player, Spell spell ) {
		
		NBTTagCompound coolDowns = player.getEntityData().getCompoundTag(Reference.MODID + ".spell.spellCooldowns");
		
		return coolDowns.getInteger(spell.getUnlocalizedName()) <= 0;
	}
	
	public static int getCoolDownRemaining(EntityPlayer player, Spell spell) {
		NBTTagCompound coolDowns = player.getEntityData().getCompoundTag(Reference.MODID + ".spell.spellCooldowns");
		
		return coolDowns.getInteger(spell.getUnlocalizedName());
	}
	
	public static void setCoolDown(EntityPlayer player, Spell spell, int amount) {
		int id = player.getEntityId();
		NBTTagCompound coolDowns = player.getEntityData().getCompoundTag(Reference.MODID + ".spell.spellCooldowns");
		
		coolDowns.setInteger(spell.getUnlocalizedName(), amount);
		player.getEntityData().setTag(Reference.MODID + ".spell.spellCooldowns", coolDowns);
		//UsefulThings.print(player.getEntityData().getCompoundTag(Reference.MODID + ".spell.spellCooldowns"));
	}
	
	public static void triggerCooldown( EntityPlayer player, Spell spell ) {
		UsefulThings.print("Triggering cooldown");
		setCoolDown(player, spell, spell.getCoolDown());
	}
	
	public static NBTTagCompound getCooldowns(EntityPlayer player) {
		return player.getEntityData().getCompoundTag(Reference.MODID + ".spell.spellCooldowns");
	}
}
