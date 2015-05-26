package com.himself12794.usefulthings.worldgen;

import java.util.Random;

import com.himself12794.usefulthings.blocks.AnimusOre;
import com.himself12794.usefulthings.blocks.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class WorldGen 
{
	public static void addOreGenerators(){

        GameRegistry.registerWorldGenerator(new AnimusOreGen(), 0);
	}
}