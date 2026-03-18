package com.ordana.would.fabric;

import com.ordana.would.WouldClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.model.geom.builders.LayerDefinition;

public class WouldFabricClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		WouldClient.registerBlockColors(ColorProviderRegistry.BLOCK::register);
		WouldClient.registerItemColors(ColorProviderRegistry.ITEM::register);
		WouldClient.setup(BlockRenderLayerMap.INSTANCE::putBlock);
		WouldClient.registerLayers((modelLayerLocation, layerDefinitionSupplier)-> EntityModelLayerRegistry.registerModelLayer(modelLayerLocation, layerDefinitionSupplier::get));
		WouldClient.registerEntityRenderers(EntityRendererRegistry::register);
	}
}
