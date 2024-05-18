package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.util.VoxelShapeHelper;
import net.fiercemanul.fiercesource.world.level.block.HorizonFacingModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;



public class NeoForgeBlock extends HorizonFacingModelBlock {


    public static final MapCodec<NeoForgeBlock> CODEC = simpleCodec(NeoForgeBlock::new);

    private static final VoxelShapeHelper SHAPE_HELPER = new VoxelShapeHelper()
            .applyCube(2.0, 0.0, 2.0, 14.0, 4.0, 14.0)
            .applyCube(3.0, 4.0, 4.0, 13.0, 5.0, 12.0)
            .applyCube(4.0, 5.0, 6.0, 12.0, 10.0, 10.0)
            .applyCube(0.0, 10.0, 3.0, 16.0, 16.0, 13.0);
    protected static final VoxelShape SHAPE_X = SHAPE_HELPER.north();
    protected static final VoxelShape SHAPE_Z = SHAPE_HELPER.west();


    public NeoForgeBlock(Properties pProperties) {
        super(pProperties.strength(0.5F, 3.0F)
                         .sound(SoundType.WOOL)
                         .mapColor(MapColor.COLOR_ORANGE)
        );
    }

    @Override
    protected MapCodec<? extends NeoForgeBlock> codec() {
        return CODEC;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return switch (pState.getValue(FACING)) {
            case NORTH, SOUTH -> SHAPE_X;
            default -> SHAPE_Z;
        };
    }
}
