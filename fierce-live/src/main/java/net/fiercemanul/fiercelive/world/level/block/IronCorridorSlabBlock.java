package net.fiercemanul.fiercelive.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class IronCorridorSlabBlock extends SlabBlock {


    protected static final VoxelShape TOP_COLLISION = Block.box(0.0, 15.0, 0.0, 16.0, 16.0, 16.0);
    protected static final VoxelShape BOTTOM_COLLISION = Block.box(0.0, 7.0, 0.0, 16.0, 8.0, 16.0);
    protected static final VoxelShape DOUBLE_COLLISION = Shapes.or(TOP_COLLISION, BOTTOM_COLLISION);

    public IronCorridorSlabBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        SlabType slabtype = state.getValue(TYPE);
        return switch (slabtype) {
            case TOP -> TOP_COLLISION;
            case BOTTOM -> BOTTOM_COLLISION;
            default -> DOUBLE_COLLISION;
        };
    }

    @Override
    protected boolean useShapeForLightOcclusion(BlockState state) {
        return false;
    }

    @Override
    protected boolean skipRendering(BlockState state, BlockState adjacentState, Direction direction) {
        return IronCorridorBlock.isCorridorSupport(adjacentState, direction);
    }

    @Override
    public boolean canPlaceLiquid(@Nullable Player player, BlockGetter level, BlockPos pos, BlockState state, Fluid fluid) {
        return fluid == Fluids.WATER;
    }

    @Override
    public boolean placeLiquid(LevelAccessor level, BlockPos pos, BlockState state, FluidState fluidState) {
        if (!state.getValue(BlockStateProperties.WATERLOGGED) && fluidState.getType() == Fluids.WATER) {
            if (!level.isClientSide()) {
                level.setBlock(pos, state.setValue(BlockStateProperties.WATERLOGGED, true), 3);
                level.scheduleTick(pos, fluidState.getType(), fluidState.getType().getTickDelay(level));
            }
            return true;
        }
        return false;
    }
}
