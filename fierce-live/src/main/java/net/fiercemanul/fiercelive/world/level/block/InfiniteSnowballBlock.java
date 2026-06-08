package net.fiercemanul.fiercelive.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class InfiniteSnowballBlock extends DuperBlock {


    public static final MapCodec<InfiniteSnowballBlock> CODEC = simpleCodec(InfiniteSnowballBlock::new);

    public InfiniteSnowballBlock(Properties properties) {
        super(properties);
    }

    @Override
    public MapCodec<InfiniteSnowballBlock> codec() {
        return CODEC;
    }

    @Override
    protected void doDupe(BlockState state, Level level, BlockPos pos) {
        if (level.isClientSide) return;
        Vec3 itemPos = pos.above().getBottomCenter();
        ItemStack itemStack = Items.SNOWBALL.getDefaultInstance();
        itemStack.setCount(4);
        ItemEntity itemEntity = new ItemEntity(level, itemPos.x, itemPos.y + 0.0625, itemPos.z, itemStack);
        level.addFreshEntity(itemEntity);
    }

}
