package com.himself12794.usefulthings.blocks;

import com.himself12794.usefulthings.util.Reference;

import net.minecraft.block.BlockBed;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Animus extends BlockBed {
	
	private static final String name = "animus";
	
	public Animus(){
        GameRegistry.registerBlock(this, name);
		setUnlocalizedName(Reference.MODID + "_" + name);
		setCreativeTab(CreativeTabs.tabBlock);
		
	}

	public String getName() {
		return name;
	}
}
