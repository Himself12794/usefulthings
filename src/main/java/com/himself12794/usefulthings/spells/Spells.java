package com.himself12794.usefulthings.spells;

public class Spells {
	
	public static Spell damage;
	public static Spell incinerate;
	public static Spell lightning;
	public static Spell heal;
	public static Spell death;
	
	public static void registerSpells() {

		damage = new Spell().setName("spellDamage");
		SpellRegistry.registerSpell(damage);
		
		incinerate = new SpellIncinerate().setName("spellIncinerate");
		SpellRegistry.registerSpell(incinerate);
		
		lightning = new SpellLightning().setName("spellLightning");
		SpellRegistry.registerSpell(lightning);
		
		heal = new SpellHeal().setName("spellHeal");
		SpellRegistry.registerSpell(heal);
		
		death = new Spell().setName("spellDeath").setCastTime(100).setPower(1000.0F).setStackDamage(10);
		SpellRegistry.registerSpell(death);
	}
}
