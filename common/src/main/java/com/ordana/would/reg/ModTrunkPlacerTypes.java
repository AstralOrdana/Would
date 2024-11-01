package com.ordana.would.reg;

import com.ordana.would.Would;
import com.ordana.would.mixins.TrunkPlacerTypeInvoker;
import com.ordana.would.worldgen.PalmTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

public class ModTrunkPlacerTypes {
    public static final TrunkPlacerType<?> PALM_TRUNK_PLACER = TrunkPlacerTypeInvoker.callRegister("palm_trunk_placer", PalmTrunkPlacer.CODEC);

    public static void register() {
        Would.LOGGER.info("Registering Trunk Placers for " + Would.MOD_ID);
    }
}