package com.himself12794.usefulthings;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.himself12794.usefulthings.blocks.ModBlocks;
import com.himself12794.usefulthings.items.ModItems;
import com.himself12794.usefulthings.spell.SpellRegistry;

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
		GameRegistry.addRecipe( new ItemStack( ModItems.hiddenBlade ),
			" I ",
			"LIL",
			"LSL",
			'I', Items.iron_ingot, 'L', Items.leather, 'S', Items.string 
		);
		GameRegistry.addRecipe( new ItemStack( ModItems.gunBarrel ),
			"III",
			"E E",
			"III",
			'I', Items.iron_ingot, 'E', Items.ender_pearl 
		);
		GameRegistry.addRecipe( new ItemStack( ModItems.gunStock ),
			"  P",
			" PI",
			"P  ",
			'I', Items.iron_ingot, 'P', Blocks.planks 
		);
		GameRegistry.addRecipe( new ItemStack( ModItems.assassinHood ),
			"WAW",
			"W W",
			'A', ModItems.ingotAnimus, 'W',  new ItemStack(Blocks.wool, 1, 0) 
		);
		GameRegistry.addRecipe( new ItemStack( ModItems.assassinRobes ),
			"L W",
			"ALA",
			"WWL",
			'A', ModItems.ingotAnimus, 'W',  new ItemStack(Blocks.wool, 1, 0), 'L', Items.leather 
		);
		GameRegistry.addRecipe( new ItemStack( ModItems.assassinPants ),
			"ALA",
			"W W",
			"W W",
			'A', ModItems.ingotAnimus, 'W',  new ItemStack(Blocks.wool, 1, 0), 'L', Items.leather 
		);
		GameRegistry.addRecipe( new ItemStack( ModItems.assassinBoots ),
			"A A",
			"L L",
			"L L",
			'A', ModItems.ingotAnimus, 'L', Items.leather 
		);
		GameRegistry.addRecipe( new ItemStack( ModItems.assassinBoots ),
			"ABA",
			'A', ModItems.ingotAnimus, 'B', Items.leather_boots 
		);
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.coalDust), Items.coal, Blocks.cobblestone);
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.lightningGun), ModItems.gunBarrel, ModItems.gunStock);
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.magicTome), Items.book);
		GameRegistry.addSmelting(ModBlocks.oreAnimus, new ItemStack(ModItems.ingotAnimus,1), 5);
		
	}
}
