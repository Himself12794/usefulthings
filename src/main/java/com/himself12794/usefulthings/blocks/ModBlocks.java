package com.himself12794.usefulthings.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import com.himself12794.usefulthings.UsefulThings;
import com.himself12794.usefulthings.util.Reference;

public class ModBlocks {
	public static final int NUMBER = 2;
	public static Block oreAnimus;
	public static Block custBed;
	//public static Block loneAnimus;
	//public static Block animus;
	
	public static void addBlocks() {
		oreAnimus = new AnimusOre();
		//custBed = new BlockBed().setHardness(0.2F).setUnlocalizedName(Reference.MODID + ":" + "custBed").setCreativeTab(UsefulThings.usefulThings);
		//loneAnimus = new LoneAnimus();
		//animus = new Animus();
	}
	
	public static void registerTextures(FMLInitializationEvent event) {
	    RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(oreAnimus), 0, new ModelResourceLocation(Reference.MODID + ":" + ((AnimusOre) oreAnimus).getName(), "inventory"));
		//renderItem.getItemModelMesher().register(Item.getItemFromBlock(custBed), 0, new ModelResourceLocation(Reference.MODID + ":" + "custBed", "inventory"));
		//renderItem.getItemModelMesher().register(Item.getItemFromBlock(loneAnimus), 0, new ModelResourceLocation(Reference.MODID + ":" + ((LoneAnimus) loneAnimus).getName(), "inventory"));
		//renderItem.getItemModelMesher().register(Item.getItemFromBlock(animus), 0, new ModelResourceLocation("bed", "inventory"));
	}
}
