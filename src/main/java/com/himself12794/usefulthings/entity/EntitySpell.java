package com.himself12794.usefulthings.entity;

import com.himself12794.usefulthings.spells.Spell;
import com.himself12794.usefulthings.spells.SpellType;
import com.himself12794.usefulthings.spells.Spells;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntitySpell extends EntityThrowable {

	private Spell spell;
	private float modifier;
	
	public EntitySpell(World worldIn){
		super(worldIn);
		this.spell = Spells.damage;
		this.modifier = 1.0F;
	}
	
	public EntitySpell(World worldIn, Spell spell) {
		super(worldIn);
		this.spell = spell;
		this.modifier = 1.0F;
		// TODO Auto-generated constructor stub
	}
	
	public EntitySpell(World worldIn, EntityLivingBase throwerIn, Spell spell, float modifier) {
		super(worldIn, throwerIn);
		this.spell = spell;
		this.modifier = modifier;
		// TODO Auto-generated constructor stub
	}

	public EntitySpell(World worldIn, double x, double y, double p_i1778_6_, Spell spell) {
		super(worldIn, x, y, p_i1778_6_);
		this.spell = spell;
		this.modifier = 1.0F;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onImpact(MovingObjectPosition movingObject) {
		spell.onStrike(this.worldObj, movingObject, this.getThrower(), 1);
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

    	double d1 = this.getEntityBoundingBox().getAverageEdgeLength() * 4.0D;
    	d1 *= 64.0D;
    	return distance < d1 * d1;

    }
    
    protected float getVelocity() {
    	return 2.0F;
    }

    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender(float p_70070_1_)
    {
        BlockPos blockpos = new BlockPos(this.posX, 0.0D, this.posZ);

        if (this.worldObj.isBlockLoaded(blockpos))
        {
            double d0 = (this.getEntityBoundingBox().maxY - this.getEntityBoundingBox().minY) * 0.66D;
            int i = MathHelper.floor_double(this.posY + d0);
            return this.worldObj.getCombinedLight(blockpos.up(i), 0);
        }
        else
        {
            return 0;
        }
    }

    /**
     * Gets how bright this entity is.
     */
    public float getBrightness(float p_70013_1_)
    {
    	if (spell == null) return 0.0F;
    	return spell.getBrightness();
    }

}
