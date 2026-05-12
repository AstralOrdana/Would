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
import org.apache.commons.compress.utils.Lists;

import java.util.List;
import java.util.function.Function;

import static com.ordana.would.reg.BlockFactories.*;

public interface ModBlocks {


    List<Block> ALL_SIGNS = Lists.newArrayList();
    List<Block> ALL_HANGING_SIGNS = Lists.newArrayList();

    static void init() {
    }


    Block HANGING_WILLOW_LEAVES = regBlock(
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

    static Boolean ocelotOrParrot(BlockState state, BlockGetter blockGetter, BlockPos pos, EntityType<?> entity) {
        return entity == EntityType.OCELOT || entity == EntityType.PARROT;
    }

    static <T extends Block> T regBlock(String name, Function<BlockBehaviour.Properties, T> blockFactory, BlockBehaviour.Properties settings, boolean shouldRegisterItem) {
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

    static <T extends Block> T regBlock(String name, Function<BlockBehaviour.Properties, T> blockFactory, BlockBehaviour.Properties settings) {
        return regBlock(name, blockFactory, settings, true);
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
    Block WILLOW_LOG = regLog("willow_log",
            logProperties(MapColor.WARPED_NYLIUM, MapColor.PODZOL, SoundType.WOOD));
    Block BAOBAB_LOG = regLog("baobab_log",
            logProperties(MapColor.WOOD, MapColor.WOOD, SoundType.WOOD));
    Block EBONY_LOG = regLog("ebony_log",
            logProperties(MapColor.TERRACOTTA_BLACK, MapColor.PODZOL, SoundType.WOOD));
    Block FIR_LOG = regLog("fir_log",
            logProperties(MapColor.WOOD, MapColor.WOOD, SoundType.WOOD));
    Block PINE_LOG = regLog("pine_log",
            logProperties(MapColor.PODZOL, MapColor.PODZOL, SoundType.WOOD));
    Block CEDAR_LOG = regLog("cedar_log",
            logProperties(MapColor.TERRACOTTA_YELLOW, MapColor.TERRACOTTA_YELLOW, SoundType.WOOD));
    Block MAHOGANY_LOG = regLog("mahogany_log",
            logProperties(MapColor.TERRACOTTA_RED, MapColor.TERRACOTTA_RED, SoundType.WOOD));
    Block AZALEA_LOG = regLog("azalea_log",
            logProperties(MapColor.TERRACOTTA_GREEN, MapColor.PODZOL, SoundType.WOOD));
    Block PALM_LOG = regLog("palm_log",
            logProperties(MapColor.NETHER, MapColor.TERRACOTTA_LIGHT_GRAY, SoundType.WOOD));
    Block MAPLE_LOG = regLog("maple_log",
            logProperties(MapColor.WOOD, MapColor.WOOD, SoundType.WOOD));
    Block MAPLE_LOG_SAPPY = regLog("maple_log_sappy",
            logProperties(MapColor.WOOD, MapColor.WOOD, SoundType.WOOD));
    Block ASPEN_LOG = regLog("aspen_log",
            logProperties(MapColor.WOOD, MapColor.TERRACOTTA_WHITE, SoundType.WOOD));
    Block ASPEN_LOG_GAZING = regLog("aspen_log_gazing",
            logProperties(MapColor.WOOD, MapColor.TERRACOTTA_WHITE, SoundType.WOOD));
    Block WALNUT_LOG = regLog("walnut_log",
            logProperties(MapColor.COLOR_BROWN, MapColor.COLOR_BROWN, SoundType.WOOD));
    Block BLUE_SPRUCE_LOG = regLog("blue_spruce_log",
            logProperties(MapColor.LAPIS, MapColor.TERRACOTTA_LIGHT_BLUE, SoundType.WOOD));

    static Block regLog(String blueSpruceLog, BlockBehaviour.Properties Properties) {
        return regBlock(blueSpruceLog, RotatedPillarBlock::new, Properties);
    }

    //wood
    Block WILLOW_WOOD = regLog("willow_wood",
            woodProperties(MapColor.PODZOL, SoundType.WOOD));
    Block BAOBAB_WOOD = regLog("baobab_wood",
            woodProperties(MapColor.WOOD, SoundType.WOOD));
    Block EBONY_WOOD = regLog("ebony_wood",
            woodProperties(MapColor.PODZOL, SoundType.WOOD));
    Block FIR_WOOD = regLog("fir_wood",
            woodProperties(MapColor.WOOD, SoundType.WOOD));
    Block PINE_WOOD = regLog("pine_wood",
            woodProperties(MapColor.PODZOL, SoundType.WOOD));
    Block CEDAR_WOOD = regLog("cedar_wood",
            woodProperties(MapColor.TERRACOTTA_YELLOW, SoundType.WOOD));
    Block MAHOGANY_WOOD = regLog("mahogany_wood",
            woodProperties(MapColor.TERRACOTTA_RED, SoundType.WOOD));
    Block AZALEA_WOOD = regLog("azalea_wood",
            woodProperties(MapColor.PODZOL, SoundType.WOOD));
    Block PALM_WOOD = regLog("palm_wood",
            woodProperties(MapColor.TERRACOTTA_LIGHT_GRAY, SoundType.WOOD));
    Block MAPLE_WOOD = regLog("maple_wood",
            woodProperties(MapColor.WOOD, SoundType.WOOD));
    Block MAPLE_WOOD_SAPPY = regLog("maple_wood_sappy",
            woodProperties(MapColor.WOOD, SoundType.WOOD));
    Block ASPEN_WOOD = regLog("aspen_wood",
            woodProperties(MapColor.TERRACOTTA_WHITE, SoundType.WOOD));
    Block ASPEN_WOOD_GAZING = regLog("aspen_wood_gazing",
            woodProperties(MapColor.TERRACOTTA_WHITE, SoundType.WOOD));
    Block WALNUT_WOOD = regLog("walnut_wood",
            woodProperties(MapColor.COLOR_BROWN, SoundType.WOOD));
    Block BLUE_SPRUCE_WOOD = regLog("blue_spruce_wood",
            woodProperties(MapColor.TERRACOTTA_LIGHT_BLUE, SoundType.WOOD));

    //stripped logs
    Block STRIPPED_WILLOW_LOG = regLog("stripped_willow_log",
            logProperties(MapColor.WARPED_NYLIUM, MapColor.WARPED_NYLIUM, SoundType.WOOD));
    Block STRIPPED_BAOBAB_LOG = regLog("stripped_baobab_log",
            logProperties(MapColor.WOOD, MapColor.WOOD, SoundType.WOOD));
    Block STRIPPED_EBONY_LOG = regLog("stripped_ebony_log",
            logProperties(MapColor.TERRACOTTA_BLACK, MapColor.TERRACOTTA_BLACK, SoundType.WOOD));
    Block EBONY_HEARTWOOD_LOG = regLog("ebony_heartwood_log",
            woodProperties(MapColor.WOOD, SoundType.WOOD));
    Block STRIPPED_FIR_LOG = regLog("stripped_fir_log",
            logProperties(MapColor.WOOD, MapColor.WOOD, SoundType.WOOD));
    Block STRIPPED_PINE_LOG = regLog("stripped_pine_log",
            logProperties(MapColor.PODZOL, MapColor.PODZOL, SoundType.WOOD));
    Block STRIPPED_CEDAR_LOG = regLog("stripped_cedar_log",
            logProperties(MapColor.TERRACOTTA_YELLOW, MapColor.TERRACOTTA_YELLOW, SoundType.WOOD));
    Block STRIPPED_MAHOGANY_LOG = regLog("stripped_mahogany_log",
            logProperties(MapColor.TERRACOTTA_RED, MapColor.TERRACOTTA_RED, SoundType.WOOD));
    Block STRIPPED_AZALEA_LOG = regLog("stripped_azalea_log",
            logProperties(MapColor.TERRACOTTA_GREEN, MapColor.TERRACOTTA_GREEN, SoundType.WOOD));
    Block STRIPPED_PALM_LOG = regLog("stripped_palm_log",
            logProperties(MapColor.NETHER, MapColor.NETHER, SoundType.WOOD));
    Block STRIPPED_MAPLE_LOG = regLog("stripped_maple_log",
            logProperties(MapColor.WOOD, MapColor.WOOD, SoundType.WOOD));
    Block STRIPPED_ASPEN_LOG = regLog("stripped_aspen_log",
            logProperties(MapColor.WOOD, MapColor.WOOD, SoundType.WOOD));
    Block STRIPPED_ASPEN_LOG_GAZING = regLog("stripped_aspen_log_gazing",
            logProperties(MapColor.WOOD, MapColor.WOOD, SoundType.WOOD));
    Block STRIPPED_WALNUT_LOG = regLog("stripped_walnut_log",
            logProperties(MapColor.COLOR_BROWN, MapColor.COLOR_BROWN, SoundType.WOOD));
    Block STRIPPED_BLUE_SPRUCE_LOG = regLog("stripped_blue_spruce_log",
            logProperties(MapColor.LAPIS, MapColor.LAPIS, SoundType.WOOD));

    //stripped wood
    Block STRIPPED_WILLOW_WOOD = regLog("stripped_willow_wood",
            woodProperties(MapColor.WARPED_NYLIUM, SoundType.WOOD));
    Block STRIPPED_BAOBAB_WOOD = regLog("stripped_baobab_wood",
            woodProperties(MapColor.WOOD, SoundType.WOOD));
    Block STRIPPED_EBONY_WOOD = regLog("stripped_ebony_wood",
            woodProperties(MapColor.PODZOL, SoundType.WOOD));
    Block EBONY_HEARTWOOD = regLog("ebony_heartwood",
            woodProperties(MapColor.TERRACOTTA_BLACK, SoundType.WOOD));
    Block STRIPPED_FIR_WOOD = regLog("stripped_fir_wood",
            woodProperties(MapColor.WOOD, SoundType.WOOD));
    Block STRIPPED_PINE_WOOD = regLog("stripped_pine_wood",
            woodProperties(MapColor.PODZOL, SoundType.WOOD));
    Block STRIPPED_CEDAR_WOOD = regLog("stripped_cedar_wood",
            woodProperties(MapColor.TERRACOTTA_YELLOW, SoundType.WOOD));
    Block STRIPPED_MAHOGANY_WOOD = regLog("stripped_mahogany_wood",
            woodProperties(MapColor.TERRACOTTA_RED, SoundType.WOOD));
    Block STRIPPED_AZALEA_WOOD = regLog("stripped_azalea_wood",
            woodProperties(MapColor.TERRACOTTA_GREEN, SoundType.WOOD));
    Block STRIPPED_PALM_WOOD = regLog("stripped_palm_wood",
            woodProperties(MapColor.NETHER, SoundType.WOOD));
    Block STRIPPED_MAPLE_WOOD = regLog("stripped_maple_wood",
            woodProperties(MapColor.WOOD, SoundType.WOOD));
    Block STRIPPED_ASPEN_WOOD = regLog("stripped_aspen_wood",
            woodProperties(MapColor.WOOD, SoundType.WOOD));
    Block STRIPPED_ASPEN_WOOD_GAZING = regLog("stripped_aspen_wood_gazing",
            woodProperties(MapColor.WOOD, SoundType.WOOD));
    Block STRIPPED_WALNUT_WOOD = regLog("stripped_walnut_wood",
            woodProperties(MapColor.COLOR_BROWN, SoundType.WOOD));
    Block STRIPPED_BLUE_SPRUCE_WOOD = regLog("stripped_blue_spruce_wood",
            woodProperties(MapColor.LAPIS, SoundType.WOOD));
    
    //leaves
    Block WILLOW_LEAVES = regLeaves("willow_leaves", SoundType.AZALEA_LEAVES);
    Block BAOBAB_LEAVES = regLeaves("baobab_leaves", SoundType.AZALEA_LEAVES);
    Block EBONY_LEAVES = regLeaves("ebony_leaves", SoundType.AZALEA_LEAVES);
    Block EBONY_LEAVES_FRUITING = regLeaves("ebony_leaves_fruiting", MapColor.GOLD, SoundType.AZALEA_LEAVES);
    Block FIR_LEAVES = regLeaves("fir_leaves", SoundType.AZALEA_LEAVES);
    Block PINE_LEAVES = regLeaves("pine_leaves", SoundType.AZALEA_LEAVES);
    Block CEDAR_LEAVES = regLeaves("cedar_leaves", SoundType.AZALEA_LEAVES);
    Block MAHOGANY_LEAVES = regLeaves("mahogany_leaves", SoundType.AZALEA_LEAVES);
    Block PALM_LEAVES = regBlock("palm_leaves", PalmLeavesBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).strength(0.2F).randomTicks().sound(SoundType.AZALEA_LEAVES).noOcclusion().isValidSpawn(ModBlocks::ocelotOrParrot).isSuffocating(BlockFactories::never).isViewBlocking(BlockFactories::never).ignitedByLava().pushReaction(PushReaction.DESTROY).isRedstoneConductor(BlockFactories::never));
    Block MAPLE_LEAVES = regLeaves("maple_leaves", MapColor.CRIMSON_STEM, SoundType.AZALEA_LEAVES);
    Block ASPEN_LEAVES = regLeaves("aspen_leaves", SoundType.AZALEA_LEAVES);
    Block WALNUT_LEAVES = regLeaves("walnut_leaves", SoundType.AZALEA_LEAVES);
    Block BLUE_SPRUCE_LEAVES = regLeaves("blue_spruce_leaves", MapColor.ICE, SoundType.AZALEA_LEAVES);

    //saplings
    Block WILLOW_SAPLING = sapling("willow_sapling", ModTreeGrowers.WILLOW);
    Block BAOBAB_SAPLING = sapling("baobab_sapling", ModTreeGrowers.BAOBAB);
    Block EBONY_SAPLING = sapling("ebony_sapling", ModTreeGrowers.EBONY);
    Block FIR_SAPLING = sapling("fir_sapling", ModTreeGrowers.FIR);
    Block PINE_SAPLING = sapling("pine_sapling", ModTreeGrowers.PINE);
    Block CEDAR_SAPLING = sapling("cedar_sapling", ModTreeGrowers.CEDAR);
    Block MAHOGANY_SAPLING = sapling("mahogany_sapling", ModTreeGrowers.MAHOGANY);
    Block COCONUT = ModBlocks.regBlock("coconut", CoconutBlock::new, (BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).randomTicks().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY)), false);
    Block HANGING_COCONUT = ModBlocks.regBlock(
            "hanging_coconut",
            HangingCoconutBlock::new,
            (BlockBehaviour.Properties.ofFullCopy(COCONUT)
                    .mapColor(MapColor.PLANT).overrideLootTable(COCONUT.getLootTable())
            ),
            false
    );
    Block MAPLE_SAPLING = sapling("maple_sapling", ModTreeGrowers.MAPLE);
    Block ASPEN_SAPLING = sapling("aspen_sapling", ModTreeGrowers.ASPEN);
    Block WALNUT_SAPLING = sapling("walnut_sapling", ModTreeGrowers.WALNUT);
    Block BLUE_SPRUCE_SAPLING = sapling("blue_spruce_sapling", ModTreeGrowers.BLUE_SPRUCE);

    Block POTTED_WILLOW_SAPLING = pottedSapling(WILLOW_SAPLING);
    Block POTTED_BAOBAB_SAPLING =  pottedSapling(BAOBAB_SAPLING);
    Block POTTED_EBONY_SAPLING = pottedSapling(EBONY_SAPLING);
    Block POTTED_FIR_SAPLING =  pottedSapling(FIR_SAPLING);
    Block POTTED_PINE_SAPLING = pottedSapling(PINE_SAPLING);
    Block POTTED_CEDAR_SAPLING = pottedSapling(CEDAR_SAPLING);
    Block POTTED_MAHOGANY_SAPLING = pottedSapling(MAHOGANY_SAPLING);
    Block POTTED_COCONUT = pottedSapling(COCONUT);
    Block POTTED_MAPLE_SAPLING = pottedSapling(MAPLE_SAPLING);
    Block POTTED_ASPEN_SAPLING = pottedSapling(ASPEN_SAPLING);
    Block POTTED_WALNUT_SAPLING = pottedSapling(WALNUT_SAPLING);
    Block POTTED_BLUE_SPRUCE_SAPLING = pottedSapling(BLUE_SPRUCE_SAPLING);


}
