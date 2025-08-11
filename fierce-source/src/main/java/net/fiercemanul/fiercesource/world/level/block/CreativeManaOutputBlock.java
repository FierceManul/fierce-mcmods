package net.fiercemanul.fiercesource.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.registries.FSBlockEntityTypes;
import net.fiercemanul.fiercesource.util.FSUtils;
import net.fiercemanul.fiercesource.util.VoxelShapeHelper;
import net.fiercemanul.fiercesource.world.level.block.entity.CreativeManaOutputBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class CreativeManaOutputBlock extends FacingModelBlock implements EntityBlock {


    public static final MapCodec<CreativeManaOutputBlock> CODEC = simpleCodec(CreativeManaOutputBlock::new);
    private static final VoxelShapeHelper SHAPE_HELPER = new VoxelShapeHelper()
            .applyCube(2.0, 2.0, 0.0, 14.0, 14.0, 2.0)
            .applyCube(3.0, 3.0, 2.0, 13.0, 13.0, 4.0)
            .applyCube(4.0, 4.0, 4.0, 12.0, 12.0, 8.0);
    public static final VoxelShape SHAPE_NORTH = SHAPE_HELPER.north();
    public static final VoxelShape SHAPE_SOUTH = SHAPE_HELPER.south();
    public static final VoxelShape SHAPE_WEST = SHAPE_HELPER.west();
    public static final VoxelShape SHAPE_EAST = SHAPE_HELPER.east();
    public static final VoxelShape SHAPE_UP = SHAPE_HELPER.up();
    public static final VoxelShape SHAPE_DOWN = SHAPE_HELPER.down();

    public CreativeManaOutputBlock(Properties pProperties) {
        super(pProperties.strength(2.0F, 1200.0F)
                         .lightLevel(value -> 15)
                         .emissiveRendering(FSUtils::getTrue)
                         .sound(SoundType.AMETHYST)
                         .mapColor(MapColor.COLOR_BLUE),
              FacingBlock.CLICKED_DIRECTION_OPPOSITE);
    }

    @Override
    protected MapCodec<? extends CreativeManaOutputBlock> codec() {
        return CODEC;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return switch (pState.getValue(FACING)) {
            case NORTH -> SHAPE_NORTH;
            case SOUTH ->  SHAPE_SOUTH;
            case EAST -> SHAPE_EAST;
            case WEST -> SHAPE_WEST;
            case UP -> SHAPE_UP;
            case DOWN -> SHAPE_DOWN;
        };
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new CreativeManaOutputBlockEntity(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if (!pLevel.isClientSide && pBlockEntityType == FSBlockEntityTypes.CREATIVE_MANA_OUTPUT_BLOCK_ENTITY.get())
            return (pLevel1, pPos, pState1, pBlockEntity) -> ((CreativeManaOutputBlockEntity) pBlockEntity).serverTick();
        else return null;
    }
}
