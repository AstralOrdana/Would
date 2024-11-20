package com.ordana.would.reg;

import com.ordana.would.Would;
import com.ordana.would.entities.FallingCoconutEntity;
import com.ordana.would.entities.ModBoatEntity;
import com.ordana.would.entities.ModChestBoatEntity;
import com.ordana.would.entities.ThrownWalnutEntity;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import java.util.function.Supplier;

public class ModEntities {

    public static void init() {
    }

    public static Supplier<EntityType<FallingCoconutEntity>> FALLING_COCONUT = RegHelper.registerEntityType(
            Would.res("falling_coconut"),
            FallingCoconutEntity::new, MobCategory.MISC, 0.28F, 0.98F, 10, 20);

    public static Supplier<EntityType<ModBoatEntity>> MOD_BOAT = RegHelper.registerEntityType(
            Would.res("mod_boat"),
            ModBoatEntity::new, MobCategory.MISC, 1.375F, 0.5625F, 10, 20);

    public static Supplier<EntityType<ModChestBoatEntity>> MOD_CHEST_BOAT = RegHelper.registerEntityType(
            Would.res("mod_chest_boat"),
            ModChestBoatEntity::new, MobCategory.MISC, 1.375F, 0.5625F, 10, 20);

    public static Supplier<EntityType<ThrownWalnutEntity>> THROWN_WALNUT = RegHelper.registerEntityType(
            Would.res("thrown_walnut"),
            ThrownWalnutEntity::new, MobCategory.MISC, 0.7F, 0.7F, 10, 20);

}
