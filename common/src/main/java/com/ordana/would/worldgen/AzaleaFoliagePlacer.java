package com.ordana.would.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ordana.would.reg.ModTrees;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class AzaleaFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<AzaleaFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(foliagePlacerInstance ->
            foliagePlacerParts(foliagePlacerInstance).and(Codec.intRange(0, 12).fieldOf("height")
                    .forGetter(instance -> instance.height)).apply(foliagePlacerInstance, AzaleaFoliagePlacer::new));
    private final int height;

    public AzaleaFoliagePlacer(IntProvider radius, IntProvider offset, int height) {
        super(radius, offset);
        this.height = height;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModTrees.AZALEA_FOLIAGE_PLACER;
    }

    @Override
    protected void createFoliage(LevelSimulatedReader level, FoliageSetter blockSetter, RandomSource random, TreeConfiguration config, int maxFreeTreeHeight, FoliageAttachment attachment, int foliageHeight, int foliageRadius, int offset) {
        boolean bl = attachment.doubleTrunk();
        BlockPos basePos = attachment.pos();
        BlockPos.MutableBlockPos pos = basePos.mutable();

        for(int j = offset; j >= -2; --j) {
            int k = 3;
            this.placeLeavesRow(level, blockSetter, random, config, attachment.pos(), k, j, attachment.doubleTrunk());
        }

    }

    @Override
    public int foliageHeight(RandomSource random, int height, TreeConfiguration config) {
        return this.height;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int localX, int localY, int localZ, int range, boolean large) {
        return ((localX > 2 && localZ > 2 && localY >= 1) || (localX <= 2 && localZ <= 2 && localY < 0) || (localY <= -1 && (localX == 3 || localZ == 3) && (random.nextFloat() < (float) Math.abs(localY) / 8)));
    }

    
}