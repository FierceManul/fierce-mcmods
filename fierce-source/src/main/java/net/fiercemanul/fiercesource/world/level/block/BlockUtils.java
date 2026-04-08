package net.fiercemanul.fiercesource.world.level.block;

import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.Vec3;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.ToIntFunction;

public final class BlockUtils {


    private BlockUtils() {}

    public static <T> ImmutableMap<BlockState, T> forEachStates(Block block, Function<BlockState, T> getter) {
        return block.getStateDefinition().getPossibleStates().stream().collect(ImmutableMap.toImmutableMap(Function.identity(), getter));
    }

    public static <E extends Enum<E>, T> ImmutableMap<BlockState, ImmutableMap<E, T>> forEachStatesWith(Block block, Class<E> enumType, BiFunction<BlockState, E, T> getter) {
        return block.getStateDefinition().getPossibleStates().stream().collect(ImmutableMap.toImmutableMap(
                Function.identity(),
                state -> {
                    ImmutableMap.Builder<E, T> builder = ImmutableMap.builder();
                    for (E e : enumType.getEnumConstants()) builder.put(e, getter.apply(state, e));
                    return builder.build();
                }
        ));
    }

    public static boolean hasCollision(BlockGetter level, BlockPos pos) {
        return !level.getBlockState(pos).getCollisionShape(level, pos).isEmpty();
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

    public static ToIntFunction<BlockState> litBlockEmission(int lightValue) {
        return state -> state.getValue(BlockStateProperties.LIT) ? lightValue : 0;
    }
}
