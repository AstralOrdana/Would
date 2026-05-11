package com.ordana.would.reg;

import com.ordana.would.Would;
import com.ordana.would.WouldPlatform;
import com.ordana.would.blocks.ModWoodenButtonBlock;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.ArrayList;
import java.util.List;

import static com.ordana.would.reg.BlockFactories.*;
import static com.ordana.would.reg.ModBlocks.*;


public interface ModWoodTypes {

    List<WouldType> ALL = new ArrayList<>();

    WouldType ASPEN = register("aspen", ModBlockSetTypes.ASPEN, MapColor.WARPED_NYLIUM, SoundType.WOOD);
    WouldType AZALEA = register("azalea", ModBlockSetTypes.AZALEA, MapColor.TERRACOTTA_GREEN, SoundType.WOOD);
    WouldType BAOBAB = register("baobab", ModBlockSetTypes.BAOBAB, MapColor.WOOD, SoundType.WOOD);
    WouldType CEDAR = register("cedar", ModBlockSetTypes.CEDAR, MapColor.TERRACOTTA_YELLOW, SoundType.WOOD);
    WouldType EBONY = register("ebony", ModBlockSetTypes.EBONY, MapColor.TERRACOTTA_BLACK, SoundType.WOOD);
    WouldType FIR = register("fir", ModBlockSetTypes.FIR, MapColor.WOOD, SoundType.WOOD);
    WouldType MAHOGANY = register("mahogany", ModBlockSetTypes.MAHOGANY, MapColor.TERRACOTTA_RED, SoundType.WOOD);
    WouldType MAPLE = register("maple", ModBlockSetTypes.MAPLE, MapColor.WOOD, SoundType.WOOD);
    WouldType PALM = register("palm", ModBlockSetTypes.PALM, MapColor.NETHER, SoundType.WOOD);
    WouldType PINE = register("pine", ModBlockSetTypes.PINE, MapColor.PODZOL, SoundType.WOOD);
    WouldType WALNUT = register("walnut", ModBlockSetTypes.WALNUT, MapColor.TERRACOTTA_BROWN, SoundType.WOOD);
    WouldType WILLOW = register("willow", ModBlockSetTypes.WILLOW, MapColor.WARPED_NYLIUM, SoundType.WOOD);
    WouldType BLUE_SPRUCE = register("blue_spruce", ModBlockSetTypes.BLUE_SPRUCE, MapColor.LAPIS, SoundType.WOOD);

    private static WouldType register(String name, BlockSetType blockSetType, MapColor mapColor, SoundType soundType) {
        // basics
        WoodType woodType = WoodType.register(new WoodType(Would.res(name).toString(), blockSetType));
        BlockBehaviour.Properties settings = plankProperties(mapColor, SoundType.WOOD);

        // blocks
        Block planks = ModBlocks.regWithItem(name+"_planks", Block::new,
                settings, true);

        SlabBlock slab = ModBlocks.regWithItem(name+"_slab", SlabBlock::new,
                slab(mapColor, SoundType.WOOD));

        StairBlock stairs = ModBlocks.regWithItem(name+"_stairs", properties -> new StairBlock(planks.defaultBlockState(), properties),
                settings);

        FenceBlock fence = ModBlocks.regWithItem( name+"_fence", FenceBlock::new,
                fence(mapColor, soundType));

        FenceGateBlock fenceGate = ModBlocks.regWithItem( name+"_fence_gate", properties -> new FenceGateBlock(woodType, properties),
                fenceGate(mapColor, soundType, woodType));

        DoorBlock door = ModBlocks.regWithItem( name+"_door", properties -> new DoorBlock(blockSetType, properties),
                BlockBehaviour.Properties.of().mapColor(mapColor).instrument(NoteBlockInstrument.BASS).strength(3.0F).noOcclusion().ignitedByLava().pushReaction(PushReaction.DESTROY));

        TrapDoorBlock trapDoorBlock = ModBlocks.regWithItem( name+"_trapdoor", properties -> new TrapDoorBlock(blockSetType, properties),
                BlockBehaviour.Properties.of().mapColor(mapColor).instrument(NoteBlockInstrument.BASS).strength(3.0F).noOcclusion().isValidSpawn(ModBlocks::never).ignitedByLava());

        StandingSignBlock signBlock = ModBlocks.regWithItem( name+"_sign", properties -> new StandingSignBlock(woodType, properties),
                BlockBehaviour.Properties.of().mapColor(mapColor).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0F).ignitedByLava());

        WallSignBlock wallSignBlock = ModBlocks.regWithItem( name+"_wall_sign", (p) -> new WallSignBlock(WoodType.WARPED, p), wallVariant(signBlock, true).mapColor(mapColor).instrument(NoteBlockInstrument.BASS).forceSolidOn().noCollision().strength(1.0F));

        var pressurePlate = regWithItem(name+"_pressure_plate", (p) -> new PressurePlateBlock(blockSetType, p), BlockBehaviour.Properties.of().mapColor(mapColor).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollision().strength(0.5F).ignitedByLava().pushReaction(PushReaction.DESTROY));
        var button = regWithItem(name+"_button", (p) -> new ModWoodenButtonBlock(blockSetType, p), BlockBehaviour.Properties.of().mapColor(mapColor).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollision().strength(0.5F).ignitedByLava().pushReaction(PushReaction.DESTROY));

        CeilingHangingSignBlock hangingSignBlock = ModBlocks.regWithItem( name+"_hanging_sign", properties -> new CeilingHangingSignBlock(woodType, properties),
                BlockBehaviour.Properties.of().mapColor(mapColor).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0F).ignitedByLava());

        WallHangingSignBlock wallHangingSignBlock = ModBlocks.regWithItem( name+"_hanging_wall_sign", (p) -> new WallHangingSignBlock(WoodType.WARPED, p), wallVariant(signBlock, true).mapColor(mapColor).instrument(NoteBlockInstrument.BASS).forceSolidOn().noCollision().strength(1.0F));


        // create holder
        var wouldType = new WouldType(woodType, planks, slab, stairs, fence, fenceGate, door, trapDoorBlock, signBlock, wallSignBlock, pressurePlate, button, hangingSignBlock, wallHangingSignBlock);

        // client
        if (WouldPlatform.INSTANCE.isClient()) {
            Sheets.SIGN_SPRITES.put(wouldType.woodType(), new SpriteId(Sheets.SIGN_SHEET, Would.res("entity/signs/" + wouldType.name())));
            Sheets.HANGING_SIGN_SPRITES.put(wouldType.woodType(), new SpriteId(Sheets.SIGN_SHEET, Would.res("entity/signs/hanging/" + wouldType.name())));
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
