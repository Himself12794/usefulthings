package com.himself12794.usefulthings.items;

import java.util.List;

import com.himself12794.usefulthings.UsefulThings;
import com.himself12794.usefulthings.util.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MemorySeal extends Item {
	private final String name = "memorySeal";
	
	public MemorySeal() {
		this.setHasSubtypes(true);
		GameRegistry.registerItem(this, name);
		setUnlocalizedName(Reference.MODID + "_" + name);
		setCreativeTab(UsefulThings.usefulThings);
	}
	
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List subItems)
    {
        subItems.add(new ItemStack(itemIn, 1, 0));
        subItems.add(new ItemStack(itemIn, 1, 1));
    }
	
    
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4){
    	if (stack.hasEffect() && stack.getMetadata() == 1){
			list.add("Used to store memories...\n");
			list.add("But this one already contains one?");
    	} else list.add("Used to store memories."); 
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public boolean hasEffect(ItemStack itemStack ){
		
    	return itemStack.getMetadata() == 1;    	
    }
	
	public String getName() {
		return name;
	}
}