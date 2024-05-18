package net.fiercemanul.fiercedecoration.world.level.block;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercedecoration.world.level.block.state.properties.FDBlockStateProperties;
import net.fiercemanul.fiercesource.world.level.block.ModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;


public class TripleCutBlock extends ModelBlock {


    public static final MapCodec<TripleCutBlock> CODEC = simpleCodec(TripleCutBlock::new);

    public static final BooleanProperty PART_A = FDBlockStateProperties.PART_A;
    public static final BooleanProperty PART_B = FDBlockStateProperties.PART_B;
    public static final BooleanProperty PART_C = FDBlockStateProperties.PART_C;
    public static final BooleanProperty PART_D = FDBlockStateProperties.PART_D;
    public static final BooleanProperty PART_E = FDBlockStateProperties.PART_E;
    public static final BooleanProperty PART_F = FDBlockStateProperties.PART_F;
    public static final BooleanProperty PART_G = FDBlockStateProperties.PART_G;
    public static final BooleanProperty PART_H = FDBlockStateProperties.PART_H;
    public static final VoxelShape SHAPE_NWD = box(0.0D, 0.0D, 0.0D, 8.0D, 8.0D, 8.0D);
    public static final VoxelShape SHAPE_NED = box(8.0D, 0.0D, 0.0D, 16.0D, 8.0D, 8.0D);
    public static final VoxelShape SHAPE_SED = box(8.0D, 0.0D, 8.0D, 16.0D, 8.0D, 16.0D);
    public static final VoxelShape SHAPE_SWD = box(0.0D, 0.0D, 8.0D, 8.0D, 8.0D, 16.0D);
    public static final VoxelShape SHAPE_NWU = box(0.0D, 8.0D, 0.0D, 8.0D, 16.0D, 8.0D);
    public static final VoxelShape SHAPE_NEU = box(8.0D, 8.0D, 0.0D, 16.0D, 16.0D, 8.0D);
    public static final VoxelShape SHAPE_SEU = box(8.0D, 8.0D, 8.0D, 16.0D, 16.0D, 16.0D);
    public static final VoxelShape SHAPE_SWU = box(0.0D, 8.0D, 8.0D, 8.0D, 16.0D, 16.0D);
    private final ImmutableMap<BlockState, VoxelShape> shapeMap;

    public TripleCutBlock(Properties pProperties) {
        super(pProperties);
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
        this.shapeMap = getShapeForEachState(TripleCutBlock::buildShape);
    }

    @Override
    protected MapCodec<? extends TripleCutBlock> codec() {
        return CODEC;
    }

    private static VoxelShape buildShape(BlockState state) {
        VoxelShape shape = Shapes.empty();
        if (state.getValue(PART_A)) shape = Shapes.or(shape, SHAPE_NWD);
        if (state.getValue(PART_B)) shape = Shapes.or(shape, SHAPE_NED);
        if (state.getValue(PART_C)) shape = Shapes.or(shape, SHAPE_SWD);
        if (state.getValue(PART_D)) shape = Shapes.or(shape, SHAPE_SED);
        if (state.getValue(PART_E)) shape = Shapes.or(shape, SHAPE_NWU);
        if (state.getValue(PART_F)) shape = Shapes.or(shape, SHAPE_NEU);
        if (state.getValue(PART_G)) shape = Shapes.or(shape, SHAPE_SWU);
        if (state.getValue(PART_H)) shape = Shapes.or(shape, SHAPE_SEU);
        return shape;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(PART_A, PART_B, PART_C, PART_D, PART_E, PART_F, PART_G, PART_H, WATERLOGGED);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        VoxelShape shape = shapeMap.get(pState);
        if (shape != null) return shape;
        return Shapes.block();
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState pState) {
        return true;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockState blockState = pContext.getLevel().getBlockState(pContext.getClickedPos());
        if (!blockState.is(this)) blockState = defaultBlockState();
        blockState = blockState.setValue(getClickedPart(pContext), true);
        return blockState.setValue(WATERLOGGED, pContext.getLevel().getFluidState(pContext.getClickedPos()).getType() == Fluids.WATER && !isShapeFullBlock(blockState.getShape(pContext.getLevel(), pContext.getClickedPos())));
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
    public BlockState rotate(BlockState pState, Rotation pRot) {
        return switch (pRot) {
            case NONE -> pState;
            case CLOCKWISE_90 -> pState
                    .setValue(PART_A, pState.getValue(PART_C))
                    .setValue(PART_B, pState.getValue(PART_A))
                    .setValue(PART_C, pState.getValue(PART_D))
                    .setValue(PART_D, pState.getValue(PART_B))
                    .setValue(PART_E, pState.getValue(PART_G))
                    .setValue(PART_F, pState.getValue(PART_E))
                    .setValue(PART_G, pState.getValue(PART_H))
                    .setValue(PART_H, pState.getValue(PART_F));
            case CLOCKWISE_180 -> pState
                    .setValue(PART_A, pState.getValue(PART_D))
                    .setValue(PART_B, pState.getValue(PART_C))
                    .setValue(PART_C, pState.getValue(PART_B))
                    .setValue(PART_D, pState.getValue(PART_A))
                    .setValue(PART_E, pState.getValue(PART_H))
                    .setValue(PART_F, pState.getValue(PART_G))
                    .setValue(PART_G, pState.getValue(PART_F))
                    .setValue(PART_H, pState.getValue(PART_E));
            case COUNTERCLOCKWISE_90 -> pState
                    .setValue(PART_A, pState.getValue(PART_B))
                    .setValue(PART_B, pState.getValue(PART_D))
                    .setValue(PART_C, pState.getValue(PART_A))
                    .setValue(PART_D, pState.getValue(PART_C))
                    .setValue(PART_E, pState.getValue(PART_F))
                    .setValue(PART_F, pState.getValue(PART_H))
                    .setValue(PART_G, pState.getValue(PART_E))
                    .setValue(PART_H, pState.getValue(PART_G));
        };
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        if (pMirror.equals(Mirror.NONE)) return pState;
        else return rotate(pState, Rotation.CLOCKWISE_180);
    }
}
