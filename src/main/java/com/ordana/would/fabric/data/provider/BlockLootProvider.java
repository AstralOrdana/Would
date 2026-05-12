package com.ordana.would.fabric.data.provider;

import com.ordana.would.reg.BlockFactories;
import com.ordana.would.reg.ModBlockFamilies;
import com.ordana.would.reg.ModBlocks;
import com.ordana.would.reg.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import org.apache.commons.compress.utils.Lists;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class BlockLootProvider extends FabricBlockLootSubProvider {

    private static final float[] FRUITING_LEAVES_FORTUNE_DROP_CHANCES = new float[] {
        1 / 200.0F, // unenchanted
        1 / 180.0F, // fortune I
        1 / 160.0F, // fortune II
        1 / 120.0F, // fortune III
        1 / 40.0F // fortune IV+ (impossible)
    };

    public BlockLootProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        /*
        List<Block> selfDroppers = Lists.newArrayList();

        selfDroppers.addAll(BlockFactories.SaplingCompound.getSaplings());
        selfDroppers.addAll(BlockFactories.HangingSignCompound.getCeilings());
        selfDroppers.add(ModBlocks.COCONUT);

        selfDroppers.forEach(this::dropSelf);

        ModBlockFamilies.MAP.values().forEach(blockFamily -> blockFamily.getVariants().forEach((variant, variantBlock) -> {
            switch (variant) {
                case DOOR -> this.add(variantBlock, this.createDoorTable(variantBlock));
                case SLAB -> this.add(variantBlock, this.createSlabItemTable(variantBlock));
                default -> this.dropSelf(variantBlock);
            }
        }));

        BlockFactories.ALL_LOGS.forEach(this::createModLogDrops);
        BlockFactories.LEAVES_TO_SAPLING_MAP.keySet().forEach(block -> this.add(block, this.createModLeavesDrops(block)));
        BlockFactories.SaplingCompound.getPottedSaplings().forEach(this::dropPottedContents);

         */
    }

    private LootTable.Builder createShearsOrSilkTouchDrops(Block block) {
        return LootTable.lootTable()
            .withPool(LootPool.lootPool()
                .when(this.hasShearsOrSilkTouch())
                .setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(block))
            );
    }

    private LootTable.Builder createFruitingLeavesDrops(Block leaves, Block sapling, ItemLike fruit) {
        HolderLookup.RegistryLookup<Enchantment> registryLookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createLeavesDrops(leaves, sapling, NORMAL_LEAVES_SAPLING_CHANCES)
            .withPool(LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1.0F))
                .when(this.doesNotHaveShearsOrSilkTouch())
                .add(this.applyExplosionCondition(leaves, LootItem.lootTableItem(fruit))
                    .when(BonusLevelTableCondition.bonusLevelFlatChance(registryLookup.getOrThrow(Enchantments.FORTUNE), FRUITING_LEAVES_FORTUNE_DROP_CHANCES))
                )
            );
    }

    /*FIXME
    private LootTable.Builder createModLeavesDrops(Block block) {
        if (block == ModBlocks.PALM_LEAVES)
            return this.createShearsOrSilkTouchDrops(block);

        if (block == ModBlocks.EBONY_LEAVES_FRUITING) {
            return this.createLeavesDrops(block, ModBlocks.EBONY_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES)
                .withPool(LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1.0F))
                    .when(this.doesNotHaveShearsOrSilkTouch())
                    .add(this.applyExplosionCondition(block, LootItem.lootTableItem(Items.GOLDEN_APPLE)))
                );
        }

        if (block == ModBlocks.WALNUT_LEAVES)
            return this.createFruitingLeavesDrops(block, ModBlocks.WALNUT_SAPLING, ModItems.WALNUT);

        return this.createLeavesDrops(block, BlockFactories.LEAVES_TO_SAPLING_MAP.get(block), NORMAL_LEAVES_SAPLING_CHANCES);
    }

    private void createModLogDrops(BlockFactories.LogCompound compound) {
        Block log = compound.log();
        Block wood = compound.wood();

        if (log == ModBlocks.MAPLE_LOG_SAPPY) {
            this.add(log, this.createSingleItemTableWithSilkTouch(log, ModBlocks.MAPLE_LOG));
            this.add(wood, this.createSingleItemTableWithSilkTouch(wood, ModBlocks.MAPLE_WOOD));
        }
        else {
            this.dropSelf(log);
            this.dropSelf(wood);
        }
    }

     */

}
