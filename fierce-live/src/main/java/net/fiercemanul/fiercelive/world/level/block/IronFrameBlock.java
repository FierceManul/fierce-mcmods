package net.fiercemanul.fiercelive.world.level.block;

import net.fiercemanul.fiercelive.data.tags.FLBlockTags;
import net.fiercemanul.fiercelive.world.level.block.state.properties.FLBlockStateProperties;
import net.fiercemanul.fiercesource.world.item.WrenchUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.WaterloggedTransparentBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class IronFrameBlock extends WaterloggedTransparentBlock {


    public IronFrameBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = context.getLevel().getBlockState(context.getClickedPos());
        if (state.getBlock() instanceof IronLadderBlock) return state.setValue(FLBlockStateProperties.FRAMED, true);
        return Objects.requireNonNull(super.getStateForPlacement(context));
    }

    @Override
    protected VoxelShape getBlockSupportShape(BlockState state, BlockGetter level, BlockPos pos) {
        return Shapes.block();
    }

    //TODO:检查原版更新(同类方块铜栅格会直接通光)
    @Override
    protected boolean propagatesSkylightDown(BlockState state, BlockGetter level, BlockPos pos) {
        return state.getFluidState().isEmpty();
    }

    @Override
    protected boolean skipRendering(BlockState state, BlockState adjacentState, Direction side) {
        if (side.getAxis().isVertical()) {
            if (adjacentState.is(FLBlockTags.FRAMES)) return true;
            if (adjacentState.getBlock() instanceof IronCorridorSlabBlock)
                return (adjacentState.getValue(BlockStateProperties.SLAB_TYPE) != SlabType.BOTTOM && side == Direction.DOWN)
                    || (adjacentState.getValue(BlockStateProperties.SLAB_TYPE) != SlabType.TOP && side == Direction.UP);
            return false;
        }
        return false;
    }

    @Override
    protected ItemInteractionResult useItemOn(
            ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand,
            BlockHitResult hitResult
    ) {
        return WrenchUtils.interact(stack, state, level, pos, player);
    }
}
