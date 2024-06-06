package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.world.level.block.HorizonFacingModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;


public class BookAndLampBlock extends HorizonFacingModelBlock {


    public static final MapCodec<BookAndLampBlock> CODEC = simpleCodec(BookAndLampBlock::new);

    protected static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 13.0D, 15.0D);

    public BookAndLampBlock(Properties pProperties) {
        super(pProperties
                      .strength(0.25F)
                      .lightLevel(value -> 7)
                      .noCollission()
                      .noOcclusion()
        );
    }

    @Override
    protected MapCodec<? extends BookAndLampBlock> codec() {
        return CODEC;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }
}
