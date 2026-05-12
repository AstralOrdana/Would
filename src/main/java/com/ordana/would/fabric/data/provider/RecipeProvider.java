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

                // generateRecipes(exporter, ModBlockFamilies.ASPEN_PLANKS, FeatureFlags.DEFAULT_FLAGS);
                // ModBlockFamilies.MAP.values().forEach(blockFamily -> generateRecipes(exporter, blockFamily, FeatureFlags.DEFAULT_FLAGS));
                ModWoodTypes.ALL.forEach(compound -> {
                    woodenBoat(compound.boatItem(), compound.planks());
                    woodenBoat(compound.chestBoatItem(), compound.planks());
                    hangingSign(compound.hangingSignItem(), compound.strippedLog());
                    woodFromLogs(compound.wood(), compound.log());
                    planksFromLogs(compound.planks(), compound.logItemTagKey(), 4);
                });
            }
        };
    }

    @Override
    public String getName() {
        return "";
    }
}
