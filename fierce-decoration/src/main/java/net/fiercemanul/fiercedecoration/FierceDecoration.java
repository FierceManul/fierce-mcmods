package net.fiercemanul.fiercedecoration;

import com.google.common.collect.ImmutableSet;
import net.fiercemanul.fiercedecoration.capabilities.InfinityWaterHandler;
import net.fiercemanul.fiercedecoration.client.renderer.blockentity.StarBlockRender;
import net.fiercemanul.fiercedecoration.client.resources.model.StarBlockModel;
import net.fiercemanul.fiercedecoration.registries.BlockBulkRegister;
import net.fiercemanul.fiercedecoration.server.commands.SitCommand;
import net.fiercemanul.fiercedecoration.world.entity.Seat;
import net.fiercemanul.fiercedecoration.world.item.FDItems;
import net.fiercemanul.fiercedecoration.world.level.block.*;
import net.fiercemanul.fiercedecoration.world.level.block.entity.CabinetBlockEntity;
import net.fiercemanul.fiercedecoration.world.level.block.entity.StarBlockEntity;
import net.fiercemanul.fiercesource.FierceSource;
import net.fiercemanul.fiercesource.util.FSUtils;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.client.resources.model.BakedModel;
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
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.data.loading.DatagenModLoader;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.items.wrapper.InvWrapper;
import net.neoforged.neoforge.registries.*;

import java.util.Map;
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
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CabinetBlockEntity>> CABINET_BLOCK_ENTITY = BLOCK_ENTITIES.register(
            "cabinet", () -> {
                ImmutableSet.Builder<Block> validBlocksBuilder = new ImmutableSet.Builder<>();
                BlockBulkRegister.CABINETS.forEach(deferredBlock -> validBlocksBuilder.add(deferredBlock.get()));
                return new BlockEntityType<>(CabinetBlockEntity::new, validBlocksBuilder.build(), null);
            });
    public static final DeferredHolder<EntityType<?>, EntityType<Seat>> SEAT = ENTITY_TYPES.register(
            "seat", () -> EntityType.Builder.<Seat>of(Seat::new, MobCategory.MISC).sized(0.6F, 0.6F).eyeHeight(0.0F).noSave().clientTrackingRange(10).fireImmune().build("seat"));
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> DECORATION_TAB = CREATIVE_MODE_TABS.register(
            "fiercedecoration_decoration",
            () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.fiercedecoration.decoration"))
                                 .withTabsBefore(FierceSource.MAIN_TAB.getKey())
                                 .icon(() -> FDItems.SOUL_CRYSTAL_ORNAMENT.get().getDefaultInstance())
                                 .displayItems(FierceDecoration::applyDecorationBlocks)
                                 .build()
    );
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BUILDING_TAB = CREATIVE_MODE_TABS.register(
            "fiercedecoration_building",
            () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.fiercedecoration.building"))
                                 .withTabsBefore(DECORATION_TAB.getKey())
                                 .icon(() -> FDItems.SMOOTH_OAK_PLANKS.get().getDefaultInstance())
                                 .displayItems((parameters, output) -> {
                                     for (ItemLike item : BlockBulkRegister.BUILDING_BLOCKS) output.accept(item);
                                 }).build()
    );
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> COLORED_TAB = CREATIVE_MODE_TABS.register(
            "fiercedecoration_colored",
            () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.fiercedecoration.colored"))
                                 .withTabsBefore(BUILDING_TAB.getKey())
                                 .icon(() -> FDItems.RAINBOW_GLASS.get().getDefaultInstance())
                                 .displayItems((parameters, output) -> {
                                     for (ItemLike item : BlockBulkRegister.COLORED_BLOCKS) output.accept(item);
                                 }).build()
    );

    static {
        FDBlocks.loadClass();
        FDItems.loadClass();
        BlockBulkRegister.starRegister();
    }

    private static void applyDecorationBlocks(CreativeModeTab.ItemDisplayParameters pParameters, CreativeModeTab.Output pOutput) {
        pOutput.accept(FDItems.SOUL_CRYSTAL_ORNAMENT);
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
        pOutput.accept(FDItems.SMOOTH_BAMBOO_PLANKS);
        pOutput.accept(FDItems.SMOOTH_CHERRY_PLANKS);
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

    private void commonSetup(FMLCommonSetupEvent event) {
        BlockBulkRegister.clean();
    }

    private void registerCapabilitiesEvent(RegisterCapabilitiesEvent event) {
        event.registerBlock(
                Capabilities.FluidHandler.BLOCK,
                (level, pos, state, blockEntity, context) -> InfinityWaterHandler.INSTANCE,
                FDBlocks.WATERLOGGED_COBBLESTONE.get()
        );
        BlockBulkRegister.CABINETS.forEach(deferredBlock -> event.registerBlock(
                Capabilities.ItemHandler.BLOCK,
                (level, pos, state, blockEntity, context) -> new InvWrapper(((CabinetBlock)state.getBlock()).getContainer(state, level, pos)),
                deferredBlock.get()
        ));
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
            Map<ModelResourceLocation, BakedModel> models = event.getModels();
            ModelResourceLocation location = ModelResourceLocation.inventory(FDBlocks.STAR_BLOCK.getId());
            models.put(location, new StarBlockModel(models.get(location)));
        }

    }
}
