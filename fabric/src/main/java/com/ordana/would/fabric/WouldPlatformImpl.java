package com.ordana.would.fabric;

import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import oshi.util.tuples.Pair;

import java.util.function.Supplier;

public class WouldPlatformImpl {
    public static void addFeatureToBiome(GenerationStep.Decoration step, TagKey<Biome> tagKey, ResourceKey<PlacedFeature> feature) {
        BiomeModifications.addFeature(BiomeSelectors.tag(tagKey), step, feature);
    }

    public static boolean isClient() {
        return FabricLoader.getInstance().getEnvironmentType().equals(EnvType.CLIENT);
    }

    public static Pair<CreativeModeTab, ResourceKey<CreativeModeTab>> registerCreativeModeTab(ResourceLocation name, MutableComponent title, Supplier<ItemStack> icon) {
        ResourceKey<CreativeModeTab> key = ResourceKey.create(Registries.CREATIVE_MODE_TAB, name);
        CreativeModeTab tab = FabricItemGroup.builder().title(title).icon(icon).build();
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, name, tab);
        return new Pair<>(tab, key);
    }
}
