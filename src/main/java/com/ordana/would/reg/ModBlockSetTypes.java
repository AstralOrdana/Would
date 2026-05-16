package com.ordana.would.reg;

import com.ordana.would.Would;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public interface ModBlockSetTypes {

    BlockSetType ASPEN = register("aspen");
    BlockSetType AZALEA = register("azalea");
    BlockSetType BAOBAB = register("baobab");
    BlockSetType CEDAR = register("cedar");
    BlockSetType EBONY = register("ebony");
    BlockSetType FIR = register("fir");
    BlockSetType MAHOGANY = register("mahogany");
    BlockSetType MAPLE = register("maple");
    BlockSetType PALM = register("palm");
    BlockSetType PINE = register("pine");
    BlockSetType WALNUT = register("walnut");
    BlockSetType WILLOW = register("willow");
    BlockSetType BLUE_SPRUCE = register("blue_spruce");
    BlockSetType BALD_CYPRESS = register("bald_cypress");
    
    private static BlockSetType register(String name) {
        return BlockSetType.register(new BlockSetType(Would.res(name).toString()));
    }

    static void init() {}

}
