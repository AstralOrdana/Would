package com.ordana.would.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ordana.would.reg.ModTrees;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class WillowFoliagePlacer extends FoliagePlacer {
    public static final Codec<WillowFoliagePlacer> CODEC = RecordCodecBuilder.create(foliagePlacerInstance ->
            foliagePlacerParts(foliagePlacerInstance).and(Codec.intRange(0, 12).fieldOf("height")
                    .forGetter(instance -> instance.height)).apply(foliagePlacerInstance, WillowFoliagePlacer::new));
    private final int height;

    public WillowFoliagePlacer(IntProvider radius, IntProvider offset, int height) {
        super(radius, offset);
        this.height = height;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModTrees.WILLOW_FOLIAGE_PLACER;
    }

    @Override
    protected void createFoliage(LevelSimulatedReader level, FoliageSetter blockSetter, RandomSource random, TreeConfiguration config, int maxFreeTreeHeight, FoliageAttachment attachment, int foliageHeight, int foliageRadius, int offset) {
        BlockPos basePos = attachment.pos();
        BlockPos.MutableBlockPos pos = basePos.mutable();
        placeLeavesRow(level, blockSetter, random, config, basePos, 1, 1, attachment.doubleTrunk());
        placeLeavesRow(level, blockSetter, random, config, basePos, 2, 0, attachment.doubleTrunk());


        pos.move(Direction.DOWN);
        for (Direction dir : Direction.Plane.HORIZONTAL.shuffledCopy(random)) {
            pos.move(dir);
            if (random.nextBoolean()) {
                tryPlaceLeaf(level, blockSetter, random, config, pos);
                pos.move(dir);
            }
            else if (random.nextBoolean()) {
                pos.move(dir.getClockWise());
            }
            for(int h = 0; h < random.nextInt(4) + 3; ++h) {
                tryPlaceLeaf(level, blockSetter, random, config, pos);
                pos.move(Direction.DOWN);
            }
            pos.set(basePos);
            pos.move(Direction.DOWN);
        }

    }

    @Override
    public int foliageHeight(RandomSource random, int height, TreeConfiguration config) {
        return this.height;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int localX, int localY, int localZ, int range, boolean large) {
        if (localY == 1 && localZ > 0 && localX > 0) return true;
        else if (localY == 0) {
            if (random.nextBoolean() && (localX == 2 || localZ == 2)) return true;
            return (localX > 1 || localZ > 1) && localX != 0 && localZ != 0;
        }

        return false;
    }
}