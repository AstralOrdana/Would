package com.ordana.would.fabric.data.provider;

import com.ordana.would.reg.ModBlocks;
import com.ordana.would.reg.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;

public class ModelProvider extends FabricModelProvider {

    public ModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators generators) {
        generators.createPlant(ModBlocks.ASPEN_SAPLING, ModBlocks.POTTED_ASPEN_SAPLING, BlockModelGenerators.TintState.NOT_TINTED);
        // ModBlockFamilies.MAP.forEach((block, blockFamily) -> generators.family(block).generateFor(blockFamily));
/*        BlockFactories.ALL_HANGING_SIGNS.forEach(compound -> generators.createHangingSign(compound.strippedLog(), compound.ceiling(), compound.wall()));
        BlockFactories.ALL_SAPLINGS.forEach(compound -> generators.createPlant(compound.sapling(), compound.pottedSapling(), BlockModelGenerators.TintState.NOT_TINTED));
        BlockFactories.ALL_LOGS.forEach(compound -> {
            Block log = compound.log();
            generators.woodProvider(log).logWithHorizontal(log).wood(compound.wood());
        });*/
    }

    @Override
    public void generateItemModels(ItemModelGenerators generators) {
        ModItems.ALL_BOATS.forEach(item -> generators.generateFlatItem(item, ModelTemplates.FLAT_ITEM));
        ModItems.ALL_CHEST_BOATS.forEach(item -> generators.generateFlatItem(item, ModelTemplates.FLAT_ITEM));
    }

}
