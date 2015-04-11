package com.himself12794.usefulthings.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.himself12794.usefulthings.util.Reference;

public class HiddenBladeRetracted extends ItemSword {
	
	private final String name = "hiddenBladeRetracted";
	private static final int harvestLevel = 1;
	private static final int uses = 500;
	private static final float efficiency = 2.0F;
	private static final float damage = 2.0F;
	private static final String typeName = "hiddenMaterial";
	private static final int enchantability = 20;
	private static final ToolMaterial attributes = EnumHelper.addToolMaterial(typeName, harvestLevel, uses, efficiency, damage, enchantability);
	
	
	public HiddenBladeRetracted() {
		super(attributes);
		GameRegistry.registerItem(this, name);
		setUnlocalizedName(Reference.MODID + "_" + name);
		setCreativeTab(null);
		setMaxStackSize(1);
	}
	
    public ItemStack onItemRightClick(ItemStack hiddenBladeRetracted, World worldIn, EntityPlayer playerIn)
    {
    	ItemStack hiddenBlade = new ItemStack(ModItems.hiddenBlade);
    	if ( hiddenBladeRetracted.isItemEnchanted()) {
    		hiddenBlade.setTagCompound(hiddenBladeRetracted.getTagCompound());
    	}
    	hiddenBlade.setItemDamage(hiddenBladeRetracted.getItemDamage());
        return hiddenBlade;
    }    
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        return true;
    }

    /**
     * Called when a Block is destroyed using this Item. Return true to trigger the "Use Item" statistic.
     */
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, Block blockIn, BlockPos pos, EntityLivingBase playerIn)
    {
        return true;
    }
    
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4){
    	list.add("Useless when retracted, ");    	
    	list.add("deadly when extended.");    	
    }
    
    public Multimap getItemAttributeModifiers()
    {
        Multimap multimap = HashMultimap.create();
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(itemModifierUUID, "Weapon modifier", (double)(this.damage), 0));
        return multimap;
    }

	public String getName() {
		return name;
	}

}
