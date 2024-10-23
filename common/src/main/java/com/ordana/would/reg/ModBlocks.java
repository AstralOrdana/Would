package com.ordana.would.reg;

import com.ordana.would.Blocks.ModButtonBlock;
import com.ordana.would.Would;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
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


    private static RotatedPillarBlock log(MapColor topMapColor, MapColor sideMapColor, SoundType soundType) {
        return new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor((blockState) -> blockState.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(soundType).ignitedByLava());
    }
    
    private static LeavesBlock leaves(SoundType type) {
        return new LeavesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).strength(0.2F).randomTicks().sound(type).noOcclusion().isValidSpawn(ModBlocks::ocelotOrParrot).isSuffocating(ModBlocks::never).isViewBlocking(ModBlocks::never).ignitedByLava().pushReaction(PushReaction.DESTROY).isRedstoneConductor(ModBlocks::never));
    }

    private static Block planks(MapColor mapColor, SoundType soundType) {
        return new Block(BlockBehaviour.Properties.of().mapColor(mapColor).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(soundType).ignitedByLava());
    }
    
    private static ButtonBlock woodenButton(BlockSetType setType, FeatureFlag... requiredFeatures) {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.of().noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY);
        if (requiredFeatures.length > 0) {
            properties = properties.requiredFeatures(requiredFeatures);
        }

        return new ModButtonBlock(properties, setType, 30, true);
    }


    // logs
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
    public static final Supplier<Block> ASPEN_LOG = regWithItem("aspen_log",
            () -> log(MapColor.WOOD, MapColor.PODZOL, SoundType.WOOD));
    public static final Supplier<Block> ASPEN_LOG_GAZING = regWithItem("aspen_log_gazing",
            () -> log(MapColor.WOOD, MapColor.PODZOL, SoundType.WOOD));
    public static final Supplier<Block> WALNUT_LOG = regWithItem("walnut_log",
            () -> log(MapColor.WOOD, MapColor.PODZOL, SoundType.WOOD));
    
    //leaves
    public static final Supplier<Block> WILLOW_LEAVES = regWithItem("willow_leaves",
            () -> leaves(SoundType.AZALEA_LEAVES));
    public static final Supplier<Block> BAOBAB_LEAVES = regWithItem("baobab_leaves",
            () -> leaves(SoundType.AZALEA_LEAVES));
    public static final Supplier<Block> EBONY_LEAVES = regWithItem("ebony_leaves",
            () -> leaves(SoundType.AZALEA_LEAVES));
    public static final Supplier<Block> FIR_LEAVES = regWithItem("fir_leaves",
            () -> leaves(SoundType.AZALEA_LEAVES));
    public static final Supplier<Block> PINE_LEAVES = regWithItem("pine_leaves",
            () -> leaves(SoundType.AZALEA_LEAVES));
    public static final Supplier<Block> CEDAR_LEAVES = regWithItem("cedar_leaves",
            () -> leaves(SoundType.AZALEA_LEAVES));
    public static final Supplier<Block> MAHOGANY_LEAVES = regWithItem("mahogany_leaves",
            () -> leaves(SoundType.AZALEA_LEAVES));
    public static final Supplier<Block> AZALEA_LEAVES = regWithItem("azalea_leaves",
            () -> leaves(SoundType.AZALEA_LEAVES));
    public static final Supplier<Block> PALM_LEAVES = regWithItem("palm_leaves",
            () -> leaves(SoundType.AZALEA_LEAVES));
    public static final Supplier<Block> MAPLE_LEAVES = regWithItem("maple_leaves",
            () -> leaves(SoundType.AZALEA_LEAVES));
    public static final Supplier<Block> ASPEN_LEAVES = regWithItem("aspen_leaves",
            () -> leaves(SoundType.AZALEA_LEAVES));
    public static final Supplier<Block> WALNUT_LEAVES = regWithItem("walnut_leaves",
            () -> leaves(SoundType.AZALEA_LEAVES));
    
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

}
