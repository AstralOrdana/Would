package com.ordana.would.reg;

import com.ordana.would.Would;
import com.ordana.would.blocks.FallingCoconutEntity;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import java.util.function.Supplier;

public class ModEntities {

    public static void init() {
    }

    public static Supplier<EntityType<FallingCoconutEntity>> FALLING_COCONUT = RegHelper.registerEntityType(
            Would.res("falling_propagule"),
            FallingCoconutEntity::new, MobCategory.MISC, 0.28F, 0.98F, 10, 20);
}
