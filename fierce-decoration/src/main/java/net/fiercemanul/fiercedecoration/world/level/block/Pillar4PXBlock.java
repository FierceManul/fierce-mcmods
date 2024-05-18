package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.util.VoxelShapeHelper;
import net.fiercemanul.fiercesource.world.level.block.AxisModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;



public class Pillar4PXBlock extends AxisModelBlock {


    public static final MapCodec<Pillar4PXBlock> CODEC = simpleCodec(Pillar4PXBlock::new);

    private static final VoxelShapeHelper SHAPE_HELPER = new VoxelShapeHelper()
            .applyCube(6.0, 6.0, 0.0, 10.0, 10.0, 16.0);
    protected static final VoxelShape SHAPE_X = SHAPE_HELPER.west();
    protected static final VoxelShape SHAPE_Y = SHAPE_HELPER.up();
    protected static final VoxelShape SHAPE_Z = SHAPE_HELPER.north();



    public Pillar4PXBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected MapCodec<? extends Pillar4PXBlock> codec() {
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
}
