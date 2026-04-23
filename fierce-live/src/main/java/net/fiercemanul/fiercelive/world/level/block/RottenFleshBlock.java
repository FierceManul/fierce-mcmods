package net.fiercemanul.fiercelive.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class RottenFleshBlock extends Block {


    public static final MapCodec<RottenFleshBlock> CODEC = simpleCodec(RottenFleshBlock::new);

    public RottenFleshBlock(Properties properties) {
        super(properties);
    }

    @Override
    public MapCodec<RottenFleshBlock> codec() {
        return CODEC;
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if (entity.isSuppressingBounce()) super.fallOn(level, state, pos, entity, fallDistance);
        else entity.causeFallDamage(fallDistance, 0.5F, level.damageSources().fall());
    }

}
