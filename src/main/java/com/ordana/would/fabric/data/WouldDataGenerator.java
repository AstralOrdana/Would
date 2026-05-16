//? fabric {
/*package com.ordana.would.fabric.data;

import com.ordana.would.fabric.data.provider.*;
import com.ordana.would.reg.ModWoodTypes;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class WouldDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        ModWoodTypes.init();
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        var blockTags = pack.addProvider(BlockTagsProvider::new);
        pack.addProvider((output, registriesFuture) -> new ItemTagsProvider(output, registriesFuture, blockTags));
        pack.addProvider(ModelProvider::new);
        pack.addProvider(RecipeProvider::new);
        pack.addProvider(BlockLootProvider::new);
        pack.addProvider(NeoForgeDataMapProvider::new);
    }

}
*///?}