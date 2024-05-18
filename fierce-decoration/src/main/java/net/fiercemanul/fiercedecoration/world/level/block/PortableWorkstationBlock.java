package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.world.level.block.HorizonFacingModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;


public class PortableWorkstationBlock extends HorizonFacingModelBlock {


    public static final MapCodec<PortableWorkstationBlock> CODEC = simpleCodec(PortableWorkstationBlock::new);

    protected static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 9.0D, 15.0D);

    public PortableWorkstationBlock(Properties pProperties) {
        super(pProperties
                      .strength(0.25F, 6.0F)
                      .lightLevel(value -> 7)
                      .noCollission()
                      .noOcclusion()
                      .mapColor(MapColor.COLOR_BLACK)
        );
    }

    @Override
    protected MapCodec<? extends PortableWorkstationBlock> codec() {
        return CODEC;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }
}
