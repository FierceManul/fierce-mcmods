package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercedecoration.world.level.block.state.properties.FDBlockStateProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class HalfPodzolBlock extends Block implements SimpleWaterloggedBlock {


    public static final MapCodec<HalfPodzolBlock> CODEC = simpleCodec(HalfPodzolBlock::new);

    public static final IntegerProperty LAYERS = FDBlockStateProperties.HALF_LAYERS;
    protected static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    protected static final VoxelShape[] SHAPE_BY_LAYER = new VoxelShape[]{
            OneCutBlock.SHAPE_DOWN,
            OneCutBlock.SHAPE_DOWN,
            box(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D),
            box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D),
            box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D),
            Shapes.block()
    };

    protected final Block doubleBlock;

    public HalfPodzolBlock(Properties pProperties) {
        this(pProperties, Blocks.PODZOL);
    }

    public HalfPodzolBlock(Properties pProperties, Block doubleBlock) {
        super(pProperties.randomTicks());
        this.doubleBlock = doubleBlock;
        this.registerDefaultState(stateDefinition.any().setValue(LAYERS, 0).setValue(WATERLOGGED, false));
    }

    @Override
    protected MapCodec<? extends HalfPodzolBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(LAYERS, WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockState blockstate = pContext.getLevel().getBlockState(pContext.getClickedPos());
        if (blockstate.is(this)) return doubleBlock.getStateForPlacement(pContext);
        return this.defaultBlockState().setValue(WATERLOGGED, pContext.getLevel().getFluidState(pContext.getClickedPos()).getType() == Fluids.WATER);
    }

    @Override
    public boolean canBeReplaced(BlockState pState, BlockPlaceContext pUseContext) {
        if (!pState.getValue(WATERLOGGED)
                && pUseContext.getItemInHand().is(Items.SNOW)
                && pState.getValue(LAYERS) < 4
                && pUseContext.getClickLocation().y - pUseContext.getClickedPos().getY() >= 0.5 + 0.125 * pState.getValue(LAYERS)
        ) {
            if (pUseContext.getLevel().isClientSide) {
                Player player = pUseContext.getPlayer();
                if (player != null) {
                    pUseContext.getPlayer().swing(pUseContext.getHand());
                    pUseContext.getItemInHand().shrink(1);
                }
                return false;
            }
            else return true;
        }
        return pUseContext.getItemInHand().is(this.asItem()) && pUseContext.getClickLocation().y - pUseContext.getClickedPos().getY() >= 0.5;
    }

    @Override
    protected ItemInteractionResult useItemOn(
            ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHitResult
    ) {
        if (pStack.getTags().anyMatch(tagKey -> tagKey.equals(ItemTags.SHOVELS))) {
            pLevel.setBlockAndUpdate(pPos, FDBlocks.HALF_PATH_BLOCK.get().defaultBlockState());
            if (!pPlayer.isCreative()) dropResources(
                    Blocks.SNOW.defaultBlockState().setValue(SnowLayerBlock.LAYERS, pState.getValue(LAYERS)),
                    pLevel,
                    pPos,
                    null,
                    pPlayer,
                    pStack,
                    false
            );
            return ItemInteractionResult.sidedSuccess(pLevel.isClientSide);
        }
        if (pLevel.isClientSide
                && !pState.getValue(WATERLOGGED)
                && pStack.is(Items.SNOW)
                && pState.getValue(LAYERS) < 4
                && pHitResult.getLocation().y - pHitResult.getBlockPos().getY() >= 0.5 + 0.125 * pState.getValue(LAYERS)
        ) {
            pStack.shrink(1);
            return ItemInteractionResult.SUCCESS;
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE_BY_LAYER[pState.getValue(LAYERS) + 1];
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE_BY_LAYER[pState.getValue(LAYERS)];
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState pState) {
        return true;
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        if (pState.getValue(WATERLOGGED)) pLevel.scheduleTick(pCurrentPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
        if (!pLevel.getFluidState(pCurrentPos.above()).isEmpty()) pState = pState.setValue(LAYERS, 0);
        return super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (!pLevel.isAreaLoaded(pPos, 1)) return;
        if (pLevel.getBrightness(LightLayer.BLOCK, pPos) > 11 && pState.getValue(LAYERS) > 0) {
            pLevel.setBlockAndUpdate(pPos, pState.setValue(LAYERS, 0));
            dropResources(
                    Blocks.SNOW.defaultBlockState().setValue(SnowLayerBlock.LAYERS, pState.getValue(LAYERS)),
                    pLevel,
                    pPos
            );
        }
        else if (pLevel.isRainingAt(pPos) && pLevel.getBiome(pPos).value().shouldSnow(pLevel, pPos)) {
            int maxLayer = pLevel.getGameRules().getInt(GameRules.RULE_SNOW_ACCUMULATION_HEIGHT);
            int myLayer = pState.getValue(LAYERS);
            if (myLayer < Integer.min(maxLayer, 4)) {
                BlockState newState = pState.setValue(LAYERS, maxLayer + 1);
                pLevel.setBlockAndUpdate(pPos, newState);
                Block.pushEntitiesUp(pState, newState, pLevel, pPos);
            }
        }
    }

    @Override
    protected boolean isPathfindable(BlockState pState, PathComputationType pPathComputationType) {
        if (pPathComputationType == PathComputationType.LAND) return pState.getValue(LAYERS) < 2;
        return false;
    }

    @Override
    public FluidState getFluidState(BlockState pState) {
        return pState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(pState);
    }

    @Override
    public boolean placeLiquid(LevelAccessor pLevel, BlockPos pPos, BlockState pState, FluidState pFluidState) {
        if (!pState.getValue(BlockStateProperties.WATERLOGGED) && pFluidState.getType() == Fluids.WATER) {
            if (!pLevel.isClientSide()) {
                pLevel.setBlock(
                        pPos,
                        pState.setValue(LAYERS, 0)
                              .setValue(BlockStateProperties.WATERLOGGED, true),
                        3);
                pLevel.scheduleTick(pPos, pFluidState.getType(), pFluidState.getType().getTickDelay(pLevel));
            }
            return true;
        } else {
            return false;
        }
    }

}
