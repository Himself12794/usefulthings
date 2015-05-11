package com.himself12794.usefulthings.items.armor;

import java.util.HashMap;
import java.util.List;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.himself12794.usefulthings.UsefulThings;
import com.himself12794.usefulthings.items.AssassinArmor;
import com.himself12794.usefulthings.util.Reference;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class AssassinRobes extends AssassinArmor {
	
	private static final String name = "assassinRobes";
	
	public AssassinRobes() {
		super(1);
		GameRegistry.registerItem(this, name);
		setUnlocalizedName(Reference.MODID + "_" + name);
		setCreativeTab(UsefulThings.usefulThings);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List info, boolean par4) {
		info.add("Assassins are able to");
		info.add("evade enemies.");
	}    
	
	public String getName() {
		return name;
	}
	
	@Override
	public String getArmorTexture(ItemStack item, Entity entity, int slot, String type){
		
		return Reference.MODID + ":textures/models/assassinArmor1.png";
		
	}
	
	/*@Override
	public Multimap getItemAttributeModifiers() {
		Multimap modifiers = HashMultimap.create();
		modifiers.put(SharedMonsterAttributes.followRange.getAttributeUnlocalizedName(), new AttributeModifier(itemModifierUUID, "Armor modifier", 0.0D, 0));
		
		return modifiers;
	}*/

}
