package net.fiercemanul.fiercelive.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.world.level.block.WaterloggedBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;


public class GreenFunRoofBlock extends WaterloggedBlock {


    public static final MapCodec<GreenFunRoofBlock> CODEC = simpleCodec(GreenFunRoofBlock::new);

    protected static final VoxelShape SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 5.0, 16.0);
    protected static final VoxelShape OCCLUSION_SHAPE = Shapes.or(
            Block.box(2, 0, 0, 6, 4, 16),
            Block.box(10, 0, 0, 14, 4, 16),
            Block.box(0, 0, 2, 16, 4, 6),
            Block.box(0, 0, 10, 16, 4, 14)
    );


    public GreenFunRoofBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends GreenFunRoofBlock> codec() {
        return CODEC;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public boolean skipRendering(BlockState state, BlockState adjacentState, Direction direction) {
        if (direction.getAxis() == Direction.Axis.Y) return false;
        return adjacentState.is(this);
    }

    @Override
    protected VoxelShape getOcclusionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return OCCLUSION_SHAPE;
    }
}
