package com.ordana.would.worldgen;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
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

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class WillowTrunkPlacer extends TrunkPlacer {

    public static final Codec<WillowTrunkPlacer> CODEC = RecordCodecBuilder.create(objectInstance ->
            trunkPlacerParts(objectInstance).apply(objectInstance, WillowTrunkPlacer::new));

    public WillowTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModTrees.WILLOW_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, int freeTreeHeight, BlockPos blockPos, TreeConfiguration config) {
        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        Direction clockDir = direction.getClockWise();
        if (random.nextBoolean()) clockDir = clockDir.getOpposite();
        BlockPos.MutableBlockPos pos = blockPos.mutable();
        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();


        //base
        for (Direction dir : Direction.Plane.HORIZONTAL.shuffledCopy(random)) {
            pos.move(dir);
            pos.move(Direction.DOWN);
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

        //trunk
        this.placeLog(level, blockSetter, random, pos, config);
        for(int h = 0; h < freeTreeHeight; ++h) {
            if (TreeFeature.validTreePos(level, pos)) this.placeLog(level, blockSetter, random, pos, config);
            pos.move(Direction.UP);
        }
        pos.move(direction);
        for(int h = 0; h < 2; ++h) {
            if (TreeFeature.validTreePos(level, pos)) this.placeLog(level, blockSetter, random, pos, config);
            pos.move(Direction.UP);
        }

        //optional branch 1
        if (random.nextBoolean()) {
            pos.move(clockDir);
            pos.move(Direction.DOWN);
            for(int h = 0; h < 2; ++h) {
                if (TreeFeature.validTreePos(level, pos)) {
                    Direction finalClockDir = clockDir;
                    this.placeLog(level, blockSetter, random, pos, config,(blockState) -> blockState.trySetValue(RotatedPillarBlock.AXIS, this.getLogAxis(blockPos.relative(finalClockDir.getOpposite()), pos)));
                }
                pos.move(clockDir);
            }
            pos.move(Direction.DOWN);
            if (TreeFeature.validTreePos(level, pos)) this.placeLog(level, blockSetter, random, pos, config);
            list.add(new FoliagePlacer.FoliageAttachment(pos.immutable(), 0, false));
            pos.move(Direction.UP);
            pos.move(Direction.UP);
            pos.move(clockDir.getOpposite());
            pos.move(clockDir.getOpposite());
            pos.move(clockDir.getOpposite());
        }


        pos.move(direction);
        if (TreeFeature.validTreePos(level, pos)) this.placeLog(level, blockSetter, random, pos, config);
        pos.move(Direction.UP);

        //optional branch 2
        if (random.nextBoolean()) {
            clockDir = clockDir.getOpposite();
            pos.move(clockDir);
            pos.move(Direction.DOWN);
            for(int h = 0; h < 2; ++h) {
                if (TreeFeature.validTreePos(level, pos)) {
                    Direction finalClockDir = clockDir;
                    this.placeLog(level, blockSetter, random, pos, config,(blockState) -> blockState.trySetValue(RotatedPillarBlock.AXIS, this.getLogAxis(blockPos.relative(finalClockDir.getOpposite()).relative(finalClockDir.getOpposite()).relative(finalClockDir.getOpposite()), pos)));
                }
                pos.move(clockDir);
            }
            pos.move(Direction.DOWN);
            if (TreeFeature.validTreePos(level, pos)) this.placeLog(level, blockSetter, random, pos, config);
            list.add(new FoliagePlacer.FoliageAttachment(pos.immutable(), 0, false));
            pos.move(Direction.UP);
            pos.move(Direction.UP);
            pos.move(clockDir.getOpposite());
            pos.move(clockDir.getOpposite());
            pos.move(clockDir.getOpposite());
        }

        //optional branch 3
        if (random.nextBoolean()) {
            pos.move(direction.getOpposite());
            if (TreeFeature.validTreePos(level, pos)) this.placeLog(level, blockSetter, random, pos, config);
            pos.move(Direction.UP);
            if (TreeFeature.validTreePos(level, pos)) this.placeLog(level, blockSetter, random, pos, config);
            pos.move(Direction.UP);
            pos.move(direction.getOpposite());
            if (TreeFeature.validTreePos(level, pos)) this.placeLog(level, blockSetter, random, pos, config);
            list.add(new FoliagePlacer.FoliageAttachment(pos.immutable(), 0, false));
            pos.move(Direction.DOWN);
            pos.move(Direction.DOWN);
            pos.move(direction);
            pos.move(direction);
        }


        pos.move(direction);
        for(int h = 0; h < heightRandA; ++h) {
            this.placeLog(level, blockSetter, random, pos, config, (blockState) -> blockState.trySetValue(RotatedPillarBlock.AXIS, this.getLogAxis(blockPos, pos)));
            pos.move(direction);
        }
        pos.move(Direction.DOWN);
        this.placeLog(level, blockSetter, random, pos, config, (blockState) -> blockState.trySetValue(RotatedPillarBlock.AXIS, this.getLogAxis(blockPos, pos)));
        pos.move(Direction.UP);
        list.add(new FoliagePlacer.FoliageAttachment(pos.immutable(), 0, false));
        return list;

    }


    protected void forceLog(BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, BlockPos pos, TreeConfiguration config) {
        this.forceLog(blockSetter, random, pos, config, Function.identity());
    }

    protected void forceLog(BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, BlockPos pos, TreeConfiguration config, Function<BlockState, BlockState> propertySetter) {
        blockSetter.accept(pos, propertySetter.apply(config.trunkProvider.getState(random, pos)));
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
