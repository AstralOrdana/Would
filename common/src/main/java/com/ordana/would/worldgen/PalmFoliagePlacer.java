package com.ordana.would.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ordana.would.reg.ModTrees;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class PalmFoliagePlacer extends FoliagePlacer {
    public static final Codec<PalmFoliagePlacer> CODEC = RecordCodecBuilder.create(chestnutFoliagePlacerInstance ->
            foliagePlacerParts(chestnutFoliagePlacerInstance).and(Codec.intRange(0, 12).fieldOf("height")
                    .forGetter(instance -> instance.height)).apply(chestnutFoliagePlacerInstance, PalmFoliagePlacer::new));
    private final int height;

    public PalmFoliagePlacer(IntProvider radius, IntProvider offset, int height) {
        super(radius, offset);
        this.height = height;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModTrees.PALM_FOLIAGE_PLACER;
    }

    @Override
    protected void createFoliage(LevelSimulatedReader level, FoliageSetter blockSetter, RandomSource random, TreeConfiguration config, int maxFreeTreeHeight, FoliageAttachment attachment, int foliageHeight, int foliageRadius, int offset) {
        BlockPos basePos = attachment.pos();
        BlockPos.MutableBlockPos pos = basePos.mutable();
        placeLeavesRow(level, blockSetter, random, config, basePos, 1, -1, attachment.doubleTrunk());
        tryPlaceLeaf(level, blockSetter, random, config, basePos);


        if (random.nextBoolean()) {
            for (Direction dir : Direction.Plane.HORIZONTAL.shuffledCopy(random)) {
                pos.move(dir);
                tryPlaceLeaf(level, blockSetter, random, config, pos);
                pos.move(dir);
                pos.move(Direction.UP);
                tryPlaceLeaf(level, blockSetter, random, config, pos);
                pos.move(dir);
                tryPlaceLeaf(level, blockSetter, random, config, pos);
                pos.move(dir);
                pos.move(Direction.DOWN);
                tryPlaceLeaf(level, blockSetter, random, config, pos);
                pos.set(basePos);
            }
            for (Direction dir : Direction.Plane.HORIZONTAL.shuffledCopy(random)) {
                pos.move(Direction.DOWN);
                pos.move(dir);
                pos.move(dir);
                tryPlaceLeaf(level, blockSetter, random, config, pos);
                pos.move(Direction.DOWN);
                pos.move(dir);
                tryPlaceLeaf(level, blockSetter, random, config, pos);
                pos.move(Direction.DOWN);
                pos.move(dir);
                tryPlaceLeaf(level, blockSetter, random, config, pos);
                pos.move(Direction.DOWN);
                tryPlaceLeaf(level, blockSetter, random, config, pos);
                pos.set(basePos);
                pos.move(Direction.DOWN);
                pos.move(dir);
                pos.move(dir);
                pos.move(dir.getClockWise(Direction.Axis.Y));
                pos.move(dir.getClockWise(Direction.Axis.Y));
                tryPlaceLeaf(level, blockSetter, random, config, pos);
                pos.move(Direction.DOWN);
                pos.move(dir);
                pos.move(dir.getClockWise(Direction.Axis.Y));
                tryPlaceLeaf(level, blockSetter, random, config, pos);
                pos.set(basePos);
            }
        }
        else {
            for (Direction dir : Direction.Plane.HORIZONTAL.shuffledCopy(random)) {
                pos.move(dir);
                tryPlaceLeaf(level, blockSetter, random, config, pos);
                pos.move(dir);
                pos.move(Direction.UP);
                tryPlaceLeaf(level, blockSetter, random, config, pos);
                pos.move(dir);
                tryPlaceLeaf(level, blockSetter, random, config, pos);
                pos.set(basePos);
            }
            for (Direction dir : Direction.Plane.HORIZONTAL.shuffledCopy(random)) {
                pos.move(Direction.DOWN);
                pos.move(dir);
                pos.move(dir);
                tryPlaceLeaf(level, blockSetter, random, config, pos);
                pos.move(Direction.DOWN);
                pos.move(dir);
                tryPlaceLeaf(level, blockSetter, random, config, pos);
                pos.move(Direction.DOWN);
                tryPlaceLeaf(level, blockSetter, random, config, pos);
                pos.set(basePos);
                pos.move(Direction.DOWN);
                pos.move(dir);
                pos.move(dir);
                pos.move(dir.getClockWise(Direction.Axis.Y));
                pos.move(dir.getClockWise(Direction.Axis.Y));
                tryPlaceLeaf(level, blockSetter, random, config, pos);
                pos.set(basePos);
            }
        }

    }

    @Override
    public int foliageHeight(RandomSource random, int height, TreeConfiguration config) {
        return this.height;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int localX, int localY, int localZ, int range, boolean large) {
        return false;
    }
}