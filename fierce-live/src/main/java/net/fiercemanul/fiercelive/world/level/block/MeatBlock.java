package net.fiercemanul.fiercelive.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class MeatBlock extends Block {


    public static final MapCodec<MeatBlock> CODEC = simpleCodec(MeatBlock::new);

    public MeatBlock(Properties properties) {
        super(properties);
    }

    @Override
    public MapCodec<MeatBlock> codec() {
        return CODEC;
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if (entity.isSuppressingBounce()) super.fallOn(level, state, pos, entity, fallDistance);
        else entity.causeFallDamage(fallDistance, 0.0F, level.damageSources().fall());
    }

    @Override
    public void updateEntityAfterFallOn(BlockGetter level, Entity entity) {
        if (entity.isSuppressingBounce()) super.updateEntityAfterFallOn(level, entity);
        else {
            Vec3 vec3 = entity.getDeltaMovement();
            if (vec3.y < 0.0) {
                double d0 = entity instanceof LivingEntity ? 0.5 : 0.4;
                entity.setDeltaMovement(vec3.x, -vec3.y * d0, vec3.z);
            }
        }
    }

}
