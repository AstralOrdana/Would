package com.ordana.would;

import com.ordana.would.reg.*;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Would {

    public static final String MOD_ID = "would";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static ResourceLocation res(String name) {
        return new ResourceLocation(MOD_ID, name);
    }

    public static void commonInit() {

        if (PlatHelper.getPhysicalSide().isClient()) {
            //ClientConfigs.init();
            ModCreativeTab.init();
            WouldClient.init();
        }

        ModItems.init();
        ModBlocks.init();
        ModTrees.init();
        ModWorldgenFeatures.init();
        ModEntities.init();
        PlatHelper.addCommonSetup(Would::setup);
    }

    public static void setup() {
        ModWoodSetup.init();
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Would.res(name));
    }
}