package com.himself12794.usefulthings.entity;

import com.himself12794.usefulthings.spells.Spells;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntitySpellHitScan extends EntityThrowable {

	private Spells spell;
	
	public EntitySpellHitScan(World worldIn){
		super(worldIn);
		this.spell = Spells.DAMAGE;
	}
	
	public EntitySpellHitScan(World worldIn, Spells spell) {
		super(worldIn);
		this.spell = spell;
		// TODO Auto-generated constructor stub
	}

	public EntitySpellHitScan(World worldIn, EntityLivingBase throwerIn, Spells type) {
		super(worldIn, throwerIn);
		this.spell = type;
		// TODO Auto-generated constructor stub
	}

	public EntitySpellHitScan(World worldIn, double x, double y, double p_i1778_6_, Spells spell) {
		super(worldIn, x, y, p_i1778_6_);
		this.spell = spell;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onImpact(MovingObjectPosition movingObject) {
		//SpellIncinerate spell = new SpellIncinerate();
		spell.onStrike(this.worldObj, movingObject, this.getThrower());
		this.setDead();

	}    
	/**
     * Gets the amount of gravity to apply to the thrown entity with each tick.
     */
    protected float getGravityVelocity()
    {
        return 0.0F;
    }
    
    @SideOnly(Side.CLIENT)
    public boolean isInRangeToRenderDist(double distance)
    {
    	return false;
    }
    
    protected float getVelocity() {
    	return 20.0f;
    }

}
