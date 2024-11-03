package com.ordana.would.reg;

import com.ordana.would.Would;
import com.ordana.would.mixins.FoliagePlacerTypeInvoker;
import com.ordana.would.mixins.TrunkPlacerTypeInvoker;
import com.ordana.would.worldgen.*;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

public class ModTrees {
    //foliage
    public static final FoliagePlacerType<?> ASPEN_FOLIAGE_PLACER = FoliagePlacerTypeInvoker.callRegister("aspen_foliage_placer", AspenFoliagePlacer.CODEC);
    public static final FoliagePlacerType<?> PALM_FOLIAGE_PLACER = FoliagePlacerTypeInvoker.callRegister("palm_foliage_placer", PalmFoliagePlacer.CODEC);
    public static final FoliagePlacerType<?> CEDAR_FOLIAGE_PLACER = FoliagePlacerTypeInvoker.callRegister("cedar_foliage_placer", CedarFoliagePlacer.CODEC);
    public static final FoliagePlacerType<?> WILLOW_FOLIAGE_PLACER = FoliagePlacerTypeInvoker.callRegister("willow_foliage_placer", WillowFoliagePlacer.CODEC);
    public static final FoliagePlacerType<?> MOD_PINE_FOLIAGE_PLACER = FoliagePlacerTypeInvoker.callRegister("mod_pine_foliage_placer", PineFoliagePlacer.CODEC);

    //trunks
    public static final TrunkPlacerType<?> PALM_TRUNK_PLACER = TrunkPlacerTypeInvoker.callRegister("palm_trunk_placer", PalmTrunkPlacer.CODEC);
    public static final TrunkPlacerType<?> WILLOW_TRUNK_PLACER = TrunkPlacerTypeInvoker.callRegister("willow_trunk_placer", WillowTrunkPlacer.CODEC);
    public static final TrunkPlacerType<?> BAOBAB_TRUNK_PLACER = TrunkPlacerTypeInvoker.callRegister("baobab_trunk_placer", BaobabTrunkPlacer.CODEC);

    public static void register() {
        Would.LOGGER.info("Registering Foliage & Trunk Placers for " + Would.MOD_ID);
    }
}