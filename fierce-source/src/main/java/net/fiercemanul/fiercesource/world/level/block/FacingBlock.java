package net.fiercemanul.fiercesource.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.world.item.WrenchAction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
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

public class FacingBlock extends WrenchDismantleBlock {


    public static final MapCodec<FacingBlock> CODEC = simpleCodec(FacingBlock::new);

    protected static final DirectionProperty FACING = BlockStateProperties.FACING;

    public static final Function<BlockPlaceContext, Direction> LOOKING_DIRECTION = BlockPlaceContext::getNearestLookingDirection;
    public static final Function<BlockPlaceContext, Direction> LOOKING_DIRECTION_OPPOSITE = placeContext -> placeContext.getNearestLookingDirection().getOpposite();
    public static final Function<BlockPlaceContext, Direction> CLICKED_DIRECTION = BlockPlaceContext::getClickedFace;
    public static final Function<BlockPlaceContext, Direction> CLICKED_DIRECTION_OPPOSITE = placeContext -> placeContext.getClickedFace().getOpposite();


    public static FacingBlock look(Properties pProperties) {
        return new FacingBlock(pProperties, FacingBlock.LOOKING_DIRECTION);
    }

    public static <B extends Block> B look(Properties pProperties, BiFunction<Properties, Function<BlockPlaceContext, Direction>, B> function) {
        return function.apply(pProperties, FacingBlock.LOOKING_DIRECTION);
    }

    public static FacingBlock lookOpposite(Properties pProperties) {
        return new FacingBlock(pProperties, FacingBlock.LOOKING_DIRECTION_OPPOSITE);
    }

    public static <B extends Block> B lookOpposite(Properties pProperties, BiFunction<Properties, Function<BlockPlaceContext, Direction>, B> function) {
        return function.apply(pProperties, FacingBlock.LOOKING_DIRECTION_OPPOSITE);
    }

    public static FacingBlock click(Properties pProperties) {
        return new FacingBlock(pProperties, FacingBlock.CLICKED_DIRECTION);
    }

    public static <B extends Block> B click(Properties pProperties, BiFunction<Properties, Function<BlockPlaceContext, Direction>, B> function) {
        return function.apply(pProperties, FacingBlock.CLICKED_DIRECTION);
    }

    public static FacingBlock clickOpposite(Properties pProperties) {
        return new FacingBlock(pProperties, FacingBlock.CLICKED_DIRECTION_OPPOSITE);
    }

    public static <B extends Block> B clickOpposite(Properties pProperties, BiFunction<Properties, Function<BlockPlaceContext, Direction>, B> function) {
        return function.apply(pProperties, FacingBlock.CLICKED_DIRECTION_OPPOSITE);
    }

    private final Function<BlockPlaceContext, Direction> GET_Direction;

    public FacingBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
        GET_Direction = LOOKING_DIRECTION_OPPOSITE;
    }

    public FacingBlock(Properties pProperties, Function<BlockPlaceContext, Direction> function) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
        GET_Direction = function;
    }

    @Override
    protected MapCodec<? extends FacingBlock> codec() {
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
            ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult
    ) {
        return WrenchAction.defaultUseOn(FACING, stack, state, level, pos, player);
    }
}
