package net.fiercemanul.fiercelive.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.util.VoxelShapeHelper;
import net.fiercemanul.fiercesource.world.item.WrenchUtils;
import net.fiercemanul.fiercesource.world.level.block.HorizonFacingWaterloggedBlock;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;

public class FakeChestBlock extends HorizonFacingWaterloggedBlock {


    public static final MapCodec<FakeChestBlock> CODEC = simpleCodec(FakeChestBlock::new);


    public static final EnumProperty<ChestType> TYPE = BlockStateProperties.CHEST_TYPE;
    protected static final VoxelShape SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 14.0, 15.0);
    private static final VoxelShapeHelper SHAPE_HELPER = new VoxelShapeHelper().applyCube(1.0, 0.0, 1.0, 16.0, 14.0, 15.0);
    //左箱子,以大箱子自身为基准.
    protected static final VoxelShape SHAPE_NORTH = SHAPE_HELPER.north();
    protected static final VoxelShape SHAPE_SOUTH = SHAPE_HELPER.south();
    protected static final VoxelShape SHAPE_WEST = SHAPE_HELPER.west();
    protected static final VoxelShape SHAPE_EAST = SHAPE_HELPER.east();


    public FakeChestBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(TYPE, ChestType.SINGLE).setValue(WATERLOGGED,false));
    }

    @Override
    protected MapCodec<? extends FakeChestBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, TYPE, WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        Direction chestDirection = context.getHorizontalDirection().getOpposite();
        BlockPos chestPos = context.getClickedPos();
        Direction clickedFace = context.getClickedFace();

        BlockState state = this.defaultBlockState()
                               .setValue(FACING, chestDirection)
                               .setValue(WATERLOGGED, level.getFluidState(chestPos).getType() == Fluids.WATER);

        if (context.isSecondaryUseActive()) {
            ChestType type;
            if (chestDirection.getCounterClockWise().equals(clickedFace)) type = ChestType.LEFT;
            else if (chestDirection.getClockWise().equals(clickedFace)) type = ChestType.RIGHT;
            else return state.setValue(TYPE, ChestType.SINGLE);

            BlockState otherState = level.getBlockState(chestPos.relative(clickedFace.getOpposite()));
            if (otherState.is(this)
                    && otherState.getValue(FACING).equals(chestDirection)
                    && !otherState.getValue(TYPE).equals(type))
                return state.setValue(TYPE, type);
            return state.setValue(TYPE, ChestType.SINGLE);
        }

        BlockState testState = level.getBlockState(chestPos.relative(chestDirection.getClockWise()));
        if (testState.is(this)
                && testState.getValue(FACING).equals(chestDirection)
                && !testState.getValue(TYPE).equals(ChestType.LEFT))
            return state.setValue(TYPE, ChestType.LEFT);
        testState = level.getBlockState(chestPos.relative(chestDirection.getCounterClockWise()));
        if (testState.is(this)
                && testState.getValue(FACING).equals(chestDirection)
                && !testState.getValue(TYPE).equals(ChestType.RIGHT))
            return state.setValue(TYPE, ChestType.RIGHT);

        return state.setValue(TYPE, ChestType.SINGLE);
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        BlockState state = pState;
        Direction chestDirection = pState.getValue(FACING);

        if (neighborState.is(this) && neighborState.getValue(FACING).equals(chestDirection)) {
            if (direction.equals(chestDirection.getCounterClockWise()) && neighborState.getValue(TYPE).equals(ChestType.LEFT))
                return super.updateShape(state.setValue(TYPE, ChestType.RIGHT), direction, neighborState, level, pos, neighborPos);
            else if (direction.equals(chestDirection.getClockWise()) && neighborState.getValue(TYPE).equals(ChestType.RIGHT))
                return super.updateShape(state.setValue(TYPE, ChestType.LEFT), direction, neighborState, level, pos, neighborPos);
        }

        ChestType type = pState.getValue(TYPE);
        if (type.equals(ChestType.LEFT)) {
            BlockState testState = level.getBlockState(pos.relative(chestDirection.getClockWise()));
            if (!testState.is(this)
                    || !testState.getValue(FACING).equals(chestDirection)
                    || !testState.getValue(TYPE).equals(ChestType.RIGHT))
                state = state.setValue(TYPE, ChestType.SINGLE);
        }
        else if (type.equals(ChestType.RIGHT)) {
            BlockState testState = level.getBlockState(pos.relative(chestDirection.getCounterClockWise()));
            if (!testState.is(this)
                    || !testState.getValue(FACING).equals(chestDirection)
                    || !testState.getValue(TYPE).equals(ChestType.LEFT))
                state = state.setValue(TYPE, ChestType.SINGLE);
        }

        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    public static Direction getChestAABBDirection(BlockState state) {
        Direction direction = state.getValue(FACING);
        return state.getValue(TYPE) == ChestType.LEFT ? direction : direction.getOpposite();
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        if (pState.getValue(TYPE) == ChestType.SINGLE) {
            return SHAPE;
        } else {
            return switch (getChestAABBDirection(pState)) {
                case SOUTH -> SHAPE_SOUTH;
                case WEST -> SHAPE_WEST;
                case EAST -> SHAPE_EAST;
                default -> SHAPE_NORTH;
            };
        }
    }

    @Override
    public float getShadeBrightness(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return 1.0F;
    }

    @Override
    public BlockState mirror(BlockState state, Mirror pMirror) {
        BlockState rotated = state.rotate(pMirror.getRotation(state.getValue(FACING)));
        return pMirror == Mirror.NONE ? rotated : rotated.setValue(TYPE, rotated.getValue(TYPE).getOpposite());
    }

    @Override
    protected ItemInteractionResult useItemOn(
            ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult
    ) {
        return WrenchUtils.interactRotate(stack, state, level, pos, player);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("fiercelive.tip.no_more_be").withStyle(ChatFormatting.DARK_PURPLE));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        return false;
    }

    @Override
    protected boolean propagatesSkylightDown(BlockState state, BlockGetter level, BlockPos pos) {
        return state.getFluidState().isEmpty();
    }
}
