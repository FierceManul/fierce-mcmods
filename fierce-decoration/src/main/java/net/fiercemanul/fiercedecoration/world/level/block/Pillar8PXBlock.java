package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.util.VoxelShapeHelper;
import net.fiercemanul.fiercesource.world.level.block.AxisModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;



public class Pillar8PXBlock extends AxisModelBlock {


    public static final MapCodec<Pillar8PXBlock> CODEC = simpleCodec(Pillar8PXBlock::new);

    private static final VoxelShapeHelper SHAPE_HELPER = new VoxelShapeHelper()
            .applyCube(4.0, 4.0, 0.0, 12.0, 12.0, 16.0);
    protected static final VoxelShape SHAPE_X = SHAPE_HELPER.west();
    protected static final VoxelShape SHAPE_Y = SHAPE_HELPER.up();
    protected static final VoxelShape SHAPE_Z = SHAPE_HELPER.north();



    public Pillar8PXBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected MapCodec<? extends Pillar8PXBlock> codec() {
        return CODEC;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return switch (pState.getValue(AXIS)) {
            case X -> SHAPE_X;
            case Y -> SHAPE_Y;
            case Z -> SHAPE_Z;
        };
    }

    @Override
    protected boolean isPathfindable(BlockState pState, PathComputationType pPathComputationType) {
        return false;
    }
}
