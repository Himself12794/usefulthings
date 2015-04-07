package com.himself12794.usefulthings.items.armor;

import com.google.common.collect.Multimap;
import com.himself12794.usefulthings.Reference;
import com.himself12794.usefulthings.UsefulThings;
import com.himself12794.usefulthings.items.AssassinArmor;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class AssassinPants extends AssassinArmor {
	
	private static final String name = "assassinPants";
	
	public AssassinPants() {
		super(2);
		GameRegistry.registerItem(this, name);
		setUnlocalizedName(Reference.MODID + "_" + name);
		setCreativeTab(UsefulThings.usefulThings);
	}
	
	@Override
	public Multimap getItemAttributeModifiers()
    {
        Multimap multimap = super.getItemAttributeModifiers();
        multimap.put(SharedMonsterAttributes.movementSpeed.getAttributeUnlocalizedName(), new AttributeModifier(itemModifierUUID, "Speed Modifier", 0.75D, 1));

        return multimap;
    }
	
	public String getName() {
		return name;
	}
	
	@Override
	public String getArmorTexture(ItemStack item, Entity entity, int slot, String type){
		
		return Reference.MODID + ":textures/models/assassinArmor2.png";
		
	}

}
