package net.fiercemanul.fiercelive.world.level.block;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercelive.world.level.block.state.properties.FLBlockStateProperties;
import net.fiercemanul.fiercesource.world.level.block.BlockUtils;
import net.fiercemanul.fiercesource.world.level.block.WaterloggedBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;


public class TripleCutBlock extends WaterloggedBlock {


    public static final MapCodec<TripleCutBlock> CODEC = simpleCodec(TripleCutBlock::new);

    private static final BooleanProperty PART_A = FLBlockStateProperties.PART_A;
    private static final BooleanProperty PART_B = FLBlockStateProperties.PART_B;
    private static final BooleanProperty PART_C = FLBlockStateProperties.PART_C;
    private static final BooleanProperty PART_D = FLBlockStateProperties.PART_D;
    private static final BooleanProperty PART_E = FLBlockStateProperties.PART_E;
    private static final BooleanProperty PART_F = FLBlockStateProperties.PART_F;
    private static final BooleanProperty PART_G = FLBlockStateProperties.PART_G;
    private static final BooleanProperty PART_H = FLBlockStateProperties.PART_H;
    public static final VoxelShape SHAPE_NWD = box(0.0D, 0.0D, 0.0D, 8.0D, 8.0D, 8.0D);
    public static final VoxelShape SHAPE_NED = box(8.0D, 0.0D, 0.0D, 16.0D, 8.0D, 8.0D);
    public static final VoxelShape SHAPE_SED = box(8.0D, 0.0D, 8.0D, 16.0D, 8.0D, 16.0D);
    public static final VoxelShape SHAPE_SWD = box(0.0D, 0.0D, 8.0D, 8.0D, 8.0D, 16.0D);
    public static final VoxelShape SHAPE_NWU = box(0.0D, 8.0D, 0.0D, 8.0D, 16.0D, 8.0D);
    public static final VoxelShape SHAPE_NEU = box(8.0D, 8.0D, 0.0D, 16.0D, 16.0D, 8.0D);
    public static final VoxelShape SHAPE_SEU = box(8.0D, 8.0D, 8.0D, 16.0D, 16.0D, 16.0D);
    public static final VoxelShape SHAPE_SWU = box(0.0D, 8.0D, 8.0D, 8.0D, 16.0D, 16.0D);
    private static final VoxelShape[] SHAPES = buildShape();
    private final ImmutableMap<BlockState, ImmutableMap<Rotation, BlockState>> rotateMap;
    private final ImmutableMap<BlockState, ImmutableMap<Mirror, BlockState>> mirrorMap;

    private static VoxelShape[] buildShape() {
        VoxelShape[] shapes = new VoxelShape[256];
        for (int i = 0; i < 256; i++) {
            if (i == 255) shapes[255] = Shapes.block();
            else {
                VoxelShape shape = Shapes.empty();
                if ((i & 1) != 0) shape = Shapes.or(shape, SHAPE_NWD);
                if ((i & 2) != 0) shape = Shapes.or(shape, SHAPE_NED);
                if ((i & 4) != 0) shape = Shapes.or(shape, SHAPE_SWD);
                if ((i & 8) != 0) shape = Shapes.or(shape, SHAPE_SED);
                if ((i & 16) != 0) shape = Shapes.or(shape, SHAPE_NWU);
                if ((i & 32) != 0) shape = Shapes.or(shape, SHAPE_NEU);
                if ((i & 64) != 0) shape = Shapes.or(shape, SHAPE_SWU);
                if ((i & 128) != 0) shape = Shapes.or(shape, SHAPE_SEU);
                shapes[i] = shape;
            }
        }
        return shapes;
    }

    private static int getShapeIndex(BlockState state) {
        int value = 0;
        if (state.getValue(PART_A)) value |= 1;
        if (state.getValue(PART_B)) value |= 2;
        if (state.getValue(PART_C)) value |= 4;
        if (state.getValue(PART_D)) value |= 8;
        if (state.getValue(PART_E)) value |= 16;
        if (state.getValue(PART_F)) value |= 32;
        if (state.getValue(PART_G)) value |= 64;
        if (state.getValue(PART_H)) value |= 128;
        return value;
    }

    public TripleCutBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(PART_A, false)
                .setValue(PART_B, false)
                .setValue(PART_C, false)
                .setValue(PART_D, false)
                .setValue(PART_E, false)
                .setValue(PART_F, false)
                .setValue(PART_G, false)
                .setValue(PART_H, false)
                .setValue(WATERLOGGED, false));
        this.rotateMap = BlockUtils.forEachStatesWith(this, Rotation.class, TripleCutBlock::getRotatedState);
        this.mirrorMap = BlockUtils.forEachStatesWith(this, Mirror.class, TripleCutBlock::getMirroredState);
    }

    @Override
    protected MapCodec<? extends TripleCutBlock> codec() {
        return CODEC;
    }

    private static BlockState getRotatedState(BlockState state, Rotation rotation) {
        return switch (rotation) {
            case NONE -> state;
            case CLOCKWISE_90 -> state
                    .setValue(PART_A, state.getValue(PART_C))
                    .setValue(PART_B, state.getValue(PART_A))
                    .setValue(PART_C, state.getValue(PART_D))
                    .setValue(PART_D, state.getValue(PART_B))
                    .setValue(PART_E, state.getValue(PART_G))
                    .setValue(PART_F, state.getValue(PART_E))
                    .setValue(PART_G, state.getValue(PART_H))
                    .setValue(PART_H, state.getValue(PART_F));
            case CLOCKWISE_180 -> state
                    .setValue(PART_A, state.getValue(PART_D))
                    .setValue(PART_B, state.getValue(PART_C))
                    .setValue(PART_C, state.getValue(PART_B))
                    .setValue(PART_D, state.getValue(PART_A))
                    .setValue(PART_E, state.getValue(PART_H))
                    .setValue(PART_F, state.getValue(PART_G))
                    .setValue(PART_G, state.getValue(PART_F))
                    .setValue(PART_H, state.getValue(PART_E));
            case COUNTERCLOCKWISE_90 -> state
                    .setValue(PART_A, state.getValue(PART_B))
                    .setValue(PART_B, state.getValue(PART_D))
                    .setValue(PART_C, state.getValue(PART_A))
                    .setValue(PART_D, state.getValue(PART_C))
                    .setValue(PART_E, state.getValue(PART_F))
                    .setValue(PART_F, state.getValue(PART_H))
                    .setValue(PART_G, state.getValue(PART_E))
                    .setValue(PART_H, state.getValue(PART_G));
        };
    }

    private static BlockState getMirroredState(BlockState state, Mirror mirror) {
        return switch (mirror) {
            case NONE -> state;
            case LEFT_RIGHT -> state
                    .setValue(PART_A, state.getValue(PART_B))
                    .setValue(PART_B, state.getValue(PART_A))
                    .setValue(PART_C, state.getValue(PART_D))
                    .setValue(PART_D, state.getValue(PART_C))
                    .setValue(PART_E, state.getValue(PART_F))
                    .setValue(PART_F, state.getValue(PART_E))
                    .setValue(PART_G, state.getValue(PART_H))
                    .setValue(PART_H, state.getValue(PART_G));
            case FRONT_BACK -> state
                    .setValue(PART_A, state.getValue(PART_C))
                    .setValue(PART_B, state.getValue(PART_D))
                    .setValue(PART_C, state.getValue(PART_A))
                    .setValue(PART_D, state.getValue(PART_B))
                    .setValue(PART_E, state.getValue(PART_G))
                    .setValue(PART_F, state.getValue(PART_H))
                    .setValue(PART_G, state.getValue(PART_E))
                    .setValue(PART_H, state.getValue(PART_F));
        };
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(PART_A, PART_B, PART_C, PART_D, PART_E, PART_F, PART_G, PART_H, WATERLOGGED);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPES[getShapeIndex(state)];
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState pState) {
        return true;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState blockState = context.getLevel().getBlockState(context.getClickedPos());
        if (!blockState.is(this)) blockState = defaultBlockState();
        blockState = blockState.setValue(getClickedPart(context), true);
        return blockState.setValue(WATERLOGGED, context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER && !isShapeFullBlock(blockState.getShape(
                context.getLevel(), context.getClickedPos())));
    }

    private BooleanProperty getClickedPart(BlockPlaceContext pContext) {
        double x, y, z;
        x = pContext.getClickLocation().x - pContext.getClickedPos().getX();
        y = pContext.getClickLocation().y - pContext.getClickedPos().getY();
        z = pContext.getClickLocation().z - pContext.getClickedPos().getZ();
        if (x == 0.5) x = pContext.getClickedFace().equals(Direction.EAST) ? 0.6 : 0.4;
        if (y == 0.5) y = pContext.getClickedFace().equals(Direction.UP) ? 0.6 : 0.4;
        if (z == 0.5) z = pContext.getClickedFace().equals(Direction.SOUTH) ? 0.6 : 0.4;

        if (x > 0.5) {
            if (z > 0.5) {
                if (y > 0.5) return PART_H;
                else return PART_D;
            } else {
                if (y > 0.5) return PART_F;
                else return PART_B;
            }
        } else {
            if (z > 0.5) {
                if (y > 0.5) return PART_G;
                else return PART_C;
            } else {
                if (y > 0.5) return PART_E;
                else return PART_A;
            }
        }
    }

    @Override
    public boolean canBeReplaced(BlockState pState, BlockPlaceContext pUseContext) {
        if (!pUseContext.getItemInHand().is(this.asItem())) return false;
        return !pState.getValue(getClickedPart(pUseContext));
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        var map = rotateMap.get(state);
        if (map != null) {
            var sta = map.get(rotation);
            if (sta != null) return sta;
        }
        return state;
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        var map = mirrorMap.get(state);
        if (map != null) {
            var sta = map.get(mirror);
            if (sta != null) return sta;
        }
        return state;
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        return false;
    }

    @Override
    public boolean canPlaceLiquid(@Nullable Player player, BlockGetter level, BlockPos pos, BlockState state, Fluid fluid) {
        return !isShapeFullBlock(state.getShape(level, pos)) && super.canPlaceLiquid(player, level, pos, state, fluid);
    }
}
