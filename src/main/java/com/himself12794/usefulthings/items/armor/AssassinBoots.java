package com.himself12794.usefulthings.items.armor;

import java.util.List;

import com.google.common.collect.Multimap;
import com.himself12794.usefulthings.Reference;
import com.himself12794.usefulthings.UsefulThings;
import com.himself12794.usefulthings.items.AssassinArmor;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class AssassinBoots extends AssassinArmor {
	
	private static final String name = "assassinBoots";
	public static final double jumpBoost = 0.15D;
	
	public AssassinBoots() {
		super(3);
		GameRegistry.registerItem(this, name);
		setUnlocalizedName(Reference.MODID + "_" + name);
		setCreativeTab(UsefulThings.usefulThings);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List info, boolean par4) {
		info.add("Assassins always need an");
		info.add("extra spring in their step.");
	}    
	
	public String getName() {
		return name;
	}
	
	@Override
	public String getArmorTexture(ItemStack item, Entity entity, int slot, String type){
		
		return Reference.MODID + ":textures/models/assassinArmor1.png";
		
	}

}
