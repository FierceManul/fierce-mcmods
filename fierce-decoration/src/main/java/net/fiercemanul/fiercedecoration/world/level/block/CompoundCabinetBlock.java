package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercedecoration.FierceDecoration;
import net.fiercemanul.fiercedecoration.world.level.block.entity.CabinetBlockEntity;
import net.fiercemanul.fiercesource.util.FSUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.CompoundContainer;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.Optional;

public abstract class CompoundCabinetBlock extends CabinetBlock {


    private static final DoubleBlockCombiner.Combiner<CabinetBlockEntity, Optional<Container>> CABINET_COMBINER = new DoubleBlockCombiner.Combiner<>() {
        public Optional<Container> acceptDouble(CabinetBlockEntity pFirst, CabinetBlockEntity pSecond) {
            return Optional.of(new CompoundContainer(pFirst, pSecond));
        }

        public Optional<Container> acceptSingle(CabinetBlockEntity pSingle) {
            return Optional.of(pSingle);
        }

        public Optional<Container> acceptNone() {
            return Optional.empty();
        }
    };
    private static final DoubleBlockCombiner.Combiner<CabinetBlockEntity, Optional<MenuProvider>> MENU_PROVIDER_COMBINER = new DoubleBlockCombiner.Combiner<>() {
        public Optional<MenuProvider> acceptDouble(CabinetBlockEntity pFirst, CabinetBlockEntity pSecond) {
            Container container = new CompoundContainer(pFirst, pSecond);
            return Optional.of(new MenuProvider() {
                @Nullable
                @Override
                public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
                    if (pFirst.canOpen(pPlayer) && pSecond.canOpen(pPlayer)) {
                        pFirst.unpackLootTable(pPlayerInventory.player);
                        pSecond.unpackLootTable(pPlayerInventory.player);
                        return ChestMenu.sixRows(pContainerId, pPlayerInventory, container);
                    }
                    else return null;
                }

                @Override
                public Component getDisplayName() {
                    if (pFirst.hasCustomName()) return pFirst.getDisplayName();
                    else return pSecond.hasCustomName() ? pSecond.getDisplayName() : Component.translatable("fiercedecoration.container.cabinet");
                }
            });
        }

        public Optional<MenuProvider> acceptSingle(CabinetBlockEntity pSingle) {
            return Optional.of(pSingle);
        }

        public Optional<MenuProvider> acceptNone() {
            return Optional.empty();
        }
    };

    public CompoundCabinetBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected abstract MapCodec<? extends CompoundCabinetBlock> codec();

    @Nullable
    @Override
    protected MenuProvider getMenuProvider(BlockState pState, Level pLevel, BlockPos pPos) {
        return this.combine(pState, pLevel, pPos).apply(MENU_PROVIDER_COMBINER).orElse(null);
    }

    @Nullable
    public Container getContainer(BlockState pState, Level pLevel, BlockPos pPos) {
        return combine(pState, pLevel, pPos).apply(CompoundCabinetBlock.CABINET_COMBINER).orElse(null);
    }

    protected abstract DoubleBlockCombiner.BlockType getCombinerType(BlockState pState);

    protected abstract Direction getConnectedDirection(BlockState pState);

    protected DoubleBlockCombiner.NeighborCombineResult<? extends CabinetBlockEntity> combine(
            BlockState pState, Level pLevel, BlockPos pPos
    ) {
        return DoubleBlockCombiner.combineWithNeigbour(
                FierceDecoration.CABINET_BLOCK_ENTITY.get(),
                this::getCombinerType,
                this::getConnectedDirection,
                FACING,
                pState,
                pLevel,
                pPos,
                FSUtils::getFalse
        );
    }

    @Override
    public abstract boolean shouldInvalidateCapabilities(BlockState oldState, BlockState newState);
}
