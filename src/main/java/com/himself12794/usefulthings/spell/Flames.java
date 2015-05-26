package com.himself12794.usefulthings.spell;

import com.himself12794.usefulthings.entity.EntitySpell;
import com.himself12794.usefulthings.util.Reference;
import com.himself12794.usefulthings.util.UsefulMethods;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class Flames extends SpellRanged {
	
	Flames() {
		setMaxConcentrationTime(5 * 20);
		setPower(0.5F);
		setCoolDown(20);
		setDuration(5 * 20);
		setUnlocalizedName("flames");
	}
	
	public boolean onCast(World world, EntityLivingBase caster, ItemStack stack, float modifier) {
		caster.playSound(Reference.MODID + ":flamethrower", 1, 1);
		return true;
	}
	
	public boolean onStrike(World world, MovingObjectPosition target, EntityLivingBase caster, float modifier ) {

		if (target.entityHit != null) {
			if (!target.entityHit.isImmuneToFire()) {
				target.entityHit.attackEntityFrom(DamageSource.inFire, getPower());
				target.entityHit.setFire(getDuration() / 20 );
			}
			
		} else {
			
			BlockPos blockPos = UsefulMethods.getBlockFromSide( target.getBlockPos(), target.sideHit);
			Block block = UsefulMethods.getBlockAtPos(blockPos, world);
			//System.out.println(block.getMaterial().getCanBurn());
			
			if (/*block.getMaterial() == Material.grass || block.getMaterial() == Material.vine || block.getMaterial() == Material.leaves || block.getMaterial() == Material.plants*/ block.getMaterial().getCanBurn()) 
				//block.breakBlock(world, target.getBlockPos(), block.getDefaultState());
				world.setBlockState(blockPos, Blocks.fire.getDefaultState());
		}
		return true;
	}	
	
	public boolean onPrepareSpell(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		boolean flag = !playerIn.isInsideOfMaterial(Material.water);
		
		return flag;
	}
	
	public void onUpdate(EntitySpell spell) {
		if (!spell.isInWater()) {
			
			World world = spell.worldObj;
			float distTraveled = getSpellVelocity() * spell.getTicksInAir();
			
			if (distTraveled > 4) spell.setDead();		
			//world.spawnParticle(EnumParticleTypes.FLAME, spell.posX, spell.posY, spell.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
			boolean cont = true;
			if (cont) {
				
				for (float j = 0.0F; j < 1.0F; j += 0.05F) {
					
					for (int i = 0; i < 10; ++i) {
						
						BlockPos pos = new BlockPos(
								spell.prevPosX + (spell.motionX * j),
								spell.prevPosY + (spell.motionY * j),
								spell.prevPosZ + (spell.motionZ * j));
						
						if (UsefulMethods.getBlockAtPos(pos, world).getMaterial() != Material.air ) cont = false;
						
						if (cont)
							world.spawnParticle(EnumParticleTypes.FLAME,
								spell.prevPosX + (spell.motionX * j) - world.rand.nextFloat() * 0.5F,
								spell.prevPosY + (spell.motionY * j) - world.rand.nextFloat() * 0.5F,
								spell.prevPosZ + (spell.motionZ * j) - world.rand.nextFloat() * 0.5F,
								0, 0, 0);
					}
				}
			}
			if (spell.getTicksInGround() > 0) spell.setDead();
			
		} else spell.setDead();
		
	}
	
	public boolean isPiercingSpell() {
		return true;
	}
	
	public float getSpellVelocity(){
		return 10.0F;
	}

}
