package com.himself12794.usefulthings;

import com.himself12794.usefulthings.blocks.ModBlocks;
import com.himself12794.usefulthings.items.ModItems;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {
	public final static int NUMBER = 4;
	public static void addRecipes() {
		GameRegistry.addRecipe( new ItemStack( ModItems.coalBall ),
			"CCC",
			"CFC",
			"CCC",
			'C', ModItems.coalDust, 'F',Items.flint
		);
		GameRegistry.addRecipe( new ItemStack( Items.diamond ),
			"CCC",
			"COC",
			"CCC",
			'C', ModItems.coalBall, 'O',Blocks.obsidian
		);
		GameRegistry.addRecipe( new ItemStack( ModItems.hiddenBladeRetracted ),
			" I ",
			"LIL",
			"LSL",
			'I', Items.iron_ingot, 'L', Items.leather, 'S', Items.string 
		);
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.coalDust), Items.coal, Blocks.cobblestone);
		GameRegistry.addSmelting(ModBlocks.oreAnimus, new ItemStack(ModItems.ingotAnimus,1), 5);
	}
}
