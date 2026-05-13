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
        HashSet<Block> validShelves = new HashSet<>(BlockEntityType.SHELF.validBlocks);
        HashSet<Block> validSigns = new HashSet<>(BlockEntityType.SIGN.validBlocks);
        Map<Block, Block> strippables = new HashMap<>(AxeItem.STRIPPABLES);

        ModWoodTypes.ALL.forEach(wouldType -> {
            validHangingSigns.add(wouldType.hangingSignBlock());
            validHangingSigns.add(wouldType.wallHangingSignBlock());
            validSigns.add(wouldType.wallSignBlock());
            validSigns.add(wouldType.standingSignBlock());
            validShelves.add(wouldType.shelfBlock());
            strippables.put(wouldType.log(), wouldType.strippedLog());
            strippables.put(wouldType.wood(), wouldType.strippedWood());
        });

        strippables.put(ModBlocks.ASPEN_LOG_GAZING, ModBlocks.STRIPPED_ASPEN_LOG_GAZING);
        strippables.put(ModBlocks.STRIPPED_EBONY_LOG, ModBlocks.EBONY_HEARTWOOD_LOG);

        BlockEntityType.SIGN.validBlocks = validSigns;
        BlockEntityType.HANGING_SIGN.validBlocks = validHangingSigns;
        BlockEntityType.SHELF.validBlocks = validShelves;
        AxeItem.STRIPPABLES = strippables;
    }
}
