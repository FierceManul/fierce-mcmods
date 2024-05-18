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

    protected void directionModel(DeferredBlock<Block> deferredBlock, boolean lockUV) {
        ResourceLocation id = deferredBlock.getId();
        ModelFile model = models().getExistingFile(new ResourceLocation(id.getNamespace(), "block/" + id.getPath()));
        getVariantBuilder(deferredBlock.get())
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
        itemModels().getBuilder(id.getPath()).parent(model);
    }

    protected void horizontalDirectionModel(DeferredBlock<Block> deferredBlock, boolean lockUV) {
        ResourceLocation id = deferredBlock.getId();
        ModelFile model = models().getExistingFile(new ResourceLocation(id.getNamespace(), "block/" + id.getPath()));
        getVariantBuilder(deferredBlock.get())
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
        itemModels().getBuilder(id.getPath()).parent(model);
    }

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
}
