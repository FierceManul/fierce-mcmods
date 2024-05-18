package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.world.level.block.HorizonAxisModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;


public class FakeCampfireBlock extends HorizonAxisModelBlock {


    public static final MapCodec<FakeCampfireBlock> CODEC = simpleCodec(FakeCampfireBlock::new);

    protected static final VoxelShape SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 7.0, 16.0);


    public FakeCampfireBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected MapCodec<? extends HorizonAxisModelBlock> codec() {
        return CODEC;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public boolean skipRendering(BlockState pState, BlockState pAdjacentState, Direction pDirection) {
        if (pDirection.getAxis().equals(Direction.Axis.Y)) return false;
        if (pAdjacentState.getBlock() instanceof FakeCampfireBlock) return pAdjacentState.getValue(AXIS).equals(pState.getValue(AXIS));
        return false;
    }
}
