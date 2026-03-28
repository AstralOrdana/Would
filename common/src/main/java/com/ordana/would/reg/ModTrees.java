package com.ordana.would.reg;

import com.mojang.serialization.MapCodec;
import com.ordana.would.Would;
import com.ordana.would.worldgen.*;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.function.Supplier;

public interface ModTrees {
    static void init() {
    }

    //foliage
    Supplier<FoliagePlacerType<?>> ASPEN_FOLIAGE_PLACER = registerFoliage("aspen", AspenFoliagePlacer.CODEC);
    Supplier<FoliagePlacerType<?>> PALM_FOLIAGE_PLACER = registerFoliage("palm", PalmFoliagePlacer.CODEC);
    Supplier<FoliagePlacerType<?>> CEDAR_FOLIAGE_PLACER = registerFoliage("cedar", CedarFoliagePlacer.CODEC);
    Supplier<FoliagePlacerType<?>> WILLOW_FOLIAGE_PLACER = registerFoliage("willow", WillowFoliagePlacer.CODEC);
    Supplier<FoliagePlacerType<?>> PINE_FOLIAGE_PLACER = registerFoliage("pine", PineFoliagePlacer.CODEC);
    Supplier<FoliagePlacerType<?>> AZALEA_FOLIAGE_PLACER = registerFoliage("azalea", AzaleaFoliagePlacer.CODEC);
    Supplier<FoliagePlacerType<?>> WALNUT_FOLIAGE_PLACER = registerFoliage("walnut", WalnutFoliagePlacer.CODEC);

    Supplier<TrunkPlacerType<?>> PALM_TRUNK_PLACER = registerTrunk("palm", PalmTrunkPlacer.CODEC);
    Supplier<TrunkPlacerType<?>> WILLOW_TRUNK_PLACER = registerTrunk("willow", WillowTrunkPlacer.CODEC);
    Supplier<TrunkPlacerType<?>> BAOBAB_TRUNK_PLACER = registerTrunk("baobab", BaobabTrunkPlacer.CODEC);
    Supplier<TrunkPlacerType<?>> MAPLE_TRUNK_PLACER = registerTrunk("maple", MapleTrunkPlacer.CODEC);
    Supplier<TrunkPlacerType<?>> WALNUT_TRUNK_PLACER = registerTrunk("walnut", WalnutTrunkPlacer.CODEC);
    Supplier<TrunkPlacerType<?>> AZALEA_TRUNK_PLACER = registerTrunk("azalea", AzaleaTrunkPlacer.CODEC);
    Supplier<TrunkPlacerType<?>> BALD_CYPRESS_TRUNK_PLACER = registerTrunk("bald_cypress", BaldCypressTrunkPlacer.CODEC);

    Supplier<TreeDecoratorType<?>> PALE_MOSS_DECORATOR = registerDecorator("pale_moss", PaleMossDecorator.CODEC);


    static Supplier<FoliagePlacerType<?>> registerFoliage(String name, MapCodec<? extends FoliagePlacer> codec) {
        return RegHelper.register(Would.res(name + "_foliage_placer"), () -> new FoliagePlacerType<>(codec), Registries.FOLIAGE_PLACER_TYPE);
    }
    static Supplier<TrunkPlacerType<?>> registerTrunk(String name, MapCodec<? extends TrunkPlacer> codec) {
        return RegHelper.register(Would.res(name + "_trunk_placer"), () -> new TrunkPlacerType<>(codec), Registries.TRUNK_PLACER_TYPE);
    }
    static Supplier<TreeDecoratorType<?>> registerDecorator(String name, MapCodec<? extends TreeDecorator> codec) {
        return RegHelper.register(Would.res(name + "_decorator"), () -> new TreeDecoratorType<>(codec), Registries.TREE_DECORATOR_TYPE);
    }
}