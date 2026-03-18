package com.ordana.would.reg;

import com.ordana.would.Would;
import com.ordana.would.entities.FallingCoconutEntity;
import com.ordana.would.entities.ModBoatEntity;
import com.ordana.would.entities.ModChestBoatEntity;
import com.ordana.would.entities.ThrownWalnutEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntities {

    public static void init() {
    }

    public static EntityType<FallingCoconutEntity> FALLING_COCONUT = registerEntityType(
            Would.res("falling_coconut"),
            FallingCoconutEntity::new, MobCategory.MISC, 0.28F, 0.98F, 10, 20);

    public static EntityType<ModBoatEntity> MOD_BOAT = registerEntityType(
            Would.res("mod_boat"),
            ModBoatEntity::new, MobCategory.MISC, 1.375F, 0.5625F, 10, 20);

    public static EntityType<ModChestBoatEntity> MOD_CHEST_BOAT = registerEntityType(
            Would.res("mod_chest_boat"),
            ModChestBoatEntity::new, MobCategory.MISC, 1.375F, 0.5625F, 10, 20);

    public static EntityType<ThrownWalnutEntity> THROWN_WALNUT = registerEntityType(
            Would.res("thrown_walnut"),
            ThrownWalnutEntity::new, MobCategory.MISC, 0.7F, 0.7F, 10, 20);

	public static <T extends Entity> EntityType<T> registerEntityType(ResourceLocation name, EntityType.EntityFactory<T> factory, MobCategory category, float width, float height, int clientTrackingRange, int updateInterval) {
		return Registry.register(BuiltInRegistries.ENTITY_TYPE, name, EntityType.Builder.of(factory, category).sized(width, height).clientTrackingRange(clientTrackingRange).updateInterval(updateInterval).build(name.toString()));
	}

}
