package net.fiercemanul.fiercelive;

import com.google.common.collect.ImmutableSet;
import net.fiercemanul.fiercelive.data.FLBlocks;
import net.fiercemanul.fiercelive.data.FLItems;
import net.fiercemanul.fiercelive.data.registries.BlockBulkRegister;
import net.fiercemanul.fiercelive.data.registries.FLRegister;
import net.fiercemanul.fiercelive.server.commands.SitCommand;
import net.fiercemanul.fiercelive.world.capabilities.InfinityWaterHandler;
import net.fiercemanul.fiercelive.world.entity.Seat;
import net.fiercemanul.fiercelive.world.level.block.CabinetBlock;
import net.fiercemanul.fiercelive.world.level.block.HalfPodzolBlock;
import net.fiercemanul.fiercelive.world.level.block.entity.CabinetBlockEntity;
import net.fiercemanul.fiercesource.data.FSCreativeModeTabs;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTab;
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

@Mod(FierceLive.MODID)
public class FierceLive {


    public static final String MODID = "fiercelive";
    //public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<StarBlockEntity>> STAR_BLOCK_ENTITY = BLOCK_ENTITIES.register(
    //        "star_block", () -> BlockEntityType.Builder.of(StarBlockEntity::new, FLBlocks.STAR_BLOCK.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CabinetBlockEntity>> CABINET_BLOCK_ENTITY = FLRegister.BLOCK_ENTITIES.register(
            "cabinet", () -> {
                ImmutableSet.Builder<Block> validBlocksBuilder = new ImmutableSet.Builder<>();
                BlockBulkRegister.CABINETS.forEach(deferredBlock -> validBlocksBuilder.add(deferredBlock.get()));
                return new BlockEntityType<>(CabinetBlockEntity::new, validBlocksBuilder.build(), null);
            }
    );
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> DECORATION_TAB = FLRegister.CREATIVE_MODE_TABS.register(
            "fiercelive_decoration",
            () -> CreativeModeTab.builder().title(Component.translatable("item_group.fiercelive.decoration"))
                                 .withTabsBefore(FSCreativeModeTabs.MAIN_TAB.getKey())
                                 .icon(() -> FLBlocks.SOUL_CRYSTAL_ORNAMENT.asItem().getDefaultInstance())
                                 .displayItems(FierceLive::applyDecorationBlocks)
                                 .build()
    );
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BUILDING_TAB = FLRegister.CREATIVE_MODE_TABS.register(
            "fiercelive_building",
            () -> CreativeModeTab.builder().title(Component.translatable("item_group.fiercelive.building"))
                                 .withTabsBefore(DECORATION_TAB.getKey())
                                 .icon(() -> FLBlocks.SMOOTH_OAK_PLANKS.asItem().getDefaultInstance())
                                 .displayItems((parameters, output) -> {
                                     for (ItemLike item : BlockBulkRegister.BUILDING_BLOCKS) output.accept(item);
                                 }).build()
    );
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> COLORED_TAB = FLRegister.CREATIVE_MODE_TABS.register(
            "fiercelive_colored",
            () -> CreativeModeTab.builder().title(Component.translatable("item_group.fiercelive.colored"))
                                 .withTabsBefore(BUILDING_TAB.getKey())
                                 .icon(() -> FLBlocks.RAINBOW_GLASS.asItem().getDefaultInstance())
                                 .displayItems((parameters, output) -> {
                                     for (ItemLike item : BlockBulkRegister.COLORED_BLOCKS) output.accept(item);
                                 }).build()
    );
    public static final DeferredHolder<EntityType<?>, EntityType<Seat>> SEAT = FLRegister.ENTITY_TYPES.register(
            "seat", () -> EntityType.Builder.<Seat>of(Seat::new, MobCategory.MISC).sized(0.6F, 0.6F).eyeHeight(0.0F).noSave()
                                            .clientTrackingRange(10).fireImmune().build("seat")
    );

    private static void applyDecorationBlocks(CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output output) {
        output.accept(FLBlocks.SOUL_CRYSTAL_ORNAMENT);
        output.accept(FLBlocks.PORTABLE_WORKSTATION);
        output.accept(FLBlocks.LAPTOP_TERMINAL);
        output.accept(FLBlocks.BOOK_AND_LAMP);
        output.accept(FLBlocks.LIGHT_TUBE);
        output.accept(FLBlocks.LIGHT_PLATE);
        output.accept(FLBlocks.GREEN_FUN_ROOF);
        output.accept(FLBlocks.FIREWOOD);
        output.accept(FLBlocks.FIREPLACE_HEART);
        output.accept(FLBlocks.ROCK_PATH);
        output.accept(FLBlocks.ITEM_FRAME_SHELL_THIN);
        output.accept(FLBlocks.ITEM_FRAME_SHELL_BIG);
        //output.accept(FLBlocks.STAR_BLOCK);
        output.accept(FLBlocks.HEAVY_CHAINS);
        output.accept(FLBlocks.FOX_CARROT_SHEAF);
        output.accept(FLBlocks.FOX_CARROT_BASKET);
        output.accept(FLBlocks.NEO_FORGE);
        output.accept(FLBlocks.CRAFTING_PAD);
        output.accept(FLBlocks.CRAFTING_DESK);
        output.accept(FLBlocks.CRAFTING_BLOCK);
        output.accept(FLBlocks.A_WALL_FLOWER_POT);
        output.accept(FLBlocks.B_WALL_FLOWER_POT);
        output.accept(FLBlocks.C_WALL_FLOWER_POT);
        output.accept(FLBlocks.D_WALL_FLOWER_POT);
        output.accept(FLBlocks.E_WALL_FLOWER_POT);
        output.accept(FLBlocks.F_WALL_FLOWER_POT);
        output.accept(FLBlocks.HALF_GRASS_BLOCK);
        output.accept(FLBlocks.HALF_PODZOL);
        output.accept(FLBlocks.HALF_MYCELIUM);
        output.accept(FLBlocks.HALF_DIRT);
        output.accept(FLBlocks.HALF_DIRT_PATH);
        output.accept(FLBlocks.IRON_FRAME);
        output.accept(FLBlocks.IRON_CORRIDOR);
        output.accept(FLBlocks.IRON_CORRIDOR_SLAB);
        output.accept(FLBlocks.IRON_CORRIDOR_STAIRS);
        output.accept(FLBlocks.IRON_GUARDRAIL);
        output.accept(FLBlocks.IRON_LADDER);
        output.accept(FLBlocks.IRON_SCAFFOLDING);
        output.accept(FLBlocks.SPIRAL_STONE);
        output.accept(FLBlocks.DEEPSLATE_SPIRAL_STONE);
        output.accept(FLBlocks.WATERLOGGED_COBBLESTONE);
        output.accept(FLBlocks.SMOOTH_OAK_PLANKS);
        output.accept(FLBlocks.SMOOTH_SPRUCE_PLANKS);
        output.accept(FLBlocks.SMOOTH_BIRCH_PLANKS);
        output.accept(FLBlocks.SMOOTH_JUNGLE_PLANKS);
        output.accept(FLBlocks.SMOOTH_ACACIA_PLANKS);
        output.accept(FLBlocks.SMOOTH_DARK_OAK_PLANKS);
        output.accept(FLBlocks.SMOOTH_MANGROVE_PLANKS);
        output.accept(FLBlocks.SMOOTH_BAMBOO_PLANKS);
        output.accept(FLBlocks.SMOOTH_CHERRY_PLANKS);
        output.accept(FLBlocks.SMOOTH_CRIMSON_PLANKS);
        output.accept(FLBlocks.SMOOTH_WARPED_PLANKS);
        output.accept(FLBlocks.OAK_PLANKS_AND_LIGHT_GRAY_CONCRETE);
        output.accept(FLBlocks.SPRUCE_PLANKS_AND_GRAY_CONCRETE);
        output.accept(FLBlocks.OAK_PLANKS_AND_SPRUCE_PLANKS);
        output.accept(FLBlocks.WHITE_CONCRETE_AND_LIGHT_GRAY_CONCRETE);
        output.accept(FLBlocks.DEEPSLATE_TILES_AND_SPRUCE_PLANKS);
        output.accept(FLBlocks.DEEPSLATE_TILES_AND_MANGROVE_PLANKS);
        output.accept(FLBlocks.DARK_PRISMARINE_AND_SPRUCE_PLANKS);
        output.accept(FLBlocks.DARK_PRISMARINE_AND_MANGROVE_PLANKS);
        output.accept(FLBlocks.BRICKS_AND_BIRCH_PLANKS);
        output.accept(FLBlocks.GLOWSTONE_LAMP);
        output.accept(FLBlocks.REINFORCED_GLOWSTONE_LAMP);
        output.accept(FLBlocks.REINFORCED_SEA_LANTERN);
        output.accept(FLBlocks.RED_LAMP);
        output.accept(FLBlocks.GREEN_LAMP);
        output.accept(FLBlocks.BLUE_LAMP);
        output.accept(FLBlocks.YELLOW_LAMP);
        output.accept(FLBlocks.CYAN_LAMP);
        output.accept(FLBlocks.PURPLE_LAMP);
        output.accept(FLBlocks.RAINBOW_WOOL);
        output.accept(FLBlocks.RAINBOW_TERRACOTTA);
        output.accept(FLBlocks.RAINBOW_CONCRETE);
        output.accept(FLBlocks.RAINBOW_GLASS);
        output.accept(FLBlocks.RAINBOW_SEA_LANTERN);
        output.accept(FLBlocks.RAINBOW_REINFORCED_SEA_LANTERN);
        output.accept(FLBlocks.RAINBOW_LAMP);
        output.accept(FLBlocks.FAKE_HOPPER);
        output.accept(FLBlocks.FAKE_FURNACE);
        output.accept(FLBlocks.LIT_FAKE_FURNACE);
        output.accept(FLBlocks.FAKE_BLAST_FURNACE);
        output.accept(FLBlocks.LIT_FAKE_BLAST_FURNACE);
        output.accept(FLBlocks.FAKE_SMOKER);
        output.accept(FLBlocks.LIT_FAKE_SMOKER);
        output.accept(FLBlocks.FAKE_BARREL);
        output.accept(FLBlocks.FAKE_CAMPFIRE);
        output.accept(FLBlocks.LIT_FAKE_CAMPFIRE);
        output.accept(FLBlocks.LIT_FAKE_SOUL_CAMPFIRE);
        output.accept(FLBlocks.FAKE_CHEST);
        output.accept(FLBlocks.FAKE_CHISELED_BOOKSHELF);
        output.accept(FLBlocks.FAKE_LECTERN);
        output.accept(FLBlocks.FAKE_BEEHIVE);
        output.accept(FLBlocks.FAKE_IRON_BLOCK);
        output.accept(FLBlocks.FAKE_GOLD_BLOCK);
        output.accept(FLBlocks.FAKE_DIAMOND_BLOCK);
        output.accept(FLBlocks.FAKE_NETHERITE_BLOCK);
        output.accept(FLBlocks.FAKE_BEDROCK);
        output.accept(FLBlocks.TEXTURE_CHISELED_BOOKSHELF);
        output.accept(FLBlocks.TEXTURE_CHISELED_BOOKSHELF_TOP);
        output.accept(FLBlocks.TEXTURE_CHISELED_BOOKSHELF_SIDE);
        output.accept(FLBlocks.TEXTURE_LOOM);
        output.accept(FLBlocks.TEXTURE_BEEHIVE_TOP);
        output.accept(FLBlocks.TEXTURE_SMITHING_TABLE_BOTTOM);
        output.accept(FLBlocks.TEXTURE_COMPOSTER_BOTTOM);
        output.accept(FLBlocks.TEXTURE_BEE_NEST_TOP);
        output.accept(FLBlocks.TEXTURE_FURNACE);
        output.accept(FLBlocks.TEXTURE_FURNACE_TOP);
        output.accept(FLBlocks.TEXTURE_BLAST_FURNACE);
        output.accept(FLBlocks.TEXTURE_BLAST_FURNACE_TOP);
        output.accept(FLBlocks.TEXTURE_SMOKER);
        output.accept(FLBlocks.TEXTURE_SMITHING_TABLE_TOP);
        output.accept(FLBlocks.TEXTURE_LODESTONE);
        output.accept(FLBlocks.TEXTURE_LODESTONE_SIDE);
        output.accept(FLBlocks.TEXTURE_LODESTONE_TOP);
        output.accept(FLBlocks.MEAT_BLOCK);
        output.accept(FLBlocks.ROTTEN_FLESH_BLOCK);

        output.accept(FLItems.CROWBAR_ITEM);
        output.accept(FLItems.NETHERITE_CROWBAR_ITEM);
        output.accept(FLItems.CLAW_HAMMER_ITEM);
        output.accept(FLItems.NETHERITE_CLAW_HAMMER_ITEM);
        output.accept(FLItems.FOX_CARROT);
        output.accept(FLItems.RAINBOW_DYE);
        output.accept(FLItems.FOX_CARROT_SEED);
    }

    static {
        FLBlocks.init();
        FLItems.init();
        BlockBulkRegister.starRegister();
    }

    public FierceLive(IEventBus modEventBus, ModContainer modContainer) {
        FLRegister.BLOCKS.register(modEventBus);
        FLRegister.ITEMS.register(modEventBus);
        FLRegister.BLOCK_ENTITIES.register(modEventBus);
        FLRegister.ENTITY_TYPES.register(modEventBus);
        FLRegister.CREATIVE_MODE_TABS.register(modEventBus);

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
                FLBlocks.WATERLOGGED_COBBLESTONE.get()
        );
        BlockBulkRegister.CABINETS.forEach(deferredBlock -> event.registerBlock(
                Capabilities.ItemHandler.BLOCK,
                (level, pos, state, blockEntity, context) -> new InvWrapper(((CabinetBlock) state.getBlock()).getContainer(state, level, pos)),
                deferredBlock.get()
        ));
        event.registerItem(
                Capabilities.FluidHandler.ITEM,
                (stack, context) -> InfinityWaterHandler.INSTANCE,
                FLBlocks.WATERLOGGED_COBBLESTONE
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
