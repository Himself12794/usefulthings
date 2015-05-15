package com.himself12794.usefulthings.spells;

import com.himself12794.usefulthings.UsefulThings;
import com.himself12794.usefulthings.network.MessageClient;
import com.himself12794.usefulthings.network.MessageServer;
import com.himself12794.usefulthings.util.UsefulMethods;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public enum Spells {
	
	DAMAGE(5.0F,"damage"),
	INCINERATE(2.0F, "fire"),
	KILL(1000.0F, "kill"),
	POISON(0.0F, "poison"),
	LIGHTNING(0.0F, "lightning");
	
	private float damage;
	private String type;

	Spells(float damage, String type) {
		this.type = type;
		this.damage = damage;
	}
	
	public void onStrike(World world, MovingObjectPosition target, EntityLivingBase caster) {        
		if (!world.isRemote) {
	        boolean flag;
	
	        if (target.entityHit != null)
	        {
	            flag = target.entityHit.attackEntityFrom(DamageSource.magic, this.damage);
	
	            if (flag) {

		        	if ("fire".equals(this.type)) {
		                if (!target.entityHit.isImmuneToFire()) {
		                    target.entityHit.setFire(20);
		                }
		        	} else if ("poison".equals(this.type) && (target.entityHit instanceof EntityLivingBase)) {
		        		EntityLivingBase entity = (EntityLivingBase) target.entityHit;
		        		entity.addPotionEffect(new PotionEffect(Potion.poison.getId(), 200, 1));
		        	} else if ("lightning".equals(this.type) && (target.entityHit instanceof EntityLivingBase)) {
		        		EntityLivingBase entity = (EntityLivingBase) target.entityHit;
		        		UsefulMethods.lightningStrike(world, (EntityPlayer) caster, entity.posX, entity.posY, entity.posZ);
		        		/*EntityLightningBolt bolt = new EntityLightningBolt(world, entity.posX, entity.posY, entity.posZ);
		        		bolt.getEntityData().setString("shooter", caster.getUniqueID().toString());
		        		world.spawnEntityInWorld(bolt);		
		        		NBTTagCompound msg = new NBTTagCompound();
						msg.setBoolean("lightning", true);
						msg.setDouble("x", entity.posX);
						msg.setDouble("y", entity.posY);
						msg.setDouble("z", entity.posZ);
						UsefulThings.proxy.network.sendTo(new MessageClient(msg),(EntityPlayerMP) caster);*/
		        	}
	            }
	        }
		} else {
			
		}
		
	}
}
