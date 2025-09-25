package com.ordana.would.fabric;

import com.ordana.would.reg.ModBlocks;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;

public class ModCompostable {
    public static void register() {
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.ASPEN_LEAVES.get().asItem(), 0.3f);
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.BAOBAB_LEAVES.get().asItem(), 0.3f);
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.CEDAR_LEAVES.get().asItem(), 0.3f);
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.EBONY_LEAVES.get().asItem(), 0.3f);
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.EBONY_LEAVES_FRUITING.get().asItem(), 0.3f);
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.FIR_LEAVES.get().asItem(), 0.3f);
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.PALM_LEAVES.get().asItem(), 0.3f);
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.PINE_LEAVES.get().asItem(), 0.3f);
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.MAHOGANY_LEAVES.get().asItem(), 0.3f);
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.MAPLE_LEAVES.get().asItem(), 0.3f);
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.WILLOW_LEAVES.get().asItem(), 0.3f);
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.WALNUT_LEAVES.get().asItem(), 0.3f);
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.BLUE_SPRUCE_LEAVES.get().asItem(), 0.3f);

        CompostingChanceRegistry.INSTANCE.add(ModBlocks.ASPEN_SAPLING.get().asItem(), 0.3f);
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.BAOBAB_SAPLING.get().asItem(), 0.3f);
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.CEDAR_SAPLING.get().asItem(), 0.3f);
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.EBONY_SAPLING.get().asItem(), 0.3f);
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.FIR_SAPLING.get().asItem(), 0.3f);
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.COCONUT.get().asItem(), 0.3f);
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.PINE_SAPLING.get().asItem(), 0.3f);
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.MAHOGANY_SAPLING.get().asItem(), 0.3f);
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.MAPLE_SAPLING.get().asItem(), 0.3f);
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.WILLOW_SAPLING.get().asItem(), 0.3f);
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.WALNUT_SAPLING.get().asItem(), 0.3f);
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.BLUE_SPRUCE_SAPLING.get().asItem(), 0.3f);
    }
}
