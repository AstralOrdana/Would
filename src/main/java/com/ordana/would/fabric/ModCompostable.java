//? fabric {
package com.ordana.would.fabric;

import com.ordana.would.reg.ModBlocks;
import com.ordana.would.reg.ModWoodTypes;
import com.ordana.would.reg.WouldType;
import net.fabricmc.fabric.api.registry.CompostableRegistry;

public class ModCompostable {
    public static void register() {
        for (WouldType wouldType : ModWoodTypes.ALL) {
            if (wouldType.leaves() != null)
                CompostableRegistry.INSTANCE.add(wouldType.leaves(), 0.3f);
            if (wouldType.sapling() != null)
                CompostableRegistry.INSTANCE.add(wouldType.sapling(), 0.3f);
        }
        CompostableRegistry.INSTANCE.add(ModBlocks.EBONY_LEAVES_FRUITING.asItem(), 0.3f);
        CompostableRegistry.INSTANCE.add(ModBlocks.COCONUT.asItem(), 0.3f);
   }
}
//?}