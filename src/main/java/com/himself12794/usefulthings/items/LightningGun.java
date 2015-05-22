package com.himself12794.usefulthings.items;

import java.util.List;

import com.himself12794.usefulthings.UsefulThings;
import com.himself12794.usefulthings.network.MessageClient;
import com.himself12794.usefulthings.network.MessageServer;
import com.himself12794.usefulthings.util.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class LightningGun extends ItemBow {

	private final String name = "lightningGun";
	
	public LightningGun() {
		super();
		this.setMaxStackSize(1);
		this.setMaxDamage(325);
        this.setCreativeTab(UsefulThings.usefulThings);
		GameRegistry.registerItem(this, name);
		setUnlocalizedName(Reference.MODID + "_" + name);
		
	}
	
	/*@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityPlayer playerIn, int timeLeft) {
		if (this.getMaxItemUseDuration(stack) - timeLeft >= 24) {
			if (worldIn.isRemote) {
				BlockPos location = playerIn.rayTrace(100.0D, 1.0F).getBlockPos();
				NBTTagCompound msg = new NBTTagCompound();
				msg.setBoolean("lightning", true);
				msg.setDouble("x", location.getX());
				msg.setDouble("y", location.getY());
				msg.setDouble("z", location.getZ());
				UsefulThings.proxy.network.sendToServer(new MessageServer(msg));
			} else {
				//Chance to cause a thunderstorm
	    		int chance = worldIn.rand.nextInt(250);
	    		//Chance to be struck by lightning
	    		int chance2 = worldIn.rand.nextInt(400);
	    		//chance = 1;
	    		if (chance2 == 1) 
	    			worldIn.addWeatherEffect(new EntityLightningBolt(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ));
	    		if( chance == 1 ){
	    	        WorldInfo worldinfo = MinecraftServer.getServer().worldServers[0].getWorldInfo();
	    	        if (!worldinfo.isRaining()) {
	                    worldinfo.setCleanWeatherTime(0);
	                    worldinfo.setRainTime(20 * 10);
	                    worldinfo.setRaining(true); 
	    	        }
	                worldinfo.setThunderTime(20 * 10);
	                worldinfo.setThundering(true);
	    	        
	
	    		}
			}
			if (!playerIn.capabilities.isCreativeMode) stack.damageItem(1, playerIn);
		}
	}*/
    
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4){

		list.add("Calls down lightning bolts.");    	
		list.add("May cause atmospheric instability.");
		list.add("Only usable with certain enchantments.");
    }
	
	
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.BOW;
    }
	
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
    	boolean flag = playerIn.capabilities.isCreativeMode 
    		|| EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, itemStackIn) > 0
    		|| EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, itemStackIn) > 0;
    			
        if (flag) {
        	playerIn.setItemInUse(itemStackIn, this.getMaxItemUseDuration(itemStackIn));
        }
        return itemStackIn;
    }
    
    public int getMaxItemUseDuration(ItemStack stack) {
    	return 72000;
    }
    
    @Override
    public int getItemEnchantability() {
		return 1;
    	
    }

	public String getName() {
		return name;
	}

}
