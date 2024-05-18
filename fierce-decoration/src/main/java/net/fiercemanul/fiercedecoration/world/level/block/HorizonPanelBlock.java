package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.world.level.block.ModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;


public class HorizonPanelBlock extends ModelBlock {


    public static final MapCodec<HorizonPanelBlock> CODEC = simpleCodec(HorizonPanelBlock::new);

    protected static final VoxelShape SHAPE = Block.box(0.0, 7.0, 0.0, 16.0, 9.0, 16.0);


    public HorizonPanelBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected MapCodec<? extends HorizonPanelBlock> codec() {
        return CODEC;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public boolean skipRendering(BlockState pState, BlockState pAdjacentState, Direction pDirection) {
        if (pDirection.getAxis().equals(Direction.Axis.Y)) return false;
        return pAdjacentState.is(this);
    }
}
