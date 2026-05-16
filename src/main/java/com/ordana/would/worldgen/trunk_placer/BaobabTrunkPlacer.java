package com.ordana.would.worldgen.trunk_placer;

import com.google.common.collect.Lists;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ordana.would.reg.ModTrees;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.BiConsumer;

public class BaobabTrunkPlacer extends TrunkPlacer {

    public static final MapCodec<BaobabTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(objectInstance ->
            trunkPlacerParts(objectInstance).apply(objectInstance, BaobabTrunkPlacer::new));

    public BaobabTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected @NotNull TrunkPlacerType<?> type() {
        return ModTrees.BAOBAB_TRUNK_PLACER;
    }

    @Override
    public @NotNull List<FoliagePlacer.FoliageAttachment> placeTrunk(WorldGenLevel level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, int freeTreeHeight, BlockPos pos, TreeConfiguration config) {
        BlockPos blockPos = pos.below();
        BlockPos.MutableBlockPos mPos = new BlockPos.MutableBlockPos();
        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();
        var j = freeTreeHeight / 2;


        if (random.nextBoolean()) {
            int y = 0;

            //trunk
            for (int i = 0; i < freeTreeHeight; ++i) {
                placeLogIfFreeWithOffset(level, blockSetter, random, mPos, config, pos, 0, i, 0);
                placeLogIfFreeWithOffset(level, blockSetter, random, mPos, config, pos, 1, i, 0);
                placeLogIfFreeWithOffset(level, blockSetter, random, mPos, config, pos, -1, i, 0);
                placeLogIfFreeWithOffset(level, blockSetter, random, mPos, config, pos, 0, i, 1);
                placeLogIfFreeWithOffset(level, blockSetter, random, mPos, config, pos, 0, i, -1);
                //y = i;
            }

            //vertical branch 1


            BlockPos homePos = new BlockPos(mPos.getX(), mPos.getY(), mPos.getZ());
            mPos.set(new BlockPos(mPos.getX(), mPos.getY(), mPos.getZ()));
            for (int k = 0; k < 2; ++k) {
                Direction ranDir = Direction.Plane.HORIZONTAL.shuffledCopy(random).listIterator().next();
                mPos.move(Direction.SOUTH);
                mPos.move(ranDir);

                for (int i = 0; i < (random.nextBoolean() ? 2 : 3); ++i) {
                    this.placeLog(level, blockSetter, random, mPos, config);
                    mPos.move(ranDir);
                    mPos.move(random.nextBoolean() ? ranDir.getClockWise() : ranDir.getCounterClockWise());
                    this.placeLog(level, blockSetter, random, mPos, config);
                    mPos.move(ranDir);
                    if (random.nextBoolean()) mPos.move(Direction.UP);
                }
                list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(mPos.getX(), mPos.getY(), mPos.getZ()), 0, false));
                mPos.set(homePos);
                for (int i = 0; i < (random.nextBoolean() ? 3 : 4); ++i) {
                    this.placeLog(level, blockSetter, random, mPos, config);
                    mPos.move(Direction.UP);
                }
                mPos.move(ranDir);
                for (int i = 0; i < (random.nextBoolean() ? 2 : 3); ++i) {
                    this.placeLog(level, blockSetter, random, mPos, config);
                    mPos.move(Direction.UP);
                }
                mPos.move(ranDir);
                this.placeLog(level, blockSetter, random, mPos, config);
                list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(mPos.getX(), mPos.getY(), mPos.getZ()), 0, false));
                mPos.set(homePos);
            }


        }
        /*
        else {
            setDirtAt(level, blockSetter, random, blockPos, config);
            setDirtAt(level, blockSetter, random, blockPos.south(), config);
            setDirtAt(level, blockSetter, random, blockPos.south().south(), config);
            setDirtAt(level, blockSetter, random, blockPos.south().south().south(), config);
            setDirtAt(level, blockSetter, random, blockPos.east(), config);
            setDirtAt(level, blockSetter, random, blockPos.east().south(), config);
            setDirtAt(level, blockSetter, random, blockPos.east().south().south(), config);
            setDirtAt(level, blockSetter, random, blockPos.east().south().south().south(), config);
            setDirtAt(level, blockSetter, random, blockPos.east().south().south().south().south(), config);
            setDirtAt(level, blockSetter, random, blockPos.east().east(), config);
            setDirtAt(level, blockSetter, random, blockPos.east().east().south(), config);
            setDirtAt(level, blockSetter, random, blockPos.east().east().south().south(), config);
            setDirtAt(level, blockSetter, random, blockPos.east().east().south().south().south(), config);
            setDirtAt(level, blockSetter, random, blockPos.east().east().south().south().south().south(), config);
            setDirtAt(level, blockSetter, random, blockPos.east().east().east(), config);
            setDirtAt(level, blockSetter, random, blockPos.east().east().east().south(), config);
            setDirtAt(level, blockSetter, random, blockPos.east().east().east().south().south(), config);
            setDirtAt(level, blockSetter, random, blockPos.east().east().east().south().south().south(), config);
            setDirtAt(level, blockSetter, random, blockPos.east().east().east().east().south(), config);
            setDirtAt(level, blockSetter, random, blockPos.east().east().east().east().south().south(), config);
            setDirtAt(level, blockSetter, random, blockPos.east().north(), config);
            setDirtAt(level, blockSetter, random, blockPos.east().east().north(), config);
            setDirtAt(level, blockSetter, random, blockPos.south().west(), config);
            setDirtAt(level, blockSetter, random, blockPos.south().south().west(), config);


            //trunk
            for (int i = -1; i < j; ++i) {
                placeLogIfFreeWithOffset(level, blockSetter, random, mutableBlockPos, config, pos, 1, i, 0);
                placeLogIfFreeWithOffset(level, blockSetter, random, mutableBlockPos, config, pos, 2, i, 0);
                placeLogIfFreeWithOffset(level, blockSetter, random, mutableBlockPos, config, pos, 0, i, 1);
                placeLogIfFreeWithOffset(level, blockSetter, random, mutableBlockPos, config, pos, 0, i, 2);

                placeLogIfFreeWithOffset(level, blockSetter, random, mutableBlockPos, config, pos, 3, i, 1);
                placeLogIfFreeWithOffset(level, blockSetter, random, mutableBlockPos, config, pos, 1, i, 3);
                placeLogIfFreeWithOffset(level, blockSetter, random, mutableBlockPos, config, pos, 3, i, 2);
                placeLogIfFreeWithOffset(level, blockSetter, random, mutableBlockPos, config, pos, 2, i, 3);

                placeLogIfFreeWithOffset(level, blockSetter, random, mutableBlockPos, config, pos, 1, i, 1);
                placeLogIfFreeWithOffset(level, blockSetter, random, mutableBlockPos, config, pos, 1, i, 2);
                placeLogIfFreeWithOffset(level, blockSetter, random, mutableBlockPos, config, pos, 2, i, 1);
                placeLogIfFreeWithOffset(level, blockSetter, random, mutableBlockPos, config, pos, 2, i, 2);
            }
            //trunk corners
            for (int i = 0; i < 3 + random.nextInt(4); ++i) {
                placeLogIfFreeWithOffset(level, blockSetter, random, mutableBlockPos, config, pos, 0, i, 0);
            }
            for (int i = 0; i < 3 + random.nextInt(4); ++i) {
                placeLogIfFreeWithOffset(level, blockSetter, random, mutableBlockPos, config, pos, 3, i, 0);
            }
            for (int i = 0; i < 3 + random.nextInt(4); ++i) {
                placeLogIfFreeWithOffset(level, blockSetter, random, mutableBlockPos, config, pos, 0, i, 3);
            }
            for (int i = 0; i < 3 + random.nextInt(4); ++i) {
                placeLogIfFreeWithOffset(level, blockSetter, random, mutableBlockPos, config, pos, 3, i, 3);
            }

            //small trunk faces
            placeLogIfFreeWithOffset(level, blockSetter, random, mutableBlockPos, config, pos, 1, 0, -1);
            placeLogIfFreeWithOffset(level, blockSetter, random, mutableBlockPos, config, pos, 2, 0, -1);
            if (random.nextBoolean()) placeLogIfFreeWithOffset(level, blockSetter, random, mutableBlockPos, config, pos, 1, 1, -1);
            else placeLogIfFreeWithOffset(level, blockSetter, random, mutableBlockPos, config, pos, 2, 1, -1);


            placeLogIfFreeWithOffset(level, blockSetter, random, mutableBlockPos, config, pos, -1, 0, 1);
            placeLogIfFreeWithOffset(level, blockSetter, random, mutableBlockPos, config, pos, -1, 0, 2);
            if (random.nextBoolean()) placeLogIfFreeWithOffset(level, blockSetter, random, mutableBlockPos, config, pos, -1, 1, 1);
            else placeLogIfFreeWithOffset(level, blockSetter, random, mutableBlockPos, config, pos, -1, 1, 2);


            placeLogIfFreeWithOffset(level, blockSetter, random, mutableBlockPos, config, pos, 4, 0, 1);
            placeLogIfFreeWithOffset(level, blockSetter, random, mutableBlockPos, config, pos, 4, 0, 2);
            if (random.nextBoolean()) placeLogIfFreeWithOffset(level, blockSetter, random, mutableBlockPos, config, pos, 4, 1, 1);
            else placeLogIfFreeWithOffset(level, blockSetter, random, mutableBlockPos, config, pos, 4, 1, 2);


            placeLogIfFreeWithOffset(level, blockSetter, random, mutableBlockPos, config, pos, 1, 0, 4);
            placeLogIfFreeWithOffset(level, blockSetter, random, mutableBlockPos, config, pos, 2, 0, 4);
            if (random.nextBoolean()) placeLogIfFreeWithOffset(level, blockSetter, random, mutableBlockPos, config, pos, 1, 1, 4);
            else placeLogIfFreeWithOffset(level, blockSetter, random, mutableBlockPos, config, pos, 2, 1, 4);

            //center branch
            var x = random.nextInt(1,3);
            var z = random.nextInt(1,3);
            for (int i = j; i < j + 4; ++i) {
                placeLogIfFreeWithOffset(level, blockSetter, random, mutableBlockPos, config, pos, x, i, z);
            }
            list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(pos.getX() + x, pos.getY() + j + 4, pos.getZ() + z), 0, false));

            //branches
            if (random.nextBoolean()) {
                var p = random.nextInt(0,2);
                var q = random.nextInt(0,2);
                buildBranch(level, blockSetter, random, mutableBlockPos, config, pos, 1, 0, 1, -1, 1, -2, j, p, q);
                list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(pos.getX() + 1, pos.getY() + j + p + q, pos.getZ() + -2), 0, false));
            }
            else {
                var p = random.nextInt(0,2);
                var q = random.nextInt(0,2);
                buildBranch(level, blockSetter, random, mutableBlockPos, config, pos, 2, 0, 2, -1, 2, -2, j, p, q);
                list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(pos.getX() + 2, pos.getY() + j + p + q, pos.getZ() + -2), 0, false));
            }


            if (random.nextBoolean()) {
                var p = random.nextInt(0,2);
                var q = random.nextInt(0,2);
                buildBranch(level, blockSetter, random, mutableBlockPos, config, pos, 0, 1, -1, 1, -2, 1, j, p, q);
                list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(pos.getX() + -2, pos.getY() + j + p + q, pos.getZ() + 1), 0, false));
            }
            else {
                var p = random.nextInt(0,2);
                var q = random.nextInt(0,2);
                buildBranch(level, blockSetter, random, mutableBlockPos, config, pos, 0, 2, -1, 2, -2, 2, j, p, q);
                list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(pos.getX() + -2, pos.getY() + j + p + q, pos.getZ() + 2), 0, false));
            }


            if (random.nextBoolean()) {
                var p = random.nextInt(0,2);
                var q = random.nextInt(0,2);
                buildBranch(level, blockSetter, random, mutableBlockPos, config, pos, 3, 1, 4, 1, 5, 1, j, p, q);
                list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(pos.getX() + 5, pos.getY() + j + p + q, pos.getZ() + 1), 0, false));
            }
            else {
                var p = random.nextInt(0,2);
                var q = random.nextInt(0,2);
                buildBranch(level, blockSetter, random, mutableBlockPos, config, pos, 3, 2, 4, 2, 5, 2, j, p, q);
                list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(pos.getX() + 5, pos.getY() + j + p + q, pos.getZ() + 2), 0, false));
            }


            if (random.nextBoolean()) {
                var p = random.nextInt(0,2);
                var q = random.nextInt(0,2);
                buildBranch(level, blockSetter, random, mutableBlockPos, config, pos, 1, 3, 1, 4, 1, 5, j, p, q);
                list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(pos.getX() + 1, pos.getY() + j + p + q, pos.getZ() + 5), 0, false));
            }
            else {
                var p = random.nextInt(0,2);
                var q = random.nextInt(0,2);
                buildBranch(level, blockSetter, random, mutableBlockPos, config, pos, 2, 3, 2, 4, 2, 5, j, p, q);
                list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(pos.getX() + 2, pos.getY() + j + p + q, pos.getZ() + 5), 0, false));
            }
        }

         */
        return list;

    }

    private void placeLogIfFreeWithOffset(WorldGenLevel level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, BlockPos.MutableBlockPos pos, TreeConfiguration config, BlockPos offsetPos, int offsetX, int offsetY, int offsetZ) {
        pos.setWithOffset(offsetPos, offsetX, offsetY, offsetZ);
        this.placeLogIfFree(level, blockSetter, random, pos, config);
    }

    private void buildBranch(WorldGenLevel level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, BlockPos.MutableBlockPos mutableBlockPos, TreeConfiguration config, BlockPos pos, int x, int z, int x2, int z2, int x3, int z3, int j, int p, int q) {

        placeLogIfFreeWithOffset(level, blockSetter, random, mutableBlockPos, config, pos, x, j, z);
        placeLogIfFreeWithOffset(level, blockSetter, random, mutableBlockPos, config, pos, x2, j + p, z2);
        placeLogIfFreeWithOffset(level, blockSetter, random, mutableBlockPos, config, pos, x3, j + p + q, z3);

    }
}
