package com.ordana.would.reg;

import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.jspecify.annotations.Nullable;

public record WouldType(WoodType woodType,
                        Block planks, SlabBlock slabBlock, StairBlock stairs, FenceBlock fenceBlock, FenceGateBlock fenceGateBlock,
                        DoorBlock door, TrapDoorBlock trapDoorBlock,
                        StandingSignBlock standingSignBlock, WallSignBlock wallSignBlock,
                        PressurePlateBlock pressurePlate, com.ordana.would.blocks.ModWoodenButtonBlock button,
                        CeilingHangingSignBlock hangingSignBlock, WallHangingSignBlock wallHangingSignBlock,
                        BoatItem boatItem, BoatItem chestBoatItem,
                        SignItem signItem, HangingSignItem hangingSignItem,
                        Block log, Block strippedLog, @Nullable Block sapling, @Nullable Block leaves,
                        net.minecraft.tags.TagKey<Item> logItemTagKey, Block wood,
                        net.minecraft.data.BlockFamily family, ShelfBlock shelfBlock, Block strippedWood) {
    public String name() {
        return woodType.name();
    }
}
