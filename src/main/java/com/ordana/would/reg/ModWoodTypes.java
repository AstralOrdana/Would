package com.ordana.would.reg;

import com.ordana.would.Would;
import com.ordana.would.WouldPlatform;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;

import static com.ordana.would.reg.ModBlocks.*;


public interface ModWoodTypes {

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
        WoodType woodType = WoodType.register(new WoodType(Would.res(name).toString(), blockSetType));
        BlockBehaviour.Properties settings = plankProperties(mapColor, SoundType.WOOD);
        Block planks = ModBlocks.regWithItem(name+"_planks", Block::new,
                settings, true);

        Block slab = ModBlocks.regWithItem(name+"_slab", SlabBlock::new,
                slab(mapColor, SoundType.WOOD));

        Block stairs = ModBlocks.regWithItem(name+"_stairs", properties -> new StairBlock(planks.defaultBlockState(), properties),
                settings);

        FenceBlock fence = ModBlocks.regWithItem( name+"_fence_gate", FenceBlock::new,
                fence(mapColor, soundType));

        Block fenceGate = ModBlocks.regWithItem( name+"_fence_gate", properties -> new FenceGateBlock(woodType, properties),
                fenceGate(mapColor, soundType, woodType));

        var wouldType = new WouldType(woodType, planks, slab, stairs, fence, fenceGate);

        if (WouldPlatform.INSTANCE.isClient()) {
            Sheets.SIGN_SPRITES.put(wouldType.woodType(), new SpriteId(Sheets.SIGN_SHEET, Would.res("entity/signs/" + wouldType.name())));
            Sheets.HANGING_SIGN_SPRITES.put(wouldType.woodType(), new SpriteId(Sheets.SIGN_SHEET, Would.res("entity/signs/hanging/" + wouldType.name())));
        }

        return wouldType;
    }

    static void init() {}

}
