package com.ordana.would.reg;

import com.ordana.would.blocks.ModSaplingBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class BlockFactories {

    static BlockBehaviour.Properties logProperties(MapColor topMapColor, MapColor sideMapColor, SoundType soundType) {
        return (BlockBehaviour.Properties.of().mapColor((blockState) -> blockState.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(soundType).ignitedByLava());
    }

    static BlockBehaviour.Properties woodProperties(MapColor mapColor, SoundType soundType) {
        return BlockBehaviour.Properties.of().mapColor(mapColor).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(soundType).ignitedByLava();
    }

    static boolean never(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return false;
    }

    static Block regLeaves(String id, MapColor mapColor, SoundType type) {
        return ModBlocks.regBlock(id, (p)->new TintedParticleLeavesBlock(0.01f, p), BlockBehaviour.Properties.of().mapColor(mapColor).strength(0.2F).randomTicks().sound(type).noOcclusion().isValidSpawn(ModBlocks::ocelotOrParrot).isSuffocating(BlockFactories::never).isViewBlocking(BlockFactories::never).ignitedByLava().pushReaction(PushReaction.DESTROY).isRedstoneConductor(BlockFactories::never));
    }

    static Block regLeaves(String id, SoundType type) {
        return regLeaves(id, MapColor.PLANT, type);
    }

    static Block sapling(String id, TreeGrower treeGrower) {
        return ModBlocks.regBlock(id, p-> new ModSaplingBlock(treeGrower, p), BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollision().randomTicks().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY));
    }

    static FlowerPotBlock pottedSapling(Block content) {
        return ModBlocks.regBlock("potted_"+ content.properties().blockIdOrThrow().identifier().getPath(), (BlockBehaviour.Properties potted) -> new FlowerPotBlock(content, potted), BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY));
    }

    static BlockBehaviour.Properties plankProperties(MapColor mapColor, SoundType soundType) {
        return BlockBehaviour.Properties.of().mapColor(mapColor).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(soundType).ignitedByLava();
    }

    static BlockBehaviour.Properties slab(MapColor mapColor, SoundType soundType) {
        return BlockBehaviour.Properties.of().mapColor(mapColor).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(soundType).ignitedByLava();
    }

    static BlockBehaviour.Properties fence(MapColor mapColor, SoundType soundType) {
        return BlockBehaviour.Properties.of().mapColor(mapColor).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).ignitedByLava().sound(soundType);
    }

    static BlockBehaviour.Properties fenceGate(MapColor mapColor, SoundType soundType, WoodType woodType) {
        return BlockBehaviour.Properties.of().mapColor(mapColor).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).ignitedByLava().sound(soundType);
    }
}
