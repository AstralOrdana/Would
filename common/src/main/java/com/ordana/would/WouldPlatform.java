package com.ordana.would;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import org.jetbrains.annotations.Contract;
import oshi.util.tuples.Pair;

import java.util.function.Consumer;
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

	@FunctionalInterface
	public interface BlockColorEvent {
		void register(BlockColor var1, Block... var2);
	}

	@FunctionalInterface
	public interface ItemColorEvent {
		void register(ItemColor var1, ItemLike... var2);
	}

	@FunctionalInterface
	public interface EntityRendererEvent {
		<E extends Entity> void register(EntityType<? extends E> var1, EntityRendererProvider<E> var2);
	}

	@FunctionalInterface
	public interface ModelLayerEvent {
		void register(ModelLayerLocation var1, Supplier<LayerDefinition> var2);
	}

	@FunctionalInterface
	public interface RenderTypeEvent {
		void register(Block block, RenderType renderType);
	}
}
