package com.ordana.would.reg;

import com.ordana.would.Would;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public interface ModTags {

    interface Items {

        TagKey<Item> ASPEN_LOGS = item("aspen_logs");
        TagKey<Item> AZALEA_LOGS = item("azalea_logs");
        TagKey<Item> BAOBAB_LOGS = item("baobab_logs");
        TagKey<Item> BLUE_SPRUCE_LOGS = item("blue_spruce_logs");
        TagKey<Item> CEDAR_LOGS = item("cedar_logs");
        TagKey<Item> EBONY_LOGS = item("ebony_logs");
        TagKey<Item> FIR_LOGS = item("fir_logs");
        TagKey<Item> MAHOGANY_LOGS = item("mahogany_logs");
        TagKey<Item> MAPLE_LOGS = item("maple_logs");
        TagKey<Item> PALM_LOGS = item("palm_logs");
        TagKey<Item> PINE_LOGS = item("pine_logs");
        TagKey<Item> WALNUT_LOGS = item("walnut_logs");
        TagKey<Item> WILLOW_LOGS = item("willow_logs");

    }

    interface Blocks {

        TagKey<Block> ASPEN_LOGS = block("aspen_logs");
        TagKey<Block> AZALEA_LOGS = block("azalea_logs");
        TagKey<Block> BAOBAB_LOGS = block("baobab_logs");
        TagKey<Block> BLUE_SPRUCE_LOGS = block("blue_spruce_logs");
        TagKey<Block> CEDAR_LOGS = block("cedar_logs");
        TagKey<Block> EBONY_LOGS = block("ebony_logs");
        TagKey<Block> FIR_LOGS = block("fir_logs");
        TagKey<Block> MAHOGANY_LOGS = block("mahogany_logs");
        TagKey<Block> MAPLE_LOGS = block("maple_logs");
        TagKey<Block> PALM_LOGS = block("palm_logs");
        TagKey<Block> PINE_LOGS = block("pine_logs");
        TagKey<Block> WALNUT_LOGS = block("walnut_logs");
        TagKey<Block> WILLOW_LOGS = block("willow_logs");

    }

    private static TagKey<Item> item(String name) {
        return TagKey.create(Registries.ITEM, Would.res(name));
    }

    private static TagKey<Block> block(String name) {
        return TagKey.create(Registries.BLOCK, Would.res(name));
    }

    static void init() {}

}
