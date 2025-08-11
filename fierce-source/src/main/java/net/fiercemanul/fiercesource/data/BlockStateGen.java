package net.fiercemanul.fiercesource.data;

import net.fiercemanul.fiercesource.FierceSource;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import static net.fiercemanul.fiercesource.registries.FSBlocksAndItems.*;

public class BlockStateGen extends FSBlockStateProvider{


    public BlockStateGen(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, FierceSource.FC_MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simple(CREATIVE_MANA_BLOCK.getFirst());
        directionBlock(CREATIVE_MANA_OUTPUT.getFirst(), false);
        simpleWithModel(HYPERCUBE.getFirst());
        simpleWithModel(WORLD_LOCATOR.getFirst());

        simpleWithModel(
                SMALL_SOUL_CRYSTAL.getFirst(),
                models().getExistingFile(MODEL_SMALL_CRYSTAL)
        );
        simpleWithModel(
                MEDIUM_SOUL_CRYSTAL.getFirst(),
                models().getExistingFile(MODEL_MEDIUM_CRYSTAL)
        );
        simpleWithModel(
                LARGE_SOUL_CRYSTAL.getFirst(),
                models().getExistingFile(MODEL_LARGE_CRYSTAL)
        );

        smallCrystal(SMALL_MANA_CRYSTAL.getFirst());
        mediumCrystal(MEDIUM_MANA_CRYSTAL.getFirst());
        largeCrystal(LARGE_MANA_CRYSTAL.getFirst());

        largeCrystalWithIcon(TEST_BLOCK.getFirst());

        rotationDecoratedBlock(SOUL_CRYSTAL_SHARD.getFirst());
        simpleWithModel(SOUL_CRYSTAL_DUST.getFirst());

        itemModels().basicItem(TEST_ITEM.get());
        itemModels().basicItem(FE_ICON.get());
        itemModels().basicItem(MANA_ICON.get());
    }
}
