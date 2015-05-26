package com.himself12794.usefulthings.items;

import java.util.Iterator;
import java.util.List;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.himself12794.usefulthings.util.Reference;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class HiddenBlade extends ItemSword {
	
	private final String name = "hiddenBlade";
	private static final int harvestLevel = 1;
	private static final int uses = 500;
	private static final float efficiency = 2.0F;
	private static final float damage = 6.0F;
	private static final String typeName = "hiddenMaterial";
	private static final int enchantability = 20;
	private static final ToolMaterial attributes = EnumHelper.addToolMaterial(typeName, harvestLevel, uses, efficiency, damage, enchantability);
	
	public HiddenBlade() {
		super( attributes );
		GameRegistry.registerItem(this, name);
		setUnlocalizedName(Reference.MODID + "_" + name);
	}
	
	/*@Override
	public float getDamageVsEntity() {
		return maxStackSize;
		
	}*/
	
    public ItemStack onItemRightClick(ItemStack stack, World worldIn, EntityPlayer playerIn) {  
    	boolean flag = isExtended(stack);
    	
    	System.out.println("Is it extended? :" + flag);
    	
        return !flag ? extend(stack) : retract(stack);
    }
    
    public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
			extend(stack);
    }
    
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4){

		list.add("Useless when retracted, ");    	
		list.add("deadly when extended.");
    }
    
    public Multimap getAttributeModifiers(ItemStack stack) {
    	boolean flag = isExtended(stack);
        Multimap multimap = HashMultimap.create();
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(itemModifierUUID, "Weapon modifier", (flag ? 10.0D : 2.0D ), 0));
        return multimap;
    }
    
    @SideOnly(Side.CLIENT)
    public net.minecraft.client.resources.model.ModelResourceLocation getModel(ItemStack stack, EntityPlayer player, int useRemaining) {
        boolean flag = !isExtended(stack);
        
        System.out.println("Trying to get the model. Is it retracted?" + flag);
        
    	return flag ? new ModelResourceLocation(Reference.MODID + ":hiddenBladeRetracted", "inventory") : null;
    }
    
    private boolean isExtended(ItemStack item) {
    	if (item.getTagCompound() == null) extend(item);
    	return item.getTagCompound().getBoolean(Reference.MODID + ":isBladeRetracted");
    }
    
    private ItemStack extend(ItemStack item) {
    	
    	NBTTagCompound nbt = null;
    	
    	if (!item.hasTagCompound()) nbt = new NBTTagCompound();
    	else nbt = item.getTagCompound();
    	nbt.setBoolean(Reference.MODID + ":isBladeExtended", true);
    	item.setTagCompound(nbt);
    	return item;
    	
    }
    
    
    
    private ItemStack retract(ItemStack item) {
    	
    	NBTTagCompound nbt = null;
    	
    	if (!item.hasTagCompound()) nbt = new NBTTagCompound();
    	else nbt = item.getTagCompound();
    	nbt.setBoolean(Reference.MODID + ":isBladeExtended", false);
    	item.setTagCompound(nbt);
    	return item;
    }

	public String getName() {
		return name;
	}

}
