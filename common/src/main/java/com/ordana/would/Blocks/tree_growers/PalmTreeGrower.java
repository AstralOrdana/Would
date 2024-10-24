package com.ordana.would.Blocks.tree_growers;

import com.ordana.would.Would;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class PalmTreeGrower extends AbstractTreeGrower {
    public PalmTreeGrower() {
    }

    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean hasFlowers) {
        return Would.createKey("palm");
    }
}
