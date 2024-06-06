package net.fiercemanul.fiercesource.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.world.item.WrenchAction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;

import java.util.function.BiFunction;
import java.util.function.Function;

public class HorizonFacingBlock extends WrenchDismantleBlock {

    public static final MapCodec<HorizonFacingBlock> CODEC = simpleCodec(HorizonFacingBlock::new);
    protected static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public static final Function<BlockPlaceContext, Direction> HORIZONTAL_DIRECTION = BlockPlaceContext::getHorizontalDirection;
    public static final Function<BlockPlaceContext, Direction> HORIZONTAL_DIRECTION_OPPOSITE = placeContext -> placeContext.getHorizontalDirection().getOpposite();
    public static final Function<BlockPlaceContext, Direction> CLICKED_DIRECTION = placeContext -> {
        Direction contextDirection = placeContext.getClickedFace();
        if (contextDirection.getAxis() == Direction.Axis.Y) return placeContext.getHorizontalDirection().getOpposite();
        else return contextDirection;
    };
    public static final Function<BlockPlaceContext, Direction> CLICKED_DIRECTION_OPPOSITE = placeContext -> {
        Direction contextDirection = placeContext.getClickedFace();
        if (contextDirection.getAxis() == Direction.Axis.Y) return placeContext.getHorizontalDirection().getOpposite();
        else return contextDirection.getOpposite();
    };
    public static HorizonFacingBlock look(Properties pProperties) {
        return new HorizonFacingBlock(pProperties, HorizonFacingBlock.HORIZONTAL_DIRECTION);
    }

    public static <B extends Block> B look(Properties pProperties, BiFunction<Properties, Function<BlockPlaceContext, Direction>, B> function) {
        return function.apply(pProperties, HorizonFacingBlock.HORIZONTAL_DIRECTION);
    }

    public static HorizonFacingBlock lookOpposite(Properties pProperties) {
        return new HorizonFacingBlock(pProperties, HorizonFacingBlock.HORIZONTAL_DIRECTION_OPPOSITE);
    }

    public static <B extends Block> B lookOpposite(Properties pProperties, BiFunction<Properties, Function<BlockPlaceContext, Direction>, B> function) {
        return function.apply(pProperties, HorizonFacingBlock.HORIZONTAL_DIRECTION_OPPOSITE);
    }

    public static HorizonFacingBlock click(Properties pProperties) {
        return new HorizonFacingBlock(pProperties, HorizonFacingBlock.CLICKED_DIRECTION);
    }

    public static <B extends Block> B click(Properties pProperties, BiFunction<Properties, Function<BlockPlaceContext, Direction>, B> function) {
        return function.apply(pProperties, HorizonFacingBlock.CLICKED_DIRECTION);
    }

    public static HorizonFacingBlock clickOpposite(Properties pProperties) {
        return new HorizonFacingBlock(pProperties, HorizonFacingBlock.CLICKED_DIRECTION_OPPOSITE);
    }

    public static <B extends Block> B clickOpposite(Properties pProperties, BiFunction<Properties, Function<BlockPlaceContext, Direction>, B> function) {
        return function.apply(pProperties, HorizonFacingBlock.CLICKED_DIRECTION_OPPOSITE);
    }

    private final Function<BlockPlaceContext, Direction> GET_Direction;

    public HorizonFacingBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
        GET_Direction = HORIZONTAL_DIRECTION_OPPOSITE;
    }

    public HorizonFacingBlock(Properties pProperties, Function<BlockPlaceContext, Direction> function) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
        GET_Direction = function;
    }

    @Override
    protected MapCodec<? extends HorizonFacingBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState()
                .setValue(FACING, GET_Direction.apply(pContext));
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Override
    protected ItemInteractionResult useItemOn(
            ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHitResult
    ) {
        boolean success = WrenchAction.doDefaultWrenchAction(FACING, pStack, pState, pLevel, pPos, pPlayer);
        return success ? ItemInteractionResult.sidedSuccess(pLevel.isClientSide) : ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }
}
