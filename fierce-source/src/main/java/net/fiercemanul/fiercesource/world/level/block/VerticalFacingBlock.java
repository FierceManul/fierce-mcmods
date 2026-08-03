package net.fiercemanul.fiercesource.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

public class VerticalFacingBlock extends Block {


    public static final MapCodec<VerticalFacingBlock> CODEC = simpleCodec(VerticalFacingBlock::new);
    public static final DirectionProperty FACING = FSBlockStateProperties.VERTICAL_FACING;

    public VerticalFacingBlock(Properties properties) {
        super(properties);
        registerDefaultState(stateDefinition.any().setValue(FACING, Direction.DOWN));
    }

    @Override
    protected MapCodec<? extends VerticalFacingBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext placeContext) {
        Direction direction = placeContext.getClickedFace();
        return defaultBlockState().setValue(
                FACING,
                direction != Direction.DOWN && (
                        direction == Direction.UP || !(placeContext.getClickLocation().y - placeContext.getClickedPos().getY() > 0.5)
                )
                ? Direction.DOWN : Direction.UP
        );
    }

}
