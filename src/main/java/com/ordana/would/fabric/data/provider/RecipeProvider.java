//? fabric {
/*package com.ordana.would.fabric.data.provider;

import com.ordana.would.reg.ModWoodTypes;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.flag.FeatureFlags;

import java.util.concurrent.CompletableFuture;

/^*
 * TODO: hanging signs, logs + wood to planks, logs to wood
 ^/
public class RecipeProvider extends FabricRecipeProvider {

    public RecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected net.minecraft.data.recipes.RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput exporter) {
        return new net.minecraft.data.recipes.RecipeProvider(registries, exporter) {

            @Override
            public void buildRecipes() {

                ModWoodTypes.ALL.forEach(type -> {
                    generateRecipes(type.family(), FeatureFlags.DEFAULT_FLAGS);
                    woodenBoat(type.boatItem(), type.planks());
                    woodenBoat(type.chestBoatItem(), type.planks());
                    hangingSign(type.hangingSignItem(), type.strippedLog());
                    woodFromLogs(type.wood(), type.log());
                    planksFromLogs(type.planks(), type.logItemTagKey(), 4);
                    shelf(type.shelfBlock(), type.strippedLog());
                });
            }
        };
    }

    @Override
    public String getName() {
        return "";
    }
}
*///?}