package com.ordana.would.worldgen;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ordana.would.reg.ModTrees;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.BiConsumer;

public class MapleTrunkPlacer extends TrunkPlacer {

    public static final Codec<MapleTrunkPlacer> CODEC = RecordCodecBuilder.create(objectInstance ->
            trunkPlacerParts(objectInstance).apply(objectInstance, MapleTrunkPlacer::new));

    public MapleTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected @NotNull TrunkPlacerType<?> type() {
        return ModTrees.MAPLE_TRUNK_PLACER.get();
    }

    @Override
    public @NotNull List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, int freeTreeHeight, BlockPos blockPos, TreeConfiguration config) {

        BlockPos.MutableBlockPos pos = blockPos.mutable();
        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();

        this.placeLog(level, blockSetter, random, pos, config);
        for (int k = 0; k < (random.nextBoolean() ? heightRandA : heightRandB) + baseHeight; ++k) {
            if (TreeFeature.validTreePos(level, pos)) {
                this.placeLog(level, blockSetter, random, pos, config);
            }
            pos.move(Direction.UP);
        }

        //pos.move(Direction.UP, baseHeight);

        if (TreeFeature.validTreePos(level, pos)) this.placeLog(level, blockSetter, random, pos, config);
        if (TreeFeature.validTreePos(level, pos.above())) this.placeLog(level, blockSetter, random, pos.above(), config);

        for (Direction dir : Direction.Plane.HORIZONTAL) {
            for (int k = 0; k < baseHeight; ++k) {
                pos.move(dir);
                if (TreeFeature.validTreePos(level, pos)) this.placeLog(level, blockSetter, random, pos, config, (blockState) -> blockState.trySetValue(RotatedPillarBlock.AXIS, this.getLogAxis(blockPos.relative(dir.getOpposite()), pos)));
            }
            pos.move(dir.getOpposite(), baseHeight);
        }
        list.add(new FoliagePlacer.FoliageAttachment(pos.move(Direction.UP, baseHeight), 0, false));

        return list;
    }

    private Direction.Axis getLogAxis(BlockPos pos, BlockPos otherPos) {
        Direction.Axis axis = Direction.Axis.Y;
        int i = Math.abs(otherPos.getX() - pos.getX());
        int j = Math.abs(otherPos.getZ() - pos.getZ());
        int k = Math.max(i, j);
        if (k > 0) {
            if (i == k) {
                axis = Direction.Axis.X;
            } else {
                axis = Direction.Axis.Z;
            }
        }

        return axis;
    }
}
