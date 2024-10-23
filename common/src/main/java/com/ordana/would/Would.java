package com.ordana.would;

import com.ordana.would.reg.ModBlocks;
import com.ordana.would.reg.ModCreativeTab;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Would {

    public static final String MOD_ID = "would";

    public static ResourceLocation res(String name) {
        return new ResourceLocation(MOD_ID, name);
    }

    public static void commonInit() {

        if (PlatHelper.getPhysicalSide().isClient()) {
            //ClientConfigs.init();
            ModCreativeTab.init();
            WouldClient.init();
        }

        ModBlocks.init();
    }

}