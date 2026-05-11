package com.ordana.would.reg;

import com.ordana.would.Would;
import com.ordana.would.entities.FallingCoconutEntity;
import com.ordana.would.entities.ThrownWalnutEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.entity.vehicle.boat.ChestBoat;
import net.minecraft.world.item.Item;

import java.util.LinkedHashMap;
import java.util.function.Supplier;

public interface ModEntities {

    static void init() {
    }

    //TODO register the boats

    EntityType<FallingCoconutEntity> FALLING_COCONUT = registerEntityType(
            Would.res("falling_coconut"),
            FallingCoconutEntity::new, MobCategory.MISC, 0.28F, 0.98F, 10, 20);

    EntityType<ThrownWalnutEntity> THROWN_WALNUT = registerEntityType(
            Would.res("thrown_walnut"),
            ThrownWalnutEntity::new, MobCategory.MISC, 0.7F, 0.7F, 10, 20);

	static <T extends Entity> EntityType<T> registerEntityType(Identifier name, EntityType.EntityFactory<T> factory, MobCategory category, float width, float height, int clientTrackingRange, int updateInterval) {
		return Registry.register(BuiltInRegistries.ENTITY_TYPE, name, EntityType.Builder.of(factory, category).sized(width, height).clientTrackingRange(clientTrackingRange).updateInterval(updateInterval).build(ResourceKey.create(Registries.ENTITY_TYPE, name)));
	}

    LinkedHashMap<String, EntityType<Boat>> BOATS = new LinkedHashMap<>();
    LinkedHashMap<String, EntityType<ChestBoat>> CHEST_BOATS = new LinkedHashMap<>();

    private static EntityType.EntityFactory<Boat> boatFactory(Supplier<Item> boatItemGetter) {
        return (entityType, level) -> new Boat(entityType, level, boatItemGetter);
    }

    private static EntityType.EntityFactory<ChestBoat> chestBoatFactory(Supplier<Item> boatItemGetter) {
        return (entityType, level) -> new ChestBoat(entityType, level, boatItemGetter);
    }

    static EntityType<Boat> registerBoat(String id, Supplier<Item> boatItemGetter) {
        EntityType<Boat> boatEntityType = register("%s_boat".formatted(id), EntityType.Builder.of(boatFactory(boatItemGetter), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
        BOATS.put(id, boatEntityType);
        return boatEntityType;
    }

    static EntityType<ChestBoat> registerChestBoat(String id, Supplier<Item> boatItemGetter) {
        EntityType<ChestBoat> boatEntityType = register("%s_chest_boat".formatted(id), EntityType.Builder.of(chestBoatFactory(boatItemGetter), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
        CHEST_BOATS.put(id, boatEntityType);
        return boatEntityType;
    }

    private static <T extends Entity> EntityType<T> register(String key, EntityType.Builder<T> builder) {
        var resourceKey = ResourceKey.create(Registries.ENTITY_TYPE, Would.res(key));
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, resourceKey, builder.build(resourceKey));
    }

}
