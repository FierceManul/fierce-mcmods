package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.phys.shapes.VoxelShape;


public class PillarConnector6PXBlock extends PillarConnectorBlock {


    public static final MapCodec<PillarConnector6PXBlock> CODEC = simpleCodec(PillarConnector6PXBlock::new);
    public static final float APOTHEM = 0.1875F;
    public static final VoxelShape[] SHAPE_BY_INDEX = makeShapes(APOTHEM);

    public PillarConnector6PXBlock(Properties pProperties) {
        super(APOTHEM, SHAPE_BY_INDEX, pProperties);
    }

    @Override
    protected MapCodec<? extends PillarConnector6PXBlock> codec() {
        return CODEC;
    }
}
