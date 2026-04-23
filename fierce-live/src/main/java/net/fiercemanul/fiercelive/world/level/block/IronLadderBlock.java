package net.fiercemanul.fiercelive.world.level.block;

import net.fiercemanul.fiercelive.data.tags.FLBlockTags;
import net.fiercemanul.fiercelive.world.level.block.state.properties.FLBlockStateProperties;
import net.fiercemanul.fiercesource.world.item.WrenchUtils;
import net.fiercemanul.fiercesource.world.level.block.HorizonFacingWaterloggedBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class IronLadderBlock extends HorizonFacingWaterloggedBlock {


    protected static final BooleanProperty FRAMED = FLBlockStateProperties.FRAMED;
    protected static final VoxelShape EAST_AABB = Block.box(0.0, 0.0, 0.0, 3.0, 16.0, 16.0);
    protected static final VoxelShape WEST_AABB = Block.box(13.0, 0.0, 0.0, 16.0, 16.0, 16.0);
    protected static final VoxelShape SOUTH_AABB = Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 3.0);
    protected static final VoxelShape NORTH_AABB = Block.box(0.0, 0.0, 13.0, 16.0, 16.0, 16.0);
    public final Block frameBlock;

    public IronLadderBlock(Properties properties, Block frameBlock) {
        super(properties);
        this.frameBlock = frameBlock;
        this.registerDefaultState(this.stateDefinition.any().setValue(FRAMED, false).setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FRAMED, FACING, WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction clickedFace = context.getClickedFace();
        return this.defaultBlockState()
                   .setValue(FACING, clickedFace.getAxis().isVertical() ? context.getHorizontalDirection().getOpposite() : clickedFace)
                   .setValue(WATERLOGGED, context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER);
    }

    @Override
    protected boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
        return !state.getValue(FRAMED) && useContext.getItemInHand().is(frameBlock.asItem());
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return state.getValue(FRAMED) ? Shapes.block() : switch (state.getValue(FACING)) {
            case SOUTH -> SOUTH_AABB;
            case WEST -> WEST_AABB;
            case EAST -> EAST_AABB;
            default -> NORTH_AABB;
        };
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case SOUTH -> SOUTH_AABB;
            case WEST -> WEST_AABB;
            case EAST -> EAST_AABB;
            default -> NORTH_AABB;
        };
    }

    @Override
    protected boolean propagatesSkylightDown(BlockState state, BlockGetter level, BlockPos pos) {
        return state.getFluidState().isEmpty();
    }

    @Override
    protected boolean skipRendering(BlockState state, BlockState adjacentState, Direction direction) {
        return direction.getStepY() == 0 && adjacentState.is(FLBlockTags.FRAMES);
    }

    @Override
    protected ItemInteractionResult useItemOn(
            ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand,
            BlockHitResult hitResult
    ) {
        if (WrenchUtils.isWrench(stack) && player.isShiftKeyDown()) {
            if (state.getValue(FRAMED)) {
                if (level.setBlock(pos, state.setValue(FRAMED, false), 11)) player.addItem(frameBlock.asItem().getDefaultInstance());
            }
            else return WrenchUtils.interact(stack, state, level, pos, player);
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }
}
