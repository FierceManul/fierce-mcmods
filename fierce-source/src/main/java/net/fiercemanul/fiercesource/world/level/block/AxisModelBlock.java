package net.fiercemanul.fiercesource.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.world.item.WrenchAction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;

import java.util.function.Function;

public class AxisModelBlock extends ModelBlock {


    public static final MapCodec<AxisModelBlock> CODEC = simpleCodec(AxisModelBlock::new);
    protected static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;

    public static AxisModelBlock look(Properties pProperties) {
        return new AxisModelBlock(pProperties, AxisBlock.LOOKING_AXIS);
    }

    public static AxisModelBlock clockWise(Properties pProperties) {
        return new AxisModelBlock(pProperties, AxisBlock.CLOCK_WISE_AXIS);
    }

    public static AxisModelBlock click(Properties pProperties) {
        return new AxisModelBlock(pProperties, AxisBlock.CLICKED_AXIS);
    }

    private final Function<BlockPlaceContext, Direction.Axis> GET_AXIS;

    public AxisModelBlock(Properties pProperties) {
        this(pProperties, AxisBlock.CLICKED_AXIS);
    }

    public AxisModelBlock(Properties pProperties, Function<BlockPlaceContext, Direction.Axis> function) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AXIS, Direction.Axis.Y).setValue(WATERLOGGED, false));
        GET_AXIS = function;
    }

    @Override
    protected MapCodec<? extends AxisModelBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AXIS, WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState()
                .setValue(AXIS, GET_AXIS.apply(pContext))
                .setValue(WATERLOGGED, pContext.getLevel().getFluidState(pContext.getClickedPos()).getType() == Fluids.WATER);
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return RotatedPillarBlock.rotatePillar(pState, pRotation);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        boolean success = WrenchAction.doDefaultWrenchAction(AXIS, pState, pLevel, pPos, pPlayer, pHand);
        return success ? InteractionResult.sidedSuccess(pLevel.isClientSide) : InteractionResult.PASS;
    }
}
