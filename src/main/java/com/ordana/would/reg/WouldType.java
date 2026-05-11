package com.ordana.would.reg;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.WoodType;

public record WouldType(WoodType woodType, Block planks, Block fenceGate, Block stairs, Block gate, Block block) {
    public String name() {
        return woodType.name();
    }
}
