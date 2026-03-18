package com.ordana.would;

import com.ordana.would.entities.ModBoatRenderer;
import com.ordana.would.reg.ModBlocks;
import com.ordana.would.reg.ModEntities;
import net.mehvahdjukaar.moonlight.api.client.renderer.FallingBlockRendererGeneric;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.util.math.colors.RGBColor;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class WouldClient {

    public static void init() {
        ClientHelper.addClientSetup(WouldClient::setup);
        ClientHelper.addBlockColorsRegistration(WouldClient::registerBlockColors);
        ClientHelper.addItemColorsRegistration(WouldClient::registerItemColors);
        ClientHelper.addEntityRenderersRegistration(WouldClient::registerEntityRenderers);
        ClientHelper.addModelLayerRegistration(WouldClient::registerLayers);
    }

    private static boolean finishedSetup = false;

    public static void setup() {
        ClientHelper.registerRenderType(ModBlocks.PALE_HANGING_MOSS, RenderType.cutoutMipped());

        ClientHelper.registerRenderType(ModBlocks.WILLOW_LEAVES, RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.BAOBAB_LEAVES, RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.EBONY_LEAVES, RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.EBONY_LEAVES_FRUITING, RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.FIR_LEAVES, RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.PINE_LEAVES, RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.CEDAR_LEAVES, RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.MAHOGANY_LEAVES, RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.PALM_LEAVES, RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.MAPLE_LEAVES, RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.ASPEN_LEAVES, RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.WALNUT_LEAVES, RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.BLUE_SPRUCE_LEAVES, RenderType.cutoutMipped());
        
        ClientHelper.registerRenderType(ModBlocks.WILLOW_SAPLING, RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.BAOBAB_SAPLING, RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.EBONY_SAPLING, RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.FIR_SAPLING, RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.PINE_SAPLING, RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.CEDAR_SAPLING, RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.MAHOGANY_SAPLING, RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.MAPLE_SAPLING, RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.ASPEN_SAPLING, RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.WALNUT_SAPLING, RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.BLUE_SPRUCE_SAPLING, RenderType.cutoutMipped());

        ClientHelper.registerRenderType(ModBlocks.POTTED_WILLOW_SAPLING, RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.POTTED_BAOBAB_SAPLING, RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.POTTED_EBONY_SAPLING, RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.POTTED_FIR_SAPLING, RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.POTTED_PINE_SAPLING, RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.POTTED_CEDAR_SAPLING, RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.POTTED_MAHOGANY_SAPLING, RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.POTTED_MAPLE_SAPLING, RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.POTTED_ASPEN_SAPLING, RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.POTTED_WALNUT_SAPLING, RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.POTTED_BLUE_SPRUCE_SAPLING, RenderType.cutoutMipped());

        ClientHelper.registerRenderType(ModBlocks.WILLOW_DOOR, RenderType.cutout());
        ClientHelper.registerRenderType(ModBlocks.BAOBAB_DOOR, RenderType.cutout());
        ClientHelper.registerRenderType(ModBlocks.EBONY_DOOR, RenderType.cutout());
        ClientHelper.registerRenderType(ModBlocks.FIR_DOOR, RenderType.cutout());
        ClientHelper.registerRenderType(ModBlocks.PINE_DOOR, RenderType.cutout());
        ClientHelper.registerRenderType(ModBlocks.CEDAR_DOOR, RenderType.cutout());
        ClientHelper.registerRenderType(ModBlocks.MAHOGANY_DOOR, RenderType.cutout());
        ClientHelper.registerRenderType(ModBlocks.AZALEA_DOOR, RenderType.cutout());
        ClientHelper.registerRenderType(ModBlocks.PALM_DOOR, RenderType.cutout());
        ClientHelper.registerRenderType(ModBlocks.MAPLE_DOOR, RenderType.cutout());
        ClientHelper.registerRenderType(ModBlocks.ASPEN_DOOR, RenderType.cutout());
        ClientHelper.registerRenderType(ModBlocks.WALNUT_DOOR, RenderType.cutout());
        ClientHelper.registerRenderType(ModBlocks.BLUE_SPRUCE_DOOR, RenderType.cutout());

        ClientHelper.registerRenderType(ModBlocks.WILLOW_TRAPDOOR, RenderType.cutout());
        ClientHelper.registerRenderType(ModBlocks.BAOBAB_TRAPDOOR, RenderType.cutout());
        ClientHelper.registerRenderType(ModBlocks.EBONY_TRAPDOOR, RenderType.cutout());
        ClientHelper.registerRenderType(ModBlocks.FIR_TRAPDOOR, RenderType.cutout());
        ClientHelper.registerRenderType(ModBlocks.PINE_TRAPDOOR, RenderType.cutout());
        ClientHelper.registerRenderType(ModBlocks.CEDAR_TRAPDOOR, RenderType.cutout());
        ClientHelper.registerRenderType(ModBlocks.MAHOGANY_TRAPDOOR, RenderType.cutout());
        ClientHelper.registerRenderType(ModBlocks.AZALEA_TRAPDOOR, RenderType.cutout());
        ClientHelper.registerRenderType(ModBlocks.PALM_TRAPDOOR, RenderType.cutout());
        ClientHelper.registerRenderType(ModBlocks.MAPLE_TRAPDOOR, RenderType.cutout());
        ClientHelper.registerRenderType(ModBlocks.ASPEN_TRAPDOOR, RenderType.cutout());
        ClientHelper.registerRenderType(ModBlocks.WALNUT_TRAPDOOR, RenderType.cutout());
        ClientHelper.registerRenderType(ModBlocks.BLUE_SPRUCE_TRAPDOOR, RenderType.cutout());

        finishedSetup = true;
    }

    public static void checkIfFailed() {
        if(!finishedSetup){
            throw new RuntimeException("Failed to run client setup. This is likely due to the mod integration code being outdated, crashing with other mods new versions. Terminating");
        }
    }

    private static void registerBlockColors(ClientHelper.BlockColorEvent event) {
        event.register((blockState, level, blockPos, i) -> event.getColor(Blocks.OAK_LEAVES.defaultBlockState(), level, blockPos, i),
                ModBlocks.WILLOW_LEAVES,
                ModBlocks.MAHOGANY_LEAVES,
                ModBlocks.BAOBAB_LEAVES,
                ModBlocks.WALNUT_LEAVES);

        event.register((blockState, level, blockPos, i) -> event.getColor(Blocks.BIRCH_LEAVES.defaultBlockState(), level, blockPos, i),
                ModBlocks.PALM_LEAVES,
                ModBlocks.CEDAR_LEAVES);

        event.register((blockState, level, blockPos, i) -> event.getColor(Blocks.SPRUCE_LEAVES.defaultBlockState(), level, blockPos, i),
                ModBlocks.PINE_LEAVES,
                ModBlocks.FIR_LEAVES);
    }


    private static void registerItemColors(ClientHelper.ItemColorEvent event) {
        event.register((itemStack, i) -> event.getColor(Items.OAK_LEAVES.getDefaultInstance(), i),
                ModBlocks.WILLOW_LEAVES,
                ModBlocks.MAHOGANY_LEAVES,
                ModBlocks.BAOBAB_LEAVES,
                ModBlocks.WALNUT_LEAVES);

        event.register((itemStack, i) -> event.getColor(Items.BIRCH_LEAVES.getDefaultInstance(), i),
                ModBlocks.PALM_LEAVES,
                ModBlocks.CEDAR_LEAVES);

        event.register((itemStack, i) -> event.getColor(Items.SPRUCE_LEAVES.getDefaultInstance(), i),
                ModBlocks.PINE_LEAVES,
                ModBlocks.FIR_LEAVES);
    }

    private static int getLeafTypeColor(ClientHelper.BlockColorEvent event, LeavesType type, BlockState state, BlockAndTintGetter level, BlockPos pos, int i) {
        int original = event.getColor(type.leaves.defaultBlockState(), level, pos, i);

        //interpolate between color and brown
        float percentage = /*state.getValue(LeafPileBlock.AGE) /*/ 10f;
        int brown = 0x7D5212;
        return new RGBColor(original).asLAB().mixWith(new RGBColor(brown).asLAB(), percentage).asRGB().toInt();
    }

    private static void registerEntityRenderers(ClientHelper.EntityRendererEvent event) {
        event.register(ModEntities.FALLING_COCONUT, FallingBlockRendererGeneric::new);
        event.register(ModEntities.THROWN_WALNUT, context -> new ThrownItemRenderer<>(context, 1, false));
        event.register(ModEntities.MOD_BOAT, context -> new ModBoatRenderer(context, false));
        event.register(ModEntities.MOD_CHEST_BOAT, context -> new ModBoatRenderer(context, true));

    }

    private static void registerLayers(ClientHelper.ModelLayerEvent event) {
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