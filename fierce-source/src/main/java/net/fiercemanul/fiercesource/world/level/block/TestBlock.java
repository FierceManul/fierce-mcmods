package net.fiercemanul.fiercesource.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.registries.FSBlockEntityTypes;
import net.fiercemanul.fiercesource.world.level.block.entity.TestBlockEntity;
import net.fiercemanul.fiercesource.world.level.menu.FierceMediaMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;


public class TestBlock extends ModelBlock implements EntityBlock {


    public static final MapCodec<TestBlock> CODEC = simpleCodec(TestBlock::new);


    public TestBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected MapCodec<? extends TestBlock> codec() {
        return CODEC;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SoulCrystalBlock.LARGE_SHAPE;
    }

    @Override
    protected boolean skipRendering(BlockState state, BlockState adjacentState, Direction direction) {
        return direction == Direction.UP && adjacentState.is(this);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new TestBlockEntity(pPos, pState);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHitResult) {
        if (pLevel.isClientSide) return InteractionResult.SUCCESS;
        if (pPlayer.isSpectator()) return InteractionResult.CONSUME;
        var provider = getMenuProvider(pState, pLevel, pPos);
        if (provider != null) pPlayer.openMenu(provider, provider::writeBuf);
        return InteractionResult.CONSUME;
    }

    @Nullable
    @Override
    protected FierceMediaMenu.Provider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        if (level instanceof ServerLevel serverLevel && level.getBlockEntity(pos) instanceof TestBlockEntity testBlockEntity)
            return new FierceMediaMenu.Provider(serverLevel, testBlockEntity.getServerApp());
        return null;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        if (level instanceof ServerLevel serverLevel && blockEntityType == FSBlockEntityTypes.TEST_BLOCK_ENTITY.get())
            return (level1, pos, state1, blockEntity) -> ((TestBlockEntity) blockEntity).serverTick(serverLevel, pos, state1);
        else return null;
    }
}
