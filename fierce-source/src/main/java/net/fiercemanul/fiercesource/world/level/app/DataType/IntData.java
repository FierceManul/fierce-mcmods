package net.fiercemanul.fiercesource.world.level.app.DataType;

import io.netty.buffer.ByteBuf;
import net.fiercemanul.fiercesource.registries.AppDataTypes;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record IntData(int index, int i) implements AppData {


    public static final StreamCodec<ByteBuf, IntData> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT,
            IntData::index,
            ByteBufCodecs.VAR_INT,
            IntData::i,
            IntData::new
    );

    @Override
    public void encode(RegistryFriendlyByteBuf buf) {
        AppDataTypes.INT.get().encodeData(buf, this);
    }

}
