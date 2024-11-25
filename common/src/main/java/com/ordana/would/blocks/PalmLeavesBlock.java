package com.ordana.would.blocks;

import com.ordana.would.reg.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.MangrovePropaguleBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public class PalmLeavesBlock extends LeavesBlock implements BonemealableBlock {
    public PalmLeavesBlock(Properties properties) {
        super(properties);
    }

    public void updateIndirectNeighbourShapes(BlockState state, LevelAccessor level, BlockPos pos, int flags, int recursionLeft) {
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();

        for (Direction dir : Direction.Plane.HORIZONTAL.shuffledCopy(level.getRandom())) {
            mutableBlockPos.move(dir);
            mutableBlockPos.move(dir.getClockWise());
            if(level.getBlockState(mutableBlockPos).is(this)) level.neighborShapeChanged(dir.getClockWise().getOpposite(), level.getBlockState(mutableBlockPos), mutableBlockPos, pos, flags, recursionLeft);
            mutableBlockPos.set(pos.relative(dir));

            mutableBlockPos.move(dir.getClockWise());
            mutableBlockPos.move(Direction.DOWN);
            if(level.getBlockState(mutableBlockPos).is(this)) level.neighborShapeChanged(Direction.UP, level.getBlockState(mutableBlockPos), mutableBlockPos, pos, flags, recursionLeft);
            mutableBlockPos.set(pos.relative(dir));

            mutableBlockPos.move(dir.getClockWise());
            mutableBlockPos.move(Direction.UP);
            if(level.getBlockState(mutableBlockPos).is(this)) level.neighborShapeChanged(Direction.DOWN, level.getBlockState(mutableBlockPos), mutableBlockPos, pos, flags, recursionLeft);
            mutableBlockPos.set(pos.relative(dir));

            mutableBlockPos.move(Direction.DOWN);
            if(level.getBlockState(mutableBlockPos).is(this)) level.neighborShapeChanged(Direction.UP, level.getBlockState(mutableBlockPos), mutableBlockPos, pos, flags, recursionLeft);
            mutableBlockPos.set(pos.relative(dir));

            mutableBlockPos.move(Direction.UP);
            if(level.getBlockState(mutableBlockPos).is(this)) level.neighborShapeChanged(Direction.DOWN, level.getBlockState(mutableBlockPos), mutableBlockPos, pos, flags, recursionLeft);
            mutableBlockPos.set(pos);

        }
    }

    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        level.setBlock(pos, updateDistance(state, level, pos), 3);
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


    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state, boolean isClient) {
        return level.getBlockState(pos.below()).isAir();
    }

    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        level.setBlock(pos.below(), ModBlocks.COCONUT.get().defaultBlockState().setValue(CoconutBlock.GREEN, true).setValue(CoconutBlock.GROWABLE, true), 2);
    }
}
