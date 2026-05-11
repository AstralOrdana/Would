//? neoforge {
/*package com.ordana.would.neoforge;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.loading.FMLEnvironment;
import oshi.util.tuples.Pair;

import java.util.function.Supplier;

public class WouldPlatformImpl {
    public static void addFeatureToBiome(GenerationStep.Decoration step, TagKey<Biome> tagKey, ResourceKey<PlacedFeature> feature) {
    }

    public static void registerStrippable(Block input, Block stripped) {

    }

    public static boolean isClient() {
        return FMLEnvironment.dist == Dist.CLIENT;
    }

    public static CreativeModeTab registerCreativeModeTab(Identifier name, MutableComponent title, Supplier<ItemStack> icon, CreativeModeTab.DisplayItemsGenerator generator) {
        CreativeModeTab tab = CreativeModeTab.builder().title(title).icon(icon).displayItems(generator).build();
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, name, tab);
        return tab;
    }
}
*///?}