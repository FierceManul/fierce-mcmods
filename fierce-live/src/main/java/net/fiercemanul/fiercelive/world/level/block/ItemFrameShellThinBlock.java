package net.fiercemanul.fiercelive.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.util.VoxelShapeHelper;
import net.fiercemanul.fiercesource.world.item.WrenchUtils;
import net.fiercemanul.fiercesource.world.level.block.FacingWaterloggedBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;


public class ItemFrameShellThinBlock extends FacingWaterloggedBlock {


    public static final MapCodec<ItemFrameShellThinBlock> CODEC = simpleCodec(ItemFrameShellThinBlock::new);

    private static final VoxelShapeHelper SHAPE_HELPER = new VoxelShapeHelper()
            .applyCube(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 6.0D);
    protected static final VoxelShape SHAPE_NORTH = SHAPE_HELPER.south();
    protected static final VoxelShape SHAPE_SOUTH = SHAPE_HELPER.north();
    protected static final VoxelShape SHAPE_WEST = SHAPE_HELPER.east();
    protected static final VoxelShape SHAPE_EAST = SHAPE_HELPER.west();
    protected static final VoxelShape SHAPE_UP = SHAPE_HELPER.down();
    protected static final VoxelShape SHAPE_DOWN = SHAPE_HELPER.up();


    public ItemFrameShellThinBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends ItemFrameShellThinBlock> codec() {
        return CODEC;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState()
                   .setValue(FACING, context.getClickedFace())
                   .setValue(WATERLOGGED, context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return switch (pState.getValue(FACING)) {
            case NORTH -> SHAPE_NORTH;
            case SOUTH -> SHAPE_SOUTH;
            case WEST -> SHAPE_WEST;
            case EAST -> SHAPE_EAST;
            case UP -> SHAPE_UP;
            case DOWN -> SHAPE_DOWN;
        };
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (isWhiteListEntity(context)) return Shapes.empty();
        return getShape(state, level, pos, context);
    }

    public static boolean isWhiteListEntity(CollisionContext context) {
        return context instanceof EntityCollisionContext ec && (ec.getEntity() instanceof ItemFrame || ec.getEntity() instanceof Painting);
    }

    @Override
    public VoxelShape getVisualShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return Shapes.empty();
    }

    @Override
    protected boolean skipRendering(BlockState state, BlockState adjacentState, Direction direction) {
        if (adjacentState.is(this)) {
            Direction myFacing = state.getValue(FACING);
            return direction.getAxis() != myFacing.getAxis() && myFacing == adjacentState.getValue(FACING);
        }
        return super.skipRendering(state, adjacentState, direction);
    }

    @Override
    protected ItemInteractionResult useItemOn(
            ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult
    ) {
        return WrenchUtils.interact(FACING, stack, state, level, pos, player);
    }

}
