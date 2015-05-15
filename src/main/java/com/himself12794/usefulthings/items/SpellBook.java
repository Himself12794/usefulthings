package com.himself12794.usefulthings.items;

import java.util.List;

import com.himself12794.usefulthings.UsefulThings;
import com.himself12794.usefulthings.entity.EntitySpellHitScan;
import com.himself12794.usefulthings.network.MessageServer;
import com.himself12794.usefulthings.spells.Spells;
import com.himself12794.usefulthings.util.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

abstract class SpellBook extends Item {
	private final String name = "spellBook";
	private int castTime = 20;
	
	public SpellBook() {
		this.setMaxStackSize(1);
		this.isDamageable();
		this.setMaxDamage(200);
	}
    
    @Override
    public ItemStack onItemRightClick(ItemStack spellBook, World worldIn, EntityPlayer playerIn){
		playerIn.setItemInUse(spellBook, getMaxItemUseDuration(spellBook));
    	return spellBook;
    }
    
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4){
		list.add("Casts Spells");
    }
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.BLOCK;
    }
	
	@Override
	public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
		if (count == getMaxItemUseDuration(stack) - 20) player.playSound("mob.guardian.idle", 3, 1);
	}
    
    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
    	return 72000;
    	//return stack.getMetadata() == 1 ? 32 : 0;
    }
    
    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer playerIn, int timeLeft) {
    	if (getMaxItemUseDuration(stack) - timeLeft >= 20) { 
    		if( castSpell(stack, playerIn, world) ) {
    			stack.damageItem(1, playerIn);
    		}
    	}
    }
	
    /*@SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List subItems)
    {
        subItems.add(new ItemStack(itemIn, 1, 0));
        subItems.add(new ItemStack(itemIn, 1, 1));
        subItems.add(new ItemStack(itemIn, 1, 2));
        subItems.add(new ItemStack(itemIn, 1, 3));
        subItems.add(new ItemStack(itemIn, 1, 4));
        subItems.add(new ItemStack(itemIn, 1, 5));
    }*/
    
    @SideOnly(Side.CLIENT)
    @Override
    public boolean hasEffect( ItemStack itemStack ){
		
    	return true;    	
    }
	
	protected boolean castSpell(ItemStack stack, EntityPlayer player, World world) {
		EntitySpellHitScan spell = new EntitySpellHitScan(world, player, Spells.DAMAGE);
		/*switch(stack.getMetadata()) {
		case 1:
			spell = new EntitySpellHitScan(world, player, Spells.DAMAGE);
			break;
		case 2:
			spell = new EntitySpellHitScan(world, player, Spells.INCINERATE);
			break;
		case 3:
			spell = new EntitySpellHitScan(world, player, Spells.KILL);
			break;
		case 4:
			spell = new EntitySpellHitScan(world, player, Spells.POISON);
			break;
		case 5:
			spell = new EntitySpellHitScan(world, player, Spells.LIGHTNING);
			break;
		default:
			spell = new EntitySpellHitScan(world, player, Spells.DAMAGE);
			break;
		}*/
			
		return world.spawnEntityInWorld(spell);			
	}
	
	public void setCastTime(int time) {
		this.castTime = time;
	}
	
	public int getCastTime() { return this.castTime; }

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
}


