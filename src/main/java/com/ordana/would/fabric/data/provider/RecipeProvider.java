package com.ordana.would.fabric.data.provider;

import com.ordana.would.reg.*;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.tags.TagKey;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

/**
 * TODO: hanging signs, logs + wood to planks, logs to wood
 */
public class RecipeProvider extends FabricRecipeProvider {

    public RecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected net.minecraft.data.recipes.RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput exporter) {
        return new net.minecraft.data.recipes.RecipeProvider(registries, exporter) {

            @Override
            public void buildRecipes() {
                /*FIXME
                // generateRecipes(exporter, ModBlockFamilies.ASPEN_PLANKS, FeatureFlags.DEFAULT_FLAGS);
                // ModBlockFamilies.MAP.values().forEach(blockFamily -> generateRecipes(exporter, blockFamily, FeatureFlags.DEFAULT_FLAGS));
                ModWoodTypes.ALL.forEach(compound -> {
                    woodenBoat(compound.boatItem(), compound.planks());
                    woodenBoat(compound.chestBoatItem(), compound.planks());
                    hangingSign(exporter, compound.hangingSignItem(), compound.strippedLog());
                });
                BlockFactories.ALL_LOGS.forEach(compound -> woodFromLogs(exporter, compound.wood(), compound.log()));

                planksFromLogs(exporter, ModBlocks.ASPEN_PLANKS, ModTags.Items.ASPEN_LOGS);
                planksFromLogs(exporter, ModBlocks.AZALEA_PLANKS, ModTags.Items.AZALEA_LOGS);
                planksFromLogs(exporter, ModBlocks.BAOBAB_PLANKS, ModTags.Items.BAOBAB_LOGS);
                planksFromLogs(exporter, ModBlocks.CEDAR_PLANKS, ModTags.Items.CEDAR_LOGS);
                planksFromLogs(exporter, ModBlocks.EBONY_PLANKS, ModTags.Items.EBONY_LOGS);
                planksFromLogs(exporter, ModBlocks.FIR_PLANKS, ModTags.Items.FIR_LOGS);
                planksFromLogs(exporter, ModBlocks.MAHOGANY_PLANKS, ModTags.Items.MAHOGANY_LOGS);
                planksFromLogs(exporter, ModBlocks.MAPLE_PLANKS, ModTags.Items.MAPLE_LOGS);
                planksFromLogs(exporter, ModBlocks.PALM_PLANKS, ModTags.Items.PALM_LOGS);
                planksFromLogs(exporter, ModBlocks.PINE_PLANKS, ModTags.Items.PINE_LOGS);
                planksFromLogs(exporter, ModBlocks.WALNUT_PLANKS, ModTags.Items.WALNUT_LOGS);
                planksFromLogs(exporter, ModBlocks.WILLOW_PLANKS, ModTags.Items.WILLOW_LOGS);

                 */
            }
        };
    }



    /*
    private static void planksFromLogs(RecipeOutput exporter, Supplier<Block> planksSupplier, TagKey<Item> logTag) {
        planksFromLogs(exporter, planksSupplier.get(), logTag, 4);
    }

     */

    @Override
    public String getName() {
        return "";
    }
}
