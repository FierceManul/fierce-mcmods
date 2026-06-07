package net.fiercemanul.fiercelive.data;

import net.fiercemanul.fiercelive.data.registries.BlockBulkRegister;
import net.fiercemanul.fiercesource.data.FSCreativeModeTabs;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.registries.DeferredHolder;

import static net.fiercemanul.fiercelive.data.registries.FLRegister.CREATIVE_MODE_TABS;

public final class FLCreativeModeTab {


    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> DECORATION_TAB = CREATIVE_MODE_TABS.register(
            "fiercelive_decoration",
            () -> CreativeModeTab.builder().title(Component.translatable("item_group.fiercelive.decoration"))
                                 .withTabsBefore(FSCreativeModeTabs.MAIN_TAB.getKey())
                                 .icon(() -> FLBlocks.SOUL_CRYSTAL_ORNAMENT.asItem().getDefaultInstance())
                                 .displayItems(FLCreativeModeTab::applyDecorationBlocks)
                                 .build()
    );
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BUILDING_TAB = CREATIVE_MODE_TABS.register(
            "fiercelive_building",
            () -> CreativeModeTab.builder().title(Component.translatable("item_group.fiercelive.building"))
                                 .withTabsBefore(DECORATION_TAB.getKey())
                                 .icon(() -> FLBlocks.SMOOTH_OAK_PLANKS.asItem().getDefaultInstance())
                                 .displayItems((parameters, output) -> {
                                     for (ItemLike item : BlockBulkRegister.BUILDING_BLOCKS) output.accept(item);
                                 }).build()
    );
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> COLORED_TAB = CREATIVE_MODE_TABS.register(
            "fiercelive_colored",
            () -> CreativeModeTab.builder().title(Component.translatable("item_group.fiercelive.colored"))
                                 .withTabsBefore(BUILDING_TAB.getKey())
                                 .icon(() -> FLBlocks.RAINBOW_GLASS.asItem().getDefaultInstance())
                                 .displayItems((parameters, output) -> {
                                     for (ItemLike item : BlockBulkRegister.COLORED_BLOCKS) output.accept(item);
                                 }).build()
    );

    private static void applyDecorationBlocks(CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output output) {
        output.accept(FLBlocks.SOUL_CRYSTAL_ORNAMENT);
        output.accept(FLBlocks.PORTABLE_WORKSTATION);
        output.accept(FLBlocks.LAPTOP_TERMINAL);
        output.accept(FLBlocks.BOOK_AND_LAMP);
        output.accept(FLBlocks.LIGHT_TUBE);
        output.accept(FLBlocks.LIGHT_PLATE);
        output.accept(FLBlocks.GLOW_PEARL);
        output.accept(FLItems.GLOW_PEARL_ARROW);
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
        output.accept(FLBlocks.SPIRAL_DEEPSLATE);
        output.accept(FLBlocks.SPIRAL_TUFF);
        output.accept(FLBlocks.SPIRAL_BLACKSTONE);
        output.accept(FLBlocks.SPIRAL_END_STONE);
        output.accept(FLBlocks.INTERLACE_BRICKS);
        output.accept(FLBlocks.WATERLOGGED_COBBLESTONE);
        output.accept(FLBlocks.VILLAGE_PATTERNED_TILES);
        output.accept(FLBlocks.VILLAGE_MOSAIC_TILES);
        output.accept(FLBlocks.BIG_FLOWER_POT);
        output.accept(FLBlocks.CONCRETE);
        output.accept(FLBlocks.CONCRETE_POWDER);
        output.accept(FLBlocks.GRAVEL_CONCRETE);
        output.accept(FLBlocks.GRAVEL_CONCRETE_POWDER);
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
        output.accept(FLBlocks.RAINBOW_GLASS_PANE);
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

        output.accept(FLItems.CROWBAR);
        output.accept(FLItems.NETHERITE_CROWBAR);
        output.accept(FLItems.GLASS_KNIFE);
        output.accept(FLItems.PUFFERFISH_ROD);
        output.accept(FLItems.METEOR_HAMMER);
        output.accept(FLItems.NETHERITE_METEOR_HAMMER);
        output.accept(FLItems.SACABAMBASPIS);
        output.accept(FLItems.PARROT);
        output.accept(FLItems.WOODEN_DOOR);
        output.accept(FLItems.IRON_DOOR);
        output.accept(FLItems.FOX_CARROT);
        output.accept(FLItems.FROSTED_BREAD);
        output.accept(FLItems.SWEET_BERRY_COOKIE);
        output.accept(FLItems.GLOW_BERRY_COOKIE);
        output.accept(FLItems.CARAMEL_COOKIE);
        output.accept(FLItems.CARAMEL);
        output.accept(FLItems.FOX_CARROT_SEED);
        output.accept(FLItems.RAINBOW_DYE);

        output.accept(FLBlocks.INFINITE_TNT);

        parameters.holders().lookup(Registries.ENCHANTMENT).flatMap(
                enchantmentLookup -> enchantmentLookup.get(FLEnchantments.POISON_ASPECT)
        ).ifPresent(enchantment -> output.accept(EnchantedBookItem.createForEnchantment(
                new EnchantmentInstance(enchantment, enchantment.value().getMaxLevel())))
        );
    }

    public static void init() {}

    private FLCreativeModeTab() {}

}
