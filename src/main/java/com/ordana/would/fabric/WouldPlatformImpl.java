//? fabric {
/*package com.ordana.would.fabric;

import com.ordana.would.WouldPlatform;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.function.Supplier;

public class WouldPlatformImpl implements WouldPlatform {
    public void addFeatureToBiome(GenerationStep.Decoration step, TagKey<Biome> tagKey, ResourceKey<PlacedFeature> feature) {
        BiomeModifications.addFeature(BiomeSelectors.tag(tagKey), step, feature);
    }

    public boolean isClient() {
        return FabricLoader.getInstance().getEnvironmentType().equals(EnvType.CLIENT);
    }

    public CreativeModeTab registerCreativeModeTab(Identifier name, MutableComponent title, Supplier<ItemStack> icon, CreativeModeTab.DisplayItemsGenerator generator) {
        CreativeModeTab tab = FabricCreativeModeTab.builder().title(title).icon(icon).displayItems(generator).build();
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, name, tab);
        return tab;
    }
}
*///?}