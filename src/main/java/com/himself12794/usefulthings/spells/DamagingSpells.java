package com.himself12794.usefulthings.spells;

import com.himself12794.usefulthings.UsefulThings;
import com.himself12794.usefulthings.entity.EntitySpell;
import com.himself12794.usefulthings.network.MessageClient;
import com.himself12794.usefulthings.network.MessageServer;
import com.himself12794.usefulthings.util.UsefulMethods;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public enum DamagingSpells {//implements Spelled {
	
	DAMAGE(5.0F, 0,"Damage"),
	INCINERATE(2.0F, 1, "Incinerate"),
	KILL(1000.0F, 2, "Avada Kedavra"),
	POISON(0.0F, 3, "Poison"),
	DRAIN(5.0F, 4, "Drain"),
	LIGHTNING(0.0F, 5, "Thunder");
	
	public static int type = 0;
	public int id;
	private float damage;
	public String info;

	DamagingSpells(float damage, int id, String name) {
		this.damage = damage;
		this.id = id;
		this.info = name;
	}
	
	/*public boolean cast(World world, EntityLivingBase caster, float modifier, ItemStack stack) {
		//EntitySpell casting = new EntitySpell(world, caster, this, stack);
		//return world.spawnEntityInWorld(casting);
	}*/
	
	public boolean onStrike(World world, MovingObjectPosition target, EntityLivingBase caster, float modifier, ItemStack stack) { 
		boolean successful = false;
		if (!world.isRemote) {
	        boolean flag;
	        
	        if (target.entityHit != null)
	        {	
	        	stack.damageItem(1, caster);
	            flag = target.entityHit.attackEntityFrom(DamageSource.magic, this.damage * modifier);
	            successful = true;
	            if (flag) {

		        	if (this == INCINERATE) {
		                if (!target.entityHit.isImmuneToFire()) {
		                    target.entityHit.setFire(20);
		                }
		        	} else if (this == POISON && (target.entityHit instanceof EntityLivingBase)) {
		        		EntityLivingBase entity = (EntityLivingBase) target.entityHit;
		        		entity.addPotionEffect(new PotionEffect(Potion.poison.getId(), 200, 1));
		        	} else if (this == LIGHTNING && (target.entityHit instanceof EntityLivingBase)) {
		        		EntityLivingBase entity = (EntityLivingBase) target.entityHit;
		        		UsefulMethods.lightningStrike(world, (EntityPlayer) caster, entity.posX, entity.posY, entity.posZ);
		        	} else if (this == DRAIN) {
		        		caster.heal(damage * modifier);
		        	}
	            }
	        }
		}
		return successful;
	}
	
	public float getDamage() {
		return damage;
	}

	public String getName() {
		return info;
	}

	public int getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	public int getType() {
		// TODO Auto-generated method stub
		return this.type;
	}
}
