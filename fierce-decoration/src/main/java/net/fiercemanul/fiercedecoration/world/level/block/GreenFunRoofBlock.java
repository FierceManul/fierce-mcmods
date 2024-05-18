package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.world.level.block.ModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;


public class GreenFunRoofBlock extends ModelBlock {


    public static final MapCodec<GreenFunRoofBlock> CODEC = simpleCodec(GreenFunRoofBlock::new);

    protected static final VoxelShape SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 5.0, 16.0);


    public GreenFunRoofBlock(Properties pProperties) {
        super(pProperties
                      .strength(2.0F)
                      .ignitedByLava()
                      .instrument(NoteBlockInstrument.BASS)
                      .mapColor(MapColor.PODZOL)
                      .sound(SoundType.WOOD)
                      .noOcclusion());
    }

    @Override
    protected MapCodec<? extends GreenFunRoofBlock> codec() {
        return CODEC;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public boolean skipRendering(BlockState pState, BlockState pAdjacentState, Direction pDirection) {
        if (pDirection.getAxis().equals(Direction.Axis.Y)) return false;
        return pAdjacentState.getBlock() instanceof GreenFunRoofBlock;
    }
}
