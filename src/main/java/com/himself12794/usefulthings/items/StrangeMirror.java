package com.himself12794.usefulthings.items;

import java.util.List;

import com.himself12794.usefulthings.Reference;
import com.himself12794.usefulthings.UsefulThings;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class StrangeMirror extends Item {
	private final String name = "strangeMirror";
	private boolean canFlying = false; 
	
	public StrangeMirror() {
		this.setMaxStackSize(1);
		this.setFull3D();
		GameRegistry.registerItem(this, name);
		setUnlocalizedName(Reference.MODID + "_" + name);
		setCreativeTab(UsefulThings.usefulThings);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List info, boolean par4) {
		info.add("You feel it pulling you");
		info.add("into another world");
	}    
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.BLOCK;
    }
	
	@Override
	public int getMaxItemUseDuration(ItemStack stackIn ) {
		return 32;
	}
	
	@Override
    public ItemStack onItemUseFinish(ItemStack stack, World world, EntityPlayer player)
    {

    	world.addWeatherEffect(new EntityLightningBolt(world, player.posX, player.posY, player.posZ));
    	if (player.dimension != 0 ) {
			player.travelToDimension(0);
    		player.preparePlayerToSpawn();
    	} else {
    		player.preparePlayerToSpawn();
			player.travelToDimension(1);
    	}
    	
    	return stack;

     }    
    
	/*@Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
     {
    	worldIn.playSoundAtEntity(playerIn, "portal.portal", 2.0F, 2.0F);
    	this.canFlying = true;
        playerIn.setItemInUse(itemStackIn, this.getMaxItemUseDuration(itemStackIn));
        return itemStackIn;
     }*/
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
	
		EntityPlayer player = (EntityPlayer) entityIn;
		if ( ( entityIn instanceof EntityPlayer ) && !(player.capabilities.isCreativeMode )) {
			
			if (!player.capabilities.allowFlying && isSelected) {
				player.capabilities.allowFlying = true;
				player.fallDistance = 0.0F;
			} else if ( !isSelected ) {
				player.capabilities.isFlying = false;
				player.capabilities.allowFlying = false;
			}
				
		}
		
	}
	
	@Override
    public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player)
    {
		if (!player.capabilities.isCreativeMode) {
			player.capabilities.isFlying = false;
			player.capabilities.allowFlying = false;
		}
		return true;
    }

	
	public String getName() {
		return name;
	}
}