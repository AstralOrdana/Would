package com.ordana.would.reg;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public interface ModWoodSetup {
    
    static void init() {
        HashSet<Block> validHangingSigns = new HashSet<>(BlockEntityType.HANGING_SIGN.validBlocks);
        validHangingSigns.add(ModBlocks.ASPEN_HANGING_SIGN.get());
        validHangingSigns.add(ModBlocks.AZALEA_HANGING_SIGN.get());
        validHangingSigns.add(ModBlocks.BAOBAB_HANGING_SIGN.get());
        validHangingSigns.add(ModBlocks.CEDAR_HANGING_SIGN.get());
        validHangingSigns.add(ModBlocks.EBONY_HANGING_SIGN.get());
        validHangingSigns.add(ModBlocks.FIR_HANGING_SIGN.get());
        validHangingSigns.add(ModBlocks.MAHOGANY_HANGING_SIGN.get());
        validHangingSigns.add(ModBlocks.MAPLE_HANGING_SIGN.get());
        validHangingSigns.add(ModBlocks.PALM_HANGING_SIGN.get());
        validHangingSigns.add(ModBlocks.PINE_HANGING_SIGN.get());
        validHangingSigns.add(ModBlocks.WALNUT_HANGING_SIGN.get());
        validHangingSigns.add(ModBlocks.WILLOW_HANGING_SIGN.get());
        validHangingSigns.add(ModBlocks.BLUE_SPRUCE_HANGING_SIGN.get());
        validHangingSigns.add(ModBlocks.ASPEN_WALL_HANGING_SIGN.get());
        validHangingSigns.add(ModBlocks.AZALEA_WALL_HANGING_SIGN.get());
        validHangingSigns.add(ModBlocks.BAOBAB_WALL_HANGING_SIGN.get());
        validHangingSigns.add(ModBlocks.CEDAR_WALL_HANGING_SIGN.get());
        validHangingSigns.add(ModBlocks.EBONY_WALL_HANGING_SIGN.get());
        validHangingSigns.add(ModBlocks.FIR_WALL_HANGING_SIGN.get());
        validHangingSigns.add(ModBlocks.MAHOGANY_WALL_HANGING_SIGN.get());
        validHangingSigns.add(ModBlocks.MAPLE_WALL_HANGING_SIGN.get());
        validHangingSigns.add(ModBlocks.PALM_WALL_HANGING_SIGN.get());
        validHangingSigns.add(ModBlocks.PINE_WALL_HANGING_SIGN.get());
        validHangingSigns.add(ModBlocks.WALNUT_WALL_HANGING_SIGN.get());
        validHangingSigns.add(ModBlocks.WILLOW_WALL_HANGING_SIGN.get());
        validHangingSigns.add(ModBlocks.BLUE_SPRUCE_WALL_HANGING_SIGN.get());
        BlockEntityType.HANGING_SIGN.validBlocks = validHangingSigns;

        HashSet<Block> validSigns = new HashSet<>(BlockEntityType.SIGN.validBlocks);
        validSigns.add(ModBlocks.ASPEN_SIGN.get());
        validSigns.add(ModBlocks.AZALEA_SIGN.get());
        validSigns.add(ModBlocks.BAOBAB_SIGN.get());
        validSigns.add(ModBlocks.CEDAR_SIGN.get());
        validSigns.add(ModBlocks.EBONY_SIGN.get());
        validSigns.add(ModBlocks.FIR_SIGN.get());
        validSigns.add(ModBlocks.MAHOGANY_SIGN.get());
        validSigns.add(ModBlocks.MAPLE_SIGN.get());
        validSigns.add(ModBlocks.PALM_SIGN.get());
        validSigns.add(ModBlocks.PINE_SIGN.get());
        validSigns.add(ModBlocks.WALNUT_SIGN.get());
        validSigns.add(ModBlocks.WILLOW_SIGN.get());
        validSigns.add(ModBlocks.BLUE_SPRUCE_SIGN.get());
        validSigns.add(ModBlocks.ASPEN_WALL_SIGN.get());
        validSigns.add(ModBlocks.AZALEA_WALL_SIGN.get());
        validSigns.add(ModBlocks.BAOBAB_WALL_SIGN.get());
        validSigns.add(ModBlocks.CEDAR_WALL_SIGN.get());
        validSigns.add(ModBlocks.EBONY_WALL_SIGN.get());
        validSigns.add(ModBlocks.FIR_WALL_SIGN.get());
        validSigns.add(ModBlocks.MAHOGANY_WALL_SIGN.get());
        validSigns.add(ModBlocks.MAPLE_WALL_SIGN.get());
        validSigns.add(ModBlocks.PALM_WALL_SIGN.get());
        validSigns.add(ModBlocks.PINE_WALL_SIGN.get());
        validSigns.add(ModBlocks.WALNUT_WALL_SIGN.get());
        validSigns.add(ModBlocks.WILLOW_WALL_SIGN.get());
        validSigns.add(ModBlocks.BLUE_SPRUCE_WALL_SIGN.get());
        BlockEntityType.SIGN.validBlocks = validSigns;

        Map<Block, Block> strippables = new HashMap<>(AxeItem.STRIPPABLES);

        strippables.put(ModBlocks.ASPEN_LOG.get(), ModBlocks.STRIPPED_ASPEN_LOG.get());
        strippables.put(ModBlocks.ASPEN_LOG_GAZING.get(), ModBlocks.STRIPPED_ASPEN_LOG_GAZING.get());
        strippables.put(ModBlocks.AZALEA_LOG.get(), ModBlocks.STRIPPED_AZALEA_LOG.get());
        strippables.put(ModBlocks.BAOBAB_LOG.get(), ModBlocks.STRIPPED_BAOBAB_LOG.get());
        strippables.put(ModBlocks.CEDAR_LOG.get(), ModBlocks.STRIPPED_CEDAR_LOG.get());
        strippables.put(ModBlocks.EBONY_LOG.get(), ModBlocks.STRIPPED_EBONY_LOG.get());
        strippables.put(ModBlocks.STRIPPED_EBONY_LOG.get(), ModBlocks.EBONY_HEARTWOOD_LOG.get());
        strippables.put(ModBlocks.FIR_LOG.get(), ModBlocks.STRIPPED_FIR_LOG.get());
        strippables.put(ModBlocks.MAHOGANY_LOG.get(), ModBlocks.STRIPPED_MAHOGANY_LOG.get());
        strippables.put(ModBlocks.MAPLE_LOG.get(), ModBlocks.STRIPPED_MAPLE_LOG.get());
        strippables.put(ModBlocks.PALM_LOG.get(), ModBlocks.STRIPPED_PALM_LOG.get());
        strippables.put(ModBlocks.PINE_LOG.get(), ModBlocks.STRIPPED_PINE_LOG.get());
        strippables.put(ModBlocks.WALNUT_LOG.get(), ModBlocks.STRIPPED_WALNUT_LOG.get());
        strippables.put(ModBlocks.WILLOW_LOG.get(), ModBlocks.STRIPPED_WILLOW_LOG.get());
        strippables.put(ModBlocks.BLUE_SPRUCE_LOG.get(), ModBlocks.STRIPPED_BLUE_SPRUCE_LOG.get());
        strippables.put(ModBlocks.ASPEN_WOOD.get(), ModBlocks.STRIPPED_ASPEN_WOOD.get());
        strippables.put(ModBlocks.ASPEN_WOOD_GAZING.get(), ModBlocks.STRIPPED_ASPEN_WOOD_GAZING.get());
        strippables.put(ModBlocks.AZALEA_WOOD.get(), ModBlocks.STRIPPED_AZALEA_WOOD.get());
        strippables.put(ModBlocks.BAOBAB_WOOD.get(), ModBlocks.STRIPPED_BAOBAB_WOOD.get());
        strippables.put(ModBlocks.CEDAR_WOOD.get(), ModBlocks.STRIPPED_CEDAR_WOOD.get());
        strippables.put(ModBlocks.EBONY_WOOD.get(), ModBlocks.STRIPPED_EBONY_WOOD.get());
        strippables.put(ModBlocks.STRIPPED_EBONY_WOOD.get(), ModBlocks.EBONY_HEARTWOOD.get());
        strippables.put(ModBlocks.FIR_WOOD.get(), ModBlocks.STRIPPED_FIR_WOOD.get());
        strippables.put(ModBlocks.MAHOGANY_WOOD.get(), ModBlocks.STRIPPED_MAHOGANY_WOOD.get());
        strippables.put(ModBlocks.MAPLE_WOOD.get(), ModBlocks.STRIPPED_MAPLE_WOOD.get());
        strippables.put(ModBlocks.PALM_WOOD.get(), ModBlocks.STRIPPED_PALM_WOOD.get());
        strippables.put(ModBlocks.PINE_WOOD.get(), ModBlocks.STRIPPED_PINE_WOOD.get());
        strippables.put(ModBlocks.WALNUT_WOOD.get(), ModBlocks.STRIPPED_WALNUT_WOOD.get());
        strippables.put(ModBlocks.WILLOW_WOOD.get(), ModBlocks.STRIPPED_WILLOW_WOOD.get());
        strippables.put(ModBlocks.BLUE_SPRUCE_WOOD.get(), ModBlocks.STRIPPED_BLUE_SPRUCE_WOOD.get());

        AxeItem.STRIPPABLES = strippables;
        ModBlockFamilies.init();
    }
}
