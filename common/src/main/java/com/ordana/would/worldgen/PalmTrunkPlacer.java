package com.ordana.would.worldgen;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ordana.would.reg.ModTrees;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.BiConsumer;

public class PalmTrunkPlacer extends TrunkPlacer {

    public static final Codec<PalmTrunkPlacer> CODEC = RecordCodecBuilder.create(objectInstance ->
            trunkPlacerParts(objectInstance).apply(objectInstance, PalmTrunkPlacer::new));

    public PalmTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected @NotNull TrunkPlacerType<?> type() {
        return ModTrees.PALM_TRUNK_PLACER;
    }

    @Override
    public @NotNull List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, int freeTreeHeight, BlockPos blockPos, TreeConfiguration config) {
        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        BlockPos.MutableBlockPos pos = blockPos.mutable();
        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();
        int bottomHeight = heightRandA + random.nextInt(2);
        int middleHeight = heightRandB + random.nextInt(2);

        this.placeLog(level, blockSetter, random, pos, config);

        if (random.nextBoolean()) {
            for (int k = 0; k <= bottomHeight; ++k) {
                if (TreeFeature.validTreePos(level, pos)) {
                    this.placeLog(level, blockSetter, random, pos, config);
                }
                pos.move(Direction.UP);
            }
            pos.move(direction);
            for (int k = 0; k <= middleHeight; ++k) {
                if (TreeFeature.validTreePos(level, pos)) {
                    this.placeLog(level, blockSetter, random, pos, config);
                }
                pos.move(Direction.UP);
            }
            pos.move(direction);
            for (int k = 0; k <= 2; ++k) {
                if (TreeFeature.validTreePos(level, pos)) {
                    this.placeLog(level, blockSetter, random, pos, config);
                }
                pos.move(Direction.UP);
            }
        }
        else {
            for(int k = 0; k <= ((baseHeight + random.nextInt(2)) / 2); ++k) {
                for(int h = 0; h <= k; ++h) {
                    if (TreeFeature.validTreePos(level, pos)) this.placeLog(level, blockSetter, random, pos, config);
                    pos.move(Direction.UP);
                }
                pos.move(direction);
            }
            pos.move(direction.getOpposite());
        }

        list.add(new FoliagePlacer.FoliageAttachment(pos.immutable(), 0, false));
        return list;
    }
}
