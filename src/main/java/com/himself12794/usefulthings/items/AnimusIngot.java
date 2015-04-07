package com.himself12794.usefulthings.items;

import java.util.List;

import com.himself12794.usefulthings.util.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class AnimusIngot extends Item {
	private final String name = "ingotAnimus";
	
	public AnimusIngot() {
		GameRegistry.registerItem(this, name);
		setUnlocalizedName(Reference.MODID + "_" + name);
		setCreativeTab(CreativeTabs.tabMaterials);
	}
    
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4){
    	list.add("Contains memories from a forgotten era.");
    }
	
	public String getName() {
		return name;
	}
}