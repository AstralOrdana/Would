package com.ordana.would.fabric.data.provider;

import com.ordana.would.reg.*;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static net.minecraft.data.BlockFamily.*;

public class ItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public ItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        FabricTagBuilder planksTag = this.getOrCreateTagBuilder(ItemTags.PLANKS);
        FabricTagBuilder fenceGatesTag = this.getOrCreateTagBuilder(ItemTags.FENCE_GATES);
        FabricTagBuilder woodenButtonsTag = this.getOrCreateTagBuilder(ItemTags.WOODEN_BUTTONS);
        FabricTagBuilder woodenDoorsTag = this.getOrCreateTagBuilder(ItemTags.WOODEN_DOORS);
        FabricTagBuilder woodenFencesTag = this.getOrCreateTagBuilder(ItemTags.WOODEN_FENCES);
        FabricTagBuilder woodenPressurePlatesTag = this.getOrCreateTagBuilder(ItemTags.WOODEN_PRESSURE_PLATES);
        FabricTagBuilder woodenSlabsTag = this.getOrCreateTagBuilder(ItemTags.WOODEN_SLABS);
        FabricTagBuilder woodenStairsTag = this.getOrCreateTagBuilder(ItemTags.WOODEN_STAIRS);
        FabricTagBuilder woodenTrapdoorsTag = this.getOrCreateTagBuilder(ItemTags.WOODEN_TRAPDOORS);

        ModBlockFamilies.MAP.forEach((block, blockFamily) -> {
            planksTag.add(block.asItem());
            fenceGatesTag.add(blockFamily.get(Variant.FENCE_GATE).asItem());
            woodenButtonsTag.add(blockFamily.get(Variant.BUTTON).asItem());
            woodenDoorsTag.add(blockFamily.get(Variant.DOOR).asItem());
            woodenFencesTag.add(blockFamily.get(Variant.FENCE).asItem());
            woodenPressurePlatesTag.add(blockFamily.get(Variant.PRESSURE_PLATE).asItem());
            woodenSlabsTag.add(blockFamily.get(Variant.SLAB).asItem());
            woodenStairsTag.add(blockFamily.get(Variant.STAIRS).asItem());
            woodenTrapdoorsTag.add(blockFamily.get(Variant.TRAPDOOR).asItem());
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

        this.getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN)
            .addTag(ModTags.Items.ASPEN_LOGS)
            .addTag(ModTags.Items.AZALEA_LOGS)
            .addTag(ModTags.Items.BAOBAB_LOGS)
            .addTag(ModTags.Items.BLUE_SPRUCE_LOGS)
            .addTag(ModTags.Items.CEDAR_LOGS)
            .addTag(ModTags.Items.EBONY_LOGS)
            .addTag(ModTags.Items.FIR_LOGS)
            .addTag(ModTags.Items.MAHOGANY_LOGS)
            .addTag(ModTags.Items.MAPLE_LOGS)
            .addTag(ModTags.Items.PALM_LOGS)
            .addTag(ModTags.Items.PINE_LOGS)
            .addTag(ModTags.Items.WALNUT_LOGS)
            .addTag(ModTags.Items.WILLOW_LOGS);

        this.getOrCreateTagBuilder(ItemTags.HANGING_SIGNS).add(toArray(ModItems.ALL_HANGING_SIGNS));
        this.getOrCreateTagBuilder(ItemTags.SIGNS).add(toArray(ModItems.ALL_SIGNS));
        this.getOrCreateTagBuilder(ItemTags.BOATS).add(toArray(ModItems.ALL_BOATS));
        this.getOrCreateTagBuilder(ItemTags.CHEST_BOATS).add(toArray(ModItems.ALL_CHEST_BOATS));
        this.getOrCreateTagBuilder(ItemTags.LEAVES).add(toArray(BlockFactories.LEAVES_TO_SAPLING_MAP.keySet()));
        this.getOrCreateTagBuilder(ItemTags.SAPLINGS).add(toArray(BlockFactories.SaplingCompound.getSaplings()));
    }

    private static Item[] toArray(Collection<? extends ItemLike> blocks) {
        return blocks.stream().map(ItemLike::asItem).toArray(Item[]::new);
    }

    @SafeVarargs
    private void addBlocksToItemTag(TagKey<Item> tagKey, Supplier<Block>... blocks) {
        this.getOrCreateTagBuilder(tagKey).add(Stream.of(blocks).map(blockSupplier -> blockSupplier.get().asItem()).toArray(Item[]::new));
    }

}
