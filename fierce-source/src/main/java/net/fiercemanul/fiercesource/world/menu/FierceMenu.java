package net.fiercemanul.fiercesource.world.menu;

import io.netty.buffer.ByteBuf;
import net.fiercemanul.fiercesource.FierceSource;
import net.fiercemanul.fiercesource.network.protocol.game.FierceMenuData;
import net.fiercemanul.fiercesource.world.level.block.entity.TestBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.PacketDistributor;

import javax.annotation.Nullable;

public class FierceMenu extends AbstractContainerMenu {


    @Nullable
    protected BlockEntity blockEntity;
    public int random;
    @Nullable
    protected ServerPlayer player;
    @Nullable
    private BlockPos blockPos;

    public record RandomData(int i) implements FierceMenuData.Data {

        public static final StreamCodec<ByteBuf, RandomData> STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.INT,
                RandomData::i,
                RandomData::new
        );

        @Override
        public void encode(ByteBuf pBuffer) {
            STREAM_CODEC.encode(pBuffer, this);
        }
    }

    public record ManaData(long l) implements FierceMenuData.Data {

        public static final StreamCodec<ByteBuf, ManaData> STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.VAR_LONG,
                ManaData::l,
                ManaData::new
        );

        @Override
        public void encode(ByteBuf pBuffer) {
            STREAM_CODEC.encode(pBuffer, this);
        }
    }


    public record FeData(int i) implements FierceMenuData.Data {

        public static final StreamCodec<ByteBuf, FeData> STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.INT,
                FeData::i,
                FeData::new
        );

        @Override
        public void encode(ByteBuf pBuffer) {
            STREAM_CODEC.encode(pBuffer, this);
        }
    }

    public FierceMenu(int pContainerId, Inventory playerInventory, RegistryFriendlyByteBuf extraData) {
        super(FierceSource.FIERCE_MENU.get(), pContainerId);
        blockEntity = null;
        if (extraData.readBoolean()) blockPos = extraData.readBlockPos();
    }

    public FierceMenu(int pContainerId, Inventory playerInventory, Level level, BlockPos blockPos, @Nullable BlockEntity blockEntity) {
        super(FierceSource.FIERCE_MENU.get(), pContainerId);
        player = (ServerPlayer) playerInventory.player;
        this.blockEntity = blockEntity;
        if (blockEntity instanceof TestBlockEntity testBlock) testBlock.setSynchronizer(this);
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return true;
    }

    @Override
    public void removed(Player pPlayer) {
        super.removed(pPlayer);
        if (blockEntity instanceof TestBlockEntity testBlock) testBlock.setSynchronizer(null);
    }

    public void handleRemoteData(byte menuAppIndex, int dataTypeIndex, ByteBuf data) {

        handleRemoteData(RandomData.STREAM_CODEC.decode(data));
    }

    public void handleRemoteData(RandomData randomData) {
        random = randomData.i;
    }

    public void sendRandom(int random) {
        if (player != null) PacketDistributor.sendToPlayer(player, new FierceMenuData(containerId, (byte) 0, 0, new RandomData(random)));
    }
}
