package net.fiercemanul.fiercesource.data;

import net.fiercemanul.fiercesource.util.FSUtils;
import net.fiercemanul.fiercesource.world.level.block.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;

import static net.fiercemanul.fiercesource.data.registries.FCRegistries.regBlock;
import static net.fiercemanul.fiercesource.data.registries.FCRegistries.simpleBlock;

public final class FSBlocks {


    public static final DeferredBlock<SoulCrystalBlock>
            SMALL_SOUL_CRYSTAL = regBlock("small_soul_crystal", p -> new SoulCrystalBlock(p, SoulCrystalType.SMALL), SoulCrystalBlock.getProperties(5, MapColor.WOOL));
    public static final DeferredBlock<SoulCrystalBlock>
            MEDIUM_SOUL_CRYSTAL = regBlock("medium_soul_crystal", p -> new SoulCrystalBlock(p, SoulCrystalType.MEDIUM), SoulCrystalBlock.getProperties(10, MapColor.WOOL));
    public static final DeferredBlock<SoulCrystalBlock>
            LARGE_SOUL_CRYSTAL = regBlock("large_soul_crystal", p -> new SoulCrystalBlock(p, SoulCrystalType.LARGE), SoulCrystalBlock.getProperties(15, MapColor.WOOL).noOcclusion());
    public static final DeferredBlock<ManaCrystalBlock>
            SMALL_MANA_CRYSTAL = regBlock("small_mana_crystal", p -> new ManaCrystalBlock(p, SoulCrystalType.MANA_SMALL), SoulCrystalBlock.getProperties(5, MapColor.COLOR_LIGHT_BLUE));
    public static final DeferredBlock<ManaCrystalBlock>
            MEDIUM_MANA_CRYSTAL = regBlock("medium_mana_crystal", p -> new ManaCrystalBlock(p, SoulCrystalType.MANA_MEDIUM), SoulCrystalBlock.getProperties(10, MapColor.COLOR_LIGHT_BLUE));
    public static final DeferredBlock<ManaCrystalBlock>
            LARGE_MANA_CRYSTAL = regBlock("large_mana_crystal", p -> new ManaCrystalBlock(p, SoulCrystalType.MANA_LARGE), SoulCrystalBlock.getProperties(15, MapColor.COLOR_LIGHT_BLUE).noOcclusion());
    public static final DeferredBlock<SimpleCapabilityBlock>
            CREATIVE_MANA_BLOCK = regBlock("creative_mana_block", SimpleCapabilityBlock::new, BlockBehaviour.Properties.of().strength(2.0F, 1200.0F).lightLevel(value -> 15).emissiveRendering(FSUtils::getTrue).sound(SoundType.AMETHYST).mapColor(MapColor.COLOR_BLUE));
    public static final DeferredBlock<CreativeManaProviderBlock>
            CREATIVE_MANA_PROVIDER = regBlock("creative_mana_provider", CreativeManaProviderBlock::new, BlockBehaviour.Properties.of().strength(2.0F, 1200.0F).lightLevel(value -> 15).emissiveRendering(FSUtils::getTrue).sound(SoundType.AMETHYST).mapColor(MapColor.COLOR_BLUE));
    public static final DeferredBlock<Block>
            HAO_STONE = regBlock("hao_stone", Block::new, BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6.0F));
    public static final DeferredBlock<Block>
            POLISHED_HAO_STONE = regBlock("polished_hao_stone", Block::new, BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6.0F));
    public static final DeferredBlock<Block>
            SMOOTH_HAO_STONE = regBlock("smooth_hao_stone", Block::new, BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6.0F));
    public static final DeferredBlock<Block>
            DEEP_STONE = regBlock("deep_stone", Block::new, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6.0F));
    public static final DeferredBlock<Block>
            POLISHED_DEEP_STONE = regBlock("polished_deep_stone", Block::new, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6.0F));
    public static final DeferredBlock<Block>
            SMOOTH_DEEP_STONE = regBlock("smooth_deep_stone", Block::new, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6.0F));
    public static final DeferredBlock<WorldLocatorBlock>
            WORLD_LOCATOR = regBlock("world_locator", WorldLocatorBlock::new, BlockBehaviour.Properties.of().strength(2.0F, 20.0F).sound(SoundType.STONE).mapColor(MapColor.COLOR_BLUE));
    public static final DeferredBlock<TestBlock>
            TEST_BLOCK = regBlock("test_block", TestBlock::new, SoulCrystalBlock.getProperties(15, MapColor.COLOR_BLACK));
    public static final DeferredBlock<Block>
            PLACEHOLDER = simpleBlock("placeholder", BlockBehaviour.Properties.of().instabreak().replaceable().noLootTable().isValidSpawn(FSUtils::getFalse).mapColor(MapColor.COLOR_LIGHT_BLUE));
    public static final DeferredBlock<OcclusionTestBlock>
            OCCLUSION_TEST = regBlock("occlusion_test", OcclusionTestBlock::new, OcclusionTestBlock.PROPERTIES);

    public static void init() {
    }

}
