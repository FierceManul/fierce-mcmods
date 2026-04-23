package net.fiercemanul.fiercesource.world.level.block;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.objects.Object2ByteLinkedOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.EmptyBlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.ToIntFunction;

public final class BlockUtils {


    public static final ThreadLocal<Object2ByteLinkedOpenHashMap<Block.BlockStatePairKey>> OCCLUSION_CACHE = ThreadLocal.withInitial(
            () -> {
                Object2ByteLinkedOpenHashMap<Block.BlockStatePairKey> map = new Object2ByteLinkedOpenHashMap<>(2048, 0.25F) {
                    @Override
                    protected void rehash(int newN) {}
                };
                map.defaultReturnValue((byte)127);
                return map;
            }
    );

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

    /**
     * 与原版的 {@link Block#shouldRenderFace(BlockState, BlockGetter, BlockPos, Direction, BlockPos)} 行为一致。
     * 通常用于处理 {@code noOcclusion()} 的方块之间的剔除，因为原版逻辑中会直接跳过。
     * <p>
     * 注意：需要先判定其他剔除条件，然后再调用该方法进行具体的形状剔除判断。
     */
    public static boolean shouldRenderFace(BlockState state, BlockState adjacentState, Direction direction) {
        Block.BlockStatePairKey block$blockstatepairkey = new Block.BlockStatePairKey(state, adjacentState, direction);
        Object2ByteLinkedOpenHashMap<Block.BlockStatePairKey> object2bytelinkedopenhashmap = OCCLUSION_CACHE.get();
        byte b0 = object2bytelinkedopenhashmap.getAndMoveToFirst(block$blockstatepairkey);
        if (b0 != 127) return b0 != 0;
        else {
            VoxelShape shape = state.getFaceOcclusionShape(EmptyBlockGetter.INSTANCE, BlockPos.ZERO, direction);
            if (shape.isEmpty()) return true;
            else {
                VoxelShape adjacentShape = adjacentState.getFaceOcclusionShape(EmptyBlockGetter.INSTANCE, BlockPos.ZERO, direction.getOpposite());
                boolean flag = Shapes.joinIsNotEmpty(shape, adjacentShape, BooleanOp.ONLY_FIRST);
                if (object2bytelinkedopenhashmap.size() == 2048) object2bytelinkedopenhashmap.removeLastByte();
                object2bytelinkedopenhashmap.putAndMoveToFirst(block$blockstatepairkey, (byte)(flag ? 1 : 0));
                return flag;
            }
        }
    }

    private BlockUtils() {}

}
