//? neoforge {
package com.ordana.would.neoforge;

import com.ordana.would.WouldClient;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@EventBusSubscriber(value = Dist.CLIENT)
public class WouldNeoForgeClient {
	@SubscribeEvent
	public static void blockColors(RegisterColorHandlersEvent.BlockTintSources event) {
		WouldClient.registerBlockColors(event::register);
	}

	@SubscribeEvent
	public static void entityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		WouldClient.registerEntityRenderers(event::registerEntityRenderer);
	}

	@SubscribeEvent
	public static void entityLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
		WouldClient.registerLayers(event::registerLayerDefinition);
	}

	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		event.enqueueWork(WouldClient::registerSprites);
	}
}
//?}