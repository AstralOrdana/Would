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
import java.util.function.Function;

public class BaldCypressTrunkPlacer extends TrunkPlacer {

    public static final MapCodec<BaldCypressTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(objectInstance ->
            trunkPlacerParts(objectInstance).apply(objectInstance, BaldCypressTrunkPlacer::new));

    public BaldCypressTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected @NotNull TrunkPlacerType<?> type() {
        return ModTrees.BALD_CYPRESS_TRUNK_PLACER.get();
    }

    @Override
    public @NotNull List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, int freeTreeHeight, BlockPos blockPos, TreeConfiguration config) {

        BlockPos.MutableBlockPos pos = blockPos.mutable();
        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();
        BlockPos centerPos = pos;

        //base
        for (Direction dir : Direction.Plane.HORIZONTAL.shuffledCopy(random)) {
            pos.move(dir);

            pos.move(Direction.DOWN);
            this.forceLog(blockSetter, random, pos, config);
            pos.move(Direction.UP);
            this.forceLog(blockSetter, random, pos, config);
            if (random.nextBoolean()) {
                pos.move(Direction.UP);
                this.forceLog(blockSetter, random, pos, config);
                if (random.nextBoolean()) {
                    pos.move(Direction.UP);
                    this.forceLog(blockSetter, random, pos, config);
                }
            }

            pos.set(blockPos);
        }

        this.placeLog(level, blockSetter, random, pos, config);
        var bl = false;
        for(int h = 0; h < freeTreeHeight; ++h) {
            if (TreeFeature.validTreePos(level, pos)) this.placeLog(level, blockSetter, random, pos, config);
            if (random.nextFloat() > 0.8 && h > 2 && h < freeTreeHeight - 2 && !bl) {
                Direction dir = Direction.Plane.HORIZONTAL.getRandomDirection(random);
                pos.move(dir);
                placeAxisLog(pos, dir, level, blockSetter, random, freeTreeHeight, blockPos, config);
                pos.move(dir);
                placeAxisLog(pos, dir, level, blockSetter, random, freeTreeHeight, blockPos, config);
                list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(pos.getX(), pos.getY(), pos.getZ()), 0, false));
                pos.move(dir.getOpposite());
                pos.move(dir.getOpposite());
                bl = true;
            }
            else bl = false;
            pos.move(Direction.UP);
        }

        list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(pos.getX(), pos.getY() + 2, pos.getZ()), 0, false));
        centerPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
        for (Direction dir : Direction.Plane.HORIZONTAL.shuffledCopy(random)) {
            pos.move(dir);
            if (TreeFeature.validTreePos(level, pos)) this.placeLog(level, blockSetter, random, pos, config);
            pos.move(Direction.UP);
            pos.move(dir);
            if (TreeFeature.validTreePos(level, pos)) this.placeLog(level, blockSetter, random, pos, config);
            BlockPos leafPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
            list.add(new FoliagePlacer.FoliageAttachment(leafPos, 0, false));
            list.add(new FoliagePlacer.FoliageAttachment(leafPos.relative(dir.getClockWise(), 1).below(), 0, false));
            pos.set(centerPos);
        }




        list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(pos.getX(), pos.getY(), pos.getZ()), 0, false));
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


    protected void forceLog(BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, BlockPos pos, TreeConfiguration config) {
        this.forceLog(blockSetter, random, pos, config, Function.identity());
    }
    protected void forceLog(BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, BlockPos pos, TreeConfiguration config, Function<BlockState, BlockState> propertySetter) {
        blockSetter.accept(pos, propertySetter.apply(config.trunkProvider.getState(random, pos)));
    }

}
