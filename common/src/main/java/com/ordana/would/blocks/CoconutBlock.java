package com.ordana.would.blocks;

import com.ordana.would.blocks.tree_growers.PalmTreeGrower;
import com.ordana.would.entities.FallingCoconutEntity;
import com.ordana.would.reg.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Fallable;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CoconutBlock extends SaplingBlock implements Fallable {
    protected static final VoxelShape GREEN_SHAPE;
    protected static final VoxelShape BROWN_SHAPE;
    public static final BooleanProperty GREEN = BlockStateProperties.HANGING;
    public static final BooleanProperty GROWABLE = BlockStateProperties.ENABLED;

    public CoconutBlock(Properties properties) {
        super(new PalmTreeGrower(), properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(GREEN, false).setValue(GROWABLE, true));
    }

    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(BlockTags.DIRT) || state.is(BlockTags.SAND);
    }

    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return state.getValue(GREEN) ? GREEN_SHAPE : BROWN_SHAPE;
    }

    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (state.getValue(GREEN)) {
            if (random.nextInt(10) == 7) {
                state.setValue(GREEN, false);
                level.scheduleTick(pos, this, this.getFallDelay());
            }
        }

        else if (state.getValue(GROWABLE) && level.getMaxLocalRawBrightness(pos.above()) >= 9 && random.nextInt(7) == 0) {
            this.advanceTree(level, pos, state, random);
        }

    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        if (context.getLevel().getBlockState(context.getClickedPos().above()).is(ModBlocks.PALM_LEAVES.get())) return this.defaultBlockState().setValue(GREEN, true);
        return this.defaultBlockState();
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return state.getValue(GREEN) ? (level.getBlockState(pos.above()).is(ModBlocks.PALM_LEAVES.get()) ||
                this.mayPlaceOn(level.getBlockState(pos.below()), level, pos)) : super.canSurvive(state, level, pos);
    }

    public void onLand(Level level, BlockPos pos, BlockState state, BlockState replaceableState, FallingBlockEntity fallingBlock) {
        level.setBlockAndUpdate(pos, state.setValue(GREEN, false).setValue(GROWABLE, false));
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (!state.canSurvive(level, pos)) {
            level.destroyBlock(pos, false);
        }
        return state;
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (canFallThrough(level.getBlockState(pos.below())) && pos.getY() >= level.getMinBuildHeight()) {
            FallingBlockEntity entity = FallingCoconutEntity.fall(level, pos, state.setValue(GREEN, false));
            this.configureFallingBlockEntity(entity);
        }
    }

    protected void configureFallingBlockEntity(FallingBlockEntity entity) {
    }

    protected int getFallDelay() {
        return 2;
    }

    public static boolean canFallThrough(BlockState state) {
        return state.isAir() || state.is(BlockTags.FIRE) || state.canBeReplaced();
    }


    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(STAGE, GREEN, GROWABLE);
    }

    static {
        BROWN_SHAPE = Block.box(4.0, 0.0, 4.0, 12.0, 8.0, 12.0);
        GREEN_SHAPE = Block.box(2.0, 4.0, 2.0, 14.0, 16.0, 14.0);
    }
}
