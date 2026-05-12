package com.ordana.would.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ordana.would.reg.ModTrees;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class WalnutFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<WalnutFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(foliagePlacerInstance ->
            foliagePlacerParts(foliagePlacerInstance).and(Codec.intRange(0, 12).fieldOf("height")
                    .forGetter(instance -> instance.height)).apply(foliagePlacerInstance, WalnutFoliagePlacer::new));
    private final int height;

    public WalnutFoliagePlacer(IntProvider radius, IntProvider offset, int height) {
        super(radius, offset);
        this.height = height;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModTrees.WALNUT_FOLIAGE_PLACER;
    }

    @Override
    protected void createFoliage(WorldGenLevel level, FoliageSetter blockSetter, RandomSource random, TreeConfiguration config, int maxFreeTreeHeight, FoliageAttachment attachment, int foliageHeight, int foliageRadius, int offset) {
        boolean bl = attachment.doubleTrunk();
        BlockPos basePos = attachment.pos();
        BlockPos.MutableBlockPos pos = basePos.mutable();

        for(int j = offset; j >= -3; --j) {
            int k = 5;
            this.placeLeavesRow(level, blockSetter, random, config, basePos.below(1) , k, j, attachment.doubleTrunk());
        }

    }

    @Override
    public int foliageHeight(RandomSource random, int height, TreeConfiguration config) {
        return this.height;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int localX, int localY, int localZ, int range, boolean large) {
        if (localX > 4 && localZ > 2 || localX > 2 && localZ > 4) return true;
        if (localY <= -2 && (localX < 5 && localZ < 3 || localZ < 5 && localX < 3)) return true;
        if (localY > -2 && (localX == 4 && localZ == 4 || localZ == 5 && localX == 2 || localX == 5 && localZ == 2)) return true;
        if (localY > -1 && (localX == 3 && localZ == 4 || localX == 4 && localZ == 3 || localZ == 5 && localX == 1 || localX == 5 && localZ == 1)) return true;
        if (localY > 0 && (localZ >= 4 || localX >= 4 || localX >= 3 && localZ >= 2 || localZ >= 3 && localX == 2)) return true;
        if (localY > 1 && (localX + localZ > 2)) return true;
        if (localY == -3 && random.nextFloat() < 0.65) return true;
        else return false;

        //return  ((localX == 4 && localZ == 2 || localX == 2 && localZ == 4) && localY == -3) ? random.nextFloat() < 0.65 : localX > 4 && localZ > 2 || localX > 2 && localZ > 4;
    }

    
}