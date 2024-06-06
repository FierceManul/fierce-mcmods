package net.fiercemanul.fiercesource.data;

import net.fiercemanul.fiercesource.FierceSource;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public abstract class FSBlockStateProvider extends BlockStateProvider {



    protected final ResourceLocation BLACK_IRON = new ResourceLocation(FierceSource.FC_MODID, "block/black_iron");


    public FSBlockStateProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, modid, exFileHelper);
    }

    protected void simple(DeferredBlock<Block> deferredBlock) {
        Block block = deferredBlock.get();
        simple(block, deferredBlock.getId().getPath(), cubeAll(block));
    }

    protected void simpleWithModel(DeferredBlock<Block> deferredBlock) {
        ResourceLocation id = deferredBlock.getId();
        ModelFile model = models().getExistingFile(new ResourceLocation(id.getNamespace(), "block/" + id.getPath()));
        simple(deferredBlock.get(), id.getPath(), model);
    }

    protected void simpleWithModel(DeferredBlock<Block> deferredBlock, ModelFile model) {
        simple(deferredBlock.get(), deferredBlock.getId().getPath(), model);
    }

    protected void simpleWithMcModel(DeferredBlock<Block> deferredBlock, String modelFile) {
        ResourceLocation id = deferredBlock.getId();
        ModelFile model = models().getExistingFile(mcLoc("block/" + modelFile));
        simple(deferredBlock.get(), id.getPath(), model);
    }

    protected void simple(Block block, String path, ModelFile model) {
        getVariantBuilder(block).partialState().setModels(new ConfiguredModel(model));
        itemModels().getBuilder(path).parent(model);
    }

    protected void simpleNature(DeferredBlock<Block> deferredBlock) {
        Block block = deferredBlock.get();
        String path = deferredBlock.getId().getPath();
        ModelFile model = cubeAll(block);
        getVariantBuilder(block)
                .partialState().modelForState()
                .modelFile(model)
                .nextModel()
                .modelFile(model)
                .rotationX(90)
                .rotationY(90)
                .nextModel()
                .modelFile(model)
                .rotationX(270)
                .nextModel()
                .modelFile(model)
                .rotationX(270)
                .rotationY(90)
                .addModel();
        itemModels().getBuilder(path).parent(model);
    }

    /**
     * 以 NORTH 为默认,使用同名模型,带物品模型
     */
    protected void directionBlock(DeferredBlock<Block> deferredBlock, boolean lockUV) {
        ResourceLocation id = deferredBlock.getId();
        ModelFile model = models().getExistingFile(new ResourceLocation(id.getNamespace(), "block/" + id.getPath()));
        directionModel(deferredBlock.get(), id.getPath(), model, lockUV);
    }

    /**
     * 以 NORTH 为默认,带物品模型
     */
    protected void directionModel(Block block, String path, ModelFile model, boolean lockUV) {
        getVariantBuilder(block)
                .partialState()
                .with(BlockStateProperties.FACING, Direction.NORTH).modelForState()
                .modelFile(model).addModel()
                .partialState()
                .with(BlockStateProperties.FACING, Direction.SOUTH).modelForState()
                .modelFile(model)
                .rotationY(180).uvLock(lockUV).addModel()
                .partialState()
                .with(BlockStateProperties.FACING, Direction.WEST).modelForState()
                .modelFile(model)
                .rotationY(270).uvLock(lockUV).addModel()
                .partialState()
                .with(BlockStateProperties.FACING, Direction.EAST).modelForState()
                .modelFile(model)
                .rotationY(90).uvLock(lockUV).addModel()
                .partialState()
                .with(BlockStateProperties.FACING, Direction.UP).modelForState()
                .modelFile(model)
                .rotationX(270).uvLock(lockUV).addModel()
                .partialState()
                .with(BlockStateProperties.FACING, Direction.DOWN).modelForState()
                .modelFile(model)
                .rotationX(90).uvLock(lockUV).addModel();
        itemModels().getBuilder(path).parent(model);
    }

    /**
     * 以 NORTH 为默认,使用同名模型,带物品模型
     */
    protected void horizontalDirectionBlock(DeferredBlock<Block> deferredBlock, boolean lockUV) {
        ResourceLocation id = deferredBlock.getId();
        ModelFile model = models().getExistingFile(new ResourceLocation(id.getNamespace(), "block/" + id.getPath()));
        horizontalDirectionModel(deferredBlock.get(), id.getPath(), model, lockUV);
    }

    /**
     * 以 NORTH 为默认,带物品模型
     */
    protected void horizontalDirectionModel(Block block, String path, ModelFile model, boolean lockUV) {
        getVariantBuilder(block)
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH).modelForState()
                .modelFile(model).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH).modelForState()
                .modelFile(model)
                .rotationY(180).uvLock(lockUV).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST).modelForState()
                .modelFile(model)
                .rotationY(270).uvLock(lockUV).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST).modelForState()
                .modelFile(model)
                .rotationY(90).uvLock(lockUV).addModel();
        itemModels().getBuilder(path).parent(model);
    }

    /**
     * 以 Y 为默认,带物品模型
     */
    protected void yAxisModel(String path, Block block, ModelFile model, boolean lockUV) {
        getVariantBuilder(block)
                .partialState()
                .with(BlockStateProperties.AXIS, Direction.Axis.Y).modelForState()
                .modelFile(model).addModel()
                .partialState()
                .with(BlockStateProperties.AXIS, Direction.Axis.X).modelForState()
                .modelFile(model)
                .rotationX(90).rotationY(270).uvLock(lockUV).addModel()
                .partialState()
                .with(BlockStateProperties.AXIS, Direction.Axis.Z).modelForState()
                .modelFile(model)
                .rotationX(90).uvLock(lockUV).addModel();
        itemModels().getBuilder(path).parent(model);
    }

    /**
     * 以 X 为默认,使用同名模型,带物品模型
     */
    protected void horizontalAxisBlock(DeferredBlock<Block> deferredBlock, boolean lockUV) {
        ResourceLocation id = deferredBlock.getId();
        ModelFile model = models().getExistingFile(new ResourceLocation(id.getNamespace(), "block/" + id.getPath()));
        horizontalAxisModel(deferredBlock.get(), id.getPath(), model, lockUV);
    }

    /**
     * 以 X 为默认,带物品模型
     */
    protected void horizontalAxisModel(Block block, String path, ModelFile model, boolean lockUV) {
        getVariantBuilder(block)
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_AXIS, Direction.Axis.X).modelForState()
                .modelFile(model).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_AXIS, Direction.Axis.Z).modelForState()
                .modelFile(model)
                .rotationY(90).uvLock(lockUV).addModel();
        itemModels().getBuilder(path).parent(model);
    }
}
