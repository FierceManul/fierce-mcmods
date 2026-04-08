package net.fiercemanul.fiercesource.data.gathers;

import net.fiercemanul.fiercesource.FierceSource;
import net.fiercemanul.fiercesource.data.FSBlockStateProvider;
import net.fiercemanul.fiercesource.data.FSItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import static net.fiercemanul.fiercesource.data.FSBlocks.*;

public class BlockStateGen extends FSBlockStateProvider {


    public BlockStateGen(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, FierceSource.FC_MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simple(CREATIVE_MANA_BLOCK.deferredBlock());
        directionBlock(CREATIVE_MANA_OUTPUT.deferredBlock(), false);
        simpleWithModel(HYPERCUBE.deferredBlock());
        simpleWithModel(WORLD_LOCATOR.deferredBlock());

        simpleWithModel(
                SMALL_SOUL_CRYSTAL.deferredBlock(),
                models().getExistingFile(MODEL_SMALL_CRYSTAL)
        );
        simpleWithModel(
                MEDIUM_SOUL_CRYSTAL.deferredBlock(),
                models().getExistingFile(MODEL_MEDIUM_CRYSTAL)
        );
        simpleWithModel(
                LARGE_SOUL_CRYSTAL.deferredBlock(),
                models().getExistingFile(MODEL_LARGE_CRYSTAL)
        );

        smallCrystal(SMALL_MANA_CRYSTAL.deferredBlock());
        mediumCrystal(MEDIUM_MANA_CRYSTAL.deferredBlock());
        largeCrystal(LARGE_MANA_CRYSTAL.deferredBlock());

        largeCrystalWithIcon(TEST_BLOCK.deferredBlock());

        rotationDecoratedItem(SOUL_CRYSTAL_SHARD.deferredBlock());
        decoratedItem(SOUL_CRYSTAL_DUST.deferredBlock());

        simpleNature(HAO_STONE.deferredBlock());
        simple(POLISHED_HAO_STONE.deferredBlock());
        simple(SMOOTH_HAO_STONE.deferredBlock());
        simple(DEEP_STONE.deferredBlock());
        simple(POLISHED_DEEP_STONE.deferredBlock());
        simple(SMOOTH_DEEP_STONE.deferredBlock());

        itemModels().basicItem(FSItems.TEST_ITEM.get());
        itemModels().basicItem(FSItems.FE_ICON.get());
        itemModels().basicItem(FSItems.MANA_ICON.get());
    }
}
