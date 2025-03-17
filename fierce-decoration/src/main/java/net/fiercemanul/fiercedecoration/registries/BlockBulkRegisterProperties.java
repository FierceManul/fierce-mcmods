package net.fiercemanul.fiercedecoration.registries;

import com.google.common.collect.ImmutableMap;
import net.fiercemanul.fiercedecoration.world.level.block.*;
import net.fiercemanul.fiercesource.util.FSUtils;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.Map;
import java.util.Objects;

import static net.fiercemanul.fiercedecoration.FierceDecoration.BLOCKS;
import static net.fiercemanul.fiercedecoration.FierceDecoration.ITEMS;

public class BlockBulkRegisterProperties {


    private final MapColorConsumers colors = new MapColorConsumers();
    private final Map<String, WoodType> woodTypeMap =
            new ImmutableMap.Builder<String, WoodType>()
                    .put("smooth_oak_planks", WoodType.OAK)
                    .put("smooth_spruce_planks", WoodType.SPRUCE)
                    .put("smooth_birch_planks", WoodType.BIRCH)
                    .put("smooth_jungle_planks", WoodType.JUNGLE)
                    .put("smooth_acacia_planks", WoodType.ACACIA)
                    .put("smooth_dark_oak_planks", WoodType.DARK_OAK)
                    .put("smooth_mangrove_planks", WoodType.MANGROVE)
                    .put("smooth_bamboo_planks", WoodType.BAMBOO)
                    .put("smooth_cherry_planks", WoodType.CHERRY)
                    .put("smooth_crimson_planks", WoodType.CRIMSON)
                    .put("smooth_warped_planks", WoodType.WARPED)
                    .build();
    private final Map<String, BlockSetType> blockSetTypeMap =
            new ImmutableMap.Builder<String, BlockSetType>()
                    .put("smooth_oak_planks", BlockSetType.OAK)
                    .put("smooth_spruce_planks", BlockSetType.SPRUCE)
                    .put("smooth_birch_planks", BlockSetType.BIRCH)
                    .put("smooth_jungle_planks", BlockSetType.JUNGLE)
                    .put("smooth_acacia_planks", BlockSetType.ACACIA)
                    .put("smooth_dark_oak_planks", BlockSetType.DARK_OAK)
                    .put("smooth_mangrove_planks", BlockSetType.MANGROVE)
                    .put("smooth_bamboo_planks", BlockSetType.BAMBOO)
                    .put("smooth_cherry_planks", BlockSetType.CHERRY)
                    .put("smooth_crimson_planks", BlockSetType.CRIMSON)
                    .put("smooth_warped_planks", BlockSetType.WARPED)
                    .build();

    public final IBlockBulkRegisterProperty vanillaPlanks = (key, dataGen) -> {
        DeferredBlock<Block> block = BLOCKS.register(key.getPath() + "_stairs", () -> new StairBlock(key.getMaterialBlock().defaultBlockState(), key.defaultProperties.get()));
        DeferredBlock<Block> block2 = BLOCKS.registerBlock(key.getPath() + "_slab", SlabBlock::new, key.defaultProperties.get());
        DeferredBlock<Block> block3 = BLOCKS.registerBlock(key.getPath() + "_fence", FenceBlock::new, key.defaultProperties.get());
        DeferredBlock<Block> block4 = BLOCKS.register(key.getPath() + "_fence_gate", () -> new FenceGateBlock(Objects.requireNonNull(woodTypeMap.get(key.getPath())), key.defaultProperties.get().forceSolidOn()));
        DeferredBlock<Block> block5 = BLOCKS.register(key.getPath() + "_pressure_plate", () -> new PressurePlateBlock(Objects.requireNonNull(blockSetTypeMap.get(key.getPath())), key.defaultProperties.get().strength(0.5F).forceSolidOn().noCollission().pushReaction(PushReaction.DESTROY)));
        DeferredBlock<Block> block6 = BLOCKS.register(key.getPath() + "_button", () -> new ButtonBlock(Objects.requireNonNull(blockSetTypeMap.get(key.getPath())), 30, BlockBehaviour.Properties.of().strength(0.5F).noCollission().pushReaction(PushReaction.DESTROY)));
        DeferredItem<BlockItem> blockItem = ITEMS.registerSimpleBlockItem(block);
        DeferredItem<BlockItem> blockItem2 = ITEMS.registerSimpleBlockItem(block2);
        DeferredItem<BlockItem> blockItem3 = ITEMS.registerSimpleBlockItem(block3);
        DeferredItem<BlockItem> blockItem4 = ITEMS.registerSimpleBlockItem(block4);
        DeferredItem<BlockItem> blockItem5 = ITEMS.registerSimpleBlockItem(block5);
        DeferredItem<BlockItem> blockItem6 = ITEMS.registerSimpleBlockItem(block6);
        if (key.hasProperty(BlockBulkRegisterProperties.Colored.class)) {
            BlockBulkRegister.COLORED_BLOCKS.add(blockItem);
            BlockBulkRegister.COLORED_BLOCKS.add(blockItem2);
            BlockBulkRegister.COLORED_BLOCKS.add(blockItem3);
            BlockBulkRegister.COLORED_BLOCKS.add(blockItem4);
            BlockBulkRegister.COLORED_BLOCKS.add(blockItem5);
            BlockBulkRegister.COLORED_BLOCKS.add(blockItem6);
        }
        else {
            BlockBulkRegister.BUILDING_BLOCKS.add(blockItem);
            BlockBulkRegister.BUILDING_BLOCKS.add(blockItem2);
            BlockBulkRegister.BUILDING_BLOCKS.add(blockItem3);
            BlockBulkRegister.BUILDING_BLOCKS.add(blockItem4);
            BlockBulkRegister.BUILDING_BLOCKS.add(blockItem5);
            BlockBulkRegister.BUILDING_BLOCKS.add(blockItem6);
        }
        dataGen.accept(block, key);
        dataGen.accept(block2, key);
        dataGen.accept(block3, key);
        dataGen.accept(block4, key);
        dataGen.accept(block5, key);
        dataGen.accept(block6, key);
    };
    public final IBlockBulkRegisterProperty stairSlab = (key, dataGen) -> {
        DeferredBlock<Block> block = BLOCKS.register(key.getPath() + "_stairs", () -> new StairBlock(key.getMaterialBlock().defaultBlockState(), key.defaultProperties.get()));
        DeferredBlock<Block> block2 = BLOCKS.registerBlock(key.getPath() + "_slab", SlabBlock::new, key.defaultProperties.get());
        DeferredItem<BlockItem> blockItem = ITEMS.registerSimpleBlockItem(block);
        DeferredItem<BlockItem> blockItem2 = ITEMS.registerSimpleBlockItem(block2);
        if (key.hasProperty(BlockBulkRegisterProperties.Colored.class)) {
            BlockBulkRegister.COLORED_BLOCKS.add(blockItem);
            BlockBulkRegister.COLORED_BLOCKS.add(blockItem2);
        }
        else {
            BlockBulkRegister.BUILDING_BLOCKS.add(blockItem);
            BlockBulkRegister.BUILDING_BLOCKS.add(blockItem2);
        }
        dataGen.accept(block, key);
        dataGen.accept(block2, key);
    };
    public final IBlockBulkRegisterProperty cutBlock = (key, dataGen) -> {
        DeferredBlock<Block> block = BLOCKS.registerBlock(key.getPath() + "_one_cut_block", OneCutBlock::new, key.getMapColorHolder().apply(key.defaultProperties.get(), colors.top));
        DeferredBlock<Block> block2 = BLOCKS.registerBlock(key.getPath() + "_thin_stairs", ThinStairBlock::new, key.getMapColorHolder().apply(key.defaultProperties.get(), colors.top));
        DeferredBlock<Block> block3 = BLOCKS.registerBlock(key.getPath() + "_double_cut_block", DoubleCutBlock::new, key.getMapColorHolder().apply(key.defaultProperties.get(), colors.top));
        DeferredBlock<Block> block4 = BLOCKS.registerBlock(key.getPath() + "_triple_cut_block", TripleCutBlock::new, key.getMapColorHolder().apply(key.defaultProperties.get(), colors.top));
        DeferredBlock<Block> block5 = BLOCKS.registerBlock(key.getPath() + "_panel_4px", Panel4PXBlock::new, key.getMapColorHolder().apply(key.defaultProperties.get(), colors.panel));
        DeferredBlock<Block> block6 = BLOCKS.registerBlock(key.getPath() + "_panel_2px", Panel2PXBlock::new, key.getMapColorHolder().apply(key.defaultProperties.get(), colors.panel));
        DeferredItem<BlockItem> blockItem = ITEMS.registerSimpleBlockItem(block);
        DeferredItem<BlockItem> blockItem2 = ITEMS.registerSimpleBlockItem(block2);
        DeferredItem<BlockItem> blockItem3 = ITEMS.registerSimpleBlockItem(block3);
        DeferredItem<BlockItem> blockItem4 = ITEMS.registerSimpleBlockItem(block4);
        DeferredItem<BlockItem> blockItem5 = ITEMS.registerSimpleBlockItem(block5);
        DeferredItem<BlockItem> blockItem6 = ITEMS.registerSimpleBlockItem(block6);
        if (key.hasProperty(BlockBulkRegisterProperties.Colored.class)) {
            BlockBulkRegister.COLORED_BLOCKS.add(blockItem);
            BlockBulkRegister.COLORED_BLOCKS.add(blockItem2);
            BlockBulkRegister.COLORED_BLOCKS.add(blockItem3);
            BlockBulkRegister.COLORED_BLOCKS.add(blockItem4);
            BlockBulkRegister.COLORED_BLOCKS.add(blockItem5);
            BlockBulkRegister.COLORED_BLOCKS.add(blockItem6);
        }
        else {
            BlockBulkRegister.BUILDING_BLOCKS.add(blockItem);
            BlockBulkRegister.BUILDING_BLOCKS.add(blockItem2);
            BlockBulkRegister.BUILDING_BLOCKS.add(blockItem3);
            BlockBulkRegister.BUILDING_BLOCKS.add(blockItem4);
            BlockBulkRegister.BUILDING_BLOCKS.add(blockItem5);
            BlockBulkRegister.BUILDING_BLOCKS.add(blockItem6);
        }
        dataGen.accept(block, key);
        dataGen.accept(block2, key);
        dataGen.accept(block3, key);
        dataGen.accept(block4, key);
        dataGen.accept(block5, key);
        dataGen.accept(block6, key);
    };
    public final IBlockBulkRegisterProperty pillar = (key, dataGen) -> {
        DeferredBlock<Block> block = BLOCKS.registerBlock(key.getPath() + "_pillar_12px", Pillar12PXBlock::new, key.getMapColorHolder().apply(key.defaultProperties.get(), colors.axis));
        DeferredBlock<Block> block2 = BLOCKS.registerBlock(key.getPath() + "_pillar_connector_12px", PillarConnector12PXBlock::new, key.getMapColorHolder().apply(key.defaultProperties.get(), colors.side));
        DeferredBlock<Block> block3 = BLOCKS.registerBlock(key.getPath() + "_pillar_8px", Pillar8PXBlock::new, key.getMapColorHolder().apply(key.defaultProperties.get(), colors.axis));
        DeferredBlock<Block> block4 = BLOCKS.registerBlock(key.getPath() + "_pillar_connector_8px", PillarConnector8PXBlock::new, key.getMapColorHolder().apply(key.defaultProperties.get(), colors.side));
        DeferredBlock<Block> block5 = BLOCKS.registerBlock(key.getPath() + "_pillar_6px", Pillar6PXBlock::new, key.getMapColorHolder().apply(key.defaultProperties.get(), colors.axis));
        DeferredBlock<Block> block6 = BLOCKS.registerBlock(key.getPath() + "_pillar_connector_6px", PillarConnector6PXBlock::new, key.getMapColorHolder().apply(key.defaultProperties.get(), colors.side));
        DeferredBlock<Block> block7 = BLOCKS.registerBlock(key.getPath() + "_pillar_4px", Pillar4PXBlock::new, key.getMapColorHolder().apply(key.defaultProperties.get(), colors.axis));
        DeferredBlock<Block> block8 = BLOCKS.registerBlock(key.getPath() + "_pillar_connector_4px", PillarConnector4PXBlock::new, key.getMapColorHolder().apply(key.defaultProperties.get(), colors.side));
        DeferredItem<BlockItem> blockItem = ITEMS.registerSimpleBlockItem(block);
        DeferredItem<BlockItem> blockItem2 = ITEMS.registerSimpleBlockItem(block2);
        DeferredItem<BlockItem> blockItem3 = ITEMS.registerSimpleBlockItem(block3);
        DeferredItem<BlockItem> blockItem4 = ITEMS.registerSimpleBlockItem(block4);
        DeferredItem<BlockItem> blockItem5 = ITEMS.registerSimpleBlockItem(block5);
        DeferredItem<BlockItem> blockItem6 = ITEMS.registerSimpleBlockItem(block6);
        DeferredItem<BlockItem> blockItem7 = ITEMS.registerSimpleBlockItem(block7);
        DeferredItem<BlockItem> blockItem8 = ITEMS.registerSimpleBlockItem(block8);
        if (key.hasProperty(BlockBulkRegisterProperties.Colored.class)) {
            BlockBulkRegister.COLORED_BLOCKS.add(blockItem);
            BlockBulkRegister.COLORED_BLOCKS.add(blockItem2);
            BlockBulkRegister.COLORED_BLOCKS.add(blockItem3);
            BlockBulkRegister.COLORED_BLOCKS.add(blockItem4);
            BlockBulkRegister.COLORED_BLOCKS.add(blockItem5);
            BlockBulkRegister.COLORED_BLOCKS.add(blockItem6);
            BlockBulkRegister.COLORED_BLOCKS.add(blockItem7);
            BlockBulkRegister.COLORED_BLOCKS.add(blockItem8);
        }
        else {
            BlockBulkRegister.BUILDING_BLOCKS.add(blockItem);
            BlockBulkRegister.BUILDING_BLOCKS.add(blockItem2);
            BlockBulkRegister.BUILDING_BLOCKS.add(blockItem3);
            BlockBulkRegister.BUILDING_BLOCKS.add(blockItem4);
            BlockBulkRegister.BUILDING_BLOCKS.add(blockItem5);
            BlockBulkRegister.BUILDING_BLOCKS.add(blockItem6);
            BlockBulkRegister.BUILDING_BLOCKS.add(blockItem7);
            BlockBulkRegister.BUILDING_BLOCKS.add(blockItem8);
        }
        dataGen.accept(block, key);
        dataGen.accept(block2, key);
        dataGen.accept(block3, key);
        dataGen.accept(block4, key);
        dataGen.accept(block5, key);
        dataGen.accept(block6, key);
        dataGen.accept(block7, key);
        dataGen.accept(block8, key);
    };
    public final IBlockBulkRegisterProperty horizonPanel = new BlockBulkRegisterProperty(
            "panel_horizon", HorizonPanelBlock::new, registerKey -> registerKey.defaultProperties.get(), colors.top
    );
    public final IBlockBulkRegisterProperty woodenGuardrail = new BlockBulkRegisterProperty(
            "guardrail", WoodenGuardrailBlock::new, registerKey -> registerKey.defaultProperties.get(), colors.side
    );
    public final IBlockBulkRegisterProperty woodenGuardrailB = new BlockBulkRegisterProperty(
            "guardrail", WoodenGuardrailTypeBBlock::new, registerKey -> registerKey.defaultProperties.get(), colors.top
    );
    public final IBlockBulkRegisterProperty stoneGuardrail = new BlockBulkRegisterProperty(
            "guardrail", StoneGuardrailBlock::new, registerKey -> registerKey.defaultProperties.get(), colors.side
    );
    public final IBlockBulkRegisterProperty glassGuardrail = new BlockBulkRegisterProperty(
            "guardrail", GlassGuardrailBlock::new, registerKey -> registerKey.defaultProperties.get(), colors.none
    );
    public final IBlockBulkRegisterProperty windowA = new BlockBulkRegisterProperty(
            "window_a", WindowTypeABlock::new, registerKey -> registerKey.defaultProperties.get(), colors.side
    );
    public final IBlockBulkRegisterProperty windowB = (key, dataGen) -> {
        String path = key.getPath().replace("smooth_", "").replace("_planks", "");
        DeferredBlock<Block> block = BLOCKS.register(
                path + "_window_b",
                () -> new WindowTypeBBlock(
                        Objects.requireNonNull(blockSetTypeMap.get(key.getPath())),
                        BlockBehaviour.Properties.of()
                                                 .instrument(NoteBlockInstrument.HAT)
                                                 .strength(0.3F)
                                                 .sound(SoundType.GLASS)
                                                 .isViewBlocking(FSUtils::getFalse)
                                                 .noOcclusion()
                )
        );
        DeferredItem<BlockItem> blockItem = ITEMS.registerSimpleBlockItem(block);
        if (key.hasProperty(BlockBulkRegisterProperties.Colored.class)) BlockBulkRegister.COLORED_BLOCKS.add(blockItem);
        else BlockBulkRegister.BUILDING_BLOCKS.add(blockItem);
        dataGen.accept(block, key);
    };
    public final IBlockBulkRegisterProperty peepWindow = new BlockBulkRegisterProperty(
            "peep_window", PeepWindowBlock::new, registerKey -> registerKey.defaultProperties.get(), colors.panel
    );
    public final IBlockBulkRegisterProperty table = new BlockBulkRegisterProperty(
            "table", TableBlock::new, registerKey -> registerKey.defaultProperties.get(), colors.side
    );
    public final IBlockBulkRegisterProperty chair = new BlockBulkRegisterProperty(
            "chair", SimpleChairBlock::new, registerKey -> registerKey.defaultProperties.get(), colors.top
    );
    public final IBlockBulkRegisterProperty gardenChair = new BlockBulkRegisterProperty(
            "garden_chair", GardenChairBlock::new, registerKey -> registerKey.defaultProperties.get(), colors.top
    );
    public final IBlockBulkRegisterProperty woolSofa = new BlockBulkRegisterProperty(
            "sofa", WoolSofaBlock::new, registerKey -> registerKey.defaultProperties.get(), colors.top
    );
    public final IBlockBulkRegisterProperty lampInGlass = new BlockBulkRegisterProperty(
            "in_glass", LampInGlassBlock::new, registerKey -> registerKey.defaultProperties.get(), colors.facing
    );
    public final IBlockBulkRegisterProperty cabinet = (key, dataGen) -> {
        String path = key.getPath().replace("smooth_", "").replace("_planks", "");

        DeferredBlock<Block> block = BLOCKS.registerBlock(path + "_cabinet_a", CabinetTypeABlock::new, key.defaultProperties.get());
        DeferredBlock<Block> block2 = BLOCKS.registerBlock(path + "_cabinet_b", CabinetTypeBBlock::new, key.defaultProperties.get());
        DeferredBlock<Block> block3 = BLOCKS.registerBlock(path + "_cabinet_c", CabinetTypeCBlock::new, key.defaultProperties.get());
        DeferredBlock<Block> block4 = BLOCKS.registerBlock(path + "_cabinet_d", CabinetBlock::new, key.defaultProperties.get());

        DeferredItem<BlockItem> blockItem = ITEMS.registerSimpleBlockItem(block);
        DeferredItem<BlockItem> blockItem2 = ITEMS.registerSimpleBlockItem(block2);
        DeferredItem<BlockItem> blockItem3 = ITEMS.registerSimpleBlockItem(block3);
        DeferredItem<BlockItem> blockItem4 = ITEMS.registerSimpleBlockItem(block4);

        BlockBulkRegister.CABINETS.add(block);
        BlockBulkRegister.CABINETS.add(block2);
        BlockBulkRegister.CABINETS.add(block3);
        BlockBulkRegister.CABINETS.add(block4);
        BlockBulkRegister.BUILDING_BLOCKS.add(blockItem);
        BlockBulkRegister.BUILDING_BLOCKS.add(blockItem2);
        BlockBulkRegister.BUILDING_BLOCKS.add(blockItem3);
        BlockBulkRegister.BUILDING_BLOCKS.add(blockItem4);

        dataGen.accept(block, key);
        dataGen.accept(block2, key);
        dataGen.accept(block3, key);
        dataGen.accept(block4, key);
    };


    public final Colored colored = new Colored();

    public static final class Colored {}

}
