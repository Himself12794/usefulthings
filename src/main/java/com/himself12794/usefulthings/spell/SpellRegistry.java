package com.himself12794.usefulthings.spell;

import java.util.List;
import java.util.Map;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import com.google.common.collect.Maps;
import com.himself12794.usefulthings.Spells;
import com.himself12794.usefulthings.UsefulThings;
import com.himself12794.usefulthings.util.Reference;

public class SpellRegistry {
	
	private static SpellRegistry INSTANCE = new SpellRegistry();
	private Map<String, Spell> spellRegister = Maps.newHashMap();
	private static int spells = 0;
	
	public static SpellRegistry instance() {
		return INSTANCE;
	}
	
	public static void registerSpell(Spell spell) {
		instance().doRegisterSpell(spell);
	}
	
	public static Spell lookupSpell(ItemStack stack) {
		if (Spell.hasSpell(stack)) {
			return instance().spellRegister.get(stack.getTagCompound().getString( Reference.MODID + ".spell.currentSpell"));
		}
		return null;
	}
	
	public static Spell lookupSpell(String spell) {
		if (Spell.spellExists(spell)) return (Spell)instance().spellRegister.get(spell);
		return null;
	}
	
	public static boolean isSpellOnStack(ItemStack stack, String spell) {
		return Spell.hasSpell(stack) && lookupSpell(stack).getUnlocalizedName().equals(spell);
	}
	
	public static Map<String, Spell> getSpells() {
		return instance().spellRegister;
	}
	
	private void doRegisterSpell(Spell spell) {
		String name = spell.getUnlocalizedName();
		if (!Spell.spellExists(name)) {
			spellRegister.put(name, spell);
			UsefulThings.print("Registered spell " + name);
			++spells;
		} else {
			UsefulThings.logger.error("Could not register spell " + spell + " under name \"" + name + "\", name has already been registered for " + lookupSpell(name));
		}
	}

	public static int getSpellCount() {
		return spells;
	}

}
