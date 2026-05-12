package com.ordana.would.fabric.data.provider;

import com.ordana.would.reg.*;
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
        var planksTag = this.valueLookupBuilder(BlockTags.PLANKS);
        var fenceGatesTag = this.valueLookupBuilder(BlockTags.FENCE_GATES);
        var standingSignsTag = this.valueLookupBuilder(BlockTags.STANDING_SIGNS);
        var wallSignsTag = this.valueLookupBuilder(BlockTags.WALL_SIGNS);
        var woodenButtonsTag = this.valueLookupBuilder(BlockTags.WOODEN_BUTTONS);
        var woodenDoorsTag = this.valueLookupBuilder(BlockTags.WOODEN_DOORS);
        var woodenFencesTag = this.valueLookupBuilder(BlockTags.WOODEN_FENCES);
        var woodenPressurePlatesTag = this.valueLookupBuilder(BlockTags.WOODEN_PRESSURE_PLATES);
        var woodenSlabsTag = this.valueLookupBuilder(BlockTags.WOODEN_SLABS);
        var woodenStairsTag = this.valueLookupBuilder(BlockTags.WOODEN_STAIRS);
        var woodenTrapdoorsTag = this.valueLookupBuilder(BlockTags.WOODEN_TRAPDOORS);

        ModWoodTypes.ALL.forEach((block) -> {
            planksTag.add(block.planks());
            fenceGatesTag.add(block.fenceGateBlock());
            woodenButtonsTag.add(block.button());
            woodenDoorsTag.add(block.door());
            woodenFencesTag.add(block.fenceBlock());
            woodenPressurePlatesTag.add(block.pressurePlate());
            woodenSlabsTag.add(block.slabBlock());
            woodenStairsTag.add(block.stairs());
            woodenTrapdoorsTag.add(block.trapDoorBlock());
            standingSignsTag.add(block.standingSignBlock());
            wallSignsTag.add(block.wallSignBlock());
        });

        this.valueLookupBuilder(BlockTags.MINEABLE_WITH_HOE).add(toArray(ModBlocks.ALL_SAPLINGS));

        this.valueLookupBuilder(BlockTags.OVERWORLD_NATURAL_LOGS).add(
            ModBlocks.ASPEN_LOG,
            ModBlocks.AZALEA_LOG,
            ModBlocks.BAOBAB_LOG,
            ModBlocks.BLUE_SPRUCE_LOG,
            ModBlocks.CEDAR_LOG,
            ModBlocks.EBONY_LOG,
            ModBlocks.FIR_LOG,
            ModBlocks.MAHOGANY_LOG,
            ModBlocks.MAPLE_LOG,
            ModBlocks.PALM_LOG,
            ModBlocks.PINE_LOG,
            ModBlocks.WALNUT_LOG
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

        this.valueLookupBuilder(BlockTags.LOGS_THAT_BURN)
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

        ModWoodTypes.ALL.forEach(type -> {
            this.valueLookupBuilder(BlockTags.CEILING_HANGING_SIGNS).add(type.hangingSignBlock());
            this.valueLookupBuilder(BlockTags.WALL_HANGING_SIGNS).add(type.wallHangingSignBlock());
            if (type.sapling() != null)
                this.valueLookupBuilder(BlockTags.SAPLINGS).add(type.sapling());
            if (type.leaves() != null)
                this.valueLookupBuilder(BlockTags.LEAVES).add(type.leaves());
        });
        this.valueLookupBuilder(BlockTags.FLOWER_POTS).add(toArray(ModBlocks.ALL_POTTED_SAPLINGS));

    }

    private static Block[] toArray(Collection<Block> blocks) {
        return blocks.toArray(Block[]::new);
    }

    @SafeVarargs
    private void addBlocksToTag(TagKey<Block> tagKey, Block... blocks) {
        this.valueLookupBuilder(tagKey).add(Stream.of(blocks).toArray(Block[]::new));
    }

}
