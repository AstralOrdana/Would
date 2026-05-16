//? fabric {
/*package com.ordana.would.fabric.data.provider;

import com.ordana.would.reg.ModBlocks;
import com.ordana.would.reg.ModItems;
import com.ordana.would.reg.ModTags;
import com.ordana.would.reg.ModWoodTypes;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class ItemTagsProvider extends FabricTagsProvider.ItemTagsProvider {

    public ItemTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture, com.ordana.would.fabric.data.provider.BlockTagsProvider blockTags) {
        super(output, registriesFuture, blockTags);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        var planksTag = this.valueLookupBuilder(ItemTags.PLANKS);
        var fenceGatesTag = this.valueLookupBuilder(ItemTags.FENCE_GATES);
        var woodenButtonsTag = this.valueLookupBuilder(ItemTags.WOODEN_BUTTONS);
        var woodenDoorsTag = this.valueLookupBuilder(ItemTags.WOODEN_DOORS);
        var woodenFencesTag = this.valueLookupBuilder(ItemTags.WOODEN_FENCES);
        var woodenPressurePlatesTag = this.valueLookupBuilder(ItemTags.WOODEN_PRESSURE_PLATES);
        var woodenSlabsTag = this.valueLookupBuilder(ItemTags.WOODEN_SLABS);
        var woodenStairsTag = this.valueLookupBuilder(ItemTags.WOODEN_STAIRS);
        var woodenTrapdoorsTag = this.valueLookupBuilder(ItemTags.WOODEN_TRAPDOORS);

        ModWoodTypes.ALL.forEach((block) -> {
            planksTag.add(block.planks().asItem());
            fenceGatesTag.add(block.fenceGateBlock().asItem());
            woodenButtonsTag.add(block.button().asItem());
            woodenDoorsTag.add(block.door().asItem());
            woodenFencesTag.add(block.fenceBlock().asItem());
            woodenPressurePlatesTag.add(block.pressurePlate().asItem());
            woodenSlabsTag.add(block.slabBlock().asItem());
            woodenStairsTag.add(block.stairs().asItem());
            woodenTrapdoorsTag.add(block.trapDoorBlock().asItem());
        });

        this.addBlocksToItemTag(ModTags.Items.ASPEN_LOGS, ModBlocks.ASPEN_LOG, ModBlocks.ASPEN_LOG_GAZING, ModBlocks.STRIPPED_ASPEN_LOG, ModBlocks.STRIPPED_ASPEN_LOG_GAZING, ModBlocks.ASPEN_WOOD, ModBlocks.ASPEN_WOOD_GAZING, ModBlocks.STRIPPED_ASPEN_WOOD, ModBlocks.STRIPPED_ASPEN_WOOD_GAZING);
        this.addBlocksToItemTag(ModTags.Items.AZALEA_LOGS, ModBlocks.AZALEA_LOG, ModBlocks.STRIPPED_AZALEA_LOG, ModBlocks.AZALEA_WOOD, ModBlocks.STRIPPED_AZALEA_WOOD);
        this.addBlocksToItemTag(ModTags.Items.BAOBAB_LOGS, ModBlocks.BAOBAB_LOG, ModBlocks.STRIPPED_BAOBAB_LOG, ModBlocks.BAOBAB_WOOD, ModBlocks.STRIPPED_BAOBAB_WOOD);
        this.addBlocksToItemTag(ModTags.Items.BLUE_SPRUCE_LOGS, ModBlocks.BLUE_SPRUCE_LOG, ModBlocks.STRIPPED_BLUE_SPRUCE_LOG, ModBlocks.BLUE_SPRUCE_WOOD, ModBlocks.STRIPPED_BLUE_SPRUCE_WOOD);
        this.addBlocksToItemTag(ModTags.Items.CEDAR_LOGS, ModBlocks.CEDAR_LOG, ModBlocks.STRIPPED_CEDAR_LOG, ModBlocks.CEDAR_WOOD, ModBlocks.STRIPPED_CEDAR_WOOD);
        this.addBlocksToItemTag(ModTags.Items.EBONY_LOGS, ModBlocks.EBONY_LOG, ModBlocks.STRIPPED_EBONY_LOG, ModBlocks.EBONY_HEARTWOOD_LOG, ModBlocks.EBONY_WOOD, ModBlocks.STRIPPED_EBONY_WOOD, ModBlocks.EBONY_HEARTWOOD);
        this.addBlocksToItemTag(ModTags.Items.FIR_LOGS, ModBlocks.FIR_LOG, ModBlocks.STRIPPED_FIR_LOG, ModBlocks.FIR_WOOD, ModBlocks.STRIPPED_FIR_WOOD);
        this.addBlocksToItemTag(ModTags.Items.MAHOGANY_LOGS, ModBlocks.MAHOGANY_LOG, ModBlocks.STRIPPED_MAHOGANY_LOG, ModBlocks.MAHOGANY_WOOD, ModBlocks.STRIPPED_MAHOGANY_WOOD);
        this.addBlocksToItemTag(ModTags.Items.MAPLE_LOGS, ModBlocks.MAPLE_LOG, ModBlocks.MAPLE_LOG_SAPPY, ModBlocks.STRIPPED_MAPLE_LOG, ModBlocks.MAPLE_WOOD, ModBlocks.MAPLE_WOOD_SAPPY, ModBlocks.STRIPPED_MAPLE_WOOD);
        this.addBlocksToItemTag(ModTags.Items.PALM_LOGS, ModBlocks.PALM_LOG, ModBlocks.STRIPPED_PALM_LOG, ModBlocks.PALM_WOOD, ModBlocks.STRIPPED_PALM_WOOD);
        this.addBlocksToItemTag(ModTags.Items.PINE_LOGS, ModBlocks.PINE_LOG, ModBlocks.STRIPPED_PINE_LOG, ModBlocks.PINE_WOOD, ModBlocks.STRIPPED_PINE_WOOD);
        this.addBlocksToItemTag(ModTags.Items.WALNUT_LOGS, ModBlocks.WALNUT_LOG, ModBlocks.STRIPPED_WALNUT_LOG, ModBlocks.WALNUT_WOOD, ModBlocks.STRIPPED_WALNUT_WOOD);
        this.addBlocksToItemTag(ModTags.Items.WILLOW_LOGS, ModBlocks.WILLOW_LOG, ModBlocks.STRIPPED_WILLOW_LOG, ModBlocks.WILLOW_WOOD, ModBlocks.STRIPPED_WILLOW_WOOD);

        this.valueLookupBuilder(ItemTags.HANGING_SIGNS).add(toArray(ModItems.ALL_HANGING_SIGNS));
        this.valueLookupBuilder(ItemTags.SIGNS).add(toArray(ModItems.ALL_SIGNS));
        this.valueLookupBuilder(ItemTags.BOATS).add(toArray(ModItems.ALL_BOATS));
        this.valueLookupBuilder(ItemTags.CHEST_BOATS).add(toArray(ModItems.ALL_CHEST_BOATS));
        copy(BlockTags.LEAVES, ItemTags.LEAVES);
        copy(BlockTags.SAPLINGS, ItemTags.SAPLINGS);
        copy(BlockTags.LOGS_THAT_BURN, ItemTags.LOGS_THAT_BURN);

    }

    private static Item[] toArray(Collection<? extends ItemLike> blocks) {
        return blocks.stream().map(ItemLike::asItem).toArray(Item[]::new);
    }

    @SafeVarargs
    private void addBlocksToItemTag(TagKey<Item> tagKey, Block... blocks) {
        this.valueLookupBuilder(tagKey).add(Stream.of(blocks).map(Block::asItem).toArray(Item[]::new));
    }

}
*///?}