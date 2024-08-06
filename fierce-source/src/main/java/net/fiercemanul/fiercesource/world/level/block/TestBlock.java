package net.fiercemanul.fiercesource.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.FierceSource;
import net.fiercemanul.fiercesource.util.Utils;
import net.fiercemanul.fiercesource.world.level.block.entity.TestBlockEntity;
import net.fiercemanul.fiercesource.world.menu.FierceMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class TestBlock extends ModelBlock implements EntityBlock {


    public static final MapCodec<TestBlock> CODEC = simpleCodec(TestBlock::new);
    public static final VoxelShape SHAPE = Shapes.or(
            box(6.0, 2.0, 6.0, 10.0, 14.0, 10.0),
            BlockUtils.CRYSTAL_BASE
    );

    public TestBlock(Properties pProperties) {
        super(pProperties.strength(1.5F, 6.0F).lightLevel(value -> 15).emissiveRendering(Utils::getTrue).sound(SoundType.LARGE_AMETHYST_BUD).mapColor(MapColor.COLOR_BLACK));
    }

    @Override
    protected MapCodec<? extends TestBlock> codec() {
        return CODEC;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new TestBlockEntity(pPos, pState);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHitResult) {
        if (pLevel.isClientSide) return InteractionResult.SUCCESS;
        MenuProvider provider = getMenuProvider(pState, pLevel, pPos);
        if (provider != null) pPlayer.openMenu(provider, byteBuf -> {
            byteBuf.writeBoolean(true);
            byteBuf.writeBlockPos(pPos);
        });
        return InteractionResult.CONSUME;
    }

    @Override
    protected MenuProvider getMenuProvider(BlockState pState, Level pLevel, BlockPos pPos) {
        return new SimpleMenuProvider(
                (pContainerId, pPlayerInventory, pPlayer) -> new FierceMenu(
                        pContainerId,
                        pPlayerInventory,
                        pLevel,
                        pPos,
                        pLevel.getBlockEntity(pPos)
                ),
                Component.literal("aawwdd")
        );
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if (!pLevel.isClientSide && pBlockEntityType == FierceSource.TEST_BLOCK_ENTITY.get())
            return (pLevel1, pPos, pState1, pBlockEntity) -> ((TestBlockEntity) pBlockEntity).serverTick(pLevel1, pPos, pState1);
        else return null;
    }
}
