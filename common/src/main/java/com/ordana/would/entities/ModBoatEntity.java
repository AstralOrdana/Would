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
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE = SynchedEntityData.defineId(ModBoatEntity.class, EntityDataSerializers.INT);

    public ModBoatEntity(EntityType<? extends Boat> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public ModBoatEntity(Level level, double pX, double pY, double pZ) {
        this(ModEntities.MOD_BOAT, level);
        this.setPos(pX, pY, pZ);
        this.xo = pX;
        this.yo = pY;
        this.zo = pZ;
    }

    @Override
    public Item getDropItem() {
        return switch (getModVariant()) {
            case ASPEN -> ModItems.ASPEN_BOAT;
            case AZALEA -> ModItems.AZALEA_BOAT;
            case BAOBAB -> ModItems.BAOBAB_BOAT;
            case BLUE_SPRUCE -> ModItems.BLUE_SPRUCE_BOAT;
            case CEDAR -> ModItems.CEDAR_BOAT;
            case EBONY -> ModItems.EBONY_BOAT;
            case FIR -> ModItems.FIR_BOAT;
            case MAHOGANY -> ModItems.MAHOGANY_BOAT;
            case MAPLE -> ModItems.MAPLE_BOAT;
            case PALM -> ModItems.PALM_BOAT;
            case PINE -> ModItems.PINE_BOAT;
            case WALNUT -> ModItems.WALNUT_BOAT;
            case WILLOW -> ModItems.WILLOW_BOAT;
        };
    }

    public void setVariant(Type pVariant) {
        this.entityData.set(DATA_ID_TYPE, pVariant.ordinal());
    }

    public Type getModVariant() {
        return Type.byId(this.entityData.get(DATA_ID_TYPE));
    }

    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_ID_TYPE, Type.PINE.ordinal());
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
        ASPEN(ModBlocks.ASPEN_PLANKS, "aspen"),
        AZALEA(ModBlocks.AZALEA_PLANKS, "azalea"),
        BAOBAB(ModBlocks.BAOBAB_PLANKS, "baobab"),
        BLUE_SPRUCE(ModBlocks.BLUE_SPRUCE_PLANKS, "blue_spruce"),
        CEDAR(ModBlocks.CEDAR_PLANKS, "cedar"),
        EBONY(ModBlocks.EBONY_PLANKS, "ebony"),
        FIR(ModBlocks.FIR_PLANKS, "fir"),
        MAHOGANY(ModBlocks.MAHOGANY_PLANKS, "mahogany"),
        MAPLE(ModBlocks.MAPLE_PLANKS, "maple"),
        PALM(ModBlocks.PALM_PLANKS, "palm"),
        PINE(ModBlocks.PINE_PLANKS, "pine"),
        WALNUT(ModBlocks.WALNUT_PLANKS, "walnut"),
        WILLOW(ModBlocks.WILLOW_PLANKS, "willow");

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