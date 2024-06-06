package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.util.VoxelShapeHelper;
import net.fiercemanul.fiercesource.world.level.block.HorizonAxisBlock;
import net.fiercemanul.fiercesource.world.level.block.HorizonAxisModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WindowTypeABlock extends HorizonAxisModelBlock {


    public static final MapCodec<? extends WindowTypeABlock> CODEC = simpleCodec(WindowTypeABlock::new);
    private static final VoxelShapeHelper SHAPE_HELPER = new VoxelShapeHelper()
            .applyCube(0.0, 0.0, 5.0, 16.0, 3.0, 11.0)
            .applyCube(0.0, 13.0, 5.0, 16.0, 16.0, 11.0)
            .applyCube(0.0, 3.0, 5.0, 3.0, 13.0, 11.0)
            .applyCube(13.0, 3.0, 5.0, 16.0, 13.0, 11.0)
            .applyCube(3.0, 3.0, 6.0, 6.0, 6.0, 10.0)
            .applyCube(10.0, 3.0, 6.0, 13.0, 6.0, 10.0)
            .applyCube(13.0, 13.0, 10.0, 13.0, 13.0, 10.0)
            .applyCube(3.0, 10.0, 6.0, 6.0, 13.0, 10.0);
    public static final VoxelShape SHAPE_X = SHAPE_HELPER.north();
    public static final VoxelShape SHAPE_Z = SHAPE_HELPER.west();

    public WindowTypeABlock(Properties pProperties) {
        super(pProperties, HorizonAxisBlock.CLOCK_WISE_AXIS);
    }

    @Override
    protected MapCodec<? extends WindowTypeABlock> codec() {
        return CODEC;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return switch (pState.getValue(AXIS)) {
            case X -> SHAPE_X;
            case Z -> SHAPE_Z;
            default -> Shapes.block();
        };
    }
}
