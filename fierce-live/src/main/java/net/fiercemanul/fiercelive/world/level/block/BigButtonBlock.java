package net.fiercemanul.fiercelive.world.level.block;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Supplier;

public class BigButtonBlock extends ButtonBlock {


    public static final MapCodec<ButtonBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                                          BlockSetType.CODEC.fieldOf("block_set_type").forGetter(buttonBlock -> buttonBlock.type),
                                          Codec.intRange(1, 1024).fieldOf("ticks_to_stay_pressed").forGetter(buttonBlock -> buttonBlock.ticksToStayPressed),
                                          propertiesCodec()
                                  )
                                  .apply(instance, BigButtonBlock::new)
    );
    protected static final VoxelShape CEILING_AABB_X = Block.box(4.0, 14.0, 2.0, 12.0, 16.0, 14.0);
    protected static final VoxelShape CEILING_AABB_Z = Block.box(2.0, 14.0, 4.0, 14.0, 16.0, 12.0);
    protected static final VoxelShape FLOOR_AABB_X = Block.box(4.0, 0.0, 2.0, 12.0, 2.0, 14.0);
    protected static final VoxelShape FLOOR_AABB_Z = Block.box(2.0, 0.0, 4.0, 14.0, 2.0, 12.0);
    protected static final VoxelShape NORTH_AABB = Block.box(2.0, 4.0, 14.0, 14.0, 12.0, 16.0);
    protected static final VoxelShape SOUTH_AABB = Block.box(2.0, 4.0, 0.0, 14.0, 12.0, 2.0);
    protected static final VoxelShape WEST_AABB = Block.box(14.0, 4.0, 2.0, 16.0, 12.0, 14.0);
    protected static final VoxelShape EAST_AABB = Block.box(0.0, 4.0, 2.0, 2.0, 12.0, 14.0);
    protected static final VoxelShape PRESSED_CEILING_AABB_X = Block.box(4.0, 15.0, 2.0, 12.0, 16.0, 14.0);
    protected static final VoxelShape PRESSED_CEILING_AABB_Z = Block.box(2.0, 15.0, 4.0, 14.0, 16.0, 12.0);
    protected static final VoxelShape PRESSED_FLOOR_AABB_X = Block.box(4.0, 0.0, 2.0, 12.0, 1.0, 14.0);
    protected static final VoxelShape PRESSED_FLOOR_AABB_Z = Block.box(2.0, 0.0, 4.0, 14.0, 1.0, 12.0);
    protected static final VoxelShape PRESSED_NORTH_AABB = Block.box(2.0, 4.0, 15.0, 14.0, 12.0, 16.0);
    protected static final VoxelShape PRESSED_SOUTH_AABB = Block.box(2.0, 4.0, 0.0, 14.0, 12.0, 1.0);
    protected static final VoxelShape PRESSED_WEST_AABB = Block.box(15.0, 4.0, 2.0, 16.0, 12.0, 14.0);
    protected static final VoxelShape PRESSED_EAST_AABB = Block.box(0.0, 4.0, 2.0, 1.0, 12.0, 14.0);
    protected final Supplier<? extends BigButtonBlock> partBlock;

    public BigButtonBlock(BlockSetType type, int ticksToStayPressed, Properties properties) {
        super(type, ticksToStayPressed, properties);
        this.partBlock = () -> this;
    }

    public BigButtonBlock(BlockSetType type, int ticksToStayPressed, Properties properties, Supplier<? extends BigButtonBlock> partBlock) {
        super(type, ticksToStayPressed, properties);
        this.partBlock = partBlock;
    }

    @Override
    public MapCodec<ButtonBlock> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);
        boolean flag = state.getValue(POWERED);
        switch (state.getValue(FACE)) {
            case FLOOR:
                if (direction.getAxis() == Direction.Axis.X) {
                    return flag ? PRESSED_FLOOR_AABB_X : FLOOR_AABB_X;
                }

                return flag ? PRESSED_FLOOR_AABB_Z : FLOOR_AABB_Z;
            case WALL:
                return switch (direction) {
                    case EAST -> flag ? PRESSED_EAST_AABB : EAST_AABB;
                    case WEST -> flag ? PRESSED_WEST_AABB : WEST_AABB;
                    case SOUTH -> flag ? PRESSED_SOUTH_AABB : SOUTH_AABB;
                    case NORTH, UP, DOWN -> flag ? PRESSED_NORTH_AABB : NORTH_AABB;
                };
            case CEILING:
            default:
                if (direction.getAxis() == Direction.Axis.X) {
                    return flag ? PRESSED_CEILING_AABB_X : CEILING_AABB_X;
                } else {
                    return flag ? PRESSED_CEILING_AABB_Z : CEILING_AABB_Z;
                }
        }
    }

    protected BlockState asPart(BlockState state) {
        return partBlock.get().defaultBlockState()
                        .setValue(FACING, state.getValue(FACING))
                        .setValue(FACE, state.getValue(FACE));
    }

}
