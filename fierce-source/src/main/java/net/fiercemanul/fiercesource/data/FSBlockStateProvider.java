package net.fiercemanul.fiercesource.data;

import net.fiercemanul.fiercesource.util.FSUtils;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.ModelProvider;
import net.neoforged.neoforge.client.model.generators.loaders.CompositeModelBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.HashMap;

public abstract class FSBlockStateProvider extends BlockStateProvider {


    protected static final ResourceLocation BLACK_IRON = FSUtils.rl("block/black_iron");
    protected static final ResourceLocation MODEL_SMALL_CRYSTAL = FSUtils.rl("block/small_soul_crystal");
    protected static final ResourceLocation MODEL_MEDIUM_CRYSTAL = FSUtils.rl("block/medium_soul_crystal");
    protected static final ResourceLocation MODEL_LARGE_CRYSTAL = FSUtils.rl("block/large_soul_crystal");
    protected static final ResourceLocation MODEL_SMALL_CRYSTAL_ICON = FSUtils.rl("block/small_soul_crystal_icon");
    protected static final ResourceLocation MODEL_MEDIUM_CRYSTAL_ICON = FSUtils.rl("block/medium_soul_crystal_icon");
    protected static final ResourceLocation MODEL_LARGE_CRYSTAL_ICON = FSUtils.rl("block/large_soul_crystal_icon");
    protected static final ResourceLocation MODEL_CRYSTAL_SHARD = FSUtils.rl("block/soul_crystal_shard");
    protected static final ResourceLocation MODEL_DUST = FSUtils.rl("block/soul_crystal_dust");
    protected static final HashMap<Direction, Integer> YROT_MAP = new HashMap<>();
    protected final ResourceLocation model_block = mcLoc("block/block");

    static {
        YROT_MAP.put(Direction.NORTH, 0);
        YROT_MAP.put(Direction.SOUTH, 180);
        YROT_MAP.put(Direction.WEST, 270);
        YROT_MAP.put(Direction.EAST, 90);
    }

    protected final ExistingFileHelper exFileHelper;


    public FSBlockStateProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, modid, exFileHelper);
        this.exFileHelper = exFileHelper;
    }

    protected void simple(DeferredBlock<? extends Block> deferredBlock) {
        Block block = deferredBlock.get();
        simple(block, deferredBlock.getId().getPath(), cubeAll(block));
    }

    protected void simpleWithModel(DeferredBlock<? extends Block> deferredBlock) {
        ResourceLocation id = deferredBlock.getId();
        ModelFile model = models().getExistingFile(ResourceLocation.fromNamespaceAndPath(id.getNamespace(), "block/" + id.getPath()));
        simple(deferredBlock.get(), id.getPath(), model);
    }

    protected void simpleWithModel(DeferredBlock<? extends Block> deferredBlock, ModelFile model) {
        simple(deferredBlock.get(), deferredBlock.getId().getPath(), model);
    }

    protected void simpleWithMcModel(DeferredBlock<? extends Block> deferredBlock, String modelFile) {
        ResourceLocation id = deferredBlock.getId();
        ModelFile model = models().getExistingFile(mcLoc("block/" + modelFile));
        simple(deferredBlock.get(), id.getPath(), model);
    }

    protected void simple(Block block, String path, ModelFile model, ResourceLocation texture) {
        simple(block, path, models().getBuilder(path).parent(model).texture("all", texture).texture("particle", texture));
    }

    protected void simple(Block block, String path, ResourceLocation modelRl, ResourceLocation texture) {
        simple(block, path, models().withExistingParent(path, modelRl).texture("all", texture).texture("particle", texture));
    }

    protected void simple(Block block, String path, ModelFile model) {
        getVariantBuilder(block).partialState().setModels(new ConfiguredModel(model));
        itemModels().getBuilder(path).parent(model);
    }

    protected void simpleNature(DeferredBlock<? extends Block> deferredBlock) {
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
    protected void directionBlock(DeferredBlock<? extends Block> deferredBlock, boolean lockUV) {
        ResourceLocation id = deferredBlock.getId();
        ModelFile model = models().getExistingFile(ResourceLocation.fromNamespaceAndPath(id.getNamespace(), "block/" + id.getPath()));
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
    protected void horizontalDirectionBlock(DeferredBlock<? extends Block> deferredBlock, boolean lockUV) {
        ResourceLocation id = deferredBlock.getId();
        ModelFile model = models().getExistingFile(ResourceLocation.fromNamespaceAndPath(id.getNamespace(), "block/" + id.getPath()));
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
    protected void yAxisModel(Block block, String path, ModelFile model, boolean lockUV) {
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
    protected void horizontalAxisBlock(DeferredBlock<? extends Block> deferredBlock, boolean lockUV) {
        ResourceLocation id = deferredBlock.getId();
        ModelFile model = models().getExistingFile(ResourceLocation.fromNamespaceAndPath(id.getNamespace(), "block/" + id.getPath()));
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

    protected void rotationDecoratedBlock(DeferredBlock<? extends Block> deferredBlock) {
        ResourceLocation id = deferredBlock.getId();
        rotationDecoratedModel(
                deferredBlock.get(),
                id.getPath(),
                models().getExistingFile(ResourceLocation.fromNamespaceAndPath(id.getNamespace(), "block/" + id.getPath())),
                models().getExistingFile(ResourceLocation.fromNamespaceAndPath(id.getNamespace(), "block/" + id.getPath() + "_r22")),
                models().getExistingFile(ResourceLocation.fromNamespaceAndPath(id.getNamespace(), "block/" + id.getPath() + "_r45")),
                models().getExistingFile(ResourceLocation.fromNamespaceAndPath(id.getNamespace(), "block/" + id.getPath() + "_r68"))
        );
    }

    protected void rotationDecoratedModel(Block block, String path, ModelFile model, ModelFile model22, ModelFile model45, ModelFile model68) {
        var builder = getVariantBuilder(block);
        for (int i = 0; i < 16; i+=4) {
            builder.partialState()
                   .with(BlockStateProperties.ROTATION_16, i).modelForState()
                   .modelFile(model).rotationY(90 * i/4).addModel()
                   .partialState()
                   .with(BlockStateProperties.ROTATION_16, i + 1).modelForState()
                   .modelFile(model22).rotationY(90 * i/4).addModel()
                   .partialState()
                   .with(BlockStateProperties.ROTATION_16, i + 2).modelForState()
                   .modelFile(model45).rotationY(90 * i/4).addModel()
                   .partialState()
                   .with(BlockStateProperties.ROTATION_16, i + 3).modelForState()
                   .modelFile(model68).rotationY(90 * i/4).addModel();
        }
        itemModels().getBuilder(path).parent(model);
    }



    protected void largeCrystal(DeferredBlock<? extends Block> deferredBlock) {
        simple(deferredBlock.get(), deferredBlock.getId().getPath(), MODEL_LARGE_CRYSTAL, blockTexture(deferredBlock.get()));
    }

    protected void largeCrystal(Block block, String path, ResourceLocation texture) {
        simple(block, path, MODEL_LARGE_CRYSTAL, texture);
    }

    protected void largeCrystalWithIcon(DeferredBlock<? extends Block> deferredBlock) {
        ResourceLocation id = deferredBlock.getId();
        largeCrystalWithIcon(
                deferredBlock.get(),
                id.getPath(),
                ResourceLocation.fromNamespaceAndPath(id.getNamespace(), ModelProvider.BLOCK_FOLDER + "/" + id.getPath()),
                ResourceLocation.fromNamespaceAndPath(id.getNamespace(), ModelProvider.BLOCK_FOLDER + "/" + id.getPath() + "_icon")
        );
    }

    protected void largeCrystalWithIcon(Block block, String path, ResourceLocation texture, ResourceLocation icon) {
        crystalWithIcon(block, path, texture, MODEL_LARGE_CRYSTAL, icon, MODEL_LARGE_CRYSTAL_ICON);
    }

    protected void mediumCrystal(DeferredBlock<? extends Block> deferredBlock) {
        simple(deferredBlock.get(), deferredBlock.getId().getPath(), MODEL_MEDIUM_CRYSTAL, blockTexture(deferredBlock.get()));
    }

    protected void mediumCrystal(Block block, String path, ResourceLocation texture) {
        simple(block, path, MODEL_MEDIUM_CRYSTAL, texture);
    }

    protected void mediumCrystalWithIcon(DeferredBlock<? extends Block> deferredBlock) {
        ResourceLocation id = deferredBlock.getId();
        mediumCrystalWithIcon(
                deferredBlock.get(),
                id.getPath(),
                ResourceLocation.fromNamespaceAndPath(id.getNamespace(), ModelProvider.BLOCK_FOLDER + "/" + id.getPath()),
                ResourceLocation.fromNamespaceAndPath(id.getNamespace(), ModelProvider.BLOCK_FOLDER + "/" + id.getPath() + "_icon")
        );
    }

    protected void mediumCrystalWithIcon(Block block, String path, ResourceLocation texture, ResourceLocation icon) {
        crystalWithIcon(block, path, texture, MODEL_MEDIUM_CRYSTAL, icon, MODEL_MEDIUM_CRYSTAL_ICON);
    }

    protected void smallCrystal(DeferredBlock<? extends Block> deferredBlock) {
        simple(deferredBlock.get(), deferredBlock.getId().getPath(), MODEL_SMALL_CRYSTAL, blockTexture(deferredBlock.get()));
    }

    protected void smallCrystal(Block block, String path, ResourceLocation texture) {
        simple(block, path, MODEL_SMALL_CRYSTAL, texture);
    }

    protected void smallCrystalWithIcon(DeferredBlock<? extends Block> deferredBlock) {
        ResourceLocation id = deferredBlock.getId();
        smallCrystalWithIcon(
                deferredBlock.get(),
                id.getPath(),
                ResourceLocation.fromNamespaceAndPath(id.getNamespace(), ModelProvider.BLOCK_FOLDER + "/" + id.getPath()),
                ResourceLocation.fromNamespaceAndPath(id.getNamespace(), ModelProvider.BLOCK_FOLDER + "/" + id.getPath() + "_icon")
        );
    }

    protected void smallCrystalWithIcon(Block block, String path, ResourceLocation texture, ResourceLocation icon) {
        crystalWithIcon(block, path, texture, MODEL_SMALL_CRYSTAL, icon, MODEL_SMALL_CRYSTAL_ICON);
    }

    protected void crystalWithIcon(
            Block block, String path, ResourceLocation texture, ResourceLocation model, ResourceLocation icon, ResourceLocation iconModel
    ) {
        var topModel = models()
                .withExistingParent(path, model_block)
                .texture("particle", texture)
                .customLoader(CompositeModelBuilder::begin)
                .child("part_a",
                       models().nested()
                               .parent(models().getExistingFile(model))
                               .texture("all", texture))
                .child("part_b",
                       models().nested()
                               .parent(models().getExistingFile(iconModel))
                               .texture("icon", icon)
                ).end();
        getVariantBuilder(block).partialState().modelForState().modelFile(topModel).addModel();
        itemModels().getBuilder(path).parent(topModel);
    }
}
