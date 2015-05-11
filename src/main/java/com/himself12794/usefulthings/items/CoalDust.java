package com.himself12794.usefulthings.items;

import com.himself12794.usefulthings.util.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CoalDust extends Item {
	private final String name = "coalDust";
	
	public CoalDust() {
		GameRegistry.registerItem(this, name);
		setUnlocalizedName(Reference.MODID + "_" + name);
		setCreativeTab(CreativeTabs.tabMaterials);
	}
	
	public String getName() {
		return name;
	}
}