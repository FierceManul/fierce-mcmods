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

public class HorizonAxisBlock extends WrenchDismantleBlock {


    public static final MapCodec<HorizonAxisBlock> CODEC = simpleCodec(HorizonAxisBlock::new);
    protected static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;

    public static final Function<BlockPlaceContext, Direction.Axis> LOOKING_AXIS = placeContext -> placeContext.getHorizontalDirection().getAxis();
    public static final Function<BlockPlaceContext, Direction.Axis> CLOCK_WISE_AXIS = placeContext -> placeContext.getHorizontalDirection().getClockWise().getAxis();
    public static final Function<BlockPlaceContext, Direction.Axis> CLICKED_AXIS = placeContext -> {
        Direction contextDirection = placeContext.getClickedFace();
        if (contextDirection.getAxis() == Direction.Axis.Y) return placeContext.getHorizontalDirection().getAxis();
        else return contextDirection.getAxis();
    };

    public static final Function<BlockPlaceContext, Direction.Axis> CLICKED_AXIS_CLOCK_WISE = placeContext -> {
        Direction contextDirection = placeContext.getClickedFace();
        if (contextDirection.getAxis() == Direction.Axis.Y) return placeContext.getHorizontalDirection().getClockWise().getAxis();
        else return contextDirection.getClockWise().getAxis();
    };


    public static HorizonAxisBlock look(Properties pProperties) {
        return new HorizonAxisBlock(pProperties, HorizonAxisBlock.LOOKING_AXIS);
    }

    public static <B extends Block> B look(Properties pProperties, BiFunction<Properties, Function<BlockPlaceContext, Direction.Axis>, B> function) {
        return function.apply(pProperties, HorizonAxisBlock.LOOKING_AXIS);
    }

    public static HorizonAxisBlock clockWise(Properties pProperties) {
        return new HorizonAxisBlock(pProperties, HorizonAxisBlock.CLOCK_WISE_AXIS);
    }

    public static <B extends Block> B clockWise(Properties pProperties, BiFunction<Properties, Function<BlockPlaceContext, Direction.Axis>, B> function) {
        return function.apply(pProperties, AxisBlock.CLOCK_WISE_AXIS);
    }

    public static HorizonAxisBlock click(Properties pProperties) {
        return new HorizonAxisBlock(pProperties, HorizonAxisBlock.CLICKED_AXIS);
    }

    public static <B extends Block> B click(Properties pProperties, BiFunction<Properties, Function<BlockPlaceContext, Direction.Axis>, B> function) {
        return function.apply(pProperties, HorizonAxisBlock.CLICKED_AXIS);
    }

    public static HorizonAxisBlock clickClockWise(Properties pProperties) {
        return new HorizonAxisBlock(pProperties, HorizonAxisBlock.CLICKED_AXIS_CLOCK_WISE);
    }

    public static <B extends Block> B clickClockWise(Properties pProperties, BiFunction<Properties, Function<BlockPlaceContext, Direction.Axis>, B> function) {
        return function.apply(pProperties, HorizonAxisBlock.CLICKED_AXIS_CLOCK_WISE);
    }

    private final Function<BlockPlaceContext, Direction.Axis> GET_AXIS;

    public HorizonAxisBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AXIS, Direction.Axis.X));
        GET_AXIS = CLOCK_WISE_AXIS;
    }

    public HorizonAxisBlock(Properties pProperties, Function<BlockPlaceContext, Direction.Axis> function) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AXIS, Direction.Axis.X));
        GET_AXIS = function;
    }

    @Override
    protected MapCodec<? extends HorizonAxisBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AXIS);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(AXIS, GET_AXIS.apply(pContext));
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return RotatedPillarBlock.rotatePillar(pState, pRotation);
    }

    @Override
    protected ItemInteractionResult useItemOn(
            ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult
    ) {
        return WrenchAction.defaultUseOn(AXIS, stack, state, level, pos, player);
    }
}
