package com.ordana.would;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import org.jetbrains.annotations.Contract;
import oshi.util.tuples.Pair;

import java.util.function.Supplier;

public class WouldPlatform {
    @Contract
    @ExpectPlatform
    public static void addFeatureToBiome(GenerationStep.Decoration step, TagKey<Biome> tagKey, ResourceKey<PlacedFeature> feature) {
        throw new AssertionError();
    }

	@ExpectPlatform
	public static boolean isClient() {
		throw new AssertionError();
	}

	@ExpectPlatform
	public static Pair<CreativeModeTab, ResourceKey<CreativeModeTab>> registerCreativeModeTab(ResourceLocation name, MutableComponent title, Supplier<ItemStack> icon) {
		throw new AssertionError();
	}
}
