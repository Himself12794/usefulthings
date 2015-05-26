package com.himself12794.usefulthings.items;

import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class AssassinArmor extends ItemArmor{

	public static final int durability = 20;
	public static final int[] damage = {2,6,5,2};
	public static final String typeName = "assassin";
	public static final int enchantability = 20;
	public static final ArmorMaterial assassinMaterial = EnumHelper.addArmorMaterial(typeName, typeName, durability, damage, enchantability);

	public AssassinArmor(int armorType) {
		super(assassinMaterial, 0, armorType);
	}

}
