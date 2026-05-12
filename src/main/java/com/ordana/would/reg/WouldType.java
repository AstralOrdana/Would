package com.ordana.would.reg;

import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.WoodType;

public record WouldType(WoodType woodType,
                        Block planks, SlabBlock slabBlock, StairBlock stairs, FenceBlock fenceBlock, FenceGateBlock fenceGateBlock,
                        DoorBlock door, TrapDoorBlock trapDoorBlock,
                        StandingSignBlock standingSignBlock, WallSignBlock wallSignBlock,
                        PressurePlateBlock pressurePlate, com.ordana.would.blocks.ModWoodenButtonBlock button,
                        CeilingHangingSignBlock hangingSignBlock, WallHangingSignBlock wallHangingSignBlock,
                        BoatItem boatItem, BoatItem chestBoatItem,
                        SignItem signItem, HangingSignItem hangingSignItem
) {
    public String name() {
        return woodType.name();
    }
}
