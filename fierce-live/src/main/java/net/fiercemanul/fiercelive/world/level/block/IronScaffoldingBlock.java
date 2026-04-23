package net.fiercemanul.fiercelive.world.level.block;

import net.fiercemanul.fiercesource.world.item.WrenchUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ScaffoldingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;

public class IronScaffoldingBlock extends ScaffoldingBlock {


    public IronScaffoldingBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isScaffolding(BlockState state, LevelReader level, BlockPos pos, LivingEntity entity) {
        return true;
    }

    @Override
    protected boolean propagatesSkylightDown(BlockState state, BlockGetter level, BlockPos pos) {
        return false;
    }

    @Override
    protected boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }

    @Override
    protected boolean skipRendering(BlockState state, BlockState adjacentState, Direction direction) {
        if (direction == Direction.UP) return false;
        boolean myBottom = state.getValue(BOTTOM);
        if (adjacentState.getBlock() instanceof IronScaffoldingBlock)
            return direction == Direction.DOWN || !myBottom || adjacentState.getValue(BOTTOM);
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
