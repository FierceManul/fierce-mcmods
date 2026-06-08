package net.fiercemanul.fiercelive.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class InfiniteRailCarpetBlock extends DuperBlock {


    public static final MapCodec<InfiniteRailCarpetBlock> CODEC = simpleCodec(InfiniteRailCarpetBlock::new);

    public InfiniteRailCarpetBlock(Properties properties) {
        super(properties);
    }

    @Override
    public MapCodec<InfiniteRailCarpetBlock> codec() {
        return CODEC;
    }

    @Override
    protected void doDupe(BlockState state, Level level, BlockPos pos) {
        if (level.isClientSide) return;
        BlockPos abovePos = pos.above();
        BlockState upState = level.getBlockState(abovePos);
        if (upState.isAir() || !upState.is(BlockTags.RAILS) && !upState.is(BlockTags.WOOL_CARPETS)) return;
        Item item = upState.getBlock().asItem();
        if (item == Items.AIR) return;
        Vec3 itemPos = abovePos.getBottomCenter();
        ItemEntity itemEntity = new ItemEntity(level, itemPos.x, itemPos.y + 0.0625, itemPos.z, item.getDefaultInstance());
        level.addFreshEntity(itemEntity);
    }

}
