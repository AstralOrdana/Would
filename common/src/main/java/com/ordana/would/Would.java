package com.ordana.would;

import com.ordana.would.reg.*;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Would {

    public static final String MOD_ID = "would";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static ResourceLocation res(String name) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
    }

    public static void commonInit() {

        if (PlatHelper.getPhysicalSide().isClient()) {
            //ClientConfigs.init();
            ModCreativeTab.init();
            WouldClient.init();
        }

        ModBlocks.init();
        ModItems.init();
        ModTrees.init();
        ModSoundEvents.init();
        ModBlockSetTypes.init();
        ModTags.init();
        ModTreeGrowers.init();
        ModWoodTypes.init();
        ModEntities.init();
        ModWorldgenFeatures.init();
        PlatHelper.addCommonSetup(Would::setup);
    }

    public static void setup() {
        ModWoodSetup.init();
    }

}