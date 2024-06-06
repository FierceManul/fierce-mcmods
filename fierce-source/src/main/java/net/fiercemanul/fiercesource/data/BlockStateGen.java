package net.fiercemanul.fiercesource.data;

import net.fiercemanul.fiercesource.FierceSource;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class BlockStateGen extends FSBlockStateProvider{


    public BlockStateGen(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, FierceSource.FC_MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simple(FierceSource.CREATIVE_MANA_BLOCK);
        directionBlock(FierceSource.CREATIVE_MANA_OUTPUT_BLOCK, false);
        simpleWithModel(FierceSource.WORLD_LOCATOR_BLOCK);

        itemModels().basicItem(FierceSource.LARGE_SOUL_CRYSTAL_BLOCK_ITEM.get());
        itemModels().basicItem(FierceSource.MEDIUM_SOUL_CRYSTAL_BLOCK_ITEM.get());
        itemModels().basicItem(FierceSource.SMALL_SOUL_CRYSTAL_BLOCK_ITEM.get());
        itemModels().basicItem(FierceSource.SOUL_CRYSTAL_SHARD_ITEM.get());
        itemModels().basicItem(FierceSource.FE_ICON.get());
        itemModels().basicItem(FierceSource.MANA_ICON.get());
        itemModels().basicItem(FierceSource.POS_RECORDER_ITEM.get());
    }
}
