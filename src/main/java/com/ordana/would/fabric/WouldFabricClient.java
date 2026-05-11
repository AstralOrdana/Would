package com.ordana.would.fabric;

import com.ordana.would.WouldClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.renderer.entity.EntityRenderers;

public class WouldFabricClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		WouldClient.registerBlockColors(BlockColorRegistry::register);
		WouldClient.registerItemColors(ColorProviderRegistry.ITEM::register);
		WouldClient.registerLayers((modelLayerLocation, layerDefinitionSupplier)-> ModelLayerRegistry.registerModelLayer(modelLayerLocation, layerDefinitionSupplier::get));
		WouldClient.registerEntityRenderers(EntityRenderers::register);
	}
}
