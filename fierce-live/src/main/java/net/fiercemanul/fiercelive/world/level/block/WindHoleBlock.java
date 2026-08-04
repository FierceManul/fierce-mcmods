package net.fiercemanul.fiercelive.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.util.VoxelShapeHelper;
import net.fiercemanul.fiercesource.world.item.WrenchUtils;
import net.fiercemanul.fiercesource.world.level.block.RotatedPillarWaterloggedBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WindHoleBlock extends RotatedPillarWaterloggedBlock {


    public static final MapCodec<? extends WindHoleBlock> CODEC = simpleCodec(WindHoleBlock::new);
    private static final VoxelShapeHelper SHAPE_HELPER = new VoxelShapeHelper()
            .applyCube(0, 0, 5, 16, 3, 11)
            .applyCube(0, 13, 5, 16, 16, 11)
            .applyCube(0, 3, 5, 3, 13, 11)
            .applyCube(13, 3, 5, 16, 13, 11)
            .applyCube(3, 3, 6, 6, 6, 10)
            .applyCube(10, 3, 6, 13, 6, 10)
            .applyCube(10, 10, 6, 13, 13, 10)
            .applyCube(3, 10, 6, 6, 13, 10);
    public static final VoxelShape SHAPE_X = SHAPE_HELPER.west();
    public static final VoxelShape SHAPE_Y = SHAPE_HELPER.up();
    public static final VoxelShape SHAPE_Z = SHAPE_HELPER.north();

    public WindHoleBlock(Properties properties) {
        super(properties);
    }

    @Override
    public MapCodec<? extends WindHoleBlock> codec() {
        return CODEC;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState()
                   .setValue(AXIS, context.getNearestLookingDirection().getAxis())
                   .setValue(WATERLOGGED, context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return switch (pState.getValue(AXIS)) {
            case X -> SHAPE_X;
            case Y -> SHAPE_Y;
            case Z -> SHAPE_Z;
        };
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        return false;
    }

    @Override
    protected ItemInteractionResult useItemOn(
            ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult
    ) {
        return WrenchUtils.interact(AXIS, stack, state, level, pos, player);
    }

}
