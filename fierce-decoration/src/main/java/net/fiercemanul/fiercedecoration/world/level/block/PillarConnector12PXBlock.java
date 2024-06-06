package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.phys.shapes.VoxelShape;


public class PillarConnector12PXBlock extends PillarConnectorBlock {


    public static final MapCodec<PillarConnector12PXBlock> CODEC = simpleCodec(PillarConnector12PXBlock::new);
    public static final float APOTHEM = 0.375F;
    public static final VoxelShape[] SHAPE_BY_INDEX = makeShapes(APOTHEM);
    public static final VoxelShape SUPPORT_SHAPE = makeSupportShape(APOTHEM);

    public PillarConnector12PXBlock(Properties pProperties) {
        super(APOTHEM, SHAPE_BY_INDEX, SUPPORT_SHAPE, pProperties);
    }

    @Override
    protected MapCodec<? extends PillarConnector12PXBlock> codec() {
        return CODEC;
    }
}
