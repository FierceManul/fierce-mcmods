package net.fiercemanul.fiercesource.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.data.FSBlockEntityTypes;
import net.fiercemanul.fiercesource.world.level.block.entity.CreativeManaOutputBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class CreativeManaProviderBlock extends WrenchDismantleBlock implements EntityBlock {


    public static final MapCodec<CreativeManaProviderBlock> CODEC = simpleCodec(CreativeManaProviderBlock::new);

    public CreativeManaProviderBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends CreativeManaProviderBlock> codec() {
        return CODEC;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CreativeManaOutputBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        if (!level.isClientSide && blockEntityType == FSBlockEntityTypes.CREATIVE_MANA_OUTPUT_BLOCK_ENTITY.get())
            return (l, p, s, t) -> ((CreativeManaOutputBlockEntity) t).serverTick();
        else return null;
    }

}
