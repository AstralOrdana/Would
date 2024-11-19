package com.ordana.would;

import com.ordana.would.entities.ModBoatRenderer;
import com.ordana.would.reg.ModBlocks;
import com.ordana.would.reg.ModEntities;
import net.mehvahdjukaar.moonlight.api.client.renderer.FallingBlockRendererGeneric;
import net.mehvahdjukaar.moonlight.api.misc.EventCalled;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.util.math.colors.RGBColor;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.RenderType;
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
        ClientHelper.registerRenderType(ModBlocks.WILLOW_LEAVES.get(), RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.BAOBAB_LEAVES.get(), RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.EBONY_LEAVES.get(), RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.EBONY_LEAVES_FRUITING.get(), RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.FIR_LEAVES.get(), RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.PINE_LEAVES.get(), RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.CEDAR_LEAVES.get(), RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.MAHOGANY_LEAVES.get(), RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.PALM_LEAVES.get(), RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.MAPLE_LEAVES.get(), RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.ASPEN_LEAVES.get(), RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.WALNUT_LEAVES.get(), RenderType.cutoutMipped());
        
        ClientHelper.registerRenderType(ModBlocks.WILLOW_SAPLING.get(), RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.BAOBAB_SAPLING.get(), RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.EBONY_SAPLING.get(), RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.FIR_SAPLING.get(), RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.PINE_SAPLING.get(), RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.CEDAR_SAPLING.get(), RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.MAHOGANY_SAPLING.get(), RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.MAPLE_SAPLING.get(), RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.ASPEN_SAPLING.get(), RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.WALNUT_SAPLING.get(), RenderType.cutoutMipped());

        ClientHelper.registerRenderType(ModBlocks.POTTED_WILLOW_SAPLING.get(), RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.POTTED_BAOBAB_SAPLING.get(), RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.POTTED_EBONY_SAPLING.get(), RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.POTTED_FIR_SAPLING.get(), RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.POTTED_PINE_SAPLING.get(), RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.POTTED_CEDAR_SAPLING.get(), RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.POTTED_MAHOGANY_SAPLING.get(), RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.POTTED_MAPLE_SAPLING.get(), RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.POTTED_ASPEN_SAPLING.get(), RenderType.cutoutMipped());
        ClientHelper.registerRenderType(ModBlocks.POTTED_WALNUT_SAPLING.get(), RenderType.cutoutMipped());

        ClientHelper.registerRenderType(ModBlocks.WILLOW_DOOR.get(), RenderType.cutout());
        ClientHelper.registerRenderType(ModBlocks.BAOBAB_DOOR.get(), RenderType.cutout());
        ClientHelper.registerRenderType(ModBlocks.EBONY_DOOR.get(), RenderType.cutout());
        ClientHelper.registerRenderType(ModBlocks.FIR_DOOR.get(), RenderType.cutout());
        ClientHelper.registerRenderType(ModBlocks.PINE_DOOR.get(), RenderType.cutout());
        ClientHelper.registerRenderType(ModBlocks.CEDAR_DOOR.get(), RenderType.cutout());
        ClientHelper.registerRenderType(ModBlocks.MAHOGANY_DOOR.get(), RenderType.cutout());
        ClientHelper.registerRenderType(ModBlocks.AZALEA_DOOR.get(), RenderType.cutout());
        ClientHelper.registerRenderType(ModBlocks.PALM_DOOR.get(), RenderType.cutout());
        ClientHelper.registerRenderType(ModBlocks.MAPLE_DOOR.get(), RenderType.cutout());
        ClientHelper.registerRenderType(ModBlocks.ASPEN_DOOR.get(), RenderType.cutout());
        ClientHelper.registerRenderType(ModBlocks.WALNUT_DOOR.get(), RenderType.cutout());

        ClientHelper.registerRenderType(ModBlocks.WILLOW_TRAPDOOR.get(), RenderType.cutout());
        ClientHelper.registerRenderType(ModBlocks.BAOBAB_TRAPDOOR.get(), RenderType.cutout());
        ClientHelper.registerRenderType(ModBlocks.EBONY_TRAPDOOR.get(), RenderType.cutout());
        ClientHelper.registerRenderType(ModBlocks.FIR_TRAPDOOR.get(), RenderType.cutout());
        ClientHelper.registerRenderType(ModBlocks.PINE_TRAPDOOR.get(), RenderType.cutout());
        ClientHelper.registerRenderType(ModBlocks.CEDAR_TRAPDOOR.get(), RenderType.cutout());
        ClientHelper.registerRenderType(ModBlocks.MAHOGANY_TRAPDOOR.get(), RenderType.cutout());
        ClientHelper.registerRenderType(ModBlocks.AZALEA_TRAPDOOR.get(), RenderType.cutout());
        ClientHelper.registerRenderType(ModBlocks.PALM_TRAPDOOR.get(), RenderType.cutout());
        ClientHelper.registerRenderType(ModBlocks.MAPLE_TRAPDOOR.get(), RenderType.cutout());
        ClientHelper.registerRenderType(ModBlocks.ASPEN_TRAPDOOR.get(), RenderType.cutout());
        ClientHelper.registerRenderType(ModBlocks.WALNUT_TRAPDOOR.get(), RenderType.cutout());

        finishedSetup = true;
    }

    public static void checkIfFailed() {
        if(!finishedSetup){
            throw new RuntimeException("Failed to run client setup. This is likely due to the mod integration code being outdated, crashing with other mods new versions. Terminating");
        }
    }

    @EventCalled
    private static void registerBlockColors(ClientHelper.BlockColorEvent event) {
        event.register((blockState, level, blockPos, i) -> event.getColor(Blocks.OAK_LEAVES.defaultBlockState(), level, blockPos, i),
                ModBlocks.WILLOW_LEAVES.get(),
                ModBlocks.MAHOGANY_LEAVES.get(),
                ModBlocks.BAOBAB_LEAVES.get(),
                ModBlocks.WALNUT_LEAVES.get());

        event.register((blockState, level, blockPos, i) -> event.getColor(Blocks.BIRCH_LEAVES.defaultBlockState(), level, blockPos, i),
                ModBlocks.PALM_LEAVES.get(),
                ModBlocks.CEDAR_LEAVES.get());

        event.register((blockState, level, blockPos, i) -> event.getColor(Blocks.SPRUCE_LEAVES.defaultBlockState(), level, blockPos, i),
                ModBlocks.PINE_LEAVES.get(),
                ModBlocks.FIR_LEAVES.get());
    }


    private static void registerItemColors(ClientHelper.ItemColorEvent event) {
        event.register((itemStack, i) -> event.getColor(Items.OAK_LEAVES.getDefaultInstance(), i),
                ModBlocks.WILLOW_LEAVES.get(),
                ModBlocks.MAHOGANY_LEAVES.get(),
                ModBlocks.BAOBAB_LEAVES.get(),
                ModBlocks.WALNUT_LEAVES.get());

        event.register((itemStack, i) -> event.getColor(Items.BIRCH_LEAVES.getDefaultInstance(), i),
                ModBlocks.PALM_LEAVES.get(),
                ModBlocks.CEDAR_LEAVES.get());

        event.register((itemStack, i) -> event.getColor(Items.SPRUCE_LEAVES.getDefaultInstance(), i),
                ModBlocks.PINE_LEAVES.get(),
                ModBlocks.FIR_LEAVES.get());
    }

    private static int getLeafTypeColor(ClientHelper.BlockColorEvent event, LeavesType type, BlockState state, BlockAndTintGetter level, BlockPos pos, int i) {
        int original = event.getColor(type.leaves.defaultBlockState(), level, pos, i);

        //interpolate between color and brown
        float percentage = /*state.getValue(LeafPileBlock.AGE) /*/ 10f;
        int brown = 0x7D5212;
        return new RGBColor(original).asLAB().mixWith(new RGBColor(brown).asLAB(), percentage).asRGB().toInt();
    }

    private static void registerEntityRenderers(ClientHelper.EntityRendererEvent event) {
        event.register(ModEntities.FALLING_COCONUT.get(), FallingBlockRendererGeneric::new);
        event.register(ModEntities.MOD_BOAT.get(), pContext -> new ModBoatRenderer(pContext, false));
        event.register(ModEntities.MOD_CHEST_BOAT.get(), pContext -> new ModBoatRenderer(pContext, true));

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

    }

    private static ModelLayerLocation loc(String name) {
        return new ModelLayerLocation(Would.res(name), "main");
    }

}