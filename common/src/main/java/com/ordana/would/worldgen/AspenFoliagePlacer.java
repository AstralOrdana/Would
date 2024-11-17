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

public class AspenFoliagePlacer extends FoliagePlacer {
    public static final Codec<AspenFoliagePlacer> CODEC = RecordCodecBuilder.create(foliagePlacerInstance ->
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

        tryPlaceLeaf(level, blockSetter, random, config, pos);
        pos.move(Direction.UP);
        tryPlaceLeaf(level, blockSetter, random, config, pos);
        pos.move(Direction.UP);
        tryPlaceLeaf(level, blockSetter, random, config, pos);
        pos.set(basePos);
        for (Direction dir : Direction.Plane.HORIZONTAL.shuffledCopy(random)) {
            pos.move(dir);
            tryPlaceLeaf(level, blockSetter, random, config, pos);
            pos.move(dir.getOpposite());
        }
        for(int k = 0; k <= foliageHeight; ++k) {
            placeLeavesRow(level, blockSetter, random, config, pos, foliageRadius, -1, bl);
            pos.move(Direction.DOWN);
        }
        pos.move(Direction.UP);
        placeLeavesRow(level, blockSetter, random, config, pos, foliageRadius + 1, -2, bl);
    }

    @Override
    public int foliageHeight(RandomSource random, int height, TreeConfiguration config) {
        return this.height;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int localX, int localY, int localZ, int range, boolean large) {
        if (localY == -1) return random.nextInt(3) == 1 && (localX > 0 && localZ > 0);
        else if (localY == -2) return random.nextInt(3) == 1 && (localX > 1 || localZ > 1);
        else return false;
    }
}