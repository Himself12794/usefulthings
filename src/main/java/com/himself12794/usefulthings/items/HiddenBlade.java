package com.himself12794.usefulthings.items;

import java.util.Iterator;
import java.util.List;

import com.himself12794.usefulthings.util.Reference;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
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
		//setCreativeTab(CreativeTabs.tabMisc);
	}
	
	@Override
	public float getDamageVsEntity() {
		return maxStackSize;
		
	}
	
    public ItemStack onItemRightClick(ItemStack hiddenBlade, World worldIn, EntityPlayer playerIn)
    {
    	NBTTagCompound tags = hiddenBlade.getTagCompound();
    	if (tags != null) {
	    	if (!tags.hasKey("retracted")) tags.setBoolean("retracted", false);
	    	tags.setBoolean("retracted", !tags.getBoolean("retracted"));
    	}
    	
    	/*ItemStack hiddenBladeRetracted = new ItemStack(ModItems.hiddenBladeRetracted);
    	if ( hiddenBlade.isItemEnchanted()) {
    		hiddenBladeRetracted.setTagCompound(hiddenBlade.getTagCompound());
    		
    	}    	
    	hiddenBladeRetracted.setItemDamage(hiddenBlade.getItemDamage());
        return hiddenBladeRetracted;*/
    	return hiddenBlade;
    }
    
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4){

		list.add("Useless when retracted, ");    	
		list.add("deadly when extended.");
    }
	
    @Override
    @SideOnly(Side.CLIENT)
    public ModelResourceLocation getModel(ItemStack stack, EntityPlayer player, int useRemaining)
    {
    	ModelResourceLocation model;
    	//System.out.println(stack.getTagCompound().getBoolean("retracted"));
    	if (stack.getTagCompound() != null) {
	    	if (stack.getTagCompound().getBoolean("retracted")) 
	    		model = new ModelResourceLocation(Reference.MODID + ":models/item/hiddenBladeRetracted");
	    	else //if (stack.getTagCompound().hasKey("retracted") && !stack.getTagCompound().getBoolean("retracted"))
	    		model = new ModelResourceLocation(Reference.MODID + ":models/item/hiddenBlade");
    	}
		model = new ModelResourceLocation(Reference.MODID + ":models/item/hiddenBlade");
    	return model;
    }

	public String getName() {
		return name;
	}

}
