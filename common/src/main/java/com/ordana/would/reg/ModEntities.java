package com.ordana.would.reg;

import com.ordana.would.Would;
import com.ordana.would.entities.FallingCoconutEntity;
import com.ordana.would.entities.ModBoatEntity;
import com.ordana.would.entities.ModChestBoatEntity;
import com.ordana.would.entities.ThrownWalnutEntity;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public interface ModEntities {

    static void init() {
    }

    Supplier<EntityType<FallingCoconutEntity>> FALLING_COCONUT = registerEntityType(
        "falling_coconut",
        FallingCoconutEntity::new,
        MobCategory.MISC,
        builder -> builder
            .sized(0.28F, 0.98F)
            .clientTrackingRange(10)
            .updateInterval(20)
    );

    Supplier<EntityType<ModBoatEntity>> MOD_BOAT = registerEntityType(
        "mod_boat",
        ModBoatEntity::new,
        MobCategory.MISC,
        builder -> builder
            .sized(1.375F, 0.5625F)
            .eyeHeight(0.5625F)
            .clientTrackingRange(10)
    );

    Supplier<EntityType<ModChestBoatEntity>> MOD_CHEST_BOAT = registerEntityType(
        "mod_chest_boat",
        ModChestBoatEntity::new,
        MobCategory.MISC,
        builder -> builder
            .sized(1.375F, 0.5625F)
            .eyeHeight(0.5625F)
            .clientTrackingRange(10)
    );

    Supplier<EntityType<ThrownWalnutEntity>> THROWN_WALNUT = registerEntityType(
        "thrown_walnut",
        ThrownWalnutEntity::new,
        MobCategory.MISC,
        builder -> builder
            .sized(0.25F, 0.25F)
            .clientTrackingRange(4)
            .updateInterval(10)
    );

    private static <T extends Entity> Supplier<EntityType<T>> registerEntityType(String name, EntityType.EntityFactory<T> factory, MobCategory category, UnaryOperator<EntityType.Builder<T>> operation) {
        EntityType.Builder<T> builder = EntityType.Builder.of(factory, category);
        return RegHelper.registerEntityType(Would.res(name), operation.apply(builder));
    }

}
