package com.ordana.would.reg;

import com.ordana.would.Would;
import com.ordana.would.WouldPlatform;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModWorldgenFeatures {
    public static void init() {
        ResourceKey<PlacedFeature> beach_palm = ResourceKey.create(Registries.PLACED_FEATURE, Would.res("beach_palm"));
        WouldPlatform.addFeatureToBiome(GenerationStep.Decoration.RAW_GENERATION, BiomeTags.HAS_RUINED_PORTAL_DESERT, beach_palm);
    }
}
