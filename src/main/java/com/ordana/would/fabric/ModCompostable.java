//? fabric {
package com.ordana.would.fabric;

import com.ordana.would.reg.ModBlocks;
import com.ordana.would.reg.ModWoodTypes;
import com.ordana.would.reg.WouldType;
import net.fabricmc.fabric.api.registry.CompostableRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.ItemLike;

import java.util.HashMap;
import java.util.Map;

public class ModCompostable {

    public static final Map<Identifier, Float> COMPOSTABLES = new HashMap<>();

    public static void register() {
        for (WouldType wouldType : ModWoodTypes.ALL) {
            if (wouldType.leaves() != null)
                add(wouldType.leaves(), 0.3f);
            if (wouldType.sapling() != null)
                add(wouldType.sapling(), 0.3f);
        }
        add(ModBlocks.EBONY_LEAVES_FRUITING, 0.3f);
        add(ModBlocks.COCONUT, 0.3f);
   }

    private static void add(ItemLike itemLike, float value) {
        COMPOSTABLES.put(BuiltInRegistries.ITEM.getKey(itemLike.asItem()), value);
        CompostableRegistry.INSTANCE.add(itemLike, value);
    }
}
//?}