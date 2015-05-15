package com.himself12794.usefulthings.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.himself12794.usefulthings.spells.Spells;

public class EntitySpellBolt extends EntitySpellHitScan {

	public EntitySpellBolt(World worldIn) {
		super(worldIn);
		// TODO Auto-generated constructor stub
	}

	public EntitySpellBolt(World worldIn, Spells spell) {
		super(worldIn, spell);
		// TODO Auto-generated constructor stub
	}

	public EntitySpellBolt(World worldIn, EntityLivingBase throwerIn,
			Spells type) {
		super(worldIn, throwerIn, type);
		// TODO Auto-generated constructor stub
	}

	public EntitySpellBolt(World worldIn, double x, double y,
			double p_i1778_6_, Spells spell) {
		super(worldIn, x, y, p_i1778_6_, spell);
		// TODO Auto-generated constructor stub
	}
    
    protected float getVelocity() {
    	return 5.0f;
    }    
    
    @SideOnly(Side.CLIENT)
    public boolean isInRangeToRenderDist(double distance)
    {
        double d1 = this.getEntityBoundingBox().getAverageEdgeLength() * 4.0D;
        d1 *= 64.0D;
        return distance < d1 * d1;
    }

}
