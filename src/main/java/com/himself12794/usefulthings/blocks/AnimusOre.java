package com.himself12794.usefulthings.blocks;

import java.util.Random;

import com.himself12794.usefulthings.Reference;
import com.himself12794.usefulthings.items.ModItems;

import net.minecraft.block.BlockOre;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class AnimusOre extends BlockOre {
	
	private static final String name = "oreAnimus";
	private static final float hardness = 3.0F;
	private static final float resistance = 5.0F;
	private static final float light = 1.0F;
	
	public AnimusOre() {
		this.setHardness(hardness);
		this.setResistance(resistance);
		this.setStepSound(soundTypePiston);
		this.setLightLevel(light);
        GameRegistry.registerBlock(this, name);
		setUnlocalizedName(Reference.MODID + "_" + name);
		setCreativeTab(CreativeTabs.tabBlock);
		
	}    
	
	/*public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return ModItems.ingotAnimus;
    }*/
	
	public String getName() {
		return name;
	}
	
	
}