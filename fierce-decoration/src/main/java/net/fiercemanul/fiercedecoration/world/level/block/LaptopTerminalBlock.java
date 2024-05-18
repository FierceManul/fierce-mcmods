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



public class LaptopTerminalBlock extends HorizonFacingModelBlock {


    public static final MapCodec<LaptopTerminalBlock> CODEC = simpleCodec(LaptopTerminalBlock::new);

    protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 8.0D, 14.0D);


    public LaptopTerminalBlock(Properties pProperties) {
        super(pProperties
                      .strength(0.25F, 6.0F)
                      .lightLevel(value -> 7)
                      .noCollission()
                      .noOcclusion()
                      .mapColor(MapColor.COLOR_BLACK)
        );
    }

    @Override
    protected MapCodec<? extends LaptopTerminalBlock> codec() {
        return CODEC;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }
}
