package com.ordana.would.reg;

import com.ordana.would.blocks.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.Supplier;

import static com.ordana.would.reg.BlockFactories.*;

public interface ModBlocks {

    static void init() {
    }

    Supplier<Block> PALE_HANGING_MOSS = register(
        "pale_hanging_moss",
        () -> new HangingMossBlock(BlockBehaviour.Properties.of()
            .ignitedByLava()
            .mapColor(MapColor.COLOR_LIGHT_GRAY)
            .noCollission()
            .sound(SoundType.MOSS_CARPET)
            .pushReaction(PushReaction.DESTROY)
        ),
        true
    );

    Supplier<Block> HANGING_WILLOW_LEAVES = register(
        "hanging_willow_leaves",
        () -> new HangingWillowLeavesBlock(BlockBehaviour.Properties.of()
            .mapColor(MapColor.PLANT)
            .noCollission()
            .randomTicks()
            .strength(0.2F)
            .sound(SoundType.VINE)
            .ignitedByLava()
            .pushReaction(PushReaction.DESTROY)
        ),
        true
    );

    //planks
    Supplier<Block> ASPEN_PLANKS = planks("aspen_planks");
    Supplier<Block> AZALEA_PLANKS = planks("azalea_planks", MapColor.TERRACOTTA_GREEN);
    Supplier<Block> BAOBAB_PLANKS = planks("baobab_planks");
    Supplier<Block> BLUE_SPRUCE_PLANKS = planks("blue_spruce_planks", MapColor.LAPIS);
    Supplier<Block> CEDAR_PLANKS = planks("cedar_planks", MapColor.TERRACOTTA_YELLOW);
    Supplier<Block> EBONY_PLANKS = planks("ebony_planks", MapColor.TERRACOTTA_BLACK);
    Supplier<Block> FIR_PLANKS = planks("fir_planks");
    Supplier<Block> MAHOGANY_PLANKS = planks("mahogany_planks", MapColor.TERRACOTTA_RED);
    Supplier<Block> MAPLE_PLANKS = planks("maple_planks");
    Supplier<Block> PALM_PLANKS = planks("palm_planks", MapColor.NETHER);
    Supplier<Block> PINE_PLANKS = planks("pine_planks", MapColor.PODZOL);
    Supplier<Block> WALNUT_PLANKS = planks("walnut_planks", MapColor.COLOR_BROWN);
    Supplier<Block> WILLOW_PLANKS = planks("willow_planks", MapColor.WARPED_NYLIUM);


    
    
    //logs
    Supplier<Block> ASPEN_LOG = log("aspen_log", MapColor.WOOD, MapColor.TERRACOTTA_WHITE);
    Supplier<Block> ASPEN_LOG_GAZING = log("aspen_log_gazing", MapColor.WOOD, MapColor.TERRACOTTA_WHITE);
    Supplier<Block> AZALEA_LOG = log("azalea_log", MapColor.TERRACOTTA_GREEN, MapColor.PODZOL);
    Supplier<Block> BAOBAB_LOG = log("baobab_log");
    Supplier<Block> BLUE_SPRUCE_LOG = log("blue_spruce_log", MapColor.LAPIS, MapColor.TERRACOTTA_LIGHT_BLUE);
    Supplier<Block> CEDAR_LOG = log("cedar_log", MapColor.TERRACOTTA_YELLOW, MapColor.TERRACOTTA_YELLOW);
    Supplier<Block> EBONY_LOG = log("ebony_log", MapColor.TERRACOTTA_BLACK, MapColor.PODZOL);
    Supplier<Block> FIR_LOG = log("fir_log");
    Supplier<Block> MAHOGANY_LOG = log("mahogany_log", MapColor.TERRACOTTA_RED, MapColor.TERRACOTTA_RED);
    Supplier<Block> MAPLE_LOG = log("maple_log");
    Supplier<Block> MAPLE_LOG_SAPPY = log("maple_log_sappy");
    Supplier<Block> PALM_LOG = log("palm_log", MapColor.NETHER, MapColor.TERRACOTTA_LIGHT_GRAY);
    Supplier<Block> PINE_LOG = log("pine_log", MapColor.PODZOL, MapColor.PODZOL);
    Supplier<Block> WALNUT_LOG = log("walnut_log", MapColor.COLOR_BROWN);
    Supplier<Block> WILLOW_LOG = log("willow_log", MapColor.WARPED_NYLIUM, MapColor.PODZOL);

    //wood
    Supplier<Block> ASPEN_WOOD = wood("aspen_wood", ASPEN_LOG, MapColor.TERRACOTTA_WHITE);
    Supplier<Block> ASPEN_WOOD_GAZING = wood("aspen_wood_gazing", ASPEN_LOG_GAZING, MapColor.TERRACOTTA_WHITE);
    Supplier<Block> AZALEA_WOOD = wood("azalea_wood", AZALEA_LOG, MapColor.PODZOL);
    Supplier<Block> BAOBAB_WOOD = wood("baobab_wood", BAOBAB_LOG);
    Supplier<Block> BLUE_SPRUCE_WOOD = wood("blue_spruce_wood", BLUE_SPRUCE_LOG, MapColor.TERRACOTTA_LIGHT_BLUE);
    Supplier<Block> CEDAR_WOOD = wood("cedar_wood", CEDAR_LOG, MapColor.TERRACOTTA_YELLOW);
    Supplier<Block> EBONY_WOOD = wood("ebony_wood", EBONY_LOG, MapColor.PODZOL);
    Supplier<Block> FIR_WOOD = wood("fir_wood", FIR_LOG);
    Supplier<Block> MAHOGANY_WOOD = wood("mahogany_wood", MAHOGANY_LOG, MapColor.TERRACOTTA_RED);
    Supplier<Block> MAPLE_WOOD = wood("maple_wood", MAPLE_LOG);
    Supplier<Block> MAPLE_WOOD_SAPPY = wood("maple_wood_sappy", MAPLE_LOG_SAPPY);
    Supplier<Block> PALM_WOOD = wood("palm_wood", PALM_LOG, MapColor.TERRACOTTA_LIGHT_GRAY);
    Supplier<Block> PINE_WOOD = wood("pine_wood", PINE_LOG, MapColor.PODZOL);
    Supplier<Block> WALNUT_WOOD = wood("walnut_wood", WALNUT_LOG, MapColor.COLOR_BROWN);
    Supplier<Block> WILLOW_WOOD = wood("willow_wood", WILLOW_LOG, MapColor.PODZOL);

    //stripped logs
    Supplier<Block> STRIPPED_ASPEN_LOG = log("stripped_aspen_log");
    Supplier<Block> STRIPPED_ASPEN_LOG_GAZING = log("stripped_aspen_log_gazing");
    Supplier<Block> STRIPPED_AZALEA_LOG = log("stripped_azalea_log", MapColor.TERRACOTTA_GREEN);
    Supplier<Block> STRIPPED_BAOBAB_LOG = log("stripped_baobab_log");
    Supplier<Block> STRIPPED_BLUE_SPRUCE_LOG = log("stripped_blue_spruce_log", MapColor.LAPIS);
    Supplier<Block> STRIPPED_CEDAR_LOG = log("stripped_cedar_log", MapColor.TERRACOTTA_YELLOW);
    Supplier<Block> STRIPPED_EBONY_LOG = log("stripped_ebony_log", MapColor.TERRACOTTA_BLACK);
    Supplier<Block> EBONY_HEARTWOOD_LOG = log("ebony_heartwood_log");
    Supplier<Block> STRIPPED_FIR_LOG = log("stripped_fir_log");
    Supplier<Block> STRIPPED_MAHOGANY_LOG = log("stripped_mahogany_log", MapColor.TERRACOTTA_RED);
    Supplier<Block> STRIPPED_MAPLE_LOG = log("stripped_maple_log");
    Supplier<Block> STRIPPED_PALM_LOG = log("stripped_palm_log", MapColor.NETHER);
    Supplier<Block> STRIPPED_PINE_LOG = log("stripped_pine_log", MapColor.PODZOL);
    Supplier<Block> STRIPPED_WALNUT_LOG = log("stripped_walnut_log", MapColor.COLOR_BROWN);
    Supplier<Block> STRIPPED_WILLOW_LOG = log("stripped_willow_log", MapColor.WARPED_NYLIUM);

    //stripped wood
    Supplier<Block> STRIPPED_ASPEN_WOOD = wood("stripped_aspen_wood", STRIPPED_ASPEN_LOG);
    Supplier<Block> STRIPPED_ASPEN_WOOD_GAZING = wood("stripped_aspen_wood_gazing", STRIPPED_ASPEN_LOG_GAZING);
    Supplier<Block> STRIPPED_AZALEA_WOOD = wood("stripped_azalea_wood", STRIPPED_AZALEA_LOG, MapColor.TERRACOTTA_GREEN);
    Supplier<Block> STRIPPED_BAOBAB_WOOD = wood("stripped_baobab_wood", STRIPPED_BAOBAB_LOG);
    Supplier<Block> STRIPPED_BLUE_SPRUCE_WOOD = wood("stripped_blue_spruce_wood", STRIPPED_BLUE_SPRUCE_LOG, MapColor.LAPIS);
    Supplier<Block> STRIPPED_CEDAR_WOOD = wood("stripped_cedar_wood", STRIPPED_CEDAR_LOG, MapColor.TERRACOTTA_YELLOW);
    Supplier<Block> STRIPPED_EBONY_WOOD = wood("stripped_ebony_wood", STRIPPED_EBONY_LOG, MapColor.TERRACOTTA_BLACK);
    Supplier<Block> EBONY_HEARTWOOD = wood("ebony_heartwood", EBONY_HEARTWOOD_LOG);
    Supplier<Block> STRIPPED_FIR_WOOD = wood("stripped_fir_wood", STRIPPED_FIR_LOG);
    Supplier<Block> STRIPPED_MAHOGANY_WOOD = wood("stripped_mahogany_wood", STRIPPED_MAHOGANY_LOG, MapColor.TERRACOTTA_RED);
    Supplier<Block> STRIPPED_MAPLE_WOOD = wood("stripped_maple_wood", STRIPPED_MAPLE_LOG);
    Supplier<Block> STRIPPED_PALM_WOOD = wood("stripped_palm_wood", STRIPPED_PALM_LOG, MapColor.NETHER);
    Supplier<Block> STRIPPED_PINE_WOOD = wood("stripped_pine_wood", STRIPPED_PINE_LOG, MapColor.PODZOL);
    Supplier<Block> STRIPPED_WALNUT_WOOD = wood("stripped_walnut_wood", STRIPPED_WALNUT_LOG, MapColor.COLOR_BROWN);
    Supplier<Block> STRIPPED_WILLOW_WOOD = wood("stripped_willow_wood", STRIPPED_WILLOW_LOG, MapColor.WARPED_NYLIUM);

    //saplings
    Supplier<Block> ASPEN_SAPLING = sapling("aspen_sapling", ModTreeGrowers.ASPEN);
    Supplier<Block> BAOBAB_SAPLING = sapling("baobab_sapling", ModTreeGrowers.BAOBAB);
    Supplier<Block> BLUE_SPRUCE_SAPLING = sapling("blue_spruce_sapling", ModTreeGrowers.BLUE_SPRUCE);
    Supplier<Block> CEDAR_SAPLING = sapling("cedar_sapling", ModTreeGrowers.CEDAR);
    Supplier<Block> EBONY_SAPLING = sapling("ebony_sapling", ModTreeGrowers.EBONY);
    Supplier<Block> FIR_SAPLING = sapling("fir_sapling", ModTreeGrowers.FIR);
    Supplier<Block> MAHOGANY_SAPLING = sapling("mahogany_sapling", ModTreeGrowers.MAHOGANY);
    Supplier<Block> MAPLE_SAPLING = sapling("maple_sapling", ModTreeGrowers.MAPLE);
    Supplier<Block> PINE_SAPLING = sapling("pine_sapling", ModTreeGrowers.PINE);
    Supplier<Block> WALNUT_SAPLING = sapling("walnut_sapling", ModTreeGrowers.WALNUT);
    Supplier<Block> WILLOW_SAPLING = sapling("willow_sapling", ModTreeGrowers.WILLOW);

    Supplier<Block> COCONUT = register(
        "coconut",
        () -> new CoconutBlock(BlockBehaviour.Properties.of()
            .mapColor(MapColor.WOOD)
            .randomTicks()
            .instabreak()
            .sound(SoundType.CHERRY_WOOD)
            .pushReaction(PushReaction.DESTROY)
        ),
        true
    );

    Supplier<Block> HANGING_COCONUT = register(
        "hanging_coconut",
        () -> new HangingCoconutBlock(BlockBehaviour.Properties.ofFullCopy(COCONUT.get())
            .mapColor(MapColor.PLANT)
            .dropsLike(COCONUT.get())
        ),
        false
    );

    Supplier<Block> POTTED_ASPEN_SAPLING = pottedSapling("potted_aspen_sapling", ASPEN_SAPLING);
    Supplier<Block> POTTED_BAOBAB_SAPLING = pottedSapling("potted_baobab_sapling", BAOBAB_SAPLING);
    Supplier<Block> POTTED_BLUE_SPRUCE_SAPLING = pottedSapling("potted_blue_spruce_sapling", BLUE_SPRUCE_SAPLING);
    Supplier<Block> POTTED_CEDAR_SAPLING = pottedSapling("potted_cedar_sapling", CEDAR_SAPLING);
    Supplier<Block> POTTED_COCONUT = pottedSapling("potted_coconut", COCONUT);
    Supplier<Block> POTTED_EBONY_SAPLING = pottedSapling("potted_ebony_sapling", EBONY_SAPLING);
    Supplier<Block> POTTED_FIR_SAPLING = pottedSapling("potted_fir_sapling", FIR_SAPLING);
    Supplier<Block> POTTED_MAHOGANY_SAPLING = pottedSapling("potted_mahogany_sapling", MAHOGANY_SAPLING);
    Supplier<Block> POTTED_MAPLE_SAPLING = pottedSapling("potted_maple_sapling", MAPLE_SAPLING);
    Supplier<Block> POTTED_PINE_SAPLING = pottedSapling("potted_pine_sapling", PINE_SAPLING);
    Supplier<Block> POTTED_WALNUT_SAPLING = pottedSapling("potted_walnut_sapling", WALNUT_SAPLING);
    Supplier<Block> POTTED_WILLOW_SAPLING = pottedSapling("potted_willow_sapling", WILLOW_SAPLING);

    //leaves
    Supplier<Block> ASPEN_LEAVES = leaves("aspen_leaves", ASPEN_SAPLING);
    Supplier<Block> BAOBAB_LEAVES = leaves("baobab_leaves", BAOBAB_SAPLING);
    Supplier<Block> BLUE_SPRUCE_LEAVES = leaves("blue_spruce_leaves", BLUE_SPRUCE_SAPLING);
    Supplier<Block> CEDAR_LEAVES = leaves("cedar_leaves", CEDAR_SAPLING);
    Supplier<Block> EBONY_LEAVES = leaves("ebony_leaves", EBONY_SAPLING);
    Supplier<Block> EBONY_LEAVES_FRUITING = leaves("ebony_leaves_fruiting", EBONY_SAPLING, MapColor.GOLD);
    Supplier<Block> FIR_LEAVES = leaves("fir_leaves", FIR_SAPLING);
    Supplier<Block> MAHOGANY_LEAVES = leaves("mahogany_leaves", MAHOGANY_SAPLING);
    Supplier<Block> MAPLE_LEAVES = leaves("maple_leaves", MAPLE_SAPLING, MapColor.CRIMSON_STEM);
    Supplier<Block> PALM_LEAVES = leaves("palm_leaves", COCONUT, PalmLeavesBlock::new, MapColor.PLANT);
    Supplier<Block> PINE_LEAVES = leaves("pine_leaves", PINE_SAPLING);
    Supplier<Block> WALNUT_LEAVES = leaves("walnut_leaves", WALNUT_SAPLING);
    Supplier<Block> WILLOW_LEAVES = leaves("willow_leaves", WILLOW_SAPLING);
    
    //slabs
    Supplier<Block> ASPEN_SLAB = slab("aspen_slab", ASPEN_PLANKS);
    Supplier<Block> AZALEA_SLAB = slab("azalea_slab", AZALEA_PLANKS);
    Supplier<Block> BAOBAB_SLAB = slab("baobab_slab", BAOBAB_PLANKS);
    Supplier<Block> BLUE_SPRUCE_SLAB = slab("blue_spruce_slab", BLUE_SPRUCE_PLANKS);
    Supplier<Block> CEDAR_SLAB = slab("cedar_slab", CEDAR_PLANKS);
    Supplier<Block> EBONY_SLAB = slab("ebony_slab", EBONY_PLANKS);
    Supplier<Block> FIR_SLAB = slab("fir_slab", FIR_PLANKS);
    Supplier<Block> MAHOGANY_SLAB = slab("mahogany_slab", MAHOGANY_PLANKS);
    Supplier<Block> MAPLE_SLAB = slab("maple_slab", MAPLE_PLANKS);
    Supplier<Block> PALM_SLAB = slab("palm_slab", PALM_PLANKS);
    Supplier<Block> PINE_SLAB = slab("pine_slab", PINE_PLANKS);
    Supplier<Block> WALNUT_SLAB = slab("walnut_slab", WALNUT_PLANKS);
    Supplier<Block> WILLOW_SLAB = slab("willow_slab", WILLOW_PLANKS);
    
    //planks
    Supplier<Block> ASPEN_STAIRS = stairs("aspen_stairs", ASPEN_PLANKS);
    Supplier<Block> AZALEA_STAIRS = stairs("azalea_stairs", AZALEA_PLANKS);
    Supplier<Block> BAOBAB_STAIRS = stairs("baobab_stairs", BAOBAB_PLANKS);
    Supplier<Block> BLUE_SPRUCE_STAIRS = stairs("blue_spruce_stairs", BLUE_SPRUCE_PLANKS);
    Supplier<Block> CEDAR_STAIRS = stairs("cedar_stairs", CEDAR_PLANKS);
    Supplier<Block> EBONY_STAIRS = stairs("ebony_stairs", EBONY_PLANKS);
    Supplier<Block> FIR_STAIRS = stairs("fir_stairs", FIR_PLANKS);
    Supplier<Block> MAHOGANY_STAIRS = stairs("mahogany_stairs", MAHOGANY_PLANKS);
    Supplier<Block> MAPLE_STAIRS = stairs("maple_stairs", MAPLE_PLANKS);
    Supplier<Block> PALM_STAIRS = stairs("palm_stairs", PALM_PLANKS);
    Supplier<Block> PINE_STAIRS = stairs("pine_stairs", PINE_PLANKS);
    Supplier<Block> WALNUT_STAIRS = stairs("walnut_stairs", WALNUT_PLANKS);
    Supplier<Block> WILLOW_STAIRS = stairs("willow_stairs", WILLOW_PLANKS);

    //fences
    Supplier<Block> ASPEN_FENCE = fence("aspen_fence", ASPEN_PLANKS);
    Supplier<Block> AZALEA_FENCE = fence("azalea_fence", AZALEA_PLANKS);
    Supplier<Block> BAOBAB_FENCE = fence("baobab_fence", BAOBAB_PLANKS);
    Supplier<Block> BLUE_SPRUCE_FENCE = fence("blue_spruce_fence", BLUE_SPRUCE_PLANKS);
    Supplier<Block> CEDAR_FENCE = fence("cedar_fence", CEDAR_PLANKS);
    Supplier<Block> EBONY_FENCE = fence("ebony_fence", EBONY_PLANKS);
    Supplier<Block> FIR_FENCE = fence("fir_fence", FIR_PLANKS);
    Supplier<Block> MAHOGANY_FENCE = fence("mahogany_fence", MAHOGANY_PLANKS);
    Supplier<Block> MAPLE_FENCE = fence("maple_fence", MAPLE_PLANKS);
    Supplier<Block> PALM_FENCE = fence("palm_fence", PALM_PLANKS);
    Supplier<Block> PINE_FENCE = fence("pine_fence", PINE_PLANKS);
    Supplier<Block> WALNUT_FENCE = fence("walnut_fence", WALNUT_PLANKS);
    Supplier<Block> WILLOW_FENCE = fence("willow_fence", WILLOW_PLANKS);

    //fence gates
    Supplier<Block> ASPEN_FENCE_GATE = fenceGate("aspen_fence_gate", ASPEN_PLANKS, ModWoodTypes.ASPEN);
    Supplier<Block> AZALEA_FENCE_GATE = fenceGate("azalea_fence_gate", AZALEA_PLANKS, ModWoodTypes.AZALEA);
    Supplier<Block> BAOBAB_FENCE_GATE = fenceGate("baobab_fence_gate", BAOBAB_PLANKS, ModWoodTypes.BAOBAB);
    Supplier<Block> BLUE_SPRUCE_FENCE_GATE = fenceGate("blue_spruce_fence_gate", BLUE_SPRUCE_PLANKS, ModWoodTypes.BLUE_SPRUCE);
    Supplier<Block> CEDAR_FENCE_GATE = fenceGate("cedar_fence_gate", CEDAR_PLANKS, ModWoodTypes.CEDAR);
    Supplier<Block> EBONY_FENCE_GATE = fenceGate("ebony_fence_gate", EBONY_PLANKS, ModWoodTypes.EBONY);
    Supplier<Block> FIR_FENCE_GATE = fenceGate("fir_fence_gate", FIR_PLANKS, ModWoodTypes.FIR);
    Supplier<Block> MAHOGANY_FENCE_GATE = fenceGate("mahogany_fence_gate", MAHOGANY_PLANKS, ModWoodTypes.MAHOGANY);
    Supplier<Block> MAPLE_FENCE_GATE = fenceGate("maple_fence_gate", MAPLE_PLANKS, ModWoodTypes.MAPLE);
    Supplier<Block> PALM_FENCE_GATE = fenceGate("palm_fence_gate", PALM_PLANKS, ModWoodTypes.PALM);
    Supplier<Block> PINE_FENCE_GATE = fenceGate("pine_fence_gate", PINE_PLANKS, ModWoodTypes.PINE);
    Supplier<Block> WALNUT_FENCE_GATE = fenceGate("walnut_fence_gate", WALNUT_PLANKS, ModWoodTypes.WALNUT);
    Supplier<Block> WILLOW_FENCE_GATE = fenceGate("willow_fence_gate", WILLOW_PLANKS, ModWoodTypes.WILLOW);

    //buttons
    Supplier<Block> ASPEN_BUTTON = button("aspen_button", ModBlockSetTypes.ASPEN);
    Supplier<Block> AZALEA_BUTTON = button("azalea_button", ModBlockSetTypes.AZALEA);
    Supplier<Block> BAOBAB_BUTTON = button("baobab_button", ModBlockSetTypes.BAOBAB);
    Supplier<Block> BLUE_SPRUCE_BUTTON = button("blue_spruce_button", ModBlockSetTypes.BLUE_SPRUCE);
    Supplier<Block> CEDAR_BUTTON = button("cedar_button", ModBlockSetTypes.CEDAR);
    Supplier<Block> EBONY_BUTTON = button("ebony_button", ModBlockSetTypes.EBONY);
    Supplier<Block> FIR_BUTTON = button("fir_button", ModBlockSetTypes.FIR);
    Supplier<Block> MAHOGANY_BUTTON = button("mahogany_button", ModBlockSetTypes.MAHOGANY);
    Supplier<Block> MAPLE_BUTTON = button("maple_button", ModBlockSetTypes.MAPLE);
    Supplier<Block> PALM_BUTTON = button("palm_button", ModBlockSetTypes.PALM);
    Supplier<Block> PINE_BUTTON = button("pine_button", ModBlockSetTypes.PINE);
    Supplier<Block> WALNUT_BUTTON = button("walnut_button", ModBlockSetTypes.WALNUT);
    Supplier<Block> WILLOW_BUTTON = button("willow_button", ModBlockSetTypes.WILLOW);

    //pressure plates
    Supplier<Block> ASPEN_PRESSURE_PLATE = pressurePlate("aspen_pressure_plate", ASPEN_PLANKS, ModBlockSetTypes.ASPEN);
    Supplier<Block> AZALEA_PRESSURE_PLATE = pressurePlate("azalea_pressure_plate", AZALEA_PLANKS, ModBlockSetTypes.AZALEA);
    Supplier<Block> BAOBAB_PRESSURE_PLATE = pressurePlate("baobab_pressure_plate", BAOBAB_PLANKS, ModBlockSetTypes.BAOBAB);
    Supplier<Block> BLUE_SPRUCE_PRESSURE_PLATE = pressurePlate("blue_spruce_pressure_plate", BLUE_SPRUCE_PLANKS, ModBlockSetTypes.BLUE_SPRUCE);
    Supplier<Block> CEDAR_PRESSURE_PLATE = pressurePlate("cedar_pressure_plate", CEDAR_PLANKS, ModBlockSetTypes.CEDAR);
    Supplier<Block> EBONY_PRESSURE_PLATE = pressurePlate("ebony_pressure_plate", EBONY_PLANKS, ModBlockSetTypes.EBONY);
    Supplier<Block> FIR_PRESSURE_PLATE = pressurePlate("fir_pressure_plate", FIR_PLANKS, ModBlockSetTypes.FIR);
    Supplier<Block> MAHOGANY_PRESSURE_PLATE = pressurePlate("mahogany_pressure_plate", MAHOGANY_PLANKS, ModBlockSetTypes.MAHOGANY);
    Supplier<Block> MAPLE_PRESSURE_PLATE = pressurePlate("maple_pressure_plate", MAPLE_PLANKS, ModBlockSetTypes.MAPLE);
    Supplier<Block> PALM_PRESSURE_PLATE = pressurePlate("palm_pressure_plate", PALM_PLANKS, ModBlockSetTypes.PALM);
    Supplier<Block> PINE_PRESSURE_PLATE = pressurePlate("pine_pressure_plate", PINE_PLANKS, ModBlockSetTypes.PINE);
    Supplier<Block> WALNUT_PRESSURE_PLATE = pressurePlate("walnut_pressure_plate", WALNUT_PLANKS, ModBlockSetTypes.WALNUT);
    Supplier<Block> WILLOW_PRESSURE_PLATE = pressurePlate("willow_pressure_plate", WILLOW_PLANKS, ModBlockSetTypes.WILLOW);

    //signs
    Supplier<Block> ASPEN_SIGN = standingSign("aspen_sign", ASPEN_PLANKS, ModWoodTypes.ASPEN);
    Supplier<Block> AZALEA_SIGN = standingSign("azalea_sign", AZALEA_PLANKS, ModWoodTypes.AZALEA);
    Supplier<Block> BAOBAB_SIGN = standingSign("baobab_sign", BAOBAB_PLANKS, ModWoodTypes.BAOBAB);
    Supplier<Block> BLUE_SPRUCE_SIGN = standingSign("blue_spruce_sign", BLUE_SPRUCE_PLANKS, ModWoodTypes.BLUE_SPRUCE);
    Supplier<Block> CEDAR_SIGN = standingSign("cedar_sign", CEDAR_PLANKS, ModWoodTypes.CEDAR);
    Supplier<Block> EBONY_SIGN = standingSign("ebony_sign", EBONY_PLANKS, ModWoodTypes.EBONY);
    Supplier<Block> FIR_SIGN = standingSign("fir_sign", FIR_PLANKS, ModWoodTypes.FIR);
    Supplier<Block> MAHOGANY_SIGN = standingSign("mahogany_sign", MAHOGANY_PLANKS, ModWoodTypes.MAHOGANY);
    Supplier<Block> MAPLE_SIGN = standingSign("maple_sign", MAPLE_PLANKS, ModWoodTypes.MAPLE);
    Supplier<Block> PALM_SIGN = standingSign("palm_sign", PALM_PLANKS, ModWoodTypes.PALM);
    Supplier<Block> PINE_SIGN = standingSign("pine_sign", PINE_PLANKS, ModWoodTypes.PINE);
    Supplier<Block> WALNUT_SIGN = standingSign("walnut_sign", WALNUT_PLANKS, ModWoodTypes.WALNUT);
    Supplier<Block> WILLOW_SIGN = standingSign("willow_sign", WILLOW_PLANKS, ModWoodTypes.WILLOW);

    Supplier<Block> ASPEN_WALL_SIGN = wallSign("aspen_wall_sign", ASPEN_SIGN, ModWoodTypes.ASPEN);
    Supplier<Block> AZALEA_WALL_SIGN = wallSign("azalea_wall_sign", AZALEA_SIGN, ModWoodTypes.AZALEA);
    Supplier<Block> BAOBAB_WALL_SIGN = wallSign("baobab_wall_sign", BAOBAB_SIGN, ModWoodTypes.BAOBAB);
    Supplier<Block> BLUE_SPRUCE_WALL_SIGN = wallSign("blue_spruce_wall_sign", BLUE_SPRUCE_SIGN, ModWoodTypes.BLUE_SPRUCE);
    Supplier<Block> CEDAR_WALL_SIGN = wallSign("cedar_wall_sign", CEDAR_SIGN, ModWoodTypes.CEDAR);
    Supplier<Block> EBONY_WALL_SIGN = wallSign("ebony_wall_sign", EBONY_SIGN, ModWoodTypes.EBONY);
    Supplier<Block> FIR_WALL_SIGN = wallSign("fir_wall_sign", FIR_SIGN, ModWoodTypes.FIR);
    Supplier<Block> MAHOGANY_WALL_SIGN = wallSign("mahogany_wall_sign", MAHOGANY_SIGN, ModWoodTypes.MAHOGANY);
    Supplier<Block> MAPLE_WALL_SIGN = wallSign("maple_wall_sign", MAPLE_SIGN, ModWoodTypes.MAPLE);
    Supplier<Block> PALM_WALL_SIGN = wallSign("palm_wall_sign", PALM_SIGN, ModWoodTypes.PALM);
    Supplier<Block> PINE_WALL_SIGN = wallSign("pine_wall_sign", PINE_SIGN, ModWoodTypes.PINE);
    Supplier<Block> WALNUT_WALL_SIGN = wallSign("walnut_wall_sign", WALNUT_SIGN, ModWoodTypes.WALNUT);
    Supplier<Block> WILLOW_WALL_SIGN = wallSign("willow_wall_sign", WILLOW_SIGN, ModWoodTypes.WILLOW);
    
    //hanging signs
    Supplier<Block> ASPEN_HANGING_SIGN = ceilingHangingSign("aspen_hanging_sign", STRIPPED_ASPEN_LOG, ModWoodTypes.ASPEN);
    Supplier<Block> AZALEA_HANGING_SIGN = ceilingHangingSign("azalea_hanging_sign", STRIPPED_AZALEA_LOG, ModWoodTypes.AZALEA);
    Supplier<Block> BAOBAB_HANGING_SIGN = ceilingHangingSign("baobab_hanging_sign", STRIPPED_BAOBAB_LOG, ModWoodTypes.BAOBAB);
    Supplier<Block> BLUE_SPRUCE_HANGING_SIGN = ceilingHangingSign("blue_spruce_hanging_sign", STRIPPED_BLUE_SPRUCE_LOG, ModWoodTypes.BLUE_SPRUCE);
    Supplier<Block> CEDAR_HANGING_SIGN = ceilingHangingSign("cedar_hanging_sign", STRIPPED_CEDAR_LOG, ModWoodTypes.CEDAR);
    Supplier<Block> EBONY_HANGING_SIGN = ceilingHangingSign("ebony_hanging_sign", EBONY_HEARTWOOD_LOG, ModWoodTypes.EBONY);
    Supplier<Block> FIR_HANGING_SIGN = ceilingHangingSign("fir_hanging_sign", STRIPPED_FIR_LOG, ModWoodTypes.FIR);
    Supplier<Block> MAHOGANY_HANGING_SIGN = ceilingHangingSign("mahogany_hanging_sign", STRIPPED_MAHOGANY_LOG, ModWoodTypes.MAHOGANY);
    Supplier<Block> MAPLE_HANGING_SIGN = ceilingHangingSign("maple_hanging_sign", STRIPPED_MAPLE_LOG, ModWoodTypes.MAPLE);
    Supplier<Block> PALM_HANGING_SIGN = ceilingHangingSign("palm_hanging_sign", STRIPPED_PALM_LOG, ModWoodTypes.PALM);
    Supplier<Block> PINE_HANGING_SIGN = ceilingHangingSign("pine_hanging_sign", STRIPPED_PINE_LOG, ModWoodTypes.PINE);
    Supplier<Block> WALNUT_HANGING_SIGN = ceilingHangingSign("walnut_hanging_sign", STRIPPED_WALNUT_LOG, ModWoodTypes.WALNUT);
    Supplier<Block> WILLOW_HANGING_SIGN = ceilingHangingSign("willow_hanging_sign", STRIPPED_WILLOW_LOG, ModWoodTypes.WILLOW);

    Supplier<Block> ASPEN_WALL_HANGING_SIGN = wallHangingSign("aspen_wall_hanging_sign", STRIPPED_ASPEN_LOG, ASPEN_SIGN, ModWoodTypes.ASPEN);
    Supplier<Block> AZALEA_WALL_HANGING_SIGN = wallHangingSign("azalea_wall_hanging_sign", STRIPPED_AZALEA_LOG, AZALEA_SIGN, ModWoodTypes.AZALEA);
    Supplier<Block> BAOBAB_WALL_HANGING_SIGN = wallHangingSign("baobab_wall_hanging_sign", STRIPPED_BAOBAB_LOG, BAOBAB_SIGN, ModWoodTypes.BAOBAB);
    Supplier<Block> BLUE_SPRUCE_WALL_HANGING_SIGN = wallHangingSign("blue_spruce_wall_hanging_sign", STRIPPED_BLUE_SPRUCE_LOG, BLUE_SPRUCE_SIGN, ModWoodTypes.BLUE_SPRUCE);
    Supplier<Block> CEDAR_WALL_HANGING_SIGN = wallHangingSign("cedar_wall_hanging_sign", STRIPPED_CEDAR_LOG, CEDAR_SIGN, ModWoodTypes.CEDAR);
    Supplier<Block> EBONY_WALL_HANGING_SIGN = wallHangingSign("ebony_wall_hanging_sign", EBONY_HEARTWOOD_LOG, EBONY_SIGN, ModWoodTypes.EBONY);
    Supplier<Block> FIR_WALL_HANGING_SIGN = wallHangingSign("fir_wall_hanging_sign", STRIPPED_FIR_LOG, FIR_SIGN, ModWoodTypes.FIR);
    Supplier<Block> MAHOGANY_WALL_HANGING_SIGN = wallHangingSign("mahogany_wall_hanging_sign", STRIPPED_MAHOGANY_LOG, MAHOGANY_SIGN, ModWoodTypes.MAHOGANY);
    Supplier<Block> MAPLE_WALL_HANGING_SIGN = wallHangingSign("maple_wall_hanging_sign", STRIPPED_MAPLE_LOG, MAPLE_SIGN, ModWoodTypes.MAPLE);
    Supplier<Block> PALM_WALL_HANGING_SIGN = wallHangingSign("palm_wall_hanging_sign", STRIPPED_PALM_LOG, PALM_SIGN, ModWoodTypes.PALM);
    Supplier<Block> PINE_WALL_HANGING_SIGN = wallHangingSign("pine_wall_hanging_sign", STRIPPED_PINE_LOG, PINE_SIGN, ModWoodTypes.PINE);
    Supplier<Block> WALNUT_WALL_HANGING_SIGN = wallHangingSign("walnut_wall_hanging_sign", STRIPPED_WALNUT_LOG, WALNUT_SIGN, ModWoodTypes.WALNUT);
    Supplier<Block> WILLOW_WALL_HANGING_SIGN = wallHangingSign("willow_wall_hanging_sign", STRIPPED_WILLOW_LOG, WILLOW_SIGN, ModWoodTypes.WILLOW);
    
    //doors
    Supplier<Block> ASPEN_DOOR = door("aspen_door", ASPEN_PLANKS, ModBlockSetTypes.ASPEN);
    Supplier<Block> AZALEA_DOOR = door("azalea_door", AZALEA_PLANKS, ModBlockSetTypes.AZALEA);
    Supplier<Block> BAOBAB_DOOR = door("baobab_door", BAOBAB_PLANKS, ModBlockSetTypes.BAOBAB);
    Supplier<Block> BLUE_SPRUCE_DOOR = door("blue_spruce_door", BLUE_SPRUCE_PLANKS, ModBlockSetTypes.BLUE_SPRUCE);
    Supplier<Block> CEDAR_DOOR = door("cedar_door", CEDAR_PLANKS, ModBlockSetTypes.CEDAR);
    Supplier<Block> EBONY_DOOR = door("ebony_door", EBONY_PLANKS, ModBlockSetTypes.EBONY);
    Supplier<Block> FIR_DOOR = door("fir_door", FIR_PLANKS, ModBlockSetTypes.FIR);
    Supplier<Block> MAHOGANY_DOOR = door("mahogany_door", MAHOGANY_PLANKS, ModBlockSetTypes.MAHOGANY);
    Supplier<Block> MAPLE_DOOR = door("maple_door", MAPLE_PLANKS, ModBlockSetTypes.MAPLE);
    Supplier<Block> PALM_DOOR = door("palm_door", PALM_PLANKS, ModBlockSetTypes.PALM);
    Supplier<Block> PINE_DOOR = door("pine_door", PINE_PLANKS, ModBlockSetTypes.PINE);
    Supplier<Block> WALNUT_DOOR = door("walnut_door", WALNUT_PLANKS, ModBlockSetTypes.WALNUT);
    Supplier<Block> WILLOW_DOOR = door("willow_door", WILLOW_PLANKS, ModBlockSetTypes.WILLOW);

    //trapdoors
    Supplier<Block> ASPEN_TRAPDOOR = trapdoor("aspen_trapdoor", ASPEN_PLANKS, ModBlockSetTypes.ASPEN);
    Supplier<Block> AZALEA_TRAPDOOR = trapdoor("azalea_trapdoor", AZALEA_PLANKS, ModBlockSetTypes.AZALEA);
    Supplier<Block> BAOBAB_TRAPDOOR = trapdoor("baobab_trapdoor", BAOBAB_PLANKS, ModBlockSetTypes.BAOBAB);
    Supplier<Block> BLUE_SPRUCE_TRAPDOOR = trapdoor("blue_spruce_trapdoor", BLUE_SPRUCE_PLANKS, ModBlockSetTypes.BLUE_SPRUCE);
    Supplier<Block> CEDAR_TRAPDOOR = trapdoor("cedar_trapdoor", CEDAR_PLANKS, ModBlockSetTypes.CEDAR);
    Supplier<Block> EBONY_TRAPDOOR = trapdoor("ebony_trapdoor", EBONY_PLANKS, ModBlockSetTypes.EBONY);
    Supplier<Block> FIR_TRAPDOOR = trapdoor("fir_trapdoor", FIR_PLANKS, ModBlockSetTypes.FIR);
    Supplier<Block> MAHOGANY_TRAPDOOR = trapdoor("mahogany_trapdoor", MAHOGANY_PLANKS, ModBlockSetTypes.MAHOGANY);
    Supplier<Block> MAPLE_TRAPDOOR = trapdoor("maple_trapdoor", MAPLE_PLANKS, ModBlockSetTypes.MAPLE);
    Supplier<Block> PALM_TRAPDOOR = trapdoor("palm_trapdoor", PALM_PLANKS, ModBlockSetTypes.PALM);
    Supplier<Block> PINE_TRAPDOOR = trapdoor("pine_trapdoor", PINE_PLANKS, ModBlockSetTypes.PINE);
    Supplier<Block> WALNUT_TRAPDOOR = trapdoor("walnut_trapdoor", WALNUT_PLANKS, ModBlockSetTypes.WALNUT);
    Supplier<Block> WILLOW_TRAPDOOR = trapdoor("willow_trapdoor", WILLOW_PLANKS, ModBlockSetTypes.WILLOW);

}
