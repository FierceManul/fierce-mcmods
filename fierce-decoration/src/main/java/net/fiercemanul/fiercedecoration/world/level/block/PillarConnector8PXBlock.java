package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.phys.shapes.VoxelShape;


public class PillarConnector8PXBlock extends PillarConnectorBlock {


    public static final MapCodec<PillarConnector8PXBlock> CODEC = simpleCodec(PillarConnector8PXBlock::new);
    public static final float APOTHEM = 0.25F;
    public static final VoxelShape[] SHAPE_BY_INDEX = makeShapes(APOTHEM);
    public static final VoxelShape SUPPORT_SHAPE = makeSupportShape(APOTHEM);

    public PillarConnector8PXBlock(Properties pProperties) {
        super(APOTHEM, SHAPE_BY_INDEX, SUPPORT_SHAPE, pProperties);
    }

    @Override
    protected MapCodec<? extends PillarConnector8PXBlock> codec() {
        return CODEC;
    }
}
