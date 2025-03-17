package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.util.VoxelShapeHelper;
import net.fiercemanul.fiercesource.world.level.block.FacingModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;



public class HeavyChainsBlock extends FacingModelBlock {


    public static final MapCodec<HeavyChainsBlock> CODEC = simpleCodec(HeavyChainsBlock::new);

    private static final VoxelShapeHelper SHAPE_HELPER = new VoxelShapeHelper()
            .applyCube(2.0D, 2.0D, 0.0D, 14.0D, 14.0D, 16.0D);
    protected static final VoxelShape SHAPE_X = SHAPE_HELPER.west();
    protected static final VoxelShape SHAPE_Y = SHAPE_HELPER.up();
    protected static final VoxelShape SHAPE_Z = SHAPE_HELPER.north();


    public HeavyChainsBlock(Properties pProperties) {
        super(pProperties.strength(1.5F, 6.0F)
                         .noOcclusion()
                         .sound(SoundType.METAL)
                         .mapColor(MapColor.COLOR_BLACK)
        );
    }

    @Override
    protected MapCodec<? extends HeavyChainsBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = this.defaultBlockState();
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState other = level.getBlockState(pos.relative(context.getClickedFace()));
        if (!other.getBlock().equals(this)) other = level.getBlockState(pos.relative(context.getClickedFace().getOpposite()));
        if (other.getBlock().equals(this)) {
            Direction direction = other.getValue(FACING);
            if (context.getPlayer() != null && context.getPlayer().isShiftKeyDown() && !direction.getAxis().equals(context.getClickedFace().getAxis()))
                state = state.setValue(FACING, context.getClickedFace());
            else state = state.setValue(FACING, direction.getOpposite());
        }
        else {
            if (other.getBlock().equals(this)) state = state.setValue(FACING, other.getValue(FACING).getOpposite());
            else state = state.setValue(FACING, context.getClickedFace());
        }
        return state.setValue(WATERLOGGED, context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return switch (pState.getValue(FACING)) {
            case NORTH, SOUTH -> SHAPE_Z;
            case WEST, EAST -> SHAPE_X;
            case UP, DOWN -> SHAPE_Y;
        };
    }

    @Override
    public boolean isPathfindable(BlockState pState, PathComputationType pType) {
        return false;
    }

    @Override
    public boolean skipRendering(BlockState pState, BlockState pAdjacentState, Direction pDirection) {
        if (pAdjacentState.is(this)) {
            Direction myDirection = pState.getValue(FACING);
            if (pDirection.getAxis().equals(myDirection.getAxis())) {
                Direction otherDirection = pAdjacentState.getValue(FACING);
                if (otherDirection.getOpposite().equals(myDirection)) return true;
            }
        }
        return super.skipRendering(pState, pAdjacentState, pDirection);
    }
}
