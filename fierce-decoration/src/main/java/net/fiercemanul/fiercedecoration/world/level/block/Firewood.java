package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.util.VoxelShapeHelper;
import net.fiercemanul.fiercesource.world.level.block.HorizonAxisBlock;
import net.fiercemanul.fiercesource.world.level.block.HorizonAxisModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Firewood extends HorizonAxisModelBlock {


    public static final MapCodec<? extends Firewood> CODEC = simpleCodec(Firewood::new);
    private static final VoxelShapeHelper SHAPE_HELPER = new VoxelShapeHelper()
            .applyCube(0.0, 0.0, 2.0, 16.0, 4.0, 14.0)
            .applyCube(0.0, 4.0, 4.0, 16.0, 8.0, 12.0)
            .applyCube(0.0, 8.0, 6.0, 16.0, 12.0, 10.0);
    public static final VoxelShape SHAPE_X = SHAPE_HELPER.north();
    public static final VoxelShape SHAPE_Z = SHAPE_HELPER.west();

    public Firewood(Properties pProperties) {
        super(pProperties
                      .strength(2.0F)
                      .sound(SoundType.WOOD)
                      .ignitedByLava()
                      .mapColor(MapColor.PODZOL),
              HorizonAxisBlock.CLOCK_WISE_AXIS);
    }

    @Override
    protected MapCodec<? extends Firewood> codec() {
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
