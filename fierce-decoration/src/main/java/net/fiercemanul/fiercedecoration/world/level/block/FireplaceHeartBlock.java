package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.world.item.WrenchAction;
import net.fiercemanul.fiercesource.world.level.block.BlockUtils;
import net.fiercemanul.fiercesource.world.level.block.ModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.SoundType;
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

public class FireplaceHeartBlock extends ModelBlock {


    public static final MapCodec<? extends FireplaceHeartBlock> CODEC = simpleCodec(FireplaceHeartBlock::new);
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final VoxelShape SHAPE = box(0.0, 0.0, 0.0, 16.0, 9.0, 16.0);
    public static final VoxelShape COLLISION_SHAPE = box(0.0, 0.0, 0.0, 16.0, 2.0, 16.0);

    public FireplaceHeartBlock(Properties pProperties) {
        super(pProperties
                      .strength(5.0F, 6.0F)
                      .requiresCorrectToolForDrops()
                      .lightLevel(BlockUtils.litBlockEmission(15))
                      .noOcclusion()
                      .sound(SoundType.METAL)
        );
        this.registerDefaultState(stateDefinition.any().setValue(WATERLOGGED, false).setValue(LIT, true));
    }

    @Override
    protected MapCodec<? extends FireplaceHeartBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(LIT, WATERLOGGED);
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
    protected ItemInteractionResult useItemOn(
            ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHitResult
    ) {
        boolean success = WrenchAction.doWrenchDismantleAction(pStack, pState, pLevel, pPos, pPlayer);
        if (success) return ItemInteractionResult.sidedSuccess(pLevel.isClientSide);
        else if (pStack.getItem().canPerformAction(pStack, ItemAbilities.SHOVEL_FLATTEN)) {
            pLevel.setBlock(pPos, pState.setValue(LIT, false), 11);
            return ItemInteractionResult.sidedSuccess(pLevel.isClientSide);
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if (pState.getValue(LIT) && pEntity instanceof LivingEntity)
            pEntity.hurt(pLevel.damageSources().campfire(), 1.0F);
        super.entityInside(pState, pLevel, pPos, pEntity);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        boolean water = pContext.getLevel().getFluidState(pContext.getClickedPos()).getType() == Fluids.WATER;
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
}
