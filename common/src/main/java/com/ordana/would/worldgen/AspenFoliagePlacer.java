package com.ordana.would.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
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

public class AspenFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<AspenFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(foliagePlacerInstance ->
            foliagePlacerParts(foliagePlacerInstance).and(Codec.intRange(0, 12).fieldOf("height")
                    .forGetter(instance -> instance.height)).apply(foliagePlacerInstance, AspenFoliagePlacer::new));
    private final int height;

    public AspenFoliagePlacer(IntProvider radius, IntProvider offset, int height) {
        super(radius, offset);
        this.height = height;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModTrees.ASPEN_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader level, FoliageSetter blockSetter, RandomSource random, TreeConfiguration config, int maxFreeTreeHeight, FoliageAttachment attachment, int foliageHeight, int foliageRadius, int offset) {
        boolean bl = attachment.doubleTrunk();
        BlockPos basePos = attachment.pos();
        BlockPos.MutableBlockPos pos = basePos.mutable();


        for(int k = 0; k <= 4; ++k) {
            tryPlaceLeaf(level, blockSetter, random, config, pos);
            pos.move(Direction.UP);
        }
        pos.set(basePos);
        pos.move(Direction.UP);
        pos.move(Direction.UP);

        for(int k = 0; k <= foliageHeight; ++k) {
            for (Direction dir : Direction.Plane.HORIZONTAL.shuffledCopy(random)) {
                pos.move(dir);

                if (k <= 2 || k >= 6) {
                    if (random.nextBoolean() && random.nextBoolean()) tryPlaceLeaf(level, blockSetter, random, config, pos);
                }

                if (k > 2 && k < 8) {
                    tryPlaceLeaf(level, blockSetter, random, config, pos);
                }

                if (k > 2 && k < 7) {
                    pos.move(dir.getClockWise());
                    if (random.nextInt(0, foliageHeight) < k+2) tryPlaceLeaf(level, blockSetter, random, config, pos);
                    pos.move(dir.getClockWise().getOpposite());
                }

                /*
                if (k > 4 && k < 7) {
                    pos.move(dir);
                    if (random.nextInt(0, foliageHeight) < k) tryPlaceLeaf(level, blockSetter, random, config, pos);
                    pos.move(dir.getOpposite());
                }
                 */

                pos.move(dir.getOpposite());
            }
            pos.move(Direction.DOWN);
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