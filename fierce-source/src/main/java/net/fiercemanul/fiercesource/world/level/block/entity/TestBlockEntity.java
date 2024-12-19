package net.fiercemanul.fiercesource.world.level.block.entity;

import net.fiercemanul.fiercesource.FierceSource;
import net.fiercemanul.fiercesource.world.inventory.FierceContainerMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;


public class TestBlockEntity extends BlockEntity {


    private static final RandomSource RANDOM = RandomSource.create();
    private long mana;
    private int fe;
    private int random1 = RANDOM.nextInt();
    @Nullable
    private FierceContainerMenu synchronizer;

    public TestBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(FierceSource.TEST_BLOCK_ENTITY.get(), pPos, pBlockState);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        mana = pTag.getLong("Mana");
        fe = pTag.getInt("Fe");
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.saveAdditional(pTag, pRegistries);
        pTag.putLong("Mana", mana);
        pTag.putInt("Fe", fe);
    }

    public void serverTick(Level pLevel, BlockPos pPos, BlockState pState) {
        if (pLevel.getServer() != null && pLevel.getServer().getTickCount() % 20 == 0) {
            random1 = RANDOM.nextInt(Integer.MAX_VALUE);
            if (synchronizer != null) synchronizer.sendRandom(random1);
        }
    }

    public void setSynchronizer(@Nullable FierceContainerMenu synchronizer) {
        this.synchronizer = synchronizer;
    }
}
