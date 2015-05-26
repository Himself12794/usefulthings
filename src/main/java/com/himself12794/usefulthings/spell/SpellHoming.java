package com.himself12794.usefulthings.spell;

import com.himself12794.usefulthings.UsefulThings;
import com.himself12794.usefulthings.entity.EntitySpell;
import com.himself12794.usefulthings.network.SetHomingSpellTargetServer;
import com.himself12794.usefulthings.network.CastSpellHomingServer2;
import com.himself12794.usefulthings.network.CastSpellInstantServer;
import com.himself12794.usefulthings.network.ServerMessageQueue;
import com.himself12794.usefulthings.network.ServerMessageQueue.CastObject;
import com.himself12794.usefulthings.proxy.CommonProxy;
import com.himself12794.usefulthings.util.Reference;
import com.himself12794.usefulthings.util.UsefulMethods;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class SpellHoming extends SpellRanged implements IHomingSpell {

	public void onUpdate(EntitySpell spell) {				
		for (float j = 0.0F; j < 1.0F; j += 0.05F) {
		
			for (int i = 0; i < 10; ++i) {
			
				spell.worldObj.spawnParticle(EnumParticleTypes.FLAME,
					spell.prevPosX + (spell.motionX * j) - spell.worldObj.rand.nextFloat() * 0.5F,
					spell.prevPosY + (spell.motionY * j) - spell.worldObj.rand.nextFloat() * 0.5F,
					spell.prevPosZ + (spell.motionZ * j) - spell.worldObj.rand.nextFloat() * 0.5F,
					0, 0, 0);
			}
		}
	}

	@Override
	public MovingObjectPosition getTarget(EntitySpell spell, MovingObjectPosition target) {
		// TODO Auto-generated method stub
		if (spell.target == null && spell.worldObj.isRemote) {
			
			MovingObjectPosition pos = UsefulMethods.getEntityLookEntity(spell, 50);
			SetHomingSpellTargetServer msg = new SetHomingSpellTargetServer(spell, pos);
			UsefulThings.proxy.network.sendToServer(msg);
			
			return pos;
			
		}
		return target;
	}

}
