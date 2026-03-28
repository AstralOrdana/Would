package com.ordana.would.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ordana.would.blocks.HangingMossBlock;
import com.ordana.would.reg.ModBlocks;
import com.ordana.would.reg.ModTrees;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import org.apache.commons.lang3.mutable.Mutable;
import org.apache.commons.lang3.mutable.MutableObject;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PaleMossDecorator extends TreeDecorator {
    public static final MapCodec<PaleMossDecorator> CODEC = RecordCodecBuilder.mapCodec((instance) ->
            instance.group(Codec.floatRange(0.0F, 1.0F).fieldOf("leaves_probability").forGetter((paleMossDecorator) ->
                    paleMossDecorator.leavesProbability)).apply(instance, PaleMossDecorator::new));
    private final float leavesProbability;

    protected @NotNull TreeDecoratorType<?> type() {
        return ModTrees.PALE_MOSS_DECORATOR.get();
    }

    public PaleMossDecorator(float f) {
        this.leavesProbability = f;
    }

    public void place(TreeDecorator.Context context) {
        RandomSource randomSource = context.random();
        List<BlockPos> list = Util.shuffledCopy(context.logs(), randomSource);
        if (!list.isEmpty()) {
            Mutable<BlockPos> mutable = new MutableObject<>(list.getFirst());
            list.forEach((blockPosx) -> {
                if (blockPosx.getY() < mutable.getValue().getY()) {
                    mutable.setValue(blockPosx);
                }

            });
            context.leaves().forEach((blockPosx) -> {
                if (randomSource.nextFloat() < this.leavesProbability) {
                    BlockPos blockPos2 = blockPosx.below();
                    if (context.isAir(blockPos2)) {
                        addMossHanger(blockPos2, context);
                    }
                }

            });
        }
    }

    private static void addMossHanger(BlockPos blockPos, TreeDecorator.Context context) {
        while(context.isAir(blockPos.below()) && !((double)context.random().nextFloat() < 0.5)) {
            context.setBlock(blockPos, ModBlocks.PALE_HANGING_MOSS.get().defaultBlockState().setValue(HangingMossBlock.TIP, false));
            blockPos = blockPos.below();
        }

        context.setBlock(blockPos, ModBlocks.PALE_HANGING_MOSS.get().defaultBlockState().setValue(HangingMossBlock.TIP, true));
    }
}
