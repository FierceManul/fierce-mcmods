package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercedecoration.world.item.FDItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;


public class FoxCarrotBlock extends CropBlock {


    public static final MapCodec<FoxCarrotBlock> CODEC = simpleCodec(FoxCarrotBlock::new);
    private static final VoxelShape SHAPE = Block.box(0.0, -1.0, 0.0, 16.0, 12.0, 16.0);
    private static final VoxelShape COLLISION_SHAPE = Shapes.or(
                    Block.box(2.0, -1.0, 2.0, 6.0, 2.0, 6.0),
                    Block.box(10.0, -1.0, 2.0, 14.0, 2.0, 6.0),
                    Block.box(2.0, -1.0, 10.0, 6.0, 2.0, 14.0),
                    Block.box(10.0, -1.0, 10.0, 14.0, 2.0, 14.0));
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.box(3.0, -1.0, 3.0, 13.0, 1.0, 13.0),
            Block.box(3.0, -1.0, 3.0, 13.0, 3.0, 13.0),
            Block.box(2.0, -1.0, 2.0, 14.0, 4.0, 14.0),
            Block.box(1.0, -1.0, 1.0, 15.0, 6.0, 15.0),
            Block.box(1.0, -1.0, 1.0, 15.0, 9.0, 15.0),
            SHAPE,
            SHAPE,
            SHAPE
    };

    private static final VoxelShape[] COLLISION_SHAPE_BY_AGE = new VoxelShape[]{
            Shapes.empty(),
            Shapes.empty(),
            Shapes.empty(),
            Shapes.or(
                    Block.box(3.0, -1.0, 3.0, 5.0, 0.0, 5.0),
                    Block.box(11.0, -1.0, 3.0, 13.0, 0.0, 5.0),
                    Block.box(3.0, -1.0, 11.0, 5.0, 0.0, 13.0),
                    Block.box(11.0, -1.0, 11.0, 13.0, 0.0, 13.0)
            ),
            Shapes.or(
                    Block.box(2.0, -1.0, 2.0, 6.0, 1.0, 6.0),
                    Block.box(10.0, -1.0, 2.0, 14.0, 1.0, 6.0),
                    Block.box(2.0, -1.0, 10.0, 6.0, 1.0, 14.0),
                    Block.box(10.0, -1.0, 10.0, 14.0, 1.0, 14.0)
            ),
            COLLISION_SHAPE,
            COLLISION_SHAPE,
            COLLISION_SHAPE
    };

    @Override
    public MapCodec<FoxCarrotBlock> codec() {
        return CODEC;
    }

    public FoxCarrotBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return FDItems.FOX_CARROT_SEED;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE_BY_AGE[this.getAge(pState)];
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return COLLISION_SHAPE_BY_AGE[this.getAge(pState)];
    }
}
