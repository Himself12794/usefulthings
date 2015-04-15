package com.himself12794.usefulthings.items;

import com.himself12794.usefulthings.UsefulThings;
import com.himself12794.usefulthings.util.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

/** 
 * 
 * @author Philip
 * 
 * This is a joke item
 * it's supposed to tame wolves, but obviously doesn't
 *
 */

public class PoweredOil extends Item {
	private final String name = "poweredOil";
	
	public PoweredOil() {
		GameRegistry.registerItem(this, name);
		setUnlocalizedName(Reference.MODID + "_" + name);
		setCreativeTab(UsefulThings.usefulThings);
	}
	
	@Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target) {
        return target instanceof EntityWolf;
    }
	
	public String getName() {
		return name;
	}
}