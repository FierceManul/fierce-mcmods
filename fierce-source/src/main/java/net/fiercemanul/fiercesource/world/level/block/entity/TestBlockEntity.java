package net.fiercemanul.fiercesource.world.level.block.entity;

import net.fiercemanul.fiercesource.data.registries.FSBlockEntityTypes;
import net.fiercemanul.fiercesource.server.level.app.ServerApp;
import net.fiercemanul.fiercesource.server.level.app.ServerAppHolder;
import net.fiercemanul.fiercesource.server.level.app.TestServerApp;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;


public class TestBlockEntity extends BlockEntity implements ServerAppHolder {


    private long mana;
    private int fe;
    @Nullable
    private TestServerApp serverApp;

    public TestBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(FSBlockEntityTypes.TEST_BLOCK_ENTITY.get(), pPos, pBlockState);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        mana = tag.getLong("Mana");
        fe = tag.getInt("Fe");
        if (serverApp != null) serverApp.random = tag.getInt("Random");
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putLong("Mana", mana);
        tag.putInt("Fe", fe);
        if (serverApp != null) tag.putInt("Random", serverApp.random);
    }

    public void serverTick(ServerLevel level, BlockPos pos, BlockState state) {
        if (serverApp == null) serverApp = new TestServerApp();
        serverApp.serverTick(level);
    }

    @Override
    public ServerApp[] getServerApp() {
        return new ServerApp[]{serverApp};
    }

}
