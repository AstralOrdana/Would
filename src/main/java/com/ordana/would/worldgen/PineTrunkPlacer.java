package com.ordana.would.worldgen;

import com.google.common.collect.Lists;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ordana.would.reg.ModTrees;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
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

public class PineTrunkPlacer extends TrunkPlacer {

    public static final MapCodec<PineTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(objectInstance ->
            trunkPlacerParts(objectInstance).apply(objectInstance, PineTrunkPlacer::new));


    public PineTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModTrees.PINE_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(WorldGenLevel level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, int freeTreeHeight, BlockPos blockPos, TreeConfiguration config) {
        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        Direction clockDir = direction.getClockWise();
        if (random.nextBoolean()) clockDir = clockDir.getOpposite();
        BlockPos.MutableBlockPos pos = blockPos.mutable();
        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();

        //trunk
        this.placeLog(level, blockSetter, random, pos, config);
        for(int h = 0; h < freeTreeHeight; ++h) {
            if (TreeFeature.validTreePos(level, pos)) this.placeLog(level, blockSetter, random, pos, config);
            if (h > random.nextInt(2, 4)) {
                BlockPos prevPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
                for(int k = 0; k < 4; ++k) {
                    pos.move(clockDir);
                    Direction finalClockDir = clockDir;
                    this.placeLog(level, blockSetter, random, pos, config,(blockState) -> blockState.trySetValue(RotatedPillarBlock.AXIS, this.getLogAxis(pos, pos.relative(finalClockDir.getOpposite()))));
                    list.add(new FoliagePlacer.FoliageAttachment(pos.immutable(), clockDir.ordinal(), false));
                }
                pos.set(prevPos);
                if (random.nextBoolean()) {
                    for(int k = 0; k < 3; ++k) {
                        pos.move(clockDir.getOpposite());
                        Direction finalClockDir = clockDir.getOpposite();
                        this.placeLog(level, blockSetter, random, pos, config,(blockState) -> blockState.trySetValue(RotatedPillarBlock.AXIS, this.getLogAxis(pos, pos.relative(finalClockDir.getOpposite()))));
                        list.add(new FoliagePlacer.FoliageAttachment(pos.immutable(), clockDir.getOpposite().ordinal(), false));
                    }
                }
                clockDir = clockDir.getClockWise();
                pos.set(prevPos);
            }
            pos.move(Direction.UP);
        }
        pos.move(direction);
        for(int h = 0; h < freeTreeHeight - 1; ++h) {
            if (TreeFeature.validTreePos(level, pos)) this.placeLog(level, blockSetter, random, pos, config);
            //if (random.nextBoolean()) {
                BlockPos prevPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
                for(int k = 0; k < Math.max(4 - h, 2); ++k) {
                    pos.move(clockDir);
                    Direction finalClockDir = clockDir;
                    this.placeLog(level, blockSetter, random, pos, config,(blockState) -> blockState.trySetValue(RotatedPillarBlock.AXIS, this.getLogAxis(pos, pos.relative(finalClockDir.getOpposite()))));
                    list.add(new FoliagePlacer.FoliageAttachment(pos.immutable(), clockDir.ordinal(), false));
                }
                clockDir = clockDir.getClockWise();
                pos.set(prevPos);
            //}
            pos.move(Direction.UP);
        }
        pos.move(direction);
        for(int h = 0; h < freeTreeHeight - 2; ++h) {
            if (TreeFeature.validTreePos(level, pos)) this.placeLog(level, blockSetter, random, pos, config);
            //if (random.nextBoolean()) {
                BlockPos prevPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
                for(int k = 0; k < random.nextInt(1, 2); ++k) {
                    pos.move(clockDir);
                    Direction finalClockDir = clockDir;
                    this.placeLog(level, blockSetter, random, pos, config,(blockState) -> blockState.trySetValue(RotatedPillarBlock.AXIS, this.getLogAxis(pos, pos.relative(finalClockDir.getOpposite()))));
                    list.add(new FoliagePlacer.FoliageAttachment(pos.immutable(), clockDir.ordinal(), false));
                }
                clockDir = clockDir.getClockWise();
                pos.set(prevPos);
            //}
            pos.move(Direction.UP);
        }

        if (TreeFeature.validTreePos(level, pos)) this.placeLog(level, blockSetter, random, pos, config);
        clockDir = Direction.UP;
        list.add(new FoliagePlacer.FoliageAttachment(pos.immutable(), clockDir.ordinal(), false));
        return list;

    }


    protected void forceLog(BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, BlockPos pos, TreeConfiguration config, WorldGenLevel level) {
        this.forceLog(blockSetter, random, pos, config, Function.identity(), level);
    }

    protected void forceLog(BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, BlockPos pos, TreeConfiguration config, Function<BlockState, BlockState> propertySetter, WorldGenLevel level) {
        blockSetter.accept(pos, propertySetter.apply(config.trunkProvider.getState(level, random, pos)));
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
