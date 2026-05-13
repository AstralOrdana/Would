package com.ordana.would.blocks;

import com.ordana.would.entities.FallingCoconutEntity;
import com.ordana.would.reg.ModBlocks;
import com.ordana.would.reg.ModItems;
import com.ordana.would.reg.ModTreeGrowers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class CoconutBlock extends SaplingBlock implements Fallable, BonemealableBlock {

    private static final float BONEMEAL_SUCCESS_CHANCE = 0.45F;

    protected static final VoxelShape SHAPE = Block.box(4.0, 0.0, 4.0, 12.0, 8.0, 12.0);

    public static final BooleanProperty PERSISTENT = BlockStateProperties.PERSISTENT;
    public static final BooleanProperty HANGING = BlockStateProperties.HANGING;

    public CoconutBlock(Properties properties) {
        super(ModTreeGrowers.COCONUT, properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(PERSISTENT, false).setValue(HANGING, false));
    }

    private static boolean mayGrowOn(BlockState blockState) {
        return blockState.is(BlockTags.DIRT) || blockState.is(BlockTags.SAND);
    }

    @Override
    @NotNull
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        if (mayGrowOn(level.getBlockState(pos.below())) && !state.getValue(PERSISTENT))
            level.levelEvent(LevelEvent.PARTICLES_EGG_CRACK, pos, 0);
    }

    @Override
    protected void onProjectileHit(Level level, BlockState state, BlockHitResult hit, Projectile projectile) {
        BlockPos blockPos = hit.getBlockPos();

        if (level instanceof ServerLevel serverLevel && projectile.mayInteract(serverLevel, blockPos) && serverLevel.getGameRules().get(GameRules.PROJECTILES_CAN_BREAK_BLOCKS)) {
            level.removeBlock(blockPos, false);
            // popResource(level, blockPos, ModItems.COCONUT.get().getDefaultInstance()); // accounting for loot table disparity
        }
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (mayGrowOn(level.getBlockState(pos.below())) && !state.getValue(PERSISTENT))
            super.randomTick(state, level, pos, random);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return Block.canSupportCenter(level, pos.below(), Direction.UP);
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess ticks, BlockPos pos, Direction directionToNeighbour, BlockPos neighbourPos, BlockState neighbourState, RandomSource random) {
        ticks.scheduleTick(pos, this, this.getFallDelay());
        return state;
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (FallingBlock.isFree(level.getBlockState(pos.below())) && pos.getY() >= level.getMinY()) {
            FallingBlockEntity entity = FallingCoconutEntity.fall(level, pos, state);
            entity.setHurtsEntities(1.0F, 6);
        }
    }

    protected int getFallDelay() {
        return 2;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return mayGrowOn(level.getBlockState(pos.below()));
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return random.nextFloat() < BONEMEAL_SUCCESS_CHANCE;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        this.advanceTree(level, pos, state, random);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(STAGE, PERSISTENT, HANGING);
    }

}
