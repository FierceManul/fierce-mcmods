package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.world.level.block.ModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;


public class RockPathBlock extends ModelBlock {



    public static final MapCodec<RockPathBlock> CODEC = simpleCodec(RockPathBlock::new);

    protected static final VoxelShape SHAPE =  Block.box(0.0, 0.0, 0.0, 16.0, 1.0, 16.0);


    public RockPathBlock(Properties properties) {
        super(properties.noOcclusion());
    }

    @Override
    public MapCodec<? extends RockPathBlock> codec() {
        return CODEC;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }
}
