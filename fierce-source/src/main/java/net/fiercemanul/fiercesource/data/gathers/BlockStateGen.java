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
        simple(CREATIVE_MANA_BLOCK);
        directionBlock(CREATIVE_MANA_PROVIDER, false);
        simpleWithModel(FSItems.HYPERCUBE);
        simpleWithModel(WORLD_LOCATOR);

        simpleWithModel(SMALL_SOUL_CRYSTAL, models().getExistingFile(MODEL_SMALL_CRYSTAL));
        simpleWithModel(MEDIUM_SOUL_CRYSTAL, models().getExistingFile(MODEL_MEDIUM_CRYSTAL));
        simpleWithModel(LARGE_SOUL_CRYSTAL, models().getExistingFile(MODEL_LARGE_CRYSTAL));
        smallCrystal(SMALL_MANA_CRYSTAL);
        mediumCrystal(MEDIUM_MANA_CRYSTAL);
        largeCrystal(LARGE_MANA_CRYSTAL);

        rotationDecoratedItem(FSItems.SOUL_CRYSTAL_SHARD);
        decoratedItem(FSItems.SOUL_CRYSTAL_DUST);

        simpleNature(HAO_STONE);
        simple(POLISHED_HAO_STONE);
        simple(SMOOTH_HAO_STONE);
        simple(DEEP_STONE);
        simple(POLISHED_DEEP_STONE);
        simple(SMOOTH_DEEP_STONE);

        largeCrystalWithIcon(TEST_BLOCK);
        simple(PLACEHOLDER);
        itemModels().basicItem(FSItems.TEST_ITEM.get());
        itemModels().basicItem(FSItems.FE_ICON.get());
        itemModels().basicItem(FSItems.MANA_ICON.get());

    }
}
