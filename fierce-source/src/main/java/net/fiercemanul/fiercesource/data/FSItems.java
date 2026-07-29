package net.fiercemanul.fiercesource.data;

import net.fiercemanul.fiercesource.world.item.TestItem;
import net.fiercemanul.fiercesource.world.level.block.DecorationBlock;
import net.fiercemanul.fiercesource.world.level.block.RotationDecorationWaterloggedBlock;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

import static net.fiercemanul.fiercesource.data.registries.FCRegistries.*;

public final class FSItems {


    public static final DeferredBlock<RotationDecorationWaterloggedBlock>
            SOUL_CRYSTAL_SHARD = shard("soul_crystal_shard", p -> p.noOcclusion().sound(SoundType.LARGE_AMETHYST_BUD).mapColor(MapColor.WOOL));
    public static final DeferredBlock<DecorationBlock>
            SOUL_CRYSTAL_DUST = dust("soul_crystal_dust", p -> p.sound(SoundType.SAND).mapColor(MapColor.WOOL));
    public static final DeferredBlock<Block>
            HYPERCUBE = simpleBlock("hypercube", BlockBehaviour.Properties.of().strength(1.5F, 6.0F).mapColor(MapColor.COLOR_BLACK));

    public static final DeferredItem<Item>
            TEST_ITEM = ITEMS.registerItem("test_item", TestItem::new);

    public static final DeferredItem<Item>
            MANA_ICON = ITEMS.registerSimpleItem("mana_icon");
    public static final DeferredItem<Item>
            FE_ICON = ITEMS.registerSimpleItem("fe_icon");

    public static void init() {
    }
}
