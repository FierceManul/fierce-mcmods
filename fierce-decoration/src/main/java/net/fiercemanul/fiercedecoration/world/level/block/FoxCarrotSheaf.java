package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.world.level.block.ModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;


public class FoxCarrotSheaf extends ModelBlock {


    public static final MapCodec<FoxCarrotSheaf> CODEC = simpleCodec(FoxCarrotSheaf::new);
    protected static final VoxelShape SHAPE = Block.box(4.0, 0.0, 4.0, 12.0, 10.0, 12.0);

    public FoxCarrotSheaf(Properties pProperties) {
        super(pProperties);
    }

    protected MapCodec<? extends FoxCarrotSheaf> codec() {
        return CODEC;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
        return false;
    }
}
