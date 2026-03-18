package com.ordana.would.blocks;

import com.mojang.serialization.MapCodec;
import com.ordana.would.reg.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class HangingMossBlock extends Block implements BonemealableBlock {
    public static final MapCodec<HangingMossBlock> CODEC = simpleCodec(HangingMossBlock::new);
    private static final VoxelShape SHAPE_BASE = column(14, 14.0, 0.0, 16.0);
    private static final VoxelShape SHAPE_TIP = column(14, 14.0, 2.0, 16.0);
    public static final BooleanProperty TIP = BooleanProperty.create("tip");

    public MapCodec<HangingMossBlock> codec() {
        return CODEC;
    }

    public HangingMossBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(TIP, true));
    }

    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return state.getValue(TIP) ? SHAPE_TIP : SHAPE_BASE;
    }

    protected boolean propagatesSkylightDown(BlockState blockState) {
        return true;
    }

    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return this.canStayAtPosition(level, pos);
    }

    private boolean canStayAtPosition(BlockGetter blockGetter, BlockPos blockPos) {
        BlockPos blockPos2 = blockPos.relative(Direction.UP);
        BlockState blockState = blockGetter.getBlockState(blockPos2);
        return MultifaceBlock.canAttachTo(blockGetter, Direction.UP, blockPos2, blockState) || blockState.is(ModBlocks.PALE_HANGING_MOSS);
    }

    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (!this.canStayAtPosition(level, pos)) {
            level.scheduleTick(pos, this, 1);
        }

        return state.setValue(TIP, !level.getBlockState(pos.below()).is(this));
    }

    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!this.canStayAtPosition(level, pos)) {
            level.destroyBlock(pos, true);
        }

    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TIP);
    }

    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return this.canGrowInto(level.getBlockState(this.getTip(level, pos).below()));
    }

    private boolean canGrowInto(BlockState blockState) {
        return blockState.isAir();
    }

    public BlockPos getTip(BlockGetter blockGetter, BlockPos blockPos) {
        BlockPos.MutableBlockPos mutableBlockPos = blockPos.mutable();

        BlockState blockState;
        do {
            mutableBlockPos.move(Direction.DOWN);
            blockState = blockGetter.getBlockState(mutableBlockPos);
        } while(blockState.is(this));

        return mutableBlockPos.relative(Direction.UP).immutable();
    }

    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        BlockPos blockPos = this.getTip(level, pos).below();
        if (this.canGrowInto(level.getBlockState(blockPos))) {
            level.setBlockAndUpdate(blockPos, state.setValue(TIP, true));
        }
    }

    public static VoxelShape column(double d, double e, double f, double g) {
        double h = d / 2.0;
        double i = e / 2.0;
        return box(8.0 - h, f, 8.0 - i, 8.0 + h, g, 8.0 + i);
    }
}
