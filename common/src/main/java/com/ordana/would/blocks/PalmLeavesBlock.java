package com.ordana.would.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.RedStoneWireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.RedstoneSide;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PalmLeavesBlock extends LeavesBlock {
    public PalmLeavesBlock(Properties properties) {
        super(properties);
    }

    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        level.scheduleTick(pos, this, 1);
        if (this.decaying(state)) {
            dropResources(state, level, pos);

            level.updateNeighborsAt(pos.above(), this);
            level.updateNeighborsAt(pos.below(), this);
            for (Direction dir : Direction.Plane.HORIZONTAL) {
                level.updateNeighborsAt(pos.relative(dir), this);
                level.updateNeighborsAt(pos.relative(dir.getClockWise()), this);
                level.updateNeighborsAt(pos.relative(dir).above(), this);
                level.updateNeighborsAt(pos.relative(dir.getClockWise()).above(), this);
                level.updateNeighborsAt(pos.relative(dir).below(), this);
                level.updateNeighborsAt(pos.relative(dir.getClockWise()).below(), this);
            }

            level.removeBlock(pos, false);
        }
    }

    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        level.setBlock(pos, updateDistance(state, level, pos), 3);
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidState = context.getLevel().getFluidState(context.getClickedPos());
        BlockState blockState = this.defaultBlockState().setValue(PERSISTENT, true).setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
        return updateDistance(blockState, context.getLevel(), context.getClickedPos());
    }


    private static BlockState updateDistance(BlockState state, LevelAccessor level, BlockPos pos) {
        int i = 7;

        List<BlockState> states = new ArrayList<>();

        //states.add(level.getBlockState(pos));
        states.add(level.getBlockState(pos.above()));
        states.add(level.getBlockState(pos.below()));
        for (Direction dir : Direction.Plane.HORIZONTAL) {
            states.add(level.getBlockState(pos.relative(dir)));
            states.add(level.getBlockState(pos.relative(dir.getClockWise())));
            states.add(level.getBlockState(pos.relative(dir).above()));
            states.add(level.getBlockState(pos.relative(dir.getClockWise()).above()));
            states.add(level.getBlockState(pos.relative(dir).below()));
            states.add(level.getBlockState(pos.relative(dir.getClockWise()).below()));
        }

        for (BlockState blockState : states) {
            if (blockState.is(BlockTags.LOGS)) {
                return state.setValue(DISTANCE, 1);
            }
            if (blockState.getBlock() instanceof PalmLeavesBlock) {
                i = Math.min(blockState.getValue(DISTANCE), i);

                if (i == 1) {
                    return state.setValue(DISTANCE, 2);
                }
            }
        }

        if (states.isEmpty()) {
            BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();
            Direction[] dir2 = Direction.values();
            int limit2 = dir2.length;
            for (int k = 0; k < limit2; ++k) {
                Direction direction = dir2[k];
                mutableBlockPos.setWithOffset(pos, direction);
                i = Math.min(i, getDistanceAt(level.getBlockState(mutableBlockPos)) + 1);
                if (i == 1) {
                    break;
                }
            }
        }

        return state.setValue(DISTANCE, Math.min(i + 1, 7));
    }

    private static int getDistanceAt(BlockState neighbor) {
        return getOptionalDistanceAt(neighbor).orElse(7);
    }

}
