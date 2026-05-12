package com.ordana.would.reg;

import com.ordana.would.Would;
import com.ordana.would.WouldPlatform;
import com.ordana.would.blocks.ModWoodenButtonBlock;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static com.ordana.would.reg.BlockFactories.*;
import static com.ordana.would.reg.ModBlocks.*;


public interface ModWoodTypes {

    List<WouldType> ALL = new ArrayList<>();

    WouldType ASPEN = register("aspen", ModBlockSetTypes.ASPEN, MapColor.WARPED_NYLIUM, SoundType.WOOD, ASPEN_LOG, STRIPPED_ASPEN_LOG, ASPEN_SAPLING, ASPEN_LEAVES, ASPEN_WOOD);
    WouldType AZALEA = register("azalea", ModBlockSetTypes.AZALEA, MapColor.TERRACOTTA_GREEN, SoundType.WOOD, AZALEA_LOG, STRIPPED_AZALEA_LOG, null, null, AZALEA_WOOD);
    WouldType BAOBAB = register("baobab", ModBlockSetTypes.BAOBAB, MapColor.WOOD, SoundType.WOOD, BAOBAB_LOG, STRIPPED_BAOBAB_LOG, BAOBAB_SAPLING, BAOBAB_LEAVES, BAOBAB_WOOD);
    WouldType CEDAR = register("cedar", ModBlockSetTypes.CEDAR, MapColor.TERRACOTTA_YELLOW, SoundType.WOOD, CEDAR_LOG, STRIPPED_CEDAR_LOG, CEDAR_SAPLING, CEDAR_LEAVES, CEDAR_WOOD);
    WouldType EBONY = register("ebony", ModBlockSetTypes.EBONY, MapColor.TERRACOTTA_BLACK, SoundType.WOOD, EBONY_LOG, STRIPPED_EBONY_LOG, EBONY_SAPLING, EBONY_LEAVES, EBONY_WOOD);
    WouldType FIR = register("fir", ModBlockSetTypes.FIR, MapColor.WOOD, SoundType.WOOD, FIR_LOG, STRIPPED_FIR_LOG, FIR_SAPLING, FIR_LEAVES, FIR_WOOD);
    WouldType MAHOGANY = register("mahogany", ModBlockSetTypes.MAHOGANY, MapColor.TERRACOTTA_RED, SoundType.WOOD, MAHOGANY_LOG, STRIPPED_MAHOGANY_LOG, MAHOGANY_SAPLING, MAHOGANY_LEAVES, MAHOGANY_WOOD);
    WouldType MAPLE = register("maple", ModBlockSetTypes.MAPLE, MapColor.WOOD, SoundType.WOOD, MAPLE_LOG, STRIPPED_MAPLE_LOG, MAPLE_SAPLING, MAPLE_LEAVES, MAPLE_WOOD);
    WouldType PALM = register("palm", ModBlockSetTypes.PALM, MapColor.NETHER, SoundType.WOOD, PALM_LOG, STRIPPED_PALM_LOG, COCONUT, PALM_LEAVES, PALM_WOOD);
    WouldType PINE = register("pine", ModBlockSetTypes.PINE, MapColor.PODZOL, SoundType.WOOD, PINE_LOG, STRIPPED_PINE_LOG, PINE_SAPLING, PINE_LEAVES, PINE_WOOD);
    WouldType WALNUT = register("walnut", ModBlockSetTypes.WALNUT, MapColor.TERRACOTTA_BROWN, SoundType.WOOD, WALNUT_LOG, STRIPPED_WALNUT_LOG, WALNUT_SAPLING, WALNUT_LEAVES, WALNUT_WOOD);
    WouldType WILLOW = register("willow", ModBlockSetTypes.WILLOW, MapColor.WARPED_NYLIUM, SoundType.WOOD, WILLOW_LOG, STRIPPED_WILLOW_LOG, WILLOW_SAPLING, WILLOW_LEAVES, WILLOW_WOOD);
    WouldType BLUE_SPRUCE = register("blue_spruce", ModBlockSetTypes.BLUE_SPRUCE, MapColor.LAPIS, SoundType.WOOD, BLUE_SPRUCE_LOG, STRIPPED_BLUE_SPRUCE_LOG, BLUE_SPRUCE_SAPLING, BLUE_SPRUCE_LEAVES, BLUE_SPRUCE_WOOD);

    private static WouldType register(String name, BlockSetType blockSetType, MapColor mapColor, SoundType soundType, Block log, Block strippedLog, @Nullable Block sapling, @Nullable Block leaves, Block wood) {
        // basics
        WoodType woodType = WoodType.register(new WoodType(Would.res(name).toString(), blockSetType));
        BlockBehaviour.Properties settings = plankProperties(mapColor, SoundType.WOOD);
        var logItemTagKey = ModTags.item(name+"_logs");

        // blocks
        Block planks = ModBlocks.regBlock(name+"_planks", Block::new,
                settings, true);

        SlabBlock slab = ModBlocks.regBlock(name+"_slab", SlabBlock::new,
                slab(mapColor, SoundType.WOOD));

        StairBlock stairs = ModBlocks.regBlock(name+"_stairs", properties -> new StairBlock(planks.defaultBlockState(), properties),
                settings);

        FenceBlock fence = ModBlocks.regBlock( name+"_fence", FenceBlock::new,
                fence(mapColor, soundType));

        FenceGateBlock fenceGate = ModBlocks.regBlock( name+"_fence_gate", properties -> new FenceGateBlock(woodType, properties),
                fenceGate(mapColor, soundType, woodType));

        DoorBlock door = ModBlocks.regBlock( name+"_door", properties -> new DoorBlock(blockSetType, properties),
                BlockBehaviour.Properties.of().mapColor(mapColor).instrument(NoteBlockInstrument.BASS).strength(3.0F).noOcclusion().ignitedByLava().pushReaction(PushReaction.DESTROY));

        TrapDoorBlock trapDoorBlock = ModBlocks.regBlock( name+"_trapdoor", properties -> new TrapDoorBlock(blockSetType, properties),
                BlockBehaviour.Properties.of().mapColor(mapColor).instrument(NoteBlockInstrument.BASS).strength(3.0F).noOcclusion().isValidSpawn(ModBlocks::never).ignitedByLava());

        StandingSignBlock signBlock = ModBlocks.regBlock( name+"_sign", properties -> new StandingSignBlock(woodType, properties),
                BlockBehaviour.Properties.of().mapColor(mapColor).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0F).ignitedByLava(), false);

        WallSignBlock wallSignBlock = ModBlocks.regBlock( name+"_wall_sign", (p) -> new WallSignBlock(WoodType.WARPED, p), wallVariant(signBlock, true).mapColor(mapColor).instrument(NoteBlockInstrument.BASS).forceSolidOn().noCollision().strength(1.0F), false);

        PressurePlateBlock pressurePlate = regBlock(name+"_pressure_plate", (p) -> new PressurePlateBlock(blockSetType, p), BlockBehaviour.Properties.of().mapColor(mapColor).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollision().strength(0.5F).ignitedByLava().pushReaction(PushReaction.DESTROY));
        ModWoodenButtonBlock button = regBlock(name+"_button", (p) -> new ModWoodenButtonBlock(blockSetType, p), BlockBehaviour.Properties.of().mapColor(mapColor).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollision().strength(0.5F).ignitedByLava().pushReaction(PushReaction.DESTROY));

        CeilingHangingSignBlock hangingSignBlock = ModBlocks.regBlock(name+"_hanging_sign", properties -> new CeilingHangingSignBlock(woodType, properties),
                BlockBehaviour.Properties.of().mapColor(mapColor).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0F).ignitedByLava(), false);

        WallHangingSignBlock wallHangingSignBlock = ModBlocks.regBlock( name+"_hanging_wall_sign", (p) -> new WallHangingSignBlock(WoodType.WARPED, p), wallVariant(signBlock, true).mapColor(mapColor).instrument(NoteBlockInstrument.BASS).forceSolidOn().noCollision().strength(1.0F), false);

        // items
        var boatItem = ModItems.regItem(name+"_boat", (p) -> new BoatItem(EntityType.PALE_OAK_BOAT, p), (new Item.Properties()).stacksTo(1));
        var chestBoatItem = ModItems.regItem(name+"_chest_boat", (p) -> new BoatItem(EntityType.CHERRY_CHEST_BOAT, p), (new Item.Properties()).stacksTo(1));
        var signItem = ModItems.regItem(name+"_sign", (p) -> new SignItem(signBlock, wallSignBlock, p), (new Item.Properties()).stacksTo(16));
        var hangingSignItem = ModItems.regItem(name+"_hanging_sign", (p) -> new HangingSignItem(hangingSignBlock, wallHangingSignBlock, p), (new Item.Properties()).stacksTo(16));

        ModBlocks.ALL_SIGNS.add(signBlock);
        if (sapling != null)
            ALL_SAPLINGS.add(sapling);
        ModBlocks.ALL_HANGING_SIGNS.add(hangingSignBlock);
        ModItems.ALL_SIGNS.add(signItem);
        ModItems.ALL_HANGING_SIGNS.add(hangingSignItem);
        ModItems.ALL_BOATS.add(boatItem);
        ModItems.ALL_CHEST_BOATS.add(chestBoatItem);

        // create holder
        BlockFamily family = new BlockFamily.Builder(planks).button(button).pressurePlate(pressurePlate).slab(slab).stairs(stairs).fence(fence).fenceGate(fenceGate).door(door).recipeUnlockedBy("has_planks").recipeGroupPrefix("wooden").getFamily();
        var wouldType = new WouldType(woodType, planks, slab, stairs, fence, fenceGate, door, trapDoorBlock, signBlock, wallSignBlock, pressurePlate, button, hangingSignBlock, wallHangingSignBlock, boatItem, chestBoatItem, signItem, hangingSignItem, log, strippedLog, sapling, leaves, logItemTagKey, wood, family);

        // client
        if (WouldPlatform.INSTANCE.isClient()) {
            Sheets.SIGN_SPRITES.put(wouldType.woodType(), new SpriteId(Sheets.SIGN_SHEET, Would.res("entity/signs/" + name)));
            Sheets.HANGING_SIGN_SPRITES.put(wouldType.woodType(), new SpriteId(Sheets.SIGN_SHEET, Would.res("entity/signs/hanging/" + name)));
        }

        // return
        ModWoodTypes.ALL.add(wouldType);

        return wouldType;
    }

    private static BlockBehaviour.Properties wallVariant(final Block standingBlock, final boolean copyName) {
        BlockBehaviour.Properties wallProperties = BlockBehaviour.Properties.of().overrideLootTable(standingBlock.getLootTable());
        if (copyName) {
            wallProperties = wallProperties.overrideDescription(standingBlock.getDescriptionId());
        }

        return wallProperties;
    }

    static void init() {}

}
