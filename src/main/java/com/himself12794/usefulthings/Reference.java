package com.himself12794.usefulthings;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class Reference {
    public static final String MODID = "usefulthings";
    public static final String VERSION = "1.0";
    public static final String NAME = "UsefulThings";

    /**
     * Gets block at world position.
     *  
     * @param pos The position of the block
     * @param worldIn The world where the block is located
     * @return The block at location {@code pos} in world {@code worldIn}
     */    
    public static Block getBlockAtPos(BlockPos pos, World worldIn ){
    	
        IBlockState iblockstate1 = worldIn.getBlockState(pos);
    	return iblockstate1.getBlock();
    }
    
    /*public static double distanceBetweenLocation( BlockPos a, BlockPos b) {
    	return sqrt((b.distanceSq(toX, toY, toZ)x2 - x1)^2 + (y2 - y1)^2 + (z2 - z1)^2) 
    }*/
}