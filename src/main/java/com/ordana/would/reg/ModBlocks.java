package com.ordana.would.reg;

import com.ordana.would.Would;
import com.ordana.would.blocks.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.Function;

import static com.ordana.would.reg.BlockFactories.*;

public interface ModBlocks {

    static void init() {
    }


    Block HANGING_WILLOW_LEAVES = regWithItem(
        "hanging_willow_leaves",
        HangingWillowLeavesBlock::new, BlockBehaviour.Properties.of()
            .mapColor(MapColor.PLANT)
            .noCollision()
            .randomTicks()
            .strength(0.2F)
            .sound(SoundType.VINE)
            .ignitedByLava()
            .pushReaction(PushReaction.DESTROY)
        ,
        true
    );

    private static Boolean ocelotOrParrot(BlockState state, BlockGetter blockGetter, BlockPos pos, EntityType<?> entity) {
        return entity == EntityType.OCELOT || entity == EntityType.PARROT;
    }

    private static <T extends Block> T regBlock(String name, T block) {
        return Registry.register(BuiltInRegistries.BLOCK, Would.res(name), block);
    }

    private static void regBlockItem(String name, Block blockSup, Item.Properties properties) {
        Registry.register(BuiltInRegistries.ITEM, Would.res(name), new BlockItem(blockSup, properties));
    }

    private static <T extends Block> T regWithItem(String name, T blockFactory) {
        T block = regBlock(name, blockFactory);
        regBlockItem(name, block, new Item.Properties());
        return block;
    }

    public static <T extends Block> T regWithItem(String name, Function<BlockBehaviour.Properties, T> blockFactory, BlockBehaviour.Properties settings, boolean shouldRegisterItem) {
        // Create a registry key for the block
        ResourceKey<Block> blockKey = keyOfBlock(name);
        // Create the block instance
        T block = blockFactory.apply(settings.setId(blockKey));

        // Sometimes, you may not want to register an item for the block.
        // Eg: if it's a technical block like `minecraft:moving_piston` or `minecraft:end_gateway`
        if (shouldRegisterItem) {
            // Items need to be registered with a different type of registry key, but the ID
            // can be the same.
            ResourceKey<Item> itemKey = keyOfItem(name);

            BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(itemKey).useBlockDescriptionPrefix());
            Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
        }

        return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
    }

    static <T extends Block> T regWithItem(String name, Function<BlockBehaviour.Properties, T> blockFactory, BlockBehaviour.Properties settings) {
        return regWithItem(name, blockFactory, settings, true);
    }

    private static ResourceKey<Block> keyOfBlock(String name) {
        return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Would.MOD_ID, name));
    }

    private static ResourceKey<Item> keyOfItem(String name) {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Would.MOD_ID, name));
    }



    static boolean never(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, EntityType<?> entityType) {
        return false;
    }
    
    //logs
    Block WILLOW_LOG = regWithItem("willow_log",
            log(MapColor.WARPED_NYLIUM, MapColor.PODZOL, SoundType.WOOD));
    Block BAOBAB_LOG = regWithItem("baobab_log",
            log(MapColor.WOOD, MapColor.WOOD, SoundType.WOOD));
    Block EBONY_LOG = regWithItem("ebony_log",
            log(MapColor.TERRACOTTA_BLACK, MapColor.PODZOL, SoundType.WOOD));
    Block FIR_LOG = regWithItem("fir_log",
            log(MapColor.WOOD, MapColor.WOOD, SoundType.WOOD));
    Block PINE_LOG = regWithItem("pine_log",
            log(MapColor.PODZOL, MapColor.PODZOL, SoundType.WOOD));
    Block CEDAR_LOG = regWithItem("cedar_log",
            log(MapColor.TERRACOTTA_YELLOW, MapColor.TERRACOTTA_YELLOW, SoundType.WOOD));
    Block MAHOGANY_LOG = regWithItem("mahogany_log",
            log(MapColor.TERRACOTTA_RED, MapColor.TERRACOTTA_RED, SoundType.WOOD));
    Block AZALEA_LOG = regWithItem("azalea_log",
            log(MapColor.TERRACOTTA_GREEN, MapColor.PODZOL, SoundType.WOOD));
    Block PALM_LOG = regWithItem("palm_log",
            log(MapColor.NETHER, MapColor.TERRACOTTA_LIGHT_GRAY, SoundType.WOOD));
    Block MAPLE_LOG = regWithItem("maple_log",
            log(MapColor.WOOD, MapColor.WOOD, SoundType.WOOD));
    Block MAPLE_LOG_SAPPY = regWithItem("maple_log_sappy",
            log(MapColor.WOOD, MapColor.WOOD, SoundType.WOOD));
    Block ASPEN_LOG = regWithItem("aspen_log",
            log(MapColor.WOOD, MapColor.TERRACOTTA_WHITE, SoundType.WOOD));
    Block ASPEN_LOG_GAZING = regWithItem("aspen_log_gazing",
            log(MapColor.WOOD, MapColor.TERRACOTTA_WHITE, SoundType.WOOD));
    Block WALNUT_LOG = regWithItem("walnut_log",
            log(MapColor.COLOR_BROWN, MapColor.COLOR_BROWN, SoundType.WOOD));
    Block BLUE_SPRUCE_LOG = regWithItem("blue_spruce_log",
            log(MapColor.LAPIS, MapColor.TERRACOTTA_LIGHT_BLUE, SoundType.WOOD));
    
    //wood
    Block WILLOW_WOOD = regWithItem("willow_wood",
            wood(MapColor.PODZOL, SoundType.WOOD));
    Block BAOBAB_WOOD = regWithItem("baobab_wood",
            wood(MapColor.WOOD, SoundType.WOOD));
    Block EBONY_WOOD = regWithItem("ebony_wood",
            wood(MapColor.PODZOL, SoundType.WOOD));
    Block FIR_WOOD = regWithItem("fir_wood",
            wood(MapColor.WOOD, SoundType.WOOD));
    Block PINE_WOOD = regWithItem("pine_wood",
            wood(MapColor.PODZOL, SoundType.WOOD));
    Block CEDAR_WOOD = regWithItem("cedar_wood",
            wood(MapColor.TERRACOTTA_YELLOW, SoundType.WOOD));
    Block MAHOGANY_WOOD = regWithItem("mahogany_wood",
            wood(MapColor.TERRACOTTA_RED, SoundType.WOOD));
    Block AZALEA_WOOD = regWithItem("azalea_wood",
            wood(MapColor.PODZOL, SoundType.WOOD));
    Block PALM_WOOD = regWithItem("palm_wood",
            wood(MapColor.TERRACOTTA_LIGHT_GRAY, SoundType.WOOD));
    Block MAPLE_WOOD = regWithItem("maple_wood",
            wood(MapColor.WOOD, SoundType.WOOD));
    Block ASPEN_WOOD = regWithItem("aspen_wood",
            wood(MapColor.TERRACOTTA_WHITE, SoundType.WOOD));
    Block ASPEN_WOOD_GAZING = regWithItem("aspen_wood_gazing",
            wood(MapColor.TERRACOTTA_WHITE, SoundType.WOOD));
    Block WALNUT_WOOD = regWithItem("walnut_wood",
            wood(MapColor.COLOR_BROWN, SoundType.WOOD));
    Block BLUE_SPRUCE_WOOD = regWithItem("blue_spruce_wood",
            wood(MapColor.TERRACOTTA_LIGHT_BLUE, SoundType.WOOD));

    //stripped logs
    Block STRIPPED_WILLOW_LOG = regWithItem("stripped_willow_log",
            log(MapColor.WARPED_NYLIUM, MapColor.WARPED_NYLIUM, SoundType.WOOD));
    Block STRIPPED_BAOBAB_LOG = regWithItem("stripped_baobab_log",
            log(MapColor.WOOD, MapColor.WOOD, SoundType.WOOD));
    Block STRIPPED_EBONY_LOG = regWithItem("stripped_ebony_log",
            log(MapColor.TERRACOTTA_BLACK, MapColor.TERRACOTTA_BLACK, SoundType.WOOD));
    Block EBONY_HEARTWOOD_LOG = regWithItem("ebony_heartwood_log",
            wood(MapColor.WOOD, SoundType.WOOD));
    Block STRIPPED_FIR_LOG = regWithItem("stripped_fir_log",
            log(MapColor.WOOD, MapColor.WOOD, SoundType.WOOD));
    Block STRIPPED_PINE_LOG = regWithItem("stripped_pine_log",
            log(MapColor.PODZOL, MapColor.PODZOL, SoundType.WOOD));
    Block STRIPPED_CEDAR_LOG = regWithItem("stripped_cedar_log",
            log(MapColor.TERRACOTTA_YELLOW, MapColor.TERRACOTTA_YELLOW, SoundType.WOOD));
    Block STRIPPED_MAHOGANY_LOG = regWithItem("stripped_mahogany_log",
            log(MapColor.TERRACOTTA_RED, MapColor.TERRACOTTA_RED, SoundType.WOOD));
    Block STRIPPED_AZALEA_LOG = regWithItem("stripped_azalea_log",
            log(MapColor.TERRACOTTA_GREEN, MapColor.TERRACOTTA_GREEN, SoundType.WOOD));
    Block STRIPPED_PALM_LOG = regWithItem("stripped_palm_log",
            log(MapColor.NETHER, MapColor.NETHER, SoundType.WOOD));
    Block STRIPPED_MAPLE_LOG = regWithItem("stripped_maple_log",
            log(MapColor.WOOD, MapColor.WOOD, SoundType.WOOD));
    Block STRIPPED_ASPEN_LOG = regWithItem("stripped_aspen_log",
            log(MapColor.WOOD, MapColor.WOOD, SoundType.WOOD));
    Block STRIPPED_ASPEN_LOG_GAZING = regWithItem("stripped_aspen_log_gazing",
            log(MapColor.WOOD, MapColor.WOOD, SoundType.WOOD));
    Block STRIPPED_WALNUT_LOG = regWithItem("stripped_walnut_log",
            log(MapColor.COLOR_BROWN, MapColor.COLOR_BROWN, SoundType.WOOD));
    Block STRIPPED_BLUE_SPRUCE_LOG = regWithItem("stripped_blue_spruce_log",
            log(MapColor.LAPIS, MapColor.LAPIS, SoundType.WOOD));

    //stripped wood
    Block STRIPPED_WILLOW_WOOD = regWithItem("stripped_willow_wood",
            wood(MapColor.WARPED_NYLIUM, SoundType.WOOD));
    Block STRIPPED_BAOBAB_WOOD = regWithItem("stripped_baobab_wood",
            wood(MapColor.WOOD, SoundType.WOOD));
    Block STRIPPED_EBONY_WOOD = regWithItem("stripped_ebony_wood",
            wood(MapColor.PODZOL, SoundType.WOOD));
    Block EBONY_HEARTWOOD = regWithItem("ebony_heartwood",
            wood(MapColor.TERRACOTTA_BLACK, SoundType.WOOD));
    Block STRIPPED_FIR_WOOD = regWithItem("stripped_fir_wood",
            wood(MapColor.WOOD, SoundType.WOOD));
    Block STRIPPED_PINE_WOOD = regWithItem("stripped_pine_wood",
            wood(MapColor.PODZOL, SoundType.WOOD));
    Block STRIPPED_CEDAR_WOOD = regWithItem("stripped_cedar_wood",
            wood(MapColor.TERRACOTTA_YELLOW, SoundType.WOOD));
    Block STRIPPED_MAHOGANY_WOOD = regWithItem("stripped_mahogany_wood",
            wood(MapColor.TERRACOTTA_RED, SoundType.WOOD));
    Block STRIPPED_AZALEA_WOOD = regWithItem("stripped_azalea_wood",
            wood(MapColor.TERRACOTTA_GREEN, SoundType.WOOD));
    Block STRIPPED_PALM_WOOD = regWithItem("stripped_palm_wood",
            wood(MapColor.NETHER, SoundType.WOOD));
    Block STRIPPED_MAPLE_WOOD = regWithItem("stripped_maple_wood",
            wood(MapColor.WOOD, SoundType.WOOD));
    Block STRIPPED_ASPEN_WOOD = regWithItem("stripped_aspen_wood",
            wood(MapColor.WOOD, SoundType.WOOD));
    Block STRIPPED_ASPEN_WOOD_GAZING = regWithItem("stripped_aspen_wood_gazing",
            wood(MapColor.WOOD, SoundType.WOOD));
    Block STRIPPED_WALNUT_WOOD = regWithItem("stripped_walnut_wood",
            wood(MapColor.COLOR_BROWN, SoundType.WOOD));
    Block STRIPPED_BLUE_SPRUCE_WOOD = regWithItem("stripped_blue_spruce_wood",
            wood(MapColor.LAPIS, SoundType.WOOD));
    
    //leaves
    Block WILLOW_LEAVES = regWithItem("willow_leaves",
            leaves(SoundType.AZALEA_LEAVES));
    Block BAOBAB_LEAVES = regWithItem("baobab_leaves",
            leaves(SoundType.AZALEA_LEAVES));
    Block EBONY_LEAVES = regWithItem("ebony_leaves",
            leaves(SoundType.AZALEA_LEAVES));
    Block EBONY_LEAVES_FRUITING = regWithItem("ebony_leaves_fruiting",
            leaves(MapColor.GOLD, SoundType.AZALEA_LEAVES));
    Block FIR_LEAVES = regWithItem("fir_leaves",
            leaves(SoundType.AZALEA_LEAVES));
    Block PINE_LEAVES = regWithItem("pine_leaves",
            leaves(SoundType.AZALEA_LEAVES));
    Block CEDAR_LEAVES = regWithItem("cedar_leaves",
            leaves(SoundType.AZALEA_LEAVES));
    Block MAHOGANY_LEAVES = regWithItem("mahogany_leaves",
            leaves(SoundType.AZALEA_LEAVES));
    Block PALM_LEAVES = regWithItem("palm_leaves",
            new PalmLeavesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).strength(0.2F).randomTicks().sound(SoundType.AZALEA_LEAVES).noOcclusion().isValidSpawn(ModBlocks::ocelotOrParrot).isSuffocating(BlockFactories::never).isViewBlocking(BlockFactories::never).ignitedByLava().pushReaction(PushReaction.DESTROY).isRedstoneConductor(BlockFactories::never)));
    Block MAPLE_LEAVES = regWithItem("maple_leaves",
            leaves(MapColor.CRIMSON_STEM, SoundType.AZALEA_LEAVES));
    Block ASPEN_LEAVES = regWithItem("aspen_leaves",
            leaves(SoundType.AZALEA_LEAVES));
    Block WALNUT_LEAVES = regWithItem("walnut_leaves",
            leaves(SoundType.AZALEA_LEAVES));
    Block BLUE_SPRUCE_LEAVES = regWithItem("blue_spruce_leaves",
            leaves(MapColor.ICE, SoundType.AZALEA_LEAVES));

    //saplings
    Block WILLOW_SAPLING = regWithItem("willow_sapling",
            sapling(ModTreeGrowers.WILLOW));
    Block BAOBAB_SAPLING = regWithItem("baobab_sapling",
            sapling(ModTreeGrowers.BAOBAB));
    Block EBONY_SAPLING = regWithItem("ebony_sapling",
            sapling(ModTreeGrowers.EBONY));
    Block FIR_SAPLING = regWithItem("fir_sapling",
            sapling(ModTreeGrowers.FIR));
    Block PINE_SAPLING = regWithItem("pine_sapling",
            sapling(ModTreeGrowers.PINE));
    Block CEDAR_SAPLING = regWithItem("cedar_sapling",
            sapling(ModTreeGrowers.CEDAR));
    Block MAHOGANY_SAPLING = regWithItem("mahogany_sapling",
            sapling(ModTreeGrowers.MAHOGANY));
    Block COCONUT = regBlock("coconut",
            new CoconutBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).randomTicks().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY)));
    Block MAPLE_SAPLING = regWithItem("maple_sapling",
            sapling(ModTreeGrowers.MAPLE));
    Block ASPEN_SAPLING = regWithItem("aspen_sapling",
            sapling(ModTreeGrowers.ASPEN));
    Block WALNUT_SAPLING = regWithItem("walnut_sapling",
            sapling(ModTreeGrowers.WALNUT));
    Block BLUE_SPRUCE_SAPLING = regWithItem("blue_spruce_sapling",
            sapling(ModTreeGrowers.BLUE_SPRUCE));

    Block POTTED_WILLOW_SAPLING = regBlock("potted_willow_sapling",
            pottedSapling(WILLOW_SAPLING));
    Block POTTED_BAOBAB_SAPLING = regBlock("potted_baobab_sapling",
            pottedSapling(BAOBAB_SAPLING));
    Block POTTED_EBONY_SAPLING = regBlock("potted_ebony_sapling",
            pottedSapling(EBONY_SAPLING));
    Block POTTED_FIR_SAPLING = regBlock("potted_fir_sapling",
            pottedSapling(FIR_SAPLING));
    Block POTTED_PINE_SAPLING = regBlock("potted_pine_sapling",
            pottedSapling(PINE_SAPLING));
    Block POTTED_CEDAR_SAPLING = regBlock("potted_cedar_sapling",
            pottedSapling(CEDAR_SAPLING));
    Block POTTED_MAHOGANY_SAPLING = regBlock("potted_mahogany_sapling",
            pottedSapling(MAHOGANY_SAPLING));
    Block POTTED_COCONUT = regBlock("potted_coconut",
            pottedSapling(COCONUT));
    Block POTTED_MAPLE_SAPLING = regBlock("potted_maple_sapling",
            pottedSapling(MAPLE_SAPLING));
    Block POTTED_ASPEN_SAPLING = regBlock("potted_aspen_sapling",
            pottedSapling(ASPEN_SAPLING));
    Block POTTED_WALNUT_SAPLING = regBlock("potted_walnut_sapling",
            pottedSapling(WALNUT_SAPLING));
    Block POTTED_BLUE_SPRUCE_SAPLING = regBlock("potted_blue_spruce_sapling",
            pottedSapling(BLUE_SPRUCE_SAPLING));


}
