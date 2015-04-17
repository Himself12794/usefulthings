package com.himself12794.usefulthings.items;

import java.util.List;

import com.himself12794.usefulthings.UsefulThings;
import com.himself12794.usefulthings.player.Teleporto;
import com.himself12794.usefulthings.util.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
	//@SideOnly(Side.SERVER)
	public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer player) {
		/*System.out.println("You used the mirror");
		if (var3 instanceof EntityPlayerMP)	{
			WorldServer worldserver = (WorldServer)var2;
			EntityPlayerMP var4 = (EntityPlayerMP)var3;
			if (var3.ridingEntity == null && var3.riddenByEntity == null && var3 instanceof EntityPlayer && var4.dimension != 0) {
				System.out.println("You used the mirror");
				var4.mcServer.getConfigurationManager().transferPlayerToDimension(var4, -1, new Teleporto(worldserver));
			}
		}
		return var1;*/
		teleport(var2,player);
		return var1;
		
	}
	
	@Override
    public ItemStack onItemUseFinish(ItemStack stack, World world, EntityPlayer player)
    {
		/*System.out.println("You used the mirror");
		if (player instanceof EntityPlayerMP)	{
			WorldServer worldserver = (WorldServer)world;
			EntityPlayerMP var4 = (EntityPlayerMP)player;
			if (player.ridingEntity == null && player.riddenByEntity == null && player instanceof EntityPlayer && var4.dimension != 0) {
				var4.mcServer.getConfigurationManager().transferPlayerToDimension(var4, -1, new Teleporto(worldserver));
			}
		}*/

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
	public void teleport(World par1World,  Entity par5Entity)
	{
		if ((par5Entity.ridingEntity == null) && (par5Entity.riddenByEntity == null) && ((par5Entity instanceof EntityPlayerMP)))
		{
			EntityPlayerMP player = (EntityPlayerMP) par5Entity;
	 
			MinecraftServer mServer = MinecraftServer.getServer();
	 
			if (player.timeUntilPortal > 0)
			{
				player.timeUntilPortal = 10;
			}
			else if (player.dimension != -1)
			{
				player.timeUntilPortal = 10;
	 
				player.mcServer.getConfigurationManager().transferPlayerToDimension(player, 2, new Teleporto(mServer.worldServerForDimension(2)));
			}
			else
			{
				player.timeUntilPortal = 10;
				player.mcServer.getConfigurationManager().transferPlayerToDimension(player, 0, new Teleporto(mServer.worldServerForDimension(1)));
			}
		}
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