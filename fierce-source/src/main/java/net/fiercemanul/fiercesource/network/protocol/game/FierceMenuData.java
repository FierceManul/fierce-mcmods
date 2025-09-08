package net.fiercemanul.fiercesource.network.protocol.game;

import net.fiercemanul.fiercesource.util.FSUtils;
import net.fiercemanul.fiercesource.world.level.app.DataType.AppData;
import net.fiercemanul.fiercesource.world.level.app.DataType.AppDataType;
import net.fiercemanul.fiercesource.world.level.menu.FierceMediaMenu;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.handling.IPayloadHandler;

public record FierceMenuData(int menuId, byte menuAppIndex, AppData data) implements CustomPacketPayload {


    public static final CustomPacketPayload.Type<FierceMenuData> TYPE = new CustomPacketPayload.Type<>(FSUtils.rl("fierce_menu_data"));

    public static final IPayloadHandler<FierceMenuData> HANDLER = FierceMenuData::handle;

    public static final StreamCodec<RegistryFriendlyByteBuf, FierceMenuData> STREAM_CODEC = new StreamCodec<>() {


        @Override
        public FierceMenuData decode(RegistryFriendlyByteBuf buffer) {
            return new FierceMenuData(buffer.readVarInt(), buffer.readByte(), AppDataType.decodeData(buffer));
        }

        @Override
        public void encode(RegistryFriendlyByteBuf buffer, FierceMenuData pack) {
            buffer.writeVarInt(pack.menuId);
            buffer.writeByte(pack.menuAppIndex);
            pack.data.encode(buffer);
        }

    };

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    private static void handle(FierceMenuData pack, IPayloadContext context) {
        Player player = context.player();
        AbstractContainerMenu menu = player.containerMenu;
        if (menu.containerId == pack.menuId() && !player.isSpectator() && menu instanceof FierceMediaMenu fierceMediaMenu && menu.stillValid(player))
            fierceMediaMenu.handleRemoteData(pack.menuAppIndex, pack.data);
    }

}
