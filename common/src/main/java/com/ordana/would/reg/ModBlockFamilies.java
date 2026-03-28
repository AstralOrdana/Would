package com.ordana.would.reg;

import com.google.common.collect.Maps;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Block;

import java.util.Map;
import java.util.function.Supplier;

import static com.ordana.would.reg.ModBlocks.*;

public interface ModBlockFamilies {

    Map<Block, BlockFamily> MAP = Maps.newHashMap();

    BlockFamily WILLOW_PLANKS = familyBuilder(ModBlocks.WILLOW_PLANKS)
        .button(WILLOW_BUTTON.get())
        .slab(WILLOW_SLAB.get())
        .stairs(WILLOW_STAIRS.get())
        .fence(WILLOW_FENCE.get())
        .fenceGate(WILLOW_FENCE_GATE.get())
        .pressurePlate(WILLOW_PRESSURE_PLATE.get())
        .sign(WILLOW_SIGN.get(), WILLOW_WALL_SIGN.get())
        .door(WILLOW_DOOR.get())
        .trapdoor(WILLOW_TRAPDOOR.get())
        .recipeGroupPrefix("wooden")
        .recipeUnlockedBy("has_planks")
        .getFamily();

    BlockFamily BAOBAB_PLANKS = familyBuilder(ModBlocks.BAOBAB_PLANKS)
        .button(BAOBAB_BUTTON.get())
        .slab(BAOBAB_SLAB.get())
        .stairs(BAOBAB_STAIRS.get())
        .fence(BAOBAB_FENCE.get())
        .fenceGate(BAOBAB_FENCE_GATE.get())
        .pressurePlate(BAOBAB_PRESSURE_PLATE.get())
        .sign(BAOBAB_SIGN.get(), BAOBAB_WALL_SIGN.get())
        .door(BAOBAB_DOOR.get())
        .trapdoor(BAOBAB_TRAPDOOR.get())
        .recipeGroupPrefix("wooden")
        .recipeUnlockedBy("has_planks")
        .getFamily();

    BlockFamily EBONY_PLANKS = familyBuilder(ModBlocks.EBONY_PLANKS)
        .button(EBONY_BUTTON.get())
        .slab(EBONY_SLAB.get())
        .stairs(EBONY_STAIRS.get())
        .fence(EBONY_FENCE.get())
        .fenceGate(EBONY_FENCE_GATE.get())
        .pressurePlate(EBONY_PRESSURE_PLATE.get())
        .sign(EBONY_SIGN.get(), EBONY_WALL_SIGN.get())
        .door(EBONY_DOOR.get())
        .trapdoor(EBONY_TRAPDOOR.get())
        .recipeGroupPrefix("wooden")
        .recipeUnlockedBy("has_planks")
        .getFamily();

    BlockFamily FIR_PLANKS = familyBuilder(ModBlocks.FIR_PLANKS)
        .button(FIR_BUTTON.get())
        .slab(FIR_SLAB.get())
        .stairs(FIR_STAIRS.get())
        .fence(FIR_FENCE.get())
        .fenceGate(FIR_FENCE_GATE.get())
        .pressurePlate(FIR_PRESSURE_PLATE.get())
        .sign(FIR_SIGN.get(), FIR_WALL_SIGN.get())
        .door(FIR_DOOR.get())
        .trapdoor(FIR_TRAPDOOR.get())
        .recipeGroupPrefix("wooden")
        .recipeUnlockedBy("has_planks")
        .getFamily();

    BlockFamily PINE_PLANKS = familyBuilder(ModBlocks.PINE_PLANKS)
        .button(PINE_BUTTON.get())
        .slab(PINE_SLAB.get())
        .stairs(PINE_STAIRS.get())
        .fence(PINE_FENCE.get())
        .fenceGate(PINE_FENCE_GATE.get())
        .pressurePlate(PINE_PRESSURE_PLATE.get())
        .sign(PINE_SIGN.get(), PINE_WALL_SIGN.get())
        .door(PINE_DOOR.get())
        .trapdoor(PINE_TRAPDOOR.get())
        .recipeGroupPrefix("wooden")
        .recipeUnlockedBy("has_planks")
        .getFamily();

    BlockFamily CEDAR_PLANKS = familyBuilder(ModBlocks.CEDAR_PLANKS)
        .button(CEDAR_BUTTON.get())
        .slab(CEDAR_SLAB.get())
        .stairs(CEDAR_STAIRS.get())
        .fence(CEDAR_FENCE.get())
        .fenceGate(CEDAR_FENCE_GATE.get())
        .pressurePlate(CEDAR_PRESSURE_PLATE.get())
        .sign(CEDAR_SIGN.get(), CEDAR_WALL_SIGN.get())
        .door(CEDAR_DOOR.get())
        .trapdoor(CEDAR_TRAPDOOR.get())
        .recipeGroupPrefix("wooden")
        .recipeUnlockedBy("has_planks")
        .getFamily();

    BlockFamily MAHOGANY_PLANKS = familyBuilder(ModBlocks.MAHOGANY_PLANKS)
        .button(MAHOGANY_BUTTON.get())
        .slab(MAHOGANY_SLAB.get())
        .stairs(MAHOGANY_STAIRS.get())
        .fence(MAHOGANY_FENCE.get())
        .fenceGate(MAHOGANY_FENCE_GATE.get())
        .pressurePlate(MAHOGANY_PRESSURE_PLATE.get())
        .sign(MAHOGANY_SIGN.get(), MAHOGANY_WALL_SIGN.get())
        .door(MAHOGANY_DOOR.get())
        .trapdoor(MAHOGANY_TRAPDOOR.get())
        .recipeGroupPrefix("wooden")
        .recipeUnlockedBy("has_planks")
        .getFamily();

    BlockFamily AZALEA_PLANKS = familyBuilder(ModBlocks.AZALEA_PLANKS)
        .button(AZALEA_BUTTON.get())
        .slab(AZALEA_SLAB.get())
        .stairs(AZALEA_STAIRS.get())
        .fence(AZALEA_FENCE.get())
        .fenceGate(AZALEA_FENCE_GATE.get())
        .pressurePlate(AZALEA_PRESSURE_PLATE.get())
        .sign(AZALEA_SIGN.get(), AZALEA_WALL_SIGN.get())
        .door(AZALEA_DOOR.get())
        .trapdoor(AZALEA_TRAPDOOR.get())
        .recipeGroupPrefix("wooden")
        .recipeUnlockedBy("has_planks")
        .getFamily();

    BlockFamily PALM_PLANKS = familyBuilder(ModBlocks.PALM_PLANKS)
        .button(PALM_BUTTON.get())
        .slab(PALM_SLAB.get())
        .stairs(PALM_STAIRS.get())
        .fence(PALM_FENCE.get())
        .fenceGate(PALM_FENCE_GATE.get())
        .pressurePlate(PALM_PRESSURE_PLATE.get())
        .sign(PALM_SIGN.get(), PALM_WALL_SIGN.get())
        .door(PALM_DOOR.get())
        .trapdoor(PALM_TRAPDOOR.get())
        .recipeGroupPrefix("wooden")
        .recipeUnlockedBy("has_planks")
        .getFamily();

    BlockFamily MAPLE_PLANKS = familyBuilder(ModBlocks.MAPLE_PLANKS)
        .button(MAPLE_BUTTON.get())
        .slab(MAPLE_SLAB.get())
        .stairs(MAPLE_STAIRS.get())
        .fence(MAPLE_FENCE.get())
        .fenceGate(MAPLE_FENCE_GATE.get())
        .pressurePlate(MAPLE_PRESSURE_PLATE.get())
        .sign(MAPLE_SIGN.get(), MAPLE_WALL_SIGN.get())
        .door(MAPLE_DOOR.get())
        .trapdoor(MAPLE_TRAPDOOR.get())
        .recipeGroupPrefix("wooden")
        .recipeUnlockedBy("has_planks")
        .getFamily();

    BlockFamily ASPEN_PLANKS = familyBuilder(ModBlocks.ASPEN_PLANKS)
        .button(ASPEN_BUTTON.get())
        .slab(ASPEN_SLAB.get())
        .stairs(ASPEN_STAIRS.get())
        .fence(ASPEN_FENCE.get())
        .fenceGate(ASPEN_FENCE_GATE.get())
        .pressurePlate(ASPEN_PRESSURE_PLATE.get())
        .sign(ASPEN_SIGN.get(), ASPEN_WALL_SIGN.get())
        .door(ASPEN_DOOR.get())
        .trapdoor(ASPEN_TRAPDOOR.get())
        .recipeGroupPrefix("wooden")
        .recipeUnlockedBy("has_planks")
        .getFamily();

    BlockFamily WALNUT_PLANKS = familyBuilder(ModBlocks.WALNUT_PLANKS)
        .button(WALNUT_BUTTON.get())
        .slab(WALNUT_SLAB.get())
        .stairs(WALNUT_STAIRS.get())
        .fence(WALNUT_FENCE.get())
        .fenceGate(WALNUT_FENCE_GATE.get())
        .pressurePlate(WALNUT_PRESSURE_PLATE.get())
        .sign(WALNUT_SIGN.get(), WALNUT_WALL_SIGN.get())
        .door(WALNUT_DOOR.get())
        .trapdoor(WALNUT_TRAPDOOR.get())
        .recipeGroupPrefix("wooden")
        .recipeUnlockedBy("has_planks")
        .getFamily();

    BlockFamily BLUE_SPRUCE_PLANKS = familyBuilder(ModBlocks.BLUE_SPRUCE_PLANKS)
        .button(BLUE_SPRUCE_BUTTON.get())
        .slab(BLUE_SPRUCE_SLAB.get())
        .stairs(BLUE_SPRUCE_STAIRS.get())
        .fence(BLUE_SPRUCE_FENCE.get())
        .fenceGate(BLUE_SPRUCE_FENCE_GATE.get())
        .pressurePlate(BLUE_SPRUCE_PRESSURE_PLATE.get())
        .sign(BLUE_SPRUCE_SIGN.get(), BLUE_SPRUCE_WALL_SIGN.get())
        .door(BLUE_SPRUCE_DOOR.get())
        .trapdoor(BLUE_SPRUCE_TRAPDOOR.get())
        .recipeGroupPrefix("wooden")
        .recipeUnlockedBy("has_planks")
        .getFamily();

    private static BlockFamily.Builder familyBuilder(Supplier<Block> baseBlock) {
        Block block = baseBlock.get();

        BlockFamily.Builder builder = new BlockFamily.Builder(block);
        MAP.put(block, builder.getFamily());

        return builder;
    }

    static void init() {}

}
