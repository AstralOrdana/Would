package com.ordana.would.reg;

import com.mojang.serialization.Codec;
import com.ordana.would.Would;
import com.ordana.would.worldgen.*;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.function.Supplier;

public class ModTrees {
    public static void init() {
    }

    //foliage
    public static final Supplier<FoliagePlacerType> ASPEN_FOLIAGE_PLACER = registerFoliage("aspen", AspenFoliagePlacer.CODEC);
    public static final Supplier<FoliagePlacerType> PALM_FOLIAGE_PLACER = registerFoliage("palm", PalmFoliagePlacer.CODEC);
    public static final Supplier<FoliagePlacerType> CEDAR_FOLIAGE_PLACER = registerFoliage("cedar", CedarFoliagePlacer.CODEC);
    public static final Supplier<FoliagePlacerType> WILLOW_FOLIAGE_PLACER = registerFoliage("willow", WillowFoliagePlacer.CODEC);
    public static final Supplier<FoliagePlacerType> PINE_FOLIAGE_PLACER = registerFoliage("pine", PineFoliagePlacer.CODEC);

    public static final Supplier<TrunkPlacerType> PALM_TRUNK_PLACER = registerTrunk("palm", PalmTrunkPlacer.CODEC);
    public static final Supplier<TrunkPlacerType> WILLOW_TRUNK_PLACER = registerTrunk("willow", WillowTrunkPlacer.CODEC);
    public static final Supplier<TrunkPlacerType> BAOBAB_TRUNK_PLACER = registerTrunk("baobab", BaobabTrunkPlacer.CODEC);


    public static Supplier<FoliagePlacerType> registerFoliage(String name, Codec codec) {
        return RegHelper.register(Would.res(name + "_foliage_placer"), () -> new FoliagePlacerType(codec), Registries.FOLIAGE_PLACER_TYPE);
    }
    public static Supplier<TrunkPlacerType> registerTrunk(String name, Codec codec) {
        return RegHelper.register(Would.res(name + "_trunk_placer"), () -> new TrunkPlacerType(codec), Registries.TRUNK_PLACER_TYPE);
    }
}