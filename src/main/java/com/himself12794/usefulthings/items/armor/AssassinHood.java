package com.himself12794.usefulthings.items.armor;

import java.util.List;

import com.google.common.collect.Multimap;
import com.himself12794.usefulthings.UsefulThings;
import com.himself12794.usefulthings.items.AssassinArmor;
import com.himself12794.usefulthings.util.Reference;

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

public class AssassinHood extends AssassinArmor {
	
	private static final String name = "assassinHood";
	
	public AssassinHood() {
		super(0);
		GameRegistry.registerItem(this, name);
		setUnlocalizedName(Reference.MODID + "_" + name);
		setCreativeTab(UsefulThings.usefulThings);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List info, boolean par4) {
		info.add("Activates your Eagle Vision");
	}    
	
	public String getName() {
		return name;
	}
    /*public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack){
    	player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 500, 1));//(Potion.nightVision);
    	player.setSilent(true);
    }*/
	
	@Override
	public String getArmorTexture(ItemStack item, Entity entity, int slot, String type){
		
		return Reference.MODID + ":textures/models/assassinArmor1.png";
		
	}

}
