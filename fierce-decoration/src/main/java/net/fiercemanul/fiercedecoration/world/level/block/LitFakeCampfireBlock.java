package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.world.level.block.HorizonAxisModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.Nullable;


public class LitFakeCampfireBlock extends FakeCampfireBlock {


    public static final MapCodec<LitFakeCampfireBlock> CODEC = simpleCodec(LitFakeCampfireBlock::new);

    public static final BooleanProperty SIGNAL_FIRE = BlockStateProperties.SIGNAL_FIRE;
    private final boolean spawnParticles;
    private final int fireDamage;

    public LitFakeCampfireBlock(Properties pProperties) { this(pProperties, false, 2); }

    public LitFakeCampfireBlock(Properties pProperties, Boolean particles, int fireDamage) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any()
                                                      .setValue(SIGNAL_FIRE, false)
                                                      .setValue(WATERLOGGED, false)
                                                      .setValue(AXIS, Direction.Axis.X));
        this.spawnParticles = particles;
        this.fireDamage = fireDamage;
    }

    @Override
    protected MapCodec<? extends HorizonAxisModelBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(SIGNAL_FIRE, WATERLOGGED, AXIS);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = super.getStateForPlacement(context);
        if (state != null) return state.setValue(SIGNAL_FIRE, isSmokeSource(context.getLevel().getBlockState(context.getClickedPos().below())));
        else return defaultBlockState();
    }

    @Override
    public BlockState updateShape(
            BlockState pState, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos
    ) {
        BlockState state = super.updateShape(pState, direction, neighborState, level, pos, neighborPos);
        return direction.equals(Direction.DOWN) ? state.setValue(SIGNAL_FIRE, isSmokeSource(neighborState)) : state;
    }

    private boolean isSmokeSource(BlockState pState) {
        return pState.is(Blocks.HAY_BLOCK);
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pRandom.nextInt(10) == 0) {
            pLevel.playLocalSound(
                    pPos.getX() + 0.5,
                    pPos.getY() + 0.5,
                    pPos.getZ() + 0.5,
                    SoundEvents.CAMPFIRE_CRACKLE,
                    SoundSource.BLOCKS,
                    0.5F + pRandom.nextFloat(),
                    pRandom.nextFloat() * 0.7F + 0.6F,
                    false
            );
        }

        if (this.spawnParticles && pRandom.nextInt(5) == 0) {
            for(int i = 0; i < pRandom.nextInt(1) + 1; ++i) {
                pLevel.addParticle(
                        ParticleTypes.LAVA,
                        pPos.getX() + 0.5,
                        pPos.getY() + 0.5,
                        pPos.getZ() + 0.5,
                        pRandom.nextFloat() / 2.0F,
                        5.0E-5,
                        pRandom.nextFloat() / 2.0F
                );
            }
        }

        if (pRandom.nextFloat() < 0.4F) {
            for(int i = 0; i < pRandom.nextInt(2) + 2; ++i) {
                CampfireBlock.makeParticles(pLevel, pPos, pState.getValue(CampfireBlock.SIGNAL_FIRE), false);
            }
        }
    }

    @Override
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if (pEntity instanceof LivingEntity) pEntity.hurt(pLevel.damageSources().campfire(), fireDamage);
        super.entityInside(pState, pLevel, pPos, pEntity);
    }
}
