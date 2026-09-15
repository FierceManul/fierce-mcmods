package net.fiercemanul.fiercelive.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.util.VoxelShapeHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CabinetMirrorBlock extends MirrorBlock {


    public static final MapCodec<CabinetMirrorBlock> CODEC = simpleCodec(CabinetMirrorBlock::new);
    protected static final VoxelShapeHelper SHAPE = new VoxelShapeHelper()
            .applyCube(0, 4, 12, 16, 16, 16)
            .applyCube(2, 15, 11, 14, 16, 12);
    protected static final VoxelShape SHAPE_NORTH = SHAPE.north();
    protected static final VoxelShape SHAPE_SOUTH = SHAPE.south();
    protected static final VoxelShape SHAPE_WEST = SHAPE.west();
    protected static final VoxelShape SHAPE_EAST = SHAPE.east();

    public CabinetMirrorBlock(Properties properties) {
        super(properties);
    }

    @Override
    public MapCodec<? extends CabinetMirrorBlock> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case SOUTH -> SHAPE_SOUTH;
            case WEST -> SHAPE_WEST;
            case EAST -> SHAPE_EAST;
            default -> SHAPE_NORTH;
        };
    }

}
