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
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;

import java.util.function.Function;

public class HorizonAxisModelBlock extends ModelBlock {

    public static final MapCodec<HorizonAxisModelBlock> CODEC = simpleCodec(HorizonAxisModelBlock::new);
    protected static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;

    public static HorizonAxisModelBlock look(Properties pProperties) {
        return new HorizonAxisModelBlock(pProperties, HorizonAxisBlock.LOOKING_AXIS);
    }

    public static HorizonAxisModelBlock clockWise(Properties pProperties) {
        return new HorizonAxisModelBlock(pProperties, HorizonAxisBlock.CLOCK_WISE_AXIS);
    }

    public static HorizonAxisModelBlock click(Properties pProperties) {
        return new HorizonAxisModelBlock(pProperties, HorizonAxisBlock.CLICKED_AXIS);
    }

    public static HorizonAxisModelBlock clickClockWise(Properties pProperties) {
        return new HorizonAxisModelBlock(pProperties, HorizonAxisBlock.CLICKED_AXIS_CLOCK_WISE);
    }

    private final Function<BlockPlaceContext, Direction.Axis> GET_AXIS;

    public HorizonAxisModelBlock(Properties pProperties) {
        this(pProperties, HorizonAxisBlock.CLOCK_WISE_AXIS);
    }

    public HorizonAxisModelBlock(Properties pProperties, Function<BlockPlaceContext, Direction.Axis> function) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AXIS, Direction.Axis.X).setValue(WATERLOGGED, false));
        GET_AXIS = function;
    }

    @Override
    protected MapCodec<? extends HorizonAxisModelBlock> codec() {
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
    protected ItemInteractionResult useItemOn(
            ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHitResult
    ) {
        return WrenchAction.defaultUseOn(AXIS, pStack, pState, pLevel, pPos, pPlayer);
    }
}
