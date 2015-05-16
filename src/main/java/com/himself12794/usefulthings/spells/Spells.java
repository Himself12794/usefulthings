package com.himself12794.usefulthings.spells;

public class Spells {
	
	public static void registerSpells() throws Exception {

		SpellRegistry.registerSpell(Spell.class, new Spell().getName());
		SpellRegistry.registerSpell(SpellIncinerate.class, new SpellIncinerate().getName());
		SpellRegistry.registerSpell(SpellLightning.class, new SpellLightning().getName());
		SpellRegistry.registerSpell(SpellHeal.class, new SpellHeal().getName());
	}
}
