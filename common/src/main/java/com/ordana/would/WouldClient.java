package com.ordana.would;

import com.ordana.would.entities.ModBoatRenderer;
import com.ordana.would.reg.ModBlocks;
import com.ordana.would.reg.ModEntities;
import net.mehvahdjukaar.moonlight.api.client.renderer.FallingBlockRendererGeneric;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.level.FoliageColor;

public class WouldClient {

    private static boolean finishedSetup = false;

    public static void setup(WouldPlatform.RenderTypeEvent event) {
        event.register(ModBlocks.PALE_HANGING_MOSS, RenderType.cutoutMipped());

        event.register(ModBlocks.WILLOW_LEAVES, RenderType.cutoutMipped());
        event.register(ModBlocks.BAOBAB_LEAVES, RenderType.cutoutMipped());
        event.register(ModBlocks.EBONY_LEAVES, RenderType.cutoutMipped());
        event.register(ModBlocks.EBONY_LEAVES_FRUITING, RenderType.cutoutMipped());
        event.register(ModBlocks.FIR_LEAVES, RenderType.cutoutMipped());
        event.register(ModBlocks.PINE_LEAVES, RenderType.cutoutMipped());
        event.register(ModBlocks.CEDAR_LEAVES, RenderType.cutoutMipped());
        event.register(ModBlocks.MAHOGANY_LEAVES, RenderType.cutoutMipped());
        event.register(ModBlocks.PALM_LEAVES, RenderType.cutoutMipped());
        event.register(ModBlocks.MAPLE_LEAVES, RenderType.cutoutMipped());
        event.register(ModBlocks.ASPEN_LEAVES, RenderType.cutoutMipped());
        event.register(ModBlocks.WALNUT_LEAVES, RenderType.cutoutMipped());
        event.register(ModBlocks.BLUE_SPRUCE_LEAVES, RenderType.cutoutMipped());
        
        event.register(ModBlocks.WILLOW_SAPLING, RenderType.cutoutMipped());
        event.register(ModBlocks.BAOBAB_SAPLING, RenderType.cutoutMipped());
        event.register(ModBlocks.EBONY_SAPLING, RenderType.cutoutMipped());
        event.register(ModBlocks.FIR_SAPLING, RenderType.cutoutMipped());
        event.register(ModBlocks.PINE_SAPLING, RenderType.cutoutMipped());
        event.register(ModBlocks.CEDAR_SAPLING, RenderType.cutoutMipped());
        event.register(ModBlocks.MAHOGANY_SAPLING, RenderType.cutoutMipped());
        event.register(ModBlocks.MAPLE_SAPLING, RenderType.cutoutMipped());
        event.register(ModBlocks.ASPEN_SAPLING, RenderType.cutoutMipped());
        event.register(ModBlocks.WALNUT_SAPLING, RenderType.cutoutMipped());
        event.register(ModBlocks.BLUE_SPRUCE_SAPLING, RenderType.cutoutMipped());

        event.register(ModBlocks.POTTED_WILLOW_SAPLING, RenderType.cutoutMipped());
        event.register(ModBlocks.POTTED_BAOBAB_SAPLING, RenderType.cutoutMipped());
        event.register(ModBlocks.POTTED_EBONY_SAPLING, RenderType.cutoutMipped());
        event.register(ModBlocks.POTTED_FIR_SAPLING, RenderType.cutoutMipped());
        event.register(ModBlocks.POTTED_PINE_SAPLING, RenderType.cutoutMipped());
        event.register(ModBlocks.POTTED_CEDAR_SAPLING, RenderType.cutoutMipped());
        event.register(ModBlocks.POTTED_MAHOGANY_SAPLING, RenderType.cutoutMipped());
        event.register(ModBlocks.POTTED_MAPLE_SAPLING, RenderType.cutoutMipped());
        event.register(ModBlocks.POTTED_ASPEN_SAPLING, RenderType.cutoutMipped());
        event.register(ModBlocks.POTTED_WALNUT_SAPLING, RenderType.cutoutMipped());
        event.register(ModBlocks.POTTED_BLUE_SPRUCE_SAPLING, RenderType.cutoutMipped());

        event.register(ModBlocks.WILLOW_DOOR, RenderType.cutout());
        event.register(ModBlocks.BAOBAB_DOOR, RenderType.cutout());
        event.register(ModBlocks.EBONY_DOOR, RenderType.cutout());
        event.register(ModBlocks.FIR_DOOR, RenderType.cutout());
        event.register(ModBlocks.PINE_DOOR, RenderType.cutout());
        event.register(ModBlocks.CEDAR_DOOR, RenderType.cutout());
        event.register(ModBlocks.MAHOGANY_DOOR, RenderType.cutout());
        event.register(ModBlocks.AZALEA_DOOR, RenderType.cutout());
        event.register(ModBlocks.PALM_DOOR, RenderType.cutout());
        event.register(ModBlocks.MAPLE_DOOR, RenderType.cutout());
        event.register(ModBlocks.ASPEN_DOOR, RenderType.cutout());
        event.register(ModBlocks.WALNUT_DOOR, RenderType.cutout());
        event.register(ModBlocks.BLUE_SPRUCE_DOOR, RenderType.cutout());

        event.register(ModBlocks.WILLOW_TRAPDOOR, RenderType.cutout());
        event.register(ModBlocks.BAOBAB_TRAPDOOR, RenderType.cutout());
        event.register(ModBlocks.EBONY_TRAPDOOR, RenderType.cutout());
        event.register(ModBlocks.FIR_TRAPDOOR, RenderType.cutout());
        event.register(ModBlocks.PINE_TRAPDOOR, RenderType.cutout());
        event.register(ModBlocks.CEDAR_TRAPDOOR, RenderType.cutout());
        event.register(ModBlocks.MAHOGANY_TRAPDOOR, RenderType.cutout());
        event.register(ModBlocks.AZALEA_TRAPDOOR, RenderType.cutout());
        event.register(ModBlocks.PALM_TRAPDOOR, RenderType.cutout());
        event.register(ModBlocks.MAPLE_TRAPDOOR, RenderType.cutout());
        event.register(ModBlocks.ASPEN_TRAPDOOR, RenderType.cutout());
        event.register(ModBlocks.WALNUT_TRAPDOOR, RenderType.cutout());
        event.register(ModBlocks.BLUE_SPRUCE_TRAPDOOR, RenderType.cutout());

        finishedSetup = true;
    }

    public static void checkIfFailed() {
        if(!finishedSetup){
            throw new RuntimeException("Failed to run client setup. This is likely due to the mod integration code being outdated, crashing with other mods new versions. Terminating");
        }
    }

    public static void registerBlockColors(WouldPlatform.BlockColorEvent event) {
        event.register((blockState, level, blockPos, i) -> level != null && blockPos != null ? BiomeColors.getAverageFoliageColor(level, blockPos) : FoliageColor.getDefaultColor(),
                ModBlocks.WILLOW_LEAVES,
                ModBlocks.MAHOGANY_LEAVES,
                ModBlocks.BAOBAB_LEAVES,
                ModBlocks.WALNUT_LEAVES);

        event.register((blockState, level, blockPos, i) -> FoliageColor.getBirchColor(),
                ModBlocks.PALM_LEAVES,
                ModBlocks.CEDAR_LEAVES);

        event.register((blockState, level, blockPos, i) -> FoliageColor.getEvergreenColor(),
                ModBlocks.PINE_LEAVES,
                ModBlocks.FIR_LEAVES);
    }


    public static void registerItemColors(WouldPlatform.ItemColorEvent event) {
        event.register((itemStack, i) -> FoliageColor.getDefaultColor(),
                ModBlocks.WILLOW_LEAVES,
                ModBlocks.MAHOGANY_LEAVES,
                ModBlocks.BAOBAB_LEAVES,
                ModBlocks.WALNUT_LEAVES);

        event.register((itemStack, i) -> FoliageColor.getBirchColor(),
                ModBlocks.PALM_LEAVES,
                ModBlocks.CEDAR_LEAVES);

        event.register((itemStack, i) -> FoliageColor.getEvergreenColor(),
                ModBlocks.PINE_LEAVES,
                ModBlocks.FIR_LEAVES);
    }

    public static void registerEntityRenderers(WouldPlatform.EntityRendererEvent event) {
        event.register(ModEntities.FALLING_COCONUT, FallingBlockRendererGeneric::new);
        event.register(ModEntities.THROWN_WALNUT, context -> new ThrownItemRenderer<>(context, 1, false));
        event.register(ModEntities.MOD_BOAT, context -> new ModBoatRenderer(context, false));
        event.register(ModEntities.MOD_CHEST_BOAT, context -> new ModBoatRenderer(context, true));

    }

    public static void registerLayers(WouldPlatform.ModelLayerEvent event) {
        event.register(loc("boat/aspen"), BoatModel::createBodyModel);
        event.register(loc("boat/azalea"), BoatModel::createBodyModel);
        event.register(loc("boat/baobab"), BoatModel::createBodyModel);
        event.register(loc("boat/cedar"), BoatModel::createBodyModel);
        event.register(loc("boat/ebony"), BoatModel::createBodyModel);
        event.register(loc("boat/fir"), BoatModel::createBodyModel);
        event.register(loc("boat/mahogany"), BoatModel::createBodyModel);
        event.register(loc("boat/maple"), BoatModel::createBodyModel);
        event.register(loc("boat/pine"), BoatModel::createBodyModel);
        event.register(loc("boat/palm"), BoatModel::createBodyModel);
        event.register(loc("boat/walnut"), BoatModel::createBodyModel);
        event.register(loc("boat/willow"), BoatModel::createBodyModel);
        event.register(loc("boat/blue_spruce"), BoatModel::createBodyModel);

        event.register(loc("chest_boat/aspen"), ChestBoatModel::createBodyModel);
        event.register(loc("chest_boat/azalea"), ChestBoatModel::createBodyModel);
        event.register(loc("chest_boat/baobab"), ChestBoatModel::createBodyModel);
        event.register(loc("chest_boat/cedar"), ChestBoatModel::createBodyModel);
        event.register(loc("chest_boat/ebony"), ChestBoatModel::createBodyModel);
        event.register(loc("chest_boat/fir"), ChestBoatModel::createBodyModel);
        event.register(loc("chest_boat/mahogany"), ChestBoatModel::createBodyModel);
        event.register(loc("chest_boat/maple"), ChestBoatModel::createBodyModel);
        event.register(loc("chest_boat/pine"), ChestBoatModel::createBodyModel);
        event.register(loc("chest_boat/palm"), ChestBoatModel::createBodyModel);
        event.register(loc("chest_boat/walnut"), ChestBoatModel::createBodyModel);
        event.register(loc("chest_boat/willow"), ChestBoatModel::createBodyModel);
        event.register(loc("chest_boat/blue_spruce"), ChestBoatModel::createBodyModel);

    }

    private static ModelLayerLocation loc(String name) {
        return new ModelLayerLocation(Would.res(name), "main");
    }

}