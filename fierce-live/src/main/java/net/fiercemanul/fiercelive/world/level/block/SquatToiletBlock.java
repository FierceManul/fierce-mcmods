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

public class SquatToiletBlock extends HorizonFacingWaterloggedBlock {


    public static final MapCodec<SquatToiletBlock> CODEC = simpleCodec(SquatToiletBlock::new);
    protected static final VoxelShapeHelper SHAPE = new VoxelShapeHelper()
            .applyCube(0, 0, 0, 16, 0.01, 16)
            .applyCube(7, 0, 14, 9, 7, 16)
            .applyCube(2, 7, 13, 14, 16, 16);
    protected static final VoxelShape SHAPE_NORTH = SHAPE.north();
    protected static final VoxelShape SHAPE_SOUTH = SHAPE.south();
    protected static final VoxelShape SHAPE_WEST = SHAPE.west();
    protected static final VoxelShape SHAPE_EAST = SHAPE.east();
    protected static final VoxelShapeHelper COLLISION_SHAPE = new VoxelShapeHelper()
            .applyCube(0, -1, 0, 16, 0, 16)
            .applyCube(7, 0, 14, 9, 7, 16)
            .applyCube(2, 7, 13, 14, 16, 16);
    protected static final VoxelShape COLLISION_SHAPE_NORTH = COLLISION_SHAPE.north();
    protected static final VoxelShape COLLISION_SHAPE_SOUTH = COLLISION_SHAPE.south();
    protected static final VoxelShape COLLISION_SHAPE_WEST = COLLISION_SHAPE.west();
    protected static final VoxelShape COLLISION_SHAPE_EAST = COLLISION_SHAPE.east();

    public SquatToiletBlock(Properties properties) {
        super(properties);
    }

    @Override
    public MapCodec<? extends SquatToiletBlock> codec() {
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
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case SOUTH -> COLLISION_SHAPE_SOUTH;
            case WEST -> COLLISION_SHAPE_WEST;
            case EAST -> COLLISION_SHAPE_EAST;
            default -> COLLISION_SHAPE_NORTH;
        };
    }

    @Override
    protected ItemInteractionResult useItemOn(
            ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult
    ) {
        return WrenchUtils.interactRotate(stack, state, level, pos, player);
    }

    @Override
    protected boolean propagatesSkylightDown(BlockState state, BlockGetter level, BlockPos pos) {
        return false;
    }

    @Override
    protected boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }
}