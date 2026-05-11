package com.ordana.would.worldgen;

import com.google.common.collect.Lists;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ordana.would.reg.ModTrees;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.BiConsumer;

public class WalnutTrunkPlacer extends TrunkPlacer {

    public static final MapCodec<WalnutTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(objectInstance ->
            trunkPlacerParts(objectInstance).apply(objectInstance, WalnutTrunkPlacer::new));

    public WalnutTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected @NotNull TrunkPlacerType<?> type() {
        return ModTrees.WALNUT_TRUNK_PLACER;
    }

    @Override
    public @NotNull List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, int freeTreeHeight, BlockPos blockPos, TreeConfiguration config) {

        BlockPos.MutableBlockPos pos = blockPos.mutable();
        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();

        this.placeLog(level, blockSetter, random, pos, config);

        list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(pos.getX(), pos.getY() + 5, pos.getZ()), 0, false));
        for (Direction dir : Direction.Plane.HORIZONTAL) {
            pos.move(dir);
            placeAxisLog(pos, dir, level, blockSetter, random, freeTreeHeight, blockPos, config);
            pos.move(dir);
            placeAxisLog(pos, dir, level, blockSetter, random, freeTreeHeight, blockPos, config);
            pos.move(dir);
            if (random.nextBoolean()) placeAxisLog(pos, dir, level, blockSetter, random, freeTreeHeight, blockPos, config);
            pos.move(Direction.UP);
            this.placeLog(level, blockSetter, random, pos, config);
            if (random.nextBoolean()) {
                pos.move(dir.getOpposite());
                this.placeLog(level, blockSetter, random, pos, config);
                pos.move(dir);
            }
            for (int k = 0; k <= baseHeight; ++k) {
                pos.move(Direction.UP);
                this.placeLog(level, blockSetter, random, pos, config);
            }
            if (random.nextBoolean()) {
                pos.move(Direction.UP);
                this.placeLog(level, blockSetter, random, pos, config);
            }
            if (random.nextBoolean()) pos.move(Direction.UP);
            pos.move(dir.getOpposite());
            placeAxisLog(pos, dir, level, blockSetter, random, freeTreeHeight, blockPos, config);
            pos.set(blockPos);
        }

        return list;
    }

    private void placeAxisLog(BlockPos.MutableBlockPos pos, Direction dir, LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, int freeTreeHeight, BlockPos blockPos, TreeConfiguration config) {
        if (TreeFeature.validTreePos(level, pos)) this.placeLog(level, blockSetter, random, pos, config, (blockState) ->
                blockState.trySetValue(RotatedPillarBlock.AXIS, this.getLogAxis(blockPos.relative(dir.getOpposite()), pos)));
    }

    private Direction.Axis getLogAxis(BlockPos pos, BlockPos otherPos) {
        Direction.Axis axis = Direction.Axis.Y;
        int i = Math.abs(otherPos.getX() - pos.getX());
        int j = Math.abs(otherPos.getZ() - pos.getZ());
        int k = Math.max(i, j);
        if (k > 0) {
            if (i == k) {
                axis = Direction.Axis.X;
            } else {
                axis = Direction.Axis.Z;
            }
        }

        return axis;
    }
}
