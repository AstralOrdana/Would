package com.ordana.would.reg;

import com.ordana.would.Would;
import com.ordana.would.blocks.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.Function;

public interface ModBlocks {

    static void init() {
    }


    Block HANGING_WILLOW_LEAVES = regBlock(
        "hanging_willow_leaves",
        new HangingWillowLeavesBlock(BlockBehaviour.Properties.of()
            .mapColor(MapColor.PLANT)
            .noCollision()
            .randomTicks()
            .strength(0.2F)
            .sound(SoundType.VINE)
            .ignitedByLava()
            .pushReaction(PushReaction.DESTROY)
        ),
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


    private static Block log(MapColor topMapColor, MapColor sideMapColor, SoundType soundType) {
        return new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor((blockState) -> blockState.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(soundType).ignitedByLava());
    }

    private static Block wood(MapColor mapColor, SoundType soundType) {
        return new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(mapColor).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(soundType).ignitedByLava());
    }
    
    private static Block leaves(SoundType type) {
        return new LeavesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).strength(0.2F).randomTicks().sound(type).noOcclusion().isValidSpawn(ModBlocks::ocelotOrParrot).isSuffocating(ModBlocks::never).isViewBlocking(ModBlocks::never).ignitedByLava().pushReaction(PushReaction.DESTROY).isRedstoneConductor(ModBlocks::never));
    }

    static boolean never(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return false;
    }

    private static Block leaves(MapColor mapColor, SoundType type) {
        return new LeavesBlock(BlockBehaviour.Properties.of().mapColor(mapColor).strength(0.2F).randomTicks().sound(type).noOcclusion().isValidSpawn(ModBlocks::ocelotOrParrot).isSuffocating(ModBlocks::never).isViewBlocking(ModBlocks::never).ignitedByLava().pushReaction(PushReaction.DESTROY).isRedstoneConductor(ModBlocks::never));
    }

    private static Block sapling(TreeGrower treeGrower) {
        return new ModSaplingBlock(treeGrower, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollision().randomTicks().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY));
    }

    private static FlowerPotBlock pottedSapling(Block content, FeatureFlag... requiredFeatures) {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY);
        if (requiredFeatures.length > 0) {
            properties = properties.requiredFeatures(requiredFeatures);
        }

        return new FlowerPotBlock(content, properties);
    }

    static BlockBehaviour.Properties plankProperties(MapColor mapColor, SoundType soundType) {
        return BlockBehaviour.Properties.of().mapColor(mapColor).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(soundType).ignitedByLava();
    }

    static BlockBehaviour.Properties slab(MapColor mapColor, SoundType soundType) {
        return BlockBehaviour.Properties.of().mapColor(mapColor).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(soundType).ignitedByLava();
    }

    static BlockBehaviour.Properties fence(MapColor mapColor, SoundType soundType) {
        return BlockBehaviour.Properties.of().mapColor(mapColor).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).ignitedByLava().sound(soundType);
    }

    static BlockBehaviour.Properties fenceGate(MapColor mapColor, SoundType soundType, WoodType woodType) {
        return BlockBehaviour.Properties.of().mapColor(mapColor).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).ignitedByLava().sound(soundType);
    }

    private static BlockBehaviour.Properties pressurePlate(MapColor mapColor) {
        return BlockBehaviour.Properties.of().mapColor(mapColor).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollision().strength(0.5F).ignitedByLava().pushReaction(PushReaction.DESTROY);
    }

    private static Block button(FeatureFlag... requiredFeatures) {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.of().noCollision().strength(0.5F).pushReaction(PushReaction.DESTROY);
        if (requiredFeatures.length > 0) {
            properties = properties.requiredFeatures(requiredFeatures);
        }

        return new ModWoodenButtonBlock(BlockSetType.ACACIA, properties);
    }

    private static Block standingSign(MapColor mapColor, WoodType woodType) {
        return new StandingSignBlock(woodType, BlockBehaviour.Properties.of().mapColor(mapColor).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0F).ignitedByLava());
    }


    private static Block wallSign(MapColor mapColor, Block block, WoodType woodType) {
        return new WallSignBlock(woodType, BlockBehaviour.Properties.of().mapColor(mapColor).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0F).dropsLike(block).ignitedByLava());
    }

    private static Block hangingSign(MapColor mapColor, WoodType woodType) {
        return new CeilingHangingSignBlock(woodType, BlockBehaviour.Properties.of().mapColor(mapColor).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0F).ignitedByLava());
    }

    private static Block wallHangingSign(MapColor mapColor, Block block, WoodType woodType) {
        return new WallHangingSignBlock(woodType, BlockBehaviour.Properties.of().mapColor(mapColor).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0F).ignitedByLava().dropsLike(block));
    }

    private static Block door(MapColor mapColor) {
        return new DoorBlock(BlockSetType.ACACIA, BlockBehaviour.Properties.of().mapColor(mapColor).instrument(NoteBlockInstrument.BASS).strength(3.0F).noOcclusion().ignitedByLava().pushReaction(PushReaction.DESTROY));
    }

    private static Block trapdoor(MapColor mapColor) {
        return new TrapDoorBlock(BlockSetType.ACACIA, BlockBehaviour.Properties.of().mapColor(mapColor).instrument(NoteBlockInstrument.BASS).strength(3.0F).noOcclusion().isValidSpawn(ModBlocks::never).ignitedByLava());
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
            new PalmLeavesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).strength(0.2F).randomTicks().sound(SoundType.AZALEA_LEAVES).noOcclusion().isValidSpawn(ModBlocks::ocelotOrParrot).isSuffocating(ModBlocks::never).isViewBlocking(ModBlocks::never).ignitedByLava().pushReaction(PushReaction.DESTROY).isRedstoneConductor(ModBlocks::never)));
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

    //buttons
    Block WILLOW_BUTTON = regWithItem("willow_button", ModBlocks.button());
    Block BAOBAB_BUTTON = regWithItem("baobab_button", ModBlocks.button());
    Block EBONY_BUTTON = regWithItem("ebony_button", ModBlocks.button());
    Block FIR_BUTTON = regWithItem("fir_button", ModBlocks.button());
    Block PINE_BUTTON = regWithItem("pine_button", ModBlocks.button());
    Block CEDAR_BUTTON = regWithItem("cedar_button", ModBlocks.button());
    Block MAHOGANY_BUTTON = regWithItem("mahogany_button", ModBlocks.button());
    Block AZALEA_BUTTON = regWithItem("azalea_button", ModBlocks.button());
    Block PALM_BUTTON = regWithItem("palm_button", ModBlocks.button());
    Block MAPLE_BUTTON = regWithItem("maple_button", ModBlocks.button());
    Block ASPEN_BUTTON = regWithItem("aspen_button", ModBlocks.button());
    Block WALNUT_BUTTON = regWithItem("walnut_button", ModBlocks.button());
    Block BLUE_SPRUCE_BUTTON = regWithItem("blue_spruce_button", ModBlocks.button());

    //pressure plates
    Block WILLOW_PRESSURE_PLATE = regWithItem("willow_pressure_plate",
            pressurePlate(WILLOW_PLANKS.defaultMapColor()));
    Block BAOBAB_PRESSURE_PLATE = regWithItem("baobab_pressure_plate",
            pressurePlate(BAOBAB_PLANKS.defaultMapColor()));
    Block EBONY_PRESSURE_PLATE = regWithItem("ebony_pressure_plate",
            pressurePlate(EBONY_PLANKS.defaultMapColor()));
    Block FIR_PRESSURE_PLATE = regWithItem("fir_pressure_plate",
            pressurePlate(FIR_PLANKS.defaultMapColor()));
    Block PINE_PRESSURE_PLATE = regWithItem("pine_pressure_plate",
            pressurePlate(PINE_PLANKS.defaultMapColor()));
    Block CEDAR_PRESSURE_PLATE = regWithItem("cedar_pressure_plate",
            pressurePlate(CEDAR_PLANKS.defaultMapColor()));
    Block MAHOGANY_PRESSURE_PLATE = regWithItem("mahogany_pressure_plate",
            pressurePlate(MAHOGANY_PLANKS.defaultMapColor()));
    Block AZALEA_PRESSURE_PLATE = regWithItem("azalea_pressure_plate",
            pressurePlate(AZALEA_PLANKS.defaultMapColor()));
    Block PALM_PRESSURE_PLATE = regWithItem("palm_pressure_plate",
            pressurePlate(PALM_PLANKS.defaultMapColor()));
    Block MAPLE_PRESSURE_PLATE = regWithItem("maple_pressure_plate",
            pressurePlate(MAPLE_PLANKS.defaultMapColor()));
    Block ASPEN_PRESSURE_PLATE = regWithItem("aspen_pressure_plate",
            pressurePlate(ASPEN_PLANKS.defaultMapColor()));
    Block WALNUT_PRESSURE_PLATE = regWithItem("walnut_pressure_plate",
            pressurePlate(WALNUT_PLANKS.defaultMapColor()));
    Block BLUE_SPRUCE_PRESSURE_PLATE = regWithItem("blue_spruce_pressure_plate",
            pressurePlate(BLUE_SPRUCE_PLANKS.defaultMapColor()));

    //signs
    Block WILLOW_SIGN = regBlock("willow_sign",
            standingSign(WILLOW_PLANKS.defaultMapColor(), ModWoodSetup.WILLOW));
    Block BAOBAB_SIGN = regBlock("baobab_sign",
            standingSign(BAOBAB_PLANKS.defaultMapColor(), ModWoodSetup.BAOBAB));
    Block EBONY_SIGN = regBlock("ebony_sign",
            standingSign(EBONY_PLANKS.defaultMapColor(), ModWoodSetup.EBONY));
    Block FIR_SIGN = regBlock("fir_sign",
            standingSign(FIR_PLANKS.defaultMapColor(), ModWoodSetup.FIR));
    Block PINE_SIGN = regBlock("pine_sign",
            standingSign(PINE_PLANKS.defaultMapColor(), ModWoodSetup.PINE));
    Block CEDAR_SIGN = regBlock("cedar_sign",
            standingSign(CEDAR_PLANKS.defaultMapColor(), ModWoodSetup.CEDAR));
    Block MAHOGANY_SIGN = regBlock("mahogany_sign",
            standingSign(MAHOGANY_PLANKS.defaultMapColor(), ModWoodSetup.MAHOGANY));
    Block AZALEA_SIGN = regBlock("azalea_sign",
            standingSign(AZALEA_PLANKS.defaultMapColor(), ModWoodSetup.AZALEA));
    Block PALM_SIGN = regBlock("palm_sign",
            standingSign(PALM_PLANKS.defaultMapColor(), ModWoodSetup.PALM));
    Block MAPLE_SIGN = regBlock("maple_sign",
            standingSign(MAPLE_PLANKS.defaultMapColor(), ModWoodSetup.MAPLE));
    Block ASPEN_SIGN = regBlock("aspen_sign",
            standingSign(ASPEN_PLANKS.defaultMapColor(), ModWoodSetup.ASPEN));
    Block WALNUT_SIGN = regBlock("walnut_sign",
            standingSign(WALNUT_PLANKS.defaultMapColor(), ModWoodSetup.WALNUT));
    Block BLUE_SPRUCE_SIGN = regBlock("blue_spruce_sign",
            standingSign(BLUE_SPRUCE_PLANKS.defaultMapColor(), ModWoodSetup.BLUE_SPRUCE));

    Block WILLOW_WALL_SIGN = regBlock("willow_wall_sign",
            wallSign(WILLOW_PLANKS.defaultMapColor(), ModBlocks.WILLOW_SIGN, ModWoodSetup.WILLOW));
    Block BAOBAB_WALL_SIGN = regBlock("baobab_wall_sign",
            wallSign(BAOBAB_PLANKS.defaultMapColor(), ModBlocks.BAOBAB_SIGN, ModWoodSetup.BAOBAB));
    Block EBONY_WALL_SIGN = regBlock("ebony_wall_sign",
            wallSign(EBONY_PLANKS.defaultMapColor(), ModBlocks.EBONY_SIGN, ModWoodSetup.EBONY));
    Block FIR_WALL_SIGN = regBlock("fir_wall_sign",
            wallSign(FIR_PLANKS.defaultMapColor(), ModBlocks.FIR_SIGN, ModWoodSetup.FIR));
    Block PINE_WALL_SIGN = regBlock("pine_wall_sign",
            wallSign(PINE_PLANKS.defaultMapColor(), ModBlocks.PINE_SIGN, ModWoodSetup.PINE));
    Block CEDAR_WALL_SIGN = regBlock("cedar_wall_sign",
            wallSign(CEDAR_PLANKS.defaultMapColor(), ModBlocks.CEDAR_SIGN, ModWoodSetup.CEDAR));
    Block MAHOGANY_WALL_SIGN = regBlock("mahogany_wall_sign",
            wallSign(MAHOGANY_PLANKS.defaultMapColor(), ModBlocks.MAHOGANY_SIGN, ModWoodSetup.MAHOGANY));
    Block AZALEA_WALL_SIGN = regBlock("azalea_wall_sign",
            wallSign(AZALEA_PLANKS.defaultMapColor(), ModBlocks.AZALEA_SIGN, ModWoodSetup.AZALEA));
    Block PALM_WALL_SIGN = regBlock("palm_wall_sign",
            wallSign(PALM_PLANKS.defaultMapColor(), ModBlocks.PALM_SIGN, ModWoodSetup.PALM));
    Block MAPLE_WALL_SIGN = regBlock("maple_wall_sign",
            wallSign(MAPLE_PLANKS.defaultMapColor(), ModBlocks.MAPLE_SIGN, ModWoodSetup.MAPLE));
    Block ASPEN_WALL_SIGN = regBlock("aspen_wall_sign",
            wallSign(ASPEN_PLANKS.defaultMapColor(), ModBlocks.ASPEN_SIGN, ModWoodSetup.ASPEN));
    Block WALNUT_WALL_SIGN = regBlock("walnut_wall_sign",
            wallSign(WALNUT_PLANKS.defaultMapColor(), ModBlocks.WALNUT_SIGN, ModWoodSetup.WALNUT));
    Block BLUE_SPRUCE_WALL_SIGN = regBlock("blue_spruce_wall_sign",
            wallSign(BLUE_SPRUCE_PLANKS.defaultMapColor(), ModBlocks.BLUE_SPRUCE_SIGN, ModWoodSetup.BLUE_SPRUCE));
    
    //hanging signs
    Block WILLOW_HANGING_SIGN = regBlock("willow_hanging_sign",
            hangingSign(WILLOW_PLANKS.defaultMapColor(), ModWoodSetup.WILLOW));
    Block BAOBAB_HANGING_SIGN = regBlock("baobab_hanging_sign",
            hangingSign(BAOBAB_PLANKS.defaultMapColor(), ModWoodSetup.BAOBAB));
    Block EBONY_HANGING_SIGN = regBlock("ebony_hanging_sign",
            hangingSign(EBONY_PLANKS.defaultMapColor(), ModWoodSetup.EBONY));
    Block FIR_HANGING_SIGN = regBlock("fir_hanging_sign",
            hangingSign(FIR_PLANKS.defaultMapColor(), ModWoodSetup.FIR));
    Block PINE_HANGING_SIGN = regBlock("pine_hanging_sign",
            hangingSign(PINE_PLANKS.defaultMapColor(), ModWoodSetup.PINE));
    Block CEDAR_HANGING_SIGN = regBlock("cedar_hanging_sign",
            hangingSign(CEDAR_PLANKS.defaultMapColor(), ModWoodSetup.CEDAR));
    Block MAHOGANY_HANGING_SIGN = regBlock("mahogany_hanging_sign",
            hangingSign(MAHOGANY_PLANKS.defaultMapColor(), ModWoodSetup.MAHOGANY));
    Block AZALEA_HANGING_SIGN = regBlock("azalea_hanging_sign",
            hangingSign(AZALEA_PLANKS.defaultMapColor(), ModWoodSetup.AZALEA));
    Block PALM_HANGING_SIGN = regBlock("palm_hanging_sign",
            hangingSign(PALM_PLANKS.defaultMapColor(), ModWoodSetup.PALM));
    Block MAPLE_HANGING_SIGN = regBlock("maple_hanging_sign",
            hangingSign(MAPLE_PLANKS.defaultMapColor(), ModWoodSetup.MAPLE));
    Block ASPEN_HANGING_SIGN = regBlock("aspen_hanging_sign",
            hangingSign(ASPEN_PLANKS.defaultMapColor(), ModWoodSetup.ASPEN));
    Block WALNUT_HANGING_SIGN = regBlock("walnut_hanging_sign",
            hangingSign(WALNUT_PLANKS.defaultMapColor(), ModWoodSetup.WALNUT));
    Block BLUE_SPRUCE_HANGING_SIGN = regBlock("blue_spruce_hanging_sign",
            hangingSign(BLUE_SPRUCE_PLANKS.defaultMapColor(), ModWoodSetup.BLUE_SPRUCE));

    Block WILLOW_WALL_HANGING_SIGN = regBlock("willow_wall_hanging_sign",
            wallHangingSign(WILLOW_SIGN.defaultMapColor(), WILLOW_SIGN, ModWoodSetup.WILLOW));
    Block BAOBAB_WALL_HANGING_SIGN = regBlock("baobab_wall_hanging_sign",
            wallHangingSign(BAOBAB_SIGN.defaultMapColor(), BAOBAB_SIGN, ModWoodSetup.BAOBAB));
    Block EBONY_WALL_HANGING_SIGN = regBlock("ebony_wall_hanging_sign",
            wallHangingSign(EBONY_SIGN.defaultMapColor(), EBONY_SIGN, ModWoodSetup.EBONY));
    Block FIR_WALL_HANGING_SIGN = regBlock("fir_wall_hanging_sign",
            wallHangingSign(FIR_SIGN.defaultMapColor(), FIR_SIGN, ModWoodSetup.FIR));
    Block PINE_WALL_HANGING_SIGN = regBlock("pine_wall_hanging_sign",
            wallHangingSign(PINE_SIGN.defaultMapColor(), PINE_SIGN, ModWoodSetup.PINE));
    Block CEDAR_WALL_HANGING_SIGN = regBlock("cedar_wall_hanging_sign",
            wallHangingSign(CEDAR_SIGN.defaultMapColor(), CEDAR_SIGN, ModWoodSetup.CEDAR));
    Block MAHOGANY_WALL_HANGING_SIGN = regBlock("mahogany_wall_hanging_sign",
            wallHangingSign(MAHOGANY_SIGN.defaultMapColor(), MAHOGANY_SIGN, ModWoodSetup.MAHOGANY));
    Block AZALEA_WALL_HANGING_SIGN = regBlock("azalea_wall_hanging_sign",
            wallHangingSign(PALM_SIGN.defaultMapColor(), PALM_SIGN, ModWoodSetup.AZALEA));
    Block PALM_WALL_HANGING_SIGN = regBlock("palm_wall_hanging_sign",
            wallHangingSign(PALM_SIGN.defaultMapColor(), PALM_SIGN, ModWoodSetup.PALM));
    Block MAPLE_WALL_HANGING_SIGN = regBlock("maple_wall_hanging_sign",
            wallHangingSign(MAPLE_SIGN.defaultMapColor(), MAPLE_SIGN, ModWoodSetup.MAPLE));
    Block ASPEN_WALL_HANGING_SIGN = regBlock("aspen_wall_hanging_sign",
            wallHangingSign(ASPEN_SIGN.defaultMapColor(), ASPEN_SIGN, ModWoodSetup.ASPEN));
    Block WALNUT_WALL_HANGING_SIGN = regBlock("walnut_wall_hanging_sign",
            wallHangingSign(WALNUT_SIGN.defaultMapColor(), WALNUT_SIGN, ModWoodSetup.WALNUT));
    Block BLUE_SPRUCE_WALL_HANGING_SIGN = regBlock("blue_spruce_wall_hanging_sign",
            wallHangingSign(BLUE_SPRUCE_SIGN.defaultMapColor(), BLUE_SPRUCE_SIGN, ModWoodSetup.BLUE_SPRUCE));
    
    //doors
    Block WILLOW_DOOR = regWithItem("willow_door",
            door(WILLOW_PLANKS.defaultMapColor()));
    Block BAOBAB_DOOR = regWithItem("baobab_door",
            door(BAOBAB_PLANKS.defaultMapColor()));
    Block EBONY_DOOR = regWithItem("ebony_door",
            door(EBONY_PLANKS.defaultMapColor()));
    Block FIR_DOOR = regWithItem("fir_door",
            door(FIR_PLANKS.defaultMapColor()));
    Block PINE_DOOR = regWithItem("pine_door",
            door(PINE_PLANKS.defaultMapColor()));
    Block CEDAR_DOOR = regWithItem("cedar_door",
            door(CEDAR_PLANKS.defaultMapColor()));
    Block MAHOGANY_DOOR = regWithItem("mahogany_door",
            door(MAHOGANY_PLANKS.defaultMapColor()));
    Block AZALEA_DOOR = regWithItem("azalea_door",
            door(AZALEA_PLANKS.defaultMapColor()));
    Block PALM_DOOR = regWithItem("palm_door",
            door(PALM_PLANKS.defaultMapColor()));
    Block MAPLE_DOOR = regWithItem("maple_door",
            door(MAPLE_PLANKS.defaultMapColor()));
    Block ASPEN_DOOR = regWithItem("aspen_door",
            door(ASPEN_PLANKS.defaultMapColor()));
    Block WALNUT_DOOR = regWithItem("walnut_door",
            door(WALNUT_PLANKS.defaultMapColor()));
    Block BLUE_SPRUCE_DOOR = regWithItem("blue_spruce_door",
            door(BLUE_SPRUCE_PLANKS.defaultMapColor()));

    //trapdoors
    Block WILLOW_TRAPDOOR = regWithItem("willow_trapdoor",
            trapdoor(WILLOW_PLANKS.defaultMapColor()));
    Block BAOBAB_TRAPDOOR = regWithItem("baobab_trapdoor",
            trapdoor(WILLOW_PLANKS.defaultMapColor()));
    Block EBONY_TRAPDOOR = regWithItem("ebony_trapdoor",
            trapdoor(WILLOW_PLANKS.defaultMapColor()));
    Block FIR_TRAPDOOR = regWithItem("fir_trapdoor",
            trapdoor(WILLOW_PLANKS.defaultMapColor()));
    Block PINE_TRAPDOOR = regWithItem("pine_trapdoor",
            trapdoor(WILLOW_PLANKS.defaultMapColor()));
    Block CEDAR_TRAPDOOR = regWithItem("cedar_trapdoor",
            trapdoor(WILLOW_PLANKS.defaultMapColor()));
    Block MAHOGANY_TRAPDOOR = regWithItem("mahogany_trapdoor",
            trapdoor(WILLOW_PLANKS.defaultMapColor()));
    Block AZALEA_TRAPDOOR = regWithItem("azalea_trapdoor",
            trapdoor(WILLOW_PLANKS.defaultMapColor()));
    Block PALM_TRAPDOOR = regWithItem("palm_trapdoor",
            trapdoor(WILLOW_PLANKS.defaultMapColor()));
    Block MAPLE_TRAPDOOR = regWithItem("maple_trapdoor",
            trapdoor(WILLOW_PLANKS.defaultMapColor()));
    Block ASPEN_TRAPDOOR = regWithItem("aspen_trapdoor",
            trapdoor(WILLOW_PLANKS.defaultMapColor()));
    Block WALNUT_TRAPDOOR = regWithItem("walnut_trapdoor",
            trapdoor(WILLOW_PLANKS.defaultMapColor()));
    Block BLUE_SPRUCE_TRAPDOOR = regWithItem("blue_spruce_trapdoor",
            trapdoor(BLUE_SPRUCE_PLANKS.defaultMapColor()));


}
