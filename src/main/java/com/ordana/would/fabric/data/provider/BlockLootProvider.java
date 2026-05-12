package com.ordana.would.fabric.data.provider;

import com.ordana.would.reg.*;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import org.apache.commons.compress.utils.Lists;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Optional;
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

        List<Block> selfDroppers = Lists.newArrayList();
        selfDroppers.add(ModBlocks.COCONUT);

        selfDroppers.forEach(this::dropSelf);


        ModWoodTypes.ALL.forEach(type->{
            if (type == null) return;
            this.add(type.door(), this.createDoorTable(type.door()));
            this.add(type.slabBlock(), this.createSlabItemTable(type.slabBlock()));
            this.dropSelf(type.planks());
            this.dropSelf(type.log());
            this.dropSelf(type.wood());
            if (type.sapling() != null)
                this.dropSelf(type.sapling());
            this.dropSelf(type.hangingSignBlock());
            this.createModLeavesDrops(type.leaves(), type.sapling());
        });


        ModBlocks.ALL_POTTED_SAPLINGS.forEach(this::dropPottedContents);


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

    private LootTable.Builder createModLeavesDrops(Block block, @Nullable Block sapling) {
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

        return this.createLeavesDrops(block, Optional.ofNullable(sapling).orElse(Blocks.AIR), NORMAL_LEAVES_SAPLING_CHANCES);
    }

    private void createModLogDrops(WouldType compound) {
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

}
