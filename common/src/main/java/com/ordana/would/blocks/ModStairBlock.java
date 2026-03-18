package com.ordana.would.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;

public class ModStairBlock extends StairBlock {
	public ModStairBlock(Block baseState, Properties properties) {
		super(baseState.defaultBlockState(), properties);
	}
}
