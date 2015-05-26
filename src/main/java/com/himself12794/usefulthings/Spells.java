package com.himself12794.usefulthings;

import com.himself12794.usefulthings.spell.Dummy;
import com.himself12794.usefulthings.spell.DummyHoming;
import com.himself12794.usefulthings.spell.Flames;
import com.himself12794.usefulthings.spell.Heal;
import com.himself12794.usefulthings.spell.Immortalize;
import com.himself12794.usefulthings.spell.Incinerate;
import com.himself12794.usefulthings.spell.Lightning;
import com.himself12794.usefulthings.spell.Spell;

public class Spells {
	
	public static final Spell damage;
	public static final Incinerate incinerate;
	public static final Lightning lightning;
	public static final Heal heal;
	public static final Spell death;
	public static final Dummy dummy;
	public static final Immortalize immortalize;
	public static final Flames flames;
	public static final DummyHoming dummyHoming;
	
	static {
		
		damage = Spell.lookupSpell("damage");
		death = Spell.lookupSpell("death");
		incinerate = (Incinerate) Spell.lookupSpell("incinerate");
		lightning = (Lightning) Spell.lookupSpell("lightning");
		heal = (Heal) Spell.lookupSpell("heal");
		dummy = (Dummy) Spell.lookupSpell("dummy");
		immortalize = (Immortalize) Spell.lookupSpell("immortalize");
		flames = (Flames) Spell.lookupSpell("flames");
		dummyHoming = (DummyHoming) Spell.lookupSpell("dummyHoming");
		
	}
}
