package com.ordana.would.reg;

//? fabric {
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
//?}
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public interface ModWoodSetup {
    HashSet<Block> validHangingSigns = new HashSet<>();
    HashSet<Block> validShelves = new HashSet<>();
    HashSet<Block> validSigns = new HashSet<>();
    Map<Block, Block> strippables = new HashMap<>(AxeItem.STRIPPABLES);

    /// Registers blocks to block entity types and strippables.
    static void init() {

        ModWoodTypes.ALL.forEach(wouldType -> {
            validHangingSigns.add(wouldType.hangingSignBlock());
            validHangingSigns.add(wouldType.wallHangingSignBlock());
            validSigns.add(wouldType.wallSignBlock());
            validSigns.add(wouldType.standingSignBlock());
            validShelves.add(wouldType.shelfBlock());
            //? fabric {
            StrippableBlockRegistry.register(wouldType.log(), wouldType.strippedLog());
            StrippableBlockRegistry.register(wouldType.wood(), wouldType.strippedWood());
            //?} else {
            /*strippables.put(wouldType.log(), wouldType.strippedLog());
            strippables.put(wouldType.wood(), wouldType.strippedWood());
            *///?}
        });

        strippables.put(ModBlocks.ASPEN_LOG_GAZING, ModBlocks.STRIPPED_ASPEN_LOG_GAZING);
        strippables.put(ModBlocks.STRIPPED_EBONY_LOG, ModBlocks.EBONY_HEARTWOOD_LOG);

        //? fabric {
        validSigns.forEach(BlockEntityType.SIGN::addValidBlock);
        validHangingSigns.forEach(BlockEntityType.HANGING_SIGN::addValidBlock);
        validShelves.forEach(BlockEntityType.SHELF::addValidBlock);
        //?}
        // FIXME - neo deprecates this, but i need to figure out data generation for it still
        //? neoforge {
        /*AxeItem.STRIPPABLES = strippables;
        *///?}
    }
}
