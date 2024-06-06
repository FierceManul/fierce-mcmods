package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercedecoration.world.inventory.CustomCraftingMenu;
import net.fiercemanul.fiercesource.world.item.WrenchAction;
import net.fiercemanul.fiercesource.world.level.block.ModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CraftingDeskBlock extends ModelBlock {



    public static final MapCodec<CraftingDeskBlock> CODEC = simpleCodec(CraftingDeskBlock::new);
    private static final Component CONTAINER_TITLE = Component.translatable("container.crafting");

    protected static final VoxelShape SHAPE = Shapes.or(
            Block.box(0.0, 11.0, 0.0, 16.0, 16.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 3.0, 11.0, 3.0),
            Block.box(13.0, 0, 0.0, 16.0, 11.0, 3.0),
            Block.box(0.0, 0.0, 13.0, 3.0, 11.0, 16.0),
            Block.box(13.0, 0, 13.0, 16.0, 11.0, 16.0)
    );


    public CraftingDeskBlock(Properties properties) {
        super(properties);
    }

    @Override
    public MapCodec<? extends CraftingDeskBlock> codec() {
        return CODEC;
    }

    @Override
    protected ItemInteractionResult useItemOn(
            ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHitResult
    ) {
        boolean success = WrenchAction.doWrenchDismantleAction(pStack, pState, pLevel, pPos, pPlayer);
        if (!success && !pLevel.isClientSide) {
            pPlayer.openMenu(pState.getMenuProvider(pLevel, pPos));
            pPlayer.awardStat(Stats.INTERACT_WITH_CRAFTING_TABLE);
            return ItemInteractionResult.CONSUME;
        }
        return ItemInteractionResult.SUCCESS;
    }

    @Override
    public MenuProvider getMenuProvider(BlockState pState, Level pLevel, BlockPos pPos) {
        return new SimpleMenuProvider(
                (pContainerId, pPlayerInventory, pPlayer) -> new CustomCraftingMenu(
                        pContainerId,
                        pPlayerInventory,
                        ContainerLevelAccess.create(pLevel, pPos),
                        this
                ),
                CONTAINER_TITLE
        );
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    protected boolean isPathfindable(BlockState pState, PathComputationType pPathComputationType) {
        return false;
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState pState) {
        return true;
    }
}
