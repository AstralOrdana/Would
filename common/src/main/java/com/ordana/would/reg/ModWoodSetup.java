package com.ordana.would.reg;

import com.google.common.collect.ImmutableMap;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

import java.util.HashSet;

public class ModWoodSetup {

    public static final WoodType ASPEN = new WoodType("aspen", BlockSetType.ACACIA);
    public static final WoodType AZALEA = new WoodType("azalea", BlockSetType.ACACIA);
    public static final WoodType BAOBAB = new WoodType("baobab", BlockSetType.ACACIA);
    public static final WoodType CEDAR = new WoodType("cedar", BlockSetType.ACACIA);
    public static final WoodType EBONY = new WoodType("ebony", BlockSetType.ACACIA);
    public static final WoodType FIR = new WoodType("fir", BlockSetType.ACACIA);
    public static final WoodType MAHOGANY = new WoodType("mahogany", BlockSetType.ACACIA);
    public static final WoodType MAPLE = new WoodType("maple", BlockSetType.ACACIA);
    public static final WoodType PALM = new WoodType("palm", BlockSetType.ACACIA);
    public static final WoodType PINE = new WoodType("pine", BlockSetType.ACACIA);
    public static final WoodType WALNUT = new WoodType("walnut", BlockSetType.ACACIA);
    public static final WoodType WILLOW = new WoodType("willow", BlockSetType.ACACIA);


    public static void init() {

        WoodType.VALUES.add(ASPEN);
        WoodType.VALUES.add(AZALEA);
        WoodType.VALUES.add(BAOBAB);
        WoodType.VALUES.add(CEDAR);
        WoodType.VALUES.add(EBONY);
        WoodType.VALUES.add(FIR);
        WoodType.VALUES.add(MAHOGANY);
        WoodType.VALUES.add(MAPLE);
        WoodType.VALUES.add(PALM);
        WoodType.VALUES.add(PINE);
        WoodType.VALUES.add(WALNUT);
        WoodType.VALUES.add(WILLOW);

        var validHangingSigns = new HashSet(BlockEntityType.HANGING_SIGN.validBlocks);
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
        BlockEntityType.HANGING_SIGN.validBlocks = validHangingSigns;

        var validSigns = new HashSet(BlockEntityType.SIGN.validBlocks);
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
        BlockEntityType.SIGN.validBlocks = validSigns;

        var strippables = new ImmutableMap.Builder().putAll(AxeItem.STRIPPABLES);
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
        AxeItem.STRIPPABLES = strippables.build();
    }
}
