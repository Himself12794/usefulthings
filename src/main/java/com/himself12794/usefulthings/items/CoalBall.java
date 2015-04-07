package com.himself12794.usefulthings.items;

import com.himself12794.usefulthings.util.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CoalBall extends Item {
	private final String name = "coalBall";
	
	public CoalBall() {
		GameRegistry.registerItem(this, name);
		setUnlocalizedName(Reference.MODID + "_" + name);
		setCreativeTab(CreativeTabs.tabMisc);
	}
	
	public String getName() {
		return name;
	}
}