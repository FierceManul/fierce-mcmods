package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercedecoration.world.inventory.CustomCraftingMenu;
import net.fiercemanul.fiercesource.world.item.WrenchAction;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;


public class CraftingBlock extends CraftingTableBlock {



    public static final MapCodec<CraftingBlock> CODEC = simpleCodec(CraftingBlock::new);
    private static final Component CONTAINER_TITLE = Component.translatable("container.crafting");


    public CraftingBlock(Properties properties) {
        super(properties);
    }

    @Override
    public MapCodec<? extends CraftingBlock> codec() {
        return CODEC;
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        boolean success = WrenchAction.doWrenchDismantleAction(pState, pLevel, pPos, pPlayer, pHand);
        if (!success && !pLevel.isClientSide) {
            pPlayer.openMenu(pState.getMenuProvider(pLevel, pPos));
            pPlayer.awardStat(Stats.INTERACT_WITH_CRAFTING_TABLE);
            return InteractionResult.CONSUME;
        }
        return InteractionResult.SUCCESS;
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
}
