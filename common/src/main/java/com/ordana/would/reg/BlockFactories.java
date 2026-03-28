package com.ordana.would.reg;

import com.google.common.collect.Maps;
import com.ordana.would.Would;
import com.ordana.would.blocks.ModWoodenButtonBlock;
import com.ordana.would.blocks.ModPressurePlateBlock;
import com.ordana.would.blocks.ModSaplingBlock;
import net.mehvahdjukaar.moonlight.api.block.ModStairBlock;
import net.mehvahdjukaar.moonlight.api.misc.RegSupplier;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import org.apache.commons.compress.utils.Lists;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public final class BlockFactories {

    public static final List<LogCompound> ALL_LOGS = Lists.newArrayList();
    public static final List<HangingSignCompound> ALL_HANGING_SIGNS = Lists.newArrayList();
    public static final List<SaplingCompound> ALL_SAPLINGS = Lists.newArrayList();

    public static final Map<Block, Block> LEAVES_TO_SAPLING_MAP = Maps.newHashMap(); // map instead of list for loot dropping purposes

    public static final BlockBehaviour.StateArgumentPredicate<EntityType<?>> OCELOT_OR_PARROT = (blockState, blockGetter, blockPos, entityType) -> entityType == EntityType.OCELOT || entityType == EntityType.PARROT;
    public static final BlockBehaviour.StatePredicate NEVER = (blockState, blockGetter, blockPos) -> false;

    private static BlockBehaviour.Properties simplePlankProperties(Supplier<Block> plankVariantSupplier) {
        return simplePlankProperties(plankVariantSupplier.get().defaultMapColor());
    }

    private static BlockBehaviour.Properties simplePlankProperties(MapColor mapColor) {
        return BlockBehaviour.Properties.of()
            .mapColor(mapColor)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F, 3.0F)
            .sound(SoundType.WOOD)
            .ignitedByLava();
    }

    static <T extends Block> Supplier<T> register(String name, Supplier<T> blockFactory, boolean withItem) {
        ResourceLocation res = Would.res(name);
        RegSupplier<T> block = RegHelper.registerBlock(res, blockFactory);

        if (withItem)
            RegHelper.registerItem(res, () -> new BlockItem(block.get(), new Item.Properties()));

        return block;
    }

    static Supplier<Block> log(String name) {
        return log(name, MapColor.WOOD);
    }

    static Supplier<Block> log(String name, MapColor mapColor) {
        return log(name, mapColor, mapColor);
    }

    static Supplier<Block> log(String name, MapColor topMapColor, MapColor sideMapColor) {
        return register(name, () -> new RotatedPillarBlock(BlockBehaviour.Properties.of()
            .mapColor(blockState -> blockState.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .sound(SoundType.WOOD)
            .ignitedByLava()
        ), true);
    }

    static Supplier<Block> wood(String name, Supplier<Block> logVariantSupplier) {
        return wood(name, logVariantSupplier, MapColor.WOOD);
    }

    static Supplier<Block> wood(String name, Supplier<Block> logVariantSupplier, MapColor mapColor) {
        return register(name, () -> (Block) new RotatedPillarBlock(simplePlankProperties(mapColor).explosionResistance(2.0F)), true);
    }

    static Supplier<Block> planks(String name) {
        return planks(name, MapColor.WOOD);
    }

    static Supplier<Block> planks(String name, MapColor mapColor) {
        return register(name, () -> new Block(simplePlankProperties(mapColor)), true);
    }

    static Supplier<Block> slab(String name, Supplier<Block> planksVariantSupplier) {
        return register(name, () -> new SlabBlock(simplePlankProperties(planksVariantSupplier)), true);
    }

    @SuppressWarnings("deprecation")
    static Supplier<Block> stairs(String name, Supplier<Block> planksVariantSupplier) {
        return register(name, () -> new ModStairBlock(planksVariantSupplier, BlockBehaviour.Properties.ofLegacyCopy(planksVariantSupplier.get())), true);
    }

    static Supplier<Block> fence(String name, Supplier<Block> planksVariantSupplier) {
        return register(name, () -> new FenceBlock(simplePlankProperties(planksVariantSupplier).forceSolidOn()), true);
    }

    static Supplier<Block> fenceGate(String name, Supplier<Block> planksVariantSupplier, WoodType woodType) {
        return register(name, () -> new FenceGateBlock(woodType, simplePlankProperties(planksVariantSupplier).forceSolidOn()), true);
    }

    static Supplier<Block> button(String name, BlockSetType blockSetType) {
        return register(name, () -> new ModWoodenButtonBlock(blockSetType, BlockBehaviour.Properties.of()
            .noCollission()
            .strength(0.5F)
            .pushReaction(PushReaction.DESTROY)
        ), true);
    }

    static Supplier<Block> door(String name, Supplier<Block> planksVariantSupplier, BlockSetType blockSetType) {
        return register(name, () -> new DoorBlock(blockSetType, simplePlankProperties(planksVariantSupplier)
            .noOcclusion()
            .strength(3.0F)
            .pushReaction(PushReaction.DESTROY)
        ), true);
    }

    static Supplier<Block> trapdoor(String name, Supplier<Block> planksVariantSupplier, BlockSetType blockSetType) {
        return register(name, () -> new TrapDoorBlock(blockSetType, simplePlankProperties(planksVariantSupplier)
            .noOcclusion()
            .strength(3.0F)
            .isValidSpawn((blockState, blockGetter, blockPos, entityType) -> false)
        ), true);
    }

    static Supplier<Block> pressurePlate(String name, Supplier<Block> planksVariantSupplier, BlockSetType blockSetType) {
        return register(name, () -> new ModPressurePlateBlock(blockSetType, simplePlankProperties(planksVariantSupplier)
            .forceSolidOn()
            .noCollission()
            .strength(0.5F)
            .pushReaction(PushReaction.DESTROY)
        ), true);
    }

    static Supplier<Block> sapling(String name, TreeGrower treeGrower) {
        return register(name, () -> new ModSaplingBlock(treeGrower, BlockBehaviour.Properties.of()
            .mapColor(MapColor.PLANT)
            .noCollission()
            .randomTicks()
            .instabreak()
            .sound(SoundType.GRASS)
            .pushReaction(PushReaction.DESTROY)
        ), true);
    }

    static Supplier<Block> pottedSapling(String name, Supplier<Block> contentSupplier) {
        return register(name, () -> {
            FlowerPotBlock block = new FlowerPotBlock(contentSupplier.get(), BlockBehaviour.Properties.of()
                .instabreak()
                .noOcclusion()
                .pushReaction(PushReaction.DESTROY)
            );
            ALL_SAPLINGS.add(new SaplingCompound(block.getPotted(), block));

            return block;
        }, false);
    }

    static Supplier<Block> standingSign(String name, Supplier<Block> planksVariantSupplier, WoodType woodType) {
        return register(name, () -> new StandingSignBlock(woodType, BlockBehaviour.Properties.of()
            .mapColor(planksVariantSupplier.get().defaultMapColor())
            .forceSolidOn()
            .instrument(NoteBlockInstrument.BASS)
            .noCollission()
            .strength(1.0F)
            .ignitedByLava()
        ), false);
    }

    static Supplier<Block> wallSign(String name, Supplier<Block> standingVariantSupplier, WoodType woodType) {
        return register(name, () -> {
            Block standing = standingVariantSupplier.get();
            return new WallSignBlock(woodType, BlockBehaviour.Properties.ofFullCopy(standing).dropsLike(standing));
        }, false);
    }

    static Supplier<Block> ceilingHangingSign(String name, Supplier<Block> strippedLogSupplier, WoodType woodType) {
        return register(name, () -> new CeilingHangingSignBlock(woodType, BlockBehaviour.Properties.of()
            .mapColor(strippedLogSupplier.get().defaultMapColor())
            .forceSolidOn()
            .instrument(NoteBlockInstrument.BASS)
            .noCollission()
            .strength(1.0F)
            .ignitedByLava()
        ), false);
    }

    static Supplier<Block> wallHangingSign(String name, Supplier<Block> strippedLogSupplier, Supplier<Block> ceilingVariantSupplier, WoodType woodType) {
        return wallHangingSign(name, strippedLogSupplier, ceilingVariantSupplier, MapColor.WOOD, woodType);
    }

    static Supplier<Block> wallHangingSign(String name, Supplier<Block> strippedLogSupplier, Supplier<Block> ceilingVariantSupplier, MapColor mapColor, WoodType woodType) {
        return register(name, () -> new WallHangingSignBlock(woodType, BlockBehaviour.Properties.of()
            .mapColor(mapColor)
            .forceSolidOn()
            .instrument(NoteBlockInstrument.BASS)
            .noCollission()
            .strength(1.0F)
            .ignitedByLava()
            .dropsLike(ceilingVariantSupplier.get())
        ), false);
    }

    static Supplier<Block> leaves(String name, Supplier<Block> saplingSupplier) {
        return leaves(name, saplingSupplier, MapColor.PLANT);
    }

    static Supplier<Block> leaves(String name, Supplier<Block> saplingSupplier, MapColor mapColor) {
        return leaves(name, saplingSupplier, LeavesBlock::new, mapColor);
    }

    static <T extends LeavesBlock> Supplier<Block> leaves(String name, Supplier<Block> saplingSupplier, Function<BlockBehaviour.Properties, T> function, MapColor mapColor) {
        return register(name, () -> function.apply(BlockBehaviour.Properties.of()
            .mapColor(mapColor)
            .strength(0.2F)
            .randomTicks()
            .sound(SoundType.AZALEA_LEAVES)
            .noOcclusion()
            .isValidSpawn(OCELOT_OR_PARROT)
            .isSuffocating(NEVER)
            .isViewBlocking(NEVER)
            .ignitedByLava()
            .pushReaction(PushReaction.DESTROY)
            .isRedstoneConductor(NEVER)
        ), true);
    }

    public record HangingSignCompound(Block strippedLog, Block ceiling, Block wall) {

        public static List<Block> getCeilings() {
            return ALL_HANGING_SIGNS.stream().map(HangingSignCompound::ceiling).toList();
        }

        public static List<Block> getWalls() {
            return ALL_HANGING_SIGNS.stream().map(HangingSignCompound::wall).toList();
        }

    }

    public record LogCompound(Block log, Block wood) {

        public static List<Block> getLogs() {
            return ALL_LOGS.stream().map(LogCompound::log).toList();
        }

        public static List<Block> getWood() {
            return ALL_LOGS.stream().map(LogCompound::wood).toList();
        }

    }

    public record SaplingCompound(Block sapling, Block pottedSapling) {

        public static List<Block> getSaplings() {
            return ALL_SAPLINGS.stream().map(SaplingCompound::sapling).toList();
        }

        public static List<Block> getPottedSaplings() {
            return ALL_SAPLINGS.stream().map(SaplingCompound::pottedSapling).toList();
        }

    }

}
