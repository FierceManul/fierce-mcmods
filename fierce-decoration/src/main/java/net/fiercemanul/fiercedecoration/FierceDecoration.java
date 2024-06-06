package net.fiercemanul.fiercedecoration;

import com.google.common.collect.ImmutableMap;
import net.fiercemanul.fiercedecoration.capabilities.InfinityWaterHandler;
import net.fiercemanul.fiercedecoration.client.renderer.blockentity.StarBlockRender;
import net.fiercemanul.fiercedecoration.client.resources.model.StarBlockModel;
import net.fiercemanul.fiercedecoration.data.DataGen;
import net.fiercemanul.fiercedecoration.server.commands.SitCommand;
import net.fiercemanul.fiercedecoration.world.entity.Seat;
import net.fiercemanul.fiercedecoration.world.item.FDItems;
import net.fiercemanul.fiercedecoration.world.level.block.*;
import net.fiercemanul.fiercedecoration.world.level.block.entity.StarBlockEntity;
import net.fiercemanul.fiercesource.FierceSource;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.LinkedList;
import java.util.function.Function;

@Mod(FierceDecoration.MODID)
public class FierceDecoration {


    public static final String MODID = "fiercedecoration";
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, MODID);
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    //public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(Registries.MENU, MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<StarBlockEntity>> STAR_BLOCK_ENTITY = BLOCK_ENTITIES.register(
            "star_block", () -> BlockEntityType.Builder.of(StarBlockEntity::new, FDBlocks.STAR_BLOCK.get()).build(null));
    public static final DeferredHolder<EntityType<?>, EntityType<Seat>> SEAT = ENTITY_TYPES.register(
            "seat", () -> EntityType.Builder.<Seat>of(Seat::new, MobCategory.MISC).sized(0.6F, 0.6F).eyeHeight(0.0F).noSave().clientTrackingRange(10).fireImmune().build("seat"));


    
    public static final LinkedList<ItemLike> BUILDING_BLOCKS = new LinkedList<>();
    public static final LinkedList<ItemLike> COLORED_BLOCKS = new LinkedList<>();

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> DECORATION_TAB = CREATIVE_MODE_TABS.register(
            "fiercedecoration_decoration",
            () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.fiercedecoration.decoration"))
                                 .withTabsBefore(FierceSource.MAIN_TAB.getKey())
                                 .icon(() -> FDItems.LAPTOP_TERMINAL.get().getDefaultInstance())
                                 .displayItems(FierceDecoration::applyDecorationBlocks)
                                 .build()
    );
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BUILDING_TAB = CREATIVE_MODE_TABS.register(
            "fiercedecoration_building",
            () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.fiercedecoration.building"))
                                 .withTabsBefore(DECORATION_TAB.getKey())
                                 .icon(() -> FDItems.SMOOTH_OAK_PLANKS.get().getDefaultInstance())
                                 .displayItems((parameters, output) -> {
                                     for (ItemLike item : BUILDING_BLOCKS) output.accept(item);
                                 }).build()
    );
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> COLORED_TAB = CREATIVE_MODE_TABS.register(
            "fiercedecoration_colored",
            () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.fiercedecoration.colored"))
                                 .withTabsBefore(BUILDING_TAB.getKey())
                                 .icon(() -> FDItems.RAINBOW_GLASS.get().getDefaultInstance())
                                 .displayItems((parameters, output) -> {
                                     for (ItemLike item : COLORED_BLOCKS) output.accept(item);
                                 }).build()
    );

    private static final ImmutableMap<String, WoodType> WOOD_TYPE_MAP =
            new ImmutableMap.Builder<String, WoodType>()
                    .put("smooth_oak_planks", WoodType.OAK)
                    .put("smooth_spruce_planks", WoodType.SPRUCE)
                    .put("smooth_birch_planks", WoodType.BIRCH)
                    .put("smooth_jungle_planks", WoodType.JUNGLE)
                    .put("smooth_acacia_planks", WoodType.ACACIA)
                    .put("smooth_dark_oak_planks", WoodType.DARK_OAK)
                    .put("smooth_mangrove_planks", WoodType.MANGROVE)
                    .put("smooth_cherry_planks", WoodType.CHERRY)
                    .put("smooth_crimson_planks", WoodType.CRIMSON)
                    .put("smooth_warped_planks", WoodType.WARPED)
                    .build();

    private static final ImmutableMap<String, BlockSetType> BLOCK_SET_TYPE_MAP =
            new ImmutableMap.Builder<String, BlockSetType>()
                    .put("smooth_oak_planks", BlockSetType.OAK)
                    .put("smooth_spruce_planks", BlockSetType.SPRUCE)
                    .put("smooth_birch_planks", BlockSetType.BIRCH)
                    .put("smooth_jungle_planks", BlockSetType.JUNGLE)
                    .put("smooth_acacia_planks", BlockSetType.ACACIA)
                    .put("smooth_dark_oak_planks", BlockSetType.DARK_OAK)
                    .put("smooth_mangrove_planks", BlockSetType.MANGROVE)
                    .put("smooth_cherry_planks", BlockSetType.CHERRY)
                    .put("smooth_crimson_planks", BlockSetType.CRIMSON)
                    .put("smooth_warped_planks", BlockSetType.WARPED)
                    .build();

    static {
        FDBlocks.init();
        FDItems.init();
        //boolean isData = Launcher.INSTANCE.environment() instanceof IEnvironment environment && ((CommonLaunchHandler) environment.findLaunchHandler(environment.getProperty(IEnvironment.Keys.LAUNCHTARGET.get()).orElse("MISSING")).get()).isData();
        for (BlockMaterial blockMaterial : BlockMaterial.BLOCK_MATERIALS) {
            if (blockMaterial.isColored()) COLORED_BLOCKS.add(blockMaterial.getBlockItemLike());
            else BUILDING_BLOCKS.add(blockMaterial.getBlockItemLike());

            if (blockMaterial.allowLampInGlass) simplerReg(
                    blockMaterial, blockMaterial.getPath() + "_in_glass", LampInGlassBlock::new, blockMaterial.getLampInGlassProperties());
            if (blockMaterial.allowPillar) {
                DeferredBlock<Block> block = BLOCKS.registerBlock(blockMaterial.getPath() + "_pillar_12px", Pillar12PXBlock::new, blockMaterial.getPillarProperties());
                DeferredBlock<Block> block2 = BLOCKS.registerBlock(blockMaterial.getPath() + "_pillar_connector_12px", PillarConnector12PXBlock::new, blockMaterial.getTableProperties());
                DeferredBlock<Block> block3 = BLOCKS.registerBlock(blockMaterial.getPath() + "_pillar_8px", Pillar8PXBlock::new, blockMaterial.getPillarProperties());
                DeferredBlock<Block> block4 = BLOCKS.registerBlock(blockMaterial.getPath() + "_pillar_connector_8px", PillarConnector8PXBlock::new, blockMaterial.getTableProperties());
                DeferredBlock<Block> block5 = BLOCKS.registerBlock(blockMaterial.getPath() + "_pillar_6px", Pillar6PXBlock::new, blockMaterial.getPillarProperties());
                DeferredBlock<Block> block6 = BLOCKS.registerBlock(blockMaterial.getPath() + "_pillar_connector_6px", PillarConnector6PXBlock::new, blockMaterial.getTableProperties());
                DeferredBlock<Block> block7 = BLOCKS.registerBlock(blockMaterial.getPath() + "_pillar_4px", Pillar4PXBlock::new, blockMaterial.getPillarProperties());
                DeferredBlock<Block> block8 = BLOCKS.registerBlock(blockMaterial.getPath() + "_pillar_connector_4px", PillarConnector4PXBlock::new, blockMaterial.getTableProperties());
                DeferredItem<BlockItem> blockItem = ITEMS.registerSimpleBlockItem(block);
                DeferredItem<BlockItem> blockItem2 = ITEMS.registerSimpleBlockItem(block2);
                DeferredItem<BlockItem> blockItem3 = ITEMS.registerSimpleBlockItem(block3);
                DeferredItem<BlockItem> blockItem4 = ITEMS.registerSimpleBlockItem(block4);
                DeferredItem<BlockItem> blockItem5 = ITEMS.registerSimpleBlockItem(block5);
                DeferredItem<BlockItem> blockItem6 = ITEMS.registerSimpleBlockItem(block6);
                DeferredItem<BlockItem> blockItem7 = ITEMS.registerSimpleBlockItem(block7);
                DeferredItem<BlockItem> blockItem8 = ITEMS.registerSimpleBlockItem(block8);
                if (blockMaterial.isColored()) {
                    COLORED_BLOCKS.add(blockItem);
                    COLORED_BLOCKS.add(blockItem2);
                    COLORED_BLOCKS.add(blockItem3);
                    COLORED_BLOCKS.add(blockItem4);
                    COLORED_BLOCKS.add(blockItem5);
                    COLORED_BLOCKS.add(blockItem6);
                    COLORED_BLOCKS.add(blockItem7);
                    COLORED_BLOCKS.add(blockItem8);
                }
                else {
                    BUILDING_BLOCKS.add(blockItem);
                    BUILDING_BLOCKS.add(blockItem2);
                    BUILDING_BLOCKS.add(blockItem3);
                    BUILDING_BLOCKS.add(blockItem4);
                    BUILDING_BLOCKS.add(blockItem5);
                    BUILDING_BLOCKS.add(blockItem6);
                    BUILDING_BLOCKS.add(blockItem7);
                    BUILDING_BLOCKS.add(blockItem8);
                }
                DataGen.BLOCKS_AND_MATERIALS.put(block, blockMaterial);
                DataGen.BLOCKS_AND_MATERIALS.put(block2, blockMaterial);
                DataGen.BLOCKS_AND_MATERIALS.put(block3, blockMaterial);
                DataGen.BLOCKS_AND_MATERIALS.put(block4, blockMaterial);
                DataGen.BLOCKS_AND_MATERIALS.put(block5, blockMaterial);
                DataGen.BLOCKS_AND_MATERIALS.put(block6, blockMaterial);
                DataGen.BLOCKS_AND_MATERIALS.put(block7, blockMaterial);
                DataGen.BLOCKS_AND_MATERIALS.put(block8, blockMaterial);
            }
            if (blockMaterial.allowHorizonPanel) simplerReg(
                    blockMaterial, blockMaterial.getPath() + "_panel_horizon", HorizonPanelBlock::new, blockMaterial.getHorizonGlassPanelProperties());
            if (blockMaterial.allowWindowA) simplerReg(
                    blockMaterial, blockMaterial.getPath() + "_window_a", WindowTypeABlock::new, blockMaterial.getDefaultBlockProperties());
            if (blockMaterial.allowTable) simplerReg(
                    blockMaterial, blockMaterial.getPath() + "_table", TableBlock::new, blockMaterial.getTableProperties());
            if (blockMaterial.allowChair) simplerReg(
                    blockMaterial, blockMaterial.getPath() + "_chair", SimpleChairBlock::new, blockMaterial.getDefaultBlockProperties());
            if (blockMaterial.allowGardenChair) simplerReg(
                    blockMaterial, blockMaterial.getPath() + "_garden_chair", GardenChairBlock::new, blockMaterial.getDefaultBlockProperties());
            if (blockMaterial.allowWoolSofa) simplerReg(
                    blockMaterial, blockMaterial.getPath() + "_sofa", WoolSofaBlock::new, blockMaterial.getDefaultBlockProperties());
            if (blockMaterial.allowGuardrail) {
                String id = blockMaterial.getPath() + "_guardrail";
                DeferredBlock<Block> block = null;
                switch (blockMaterial.getMaterialType()) {
                    case WOOD -> {
                        if (blockMaterial.getModelType().equals(BlockMaterial.ModelType.LOG))
                            block = BLOCKS.registerBlock(id, WoodenGuardrailBlock::new, blockMaterial.getTableProperties());
                        else block = BLOCKS.registerBlock(id, WoodenGuardrailTypeBBlock::new, blockMaterial.getTableProperties());
                    }
                    case STONE -> block = BLOCKS.registerBlock(id, StoneGuardrailBlock::new, blockMaterial.getTableProperties());
                    case GLASS -> block = BLOCKS.registerBlock(id, GlassGuardrailBlock::new, blockMaterial.getTableProperties());
                }
                if (block != null) {
                    DeferredItem<BlockItem> blockItem = ITEMS.registerSimpleBlockItem(block);
                    if (blockMaterial.isColored()) COLORED_BLOCKS.add(blockItem);
                    else BUILDING_BLOCKS.add(blockItem);
                    DataGen.BLOCKS_AND_MATERIALS.put(block, blockMaterial);
                }
            }
            if (blockMaterial.allowPeepWindow) simplerReg(
                    blockMaterial, blockMaterial.getPath() + "_peep_window", PeepWindowBlock::new, blockMaterial.getPeepWindowProperties());
            if (blockMaterial.allowVanillaSlab) {
                DeferredBlock<Block> block = BLOCKS.register(blockMaterial.getPath() + "_stairs", () -> new StairBlock(blockMaterial.getMaterialBlock().defaultBlockState(), blockMaterial.getProperties()));
                DeferredBlock<Block> block2 = BLOCKS.registerBlock(blockMaterial.getPath() + "_slab", SlabBlock::new, blockMaterial.getProperties());
                DeferredItem<BlockItem> blockItem = ITEMS.registerSimpleBlockItem(block);
                DeferredItem<BlockItem> blockItem2 = ITEMS.registerSimpleBlockItem(block2);
                if (blockMaterial.isColored()) {
                    COLORED_BLOCKS.add(blockItem);
                    COLORED_BLOCKS.add(blockItem2);
                }
                else {
                    BUILDING_BLOCKS.add(blockItem);
                    BUILDING_BLOCKS.add(blockItem2);
                }
                DataGen.BLOCKS_AND_MATERIALS.put(block, blockMaterial);
                DataGen.BLOCKS_AND_MATERIALS.put(block2, blockMaterial);
            }
            if (blockMaterial.allowCutBlock) {
                DeferredBlock<Block> block = BLOCKS.registerBlock(blockMaterial.getPath() + "_one_cut_block", OneCutBlock::new, blockMaterial.getDefaultBlockProperties());
                DeferredBlock<Block> block2 = BLOCKS.registerBlock(blockMaterial.getPath() + "_thin_stairs", ThinStairBlock::new, blockMaterial.getDefaultBlockProperties());
                DeferredBlock<Block> block3 = BLOCKS.registerBlock(blockMaterial.getPath() + "_double_cut_block", DoubleCutBlock::new, blockMaterial.getDefaultBlockProperties());
                DeferredBlock<Block> block4 = BLOCKS.registerBlock(blockMaterial.getPath() + "_triple_cut_block", TripleCutBlock::new, blockMaterial.getDefaultBlockProperties());
                DeferredBlock<Block> block5 = BLOCKS.registerBlock(blockMaterial.getPath() + "_panel_4px", Panel4PXBlock::new, blockMaterial.getPanelProperties());
                DeferredBlock<Block> block6 = BLOCKS.registerBlock(blockMaterial.getPath() + "_panel_2px", Panel2PXBlock::new, blockMaterial.getPanelProperties());
                DeferredItem<BlockItem> blockItem = ITEMS.registerSimpleBlockItem(block);
                DeferredItem<BlockItem> blockItem2 = ITEMS.registerSimpleBlockItem(block2);
                DeferredItem<BlockItem> blockItem3 = ITEMS.registerSimpleBlockItem(block3);
                DeferredItem<BlockItem> blockItem4 = ITEMS.registerSimpleBlockItem(block4);
                DeferredItem<BlockItem> blockItem5 = ITEMS.registerSimpleBlockItem(block5);
                DeferredItem<BlockItem> blockItem6 = ITEMS.registerSimpleBlockItem(block6);
                if (blockMaterial.isColored()) {
                    COLORED_BLOCKS.add(blockItem);
                    COLORED_BLOCKS.add(blockItem2);
                    COLORED_BLOCKS.add(blockItem3);
                    COLORED_BLOCKS.add(blockItem4);
                    COLORED_BLOCKS.add(blockItem5);
                    COLORED_BLOCKS.add(blockItem6);
                }
                else {
                    BUILDING_BLOCKS.add(blockItem);
                    BUILDING_BLOCKS.add(blockItem2);
                    BUILDING_BLOCKS.add(blockItem3);
                    BUILDING_BLOCKS.add(blockItem4);
                    BUILDING_BLOCKS.add(blockItem5);
                    BUILDING_BLOCKS.add(blockItem6);
                }
                DataGen.BLOCKS_AND_MATERIALS.put(block, blockMaterial);
                DataGen.BLOCKS_AND_MATERIALS.put(block2, blockMaterial);
                DataGen.BLOCKS_AND_MATERIALS.put(block3, blockMaterial);
                DataGen.BLOCKS_AND_MATERIALS.put(block4, blockMaterial);
                DataGen.BLOCKS_AND_MATERIALS.put(block5, blockMaterial);
                DataGen.BLOCKS_AND_MATERIALS.put(block6, blockMaterial);
            }
            if (blockMaterial.vanillaPlanksLike) {
                DeferredBlock<Block> block = BLOCKS.registerBlock(blockMaterial.getPath() + "_fence", FenceBlock::new, blockMaterial.getProperties());
                DeferredBlock<Block> block2 = BLOCKS.register(blockMaterial.getPath() + "_fence_gate", () ->
                        new FenceGateBlock(WOOD_TYPE_MAP.get(blockMaterial.getPath()),
                                           blockMaterial.getProperties()
                                                        .forceSolidOn()));
                DeferredBlock<Block> block3 = BLOCKS.register(blockMaterial.getPath() + "_pressure_plate", () ->
                        new PressurePlateBlock(BLOCK_SET_TYPE_MAP.get(blockMaterial.getPath()),
                                               blockMaterial.getProperties()
                                                            .strength(0.5F)
                                                            .forceSolidOn()
                                                            .noCollission()
                                                            .pushReaction(PushReaction.DESTROY)));
                DeferredBlock<Block> block4 = BLOCKS.register(blockMaterial.getPath() + "_button", () ->
                        new ButtonBlock(BLOCK_SET_TYPE_MAP.get(blockMaterial.getPath()), 30,
                                        BlockBehaviour.Properties.of()
                                                                 .strength(0.5F)
                                                                 .noCollission()
                                                                 .pushReaction(PushReaction.DESTROY)));
                DeferredItem<BlockItem> blockItem = ITEMS.registerSimpleBlockItem(block);
                DeferredItem<BlockItem> blockItem2 = ITEMS.registerSimpleBlockItem(block2);
                DeferredItem<BlockItem> blockItem3 = ITEMS.registerSimpleBlockItem(block3);
                DeferredItem<BlockItem> blockItem4 = ITEMS.registerSimpleBlockItem(block4);
                if (blockMaterial.isColored()) {
                    COLORED_BLOCKS.add(blockItem);
                    COLORED_BLOCKS.add(blockItem2);
                    COLORED_BLOCKS.add(blockItem3);
                    COLORED_BLOCKS.add(blockItem4);
                }
                else {
                    BUILDING_BLOCKS.add(blockItem);
                    BUILDING_BLOCKS.add(blockItem2);
                    BUILDING_BLOCKS.add(blockItem3);
                    BUILDING_BLOCKS.add(blockItem4);
                }
                DataGen.BLOCKS_AND_MATERIALS.put(block, blockMaterial);
                DataGen.BLOCKS_AND_MATERIALS.put(block2, blockMaterial);
                DataGen.BLOCKS_AND_MATERIALS.put(block3, blockMaterial);
                DataGen.BLOCKS_AND_MATERIALS.put(block4, blockMaterial);
            }
        }
    }

    private static void simplerReg(BlockMaterial blockMaterial, String path, Function<BlockBehaviour.Properties, ? extends Block> blockFunc, BlockBehaviour.Properties properties) {
        DeferredBlock<Block> block = BLOCKS.registerBlock(path, blockFunc, properties);
        DeferredItem<BlockItem> blockItem = ITEMS.registerSimpleBlockItem(block);
        if (blockMaterial.isColored()) COLORED_BLOCKS.add(blockItem);
        else BUILDING_BLOCKS.add(blockItem);
        //if (FMLLoader.getLaunchHandler().isData())
        DataGen.BLOCKS_AND_MATERIALS.put(block, blockMaterial);
    }

    private static void applyDecorationBlocks(CreativeModeTab.ItemDisplayParameters pParameters, CreativeModeTab.Output pOutput) {
        pOutput.accept(FDItems.PORTABLE_WORKSTATION);
        pOutput.accept(FDItems.LAPTOP_TERMINAL);
        pOutput.accept(FDItems.BOOK_AND_LAMP);
        pOutput.accept(FDItems.LIGHT_TUBE);
        pOutput.accept(FDItems.LIGHT_PLATE);
        pOutput.accept(FDItems.GREEN_FUN_ROOF);
        pOutput.accept(FDItems.FIREWOOD);
        pOutput.accept(FDItems.FIREPLACE_HEART);
        pOutput.accept(FDItems.ROCK_PATH);
        pOutput.accept(FDItems.ITEM_FRAME_SHELL_THIN);
        pOutput.accept(FDItems.ITEM_FRAME_SHELL_BIG);
        pOutput.accept(FDItems.STAR_BLOCK);
        pOutput.accept(FDItems.HEAVY_CHAINS);
        pOutput.accept(FDItems.FOX_CARROT_SEED);
        pOutput.accept(FDItems.FOX_CARROT);
        pOutput.accept(FDItems.FOX_CARROT_SHEAF);
        pOutput.accept(FDItems.FOX_CARROT_BASKET);
        pOutput.accept(FDItems.NEO_FORGE);
        pOutput.accept(FDItems.CRAFTING_PAD);
        pOutput.accept(FDItems.CRAFTING_DESK);
        pOutput.accept(FDItems.CRAFTING_BLOCK);
        pOutput.accept(FDItems.WALL_FLOWER_POT_A);
        pOutput.accept(FDItems.WALL_FLOWER_POT_B);
        pOutput.accept(FDItems.WALL_FLOWER_POT_C);
        pOutput.accept(FDItems.WALL_FLOWER_POT_D);
        pOutput.accept(FDItems.WALL_FLOWER_POT_E);
        pOutput.accept(FDItems.WALL_FLOWER_POT_F);
        pOutput.accept(FDItems.HALF_GRASS_BLOCK);
        pOutput.accept(FDItems.HALF_PODZOL);
        pOutput.accept(FDItems.HALF_MYCELIUM);
        pOutput.accept(FDItems.HALF_DIRT);
        pOutput.accept(FDItems.HALF_PATH);
        pOutput.accept(FDItems.WATERLOGGED_COBBLESTONE);
        pOutput.accept(FDItems.ROTTEN_FLESH_BLOCK);
        pOutput.accept(FDItems.SMOOTH_GLOWSTONE);
        pOutput.accept(FDItems.REINFORCED_SMOOTH_GLOWSTONE);
        pOutput.accept(Items.SEA_LANTERN);
        pOutput.accept(FDItems.REINFORCED_SEA_LANTERN);
        pOutput.accept(FDItems.WHITE_SEA_LANTERN);
        pOutput.accept(FDItems.REINFORCED_WHITE_SEA_LANTERN);
        pOutput.accept(FDItems.ORANGE_SEA_LANTERN);
        pOutput.accept(FDItems.REINFORCED_ORANGE_SEA_LANTERN);
        pOutput.accept(FDItems.MAGENTA_SEA_LANTERN);
        pOutput.accept(FDItems.REINFORCED_MAGENTA_SEA_LANTERN);
        pOutput.accept(FDItems.LIGHT_BLUE_SEA_LANTERN);
        pOutput.accept(FDItems.REINFORCED_LIGHT_BLUE_SEA_LANTERN);
        pOutput.accept(FDItems.YELLOW_SEA_LANTERN);
        pOutput.accept(FDItems.REINFORCED_YELLOW_SEA_LANTERN);
        pOutput.accept(FDItems.LIME_SEA_LANTERN);
        pOutput.accept(FDItems.REINFORCED_LIME_SEA_LANTERN);
        pOutput.accept(FDItems.PINK_SEA_LANTERN);
        pOutput.accept(FDItems.REINFORCED_PINK_SEA_LANTERN);
        pOutput.accept(FDItems.GRAY_SEA_LANTERN);
        pOutput.accept(FDItems.REINFORCED_GRAY_SEA_LANTERN);
        pOutput.accept(FDItems.LIGHT_GRAY_SEA_LANTERN);
        pOutput.accept(FDItems.REINFORCED_LIGHT_GRAY_SEA_LANTERN);
        pOutput.accept(FDItems.CYAN_SEA_LANTERN);
        pOutput.accept(FDItems.REINFORCED_CYAN_SEA_LANTERN);
        pOutput.accept(FDItems.PURPLE_SEA_LANTERN);
        pOutput.accept(FDItems.REINFORCED_PURPLE_SEA_LANTERN);
        pOutput.accept(FDItems.BLUE_SEA_LANTERN);
        pOutput.accept(FDItems.REINFORCED_BLUE_SEA_LANTERN);
        pOutput.accept(FDItems.BROWN_SEA_LANTERN);
        pOutput.accept(FDItems.REINFORCED_BROWN_SEA_LANTERN);
        pOutput.accept(FDItems.GREEN_SEA_LANTERN);
        pOutput.accept(FDItems.REINFORCED_GREEN_SEA_LANTERN);
        pOutput.accept(FDItems.RED_SEA_LANTERN);
        pOutput.accept(FDItems.REINFORCED_RED_SEA_LANTERN);
        pOutput.accept(FDItems.BLACK_SEA_LANTERN);
        pOutput.accept(FDItems.REINFORCED_BLACK_SEA_LANTERN);
        pOutput.accept(FDItems.RED_LAMP);
        pOutput.accept(FDItems.GREEN_LAMP);
        pOutput.accept(FDItems.BLUE_LAMP);
        pOutput.accept(FDItems.YELLOW_LAMP);
        pOutput.accept(FDItems.CYAN_LAMP);
        pOutput.accept(FDItems.PURPLE_LAMP);
        pOutput.accept(FDItems.RAINBOW_WOOL);
        pOutput.accept(FDItems.RAINBOW_TERRACOTTA);
        pOutput.accept(FDItems.RAINBOW_CONCRETE);
        pOutput.accept(FDItems.RAINBOW_GLASS);
        pOutput.accept(FDItems.RAINBOW_SEA_LANTERN);
        pOutput.accept(FDItems.RAINBOW_REINFORCED_SEA_LANTERN);
        pOutput.accept(FDItems.RAINBOW_LAMP);
        pOutput.accept(FDItems.SMOOTH_OAK_PLANKS);
        pOutput.accept(FDItems.SMOOTH_SPRUCE_PLANKS);
        pOutput.accept(FDItems.SMOOTH_BIRCH_PLANKS);
        pOutput.accept(FDItems.SMOOTH_JUNGLE_PLANKS);
        pOutput.accept(FDItems.SMOOTH_ACACIA_PLANKS);
        pOutput.accept(FDItems.SMOOTH_DARK_OAK_PLANKS);
        pOutput.accept(FDItems.SMOOTH_MANGROVE_PLANKS);
        pOutput.accept(FDItems.SMOOTH_CRIMSON_PLANKS);
        pOutput.accept(FDItems.SMOOTH_WARPED_PLANKS);
        pOutput.accept(FDItems.OAK_PLANKS_AND_LIGHT_GRAY_CONCRETE);
        pOutput.accept(FDItems.SPRUCE_PLANKS_AND_GRAY_CONCRETE);
        pOutput.accept(FDItems.OAK_PLANKS_AND_SPRUCE_PLANKS);
        pOutput.accept(FDItems.WHITE_CONCRETE_AND_LIGHT_GRAY_CONCRETE);
        pOutput.accept(FDItems.DEEPSLATE_TILES_AND_SPRUCE_PLANKS);
        pOutput.accept(FDItems.DEEPSLATE_TILES_AND_MANGROVE_PLANKS);
        pOutput.accept(FDItems.DARK_PRISMARINE_AND_SPRUCE_PLANKS);
        pOutput.accept(FDItems.DARK_PRISMARINE_AND_MANGROVE_PLANKS);
        pOutput.accept(FDItems.BRICKS_AND_BIRCH_PLANKS);
        pOutput.accept(FDItems.FAKE_HOPPER);
        pOutput.accept(FDItems.FAKE_FURNACE);
        pOutput.accept(FDItems.LIT_FAKE_FURNACE);
        pOutput.accept(FDItems.FAKE_BLAST_FURNACE);
        pOutput.accept(FDItems.LIT_FAKE_BLAST_FURNACE);
        pOutput.accept(FDItems.FAKE_SMOKER);
        pOutput.accept(FDItems.LIT_FAKE_SMOKER);
        pOutput.accept(FDItems.FAKE_BARREL);
        pOutput.accept(FDItems.FAKE_CAMPFIRE);
        pOutput.accept(FDItems.LIT_FAKE_CAMPFIRE);
        pOutput.accept(FDItems.LIT_FAKE_SOUL_CAMPFIRE);
        pOutput.accept(FDItems.FAKE_CHEST);
        pOutput.accept(FDItems.FAKE_CHISELED_BOOKSHELF);
        pOutput.accept(FDItems.FAKE_LECTERN);
        pOutput.accept(FDItems.FAKE_BEEHIVE);
        pOutput.accept(FDItems.FAKE_IRON_BLOCK);
        pOutput.accept(FDItems.FAKE_GOLD_BLOCK);
        pOutput.accept(FDItems.FAKE_DIAMOND_BLOCK);
        pOutput.accept(FDItems.FAKE_NETHERITE_BLOCK);
        pOutput.accept(FDItems.FAKE_BEDROCK);
        pOutput.accept(FDItems.TEXTURE_CHISELED_BOOKSHELF);
        pOutput.accept(FDItems.TEXTURE_CHISELED_BOOKSHELF_TOP);
        pOutput.accept(FDItems.TEXTURE_CHISELED_BOOKSHELF_SIDE);
        pOutput.accept(FDItems.TEXTURE_LOOM);
        pOutput.accept(FDItems.TEXTURE_BEEHIVE_TOP);
        pOutput.accept(FDItems.TEXTURE_SMITHING_TABLE_BOTTOM);
        pOutput.accept(FDItems.TEXTURE_COMPOSTER_BOTTOM);
        pOutput.accept(FDItems.TEXTURE_BEE_NEST_TOP);
        pOutput.accept(FDItems.TEXTURE_FURNACE);
        pOutput.accept(FDItems.TEXTURE_FURNACE_TOP);
        pOutput.accept(FDItems.TEXTURE_BLAST_FURNACE);
        pOutput.accept(FDItems.TEXTURE_BLAST_FURNACE_TOP);
        pOutput.accept(FDItems.TEXTURE_SMOKER);
        pOutput.accept(FDItems.TEXTURE_SMITHING_TABLE_TOP);
        pOutput.accept(FDItems.TEXTURE_LODESTONE);
        pOutput.accept(FDItems.TEXTURE_LODESTONE_SIDE);
        pOutput.accept(FDItems.TEXTURE_LODESTONE_TOP);
    }

    public FierceDecoration(IEventBus modEventBus, ModContainer modContainer) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        BLOCK_ENTITIES.register(modEventBus);
        ENTITY_TYPES.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);

        NeoForge.EVENT_BUS.addListener(this::registerCommandsEvent);
        NeoForge.EVENT_BUS.addListener(this::blockPlacedEvent);

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::registerCapabilitiesEvent);
    }

    private void registerCommandsEvent(RegisterCommandsEvent event) {
        SitCommand.register(event.getDispatcher());
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        DataGen.BLOCKS_AND_MATERIALS.clear();
    }

    private void registerCapabilitiesEvent(RegisterCapabilitiesEvent event) {
        event.registerBlock(
                Capabilities.FluidHandler.BLOCK,
                (level, pos, state, blockEntity, context) -> InfinityWaterHandler.INSTANCE,
                FDBlocks.WATERLOGGED_COBBLESTONE.get()
        );
    }

    private void blockPlacedEvent(BlockEvent.EntityPlaceEvent event) {
        BlockState replacedBlock = event.getBlockSnapshot().getState();
        if (replacedBlock.getBlock() instanceof HalfPodzolBlock && event.getPlacedBlock().is(Blocks.SNOW)) {
            event.getLevel().setBlock(
                    event.getPos(),
                    replacedBlock.setValue(
                            HalfPodzolBlock.LAYERS,
                            Integer.min(replacedBlock.getValue(HalfPodzolBlock.LAYERS) + 1, 4)
                    ),
                    11
            );
        }
    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {


        @SubscribeEvent
        public static void registerColorHandlers(RegisterColorHandlersEvent.Block event) {
            event.register(
                    (pState, pLevel, pPos, pTintIndex) -> pLevel != null && pPos != null
                    ? BiomeColors.getAverageGrassColor(pLevel, pPos)
                    : GrassColor.getDefaultColor(),
                    FDBlocks.HALF_GRASS_BLOCK.get()
            );
        }

        @SubscribeEvent
        public static void registerColorHandlers(RegisterColorHandlersEvent.Item event) {
            event.register(
                    (pStack, pTintIndex) -> event.getBlockColors().getColor(((BlockItem)pStack.getItem()).getBlock().defaultBlockState(), null, null, pTintIndex),
                    FDItems.HALF_GRASS_BLOCK
            );
        }

        @SubscribeEvent
        public static void registerEntityRenderer(EntityRenderersEvent.RegisterRenderers register) {
            register.registerBlockEntityRenderer(STAR_BLOCK_ENTITY.get(), StarBlockRender::new);
            register.registerEntityRenderer(SEAT.get(), NoopRenderer::new);
        }

        @SubscribeEvent
        public static void modifyBakingResult(ModelEvent.ModifyBakingResult event) {
            ModelResourceLocation location = new ModelResourceLocation(MODID, "star_block", "inventory");
            event.getModels().put(location, new StarBlockModel(event.getModels().get(location)));
        }

    }
}
