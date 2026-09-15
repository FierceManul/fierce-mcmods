package net.fiercemanul.fiercelive.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercelive.world.level.block.state.properties.FLBlockStateProperties;
import net.fiercemanul.fiercesource.util.VoxelShapeHelper;
import net.fiercemanul.fiercesource.world.item.WrenchUtils;
import net.fiercemanul.fiercesource.world.level.block.HorizonFacingWaterloggedBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MirrorBlock extends HorizonFacingWaterloggedBlock {


    public static final MapCodec<MirrorBlock> CODEC = simpleCodec(MirrorBlock::new);
    protected static final IntegerProperty TYPE = FLBlockStateProperties.MIRROR_TYPE;
    protected static final VoxelShapeHelper SHAPE = new VoxelShapeHelper()
            .applyCube(0, 4, 15, 16, 16, 16)
            .applyCube(2, 15, 14, 14, 16, 15);
    protected static final VoxelShape SHAPE_NORTH = SHAPE.north();
    protected static final VoxelShape SHAPE_SOUTH = SHAPE.south();
    protected static final VoxelShape SHAPE_WEST = SHAPE.west();
    protected static final VoxelShape SHAPE_EAST = SHAPE.east();

    public MirrorBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(TYPE, 0).setValue(WATERLOGGED, false));
    }

    @Override
    public MapCodec<? extends MirrorBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, TYPE, WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction clickedFace = context.getClickedFace();
        return defaultBlockState()
                .setValue(FACING, clickedFace.getAxis().isVertical() ? context.getHorizontalDirection().getOpposite() : clickedFace)
                .setValue(TYPE, 1)
                .setValue(WATERLOGGED, context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case SOUTH -> SHAPE_SOUTH;
            case WEST -> SHAPE_WEST;
            case EAST -> SHAPE_EAST;
            default -> SHAPE_NORTH;
        };
    }

    @Override
    protected ItemInteractionResult useItemOn(
            ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult
    ) {
        return WrenchUtils.interact(stack, state, level, pos, player);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (state.getValue(TYPE) == 1) {
            if (!level.isClientSide) {
                int r = level.random.nextInt(61);
                if (r > 54) {
                    int i = r - 53;
                    level.setBlock(pos, state.setValue(TYPE, i), Block.UPDATE_ALL_IMMEDIATE);
                    level.scheduleTick(pos, state.getBlock(), 5);
                    if (i == 2 || i == 3 || i == 6) level.playSound(null, pos, SoundEvents.ZOMBIE_AMBIENT, SoundSource.BLOCKS, 0.3F, 1.1F);
                    if (i == 4 || i == 5) level.playSound(null, pos, SoundEvents.SKELETON_AMBIENT, SoundSource.BLOCKS, 0.3F, 1.1F);
                    if (i == 7) level.playSound(null, pos, SoundEvents.CAT_AMBIENT, SoundSource.BLOCKS, 0.3F, 1.1F);
                }
                else {
                    level.setBlock(pos, state.setValue(TYPE, 0), Block.UPDATE_ALL_IMMEDIATE);
                    level.playSound(null, pos, SoundEvents.LEVER_CLICK, SoundSource.BLOCKS, 0.3F, 0.5F);
                }
                level.gameEvent(player, GameEvent.BLOCK_DEACTIVATE, pos);
            }
        }
        else {
            level.setBlock(pos, state.setValue(TYPE, 1), Block.UPDATE_ALL_IMMEDIATE);
            level.playSound(player, pos, SoundEvents.LEVER_CLICK, SoundSource.BLOCKS, 0.3F, 0.6F);
            level.gameEvent(player, GameEvent.BLOCK_ACTIVATE, pos);
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        level.setBlock(pos, state.setValue(TYPE, 0), Block.UPDATE_ALL);
    }

}