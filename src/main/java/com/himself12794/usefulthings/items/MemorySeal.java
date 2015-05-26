package com.himself12794.usefulthings.items;

import java.util.List;

import com.himself12794.usefulthings.UsefulThings;
import com.himself12794.usefulthings.events.EagleVision;
import com.himself12794.usefulthings.util.Reference;
import com.himself12794.usefulthings.util.UsefulMethods;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
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
	
    /*@SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List subItems)
    {
        subItems.add(new ItemStack(itemIn, 1, 0));
        subItems.add(new ItemStack(itemIn, 1, 1));
    }*/
	
    
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4){
    	if (stack.hasEffect() && stack.getMetadata() == 1){
			list.add("Used to store memories...");
			list.add("But this one already contains one?");
    	} else list.add("Makes you remember home."); 
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack memorySeal, World worldIn, EntityPlayer playerIn){
		playerIn.setItemInUse(memorySeal, getMaxItemUseDuration(memorySeal));
		playerIn.playSound("mob.guardian.idle", 1, 1);
    	return memorySeal;
    }    
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.BLOCK;
    }
    
    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
    	return 32;
    	//return stack.getMetadata() == 1 ? 32 : 0;
    }
    
    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World world, EntityPlayer playerIn){
    	if (playerIn.getBedLocation() != null) playerIn.respawnPlayer();
    	return stack;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public boolean hasEffect(ItemStack itemStack ){
		
    	return itemStack.getMetadata() == 1;    	
    }
    
    @Override
    public int getItemStackLimit(ItemStack stack)
    {
        return stack.getMetadata() == 0 ? 64 : 1;
    }
	
	public String getName() {
		return name;
	}
}