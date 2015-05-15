package com.himself12794.usefulthings.worldgen;

import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderEnd;

public class AnimusWorldProvider extends WorldProvider
{
	public void registerWorldChunkManager()
	{
		this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.desertHills, 0.1F);
		this.dimensionId = 2;
	}
	
	public IChunkProvider createChunkGenerator()
	{
		return new ChunkProviderEnd(worldObj, worldObj.getSeed());
	}
	
	@Override
	public String getDimensionName()
	{
		return "Tutorial";
	}

	@Override
	public String getInternalNameSuffix() {
		// TODO Auto-generated method stub
		return null;
	}
}