package com.himself12794.usefulthings.spell;

import com.himself12794.usefulthings.UsefulThings;
import com.himself12794.usefulthings.entity.EntitySpell;
import com.himself12794.usefulthings.network.CastSpellHomingServer;
import com.himself12794.usefulthings.network.CastSpellInstantServer;
import com.himself12794.usefulthings.network.ServerMessageQueue;
import com.himself12794.usefulthings.util.Reference;
import com.himself12794.usefulthings.util.UsefulMethods;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class SpellHoming extends Spell implements IProjectileSpell {

	@Override
	public boolean cast(World world, EntityLivingBase caster, ItemStack tome, float modifier) {
		
		boolean flag1 = onCast(world, caster, tome, modifier);
		EntitySpell casting = new EntitySpell(world, caster, this, modifier, getTarget(world, caster) );
		boolean flag2 = world.spawnEntityInWorld(casting);
		return flag1 && flag2;
		
	}
	
	public float getSpellVelocity() {
		return 0.5F;
	}

	@Override
	public MovingObjectPosition getTarget(World world, EntityLivingBase caster) {
		//return type.action(this, world, caster, tome, modifier);
		
		if (world.isRemote) {
			
			MovingObjectPosition pos = UsefulMethods.getMouseOverExtended(50);
			
			IMessage msg = new CastSpellHomingServer(pos);
			UsefulThings.proxy.network.sendToServer(msg);
			return pos;
			
		} else  {
			
			MovingObjectPosition pos = ServerMessageQueue.MovingObjectsQueue.getNextPostionInQueue();
			if (pos != null) System.out.println("What do you know, it worked! Position: " + pos.hitVec);
			return pos;
			
		}
		
	}

	@Override
	public void onUpdate(EntitySpell entitySpell, MovingObjectPosition target) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isPiercingSpell() {
		// TODO Auto-generated method stub
		return false;
	}

}
