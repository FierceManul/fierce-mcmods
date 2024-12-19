package net.fiercemanul.fiercesource.network.protocol.game;

import io.netty.buffer.ByteBuf;
import net.fiercemanul.fiercesource.FierceSource;
import net.fiercemanul.fiercesource.world.inventory.FierceContainerMenu;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.handling.IPayloadHandler;

import javax.annotation.Nullable;

public record FierceMenuData(int menuId, byte menuAppIndex, int dataTypeIndex, Data data) implements CustomPacketPayload {


    public static final CustomPacketPayload.Type<FierceMenuData> TYPE = new CustomPacketPayload.Type<>(
            ResourceLocation.fromNamespaceAndPath(FierceSource.MODID, "fierce_menu_data"));

    public static final IPayloadHandler<FierceMenuData> HANDLER = FierceMenuData::handle;

    public static final StreamCodec<ByteBuf, FierceMenuData> STREAM_CODEC = new StreamCodec<>() {
        @Override
        public FierceMenuData decode(ByteBuf pBuffer) {
            int i = pBuffer.readInt();
            byte b = pBuffer.readByte();
            int j = pBuffer.readInt();
            return new FierceMenuData(i, b, j, new BufData(pBuffer.readBytes(pBuffer.readableBytes())));
        }

        @Override
        public void encode(ByteBuf pBuffer, FierceMenuData pack) {
            pBuffer.writeInt(pack.menuId);
            pBuffer.writeByte(pack.menuAppIndex);
            pBuffer.writeInt(pack.dataTypeIndex);
            pack.data.encode(pBuffer);
        }
    };

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    private static void handle(FierceMenuData pack, IPayloadContext context) {
        ByteBuf buf = pack.data.get();
        if (buf == null) return;
        Player player = context.player();
        AbstractContainerMenu menu = player.containerMenu;
        if (menu.containerId == pack.menuId() && !player.isSpectator() && menu.stillValid(player) && menu instanceof FierceContainerMenu fierceContainerMenu)
            fierceContainerMenu.handleRemoteData(pack.menuAppIndex, pack.dataTypeIndex, buf);
    }

    public interface Data {

        @Nullable
        default ByteBuf get() {
            return null;
        }

        void encode(ByteBuf pBuffer);
    }

    private record BufData(@Nullable ByteBuf byteBuf) implements Data {

        @Nullable
        @Override
        public ByteBuf get() {
            return byteBuf;
        }

        @Override
        public void encode(ByteBuf pBuffer) {}
    }
}
