package net.fiercemanul.fiercelive.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.util.VoxelShapeHelper;
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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ShowerSetBlock extends HorizonFacingWaterloggedBlock {


    public static final MapCodec<ShowerSetBlock> CODEC = simpleCodec(ShowerSetBlock::new);
    protected static final VoxelShapeHelper SHAPE = new VoxelShapeHelper()
            .applyCube(5.5, 0, 13, 9.5, 15, 16)
            .applyCube(6, 15, 8, 10, 16, 16);
    protected static final VoxelShape SHAPE_NORTH = SHAPE.north();
    protected static final VoxelShape SHAPE_SOUTH = SHAPE.south();
    protected static final VoxelShape SHAPE_WEST = SHAPE.west();
    protected static final VoxelShape SHAPE_EAST = SHAPE.east();

    public ShowerSetBlock(Properties properties) {
        super(properties);
    }

    @Override
    public MapCodec<? extends ShowerSetBlock> codec() {
        return CODEC;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction clickedFace = context.getClickedFace();
        return this.defaultBlockState()
                   .setValue(FACING, clickedFace.getAxis().isVertical() ? context.getHorizontalDirection().getOpposite() : clickedFace)
                   .setValue(WATERLOGGED, context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case SOUTH -> SHAPE_SOUTH;
            case WEST -> SHAPE_WEST;
            case EAST -> SHAPE_EAST;
            default -> SHAPE_NORTH;
        };
    }

    @Override
    protected ItemInteractionResult useItemOn(
            ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult
    ) {
        return WrenchUtils.interact(stack, state, level, pos, player);
    }

}