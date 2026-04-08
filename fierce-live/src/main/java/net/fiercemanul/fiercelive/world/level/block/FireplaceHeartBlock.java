package net.fiercemanul.fiercelive.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.world.level.block.WaterloggedBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import org.jetbrains.annotations.Nullable;

public class FireplaceHeartBlock extends WaterloggedBlock {


    public static final MapCodec<? extends FireplaceHeartBlock> CODEC = simpleCodec(FireplaceHeartBlock::new);
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final VoxelShape SHAPE = box(0.0, 0.0, 0.0, 16.0, 9.0, 16.0);
    public static final VoxelShape COLLISION_SHAPE = box(0.0, 0.0, 0.0, 16.0, 2.0, 16.0);

    public FireplaceHeartBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(stateDefinition.any().setValue(WATERLOGGED, false).setValue(LIT, true));
    }

    @Override
    protected MapCodec<? extends FireplaceHeartBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT, WATERLOGGED);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return COLLISION_SHAPE;
    }

    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility itemAbility, boolean simulate) {
        if (itemAbility == ItemAbilities.SHOVEL_DOUSE && state.getValue(LIT)) {
            if (!simulate) {
                Level level = context.getLevel();
                BlockPos pos = context.getClickedPos();
                if (level.isClientSide()) for (int i = 0; i < 20; i++)
                    CampfireBlock.makeParticles(level, pos, false, true);
                level.gameEvent(context.getPlayer(), GameEvent.BLOCK_CHANGE, pos);
            }
            return state.setValue(LIT, false);
        }
        else if (itemAbility == ItemAbilities.FIRESTARTER_LIGHT && !state.getValue(LIT)) {
            if (!simulate) context.getLevel().gameEvent(context.getPlayer(), GameEvent.BLOCK_CHANGE, context.getClickedPos());
            return state.setValue(LIT, true);
        }
        return null;
    }

    @Override
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if (pState.getValue(LIT) && pEntity instanceof LivingEntity)
            pEntity.hurt(pLevel.damageSources().campfire(), 1.0F);
        super.entityInside(pState, pLevel, pPos, pEntity);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        boolean water = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
        return this.defaultBlockState().setValue(WATERLOGGED, water).setValue(LIT, !water);
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pState.getValue(LIT)) {
            if (pRandom.nextInt(10) == 0) {
                pLevel.playLocalSound(
                        (double)pPos.getX() + 0.5,
                        (double)pPos.getY() + 0.5,
                        (double)pPos.getZ() + 0.5,
                        SoundEvents.CAMPFIRE_CRACKLE,
                        SoundSource.BLOCKS,
                        0.5F + pRandom.nextFloat(),
                        pRandom.nextFloat() * 0.7F + 0.6F,
                        false
                );
            }
        }
    }

    @Override
    public boolean placeLiquid(LevelAccessor pLevel, BlockPos pPos, BlockState pState, FluidState pFluidState) {
        boolean flag = super.placeLiquid(pLevel, pPos, pState, pFluidState);
        if(flag) {
            pLevel.setBlock(pPos, pLevel.getBlockState(pPos).setValue(LIT, false), 0);
            if (pState.getValue(LIT)) {
                if (pLevel.isClientSide()) for (int i = 0; i < 20; ++i) CampfireBlock.makeParticles((Level) pLevel, pPos, false, true);
                else pLevel.playSound(null, pPos, SoundEvents.GENERIC_EXTINGUISH_FIRE, SoundSource.BLOCKS, 1.0F, 1.0F);

                pLevel.gameEvent(null, GameEvent.BLOCK_CHANGE, pPos);
            }
        }
        return flag;
    }

    @Override
    public void onProjectileHit(Level pLevel, BlockState pState, BlockHitResult pHit, Projectile pProjectile) {
        BlockPos blockpos = pHit.getBlockPos();
        if (!pLevel.isClientSide
                && pProjectile.isOnFire()
                && pProjectile.mayInteract(pLevel, blockpos)
                && !pState.getValue(LIT)
                && !pState.getValue(WATERLOGGED)) {
            pLevel.setBlock(blockpos, pState.setValue(BlockStateProperties.LIT, true), 11);
        }
    }

    @Override
    protected boolean isPathfindable(BlockState pState, PathComputationType pPathComputationType) {
        return false;
    }

    @Override
    protected boolean propagatesSkylightDown(BlockState state, BlockGetter level, BlockPos pos) {
        return false;
    }
}
