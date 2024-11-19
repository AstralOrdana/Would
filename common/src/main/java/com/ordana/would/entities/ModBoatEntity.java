package com.ordana.would.entities;

import com.ordana.would.reg.ModBlocks;
import com.ordana.would.reg.ModEntities;
import com.ordana.would.reg.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.function.IntFunction;

public class ModBoatEntity extends Boat {
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE = SynchedEntityData.defineId(Boat.class, EntityDataSerializers.INT);

    public ModBoatEntity(EntityType<? extends Boat> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public ModBoatEntity(Level level, double pX, double pY, double pZ) {
        this(ModEntities.MOD_BOAT.get(), level);
        this.setPos(pX, pY, pZ);
        this.xo = pX;
        this.yo = pY;
        this.zo = pZ;
    }

    @Override
    public Item getDropItem() {
        return switch (getModVariant()) {
            case ASPEN -> ModItems.ASPEN_BOAT.get();
            case AZALEA -> ModItems.AZALEA_BOAT.get();
            case BAOBAB -> ModItems.BAOBAB_BOAT.get();
            case CEDAR -> ModItems.CEDAR_BOAT.get();
            case EBONY -> ModItems.EBONY_BOAT.get();
            case FIR -> ModItems.FIR_BOAT.get();
            case MAHOGANY -> ModItems.MAHOGANY_BOAT.get();
            case MAPLE -> ModItems.MAPLE_BOAT.get();
            case PALM -> ModItems.PALM_BOAT.get();
            case PINE -> ModItems.PINE_BOAT.get();
            case WALNUT -> ModItems.WALNUT_BOAT.get();
            case WILLOW -> ModItems.WILLOW_BOAT.get();
        };
    }

    public void setVariant(Type pVariant) {
        this.entityData.set(DATA_ID_TYPE, pVariant.ordinal());
    }

    public Type getModVariant() {
        return Type.byId(this.entityData.get(DATA_ID_TYPE));
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE, Type.PINE.ordinal());
    }

    protected void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putString("Type", this.getModVariant().getSerializedName());
    }

    protected void readAdditionalSaveData(CompoundTag pCompound) {
        if (pCompound.contains("Type", 8)) {
            this.setVariant(Type.byName(pCompound.getString("Type")));
        }
    }

    public static enum Type implements StringRepresentable {
        ASPEN(ModBlocks.ASPEN_PLANKS.get(), "aspen"),
        AZALEA(ModBlocks.AZALEA_PLANKS.get(), "azalea"),
        BAOBAB(ModBlocks.BAOBAB_PLANKS.get(), "baobab"),
        CEDAR(ModBlocks.CEDAR_PLANKS.get(), "cedar"),
        EBONY(ModBlocks.EBONY_PLANKS.get(), "ebony"),
        FIR(ModBlocks.FIR_PLANKS.get(), "fir"),
        MAHOGANY(ModBlocks.MAHOGANY_PLANKS.get(), "mahogany"),
        MAPLE(ModBlocks.MAPLE_PLANKS.get(), "maple"),
        PALM(ModBlocks.PALM_PLANKS.get(), "palm"),
        PINE(ModBlocks.PINE_PLANKS.get(), "pine"),
        WALNUT(ModBlocks.WALNUT_PLANKS.get(), "walnut"),
        WILLOW(ModBlocks.WILLOW_PLANKS.get(), "willow");

        private final String name;
        private final Block planks;
        public static final StringRepresentable.EnumCodec<ModBoatEntity.Type> CODEC = StringRepresentable.fromEnum(ModBoatEntity.Type::values);
        private static final IntFunction<ModBoatEntity.Type> BY_ID = ByIdMap.continuous(Enum::ordinal, values(), ByIdMap.OutOfBoundsStrategy.ZERO);

        private Type(Block pPlanks, String pName) {
            this.name = pName;
            this.planks = pPlanks;
        }

        public String getSerializedName() {
            return this.name;
        }

        public String getName() {
            return this.name;
        }

        public Block getPlanks() {
            return this.planks;
        }

        public String toString() {
            return this.name;
        }

        /**
         * Get a boat type by its enum ordinal
         */
        public static ModBoatEntity.Type byId(int pId) {
            return BY_ID.apply(pId);
        }

        public static ModBoatEntity.Type byName(String pName) {
            return CODEC.byName(pName, PINE);
        }
    }
}