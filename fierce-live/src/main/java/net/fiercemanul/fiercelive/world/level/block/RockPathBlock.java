package net.fiercemanul.fiercelive.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.world.level.block.WaterloggedBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class RockPathBlock extends WaterloggedBlock {


    public static final MapCodec<RockPathBlock> CODEC = simpleCodec(RockPathBlock::new);
    protected static final VoxelShape SHAPE =  box(0.5, 0.0, 0.5, 15.5, 1.0, 15.5);
    protected static final VoxelShape OCCLUSION_SHAPE = Shapes.or(
            box(0.5, 0, 0.5, 10.5, 1, 4.5),
            box(11.5, 0, 0.5, 15.5, 1, 10.5),
            box(0.5, 0, 5.5, 4.5, 1, 15.5),
            box(5.5, 0, 11.5, 15.5, 1, 15.5),
            box(5.5, 0, 5.5, 10.5, 1, 10.5)
    );


    public RockPathBlock(Properties properties) {
        super(properties);
    }

    @Override
    public MapCodec<? extends RockPathBlock> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected VoxelShape getOcclusionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return OCCLUSION_SHAPE;
    }

    @Override
    protected boolean propagatesSkylightDown(BlockState state, BlockGetter level, BlockPos pos) {
        return state.getFluidState().isEmpty();
    }
}
