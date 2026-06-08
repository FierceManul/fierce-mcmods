package net.fiercemanul.fiercelive.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class InfiniteTntBlock extends DuperBlock {


    public static final MapCodec<InfiniteTntBlock> CODEC = simpleCodec(InfiniteTntBlock::new);

    public InfiniteTntBlock(Properties properties) {
        super(properties);
    }

    @Override
    public MapCodec<InfiniteTntBlock> codec() {
        return CODEC;
    }

    @Override
    protected void doDupe(BlockState state, Level level, BlockPos pos) {
        onCaughtFire(state, level, pos, null, null);
    }

    @Override
    public void onCaughtFire(BlockState state, Level world, BlockPos pos, @Nullable net.minecraft.core.Direction face, @Nullable LivingEntity igniter) {
        Blocks.TNT.onCaughtFire(state, world, pos, face, igniter);
    }

    @Override
    public void wasExploded(Level level, BlockPos pos, Explosion explosion) {
        if (!level.isClientSide) {
            PrimedTnt primedtnt = new PrimedTnt(level, (double)pos.getX() + 0.5, (double)pos.getY(), (double)pos.getZ() + 0.5, explosion.getIndirectSourceEntity());
            int i = primedtnt.getFuse();
            primedtnt.setFuse((short)(level.random.nextInt(i / 4) + i / 8));
            level.addFreshEntity(primedtnt);
        }
    }

    @Override
    protected void onProjectileHit(Level level, BlockState state, BlockHitResult hit, Projectile projectile) {
        if (!level.isClientSide) {
            BlockPos blockpos = hit.getBlockPos();
            Entity entity = projectile.getOwner();
            if (projectile.isOnFire() && projectile.mayInteract(level, blockpos)) {
                onCaughtFire(state, level, blockpos, null, entity instanceof LivingEntity ? (LivingEntity)entity : null);
                level.removeBlock(blockpos, false);
            }
        }
    }

    @Override
    public boolean dropFromExplosion(Explosion explosion) {
        return false;
    }

}
