package com.ordana.would.reg;

//? fabric {
/*import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
*///?}
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public interface ModWoodSetup {
    HashSet<Block> validHangingSigns = new HashSet<>();
    HashSet<Block> validShelves = new HashSet<>();
    HashSet<Block> validSigns = new HashSet<>();
    Map<Identifier, Identifier> strippables = new HashMap<>();

    /// Registers blocks to block entity types and strippables.
    static void init() {

        ModWoodTypes.ALL.forEach(wouldType -> {
            validHangingSigns.add(wouldType.hangingSignBlock());
            validHangingSigns.add(wouldType.wallHangingSignBlock());
            validSigns.add(wouldType.wallSignBlock());
            validSigns.add(wouldType.standingSignBlock());
            validShelves.add(wouldType.shelfBlock());
            put(wouldType.log(), wouldType.strippedLog());
            put(wouldType.wood(), wouldType.strippedWood());
        });

        put(ModBlocks.ASPEN_LOG_GAZING, ModBlocks.STRIPPED_ASPEN_LOG_GAZING);
        put(ModBlocks.STRIPPED_EBONY_LOG, ModBlocks.EBONY_HEARTWOOD_LOG);

        //? fabric {
        /*validSigns.forEach(BlockEntityType.SIGN::addValidBlock);
        validHangingSigns.forEach(BlockEntityType.HANGING_SIGN::addValidBlock);
        validShelves.forEach(BlockEntityType.SHELF::addValidBlock);
        *///?}
    }

    static void put(Block input, Block stripped) {
        //? fabric
        //StrippableBlockRegistry.register(input, stripped);
        strippables.put(BuiltInRegistries.BLOCK.getKey(input), BuiltInRegistries.BLOCK.getKey(stripped));
    }
}
