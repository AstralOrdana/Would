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
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

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
        registerRenderType(
            RenderType.cutoutMipped(),

            ModBlocks.PALE_HANGING_MOSS,
            ModBlocks.WILLOW_LEAVES,
            ModBlocks.BAOBAB_LEAVES,
            ModBlocks.EBONY_LEAVES,
            ModBlocks.EBONY_LEAVES_FRUITING,
            ModBlocks.FIR_LEAVES,
            ModBlocks.PINE_LEAVES,
            ModBlocks.CEDAR_LEAVES,
            ModBlocks.MAHOGANY_LEAVES,
            ModBlocks.PALM_LEAVES,
            ModBlocks.MAPLE_LEAVES,
            ModBlocks.ASPEN_LEAVES,
            ModBlocks.WALNUT_LEAVES,
            ModBlocks.BLUE_SPRUCE_LEAVES,

            ModBlocks.WILLOW_SAPLING,
            ModBlocks.BAOBAB_SAPLING,
            ModBlocks.EBONY_SAPLING,
            ModBlocks.FIR_SAPLING,
            ModBlocks.PINE_SAPLING,
            ModBlocks.CEDAR_SAPLING,
            ModBlocks.MAHOGANY_SAPLING,
            ModBlocks.MAPLE_SAPLING,
            ModBlocks.ASPEN_SAPLING,
            ModBlocks.WALNUT_SAPLING,
            ModBlocks.BLUE_SPRUCE_SAPLING,

            ModBlocks.POTTED_WILLOW_SAPLING,
            ModBlocks.POTTED_BAOBAB_SAPLING,
            ModBlocks.POTTED_EBONY_SAPLING,
            ModBlocks.POTTED_FIR_SAPLING,
            ModBlocks.POTTED_PINE_SAPLING,
            ModBlocks.POTTED_CEDAR_SAPLING,
            ModBlocks.POTTED_MAHOGANY_SAPLING,
            ModBlocks.POTTED_MAPLE_SAPLING,
            ModBlocks.POTTED_ASPEN_SAPLING,
            ModBlocks.POTTED_WALNUT_SAPLING,
            ModBlocks.POTTED_BLUE_SPRUCE_SAPLING
        );

        registerRenderType(
            RenderType.cutout(),
            
            ModBlocks.WILLOW_DOOR,
            ModBlocks.BAOBAB_DOOR,
            ModBlocks.EBONY_DOOR,
            ModBlocks.FIR_DOOR,
            ModBlocks.PINE_DOOR,
            ModBlocks.CEDAR_DOOR,
            ModBlocks.MAHOGANY_DOOR,
            ModBlocks.AZALEA_DOOR,
            ModBlocks.PALM_DOOR,
            ModBlocks.MAPLE_DOOR,
            ModBlocks.ASPEN_DOOR,
            ModBlocks.WALNUT_DOOR,
            ModBlocks.BLUE_SPRUCE_DOOR,

            ModBlocks.WILLOW_TRAPDOOR,
            ModBlocks.BAOBAB_TRAPDOOR,
            ModBlocks.EBONY_TRAPDOOR,
            ModBlocks.FIR_TRAPDOOR,
            ModBlocks.PINE_TRAPDOOR,
            ModBlocks.CEDAR_TRAPDOOR,
            ModBlocks.MAHOGANY_TRAPDOOR,
            ModBlocks.AZALEA_TRAPDOOR,
            ModBlocks.PALM_TRAPDOOR,
            ModBlocks.MAPLE_TRAPDOOR,
            ModBlocks.ASPEN_TRAPDOOR,
            ModBlocks.WALNUT_TRAPDOOR,
            ModBlocks.BLUE_SPRUCE_TRAPDOOR,
            
            ModBlocks.HANGING_WILLOW_LEAVES
        );

        finishedSetup = true;
    }

    public static void checkIfFailed() {
        if(!finishedSetup){
            throw new RuntimeException("Failed to run client setup. This is likely due to the mod integration code being outdated, crashing with other mods new versions. Terminating");
        }
    }

    @SafeVarargs
    private static void registerRenderType(RenderType renderType, Supplier<Block>... blocks) {
        for (Supplier<Block> block : blocks)
            ClientHelper.registerRenderType(block.get(), renderType);
    }

    @EventCalled
    private static void registerBlockColors(ClientHelper.BlockColorEvent event) {
        event.register((blockState, level, blockPos, i) -> event.getColor(Blocks.OAK_LEAVES.defaultBlockState(), level, blockPos, i),
                ModBlocks.WILLOW_LEAVES.get(),
                ModBlocks.HANGING_WILLOW_LEAVES.get(),
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
                ModBlocks.HANGING_WILLOW_LEAVES.get(),
                ModBlocks.MAHOGANY_LEAVES.get(),
                ModBlocks.BAOBAB_LEAVES.get(),
                ModBlocks.WALNUT_LEAVES.get());

        event.register((itemStack, i) -> event.getColor(Items.BIRCH_LEAVES.getDefaultInstance(), i),
                ModBlocks.PALM_LEAVES.get(),
                ModBlocks.CEDAR_LEAVES.get());

        event.register((itemStack, i) -> event.getColor(Items.SPRUCE_LEAVES.getDefaultInstance(), i),
                ModBlocks.PINE_LEAVES.get(),
                ModBlocks.FIR_SAPLING.get());
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
        event.register(ModEntities.THROWN_WALNUT.get(), context -> new ThrownItemRenderer<>(context, 1, false));
        event.register(ModEntities.MOD_BOAT.get(), context -> new ModBoatRenderer(context, false));
        event.register(ModEntities.MOD_CHEST_BOAT.get(), context -> new ModBoatRenderer(context, true));

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