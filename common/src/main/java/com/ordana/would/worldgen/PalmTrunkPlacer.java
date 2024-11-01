package com.ordana.would.worldgen;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ordana.would.reg.ModTrunkPlacerTypes;
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

import java.util.List;
import java.util.function.BiConsumer;

public class PalmTrunkPlacer extends TrunkPlacer {

    public static final Codec<PalmTrunkPlacer> CODEC = RecordCodecBuilder.create(objectInstance ->
            trunkPlacerParts(objectInstance).apply(objectInstance, PalmTrunkPlacer::new));

    public PalmTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModTrunkPlacerTypes.PALM_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, int freeTreeHeight, BlockPos pos, TreeConfiguration config) {
        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        BlockPos.MutableBlockPos mutableBlockPos = pos.mutable();
        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();
        int bottomHeight = baseHeight + heightRandA + random.nextInt(2);
        int middleHeight = baseHeight + heightRandB + random.nextInt(2);

        var j = 5;
        for(int k = 0; k <= bottomHeight; ++k) {
            if (TreeFeature.validTreePos(level, mutableBlockPos)) {
                this.placeLog(level, blockSetter, random, mutableBlockPos, config);
            }
            mutableBlockPos.move(Direction.UP);
        }
        mutableBlockPos.move(direction);
        for(int k = 0; k <= middleHeight; ++k) {
            if (TreeFeature.validTreePos(level, mutableBlockPos)) {
                this.placeLog(level, blockSetter, random, mutableBlockPos, config);
            }
            mutableBlockPos.move(Direction.UP);
        }
        mutableBlockPos.move(direction);
        for(int k = 0; k <= 2; ++k) {
            if (TreeFeature.validTreePos(level, mutableBlockPos)) {
                this.placeLog(level, blockSetter, random, mutableBlockPos, config);
            }
            mutableBlockPos.move(Direction.UP);
        }

        list.add(new FoliagePlacer.FoliageAttachment(mutableBlockPos.immutable(), 0, false));
        return list;
    }
}
