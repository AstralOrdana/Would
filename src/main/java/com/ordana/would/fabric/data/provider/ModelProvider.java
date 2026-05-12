package com.ordana.would.fabric.data.provider;

import com.ordana.would.reg.ModBlocks;
import com.ordana.would.reg.ModItems;
import com.ordana.would.reg.ModWoodTypes;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.color.block.BlockTintSources;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.Blocks;

import java.util.List;

public class ModelProvider extends FabricModelProvider {

    public ModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators generators) {
        generators.createPlant(ModBlocks.ASPEN_SAPLING, ModBlocks.POTTED_ASPEN_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        generators.createPlant(ModBlocks.FIR_SAPLING, ModBlocks.POTTED_FIR_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        generators.createPlant(ModBlocks.MAPLE_SAPLING, ModBlocks.POTTED_MAPLE_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        generators.createPlant(ModBlocks.WILLOW_SAPLING, ModBlocks.POTTED_WILLOW_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        generators.createPlant(ModBlocks.WALNUT_SAPLING, ModBlocks.POTTED_WALNUT_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        generators.createPlant(ModBlocks.MAHOGANY_SAPLING, ModBlocks.POTTED_MAHOGANY_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        generators.createPlant(ModBlocks.PINE_SAPLING, ModBlocks.POTTED_PINE_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        generators.createPlant(ModBlocks.BAOBAB_SAPLING, ModBlocks.POTTED_BAOBAB_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        generators.createPlant(ModBlocks.CEDAR_SAPLING, ModBlocks.POTTED_CEDAR_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        generators.createPlant(ModBlocks.EBONY_SAPLING, ModBlocks.POTTED_EBONY_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        generators.createPlant(ModBlocks.BLUE_SPRUCE_SAPLING, ModBlocks.POTTED_BLUE_SPRUCE_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);

        ModWoodTypes.ALL.forEach(wouldType -> {
            generators.woodProvider(wouldType.log()).logWithHorizontal(wouldType.log()).wood(wouldType.wood());
            generators.woodProvider(wouldType.strippedLog()).logWithHorizontal(wouldType.strippedLog()).wood(wouldType.strippedWood());
            generators.createHangingSign(wouldType.planks(), wouldType.hangingSignBlock(), wouldType.wallHangingSignBlock());
            generators.createShelf(wouldType.shelfBlock(), wouldType.planks());
            generators.family(wouldType.planks()).generateFor(wouldType.family());
        });

        generators.createTintedLeaves(ModBlocks.WILLOW_LEAVES, TexturedModel.LEAVES, FoliageColor.FOLIAGE_DEFAULT);
        generators.createTintedLeaves(ModBlocks.MAHOGANY_LEAVES, TexturedModel.LEAVES, FoliageColor.FOLIAGE_DEFAULT);
        generators.createTintedLeaves(ModBlocks.BAOBAB_LEAVES, TexturedModel.LEAVES, FoliageColor.FOLIAGE_DEFAULT);
        generators.createTintedLeaves(ModBlocks.WALNUT_LEAVES, TexturedModel.LEAVES, FoliageColor.FOLIAGE_DEFAULT);

        generators.createTintedLeaves(ModBlocks.PALM_LEAVES, TexturedModel.LEAVES, FoliageColor.FOLIAGE_BIRCH);
        generators.createTintedLeaves(ModBlocks.CEDAR_LEAVES, TexturedModel.LEAVES, FoliageColor.FOLIAGE_BIRCH);

        generators.createTintedLeaves(ModBlocks.PINE_LEAVES, TexturedModel.LEAVES, FoliageColor.FOLIAGE_EVERGREEN);
        generators.createTintedLeaves(ModBlocks.FIR_LEAVES, TexturedModel.LEAVES, FoliageColor.FOLIAGE_EVERGREEN);
        generators.createTrivialCube(ModBlocks.BLUE_SPRUCE_LEAVES);
        generators.createTrivialCube(ModBlocks.ASPEN_LEAVES);
        generators.createTrivialCube(ModBlocks.EBONY_LEAVES);
        generators.createTrivialCube(ModBlocks.MAPLE_LEAVES);
    }

    @Override
    public void generateItemModels(ItemModelGenerators generators) {
        ModItems.ALL_BOATS.forEach(item -> generators.generateFlatItem(item, ModelTemplates.FLAT_ITEM));
        ModItems.ALL_CHEST_BOATS.forEach(item -> generators.generateFlatItem(item, ModelTemplates.FLAT_ITEM));
        generators.generateFlatItem(ModItems.COCONUT, ModelTemplates.FLAT_ITEM);
    }

}
