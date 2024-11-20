package com.ordana.would.reg;

import com.ordana.would.Would;
import com.ordana.would.blocks.*;
import com.ordana.would.blocks.tree_growers.*;
import net.mehvahdjukaar.moonlight.api.block.ModStairBlock;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.Supplier;

public class ModBlocks {

    public static void init() {
    }

    private static boolean always(BlockState state, BlockGetter blockGetter, BlockPos pos) {
        return true;
    }

    private static boolean never(BlockState state, BlockGetter blockGetter, BlockPos pos) {
        return false;
    }
    private static boolean never(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, EntityType<?> entityType) {return false;}

    private static Boolean ocelotOrParrot(BlockState state, BlockGetter blockGetter, BlockPos pos, EntityType<?> entity) {
        return entity == EntityType.OCELOT || entity == EntityType.PARROT;
    }

    public static <T extends Block> Supplier<T> regBlock(String name, Supplier<T> block) {
        return RegHelper.registerBlock(Would.res(name), block);
    }

    public static void regBlockItem(String name, Supplier<? extends Block> blockSup, Item.Properties properties) {
        RegHelper.registerItem(Would.res(name), () -> new BlockItem(blockSup.get(), properties));
    }

    public static <T extends Block> Supplier<T> regWithItem(String name, Supplier<T> blockFactory) {
        Supplier<T> block = regBlock(name, blockFactory);
        regBlockItem(name, block, new Item.Properties());
        return block;
    }


    private static Block log(MapColor topMapColor, MapColor sideMapColor, SoundType soundType) {
        return new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor((blockState) -> blockState.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(soundType).ignitedByLava());
    }

    private static Block wood(MapColor mapColor, SoundType soundType) {
        return new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(mapColor).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(soundType).ignitedByLava());
    }
    
    private static Block leaves(SoundType type) {
        return new LeavesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).strength(0.2F).randomTicks().sound(type).noOcclusion().isValidSpawn(ModBlocks::ocelotOrParrot).isSuffocating(ModBlocks::never).isViewBlocking(ModBlocks::never).ignitedByLava().pushReaction(PushReaction.DESTROY).isRedstoneConductor(ModBlocks::never));
    }

    private static Block sapling(AbstractTreeGrower treeGrower) {
        return new ModSaplingBlock(treeGrower, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY));
    }

    private static FlowerPotBlock pottedSapling(Block content, FeatureFlag... requiredFeatures) {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY);
        if (requiredFeatures.length > 0) {
            properties = properties.requiredFeatures(requiredFeatures);
        }

        return new FlowerPotBlock(content, properties);
    }

    private static Block planks(MapColor mapColor, SoundType soundType) {
        return new Block(BlockBehaviour.Properties.of().mapColor(mapColor).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(soundType).ignitedByLava());
    }

    private static Block slab(MapColor mapColor, SoundType soundType) {
        return new SlabBlock(BlockBehaviour.Properties.of().mapColor(mapColor).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(soundType).ignitedByLava());
    }

    private static Block stairs(Supplier<Block> state, BlockBehaviour.Properties properties) {
        return new ModStairBlock(state, properties);
    }

    private static Block fence(MapColor mapColor, SoundType soundType) {
        return new FenceBlock(BlockBehaviour.Properties.of().mapColor(mapColor).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).ignitedByLava().sound(soundType));
    }

    private static Block fenceGate(MapColor mapColor, SoundType soundType, WoodType woodType) {
        return new FenceGateBlock(BlockBehaviour.Properties.of().mapColor(mapColor).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).ignitedByLava().sound(soundType), woodType);
    }

    private static Block pressurePlate(MapColor mapColor) {
        return new ModPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of().mapColor(mapColor).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(0.5F).ignitedByLava().pushReaction(PushReaction.DESTROY), BlockSetType.ACACIA);
    }

    private static Block button(FeatureFlag... requiredFeatures) {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.of().noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY);
        if (requiredFeatures.length > 0) {
            properties = properties.requiredFeatures(requiredFeatures);
        }

        return new ModButtonBlock(properties, BlockSetType.ACACIA, 30, true);
    }

    private static Block standingSign(MapColor mapColor, WoodType woodType) {
        return new StandingSignBlock(BlockBehaviour.Properties.of().mapColor(mapColor).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F).ignitedByLava(), woodType);
    }


    private static Block wallSign(MapColor mapColor, Block block, WoodType woodType) {
        return new WallSignBlock(BlockBehaviour.Properties.of().mapColor(mapColor).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F).dropsLike(block).ignitedByLava(), woodType);
    }

    private static Block hangingSign(MapColor mapColor, WoodType woodType) {
        return new CeilingHangingSignBlock(BlockBehaviour.Properties.of().mapColor(mapColor).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F).ignitedByLava(), woodType);
    }

    private static Block wallHangingSign(MapColor mapColor, Block block, WoodType woodType) {
        return new WallHangingSignBlock(BlockBehaviour.Properties.of().mapColor(mapColor).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F).ignitedByLava().dropsLike(block), woodType);
    }

    private static Block door(MapColor mapColor) {
        return new DoorBlock(BlockBehaviour.Properties.of().mapColor(mapColor).instrument(NoteBlockInstrument.BASS).strength(3.0F).noOcclusion().ignitedByLava().pushReaction(PushReaction.DESTROY), BlockSetType.ACACIA);
    }

    private static Block trapdoor(MapColor mapColor) {
        return new TrapDoorBlock(BlockBehaviour.Properties.of().mapColor(mapColor).instrument(NoteBlockInstrument.BASS).strength(3.0F).noOcclusion().isValidSpawn(ModBlocks::never).ignitedByLava(), BlockSetType.ACACIA);
    }


    //logs
    public static final Supplier<Block> WILLOW_LOG = regWithItem("willow_log",
            () -> log(MapColor.WOOD, MapColor.PODZOL, SoundType.WOOD));
    public static final Supplier<Block> BAOBAB_LOG = regWithItem("baobab_log",
            () -> log(MapColor.WOOD, MapColor.PODZOL, SoundType.WOOD));
    public static final Supplier<Block> EBONY_LOG = regWithItem("ebony_log",
            () -> log(MapColor.WOOD, MapColor.PODZOL, SoundType.WOOD));
    public static final Supplier<Block> FIR_LOG = regWithItem("fir_log",
            () -> log(MapColor.WOOD, MapColor.PODZOL, SoundType.WOOD));
    public static final Supplier<Block> PINE_LOG = regWithItem("pine_log",
            () -> log(MapColor.WOOD, MapColor.PODZOL, SoundType.WOOD));
    public static final Supplier<Block> CEDAR_LOG = regWithItem("cedar_log",
            () -> log(MapColor.WOOD, MapColor.PODZOL, SoundType.WOOD));
    public static final Supplier<Block> MAHOGANY_LOG = regWithItem("mahogany_log",
            () -> log(MapColor.WOOD, MapColor.PODZOL, SoundType.WOOD));
    public static final Supplier<Block> AZALEA_LOG = regWithItem("azalea_log",
            () -> log(MapColor.WOOD, MapColor.PODZOL, SoundType.WOOD));
    public static final Supplier<Block> PALM_LOG = regWithItem("palm_log",
            () -> log(MapColor.WOOD, MapColor.PODZOL, SoundType.WOOD));
    public static final Supplier<Block> MAPLE_LOG = regWithItem("maple_log",
            () -> log(MapColor.WOOD, MapColor.PODZOL, SoundType.WOOD));
    public static final Supplier<Block> MAPLE_LOG_SAPPY = regWithItem("maple_log_sappy",
            () -> log(MapColor.WOOD, MapColor.PODZOL, SoundType.WOOD));
    public static final Supplier<Block> ASPEN_LOG = regWithItem("aspen_log",
            () -> log(MapColor.WOOD, MapColor.PODZOL, SoundType.WOOD));
    public static final Supplier<Block> ASPEN_LOG_GAZING = regWithItem("aspen_log_gazing",
            () -> log(MapColor.WOOD, MapColor.PODZOL, SoundType.WOOD));
    public static final Supplier<Block> WALNUT_LOG = regWithItem("walnut_log",
            () -> log(MapColor.WOOD, MapColor.PODZOL, SoundType.WOOD));
    
    //wood
    public static final Supplier<Block> WILLOW_WOOD = regWithItem("willow_wood",
            () -> wood(MapColor.WOOD, SoundType.WOOD));
    public static final Supplier<Block> BAOBAB_WOOD = regWithItem("baobab_wood",
            () -> wood(MapColor.WOOD, SoundType.WOOD));
    public static final Supplier<Block> EBONY_WOOD = regWithItem("ebony_wood",
            () -> wood(MapColor.WOOD, SoundType.WOOD));
    public static final Supplier<Block> FIR_WOOD = regWithItem("fir_wood",
            () -> wood(MapColor.WOOD, SoundType.WOOD));
    public static final Supplier<Block> PINE_WOOD = regWithItem("pine_wood",
            () -> wood(MapColor.WOOD, SoundType.WOOD));
    public static final Supplier<Block> CEDAR_WOOD = regWithItem("cedar_wood",
            () -> wood(MapColor.WOOD, SoundType.WOOD));
    public static final Supplier<Block> MAHOGANY_WOOD = regWithItem("mahogany_wood",
            () -> wood(MapColor.WOOD, SoundType.WOOD));
    public static final Supplier<Block> AZALEA_WOOD = regWithItem("azalea_wood",
            () -> wood(MapColor.WOOD, SoundType.WOOD));
    public static final Supplier<Block> PALM_WOOD = regWithItem("palm_wood",
            () -> wood(MapColor.WOOD, SoundType.WOOD));
    public static final Supplier<Block> MAPLE_WOOD = regWithItem("maple_wood",
            () -> wood(MapColor.WOOD, SoundType.WOOD));
    public static final Supplier<Block> ASPEN_WOOD = regWithItem("aspen_wood",
            () -> wood(MapColor.WOOD, SoundType.WOOD));
    public static final Supplier<Block> ASPEN_WOOD_GAZING = regWithItem("aspen_wood_gazing",
            () -> wood(MapColor.WOOD, SoundType.WOOD));
    public static final Supplier<Block> WALNUT_WOOD = regWithItem("walnut_wood",
            () -> wood(MapColor.WOOD, SoundType.WOOD));

    //stripped logs
    public static final Supplier<Block> STRIPPED_WILLOW_LOG = regWithItem("stripped_willow_log",
            () -> log(MapColor.WOOD, MapColor.PODZOL, SoundType.WOOD));
    public static final Supplier<Block> STRIPPED_BAOBAB_LOG = regWithItem("stripped_baobab_log",
            () -> log(MapColor.WOOD, MapColor.PODZOL, SoundType.WOOD));
    public static final Supplier<Block> STRIPPED_EBONY_LOG = regWithItem("stripped_ebony_log",
            () -> log(MapColor.WOOD, MapColor.PODZOL, SoundType.WOOD));
    public static final Supplier<Block> EBONY_HEARTWOOD_LOG = regWithItem("ebony_heartwood_log",
            () -> wood(MapColor.WOOD, SoundType.WOOD));
    public static final Supplier<Block> STRIPPED_FIR_LOG = regWithItem("stripped_fir_log",
            () -> log(MapColor.WOOD, MapColor.PODZOL, SoundType.WOOD));
    public static final Supplier<Block> STRIPPED_PINE_LOG = regWithItem("stripped_pine_log",
            () -> log(MapColor.WOOD, MapColor.PODZOL, SoundType.WOOD));
    public static final Supplier<Block> STRIPPED_CEDAR_LOG = regWithItem("stripped_cedar_log",
            () -> log(MapColor.WOOD, MapColor.PODZOL, SoundType.WOOD));
    public static final Supplier<Block> STRIPPED_MAHOGANY_LOG = regWithItem("stripped_mahogany_log",
            () -> log(MapColor.WOOD, MapColor.PODZOL, SoundType.WOOD));
    public static final Supplier<Block> STRIPPED_AZALEA_LOG = regWithItem("stripped_azalea_log",
            () -> log(MapColor.WOOD, MapColor.PODZOL, SoundType.WOOD));
    public static final Supplier<Block> STRIPPED_PALM_LOG = regWithItem("stripped_palm_log",
            () -> log(MapColor.WOOD, MapColor.PODZOL, SoundType.WOOD));
    public static final Supplier<Block> STRIPPED_MAPLE_LOG = regWithItem("stripped_maple_log",
            () -> log(MapColor.WOOD, MapColor.PODZOL, SoundType.WOOD));
    public static final Supplier<Block> STRIPPED_ASPEN_LOG = regWithItem("stripped_aspen_log",
            () -> log(MapColor.WOOD, MapColor.PODZOL, SoundType.WOOD));
    public static final Supplier<Block> STRIPPED_ASPEN_LOG_GAZING = regWithItem("stripped_aspen_log_gazing",
            () -> log(MapColor.WOOD, MapColor.PODZOL, SoundType.WOOD));
    public static final Supplier<Block> STRIPPED_WALNUT_LOG = regWithItem("stripped_walnut_log",
            () -> log(MapColor.WOOD, MapColor.PODZOL, SoundType.WOOD));

    //stripped wood
    public static final Supplier<Block> STRIPPED_WILLOW_WOOD = regWithItem("stripped_willow_wood",
            () -> wood(MapColor.WOOD, SoundType.WOOD));
    public static final Supplier<Block> STRIPPED_BAOBAB_WOOD = regWithItem("stripped_baobab_wood",
            () -> wood(MapColor.WOOD, SoundType.WOOD));
    public static final Supplier<Block> STRIPPED_EBONY_WOOD = regWithItem("stripped_ebony_wood",
            () -> wood(MapColor.WOOD, SoundType.WOOD));
    public static final Supplier<Block> EBONY_HEARTWOOD = regWithItem("ebony_heartwood",
            () -> wood(MapColor.WOOD, SoundType.WOOD));
    public static final Supplier<Block> STRIPPED_FIR_WOOD = regWithItem("stripped_fir_wood",
            () -> wood(MapColor.WOOD, SoundType.WOOD));
    public static final Supplier<Block> STRIPPED_PINE_WOOD = regWithItem("stripped_pine_wood",
            () -> wood(MapColor.WOOD, SoundType.WOOD));
    public static final Supplier<Block> STRIPPED_CEDAR_WOOD = regWithItem("stripped_cedar_wood",
            () -> wood(MapColor.WOOD, SoundType.WOOD));
    public static final Supplier<Block> STRIPPED_MAHOGANY_WOOD = regWithItem("stripped_mahogany_wood",
            () -> wood(MapColor.WOOD, SoundType.WOOD));
    public static final Supplier<Block> STRIPPED_AZALEA_WOOD = regWithItem("stripped_azalea_wood",
            () -> wood(MapColor.WOOD, SoundType.WOOD));
    public static final Supplier<Block> STRIPPED_PALM_WOOD = regWithItem("stripped_palm_wood",
            () -> wood(MapColor.WOOD, SoundType.WOOD));
    public static final Supplier<Block> STRIPPED_MAPLE_WOOD = regWithItem("stripped_maple_wood",
            () -> wood(MapColor.WOOD, SoundType.WOOD));
    public static final Supplier<Block> STRIPPED_ASPEN_WOOD = regWithItem("stripped_aspen_wood",
            () -> wood(MapColor.WOOD, SoundType.WOOD));
    public static final Supplier<Block> STRIPPED_ASPEN_WOOD_GAZING = regWithItem("stripped_aspen_wood_gazing",
            () -> wood(MapColor.WOOD, SoundType.WOOD));
    public static final Supplier<Block> STRIPPED_WALNUT_WOOD = regWithItem("stripped_walnut_wood",
            () -> wood(MapColor.WOOD, SoundType.WOOD));
    
    //leaves
    public static final Supplier<Block> WILLOW_LEAVES = regWithItem("willow_leaves",
            () -> leaves(SoundType.AZALEA_LEAVES));
    public static final Supplier<Block> BAOBAB_LEAVES = regWithItem("baobab_leaves",
            () -> leaves(SoundType.AZALEA_LEAVES));
    public static final Supplier<Block> EBONY_LEAVES = regWithItem("ebony_leaves",
            () -> leaves(SoundType.AZALEA_LEAVES));
    public static final Supplier<Block> EBONY_LEAVES_FRUITING = regWithItem("ebony_leaves_fruiting",
            () -> leaves(SoundType.AZALEA_LEAVES));
    public static final Supplier<Block> FIR_LEAVES = regWithItem("fir_leaves",
            () -> leaves(SoundType.AZALEA_LEAVES));
    public static final Supplier<Block> PINE_LEAVES = regWithItem("pine_leaves",
            () -> leaves(SoundType.AZALEA_LEAVES));
    public static final Supplier<Block> CEDAR_LEAVES = regWithItem("cedar_leaves",
            () -> leaves(SoundType.AZALEA_LEAVES));
    public static final Supplier<Block> MAHOGANY_LEAVES = regWithItem("mahogany_leaves",
            () -> leaves(SoundType.AZALEA_LEAVES));
    public static final Supplier<Block> PALM_LEAVES = regWithItem("palm_leaves",
            () -> new PalmLeavesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).strength(0.2F).randomTicks().sound(SoundType.AZALEA_LEAVES).noOcclusion().isValidSpawn(ModBlocks::ocelotOrParrot).isSuffocating(ModBlocks::never).isViewBlocking(ModBlocks::never).ignitedByLava().pushReaction(PushReaction.DESTROY).isRedstoneConductor(ModBlocks::never)));
    public static final Supplier<Block> MAPLE_LEAVES = regWithItem("maple_leaves",
            () -> leaves(SoundType.AZALEA_LEAVES));
    public static final Supplier<Block> ASPEN_LEAVES = regWithItem("aspen_leaves",
            () -> leaves(SoundType.AZALEA_LEAVES));
    public static final Supplier<Block> WALNUT_LEAVES = regWithItem("walnut_leaves",
            () -> leaves(SoundType.AZALEA_LEAVES));

    //saplings
    public static final Supplier<Block> WILLOW_SAPLING = regWithItem("willow_sapling",
            () -> sapling(new WillowTreeGrower()));
    public static final Supplier<Block> BAOBAB_SAPLING = regWithItem("baobab_sapling",
            () -> sapling(new BaobabTreeGrower()));
    public static final Supplier<Block> EBONY_SAPLING = regWithItem("ebony_sapling",
            () -> sapling(new EbonyTreeGrower()));
    public static final Supplier<Block> FIR_SAPLING = regWithItem("fir_sapling",
            () -> sapling(new FirTreeGrower()));
    public static final Supplier<Block> PINE_SAPLING = regWithItem("pine_sapling",
            () -> sapling(new PineTreeGrower()));
    public static final Supplier<Block> CEDAR_SAPLING = regWithItem("cedar_sapling",
            () -> sapling(new CedarTreeGrower()));
    public static final Supplier<Block> MAHOGANY_SAPLING = regWithItem("mahogany_sapling",
            () -> sapling(new MahoganyTreeGrower()));
    public static final Supplier<Block> COCONUT = regBlock("coconut",
            () -> new CoconutBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).randomTicks().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY)));
    public static final Supplier<Block> MAPLE_SAPLING = regWithItem("maple_sapling",
            () -> sapling(new MapleTreeGrower()));
    public static final Supplier<Block> ASPEN_SAPLING = regWithItem("aspen_sapling",
            () -> sapling(new AspenTreeGrower()));
    public static final Supplier<Block> WALNUT_SAPLING = regWithItem("walnut_sapling",
            () -> sapling(new WalnutTreeGrower()));
    
    public static final Supplier<Block> POTTED_WILLOW_SAPLING = regBlock("potted_willow_sapling",
            () -> pottedSapling(WILLOW_SAPLING.get()));
    public static final Supplier<Block> POTTED_BAOBAB_SAPLING = regBlock("potted_baobab_sapling",
            () -> pottedSapling(BAOBAB_SAPLING.get()));
    public static final Supplier<Block> POTTED_EBONY_SAPLING = regBlock("potted_ebony_sapling",
            () -> pottedSapling(EBONY_SAPLING.get()));
    public static final Supplier<Block> POTTED_FIR_SAPLING = regBlock("potted_fir_sapling",
            () -> pottedSapling(FIR_SAPLING.get()));
    public static final Supplier<Block> POTTED_PINE_SAPLING = regBlock("potted_pine_sapling",
            () -> pottedSapling(PINE_SAPLING.get()));
    public static final Supplier<Block> POTTED_CEDAR_SAPLING = regBlock("potted_cedar_sapling",
            () -> pottedSapling(CEDAR_SAPLING.get()));
    public static final Supplier<Block> POTTED_MAHOGANY_SAPLING = regBlock("potted_mahogany_sapling",
            () -> pottedSapling(MAHOGANY_SAPLING.get()));
    public static final Supplier<Block> POTTED_COCONUT = regBlock("potted_coconut",
            () -> pottedSapling(COCONUT.get()));
    public static final Supplier<Block> POTTED_MAPLE_SAPLING = regBlock("potted_maple_sapling",
            () -> pottedSapling(MAPLE_SAPLING.get()));
    public static final Supplier<Block> POTTED_ASPEN_SAPLING = regBlock("potted_aspen_sapling",
            () -> pottedSapling(ASPEN_SAPLING.get()));
    public static final Supplier<Block> POTTED_WALNUT_SAPLING = regBlock("potted_walnut_sapling",
            () -> pottedSapling(WALNUT_SAPLING.get()));
    
    //planks
    public static final Supplier<Block> WILLOW_PLANKS = regWithItem("willow_planks",
            () -> planks(MapColor.WOOD, SoundType.WOOD));
    public static final Supplier<Block> BAOBAB_PLANKS = regWithItem("baobab_planks",
            () -> planks(MapColor.WOOD, SoundType.WOOD));
    public static final Supplier<Block> EBONY_PLANKS = regWithItem("ebony_planks",
            () -> planks(MapColor.WOOD, SoundType.WOOD));
    public static final Supplier<Block> FIR_PLANKS = regWithItem("fir_planks",
            () -> planks(MapColor.WOOD, SoundType.WOOD));
    public static final Supplier<Block> PINE_PLANKS = regWithItem("pine_planks",
            () -> planks(MapColor.WOOD, SoundType.WOOD));
    public static final Supplier<Block> CEDAR_PLANKS = regWithItem("cedar_planks",
            () -> planks(MapColor.WOOD, SoundType.WOOD));
    public static final Supplier<Block> MAHOGANY_PLANKS = regWithItem("mahogany_planks",
            () -> planks(MapColor.WOOD, SoundType.WOOD));
    public static final Supplier<Block> AZALEA_PLANKS = regWithItem("azalea_planks",
            () -> planks(MapColor.WOOD, SoundType.WOOD));
    public static final Supplier<Block> PALM_PLANKS = regWithItem("palm_planks",
            () -> planks(MapColor.WOOD, SoundType.WOOD));
    public static final Supplier<Block> MAPLE_PLANKS = regWithItem("maple_planks",
            () -> planks(MapColor.WOOD, SoundType.WOOD));
    public static final Supplier<Block> ASPEN_PLANKS = regWithItem("aspen_planks",
            () -> planks(MapColor.WOOD, SoundType.WOOD));
    public static final Supplier<Block> WALNUT_PLANKS = regWithItem("walnut_planks",
            () -> planks(MapColor.WOOD, SoundType.WOOD));
    
    //slabs
    public static final Supplier<Block> WILLOW_SLAB = regWithItem("willow_slab",
            () -> slab(MapColor.WOOD, SoundType.WOOD));
    public static final Supplier<Block> BAOBAB_SLAB = regWithItem("baobab_slab",
            () -> slab(MapColor.WOOD, SoundType.WOOD));
    public static final Supplier<Block> EBONY_SLAB = regWithItem("ebony_slab",
            () -> slab(MapColor.WOOD, SoundType.WOOD));
    public static final Supplier<Block> FIR_SLAB = regWithItem("fir_slab",
            () -> slab(MapColor.WOOD, SoundType.WOOD));
    public static final Supplier<Block> PINE_SLAB = regWithItem("pine_slab",
            () -> slab(MapColor.WOOD, SoundType.WOOD));
    public static final Supplier<Block> CEDAR_SLAB = regWithItem("cedar_slab",
            () -> slab(MapColor.WOOD, SoundType.WOOD));
    public static final Supplier<Block> MAHOGANY_SLAB = regWithItem("mahogany_slab",
            () -> slab(MapColor.WOOD, SoundType.WOOD));
    public static final Supplier<Block> AZALEA_SLAB = regWithItem("azalea_slab",
            () -> slab(MapColor.WOOD, SoundType.WOOD));
    public static final Supplier<Block> PALM_SLAB = regWithItem("palm_slab",
            () -> slab(MapColor.WOOD, SoundType.WOOD));
    public static final Supplier<Block> MAPLE_SLAB = regWithItem("maple_slab",
            () -> slab(MapColor.WOOD, SoundType.WOOD));
    public static final Supplier<Block> ASPEN_SLAB = regWithItem("aspen_slab",
            () -> slab(MapColor.WOOD, SoundType.WOOD));
    public static final Supplier<Block> WALNUT_SLAB = regWithItem("walnut_slab",
            () -> slab(MapColor.WOOD, SoundType.WOOD));
    
    //planks
    public static final Supplier<Block> WILLOW_STAIRS = regWithItem("willow_stairs",
            () -> stairs(WILLOW_PLANKS, BlockBehaviour.Properties.copy(WILLOW_PLANKS.get())));
    public static final Supplier<Block> BAOBAB_STAIRS = regWithItem("baobab_stairs",
            () -> stairs(BAOBAB_PLANKS, BlockBehaviour.Properties.copy(BAOBAB_PLANKS.get())));
    public static final Supplier<Block> EBONY_STAIRS = regWithItem("ebony_stairs",
            () -> stairs(EBONY_PLANKS, BlockBehaviour.Properties.copy(EBONY_PLANKS.get())));
    public static final Supplier<Block> FIR_STAIRS = regWithItem("fir_stairs",
            () -> stairs(FIR_PLANKS, BlockBehaviour.Properties.copy(FIR_PLANKS.get())));
    public static final Supplier<Block> PINE_STAIRS = regWithItem("pine_stairs",
            () -> stairs(PINE_PLANKS, BlockBehaviour.Properties.copy(PINE_PLANKS.get())));
    public static final Supplier<Block> CEDAR_STAIRS = regWithItem("cedar_stairs",
            () -> stairs(CEDAR_PLANKS, BlockBehaviour.Properties.copy(CEDAR_PLANKS.get())));
    public static final Supplier<Block> MAHOGANY_STAIRS = regWithItem("mahogany_stairs",
            () -> stairs(MAHOGANY_PLANKS, BlockBehaviour.Properties.copy(MAHOGANY_PLANKS.get())));
    public static final Supplier<Block> AZALEA_STAIRS = regWithItem("azalea_stairs",
            () -> stairs(AZALEA_PLANKS, BlockBehaviour.Properties.copy(AZALEA_PLANKS.get())));
    public static final Supplier<Block> PALM_STAIRS = regWithItem("palm_stairs",
            () -> stairs(PALM_PLANKS, BlockBehaviour.Properties.copy(PALM_PLANKS.get())));
    public static final Supplier<Block> MAPLE_STAIRS = regWithItem("maple_stairs",
            () -> stairs(MAPLE_PLANKS, BlockBehaviour.Properties.copy(MAPLE_PLANKS.get())));
    public static final Supplier<Block> ASPEN_STAIRS = regWithItem("aspen_stairs",
            () -> stairs(ASPEN_PLANKS, BlockBehaviour.Properties.copy(ASPEN_PLANKS.get())));
    public static final Supplier<Block> WALNUT_STAIRS = regWithItem("walnut_stairs",
            () -> stairs(WALNUT_PLANKS, BlockBehaviour.Properties.copy(WALNUT_PLANKS.get())));

    //fences
    public static final Supplier<Block> WILLOW_FENCE = regWithItem("willow_fence",
            () -> fence(WILLOW_PLANKS.get().defaultMapColor(), SoundType.WOOD));
    public static final Supplier<Block> BAOBAB_FENCE = regWithItem("baobab_fence",
            () -> fence(BAOBAB_PLANKS.get().defaultMapColor(), SoundType.WOOD));
    public static final Supplier<Block> EBONY_FENCE = regWithItem("ebony_fence",
            () -> fence(EBONY_PLANKS.get().defaultMapColor(), SoundType.WOOD));
    public static final Supplier<Block> FIR_FENCE = regWithItem("fir_fence",
            () -> fence(FIR_PLANKS.get().defaultMapColor(), SoundType.WOOD));
    public static final Supplier<Block> PINE_FENCE = regWithItem("pine_fence",
            () -> fence(PINE_PLANKS.get().defaultMapColor(), SoundType.WOOD));
    public static final Supplier<Block> CEDAR_FENCE = regWithItem("cedar_fence",
            () -> fence(CEDAR_PLANKS.get().defaultMapColor(), SoundType.WOOD));
    public static final Supplier<Block> MAHOGANY_FENCE = regWithItem("mahogany_fence",
            () -> fence(MAHOGANY_PLANKS.get().defaultMapColor(), SoundType.WOOD));
    public static final Supplier<Block> AZALEA_FENCE = regWithItem("azalea_fence",
            () -> fence(AZALEA_PLANKS.get().defaultMapColor(), SoundType.WOOD));
    public static final Supplier<Block> PALM_FENCE = regWithItem("palm_fence",
            () -> fence(PALM_PLANKS.get().defaultMapColor(), SoundType.WOOD));
    public static final Supplier<Block> MAPLE_FENCE = regWithItem("maple_fence",
            () -> fence(MAPLE_PLANKS.get().defaultMapColor(), SoundType.WOOD));
    public static final Supplier<Block> ASPEN_FENCE = regWithItem("aspen_fence",
            () -> fence(ASPEN_PLANKS.get().defaultMapColor(), SoundType.WOOD));
    public static final Supplier<Block> WALNUT_FENCE = regWithItem("walnut_fence",
            () -> fence(WALNUT_PLANKS.get().defaultMapColor(), SoundType.WOOD));

    //fence gates
    public static final Supplier<Block> WILLOW_FENCE_GATE = regWithItem("willow_fence_gate",
            () -> fenceGate(WILLOW_PLANKS.get().defaultMapColor(), SoundType.WOOD, ModWoodSetup.WILLOW));
    public static final Supplier<Block> BAOBAB_FENCE_GATE = regWithItem("baobab_fence_gate",
            () -> fenceGate(BAOBAB_PLANKS.get().defaultMapColor(), SoundType.WOOD, ModWoodSetup.BAOBAB));
    public static final Supplier<Block> EBONY_FENCE_GATE = regWithItem("ebony_fence_gate",
            () -> fenceGate(EBONY_PLANKS.get().defaultMapColor(), SoundType.WOOD, ModWoodSetup.EBONY));
    public static final Supplier<Block> FIR_FENCE_GATE = regWithItem("fir_fence_gate",
            () -> fenceGate(FIR_PLANKS.get().defaultMapColor(), SoundType.WOOD, ModWoodSetup.FIR));
    public static final Supplier<Block> PINE_FENCE_GATE = regWithItem("pine_fence_gate",
            () -> fenceGate(PINE_PLANKS.get().defaultMapColor(), SoundType.WOOD, ModWoodSetup.PINE));
    public static final Supplier<Block> CEDAR_FENCE_GATE = regWithItem("cedar_fence_gate",
            () -> fenceGate(CEDAR_PLANKS.get().defaultMapColor(), SoundType.WOOD, ModWoodSetup.CEDAR));
    public static final Supplier<Block> MAHOGANY_FENCE_GATE = regWithItem("mahogany_fence_gate",
            () -> fenceGate(MAHOGANY_PLANKS.get().defaultMapColor(), SoundType.WOOD, ModWoodSetup.MAHOGANY));
    public static final Supplier<Block> AZALEA_FENCE_GATE = regWithItem("azalea_fence_gate",
            () -> fenceGate(AZALEA_PLANKS.get().defaultMapColor(), SoundType.WOOD, ModWoodSetup.AZALEA));
    public static final Supplier<Block> PALM_FENCE_GATE = regWithItem("palm_fence_gate",
            () -> fenceGate(PALM_PLANKS.get().defaultMapColor(), SoundType.WOOD, ModWoodSetup.PALM));
    public static final Supplier<Block> MAPLE_FENCE_GATE = regWithItem("maple_fence_gate",
            () -> fenceGate(MAPLE_PLANKS.get().defaultMapColor(), SoundType.WOOD, ModWoodSetup.MAPLE));
    public static final Supplier<Block> ASPEN_FENCE_GATE = regWithItem("aspen_fence_gate",
            () -> fenceGate(ASPEN_PLANKS.get().defaultMapColor(), SoundType.WOOD, ModWoodSetup.ASPEN));
    public static final Supplier<Block> WALNUT_FENCE_GATE = regWithItem("walnut_fence_gate",
            () -> fenceGate(WALNUT_PLANKS.get().defaultMapColor(), SoundType.WOOD, ModWoodSetup.WALNUT));

    //buttons
    public static final Supplier<Block> WILLOW_BUTTON = regWithItem("willow_button", ModBlocks::button);
    public static final Supplier<Block> BAOBAB_BUTTON = regWithItem("baobab_button", ModBlocks::button);
    public static final Supplier<Block> EBONY_BUTTON = regWithItem("ebony_button", ModBlocks::button);
    public static final Supplier<Block> FIR_BUTTON = regWithItem("fir_button", ModBlocks::button);
    public static final Supplier<Block> PINE_BUTTON = regWithItem("pine_button", ModBlocks::button);
    public static final Supplier<Block> CEDAR_BUTTON = regWithItem("cedar_button", ModBlocks::button);
    public static final Supplier<Block> MAHOGANY_BUTTON = regWithItem("mahogany_button", ModBlocks::button);
    public static final Supplier<Block> AZALEA_BUTTON = regWithItem("azalea_button", ModBlocks::button);
    public static final Supplier<Block> PALM_BUTTON = regWithItem("palm_button", ModBlocks::button);
    public static final Supplier<Block> MAPLE_BUTTON = regWithItem("maple_button", ModBlocks::button);
    public static final Supplier<Block> ASPEN_BUTTON = regWithItem("aspen_button", ModBlocks::button);
    public static final Supplier<Block> WALNUT_BUTTON = regWithItem("walnut_button", ModBlocks::button);

    //pressure plates
    public static final Supplier<Block> WILLOW_PRESSURE_PLATE = regWithItem("willow_pressure_plate",
            () -> pressurePlate(WILLOW_PLANKS.get().defaultMapColor()));
    public static final Supplier<Block> BAOBAB_PRESSURE_PLATE = regWithItem("baobab_pressure_plate",
            () -> pressurePlate(BAOBAB_PLANKS.get().defaultMapColor()));
    public static final Supplier<Block> EBONY_PRESSURE_PLATE = regWithItem("ebony_pressure_plate",
            () -> pressurePlate(EBONY_PLANKS.get().defaultMapColor()));
    public static final Supplier<Block> FIR_PRESSURE_PLATE = regWithItem("fir_pressure_plate",
            () -> pressurePlate(FIR_PLANKS.get().defaultMapColor()));
    public static final Supplier<Block> PINE_PRESSURE_PLATE = regWithItem("pine_pressure_plate",
            () -> pressurePlate(PINE_PLANKS.get().defaultMapColor()));
    public static final Supplier<Block> CEDAR_PRESSURE_PLATE = regWithItem("cedar_pressure_plate",
            () -> pressurePlate(CEDAR_PLANKS.get().defaultMapColor()));
    public static final Supplier<Block> MAHOGANY_PRESSURE_PLATE = regWithItem("mahogany_pressure_plate",
            () -> pressurePlate(MAHOGANY_PLANKS.get().defaultMapColor()));
    public static final Supplier<Block> AZALEA_PRESSURE_PLATE = regWithItem("azalea_pressure_plate",
            () -> pressurePlate(AZALEA_PLANKS.get().defaultMapColor()));
    public static final Supplier<Block> PALM_PRESSURE_PLATE = regWithItem("palm_pressure_plate",
            () -> pressurePlate(PALM_PLANKS.get().defaultMapColor()));
    public static final Supplier<Block> MAPLE_PRESSURE_PLATE = regWithItem("maple_pressure_plate",
            () -> pressurePlate(MAPLE_PLANKS.get().defaultMapColor()));
    public static final Supplier<Block> ASPEN_PRESSURE_PLATE = regWithItem("aspen_pressure_plate",
            () -> pressurePlate(ASPEN_PLANKS.get().defaultMapColor()));
    public static final Supplier<Block> WALNUT_PRESSURE_PLATE = regWithItem("walnut_pressure_plate",
            () -> pressurePlate(WALNUT_PLANKS.get().defaultMapColor()));

    //signs
    public static final Supplier<Block> WILLOW_SIGN = regBlock("willow_sign",
            () -> standingSign(MapColor.WOOD, ModWoodSetup.WILLOW));
    public static final Supplier<Block> BAOBAB_SIGN = regBlock("baobab_sign",
            () -> standingSign(MapColor.WOOD, ModWoodSetup.BAOBAB));
    public static final Supplier<Block> EBONY_SIGN = regBlock("ebony_sign",
            () -> standingSign(MapColor.WOOD, ModWoodSetup.EBONY));
    public static final Supplier<Block> FIR_SIGN = regBlock("fir_sign",
            () -> standingSign(MapColor.WOOD, ModWoodSetup.FIR));
    public static final Supplier<Block> PINE_SIGN = regBlock("pine_sign",
            () -> standingSign(MapColor.WOOD, ModWoodSetup.PINE));
    public static final Supplier<Block> CEDAR_SIGN = regBlock("cedar_sign",
            () -> standingSign(MapColor.WOOD, ModWoodSetup.CEDAR));
    public static final Supplier<Block> MAHOGANY_SIGN = regBlock("mahogany_sign",
            () -> standingSign(MapColor.WOOD, ModWoodSetup.MAHOGANY));
    public static final Supplier<Block> AZALEA_SIGN = regBlock("azalea_sign",
            () -> standingSign(MapColor.WOOD, ModWoodSetup.AZALEA));
    public static final Supplier<Block> PALM_SIGN = regBlock("palm_sign",
            () -> standingSign(MapColor.WOOD, ModWoodSetup.PALM));
    public static final Supplier<Block> MAPLE_SIGN = regBlock("maple_sign",
            () -> standingSign(MapColor.WOOD, ModWoodSetup.MAPLE));
    public static final Supplier<Block> ASPEN_SIGN = regBlock("aspen_sign",
            () -> standingSign(MapColor.WOOD, ModWoodSetup.ASPEN));
    public static final Supplier<Block> WALNUT_SIGN = regBlock("walnut_sign",
            () -> standingSign(MapColor.WOOD, ModWoodSetup.WALNUT));
    
    public static final Supplier<Block> WILLOW_WALL_SIGN = regBlock("willow_wall_sign",
            () -> wallSign(MapColor.WOOD, WILLOW_SIGN.get(), ModWoodSetup.WILLOW));
    public static final Supplier<Block> BAOBAB_WALL_SIGN = regBlock("baobab_wall_sign",
            () -> wallSign(MapColor.WOOD, BAOBAB_SIGN.get(), ModWoodSetup.BAOBAB));
    public static final Supplier<Block> EBONY_WALL_SIGN = regBlock("ebony_wall_sign",
            () -> wallSign(MapColor.WOOD, EBONY_SIGN.get(), ModWoodSetup.EBONY));
    public static final Supplier<Block> FIR_WALL_SIGN = regBlock("fir_wall_sign",
            () -> wallSign(MapColor.WOOD, FIR_SIGN.get(), ModWoodSetup.FIR));
    public static final Supplier<Block> PINE_WALL_SIGN = regBlock("pine_wall_sign",
            () -> wallSign(MapColor.WOOD, PINE_SIGN.get(), ModWoodSetup.PINE));
    public static final Supplier<Block> CEDAR_WALL_SIGN = regBlock("cedar_wall_sign",
            () -> wallSign(MapColor.WOOD, CEDAR_SIGN.get(), ModWoodSetup.CEDAR));
    public static final Supplier<Block> MAHOGANY_WALL_SIGN = regBlock("mahogany_wall_sign",
            () -> wallSign(MapColor.WOOD, MAHOGANY_SIGN.get(), ModWoodSetup.MAHOGANY));
    public static final Supplier<Block> AZALEA_WALL_SIGN = regBlock("azalea_wall_sign",
            () -> wallSign(MapColor.WOOD, AZALEA_SIGN.get(), ModWoodSetup.AZALEA));
    public static final Supplier<Block> PALM_WALL_SIGN = regBlock("palm_wall_sign",
            () -> wallSign(MapColor.WOOD, PALM_SIGN.get(), ModWoodSetup.PALM));
    public static final Supplier<Block> MAPLE_WALL_SIGN = regBlock("maple_wall_sign",
            () -> wallSign(MapColor.WOOD, MAPLE_SIGN.get(), ModWoodSetup.MAPLE));
    public static final Supplier<Block> ASPEN_WALL_SIGN = regBlock("aspen_wall_sign",
            () -> wallSign(MapColor.WOOD, ASPEN_SIGN.get(), ModWoodSetup.ASPEN));
    public static final Supplier<Block> WALNUT_WALL_SIGN = regBlock("walnut_wall_sign",
            () -> wallSign(MapColor.WOOD, WALNUT_SIGN.get(), ModWoodSetup.WALNUT));
    
    //hanging signs
    public static final Supplier<Block> WILLOW_HANGING_SIGN = regBlock("willow_hanging_sign",
            () -> hangingSign(MapColor.WOOD, ModWoodSetup.WILLOW));
    public static final Supplier<Block> BAOBAB_HANGING_SIGN = regBlock("baobab_hanging_sign",
            () -> hangingSign(MapColor.WOOD, ModWoodSetup.BAOBAB));
    public static final Supplier<Block> EBONY_HANGING_SIGN = regBlock("ebony_hanging_sign",
            () -> hangingSign(MapColor.WOOD, ModWoodSetup.EBONY));
    public static final Supplier<Block> FIR_HANGING_SIGN = regBlock("fir_hanging_sign",
            () -> hangingSign(MapColor.WOOD, ModWoodSetup.FIR));
    public static final Supplier<Block> PINE_HANGING_SIGN = regBlock("pine_hanging_sign",
            () -> hangingSign(MapColor.WOOD, ModWoodSetup.PINE));
    public static final Supplier<Block> CEDAR_HANGING_SIGN = regBlock("cedar_hanging_sign",
            () -> hangingSign(MapColor.WOOD, ModWoodSetup.CEDAR));
    public static final Supplier<Block> MAHOGANY_HANGING_SIGN = regBlock("mahogany_hanging_sign",
            () -> hangingSign(MapColor.WOOD, ModWoodSetup.MAHOGANY));
    public static final Supplier<Block> AZALEA_HANGING_SIGN = regBlock("azalea_hanging_sign",
            () -> hangingSign(MapColor.WOOD, ModWoodSetup.AZALEA));
    public static final Supplier<Block> PALM_HANGING_SIGN = regBlock("palm_hanging_sign",
            () -> hangingSign(MapColor.WOOD, ModWoodSetup.PALM));
    public static final Supplier<Block> MAPLE_HANGING_SIGN = regBlock("maple_hanging_sign",
            () -> hangingSign(MapColor.WOOD, ModWoodSetup.MAPLE));
    public static final Supplier<Block> ASPEN_HANGING_SIGN = regBlock("aspen_hanging_sign",
            () -> hangingSign(MapColor.WOOD, ModWoodSetup.ASPEN));
    public static final Supplier<Block> WALNUT_HANGING_SIGN = regBlock("walnut_hanging_sign",
            () -> hangingSign(MapColor.WOOD, ModWoodSetup.WALNUT));

    public static final Supplier<Block> WILLOW_WALL_HANGING_SIGN = regBlock("willow_wall_hanging_sign",
            () -> wallHangingSign(MapColor.WOOD, WILLOW_SIGN.get(), ModWoodSetup.WILLOW));
    public static final Supplier<Block> BAOBAB_WALL_HANGING_SIGN = regBlock("baobab_wall_hanging_sign",
            () -> wallHangingSign(MapColor.WOOD, BAOBAB_SIGN.get(), ModWoodSetup.BAOBAB));
    public static final Supplier<Block> EBONY_WALL_HANGING_SIGN = regBlock("ebony_wall_hanging_sign",
            () -> wallHangingSign(MapColor.WOOD, EBONY_SIGN.get(), ModWoodSetup.EBONY));
    public static final Supplier<Block> FIR_WALL_HANGING_SIGN = regBlock("fir_wall_hanging_sign",
            () -> wallHangingSign(MapColor.WOOD, FIR_SIGN.get(), ModWoodSetup.FIR));
    public static final Supplier<Block> PINE_WALL_HANGING_SIGN = regBlock("pine_wall_hanging_sign",
            () -> wallHangingSign(MapColor.WOOD, PINE_SIGN.get(), ModWoodSetup.PINE));
    public static final Supplier<Block> CEDAR_WALL_HANGING_SIGN = regBlock("cedar_wall_hanging_sign",
            () -> wallHangingSign(MapColor.WOOD, CEDAR_SIGN.get(), ModWoodSetup.CEDAR));
    public static final Supplier<Block> MAHOGANY_WALL_HANGING_SIGN = regBlock("mahogany_wall_hanging_sign",
            () -> wallHangingSign(MapColor.WOOD, MAHOGANY_SIGN.get(), ModWoodSetup.MAHOGANY));
    public static final Supplier<Block> AZALEA_WALL_HANGING_SIGN = regBlock("azalea_wall_hanging_sign",
            () -> wallHangingSign(MapColor.WOOD, AZALEA_SIGN.get(), ModWoodSetup.AZALEA));
    public static final Supplier<Block> PALM_WALL_HANGING_SIGN = regBlock("palm_wall_hanging_sign",
            () -> wallHangingSign(MapColor.WOOD, PALM_SIGN.get(), ModWoodSetup.PALM));
    public static final Supplier<Block> MAPLE_WALL_HANGING_SIGN = regBlock("maple_wall_hanging_sign",
            () -> wallHangingSign(MapColor.WOOD, MAPLE_SIGN.get(), ModWoodSetup.MAPLE));
    public static final Supplier<Block> ASPEN_WALL_HANGING_SIGN = regBlock("aspen_wall_hanging_sign",
            () -> wallHangingSign(MapColor.WOOD, ASPEN_SIGN.get(), ModWoodSetup.ASPEN));
    public static final Supplier<Block> WALNUT_WALL_HANGING_SIGN = regBlock("walnut_wall_hanging_sign",
            () -> wallHangingSign(MapColor.WOOD, WALNUT_SIGN.get(), ModWoodSetup.WALNUT));
    
    //doors
    public static final Supplier<Block> WILLOW_DOOR = regWithItem("willow_door",
            () -> door(MapColor.WOOD));
    public static final Supplier<Block> BAOBAB_DOOR = regWithItem("baobab_door",
            () -> door(MapColor.WOOD));
    public static final Supplier<Block> EBONY_DOOR = regWithItem("ebony_door",
            () -> door(MapColor.WOOD));
    public static final Supplier<Block> FIR_DOOR = regWithItem("fir_door",
            () -> door(MapColor.WOOD));
    public static final Supplier<Block> PINE_DOOR = regWithItem("pine_door",
            () -> door(MapColor.WOOD));
    public static final Supplier<Block> CEDAR_DOOR = regWithItem("cedar_door",
            () -> door(MapColor.WOOD));
    public static final Supplier<Block> MAHOGANY_DOOR = regWithItem("mahogany_door",
            () -> door(MapColor.WOOD));
    public static final Supplier<Block> AZALEA_DOOR = regWithItem("azalea_door",
            () -> door(MapColor.WOOD));
    public static final Supplier<Block> PALM_DOOR = regWithItem("palm_door",
            () -> door(MapColor.WOOD));
    public static final Supplier<Block> MAPLE_DOOR = regWithItem("maple_door",
            () -> door(MapColor.WOOD));
    public static final Supplier<Block> ASPEN_DOOR = regWithItem("aspen_door",
            () -> door(MapColor.WOOD));
    public static final Supplier<Block> WALNUT_DOOR = regWithItem("walnut_door",
            () -> door(MapColor.WOOD));

    //trapdoors
    public static final Supplier<Block> WILLOW_TRAPDOOR = regWithItem("willow_trapdoor",
            () -> trapdoor(MapColor.WOOD));
    public static final Supplier<Block> BAOBAB_TRAPDOOR = regWithItem("baobab_trapdoor",
            () -> trapdoor(MapColor.WOOD));
    public static final Supplier<Block> EBONY_TRAPDOOR = regWithItem("ebony_trapdoor",
            () -> trapdoor(MapColor.WOOD));
    public static final Supplier<Block> FIR_TRAPDOOR = regWithItem("fir_trapdoor",
            () -> trapdoor(MapColor.WOOD));
    public static final Supplier<Block> PINE_TRAPDOOR = regWithItem("pine_trapdoor",
            () -> trapdoor(MapColor.WOOD));
    public static final Supplier<Block> CEDAR_TRAPDOOR = regWithItem("cedar_trapdoor",
            () -> trapdoor(MapColor.WOOD));
    public static final Supplier<Block> MAHOGANY_TRAPDOOR = regWithItem("mahogany_trapdoor",
            () -> trapdoor(MapColor.WOOD));
    public static final Supplier<Block> AZALEA_TRAPDOOR = regWithItem("azalea_trapdoor",
            () -> trapdoor(MapColor.WOOD));
    public static final Supplier<Block> PALM_TRAPDOOR = regWithItem("palm_trapdoor",
            () -> trapdoor(MapColor.WOOD));
    public static final Supplier<Block> MAPLE_TRAPDOOR = regWithItem("maple_trapdoor",
            () -> trapdoor(MapColor.WOOD));
    public static final Supplier<Block> ASPEN_TRAPDOOR = regWithItem("aspen_trapdoor",
            () -> trapdoor(MapColor.WOOD));
    public static final Supplier<Block> WALNUT_TRAPDOOR = regWithItem("walnut_trapdoor",
            () -> trapdoor(MapColor.WOOD));


}
