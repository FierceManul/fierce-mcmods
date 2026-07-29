package net.fiercemanul.fiercesource.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WorldLocatorBlock extends WaterloggedBlock {


    public static final MapCodec<WorldLocatorBlock> CODEC = simpleCodec(WorldLocatorBlock::new);
    public static final VoxelShape SHAPE = box(3.0, 3.0, 3.0, 13.0, 13.0, 13.0);

    public WorldLocatorBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends WorldLocatorBlock> codec() {
        return CODEC;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }
}
