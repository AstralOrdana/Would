package com.ordana.would.reg;

import com.ordana.would.Would;
import com.ordana.would.mixins.FoliagePlacerTypeInvoker;
import com.ordana.would.mixins.TrunkPlacerTypeInvoker;
import com.ordana.would.worldgen.CedarFoliagePlacer;
import com.ordana.would.worldgen.PalmFoliagePlacer;
import com.ordana.would.worldgen.PalmTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

public class ModTrees {
    //foliage
    public static final FoliagePlacerType<?> PALM_FOLIAGE_PLACER = FoliagePlacerTypeInvoker.callRegister("palm_foliage_placer", PalmFoliagePlacer.CODEC);
    public static final FoliagePlacerType<?> CEDAR_FOLIAGE_PLACER = FoliagePlacerTypeInvoker.callRegister("cedar_foliage_placer", CedarFoliagePlacer.CODEC);

    //trunks
    public static final TrunkPlacerType<?> PALM_TRUNK_PLACER = TrunkPlacerTypeInvoker.callRegister("palm_trunk_placer", PalmTrunkPlacer.CODEC);

    public static void register() {
        Would.LOGGER.info("Registering Foliage & Trunk Placers for " + Would.MOD_ID);
    }
}