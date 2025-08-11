package net.fiercemanul.fiercesource.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WorldLocatorBlock extends ModelBlock {


    public static final MapCodec<WorldLocatorBlock> CODEC = simpleCodec(WorldLocatorBlock::new);
    public static final VoxelShape SHAPE = box(3.0, 3.0, 3.0, 13.0, 13.0, 13.0);

    public WorldLocatorBlock(Properties pProperties) {
        super(pProperties.strength(2.0F, 20.0F).sound(SoundType.STONE).mapColor(MapColor.COLOR_BLUE));
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
