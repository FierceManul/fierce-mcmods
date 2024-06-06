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
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;

import java.util.function.Function;

public class FacingModelBlock extends ModelBlock {


    public static final MapCodec<FacingModelBlock> CODEC = simpleCodec(FacingModelBlock::new);

    protected static final DirectionProperty FACING = BlockStateProperties.FACING;

    public static FacingModelBlock look(Properties pProperties) {
        return new FacingModelBlock(pProperties, FacingBlock.LOOKING_DIRECTION);
    }

    public static FacingModelBlock lookOpposite(Properties pProperties) {
        return new FacingModelBlock(pProperties, FacingBlock.LOOKING_DIRECTION_OPPOSITE);
    }

    public static FacingModelBlock click(Properties pProperties) {
        return new FacingModelBlock(pProperties, FacingBlock.CLICKED_DIRECTION);
    }

    public static FacingModelBlock clickOpposite(Properties pProperties) {
        return new FacingModelBlock(pProperties, FacingBlock.CLICKED_DIRECTION_OPPOSITE);
    }

    private final Function<BlockPlaceContext, Direction> GET_Direction;

    public FacingModelBlock(Properties pProperties) {
        this(pProperties, FacingBlock.LOOKING_DIRECTION_OPPOSITE);
    }

    public FacingModelBlock(Properties pProperties, Function<BlockPlaceContext, Direction> function) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
        GET_Direction = function;
    }

    @Override
    protected MapCodec<? extends FacingModelBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState()
                .setValue(FACING, GET_Direction.apply(pContext))
                .setValue(WATERLOGGED, pContext.getLevel().getFluidState(pContext.getClickedPos()).getType() == Fluids.WATER);
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
