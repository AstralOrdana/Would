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

public class PineFoliagePlacer extends FoliagePlacer {
    public static final Codec<PineFoliagePlacer> CODEC = RecordCodecBuilder.create(foliagePlacerInstance ->
            foliagePlacerParts(foliagePlacerInstance).and(Codec.intRange(0, 12).fieldOf("height")
                    .forGetter(instance -> instance.height)).apply(foliagePlacerInstance, PineFoliagePlacer::new));
    private final int height;

    public PineFoliagePlacer(IntProvider radius, IntProvider offset, int height) {
        super(radius, offset);
        this.height = height;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModTrees.PINE_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader level, FoliageSetter blockSetter, RandomSource random, TreeConfiguration config, int maxFreeTreeHeight, FoliageAttachment attachment, int foliageHeight, int foliageRadius, int offset) {
        boolean bl = attachment.doubleTrunk();
        BlockPos basePos = attachment.pos();
        BlockPos.MutableBlockPos pos = basePos.mutable();

        tryPlaceLeaf(level, blockSetter, random, config, pos.above());
        tryPlaceLeaf(level, blockSetter, random, config, pos);
        for (Direction dir : Direction.Plane.HORIZONTAL.shuffledCopy(random)) {
            pos.move(dir);
            tryPlaceLeaf(level, blockSetter, random, config, pos);
            pos.move(dir.getOpposite());
        }
        pos.move(Direction.DOWN);
        for(int k = 0; k <= foliageHeight + random.nextInt(2); ++k) {
            placeLeavesRow(level, blockSetter, random, config, pos, foliageRadius, 0, bl);
            pos.move(Direction.DOWN);
        }
        for (Direction dir : Direction.Plane.HORIZONTAL.shuffledCopy(random)) {
            pos.move(dir);
            tryPlaceLeaf(level, blockSetter, random, config, pos);
            pos.move(dir.getOpposite());
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