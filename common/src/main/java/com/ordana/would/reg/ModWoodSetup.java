package com.ordana.would.reg;

import com.google.common.collect.ImmutableMap;
import com.ordana.would.Would;
import com.ordana.would.WouldPlatform;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
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
    public static final WoodType BLUE_SPRUCE = new WoodType("blue_spruce", BlockSetType.ACACIA);

    private static void register(WoodType woodType) {
        WoodType.register(woodType);
        if (WouldPlatform.isClient()) {
            Sheets.SIGN_MATERIALS.put(woodType, new Material(Sheets.SIGN_SHEET, Would.res("entity/signs/" + woodType.name())));
            Sheets.HANGING_SIGN_MATERIALS.put(woodType, new Material(Sheets.SIGN_SHEET, Would.res("entity/signs/hanging/" + woodType.name())));
        }
   }
    
    public static void init() {

        register(ASPEN);
        register(AZALEA);
        register(BAOBAB);
        register(CEDAR);
        register(EBONY);
        register(FIR);
        register(MAHOGANY);
        register(MAPLE);
        register(PALM);
        register(PINE);
        register(WALNUT);
        register(WILLOW);
        register(BLUE_SPRUCE);

        var validHangingSigns = new HashSet(BlockEntityType.HANGING_SIGN.validBlocks);
        validHangingSigns.add(ModBlocks.ASPEN_HANGING_SIGN);
        validHangingSigns.add(ModBlocks.AZALEA_HANGING_SIGN);
        validHangingSigns.add(ModBlocks.BAOBAB_HANGING_SIGN);
        validHangingSigns.add(ModBlocks.CEDAR_HANGING_SIGN);
        validHangingSigns.add(ModBlocks.EBONY_HANGING_SIGN);
        validHangingSigns.add(ModBlocks.FIR_HANGING_SIGN);
        validHangingSigns.add(ModBlocks.MAHOGANY_HANGING_SIGN);
        validHangingSigns.add(ModBlocks.MAPLE_HANGING_SIGN);
        validHangingSigns.add(ModBlocks.PALM_HANGING_SIGN);
        validHangingSigns.add(ModBlocks.PINE_HANGING_SIGN);
        validHangingSigns.add(ModBlocks.WALNUT_HANGING_SIGN);
        validHangingSigns.add(ModBlocks.WILLOW_HANGING_SIGN);
        validHangingSigns.add(ModBlocks.BLUE_SPRUCE_HANGING_SIGN);
        validHangingSigns.add(ModBlocks.ASPEN_WALL_HANGING_SIGN);
        validHangingSigns.add(ModBlocks.AZALEA_WALL_HANGING_SIGN);
        validHangingSigns.add(ModBlocks.BAOBAB_WALL_HANGING_SIGN);
        validHangingSigns.add(ModBlocks.CEDAR_WALL_HANGING_SIGN);
        validHangingSigns.add(ModBlocks.EBONY_WALL_HANGING_SIGN);
        validHangingSigns.add(ModBlocks.FIR_WALL_HANGING_SIGN);
        validHangingSigns.add(ModBlocks.MAHOGANY_WALL_HANGING_SIGN);
        validHangingSigns.add(ModBlocks.MAPLE_WALL_HANGING_SIGN);
        validHangingSigns.add(ModBlocks.PALM_WALL_HANGING_SIGN);
        validHangingSigns.add(ModBlocks.PINE_WALL_HANGING_SIGN);
        validHangingSigns.add(ModBlocks.WALNUT_WALL_HANGING_SIGN);
        validHangingSigns.add(ModBlocks.WILLOW_WALL_HANGING_SIGN);
        validHangingSigns.add(ModBlocks.BLUE_SPRUCE_WALL_HANGING_SIGN);
        BlockEntityType.HANGING_SIGN.validBlocks = validHangingSigns;

        var validSigns = new HashSet(BlockEntityType.SIGN.validBlocks);
        validSigns.add(ModBlocks.ASPEN_SIGN);
        validSigns.add(ModBlocks.AZALEA_SIGN);
        validSigns.add(ModBlocks.BAOBAB_SIGN);
        validSigns.add(ModBlocks.CEDAR_SIGN);
        validSigns.add(ModBlocks.EBONY_SIGN);
        validSigns.add(ModBlocks.FIR_SIGN);
        validSigns.add(ModBlocks.MAHOGANY_SIGN);
        validSigns.add(ModBlocks.MAPLE_SIGN);
        validSigns.add(ModBlocks.PALM_SIGN);
        validSigns.add(ModBlocks.PINE_SIGN);
        validSigns.add(ModBlocks.WALNUT_SIGN);
        validSigns.add(ModBlocks.WILLOW_SIGN);
        validSigns.add(ModBlocks.BLUE_SPRUCE_SIGN);
        validSigns.add(ModBlocks.ASPEN_WALL_SIGN);
        validSigns.add(ModBlocks.AZALEA_WALL_SIGN);
        validSigns.add(ModBlocks.BAOBAB_WALL_SIGN);
        validSigns.add(ModBlocks.CEDAR_WALL_SIGN);
        validSigns.add(ModBlocks.EBONY_WALL_SIGN);
        validSigns.add(ModBlocks.FIR_WALL_SIGN);
        validSigns.add(ModBlocks.MAHOGANY_WALL_SIGN);
        validSigns.add(ModBlocks.MAPLE_WALL_SIGN);
        validSigns.add(ModBlocks.PALM_WALL_SIGN);
        validSigns.add(ModBlocks.PINE_WALL_SIGN);
        validSigns.add(ModBlocks.WALNUT_WALL_SIGN);
        validSigns.add(ModBlocks.WILLOW_WALL_SIGN);
        validSigns.add(ModBlocks.BLUE_SPRUCE_WALL_SIGN);
        BlockEntityType.SIGN.validBlocks = validSigns;

        var strippables = new ImmutableMap.Builder().putAll(AxeItem.STRIPPABLES);
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
        AxeItem.STRIPPABLES = strippables.build();
    }
}
