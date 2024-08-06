package net.fiercemanul.fiercesource.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.ToIntFunction;

public final class BlockUtils {


    public static final VoxelShape CRYSTAL_BASE = Shapes.join(
            Block.box(1.0, 0.0, 1.0, 15.0, 1.0, 15.0),
            Block.box(5.0, 0.0, 5.0, 11.0, 1.0, 11.0),
            BooleanOp.ONLY_FIRST
    );

    private BlockUtils() {}

    public static boolean hasCollision(BlockGetter pLevel, BlockPos pPos) {
        return !pLevel.getBlockState(pPos).getCollisionShape(pLevel, pPos).isEmpty();
    }

    public static Direction getInteractionDirection(float apothem, Vec3 hitRelativePos, Direction clickDirection) {
        if (hitRelativePos.z < apothem) return Direction.NORTH;
        else if (hitRelativePos.z > apothem + 0.5F) return Direction.SOUTH;
        else if (hitRelativePos.x < apothem) return Direction.WEST;
        else if (hitRelativePos.x > apothem + 0.5F) return Direction.EAST;
        else if (hitRelativePos.y < apothem) return Direction.DOWN;
        else if (hitRelativePos.y > apothem + 0.5F) return Direction.UP;
        else return clickDirection;
    }

    public static BooleanProperty getInteractionDirectionProperty(float apothem, Vec3 hitRelativePos, BooleanProperty defaultProperty) {
        if (hitRelativePos.z < 0.5F - apothem) return BlockStateProperties.NORTH;
        else if (hitRelativePos.z > 0.5F + apothem) return BlockStateProperties.SOUTH;
        else if (hitRelativePos.x < 0.5F - apothem) return BlockStateProperties.WEST;
        else if (hitRelativePos.x > 0.5F + apothem) return BlockStateProperties.EAST;
        else if (hitRelativePos.y < 0.5F - apothem) return BlockStateProperties.DOWN;
        else if (hitRelativePos.y > 0.5F + apothem) return BlockStateProperties.UP;
        else return defaultProperty;
    }

    public static ToIntFunction<BlockState> litBlockEmission(int plightValue) {
        return state -> state.getValue(BlockStateProperties.LIT) ? plightValue : 0;
    }
}
