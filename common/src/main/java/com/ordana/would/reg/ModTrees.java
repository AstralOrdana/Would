package com.ordana.would.reg;

import com.mojang.serialization.MapCodec;
import com.ordana.would.Would;
import com.ordana.would.worldgen.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

public class ModTrees {
    public static void init() {
    }

    //foliage
    public static final FoliagePlacerType<AspenFoliagePlacer> ASPEN_FOLIAGE_PLACER = registerFoliage("aspen", AspenFoliagePlacer.CODEC);
    public static final FoliagePlacerType<PalmFoliagePlacer> PALM_FOLIAGE_PLACER = registerFoliage("palm", PalmFoliagePlacer.CODEC);
    public static final FoliagePlacerType<CedarFoliagePlacer> CEDAR_FOLIAGE_PLACER = registerFoliage("cedar", CedarFoliagePlacer.CODEC);
    public static final FoliagePlacerType<WillowFoliagePlacer> WILLOW_FOLIAGE_PLACER = registerFoliage("willow", WillowFoliagePlacer.CODEC);
    public static final FoliagePlacerType<PineFoliagePlacer> PINE_FOLIAGE_PLACER = registerFoliage("pine", PineFoliagePlacer.CODEC);
    public static final FoliagePlacerType<AzaleaFoliagePlacer> AZALEA_FOLIAGE_PLACER = registerFoliage("azalea", AzaleaFoliagePlacer.CODEC);
    public static final FoliagePlacerType<WalnutFoliagePlacer> WALNUT_FOLIAGE_PLACER = registerFoliage("walnut", WalnutFoliagePlacer.CODEC);

    public static final TrunkPlacerType<PalmTrunkPlacer> PALM_TRUNK_PLACER = registerTrunk("palm", PalmTrunkPlacer.CODEC);
    public static final TrunkPlacerType<WillowTrunkPlacer> WILLOW_TRUNK_PLACER = registerTrunk("willow", WillowTrunkPlacer.CODEC);
    public static final TrunkPlacerType<BaobabTrunkPlacer> BAOBAB_TRUNK_PLACER = registerTrunk("baobab", BaobabTrunkPlacer.CODEC);
    public static final TrunkPlacerType<MapleTrunkPlacer> MAPLE_TRUNK_PLACER = registerTrunk("maple", MapleTrunkPlacer.CODEC);
    public static final TrunkPlacerType<WalnutTrunkPlacer> WALNUT_TRUNK_PLACER = registerTrunk("walnut", WalnutTrunkPlacer.CODEC);
    public static final TrunkPlacerType<AzaleaTrunkPlacer> AZALEA_TRUNK_PLACER = registerTrunk("azalea", AzaleaTrunkPlacer.CODEC);
    public static final TrunkPlacerType<BaldCypressTrunkPlacer> BALD_CYPRESS_TRUNK_PLACER = registerTrunk("bald_cypress", BaldCypressTrunkPlacer.CODEC);

    public static final TreeDecoratorType<PaleMossDecorator> PALE_MOSS_DECORATOR = registerDecorator("pale_moss", PaleMossDecorator.CODEC);


    public static <T extends FoliagePlacer> FoliagePlacerType<T> registerFoliage(String name, MapCodec<T> codec) {
        return Registry.register(BuiltInRegistries.FOLIAGE_PLACER_TYPE, Would.res(name + "_foliage_placer"), new FoliagePlacerType<>(codec));
    }
    public static <T extends TrunkPlacer> TrunkPlacerType<T> registerTrunk(String name, MapCodec<T> codec) {
        return Registry.register(BuiltInRegistries.TRUNK_PLACER_TYPE, Would.res(name + "_trunk_placer"), new TrunkPlacerType<>(codec));
    }
    public static <T extends TreeDecorator> TreeDecoratorType<T> registerDecorator(String name, MapCodec<T> codec) {
        return Registry.register(BuiltInRegistries.TREE_DECORATOR_TYPE, Would.res(name + "_decorator"), new TreeDecoratorType<>(codec));
    }
}