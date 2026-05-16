package com.ordana.would.worldgen.trunk_placer;

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
import net.minecraft.world.level.levelgen.feature.trunkplacers.GiantTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class MegaPineTrunkPlacer extends GiantTrunkPlacer {

    public static final MapCodec<MegaPineTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(objectInstance ->
            trunkPlacerParts(objectInstance).apply(objectInstance, MegaPineTrunkPlacer::new));


    public MegaPineTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModTrees.MEGA_PINE_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(WorldGenLevel level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, int freeTreeHeight, BlockPos blockPos, TreeConfiguration config) {

        BlockPos belowPos = blockPos.below();
        /*FIXME
        setDirtAt(level, blockSetter, random, belowPos, config);
        setDirtAt(level, blockSetter, random, belowPos.east(), config);
        setDirtAt(level, blockSetter, random, belowPos.south(), config);
        setDirtAt(level, blockSetter, random, belowPos.south().east(), config);

         */


        Direction dir = Direction.Plane.HORIZONTAL.getRandomDirection(random);

        BlockPos.MutableBlockPos pos = blockPos.mutable();
        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();
        int branchlessHeight = random.nextIntBetweenInclusive(7, 10);



        BlockPos prevPos1 = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
        for (Direction baseDir : Direction.Plane.HORIZONTAL) {
            pos.move(baseDir, (baseDir == Direction.EAST || baseDir == Direction.SOUTH) ? 2 : 1);
            if (random.nextBoolean()) pos.move(baseDir == Direction.WEST || baseDir == Direction.SOUTH ? baseDir.getCounterClockWise() : baseDir.getClockWise());
            if (TreeFeature.validTreePos(level, pos)) this.placeLog(level, blockSetter, random, pos, config);

            if (random.nextBoolean()) {
                pos.move(Direction.UP);
                if (TreeFeature.validTreePos(level, pos)) this.placeLog(level, blockSetter, random, pos, config);
                pos.move(Direction.DOWN);
            }
            if (random.nextBoolean()) {
                pos.move(baseDir);
                if (TreeFeature.validTreePos(level, pos)) this.placeLog(level, blockSetter, random, pos, config);
                pos.move(baseDir.getOpposite());
            }

            pos.set(prevPos1);

        }

        if (TreeFeature.validTreePos(level, pos)) this.placeLog(level, blockSetter, random, pos, config);
        if (TreeFeature.validTreePos(level, pos.relative(Direction.SOUTH))) this.placeLog(level, blockSetter, random, pos.relative(Direction.SOUTH), config);
        if (TreeFeature.validTreePos(level, pos.relative(Direction.EAST))) this.placeLog(level, blockSetter, random, pos.relative(Direction.EAST), config);
        if (TreeFeature.validTreePos(level, pos.relative(Direction.SOUTH).relative(Direction.EAST))) this.placeLog(level, blockSetter, random, pos.relative(Direction.SOUTH).relative(Direction.EAST), config);



        // BOTTOM
        for(int h = 0; h < freeTreeHeight; ++h) {
            if (TreeFeature.validTreePos(level, pos)) this.placeLog(level, blockSetter, random, pos, config);
            if (TreeFeature.validTreePos(level, pos.relative(Direction.SOUTH))) this.placeLog(level, blockSetter, random, pos.relative(Direction.SOUTH), config);
            if (TreeFeature.validTreePos(level, pos.relative(Direction.EAST))) this.placeLog(level, blockSetter, random, pos.relative(Direction.EAST), config);
            if (TreeFeature.validTreePos(level, pos.relative(Direction.SOUTH).relative(Direction.EAST))) this.placeLog(level, blockSetter, random, pos.relative(Direction.SOUTH).relative(Direction.EAST), config);

            //branches
            if (h > branchlessHeight) {
                BlockPos prevPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
                for(int k = 0; k < 4; ++k) {

                    //lowest level check for offset
                    if (k == 0) {
                        pos.move(dir, (dir == Direction.EAST || dir == Direction.SOUTH) ? 2 : 1);
                        if (random.nextBoolean()) pos.move(dir == Direction.WEST || dir == Direction.SOUTH ? dir.getCounterClockWise() : dir.getClockWise());
                    }

                    Direction finalDir = dir;
                    this.placeLog(level, blockSetter, random, pos, config,(blockState) -> blockState.trySetValue(RotatedPillarBlock.AXIS, this.getLogAxis(pos, pos.relative(finalDir.getOpposite()))));
                    list.add(new FoliagePlacer.FoliageAttachment(pos.immutable(), dir.ordinal(), false));
                }
                //branch droop
                pos.move(Direction.DOWN);
                if (random.nextBoolean()) pos.move(random.nextBoolean() ? dir.getClockWise() : dir.getCounterClockWise());
                for(int k = 0; k < 2; ++k) {
                    pos.move(dir);
                    Direction finaldir = dir;
                    this.placeLog(level, blockSetter, random, pos, config,(blockState) -> blockState.trySetValue(RotatedPillarBlock.AXIS, this.getLogAxis(pos, pos.relative(finaldir.getOpposite()))));
                    list.add(new FoliagePlacer.FoliageAttachment(pos.immutable(), dir.ordinal(), false));
                }
                pos.move(Direction.DOWN);
                for(int k = 0; k < 3; ++k) {
                    pos.move(dir);
                    Direction finaldir = dir;
                    this.placeLog(level, blockSetter, random, pos, config,(blockState) -> blockState.trySetValue(RotatedPillarBlock.AXIS, this.getLogAxis(pos, pos.relative(finaldir.getOpposite()))));
                    list.add(new FoliagePlacer.FoliageAttachment(pos.immutable(), dir.ordinal(), false));
                }
                pos.set(prevPos);

                /*
                if (random.nextBoolean()) {
                    for(int k = 0; k < 3; ++k) {
                        pos.move(dir.getOpposite());
                        Direction finaldir = dir.getOpposite();
                        this.placeLog(level, blockSetter, random, pos, config,(blockState) -> blockState.trySetValue(RotatedPillarBlock.AXIS, this.getLogAxis(pos, pos.relative(finaldir.getOpposite()))));
                        list.add(new FoliagePlacer.FoliageAttachment(pos.immutable(), dir.getOpposite().ordinal(), false));
                    }
                }

                 */
                dir = dir.getClockWise();
                pos.set(prevPos);
            }
            pos.move(Direction.UP);
        }


        // MIDDLE
        for(int h = 0; h < freeTreeHeight - 5; ++h) {
            if (TreeFeature.validTreePos(level, pos)) this.placeLog(level, blockSetter, random, pos, config);
            if (TreeFeature.validTreePos(level, pos.relative(Direction.SOUTH))) this.placeLog(level, blockSetter, random, pos.relative(Direction.SOUTH), config);
            if (TreeFeature.validTreePos(level, pos.relative(Direction.EAST))) this.placeLog(level, blockSetter, random, pos.relative(Direction.EAST), config);
            if (TreeFeature.validTreePos(level, pos.relative(Direction.SOUTH).relative(Direction.EAST))) this.placeLog(level, blockSetter, random, pos.relative(Direction.SOUTH).relative(Direction.EAST), config);

            BlockPos prevPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
            for(int k = 0; k < Math.max(4 - h, 2); ++k) {

                //lowest level check for offset
                if (k == 0) {
                    pos.move(dir, (dir == Direction.EAST || dir == Direction.SOUTH) ? 2 : 1);
                    if (random.nextBoolean()) pos.move(dir == Direction.WEST || dir == Direction.SOUTH ? dir.getCounterClockWise() : dir.getClockWise());
                }

                Direction finalDir = dir;
                this.placeLog(level, blockSetter, random, pos, config,(blockState) -> blockState.trySetValue(RotatedPillarBlock.AXIS, this.getLogAxis(pos, pos.relative(finalDir.getOpposite()))));
                list.add(new FoliagePlacer.FoliageAttachment(pos.immutable(), dir.ordinal(), false));
            }

            //droop branch
            pos.move(Direction.DOWN);
            for(int k = 0; k < 2; ++k) {
                pos.move(dir);
                Direction finaldir = dir;
                this.placeLog(level, blockSetter, random, pos, config,(blockState) -> blockState.trySetValue(RotatedPillarBlock.AXIS, this.getLogAxis(pos, pos.relative(finaldir.getOpposite()))));
                list.add(new FoliagePlacer.FoliageAttachment(pos.immutable(), dir.ordinal(), false));
            }
            dir = dir.getClockWise();
            pos.set(prevPos);
            //}
            pos.move(Direction.UP);
        }

        //TOP
        int p = random.nextIntBetweenInclusive(1, 4);
        if (p == 2) {
            pos.move(Direction.SOUTH);
        }
        if (p == 3) {
            pos.move(Direction.EAST);
        }
        if (p == 4) {
            pos.move(Direction.SOUTH).relative(Direction.EAST);
        }
        for(int h = 0; h < 7; ++h) {
            if (TreeFeature.validTreePos(level, pos)) this.placeLog(level, blockSetter, random, pos, config);

            //if (random.nextBoolean()) {
            BlockPos prevPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
            for(int k = 0; k < random.nextIntBetweenInclusive(1, 2); ++k) {
                pos.move(dir);
                Direction finalDir = dir;
                this.placeLog(level, blockSetter, random, pos, config,(blockState) -> blockState.trySetValue(RotatedPillarBlock.AXIS, this.getLogAxis(pos, pos.relative(finalDir.getOpposite()))));
                list.add(new FoliagePlacer.FoliageAttachment(pos.immutable(), dir.ordinal(), false));
            }
            dir = dir.getClockWise();
            pos.set(prevPos);
            pos.move(Direction.UP);
        }

        if (TreeFeature.validTreePos(level, pos)) this.placeLog(level, blockSetter, random, pos, config);
        dir = Direction.UP;
        list.add(new FoliagePlacer.FoliageAttachment(pos.immutable(), dir.ordinal(), false));
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

    private void placeLogIfFreeWithOffset(WorldGenLevel level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, BlockPos.MutableBlockPos pos, TreeConfiguration config, BlockPos offsetPos, int offsetX, int offsetY, int offsetZ) {
        pos.setWithOffset(offsetPos, offsetX, 0, offsetZ);
        this.placeLogIfFree(level, blockSetter, random, pos, config);
    }
}
