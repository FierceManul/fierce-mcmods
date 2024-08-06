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
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;

import java.util.function.BiFunction;
import java.util.function.Function;

public class AxisBlock extends WrenchDismantleBlock {


    public static final MapCodec<AxisBlock> CODEC = simpleCodec(AxisBlock::new);
    protected static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;

    public static final Function<BlockPlaceContext, Direction.Axis> LOOKING_AXIS = placeContext -> placeContext.getHorizontalDirection().getAxis();
    public static final Function<BlockPlaceContext, Direction.Axis> CLOCK_WISE_AXIS = placeContext -> placeContext.getHorizontalDirection().getClockWise().getAxis();
    public static final Function<BlockPlaceContext, Direction.Axis> CLICKED_AXIS = placeContext -> placeContext.getClickedFace().getAxis();


    public static AxisBlock look(Properties pProperties) {
        return new AxisBlock(pProperties, AxisBlock.LOOKING_AXIS);
    }

    public static <B extends Block> B look(Properties pProperties, BiFunction<Properties, Function<BlockPlaceContext, Direction.Axis>, B> function) {
        return function.apply(pProperties, AxisBlock.LOOKING_AXIS);
    }

    public static AxisBlock clockWise(Properties pProperties) {
        return new AxisBlock(pProperties, AxisBlock.CLOCK_WISE_AXIS);
    }

    public static <B extends Block> B clockWise(Properties pProperties, BiFunction<Properties, Function<BlockPlaceContext, Direction.Axis>, B> function) {
        return function.apply(pProperties, AxisBlock.CLOCK_WISE_AXIS);
    }

    public static AxisBlock click(Properties pProperties) {
        return new AxisBlock(pProperties, AxisBlock.CLICKED_AXIS);
    }

    public static <B extends Block> B click(Properties pProperties, BiFunction<Properties, Function<BlockPlaceContext, Direction.Axis>, B> function) {
        return function.apply(pProperties, AxisBlock.CLICKED_AXIS);
    }

    private final Function<BlockPlaceContext, Direction.Axis> GET_AXIS;

    public AxisBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AXIS, Direction.Axis.Y));
        GET_AXIS = CLICKED_AXIS;
    }

    public AxisBlock(Properties pProperties, Function<BlockPlaceContext, Direction.Axis> function) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AXIS, Direction.Axis.Y));
        GET_AXIS = function;
    }

    @Override
    protected MapCodec<? extends AxisBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AXIS);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState()
                .setValue(AXIS, GET_AXIS.apply(pContext));
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return RotatedPillarBlock.rotatePillar(pState, pRotation);
    }

    @Override
    protected ItemInteractionResult useItemOn(
            ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHitResult
    ) {
        return WrenchAction.defaultUseOn(AXIS, pStack, pState, pLevel, pPos, pPlayer);
    }
}
