package net.fiercemanul.fiercelive.world.level.block;

import net.fiercemanul.fiercesource.util.VoxelShapeHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class RoofBlock extends ThinStairBlock {


    protected static final VoxelShape[] SHAPES = makeShapes(
            new VoxelShapeHelper().applyCube(0, 0, 0, 16, 16, 8)
                                  .applyCube(0, 0, 8, 16, 8, 16),
            new VoxelShapeHelper().applyCube(0, 0, 0, 16, 16, 8)
                                  .applyCube(0, 0, 8, 8, 16, 16)
                                  .applyCube(8, 0, 8, 16, 8, 16),
            new VoxelShapeHelper().applyCube(0, 0, 0, 8, 16, 8)
                                  .applyCube(8, 0, 0, 16, 8, 8)
                                  .applyCube(0, 0, 8, 16, 8, 16)
    );
    //mc会根据拿个形状来分析边缘形状，但分析的第一步是检测其边界是否正好在轴面上，越界的面会直接返回空形状。
    protected static final VoxelShape[] OCCLUSION_SHAPES = makeShapes(
            new VoxelShapeHelper().applyCube(0, 0, 0, 16, 16, 7)
                                  .applyCube(0, 0, 7, 16, 15, 11)
                                  .applyCube(0, 0, 11, 16, 11, 15)
                                  .applyCube(0, 0, 15, 16, 7, 16)
                                  .applyCube(0, 16, 12, 16, 17, 16),
            new VoxelShapeHelper().applyCube(0, 0, 0, 16, 16, 7)
                                  .applyCube(0, 0, 7, 7, 16, 16)
                                  .applyCube(7, 0, 7, 16, 15, 11)
                                  .applyCube(7, 0, 11, 11, 15, 16)
                                  .applyCube(11, 0, 11, 16, 11, 15)
                                  .applyCube(11, 0, 15, 15, 11, 16)
                                  .applyCube(15, 0, 15, 16, 7, 16)
                                  .applyCube(12, 16, 12, 16, 17, 16),
            new VoxelShapeHelper().applyCube(0, 15, 0, 7, 16, 7)
                                  .applyCube(0, 11, 0, 11, 15, 11)
                                  .applyCube(0, 7, 0, 15, 11, 15)
                                  .applyCube(0, 0, 0, 16, 7, 16)
                                  .applyCube(0, 16, 12, 16, 17, 16)
                                  .applyCube(12, 16, 0, 16, 17, 12)
    );

    public RoofBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFamily(BlockState state) {
        return state.getBlock().getClass() == RoofBlock.class;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPES[getShapeIndex(state)];
    }

    @Override
    protected VoxelShape getOcclusionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return OCCLUSION_SHAPES[getShapeIndex(state)];
    }

    @Override
    protected boolean propagatesSkylightDown(BlockState state, BlockGetter level, BlockPos pos) {
        return false;
    }

}
