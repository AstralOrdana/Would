package com.ordana.would.fabric.data.provider;

import com.ordana.would.reg.BlockFactories;
import com.ordana.would.reg.ModBlockFamilies;
import com.ordana.would.reg.ModBlocks;
import com.ordana.would.reg.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static net.minecraft.data.BlockFamily.*;

public class BlockTagsProvider extends FabricTagsProvider.BlockTagsProvider {

    public BlockTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        FabricTagBuilder planksTag = this.getOrCreateTagBuilder(BlockTags.PLANKS);
        FabricTagBuilder fenceGatesTag = this.getOrCreateTagBuilder(BlockTags.FENCE_GATES);
        FabricTagBuilder standingSignsTag = this.getOrCreateTagBuilder(BlockTags.STANDING_SIGNS);
        FabricTagBuilder wallSignsTag = this.getOrCreateTagBuilder(BlockTags.WALL_SIGNS);
        FabricTagBuilder woodenButtonsTag = this.getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS);
        FabricTagBuilder woodenDoorsTag = this.getOrCreateTagBuilder(BlockTags.WOODEN_DOORS);
        FabricTagBuilder woodenFencesTag = this.getOrCreateTagBuilder(BlockTags.WOODEN_FENCES);
        FabricTagBuilder woodenPressurePlatesTag = this.getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES);
        FabricTagBuilder woodenSlabsTag = this.getOrCreateTagBuilder(BlockTags.WOODEN_SLABS);
        FabricTagBuilder woodenStairsTag = this.getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS);
        FabricTagBuilder woodenTrapdoorsTag = this.getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS);

        ModBlockFamilies.MAP.forEach((block, blockFamily) -> {
            planksTag.add(block);
            fenceGatesTag.add(blockFamily.get(Variant.FENCE_GATE));
            woodenButtonsTag.add(blockFamily.get(Variant.BUTTON));
            woodenDoorsTag.add(blockFamily.get(Variant.DOOR));
            woodenFencesTag.add(blockFamily.get(Variant.FENCE));
            woodenPressurePlatesTag.add(blockFamily.get(Variant.PRESSURE_PLATE));
            woodenSlabsTag.add(blockFamily.get(Variant.SLAB));
            woodenStairsTag.add(blockFamily.get(Variant.STAIRS));
            woodenTrapdoorsTag.add(blockFamily.get(Variant.TRAPDOOR));
            standingSignsTag.add(blockFamily.get(Variant.SIGN));
            wallSignsTag.add(blockFamily.get(Variant.WALL_SIGN));
        });

        this.getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_HOE).add(toArray(BlockFactories.LEAVES_TO_SAPLING_MAP.keySet()));

        this.getOrCreateTagBuilder(BlockTags.OVERWORLD_NATURAL_LOGS).add(
            ModBlocks.ASPEN_LOG.get(),
            ModBlocks.AZALEA_LOG.get(),
            ModBlocks.BAOBAB_LOG.get(),
            ModBlocks.BLUE_SPRUCE_LOG.get(),
            ModBlocks.CEDAR_LOG.get(),
            ModBlocks.EBONY_LOG.get(),
            ModBlocks.FIR_LOG.get(),
            ModBlocks.MAHOGANY_LOG.get(),
            ModBlocks.MAPLE_LOG.get(),
            ModBlocks.PALM_LOG.get(),
            ModBlocks.PINE_LOG.get(),
            ModBlocks.WALNUT_LOG.get()
        );

        this.addBlocksToTag(ModTags.Blocks.ASPEN_LOGS, ModBlocks.ASPEN_LOG, ModBlocks.ASPEN_LOG_GAZING, ModBlocks.STRIPPED_ASPEN_LOG, ModBlocks.STRIPPED_ASPEN_LOG_GAZING, ModBlocks.ASPEN_WOOD, ModBlocks.ASPEN_WOOD_GAZING, ModBlocks.STRIPPED_ASPEN_WOOD, ModBlocks.STRIPPED_ASPEN_WOOD_GAZING);
        this.addBlocksToTag(ModTags.Blocks.AZALEA_LOGS, ModBlocks.AZALEA_LOG, ModBlocks.STRIPPED_AZALEA_LOG, ModBlocks.AZALEA_WOOD, ModBlocks.STRIPPED_AZALEA_WOOD);
        this.addBlocksToTag(ModTags.Blocks.BAOBAB_LOGS, ModBlocks.BAOBAB_LOG, ModBlocks.STRIPPED_BAOBAB_LOG, ModBlocks.BAOBAB_WOOD, ModBlocks.STRIPPED_BAOBAB_WOOD);
        this.addBlocksToTag(ModTags.Blocks.BLUE_SPRUCE_LOGS, ModBlocks.BLUE_SPRUCE_LOG, ModBlocks.STRIPPED_BLUE_SPRUCE_LOG, ModBlocks.BLUE_SPRUCE_WOOD, ModBlocks.STRIPPED_BLUE_SPRUCE_WOOD);
        this.addBlocksToTag(ModTags.Blocks.CEDAR_LOGS, ModBlocks.CEDAR_LOG, ModBlocks.STRIPPED_CEDAR_LOG, ModBlocks.CEDAR_WOOD, ModBlocks.STRIPPED_CEDAR_WOOD);
        this.addBlocksToTag(ModTags.Blocks.EBONY_LOGS, ModBlocks.EBONY_LOG, ModBlocks.STRIPPED_EBONY_LOG, ModBlocks.EBONY_HEARTWOOD_LOG, ModBlocks.EBONY_WOOD, ModBlocks.STRIPPED_EBONY_WOOD, ModBlocks.EBONY_HEARTWOOD);
        this.addBlocksToTag(ModTags.Blocks.FIR_LOGS, ModBlocks.FIR_LOG, ModBlocks.STRIPPED_FIR_LOG, ModBlocks.FIR_WOOD, ModBlocks.STRIPPED_FIR_WOOD);
        this.addBlocksToTag(ModTags.Blocks.MAHOGANY_LOGS, ModBlocks.MAHOGANY_LOG, ModBlocks.STRIPPED_MAHOGANY_LOG, ModBlocks.MAHOGANY_WOOD, ModBlocks.STRIPPED_MAHOGANY_WOOD);
        this.addBlocksToTag(ModTags.Blocks.MAPLE_LOGS, ModBlocks.MAPLE_LOG, ModBlocks.MAPLE_LOG_SAPPY, ModBlocks.STRIPPED_MAPLE_LOG, ModBlocks.MAPLE_WOOD, ModBlocks.MAPLE_WOOD_SAPPY, ModBlocks.STRIPPED_MAPLE_WOOD);
        this.addBlocksToTag(ModTags.Blocks.PALM_LOGS, ModBlocks.PALM_LOG, ModBlocks.STRIPPED_PALM_LOG, ModBlocks.PALM_WOOD, ModBlocks.STRIPPED_PALM_WOOD);
        this.addBlocksToTag(ModTags.Blocks.PINE_LOGS, ModBlocks.PINE_LOG, ModBlocks.STRIPPED_PINE_LOG, ModBlocks.PINE_WOOD, ModBlocks.STRIPPED_PINE_WOOD);
        this.addBlocksToTag(ModTags.Blocks.WALNUT_LOGS, ModBlocks.WALNUT_LOG, ModBlocks.STRIPPED_WALNUT_LOG, ModBlocks.WALNUT_WOOD, ModBlocks.STRIPPED_WALNUT_WOOD);
        this.addBlocksToTag(ModTags.Blocks.WILLOW_LOGS, ModBlocks.WILLOW_LOG, ModBlocks.STRIPPED_WILLOW_LOG, ModBlocks.WILLOW_WOOD, ModBlocks.STRIPPED_WILLOW_WOOD);

        this.getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
            .addTag(ModTags.Blocks.ASPEN_LOGS)
            .addTag(ModTags.Blocks.AZALEA_LOGS)
            .addTag(ModTags.Blocks.BAOBAB_LOGS)
            .addTag(ModTags.Blocks.BLUE_SPRUCE_LOGS)
            .addTag(ModTags.Blocks.CEDAR_LOGS)
            .addTag(ModTags.Blocks.EBONY_LOGS)
            .addTag(ModTags.Blocks.FIR_LOGS)
            .addTag(ModTags.Blocks.MAHOGANY_LOGS)
            .addTag(ModTags.Blocks.MAPLE_LOGS)
            .addTag(ModTags.Blocks.PALM_LOGS)
            .addTag(ModTags.Blocks.PINE_LOGS)
            .addTag(ModTags.Blocks.WALNUT_LOGS)
            .addTag(ModTags.Blocks.WILLOW_LOGS);

        this.getOrCreateTagBuilder(BlockTags.CEILING_HANGING_SIGNS).add(toArray(BlockFactories.HangingSignCompound.getCeilings()));
        this.getOrCreateTagBuilder(BlockTags.WALL_HANGING_SIGNS).add(toArray(BlockFactories.HangingSignCompound.getWalls()));
        this.getOrCreateTagBuilder(BlockTags.SAPLINGS).add(toArray(BlockFactories.SaplingCompound.getSaplings()));
        this.getOrCreateTagBuilder(BlockTags.FLOWER_POTS).add(toArray(BlockFactories.SaplingCompound.getPottedSaplings()));
        this.getOrCreateTagBuilder(BlockTags.LEAVES).add(toArray(BlockFactories.LEAVES_TO_SAPLING_MAP.keySet()));
    }

    private static Block[] toArray(Collection<Block> blocks) {
        return blocks.toArray(Block[]::new);
    }

    @SafeVarargs
    private void addBlocksToTag(TagKey<Block> tagKey, Supplier<Block>... blocks) {
        this.getOrCreateTagBuilder(tagKey).add(Stream.of(blocks).map(Supplier::get).toArray(Block[]::new));
    }

}
