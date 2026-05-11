//? fabric {
package com.ordana.would.fabric;

import com.ordana.would.reg.ModBlocks;
import net.fabricmc.fabric.api.registry.CompostableRegistry;

public class ModCompostable {
    public static void register() {
        CompostableRegistry.INSTANCE.add(ModBlocks.ASPEN_LEAVES.asItem(), 0.3f);
        CompostableRegistry.INSTANCE.add(ModBlocks.BAOBAB_LEAVES.asItem(), 0.3f);
        CompostableRegistry.INSTANCE.add(ModBlocks.CEDAR_LEAVES.asItem(), 0.3f);
        CompostableRegistry.INSTANCE.add(ModBlocks.EBONY_LEAVES.asItem(), 0.3f);
        CompostableRegistry.INSTANCE.add(ModBlocks.EBONY_LEAVES_FRUITING.asItem(), 0.3f);
        CompostableRegistry.INSTANCE.add(ModBlocks.FIR_LEAVES.asItem(), 0.3f);
        CompostableRegistry.INSTANCE.add(ModBlocks.PALM_LEAVES.asItem(), 0.3f);
        CompostableRegistry.INSTANCE.add(ModBlocks.PINE_LEAVES.asItem(), 0.3f);
        CompostableRegistry.INSTANCE.add(ModBlocks.MAHOGANY_LEAVES.asItem(), 0.3f);
        CompostableRegistry.INSTANCE.add(ModBlocks.MAPLE_LEAVES.asItem(), 0.3f);
        CompostableRegistry.INSTANCE.add(ModBlocks.WILLOW_LEAVES.asItem(), 0.3f);
        CompostableRegistry.INSTANCE.add(ModBlocks.WALNUT_LEAVES.asItem(), 0.3f);
        CompostableRegistry.INSTANCE.add(ModBlocks.BLUE_SPRUCE_LEAVES.asItem(), 0.3f);

        CompostableRegistry.INSTANCE.add(ModBlocks.ASPEN_SAPLING.asItem(), 0.3f);
        CompostableRegistry.INSTANCE.add(ModBlocks.BAOBAB_SAPLING.asItem(), 0.3f);
        CompostableRegistry.INSTANCE.add(ModBlocks.CEDAR_SAPLING.asItem(), 0.3f);
        CompostableRegistry.INSTANCE.add(ModBlocks.EBONY_SAPLING.asItem(), 0.3f);
        CompostableRegistry.INSTANCE.add(ModBlocks.FIR_SAPLING.asItem(), 0.3f);
        CompostableRegistry.INSTANCE.add(ModBlocks.COCONUT.asItem(), 0.3f);
        CompostableRegistry.INSTANCE.add(ModBlocks.PINE_SAPLING.asItem(), 0.3f);
        CompostableRegistry.INSTANCE.add(ModBlocks.MAHOGANY_SAPLING.asItem(), 0.3f);
        CompostableRegistry.INSTANCE.add(ModBlocks.MAPLE_SAPLING.asItem(), 0.3f);
        CompostableRegistry.INSTANCE.add(ModBlocks.WILLOW_SAPLING.asItem(), 0.3f);
        CompostableRegistry.INSTANCE.add(ModBlocks.HANGING_WILLOW_LEAVES, 0.3f);
        CompostableRegistry.INSTANCE.add(ModBlocks.WALNUT_SAPLING.asItem(), 0.3f);
        CompostableRegistry.INSTANCE.add(ModBlocks.BLUE_SPRUCE_SAPLING.asItem(), 0.3f);
    }
}
//?}