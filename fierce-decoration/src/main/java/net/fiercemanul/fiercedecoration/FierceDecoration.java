package net.fiercemanul.fiercedecoration;

import com.google.common.collect.ImmutableSet;
import net.fiercemanul.fiercedecoration.world.capabilities.InfinityWaterHandler;
import net.fiercemanul.fiercedecoration.data.registries.BlockBulkRegister;
import net.fiercemanul.fiercedecoration.server.commands.SitCommand;
import net.fiercemanul.fiercedecoration.world.entity.Seat;
import net.fiercemanul.fiercedecoration.data.FDItems;
import net.fiercemanul.fiercedecoration.world.level.block.CabinetBlock;
import net.fiercemanul.fiercedecoration.data.FDBlocks;
import net.fiercemanul.fiercedecoration.world.level.block.HalfPodzolBlock;
import net.fiercemanul.fiercedecoration.world.level.block.entity.CabinetBlockEntity;
import net.fiercemanul.fiercedecoration.world.level.block.entity.StarBlockEntity;
import net.fiercemanul.fiercesource.data.registries.FSCreativeModeTabs;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.items.wrapper.InvWrapper;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(FierceDecoration.MODID)
public class FierceDecoration {


    public static final String MODID = "fiercedecoration";
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, MODID);
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<StarBlockEntity>> STAR_BLOCK_ENTITY = BLOCK_ENTITIES.register(
            "star_block", () -> BlockEntityType.Builder.of(StarBlockEntity::new, FDBlocks.STAR_BLOCK.get()).build(null));
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, MODID);
    public static final DeferredHolder<EntityType<?>, EntityType<Seat>> SEAT = ENTITY_TYPES.register(
            "seat", () -> EntityType.Builder.<Seat>of(Seat::new, MobCategory.MISC).sized(0.6F, 0.6F).eyeHeight(0.0F).noSave()
                                            .clientTrackingRange(10).fireImmune().build("seat")
    );
    /*public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(Registries.MENU, MODID);*/
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> DECORATION_TAB = CREATIVE_MODE_TABS.register(
            "fiercedecoration_decoration",
            () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.fiercedecoration.decoration"))
                                 .withTabsBefore(FSCreativeModeTabs.MAIN_TAB.getKey())
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

    private static void applyDecorationBlocks(CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output output) {
        output.accept(FDItems.SOUL_CRYSTAL_ORNAMENT);
        output.accept(FDItems.PORTABLE_WORKSTATION);
        output.accept(FDItems.LAPTOP_TERMINAL);
        output.accept(FDItems.BOOK_AND_LAMP);
        output.accept(FDItems.LIGHT_TUBE);
        output.accept(FDItems.LIGHT_PLATE);
        output.accept(FDItems.GREEN_FUN_ROOF);
        output.accept(FDItems.FIREWOOD);
        output.accept(FDItems.FIREPLACE_HEART);
        output.accept(FDItems.ROCK_PATH);
        output.accept(FDItems.ITEM_FRAME_SHELL_THIN);
        output.accept(FDItems.ITEM_FRAME_SHELL_BIG);
        output.accept(FDItems.STAR_BLOCK);
        output.accept(FDItems.HEAVY_CHAINS);
        output.accept(FDItems.FOX_CARROT_SEED);
        output.accept(FDItems.FOX_CARROT);
        output.accept(FDItems.FOX_CARROT_SHEAF);
        output.accept(FDItems.FOX_CARROT_BASKET);
        output.accept(FDItems.NEO_FORGE);
        output.accept(FDItems.CRAFTING_PAD);
        output.accept(FDItems.CRAFTING_DESK);
        output.accept(FDItems.CRAFTING_BLOCK);
        output.accept(FDItems.WALL_FLOWER_POT_A);
        output.accept(FDItems.WALL_FLOWER_POT_B);
        output.accept(FDItems.WALL_FLOWER_POT_C);
        output.accept(FDItems.WALL_FLOWER_POT_D);
        output.accept(FDItems.WALL_FLOWER_POT_E);
        output.accept(FDItems.WALL_FLOWER_POT_F);
        output.accept(FDItems.HALF_GRASS_BLOCK);
        output.accept(FDItems.HALF_PODZOL);
        output.accept(FDItems.HALF_MYCELIUM);
        output.accept(FDItems.HALF_DIRT);
        output.accept(FDItems.HALF_PATH);
        output.accept(FDItems.WATERLOGGED_COBBLESTONE);
        output.accept(FDItems.ROTTEN_FLESH_BLOCK);
        output.accept(FDItems.GLOWSTONE_LAMP);
        output.accept(FDItems.REINFORCED_SMOOTH_GLOWSTONE);
        output.accept(Items.SEA_LANTERN);
        output.accept(FDItems.REINFORCED_SEA_LANTERN);
        output.accept(FDItems.RED_LAMP);
        output.accept(FDItems.GREEN_LAMP);
        output.accept(FDItems.BLUE_LAMP);
        output.accept(FDItems.YELLOW_LAMP);
        output.accept(FDItems.CYAN_LAMP);
        output.accept(FDItems.PURPLE_LAMP);
        output.accept(FDItems.RAINBOW_WOOL);
        output.accept(FDItems.RAINBOW_TERRACOTTA);
        output.accept(FDItems.RAINBOW_CONCRETE);
        output.accept(FDItems.RAINBOW_GLASS);
        output.accept(FDItems.RAINBOW_SEA_LANTERN);
        output.accept(FDItems.RAINBOW_REINFORCED_SEA_LANTERN);
        output.accept(FDItems.RAINBOW_LAMP);
        output.accept(FDItems.SMOOTH_OAK_PLANKS);
        output.accept(FDItems.SMOOTH_SPRUCE_PLANKS);
        output.accept(FDItems.SMOOTH_BIRCH_PLANKS);
        output.accept(FDItems.SMOOTH_JUNGLE_PLANKS);
        output.accept(FDItems.SMOOTH_ACACIA_PLANKS);
        output.accept(FDItems.SMOOTH_DARK_OAK_PLANKS);
        output.accept(FDItems.SMOOTH_MANGROVE_PLANKS);
        output.accept(FDItems.SMOOTH_BAMBOO_PLANKS);
        output.accept(FDItems.SMOOTH_CHERRY_PLANKS);
        output.accept(FDItems.SMOOTH_CRIMSON_PLANKS);
        output.accept(FDItems.SMOOTH_WARPED_PLANKS);
        output.accept(FDItems.OAK_PLANKS_AND_LIGHT_GRAY_CONCRETE);
        output.accept(FDItems.SPRUCE_PLANKS_AND_GRAY_CONCRETE);
        output.accept(FDItems.OAK_PLANKS_AND_SPRUCE_PLANKS);
        output.accept(FDItems.WHITE_CONCRETE_AND_LIGHT_GRAY_CONCRETE);
        output.accept(FDItems.DEEPSLATE_TILES_AND_SPRUCE_PLANKS);
        output.accept(FDItems.DEEPSLATE_TILES_AND_MANGROVE_PLANKS);
        output.accept(FDItems.DARK_PRISMARINE_AND_SPRUCE_PLANKS);
        output.accept(FDItems.DARK_PRISMARINE_AND_MANGROVE_PLANKS);
        output.accept(FDItems.BRICKS_AND_BIRCH_PLANKS);
        output.accept(FDItems.FAKE_HOPPER);
        output.accept(FDItems.FAKE_FURNACE);
        output.accept(FDItems.LIT_FAKE_FURNACE);
        output.accept(FDItems.FAKE_BLAST_FURNACE);
        output.accept(FDItems.LIT_FAKE_BLAST_FURNACE);
        output.accept(FDItems.FAKE_SMOKER);
        output.accept(FDItems.LIT_FAKE_SMOKER);
        output.accept(FDItems.FAKE_BARREL);
        output.accept(FDItems.FAKE_CAMPFIRE);
        output.accept(FDItems.LIT_FAKE_CAMPFIRE);
        output.accept(FDItems.LIT_FAKE_SOUL_CAMPFIRE);
        output.accept(FDItems.FAKE_CHEST);
        output.accept(FDItems.FAKE_CHISELED_BOOKSHELF);
        output.accept(FDItems.FAKE_LECTERN);
        output.accept(FDItems.FAKE_BEEHIVE);
        output.accept(FDItems.FAKE_IRON_BLOCK);
        output.accept(FDItems.FAKE_GOLD_BLOCK);
        output.accept(FDItems.FAKE_DIAMOND_BLOCK);
        output.accept(FDItems.FAKE_NETHERITE_BLOCK);
        output.accept(FDItems.FAKE_BEDROCK);
        output.accept(FDItems.TEXTURE_CHISELED_BOOKSHELF);
        output.accept(FDItems.TEXTURE_CHISELED_BOOKSHELF_TOP);
        output.accept(FDItems.TEXTURE_CHISELED_BOOKSHELF_SIDE);
        output.accept(FDItems.TEXTURE_LOOM);
        output.accept(FDItems.TEXTURE_BEEHIVE_TOP);
        output.accept(FDItems.TEXTURE_SMITHING_TABLE_BOTTOM);
        output.accept(FDItems.TEXTURE_COMPOSTER_BOTTOM);
        output.accept(FDItems.TEXTURE_BEE_NEST_TOP);
        output.accept(FDItems.TEXTURE_FURNACE);
        output.accept(FDItems.TEXTURE_FURNACE_TOP);
        output.accept(FDItems.TEXTURE_BLAST_FURNACE);
        output.accept(FDItems.TEXTURE_BLAST_FURNACE_TOP);
        output.accept(FDItems.TEXTURE_SMOKER);
        output.accept(FDItems.TEXTURE_SMITHING_TABLE_TOP);
        output.accept(FDItems.TEXTURE_LODESTONE);
        output.accept(FDItems.TEXTURE_LODESTONE_SIDE);
        output.accept(FDItems.TEXTURE_LODESTONE_TOP);
    }

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CabinetBlockEntity>> CABINET_BLOCK_ENTITY = BLOCK_ENTITIES.register(
            "cabinet", () -> {
                ImmutableSet.Builder<Block> validBlocksBuilder = new ImmutableSet.Builder<>();
                BlockBulkRegister.CABINETS.forEach(deferredBlock -> validBlocksBuilder.add(deferredBlock.get()));
                return new BlockEntityType<>(CabinetBlockEntity::new, validBlocksBuilder.build(), null);
            }
    );

    static {
        FDBlocks.init();
        FDItems.init();
        BlockBulkRegister.starRegister();
    }

    public FierceDecoration(IEventBus modEventBus, ModContainer modContainer) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        BLOCK_ENTITIES.register(modEventBus);
        ENTITY_TYPES.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);

        NeoForge.EVENT_BUS.addListener(this::registerCommandsEvent);
        NeoForge.EVENT_BUS.addListener(this::blockPlacedEvent);

        modEventBus.addListener(this::registerCapabilitiesEvent);
    }

    private void registerCommandsEvent(RegisterCommandsEvent event) {
        SitCommand.register(event.getDispatcher());
    }

    private void registerCapabilitiesEvent(RegisterCapabilitiesEvent event) {
        event.registerBlock(
                Capabilities.FluidHandler.BLOCK,
                (level, pos, state, blockEntity, context) -> InfinityWaterHandler.INSTANCE,
                FDBlocks.WATERLOGGED_COBBLESTONE.get()
        );
        BlockBulkRegister.CABINETS.forEach(deferredBlock -> event.registerBlock(
                Capabilities.ItemHandler.BLOCK,
                (level, pos, state, blockEntity, context) -> new InvWrapper(((CabinetBlock) state.getBlock()).getContainer(state, level, pos)),
                deferredBlock.get()
        ));
        event.registerItem(
                Capabilities.FluidHandler.ITEM,
                (stack, context) -> InfinityWaterHandler.INSTANCE,
                FDItems.WATERLOGGED_COBBLESTONE.get()
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


}
