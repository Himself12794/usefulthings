package com.himself12794.usefulthings.blocks;

import com.himself12794.usefulthings.UsefulThings;
import com.himself12794.usefulthings.util.Reference;

import net.minecraft.block.BlockEnchantmentTable;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class LoneAnimus extends BlockEnchantmentTable {
	
	private static final String name = "loneAnimus";
	
	public LoneAnimus(){
        GameRegistry.registerBlock(this, name);
		setUnlocalizedName(Reference.MODID + "_" + name);
		setCreativeTab(UsefulThings.usefulThings);
		
	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

}
