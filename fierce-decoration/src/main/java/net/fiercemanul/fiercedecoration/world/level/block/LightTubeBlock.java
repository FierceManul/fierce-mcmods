package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.util.VoxelShapeHelper;
import net.fiercemanul.fiercesource.world.level.block.FacingModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;



public class LightTubeBlock extends FacingModelBlock {


    public static final MapCodec<LightTubeBlock> CODEC = simpleCodec(LightTubeBlock::new);

    public static final BooleanProperty VERTICAL = BooleanProperty.create("vertical");

    private static final VoxelShapeHelper SHAPE_H_HELPER = new VoxelShapeHelper()
            .applyCube(0.0D, 7.0D, 15.0D, 16.0D, 9.0D, 16.0D)
            .applyCube(1.0D, 7.0D, 14.0D, 15.0D, 9.0D, 16.0D);
    protected static final VoxelShape SHAPE_NORTH_H = SHAPE_H_HELPER.north();
    protected static final VoxelShape SHAPE_SOUTH_H = SHAPE_H_HELPER.south();
    protected static final VoxelShape SHAPE_WEST_H = SHAPE_H_HELPER.west();
    protected static final VoxelShape SHAPE_EAST_H = SHAPE_H_HELPER.east();
    protected static final VoxelShape SHAPE_UP_H = SHAPE_H_HELPER.up();
    protected static final VoxelShape SHAPE_DOWN_H = SHAPE_H_HELPER.down();
    private static final VoxelShapeHelper SHAPE_V_HELPER = new VoxelShapeHelper()
            .applyCube(7.0D, 0.0D, 15.0D, 9.0D, 16.0D, 16.0D)
            .applyCube(7.0D, 1.0D, 14.0D, 9.0D, 15.0D, 16.0D);
    protected static final VoxelShape SHAPE_NORTH_V = SHAPE_V_HELPER.north();
    protected static final VoxelShape SHAPE_SOUTH_V = SHAPE_V_HELPER.south();
    protected static final VoxelShape SHAPE_WEST_V = SHAPE_V_HELPER.west();
    protected static final VoxelShape SHAPE_EAST_V = SHAPE_V_HELPER.east();
    protected static final VoxelShape SHAPE_UP_V = SHAPE_V_HELPER.up();
    protected static final VoxelShape SHAPE_DOWN_V = SHAPE_V_HELPER.down();


    public LightTubeBlock(Properties pProperties) {
        super(pProperties
                      .strength(0.3F)
                      .lightLevel(value -> 15)
                      .noCollission()
        );
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(VERTICAL, false)
                .setValue(WATERLOGGED, false));
    }

    @Override
    protected MapCodec<? extends LightTubeBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, VERTICAL, WATERLOGGED);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        boolean vertical = pState.getValue(VERTICAL);
        return switch (pState.getValue(FACING)) {
            case DOWN -> vertical ? SHAPE_DOWN_V : SHAPE_DOWN_H;
            case UP -> vertical ? SHAPE_UP_V : SHAPE_UP_H;
            case NORTH -> vertical ? SHAPE_NORTH_V : SHAPE_NORTH_H;
            case SOUTH -> vertical ? SHAPE_SOUTH_V : SHAPE_SOUTH_H;
            case WEST -> vertical ? SHAPE_WEST_V : SHAPE_WEST_H;
            case EAST -> vertical ? SHAPE_EAST_V : SHAPE_EAST_H;
        };
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        Direction clickFace = pContext.getClickedFace();
        Direction horizontalDirection = pContext.getHorizontalDirection();
        BlockState state = defaultBlockState().setValue(FACING, clickFace);
        if (pContext.getPlayer() != null) {
            boolean snaking = pContext.getPlayer().isShiftKeyDown();
            if (clickFace == Direction.DOWN || clickFace == Direction.UP) {
                if (horizontalDirection == Direction.NORTH || horizontalDirection == Direction.SOUTH) {
                    state = state.setValue(VERTICAL, snaking);
                } else {
                    state = state.setValue(VERTICAL, !snaking);
                }
            } else {
                if (snaking) state = state.setValue(VERTICAL, true);
            }
        }
        return state.setValue(WATERLOGGED, pContext.getLevel().getFluidState(pContext.getClickedPos()).getType() == Fluids.WATER);
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        BlockState state = pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
        if (state.getValue(FACING) == pState.getValue(FACING).getClockWise() || state.getValue(FACING) == pState.getValue(FACING).getCounterClockWise()) {
            state = state.setValue(VERTICAL, !state.getValue(VERTICAL));
        }
        return state;
    }

    @Override
    
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        pTooltip.add(Component.translatable("fiercedecoration.tip.snake_vertical"));
    }
}
