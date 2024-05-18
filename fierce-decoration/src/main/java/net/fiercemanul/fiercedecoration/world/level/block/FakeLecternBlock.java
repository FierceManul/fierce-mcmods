package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.world.level.block.HorizonFacingModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.LecternBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;


public class FakeLecternBlock extends HorizonFacingModelBlock {


    public static final MapCodec<FakeLecternBlock> CODEC = simpleCodec(FakeLecternBlock::new);

    public FakeLecternBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected MapCodec<? extends FakeLecternBlock> codec() {
        return CODEC;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return switch (pState.getValue(FACING)) {
            case NORTH -> LecternBlock.SHAPE_NORTH;
            case SOUTH -> LecternBlock.SHAPE_SOUTH;
            case EAST -> LecternBlock.SHAPE_EAST;
            case WEST -> LecternBlock.SHAPE_WEST;
            default -> LecternBlock.SHAPE_COMMON;
        };
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return LecternBlock.SHAPE_COLLISION;
    }

    @Override
    public VoxelShape getOcclusionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return LecternBlock.SHAPE_COMMON;
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState pState) {
        return true;
    }

    @Override
    public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
        return false;
    }
}
