package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercedecoration.tags.FDBlockTags;
import net.fiercemanul.fiercesource.world.item.WrenchAction;
import net.fiercemanul.fiercesource.world.level.block.AxisBlock;
import net.fiercemanul.fiercesource.world.level.block.HorizonAxisModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TableBlock extends HorizonAxisModelBlock {


    public static final MapCodec<TableBlock> CODEC = simpleCodec(TableBlock::new);
    public static final BooleanProperty LEGGED = BooleanProperty.create("legged");
    public static final VoxelShape SHAPE_TOP = Block.box(0.0, 14.0, 0.0, 16.0, 16.0, 16.0);
    public static final VoxelShape SHAPE_LEGGED = Shapes.or(SHAPE_TOP, Block.box(6.0, 0.0, 6.0, 10.0, 14.0, 10.0));

    public TableBlock(Properties pProperties) {
        super(pProperties, AxisBlock.CLOCK_WISE_AXIS);
        this.registerDefaultState(this.stateDefinition.any().setValue(AXIS, Direction.Axis.X).setValue(LEGGED, true).setValue(WATERLOGGED, false));
    }

    protected MapCodec<? extends TableBlock> codec() {
        return CODEC;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AXIS, LEGGED, WATERLOGGED);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return pState.getValue(LEGGED) ? SHAPE_LEGGED : SHAPE_TOP;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return super.getStateForPlacement(pContext).setValue(LEGGED, needLeg(pContext.getLevel(), pContext.getClickedPos()));
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        return super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos).setValue(LEGGED, needLeg(pLevel, pCurrentPos));
    }

    private static boolean needLeg(LevelAccessor pLevel, BlockPos pCurrentPos) {
        return !((
                pLevel.getBlockState(pCurrentPos.north()).is(FDBlockTags.TABLE_CONNECT)
                        && pLevel.getBlockState(pCurrentPos.south()).is(FDBlockTags.TABLE_CONNECT)
        ) || (
                pLevel.getBlockState(pCurrentPos.west()).is(FDBlockTags.TABLE_CONNECT)
                        && pLevel.getBlockState(pCurrentPos.east()).is(FDBlockTags.TABLE_CONNECT)
        ));
    }

    @Override
    protected ItemInteractionResult useItemOn(
            ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHitResult
    ) {
        boolean success = WrenchAction.doDefaultWrenchAction(LEGGED, pStack, pState, pLevel, pPos, pPlayer);
        return success ? ItemInteractionResult.sidedSuccess(pLevel.isClientSide) : ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    public boolean skipRendering(BlockState pState, BlockState pAdjacentState, Direction pDirection) {
        return pAdjacentState.is(this) && !pDirection.getAxis().isVertical();
    }
}
