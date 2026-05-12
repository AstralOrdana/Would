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
        HashSet<Block> validSigns = new HashSet<>(BlockEntityType.SIGN.validBlocks);
        Map<Block, Block> strippables = new HashMap<>(AxeItem.STRIPPABLES);

        ModWoodTypes.ALL.forEach(wouldType -> {
            validHangingSigns.add(wouldType.hangingSignBlock());
            validHangingSigns.add(wouldType.wallHangingSignBlock());
            validSigns.add(wouldType.wallSignBlock());
            validSigns.add(wouldType.standingSignBlock());
        });

        strippables.put(ModBlocks.ASPEN_LOG, ModBlocks.STRIPPED_ASPEN_LOG);
        strippables.put(ModBlocks.ASPEN_LOG_GAZING, ModBlocks.STRIPPED_ASPEN_LOG_GAZING);
        strippables.put(ModBlocks.AZALEA_LOG, ModBlocks.STRIPPED_AZALEA_LOG);
        strippables.put(ModBlocks.BAOBAB_LOG, ModBlocks.STRIPPED_BAOBAB_LOG);
        strippables.put(ModBlocks.CEDAR_LOG, ModBlocks.STRIPPED_CEDAR_LOG);
        strippables.put(ModBlocks.EBONY_LOG, ModBlocks.STRIPPED_EBONY_LOG);
        strippables.put(ModBlocks.STRIPPED_EBONY_LOG, ModBlocks.EBONY_HEARTWOOD_LOG);
        strippables.put(ModBlocks.FIR_LOG, ModBlocks.STRIPPED_FIR_LOG);
        strippables.put(ModBlocks.MAHOGANY_LOG, ModBlocks.STRIPPED_MAHOGANY_LOG);
        strippables.put(ModBlocks.MAPLE_LOG, ModBlocks.STRIPPED_MAPLE_LOG);
        strippables.put(ModBlocks.PALM_LOG, ModBlocks.STRIPPED_PALM_LOG);
        strippables.put(ModBlocks.PINE_LOG, ModBlocks.STRIPPED_PINE_LOG);
        strippables.put(ModBlocks.WALNUT_LOG, ModBlocks.STRIPPED_WALNUT_LOG);
        strippables.put(ModBlocks.WILLOW_LOG, ModBlocks.STRIPPED_WILLOW_LOG);
        strippables.put(ModBlocks.BLUE_SPRUCE_LOG, ModBlocks.STRIPPED_BLUE_SPRUCE_LOG);
        strippables.put(ModBlocks.ASPEN_WOOD, ModBlocks.STRIPPED_ASPEN_WOOD);
        strippables.put(ModBlocks.ASPEN_WOOD_GAZING, ModBlocks.STRIPPED_ASPEN_WOOD_GAZING);
        strippables.put(ModBlocks.AZALEA_WOOD, ModBlocks.STRIPPED_AZALEA_WOOD);
        strippables.put(ModBlocks.BAOBAB_WOOD, ModBlocks.STRIPPED_BAOBAB_WOOD);
        strippables.put(ModBlocks.CEDAR_WOOD, ModBlocks.STRIPPED_CEDAR_WOOD);
        strippables.put(ModBlocks.EBONY_WOOD, ModBlocks.STRIPPED_EBONY_WOOD);
        strippables.put(ModBlocks.STRIPPED_EBONY_WOOD, ModBlocks.EBONY_HEARTWOOD);
        strippables.put(ModBlocks.FIR_WOOD, ModBlocks.STRIPPED_FIR_WOOD);
        strippables.put(ModBlocks.MAHOGANY_WOOD, ModBlocks.STRIPPED_MAHOGANY_WOOD);
        strippables.put(ModBlocks.MAPLE_WOOD, ModBlocks.STRIPPED_MAPLE_WOOD);
        strippables.put(ModBlocks.PALM_WOOD, ModBlocks.STRIPPED_PALM_WOOD);
        strippables.put(ModBlocks.PINE_WOOD, ModBlocks.STRIPPED_PINE_WOOD);
        strippables.put(ModBlocks.WALNUT_WOOD, ModBlocks.STRIPPED_WALNUT_WOOD);
        strippables.put(ModBlocks.WILLOW_WOOD, ModBlocks.STRIPPED_WILLOW_WOOD);
        strippables.put(ModBlocks.BLUE_SPRUCE_WOOD, ModBlocks.STRIPPED_BLUE_SPRUCE_WOOD);

        BlockEntityType.SIGN.validBlocks = validSigns;
        BlockEntityType.HANGING_SIGN.validBlocks = validHangingSigns;
        AxeItem.STRIPPABLES = strippables;
    }
}
