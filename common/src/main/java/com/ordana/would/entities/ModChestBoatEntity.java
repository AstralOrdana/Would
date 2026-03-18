package com.ordana.would.entities;

import com.ordana.would.reg.ModEntities;
import com.ordana.would.reg.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class ModChestBoatEntity extends ChestBoat {
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE = SynchedEntityData.defineId(ModChestBoatEntity.class, EntityDataSerializers.INT);

    public ModChestBoatEntity(EntityType<? extends ChestBoat> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public ModChestBoatEntity(Level pLevel, double pX, double pY, double pZ) {
        this(ModEntities.MOD_CHEST_BOAT, pLevel);
        this.setPos(pX, pY, pZ);
        this.xo = pX;
        this.yo = pY;
        this.zo = pZ;
    }

    @Override
    public Item getDropItem() {
        return switch (getModVariant()) {
            case ASPEN -> ModItems.ASPEN_CHEST_BOAT;
            case AZALEA -> ModItems.AZALEA_CHEST_BOAT;
            case BAOBAB -> ModItems.BAOBAB_CHEST_BOAT;
            case BLUE_SPRUCE -> ModItems.BLUE_SPRUCE_CHEST_BOAT;
            case CEDAR -> ModItems.CEDAR_CHEST_BOAT;
            case EBONY -> ModItems.EBONY_CHEST_BOAT;
            case FIR -> ModItems.FIR_CHEST_BOAT;
            case MAHOGANY -> ModItems.MAHOGANY_CHEST_BOAT;
            case MAPLE -> ModItems.MAPLE_CHEST_BOAT;
            case PALM -> ModItems.PALM_CHEST_BOAT;
            case PINE -> ModItems.PINE_CHEST_BOAT;
            case WALNUT -> ModItems.WALNUT_CHEST_BOAT;
            case WILLOW -> ModItems.WILLOW_CHEST_BOAT;
        };
    }

    public void setVariant(ModBoatEntity.Type pVariant) {
        this.entityData.set(DATA_ID_TYPE, pVariant.ordinal());
    }

    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_ID_TYPE, ModBoatEntity.Type.PINE.ordinal());
    }

    protected void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putString("Type", this.getModVariant().getSerializedName());
    }

    protected void readAdditionalSaveData(CompoundTag pCompound) {
        if (pCompound.contains("Type", 8)) {
            this.setVariant(ModBoatEntity.Type.byName(pCompound.getString("Type")));
        }
    }

    public ModBoatEntity.Type getModVariant() {
        return ModBoatEntity.Type.byId(this.entityData.get(DATA_ID_TYPE));
    }
}