package com.himself12794.usefulthings;

import com.himself12794.usefulthings.spell.Death;
import com.himself12794.usefulthings.spell.Dummy;
import com.himself12794.usefulthings.spell.Spell;
import com.himself12794.usefulthings.spell.Heal;
import com.himself12794.usefulthings.spell.Incinerate;
import com.himself12794.usefulthings.spell.Lightning;
import com.himself12794.usefulthings.spell.SpellRegistry;
import com.himself12794.usefulthings.spell.SpellType;
import com.himself12794.usefulthings.spellfx.SpellEffect;
import com.himself12794.usefulthings.spellfx.SpontaneousRegeneration;
import com.himself12794.usefulthings.util.Reference;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class Spells {
	
	public static final Spell damage;
	public static final Incinerate incinerate;
	public static final Lightning lightning;
	public static final Heal heal;
	public static final Death death;
	public static final Dummy dummy;
	
	static {
		
		damage = SpellRegistry.lookupSpell("damage");
		incinerate = (Incinerate) SpellRegistry.lookupSpell("incinerate");
		lightning = (Lightning) SpellRegistry.lookupSpell("lightning");
		heal = (Heal) SpellRegistry.lookupSpell("heal");
		death = (Death) SpellRegistry.lookupSpell("death");
		dummy = (Dummy) SpellRegistry.lookupSpell("dummy");
		
	}
}
