package net.fiercemanul.fiercelive.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.util.VoxelShapeHelper;
import net.fiercemanul.fiercesource.world.item.WrenchUtils;
import net.fiercemanul.fiercesource.world.level.block.HorizonFacingWaterloggedBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SinkBlock extends HorizonFacingWaterloggedBlock {


    public static final MapCodec<SinkBlock> CODEC = simpleCodec(SinkBlock::new);
    protected static final VoxelShapeHelper SHAPE = new VoxelShapeHelper()
            .applyCube(5, 8, 11, 11, 13, 16)
            .applyCube(0, 13, 2, 16, 16, 16);
    protected static final VoxelShape SHAPE_NORTH = SHAPE.north();
    protected static final VoxelShape SHAPE_SOUTH = SHAPE.south();
    protected static final VoxelShape SHAPE_WEST = SHAPE.west();
    protected static final VoxelShape SHAPE_EAST = SHAPE.east();

    public SinkBlock(Properties properties) {
        super(properties);
    }

    @Override
    public MapCodec<? extends SinkBlock> codec() {
        return CODEC;
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
        return WrenchUtils.interactRotate(stack, state, level, pos, player);
    }

}