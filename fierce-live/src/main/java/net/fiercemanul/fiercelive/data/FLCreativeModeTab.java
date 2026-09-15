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

import static net.fiercemanul.fiercelive.data.FLBlocks.*;
import static net.fiercemanul.fiercelive.data.FLItems.*;
import static net.fiercemanul.fiercelive.data.registries.FLRegister.CREATIVE_MODE_TABS;

public final class FLCreativeModeTab {


    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> DECORATION_TAB = CREATIVE_MODE_TABS.register(
            "fiercelive_decoration",
            () -> CreativeModeTab.builder().title(Component.translatable("item_group.fiercelive.goods"))
                                 .withTabsBefore(FSCreativeModeTabs.MAIN_TAB.getKey())
                                 .icon(SOUL_CRYSTAL_ORNAMENT::toStack)
                                 .displayItems(FLCreativeModeTab::applyDecorationBlocks)
                                 .build()
    );
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BUILDING_TAB = CREATIVE_MODE_TABS.register(
            "fiercelive_building",
            () -> CreativeModeTab.builder().title(Component.translatable("item_group.fiercelive.templateite"))
                                 .withTabsBefore(DECORATION_TAB.getKey())
                                 .icon(SMOOTH_OAK_PLANKS::toStack)
                                 .displayItems((parameters, output) -> {
                                     for (ItemLike item : BlockBulkRegister.BUILDING_BLOCKS) output.accept(item);
                                 }).build()
    );
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> COLORED_TAB = CREATIVE_MODE_TABS.register(
            "fiercelive_colored",
            () -> CreativeModeTab.builder().title(Component.translatable("item_group.fiercelive.colored"))
                                 .withTabsBefore(BUILDING_TAB.getKey())
                                 .icon(RAINBOW_GLASS::toStack)
                                 .displayItems((parameters, output) -> {
                                     for (ItemLike item : BlockBulkRegister.COLORED_BLOCKS) output.accept(item);
                                 }).build()
    );

    private static void applyDecorationBlocks(CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output output) {
        output.accept(SOUL_CRYSTAL_ORNAMENT);
        output.accept(PORTABLE_WORKSTATION);
        output.accept(LAPTOP_TERMINAL);
        output.accept(BOOK_AND_LAMP);
        output.accept(LIGHT_TUBE);
        output.accept(LIGHT_PLATE);
        output.accept(GLOW_PEARL);
        output.accept(FLItems.GLOW_PEARL_ARROW);
        output.accept(GREEN_FUN_ROOF);
        output.accept(FIREWOOD);
        output.accept(FIREPLACE_HEART);
        output.accept(ROCK_PATH);
        output.accept(ITEM_FRAME_SHELL_THIN);
        output.accept(ITEM_FRAME_SHELL_BIG);
        //output.accept(FLBlocks.STAR_BLOCK);
        output.accept(HEAVY_CHAINS);
        output.accept(FOX_CARROT_SHEAF);
        output.accept(FOX_CARROT_BASKET);
        output.accept(NEO_FORGE);
        output.accept(CRAFTING_PAD);
        output.accept(CRAFTING_DESK);
        output.accept(CRAFTING_BLOCK);
        output.accept(A_WALL_FLOWER_POT);
        output.accept(B_WALL_FLOWER_POT);
        output.accept(C_WALL_FLOWER_POT);
        output.accept(D_WALL_FLOWER_POT);
        output.accept(E_WALL_FLOWER_POT);
        output.accept(F_WALL_FLOWER_POT);
        output.accept(HALF_GRASS_BLOCK);
        output.accept(HALF_PODZOL);
        output.accept(HALF_MYCELIUM);
        output.accept(HALF_DIRT);
        output.accept(HALF_DIRT_PATH);
        output.accept(IRON_FRAME);
        output.accept(IRON_CORRIDOR);
        output.accept(IRON_CORRIDOR_SLAB);
        output.accept(IRON_CORRIDOR_STAIRS);
        output.accept(IRON_GUARDRAIL);
        output.accept(IRON_LADDER);
        output.accept(IRON_SCAFFOLDING);
        output.accept(QUARTZ_SQUAT_TOILET);
        output.accept(IRON_SQUAT_TOILET);
        output.accept(QUARTZ_TOILET);
        output.accept(IRON_TOILET);
        output.accept(SINK);
        output.accept(MANGROVE_SINK);
        output.accept(MIRROR);
        output.accept(CABINET_MIRROR);
        output.accept(SHOWER_SET);
        output.accept(SPIRAL_STONE);
        output.accept(SPIRAL_DEEPSLATE);
        output.accept(SPIRAL_TUFF);
        output.accept(SPIRAL_BLACKSTONE);
        output.accept(SPIRAL_END_STONE);
        output.accept(INTERLACE_BRICKS);
        output.accept(WATERLOGGED_COBBLESTONE);
        output.accept(VILLAGE_PATTERNED_TILES);
        output.accept(VILLAGE_MOSAIC_TILES);
        output.accept(BIG_FLOWER_POT);
        output.accept(CONCRETE);
        output.accept(CONCRETE_POWDER);
        output.accept(GRAVEL_CONCRETE);
        output.accept(GRAVEL_CONCRETE_POWDER);
        output.accept(BLACK_IRON_BLOCK);
        output.accept(SMOOTH_OAK_PLANKS);
        output.accept(SMOOTH_SPRUCE_PLANKS);
        output.accept(SMOOTH_BIRCH_PLANKS);
        output.accept(SMOOTH_JUNGLE_PLANKS);
        output.accept(SMOOTH_ACACIA_PLANKS);
        output.accept(SMOOTH_DARK_OAK_PLANKS);
        output.accept(SMOOTH_MANGROVE_PLANKS);
        output.accept(SMOOTH_BAMBOO_PLANKS);
        output.accept(SMOOTH_CHERRY_PLANKS);
        output.accept(SMOOTH_CRIMSON_PLANKS);
        output.accept(SMOOTH_WARPED_PLANKS);
        output.accept(OAK_PLANKS_AND_LIGHT_GRAY_CONCRETE);
        output.accept(SPRUCE_PLANKS_AND_GRAY_CONCRETE);
        output.accept(OAK_PLANKS_AND_SPRUCE_PLANKS);
        output.accept(WHITE_CONCRETE_AND_LIGHT_GRAY_CONCRETE);
        output.accept(DEEPSLATE_TILES_AND_SPRUCE_PLANKS);
        output.accept(DEEPSLATE_TILES_AND_MANGROVE_PLANKS);
        output.accept(DARK_PRISMARINE_AND_SPRUCE_PLANKS);
        output.accept(DARK_PRISMARINE_AND_MANGROVE_PLANKS);
        output.accept(BRICKS_AND_BIRCH_PLANKS);
        output.accept(GLOWSTONE_LAMP);
        output.accept(REINFORCED_GLOWSTONE_LAMP);
        output.accept(REINFORCED_SEA_LANTERN);
        output.accept(RED_LAMP);
        output.accept(GREEN_LAMP);
        output.accept(BLUE_LAMP);
        output.accept(YELLOW_LAMP);
        output.accept(CYAN_LAMP);
        output.accept(PURPLE_LAMP);
        output.accept(RAINBOW_WOOL);
        output.accept(RAINBOW_TERRACOTTA);
        output.accept(RAINBOW_CONCRETE);
        output.accept(RAINBOW_GLASS_PANE);
        output.accept(RAINBOW_GLASS);
        output.accept(RAINBOW_SEA_LANTERN);
        output.accept(RAINBOW_REINFORCED_SEA_LANTERN);
        output.accept(RAINBOW_LAMP);
        output.accept(FAKE_HOPPER);
        output.accept(FAKE_FURNACE);
        output.accept(LIT_FAKE_FURNACE);
        output.accept(FAKE_BLAST_FURNACE);
        output.accept(LIT_FAKE_BLAST_FURNACE);
        output.accept(FAKE_SMOKER);
        output.accept(LIT_FAKE_SMOKER);
        output.accept(FAKE_BARREL);
        output.accept(FAKE_CAMPFIRE);
        output.accept(LIT_FAKE_CAMPFIRE);
        output.accept(LIT_FAKE_SOUL_CAMPFIRE);
        output.accept(FAKE_CHEST);
        output.accept(FAKE_CHISELED_BOOKSHELF);
        output.accept(FAKE_LECTERN);
        output.accept(FAKE_BEEHIVE);
        output.accept(FAKE_IRON_BLOCK);
        output.accept(FAKE_GOLD_BLOCK);
        output.accept(FAKE_DIAMOND_BLOCK);
        output.accept(FAKE_NETHERITE_BLOCK);
        output.accept(FAKE_REINFORCED_DEEPSLATE);
        output.accept(FAKE_BEDROCK);
        output.accept(TEXTURE_CHISELED_BOOKSHELF);
        output.accept(TEXTURE_CHISELED_BOOKSHELF_TOP);
        output.accept(TEXTURE_CHISELED_BOOKSHELF_SIDE);
        output.accept(TEXTURE_LOOM);
        output.accept(TEXTURE_BEEHIVE_TOP);
        output.accept(TEXTURE_SMITHING_TABLE_BOTTOM);
        output.accept(TEXTURE_COMPOSTER_BOTTOM);
        output.accept(TEXTURE_BEE_NEST_TOP);
        output.accept(TEXTURE_FURNACE);
        output.accept(TEXTURE_FURNACE_TOP);
        output.accept(TEXTURE_BLAST_FURNACE);
        output.accept(TEXTURE_BLAST_FURNACE_TOP);
        output.accept(TEXTURE_SMOKER);
        output.accept(TEXTURE_SMITHING_TABLE_TOP);
        output.accept(TEXTURE_LODESTONE);
        output.accept(TEXTURE_LODESTONE_SIDE);
        output.accept(TEXTURE_LODESTONE_TOP);
        output.accept(MEAT_BLOCK);
        output.accept(ROTTEN_FLESH_BLOCK);

        output.accept(CROWBAR);
        output.accept(NETHERITE_CROWBAR);
        output.accept(GLASS_KNIFE);
        output.accept(PUFFERFISH_ROD);
        output.accept(METEOR_HAMMER);
        output.accept(NETHERITE_METEOR_HAMMER);
        output.accept(SACABAMBASPIS);
        output.accept(PARROT);
        output.accept(WOODEN_DOOR);
        output.accept(IRON_DOOR);
        output.accept(FOX_CARROT);
        output.accept(FROSTED_BREAD);
        output.accept(SWEET_BERRY_COOKIE);
        output.accept(GLOW_BERRY_COOKIE);
        output.accept(CARAMEL_COOKIE);
        output.accept(CARAMEL);
        output.accept(FOX_CARROT_SEED);
        output.accept(RAINBOW_DYE);

        parameters.holders().lookup(Registries.ENCHANTMENT).flatMap(
                enchantmentLookup -> enchantmentLookup.get(FLEnchantments.POISON_ASPECT)
        ).ifPresent(enchantment -> output.accept(EnchantedBookItem.createForEnchantment(
                new EnchantmentInstance(enchantment, enchantment.value().getMaxLevel())))
        );

        output.accept(INFINITE_TNT);
        output.accept(INFINITE_RAIL_CARPET);
        output.accept(SNOWBALL_GENERATOR);
        output.accept(INFINITE_SNOWBALL);
        BlockBulkRegister.BIG_BUTTON_BLOCKS_IN_TAB.forEach(output::accept);

    }

    public static void init() {}

    private FLCreativeModeTab() {}

}
