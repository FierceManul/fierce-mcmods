package net.fiercemanul.fiercedecoration.world.level.block;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercedecoration.world.level.block.state.properties.ChairType;
import net.fiercemanul.fiercesource.util.VoxelShapeHelper;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WoolSofaBlock extends AbstractLongChairBlock {


    public static final MapCodec<WoolSofaBlock> CODEC = simpleCodec(WoolSofaBlock::new);

    protected static final VoxelShapeHelper SHAPE_LEFT = new VoxelShapeHelper().applyCube(0.0, 8.0, 0.0, 3.0, 13.0, 12.0);
    protected static final VoxelShapeHelper SHAPE_RIGHT = new VoxelShapeHelper().applyCube(13.0, 8.0, 0.0, 16.0, 13.0, 12.0);
    protected static final VoxelShapeHelper SHAPE_BACK = new VoxelShapeHelper().applyCube(0.0, 8.0, 9.0, 16.0, 18.0, 16.0);
    protected static final VoxelShapeHelper COLLISION_SHAPE_BACK = new VoxelShapeHelper().applyCube(0.0, 8.0, 11.0, 16.0, 18.0, 16.0);
    protected static final ImmutableMap<Direction, ImmutableMap<ChairType, VoxelShape>> SHAPE_MAP = buildShapes(SHAPE_DEFAULT_BASE, SHAPE_BACK, SHAPE_LEFT, SHAPE_RIGHT);;
    protected static final ImmutableMap<Direction, ImmutableMap<ChairType, VoxelShape>> COLLISION_SHAPE_MAP = buildShapes(SHAPE_DEFAULT_BASE, COLLISION_SHAPE_BACK, SHAPE_LEFT, SHAPE_RIGHT);
    protected static final Vec3 SIT_POS_NORTH = new Vec3(0.5, 0.5625, 0.375);
    protected static final Vec3 SIT_POS_SOUTH = VoxelShapeHelper.south(SIT_POS_NORTH);
    protected static final Vec3 SIT_POS_WEST = VoxelShapeHelper.west(SIT_POS_NORTH);
    protected static final Vec3 SIT_POS_EAST = VoxelShapeHelper.east(SIT_POS_NORTH);

    public WoolSofaBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public MapCodec<? extends WoolSofaBlock> codec() {
        return CODEC;
    }

    @Override
    protected ImmutableMap<Direction, ImmutableMap<ChairType, VoxelShape>> getShapeMap() {
        return SHAPE_MAP;
    }

    @Override
    protected ImmutableMap<Direction, ImmutableMap<ChairType, VoxelShape>> getCollisionShapeMap() {
        return COLLISION_SHAPE_MAP;
    }

    @Override
    protected Vec3 getSitPos(Direction direction) {
        return switch (direction) {
            case DOWN, UP -> SIT_POS_DEFAULT;
            case NORTH -> SIT_POS_NORTH;
            case SOUTH -> SIT_POS_SOUTH;
            case WEST -> SIT_POS_WEST;
            case EAST -> SIT_POS_EAST;
        };
    }
}
