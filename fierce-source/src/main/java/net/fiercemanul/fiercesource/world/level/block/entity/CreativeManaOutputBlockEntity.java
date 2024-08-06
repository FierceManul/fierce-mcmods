package net.fiercemanul.fiercesource.world.level.block.entity;

import net.fiercemanul.fiercesource.FierceSource;
import net.fiercemanul.fiercesource.capabilities.FSCapabilities;
import net.fiercemanul.fiercesource.capabilities.IManaHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.capabilities.BlockCapabilityCache;
import org.jetbrains.annotations.Nullable;


public class CreativeManaOutputBlockEntity extends BlockEntity {


    @Nullable
    private BlockCapabilityCache<IManaHandler, @Nullable Direction> cache;

    public CreativeManaOutputBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(FierceSource.CREATIVE_MANA_OUTPUT_BLOCK_ENTITY.get(), pPos, pBlockState);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        newCache();
    }

    private void newCache() {
        if (level instanceof ServerLevel serverLevel) {
            Direction direction = getBlockState().getValue(BlockStateProperties.FACING);
            cache = BlockCapabilityCache.create(
                    FSCapabilities.BLOCK_MANA_CAP,
                    serverLevel,
                    getBlockPos().relative(direction),
                    direction.getOpposite(),
                    () -> !isRemoved(),
                    () -> {}
            );
        }
    }

    public void serverTick() {
        if (cache == null) return;
        IManaHandler manaStorage = cache.getCapability();
        if (manaStorage != null) manaStorage.receiveMana(Long.MAX_VALUE, false);
    }

}
