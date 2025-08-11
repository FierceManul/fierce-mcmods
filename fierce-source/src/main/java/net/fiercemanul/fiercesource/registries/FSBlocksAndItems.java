package net.fiercemanul.fiercesource.registries;

import net.fiercemanul.fiercesource.util.FSUtils;
import net.fiercemanul.fiercesource.world.item.CrowbarItem;
import net.fiercemanul.fiercesource.world.level.block.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredItem;

import static net.fiercemanul.fiercesource.registries.FCRegistries.*;

public class FSBlocksAndItems {


    public static final ItemBlockGroup<SoulCrystalBlock, BlockItem>
            SMALL_SOUL_CRYSTAL = simpleBlockItem("small_soul_crystal", p -> new SoulCrystalBlock(p, SoulCrystalBlock.SMALL), SoulCrystalBlock.getProperties(5, MapColor.WOOL));
    public static final ItemBlockGroup<SoulCrystalBlock, BlockItem>
            MEDIUM_SOUL_CRYSTAL = simpleBlockItem("medium_soul_crystal", p -> new SoulCrystalBlock(p, SoulCrystalBlock.MEDIUM), SoulCrystalBlock.getProperties(10, MapColor.WOOL));
    public static final ItemBlockGroup<SoulCrystalBlock, BlockItem>
            LARGE_SOUL_CRYSTAL = simpleBlockItem("large_soul_crystal", p -> new SoulCrystalBlock(p, SoulCrystalBlock.LARGE), SoulCrystalBlock.getProperties(15, MapColor.WOOL));
    public static final ItemBlockGroup<ManaCrystalBlock, BlockItem>
            SMALL_MANA_CRYSTAL = simpleBlockItem("small_mana_crystal", p -> new ManaCrystalBlock(p, ManaCrystalBlock.SMALL), SoulCrystalBlock.getProperties(5, MapColor.COLOR_LIGHT_BLUE));
    public static final ItemBlockGroup<ManaCrystalBlock, BlockItem>
            MEDIUM_MANA_CRYSTAL = simpleBlockItem("medium_mana_crystal", p -> new ManaCrystalBlock(p, ManaCrystalBlock.MEDIUM), SoulCrystalBlock.getProperties(10, MapColor.COLOR_LIGHT_BLUE));
    public static final ItemBlockGroup<ManaCrystalBlock, BlockItem>
            LARGE_MANA_CRYSTAL = simpleBlockItem("large_mana_crystal", p -> new ManaCrystalBlock(p, ManaCrystalBlock.LARGE), SoulCrystalBlock.getProperties(15, MapColor.COLOR_LIGHT_BLUE));
    public static final ItemBlockGroup<SimpleCapabilityBlock, BlockItem>
            CREATIVE_MANA_BLOCK = simpleBlockItem("creative_mana_block", SimpleCapabilityBlock::new, BlockBehaviour.Properties.of().strength(2.0F, 1200.0F).lightLevel(value -> 15).emissiveRendering(FSUtils::getTrue).sound(SoundType.AMETHYST).mapColor(MapColor.COLOR_BLUE));
    public static final ItemBlockGroup<CreativeManaOutputBlock, BlockItem>
            CREATIVE_MANA_OUTPUT = simpleBlockItem("creative_mana_output", CreativeManaOutputBlock::new, BlockBehaviour.Properties.of());
    public static final ItemBlockGroup<WorldLocatorBlock, BlockItem>
            WORLD_LOCATOR = simpleBlockItem("world_locator", WorldLocatorBlock::new, BlockBehaviour.Properties.of());
    public static final ItemBlockGroup<TestBlock, BlockItem>
            TEST_BLOCK = simpleBlockItem("test_block", TestBlock::new, SoulCrystalBlock.getProperties(15, MapColor.COLOR_BLACK));
    public static final ItemBlockGroup<RotationWaterloggedItemBlock, BlockItem>
            SOUL_CRYSTAL_SHARD = simpleBlockItem("soul_crystal_shard", p -> new RotationWaterloggedItemBlock(p, RotationWaterloggedItemBlock.CRYSTAL_SHARD_SHAPE), BlockBehaviour.Properties.of().instabreak().noCollission().pushReaction(PushReaction.DESTROY).noOcclusion().sound(SoundType.LARGE_AMETHYST_BUD).mapColor(MapColor.WOOL));
    public static final ItemBlockGroup<ItemBlock, BlockItem>
            SOUL_CRYSTAL_DUST = dust("soul_crystal_dust", p -> p.sound(SoundType.SAND).mapColor(MapColor.WOOL));
    public static final ItemBlockGroup<Block, BlockItem>
            HYPERCUBE = simple("hypercube", BlockBehaviour.Properties.of().strength(1.5F, 6.0F).mapColor(MapColor.COLOR_BLACK));

    public static final DeferredItem<Item>
            CLAW_HAMMER_ITEM = ITEM.register("claw_hammer", () -> new CrowbarItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<Item>
            NETHERITE_CLAW_HAMMER_ITEM = ITEM.register("netherite_claw_hammer", () -> new CrowbarItem(Tiers.NETHERITE, new Item.Properties().fireResistant()));
    public static final DeferredItem<Item>
            CROWBAR_ITEM = ITEM.register("crowbar", () -> new CrowbarItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<Item>
            NETHERITE_CROWBAR_ITEM = ITEM.register("netherite_crowbar", () -> new CrowbarItem(Tiers.NETHERITE, new Item.Properties().fireResistant()));
    public static final DeferredItem<Item>
            TEST_ITEM = ITEM.registerSimpleItem("test_item");
    public static final DeferredItem<Item>
            MANA_ICON = ITEM.registerSimpleItem("mana_icon");
    public static final DeferredItem<Item>
            FE_ICON = ITEM.registerSimpleItem("fe_icon");

    public static void init() {
    }

}
