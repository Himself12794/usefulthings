package com.himself12794.usefulthings.player;

import net.minecraft.entity.Entity;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class Teleporto extends Teleporter {

	public Teleporto(WorldServer worldIn) {
		super(worldIn);
	}
	
	@Override
    public void placeInPortal(Entity entityIn, float rotationYaw){}
}
