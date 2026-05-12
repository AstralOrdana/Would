package com.ordana.would;

import com.ordana.would.reg.ModBlocks;
import com.ordana.would.reg.ModEntities;
import net.minecraft.client.color.block.BlockTintSources;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.object.boat.BoatModel;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.FallingBlockRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.level.FoliageColor;

import java.util.List;

import static com.ordana.would.reg.ModEntities.BOATS;
import static com.ordana.would.reg.ModEntities.CHEST_BOATS;
import static net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry.registerModelLayer;

public class WouldClient {

    private static boolean finishedSetup = false;

    public static void checkIfFailed() {
        if(!finishedSetup){
            throw new RuntimeException("Failed to run client setup. This is likely due to the mod integration code being outdated, crashing with other mods new versions. Terminating");
        }
    }

    public static void registerBlockColors(WouldPlatform.BlockColorEvent event) {
        event.register(List.of(BlockTintSources.foliage()),
                ModBlocks.WILLOW_LEAVES,
                ModBlocks.MAHOGANY_LEAVES,
                ModBlocks.BAOBAB_LEAVES,
                ModBlocks.WALNUT_LEAVES);

        event.register(List.of((blockState) -> FoliageColor.FOLIAGE_BIRCH),
                ModBlocks.PALM_LEAVES,
                ModBlocks.CEDAR_LEAVES);

        event.register(List.of((blockState)->FoliageColor.FOLIAGE_EVERGREEN),
                ModBlocks.PINE_LEAVES,
                ModBlocks.FIR_LEAVES);
    }

    public static void registerEntityRenderers(WouldPlatform.EntityRendererEvent event) {
        event.register(ModEntities.FALLING_COCONUT, FallingBlockRenderer::new);
        event.register(ModEntities.THROWN_WALNUT, context -> new ThrownItemRenderer<>(context, 1, false));
        BOATS.forEach((id, entityType) -> {
            var layer = new ModelLayerLocation(Would.res("boat/" + id), "main");
            registerModelLayer(layer, BoatModel::createBoatModel);
            EntityRenderers.register(entityType, (context) -> new BoatRenderer(context, layer));
        });
        CHEST_BOATS.forEach((id, entityType) -> {
            var layer = new ModelLayerLocation(Would.res("chest_boat/" + id), "main");
            registerModelLayer(layer, BoatModel::createChestBoatModel);
            EntityRenderers.register(entityType, (context) -> new BoatRenderer(context, layer));
        });

    }

    private static ModelLayerLocation loc(String name) {
        return new ModelLayerLocation(Would.res(name), "main");
    }

}