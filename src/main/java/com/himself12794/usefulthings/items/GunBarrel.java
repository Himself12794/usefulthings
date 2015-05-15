package com.himself12794.usefulthings.items;

import com.himself12794.usefulthings.UsefulThings;
import com.himself12794.usefulthings.util.Reference;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class GunBarrel extends Item {

	private final String name = "gunBarrel";
	
	public GunBarrel() {
		super();
		this.setMaxStackSize(64);
        this.setCreativeTab(UsefulThings.usefulThings);
		GameRegistry.registerItem(this, name);
		setUnlocalizedName(Reference.MODID + "_" + name);
		
	}
	
	public String getName() {
		return name;
	}
}
