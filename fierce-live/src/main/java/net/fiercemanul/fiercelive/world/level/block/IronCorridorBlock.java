package net.fiercemanul.fiercelive.world.level.block;

import net.fiercemanul.fiercelive.data.tags.FLBlockTags;
import net.fiercemanul.fiercesource.world.item.WrenchUtils;
import net.fiercemanul.fiercesource.world.level.block.WaterloggedBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.WaterloggedTransparentBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class IronCorridorBlock extends IronFrameBlock {


    protected static final VoxelShape COLLISION_SHAPE = box(0, 15, 0, 16, 16, 16);

    public IronCorridorBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return COLLISION_SHAPE;
    }

    @Override
    protected boolean skipRendering(BlockState state, BlockState adjacentState, Direction direction) {
        return isCorridorSupport(adjacentState, direction);
    }

    public static boolean isCorridorSupport(BlockState adjacentState, Direction direction) {
        return direction == Direction.DOWN && (adjacentState.is(FLBlockTags.FRAMES)
                || (adjacentState.getBlock() instanceof IronCorridorSlabBlock
                && adjacentState.getValue(BlockStateProperties.SLAB_TYPE) != SlabType.BOTTOM));
    }

}
