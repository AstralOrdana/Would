package com.ordana.would.blocks;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.serialization.MapCodec;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class HangingWillowLeavesBlock extends Block {

    private static final MapCodec<HangingWillowLeavesBlock> CODEC = simpleCodec(HangingWillowLeavesBlock::new);

    public static final BooleanProperty NORTH = PipeBlock.NORTH;
    public static final BooleanProperty EAST = PipeBlock.EAST;
    public static final BooleanProperty SOUTH = PipeBlock.SOUTH;
    public static final BooleanProperty WEST = PipeBlock.WEST;

    public static final BooleanProperty TIP = BooleanProperty.create("tip");

    private static final VoxelShape NORTH_AABB = Block.box(0, 0, 0, 16, 16, 1);
    private static final VoxelShape EAST_AABB = Block.box(15, 0, 0, 16, 16, 16);
    private static final VoxelShape SOUTH_AABB = Block.box(0, 0, 15, 16, 16, 16);
    private static final VoxelShape WEST_AABB = Block.box(0, 0, 0, 1, 16, 16);

    private static final Map<Direction, BooleanProperty> PROPERTY_BY_DIRECTION = PipeBlock.PROPERTY_BY_DIRECTION
        .entrySet()
        .stream()
        .filter(entry -> entry.getKey().getAxis().isHorizontal())
        .collect(Util.toMap());

    private static final Map<Direction, VoxelShape> SHAPE_BY_DIRECTION = Util.make(Maps.newEnumMap(Direction.class), map -> {
        map.put(Direction.NORTH, NORTH_AABB);
        map.put(Direction.EAST, EAST_AABB);
        map.put(Direction.SOUTH, SOUTH_AABB);
        map.put(Direction.WEST, WEST_AABB);
    });

    private final Map<BlockState, VoxelShape> shapesCache = Maps.newHashMap();

    public HangingWillowLeavesBlock(Properties properties) {
        super(properties);

        this.registerDefaultState(this.stateDefinition.any()
            .setValue(NORTH, false)
            .setValue(EAST, false)
            .setValue(SOUTH, false)
            .setValue(WEST, false)
            .setValue(TIP, true)
        );

        for (BlockState blockState : this.stateDefinition.getPossibleStates())
            this.shapesCache.put(blockState, calculateShape(blockState));
    }

    @Override
    @NotNull
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }

    @Override
    @NotNull
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return this.shapesCache.get(state);
    }

    @Override
    protected boolean canBeReplaced(BlockState blockState, BlockPlaceContext blockPlaceContext) {
        BlockState clickedState = blockPlaceContext.getLevel().getBlockState(blockPlaceContext.getClickedPos());
        return clickedState.is(this) ? hasEmptyFace(clickedState) : super.canBeReplaced(blockState, blockPlaceContext);
    }

    @Override
    protected boolean propagatesSkylightDown(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState blockState = context.getLevel().getBlockState(context.getClickedPos());

        if (!blockState.is(this))
            blockState = this.defaultBlockState();

        return blockState.setValue(PROPERTY_BY_DIRECTION.get(context.getHorizontalDirection()), true);
    }

    private static List<Direction> getPresentFaces(BlockState blockState) {
        List<Direction> list = Lists.newArrayList();

        for (Map.Entry<Direction, BooleanProperty> entry : PROPERTY_BY_DIRECTION.entrySet()) {
            if (blockState.getValue(entry.getValue()))
                list.add(entry.getKey());
        }

        return list;
    }

    private static boolean hasEmptyFace(BlockState blockState) {
        return getPresentFaces(blockState).size() < PROPERTY_BY_DIRECTION.size();
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess ticks, BlockPos pos, Direction directionToNeighbour, BlockPos neighbourPos, BlockState neighbourState, RandomSource random) {
        if (directionToNeighbour == Direction.DOWN)
            state = state.setValue(TIP, neighbourState.is(this));

        return state.canSurvive(level, pos) ? state : revalidateFaces(state, level, pos);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos above = pos.above();
        BlockState aboveState = level.getBlockState(above);

        VoxelShape shape = state.getShape(level, pos).getFaceShape(Direction.DOWN);

        return Shapes.blockOccudes(shape, aboveState.getShape(level, above), Direction.UP);
    }

    private static BlockState revalidateFaces(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        BlockPos above = blockPos.above();
        BlockState aboveState = levelReader.getBlockState(above);

        VoxelShape aboveShape = aboveState.getShape(levelReader, above);

        for (Direction direction : getPresentFaces(blockState)) {
            VoxelShape voxelShape = SHAPE_BY_DIRECTION.get(direction);
            boolean shapeCoveredFromAbove = Shapes.blockOccudes(voxelShape, aboveShape, Direction.UP);

            blockState = blockState.setValue(PROPERTY_BY_DIRECTION.get(direction), shapeCoveredFromAbove);
        }

        return getPresentFaces(blockState).isEmpty() ? Blocks.AIR.defaultBlockState() : blockState;
    }

    private static VoxelShape calculateShape(BlockState state) {
        VoxelShape voxelShape = Shapes.empty();

        if (state.getValue(NORTH))
            voxelShape = Shapes.or(voxelShape, NORTH_AABB);

        if (state.getValue(SOUTH))
            voxelShape = Shapes.or(voxelShape, SOUTH_AABB);

        if (state.getValue(EAST))
            voxelShape = Shapes.or(voxelShape, EAST_AABB);

        if (state.getValue(WEST))
            voxelShape = Shapes.or(voxelShape, WEST_AABB);

        return voxelShape.isEmpty() ? Shapes.block() : voxelShape;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        PROPERTY_BY_DIRECTION.values().forEach(builder::add);
        builder.add(TIP);
    }

}
