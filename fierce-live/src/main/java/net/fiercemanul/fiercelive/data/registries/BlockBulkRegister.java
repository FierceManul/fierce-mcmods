package net.fiercemanul.fiercelive.data.registries;

import net.fiercemanul.fiercelive.data.FLBlocks;
import net.fiercemanul.fiercelive.data.gathers.*;
import net.fiercemanul.fiercelive.data.tags.FLBlockTags;
import net.fiercemanul.fiercelive.world.level.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.FastColor;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.EmptyBlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.data.loading.DatagenModLoader;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.*;

import static net.fiercemanul.fiercelive.data.registries.FLRegister.BLOCKS;
import static net.fiercemanul.fiercelive.data.registries.FLRegister.ITEMS;

public final class BlockBulkRegister {


    private static final int[] TINT_COLORS = new int[DyeColor.values().length];
    private static final int[] TINT_COLOR_LIGHT_LEVELS = new int[DyeColor.values().length];
    private static final Item[] DYES = new Item[DyeColor.values().length];
    public static final Set<DeferredBlock<Block>> CABINETS = new HashSet<>();
    public static final LinkedList<ItemLike>
            BUILDING_BLOCKS = new LinkedList<>(),
            COLORED_BLOCKS = new LinkedList<>();
    public static final LinkedHashSet<BlockMaterial> MATERIALS = new LinkedHashSet<>();
    public static final HashMap<BlockMaterial, WoodType> WOOD_TYPE_MAP = new HashMap<>();
    public static final HashMap<BlockMaterial, BlockSetType> BLOCK_SET_TYPE_MAP = new HashMap<>();
    public static final HashMap<DeferredBlock<? extends Block>, Integer> BLOCK_TINT_MAP = new HashMap<>();
    private static final ItemLike[] COLOR_SEA_LANTERN = new ItemLike[DyeColor.values().length];
    private static final ItemLike[] COLOR_REINFORCED_SEA_LANTERN = new ItemLike[DyeColor.values().length];
    public static boolean fired = false;

    static {
        //取色来源：染色玻璃
        TINT_COLORS[DyeColor.WHITE.getId()] = 0xFFFFFF;
        TINT_COLORS[DyeColor.ORANGE.getId()] = makeTintColor(216, 127, 51);
        TINT_COLORS[DyeColor.MAGENTA.getId()] = makeTintColor(178, 76, 216);
        TINT_COLORS[DyeColor.LIGHT_BLUE.getId()] = makeTintColor(102, 153, 216);
        TINT_COLORS[DyeColor.YELLOW.getId()] = makeTintColor(229, 229, 51);
        TINT_COLORS[DyeColor.LIME.getId()] = makeTintColor(127, 204, 25);
        TINT_COLORS[DyeColor.PINK.getId()] = makeTintColor(242, 127, 165);
        TINT_COLORS[DyeColor.GRAY.getId()] = makeTintColor(76, 76, 76);
        TINT_COLORS[DyeColor.LIGHT_GRAY.getId()] = makeTintColor(153, 153, 153);
        TINT_COLORS[DyeColor.CYAN.getId()] = makeTintColor(76, 127, 153);
        TINT_COLORS[DyeColor.PURPLE.getId()] = makeTintColor(127, 63, 178);
        TINT_COLORS[DyeColor.BLUE.getId()] = makeTintColor(51, 76, 178);
        TINT_COLORS[DyeColor.BROWN.getId()] = makeTintColor(102, 76, 51);
        TINT_COLORS[DyeColor.GREEN.getId()] = makeTintColor(102, 127, 51);
        TINT_COLORS[DyeColor.RED.getId()] = makeTintColor(153, 51, 51);
        TINT_COLORS[DyeColor.BLACK.getId()] = makeTintColor(25, 25, 25);
        for (DyeColor color : DyeColor.values()) TINT_COLOR_LIGHT_LEVELS[color.getId()] = makeBlockLight(TINT_COLORS[color.getId()]);
        DYES[DyeColor.WHITE.getId()] = Items.WHITE_DYE;
        DYES[DyeColor.ORANGE.getId()] = Items.ORANGE_DYE;
        DYES[DyeColor.MAGENTA.getId()] = Items.MAGENTA_DYE;
        DYES[DyeColor.LIGHT_BLUE.getId()] = Items.LIGHT_BLUE_DYE;
        DYES[DyeColor.YELLOW.getId()] = Items.YELLOW_DYE;
        DYES[DyeColor.LIME.getId()] = Items.LIME_DYE;
        DYES[DyeColor.PINK.getId()] = Items.PINK_DYE;
        DYES[DyeColor.GRAY.getId()] = Items.GRAY_DYE;
        DYES[DyeColor.LIGHT_GRAY.getId()] = Items.LIGHT_GRAY_DYE;
        DYES[DyeColor.CYAN.getId()] = Items.CYAN_DYE;
        DYES[DyeColor.PURPLE.getId()] = Items.PURPLE_DYE;
        DYES[DyeColor.BLUE.getId()] = Items.BLUE_DYE;
        DYES[DyeColor.BROWN.getId()] = Items.BROWN_DYE;
        DYES[DyeColor.GREEN.getId()] = Items.GREEN_DYE;
        DYES[DyeColor.RED.getId()] = Items.RED_DYE;
        DYES[DyeColor.BLACK.getId()] = Items.BLACK_DYE;
    }

    private BlockBulkRegister() {}

    public static int makeTintColor(int r, int g, int b) {
        return FastColor.ARGB32.color(255, r / 2 + 128, g / 2 + 128, b / 2 + 128);
    }

    public static BlockMaterial registerBlockMaterial(BlockMaterial blockMaterial) {
        if (fired) return blockMaterial;
        MATERIALS.add(blockMaterial);
        return blockMaterial;
    }

    public static int makeBlockLight(int color) {
        int r = FastColor.ARGB32.red(color);
        int g = FastColor.ARGB32.green(color);
        int b = FastColor.ARGB32.blue(color);
        //(r + g + b) / (3 * 255) * 15
        return Math.toIntExact(Math.round((r + g + b) / 51f));
    }

    public static void starRegister() {
        if (!fired) {
            BlockMaterials.init();
            register();
            fired = true;
        }
    }

    private static void register() {
        final boolean genData = DatagenModLoader.isRunningDataGen();
        MATERIALS.forEach(material -> {
            List<ItemLike> itemList = material.hasTag(BlockMaterialTag.TEXTURE_COLORED) ? COLORED_BLOCKS : BUILDING_BLOCKS;
            vanillaBlocks(material, itemList, genData);
            cutBlocks(material, itemList, genData);
            pillars(material, itemList, genData);
            horizonPanels(material, itemList, genData);
            windHoles(material, itemList, genData);
            crossHoles(material, itemList, genData);
            guardrail(material, itemList, genData);
            gardenChair(material, itemList, genData);
            windows(material, itemList, genData);
            table(material, itemList, genData);
            chair(material, itemList, genData);
            woolSofa(material, itemList, genData);
            cabinet(material, itemList, genData);
            lamps(material, itemList, genData);
        });
    }

    public static boolean isSimpleCubeBlock(BlockMaterial material) {
        return material.hasAllTags(BlockMaterialTag.STATE_STAND, BlockMaterialTag.MODEL_CUBE)
                && !material.hasAnyTags(BlockMaterialTag.TEXTURE_CHISELED, BlockMaterialTag.TEXTURE_TRANSLUCENT, BlockMaterialTag.TEXTURE_SNOW, BlockMaterialTag.LAMP);
    }

    public static boolean isSimpleStrongCubeBlock(BlockMaterial material) {
        return material.hasAnyTags(BlockMaterialTag.TOOL_PICKAXE, BlockMaterialTag.TOOL_AXE)
                && material.hasAllTags(BlockMaterialTag.STATE_STAND, BlockMaterialTag.MODEL_CUBE)
                && material.hasAnyTags(BlockMaterialTag.TEXTURE_SIMPLE, BlockMaterialTag.TEXTURE_FRAMED, BlockMaterialTag.TEXTURE_SMOOTH)
                && !material.hasAnyTags(BlockMaterialTag.TEXTURE_TRANSLUCENT);
    }

    public static boolean canMakePillar(BlockMaterial material) {
        return material.hasAnyTags(BlockMaterialTag.TOOL_PICKAXE, BlockMaterialTag.TOOL_AXE)
                && material.hasAnyTags(BlockMaterialTag.STATE_STAND, BlockMaterialTag.STATE_PILLAR)
                && material.hasAnyTags(BlockMaterialTag.MODEL_CUBE, BlockMaterialTag.MODEL_PILLAR, BlockMaterialTag.MODEL_LOG)
                && material.hasAnyTags(BlockMaterialTag.TEXTURE_SIMPLE, BlockMaterialTag.TEXTURE_FRAMED, BlockMaterialTag.TEXTURE_SMOOTH, BlockMaterialTag.TEXTURE_PILLAR)
                && !material.hasAnyTags(BlockMaterialTag.TEXTURE_TRANSLUCENT);
    }

    //TODO:麻酱没有很统一的做好方块属性，也没复用，这里复用了，留意麻酱的更新。

    private static void vanillaBlocks(BlockMaterial material, List<ItemLike> itemTab, boolean genData) {
        if (!material.hasTag(BlockMaterialTag.STATE_STAND)) return;
        if (material.hasTag(BlockMaterialTag.SMOOTH_PLANKS)) {
            DeferredBlock<StairBlock> stair = BLOCKS.register(
                    material.getPath() + "_stairs",
                    () -> new StairBlock(material.getBlock().defaultBlockState(), material.getBlock().properties())
            );
            itemTab.add(ITEMS.registerSimpleBlockItem(stair));

            DeferredBlock<SlabBlock> slab = BLOCKS.register(material.getPath() + "_slab", () -> new SlabBlock(material.getBlock().properties()));
            itemTab.add(ITEMS.registerSimpleBlockItem(slab));

            DeferredBlock<FenceBlock> fence = BLOCKS.register(material.getPath() + "_fence", () -> new FenceBlock(material.getBlock().properties()));
            itemTab.add(ITEMS.registerSimpleBlockItem(fence));

            DeferredBlock<FenceGateBlock> fence_gate = BLOCKS.register(
                    material.getPath() + "_fence_gate",
                    () -> new FenceGateBlock(WOOD_TYPE_MAP.get(material), material.getProperties().forceSolidOn())
            );
            itemTab.add(ITEMS.registerSimpleBlockItem(fence_gate));

            DeferredBlock<PressurePlateBlock> pressure_plate = BLOCKS.register(
                    material.getPath() + "_pressure_plate",
                    () -> new PressurePlateBlock(
                            BLOCK_SET_TYPE_MAP.get(material),
                            material.getProperties().strength(0.5F).forceSolidOn().noCollission().pushReaction(PushReaction.DESTROY)
                    )
            );
            itemTab.add(ITEMS.registerSimpleBlockItem(pressure_plate));

            DeferredBlock<ButtonBlock> button = BLOCKS.register(
                    material.getPath() + "_button",
                    () -> new ButtonBlock(
                            BLOCK_SET_TYPE_MAP.get(material),
                            30,
                            BlockBehaviour.Properties.of().strength(0.5F).noCollission().pushReaction(PushReaction.DESTROY)
                    )
            );
            itemTab.add(ITEMS.registerSimpleBlockItem(button));

            if (genData) {
                BlockTagsGen.ROWS.add(g -> g.woodenTagOrBasicTags(material, BlockTags.WOODEN_STAIRS, stair));
                BlockTagsGen.ROWS.add(g -> g.tag(BlockTags.STAIRS, stair));
                ItemTagsGen.ROWS.add(g -> g.basicTags(material, stair));
                ItemTagsGen.ROWS.add(g -> g.tag(ItemTags.WOODEN_STAIRS, stair));
                BlockStateGen.ROWS.put(stair, g -> g.stair(stair, material));
                RecipeGen.ROWS.add((g, o) -> g.buildStair(o, stair, material));
                LangGanZHCN.ROWS.put(stair, material.getPath() + "楼梯");

                BlockTagsGen.ROWS.add(g -> g.woodenTagOrBasicTags(material, BlockTags.WOODEN_SLABS, slab));
                BlockTagsGen.ROWS.add(g -> g.tag(BlockTags.SLABS, slab));
                ItemTagsGen.ROWS.add(g -> g.basicTags(material, slab));
                ItemTagsGen.ROWS.add(g -> g.tag(ItemTags.WOODEN_SLABS, slab));
                BlockStateGen.ROWS.put(slab, g -> g.slab(slab, material));
                RecipeGen.ROWS.add((g, o) -> g.buildSlab(o, slab, material));
                LangGanZHCN.ROWS.put(slab, material.getPath() + "台阶");

                BlockTagsGen.ROWS.add(g -> g.woodenTagOrBasicTags(material, BlockTags.WOODEN_FENCES, fence));
                ItemTagsGen.ROWS.add(g -> g.basicTags(material, fence));
                ItemTagsGen.ROWS.add(g -> g.tag(ItemTags.WOODEN_FENCES, fence));
                BlockStateGen.ROWS.put(fence, g -> g.fence(fence, material));
                RecipeGen.ROWS.add((g, o) -> g.buildFence(o, fence, material));
                LangGanZHCN.ROWS.put(fence, material.getPath() + "栅栏");

                BlockTagsGen.ROWS.add(g -> g.basicTags(material, fence_gate));
                BlockTagsGen.ROWS.add(g -> g.tag(BlockTags.FENCE_GATES, fence_gate));
                BlockTagsGen.ROWS.add(g -> g.tag(Tags.Blocks.FENCE_GATES_WOODEN, fence_gate));
                ItemTagsGen.ROWS.add(g -> g.basicTags(material, fence_gate));
                ItemTagsGen.ROWS.add(g -> g.tag(ItemTags.FENCE_GATES, fence_gate));
                ItemTagsGen.ROWS.add(g -> g.tag(Tags.Items.FENCE_GATES_WOODEN, fence_gate));
                BlockStateGen.ROWS.put(fence_gate, g -> g.fenceGate(fence_gate, material));
                RecipeGen.ROWS.add((g, o) -> g.buildFenceGate(o, fence_gate, material));
                LangGanZHCN.ROWS.put(fence_gate, material.getPath() + "栅栏门");

                BlockTagsGen.ROWS.add(g -> g.woodenTagOrBasicTags(material, BlockTags.WOODEN_PRESSURE_PLATES, pressure_plate));
                ItemTagsGen.ROWS.add(g -> g.basicTags(material, pressure_plate));
                ItemTagsGen.ROWS.add(g -> g.tag(ItemTags.WOODEN_PRESSURE_PLATES, pressure_plate));
                BlockStateGen.ROWS.put(pressure_plate, g -> g.pressurePlate(pressure_plate, material));
                RecipeGen.ROWS.add((g, o) -> g.buildPressurePlate(o, pressure_plate, material));
                LangGanZHCN.ROWS.put(pressure_plate, material.getPath() + "踏板");

                BlockTagsGen.ROWS.add(g -> g.woodenTagOrBasicTags(material, BlockTags.WOODEN_BUTTONS, button));
                ItemTagsGen.ROWS.add(g -> g.basicTags(material, button));
                ItemTagsGen.ROWS.add(g -> g.tag(ItemTags.WOODEN_BUTTONS, button));
                BlockStateGen.ROWS.put(button, g -> g.button(button, material));
                RecipeGen.ROWS.add((g, o) -> g.buildButton(o, button, material));
                LangGanZHCN.ROWS.put(button, material.getPath() + "按钮");
            }
        }

        if (material.blockRl().getNamespace().equals("minecraft")
                && material.hasTag(BlockMaterialTag.TOOL_PICKAXE)
                && material.hasAllTags(BlockMaterialTag.STATE_STAND, BlockMaterialTag.MODEL_CUBE)
                && material.hasAnyTags(BlockMaterialTag.TEXTURE_SIMPLE, BlockMaterialTag.TEXTURE_BRICKS, BlockMaterialTag.TEXTURE_FRAMED, BlockMaterialTag.TEXTURE_SMOOTH)
                && !material.hasAnyTags(BlockMaterialTag.TEXTURE_TRANSLUCENT)) {
            String path = material.getPath() + "_slab";
            if (!BuiltInRegistries.BLOCK.containsKey(ResourceLocation.fromNamespaceAndPath("minecraft", path))) {
                DeferredBlock<StairBlock> stair = BLOCKS.register(
                        material.getPath() + "_stairs",
                        () -> new StairBlock(material.getBlock().defaultBlockState(), material.getBlock().properties())
                );
                itemTab.add(ITEMS.registerSimpleBlockItem(stair));

                DeferredBlock<SlabBlock> slab = BLOCKS.register(path, () -> new SlabBlock(material.getBlock().properties()));
                itemTab.add(ITEMS.registerSimpleBlockItem(slab));

                if (genData) {
                    basicData(material, stair);
                    BlockTagsGen.ROWS.add(g -> g.tag(BlockTags.STAIRS, stair));
                    ItemTagsGen.ROWS.add(g -> g.tag(ItemTags.STAIRS, stair));
                    BlockStateGen.ROWS.put(stair, g -> g.stair(stair, material));
                    RecipeGen.ROWS.add((g, o) -> g.buildStair(o, stair, material));
                    LangGanZHCN.ROWS.put(stair, material.getPath() + "楼梯");

                    basicData(material, slab);
                    BlockTagsGen.ROWS.add(g -> g.tag(BlockTags.SLABS, slab));
                    ItemTagsGen.ROWS.add(g -> g.tag(ItemTags.SLABS, slab));
                    BlockStateGen.ROWS.put(slab, g -> g.slab(slab, material));
                    RecipeGen.ROWS.add((g, o) -> g.buildSlab(o, slab, material));
                    LangGanZHCN.ROWS.put(slab, material.getPath() + "台阶");
                }
            }

            String path2 = material.getPath() + "_wall";
            if (!BuiltInRegistries.BLOCK.containsKey(ResourceLocation.fromNamespaceAndPath("minecraft", path2))) {
                DeferredBlock<WallBlock> wall = BLOCKS.register(path2, () -> new WallBlock(material.getProperties().forceSolidOn()));
                itemTab.add(ITEMS.registerSimpleBlockItem(wall));

                if (genData) {
                    basicData(material, wall);
                    BlockTagsGen.ROWS.add(g -> g.tag(BlockTags.WALLS, wall));
                    ItemTagsGen.ROWS.add(g -> g.tag(ItemTags.WALLS, wall));
                    BlockStateGen.ROWS.put(wall, g -> g.wall(wall, material));
                    RecipeGen.ROWS.add((g, o) -> g.buildWall(o, wall, material));
                    LangGanZHCN.ROWS.put(wall, material.getPath() + "墙");
                }
            }
        }
    }

    private static void cutBlocks(BlockMaterial material, List<ItemLike> itemTab, boolean genData) {
        if (!isSimpleCubeBlock(material)) return;

        DeferredBlock<Block> one_cut_block = BLOCKS.register(material.getPath() + "_one_cut_block", () -> new OneCutBlock(material.getBlock().properties()));
        DeferredBlock<Block> thin_stairs = BLOCKS.register(material.getPath() + "_thin_stairs", () -> new ThinStairBlock(material.getBlock().properties()));
        DeferredBlock<Block> double_cut_block = BLOCKS.register(material.getPath() + "_double_cut_block", () -> new DoubleCutBlock(material.getBlock().properties()));
        DeferredBlock<Block> triple_cut_block = BLOCKS.register(material.getPath() + "_triple_cut_block", () -> new TripleCutBlock(material.getBlock().properties()));
        DeferredBlock<Block> panel4px = BLOCKS.register(material.getPath() + "_4px_panel", () -> new Panel4PXBlock(material.getBlock().properties()));
        DeferredBlock<Block> panel2px = BLOCKS.register(material.getPath() + "_2px_panel", () -> new Panel2PXBlock(material.getBlock().properties()));
        itemTab.add(ITEMS.registerSimpleBlockItem(one_cut_block));
        itemTab.add(ITEMS.registerSimpleBlockItem(thin_stairs));
        itemTab.add(ITEMS.registerSimpleBlockItem(double_cut_block));
        itemTab.add(ITEMS.registerSimpleBlockItem(triple_cut_block));
        itemTab.add(ITEMS.registerSimpleBlockItem(panel4px));
        itemTab.add(ITEMS.registerSimpleBlockItem(panel2px));

        if (genData) {
            boolean isWood = material.hasTag(BlockMaterialTag.TOOL_AXE);
            basicData(material, one_cut_block);
            BlockTagsGen.ROWS.add(g -> g.tag(FLBlockTags.CUT_BLOCKS, one_cut_block));
            BlockTagsGen.ROWS.add(g -> g.woodenTagOrBasicTags(material, BlockTags.WOODEN_STAIRS, thin_stairs));
            if (isWood) {
                ItemTagsGen.ROWS.add(g -> g.tag(ItemTags.WOODEN_STAIRS, thin_stairs));
            } else {
                BlockTagsGen.ROWS.add(g -> g.tag(BlockTags.STAIRS, thin_stairs));
                ItemTagsGen.ROWS.add(g -> g.tag(ItemTags.STAIRS, thin_stairs));
            }
            ItemTagsGen.ROWS.add(g -> g.basicTags(material, thin_stairs));
            basicData(material, double_cut_block);
            BlockTagsGen.ROWS.add(g -> g.tag(FLBlockTags.CUT_BLOCKS, double_cut_block));
            basicData(material, triple_cut_block);
            BlockTagsGen.ROWS.add(g -> g.tag(FLBlockTags.CUT_BLOCKS, triple_cut_block));
            basicData(material, panel4px);
            basicData(material, panel2px);
            BlockStateGen.ROWS.put(one_cut_block, g -> g.oneCutBlock(one_cut_block, g.getSimpleCubeMaterialResource(material)));
            BlockStateGen.ROWS.put(thin_stairs, g -> g.thinStairs(thin_stairs, g.getSimpleCubeMaterialResource(material)));
            BlockStateGen.ROWS.put(double_cut_block, g -> g.doubleCutBlock(double_cut_block, g.getSimpleCubeMaterialResource(material)));
            BlockStateGen.ROWS.put(triple_cut_block, g -> g.tripleCutBlock(triple_cut_block, g.getSimpleCubeMaterialResource(material)));
            BlockStateGen.ROWS.put(panel4px, g -> g.panel4px(panel4px, g.getSimpleCubeMaterialResource(material)));
            BlockStateGen.ROWS.put(panel2px, g -> g.panel2px(panel2px, g.getSimpleCubeMaterialResource(material)));
            BlockLootGen.ROWS.put(one_cut_block, g -> g.oneCutBlock(one_cut_block));
            BlockLootGen.ROWS.put(double_cut_block, g -> g.doubleCutBlock(double_cut_block));
            BlockLootGen.ROWS.put(triple_cut_block, g -> g.tripleCutBlock(triple_cut_block));
            RecipeGen.ROWS.add((g, o) -> g.cutOneToTwo(o, one_cut_block, material));
            RecipeGen.ROWS.add((g, o) -> g.cutOneToTwo(o, thin_stairs, material));
            RecipeGen.ROWS.add((g, o) -> g.cut(o, double_cut_block, material, 4));
            RecipeGen.ROWS.add((g, o) -> g.cut(o, triple_cut_block, material, 8));
            RecipeGen.ROWS.add((g, o) -> g.cut(o, panel4px, material, 4));
            RecipeGen.ROWS.add((g, o) -> g.cut(o, panel2px, material, 8));
            LangGanZHCN.ROWS.put(one_cut_block, material.getPath() + "一刀块");
            LangGanZHCN.ROWS.put(thin_stairs, material.getPath() + "薄楼梯");
            LangGanZHCN.ROWS.put(double_cut_block, material.getPath() + "两刀块");
            LangGanZHCN.ROWS.put(triple_cut_block, material.getPath() + "三刀块");
            LangGanZHCN.ROWS.put(panel4px, material.getPath() + "四号板");
            LangGanZHCN.ROWS.put(panel2px, material.getPath() + "二号板");
        }
    }

    private static void pillars(BlockMaterial material, List<ItemLike> itemTab, boolean genData) {
        if (!canMakePillar(material)) return;

        DeferredBlock<Block> pillar12 = BLOCKS.register(material.getPath() + "_12px_pillar", () -> new Pillar12PXBlock(material.getBlock().properties()));
        DeferredBlock<Block> connector12 = BLOCKS.register(material.getPath() + "_12px_pillar_connector", () -> new PillarConnector12PXBlock(material.getProperties().mapColor(material.mapColorHolder().side())));
        DeferredBlock<Block> pillar8 = BLOCKS.register(material.getPath() + "_8px_pillar", () -> new Pillar8PXBlock(material.getBlock().properties()));
        DeferredBlock<Block> connector8 = BLOCKS.register(material.getPath() + "_8px_pillar_connector", () -> new PillarConnector8PXBlock(material.getProperties().mapColor(material.mapColorHolder().side())));
        DeferredBlock<Block> pillar6 = BLOCKS.register(material.getPath() + "_6px_pillar", () -> new Pillar6PXBlock(material.getBlock().properties()));
        DeferredBlock<Block> connector6 = BLOCKS.register(material.getPath() + "_6px_pillar_connector", () -> new PillarConnector6PXBlock(material.getProperties().mapColor(material.mapColorHolder().side())));
        DeferredBlock<Block> pillar4 = BLOCKS.register(material.getPath() + "_4px_pillar", () -> new Pillar4PXBlock(material.getBlock().properties()));
        DeferredBlock<Block> connector4 = BLOCKS.register(material.getPath() + "_4px_pillar_connector", () -> new PillarConnector4PXBlock(material.getProperties().mapColor(material.mapColorHolder().side())));
        itemTab.add(ITEMS.registerSimpleBlockItem(pillar12));
        itemTab.add(ITEMS.registerSimpleBlockItem(connector12));
        itemTab.add(ITEMS.registerSimpleBlockItem(pillar8));
        itemTab.add(ITEMS.registerSimpleBlockItem(connector8));
        itemTab.add(ITEMS.registerSimpleBlockItem(pillar6));
        itemTab.add(ITEMS.registerSimpleBlockItem(connector6));
        itemTab.add(ITEMS.registerSimpleBlockItem(pillar4));
        itemTab.add(ITEMS.registerSimpleBlockItem(connector4));

        if (genData) {
            basicData(material, pillar4);
            basicData(material, connector4);
            basicData(material, pillar6);
            basicData(material, connector6);
            basicData(material, pillar8);
            basicData(material, connector8);
            basicData(material, pillar12);
            basicData(material, connector12);
            BlockTagsGen.ROWS.add(g -> g.tag(FLBlockTags.PILLARS, pillar4));
            BlockTagsGen.ROWS.add(g -> g.tag(FLBlockTags.PILLARS, connector4));
            BlockTagsGen.ROWS.add(g -> g.tag(FLBlockTags.PILLARS, pillar6));
            BlockTagsGen.ROWS.add(g -> g.tag(FLBlockTags.PILLARS, connector6));
            BlockTagsGen.ROWS.add(g -> g.tag(FLBlockTags.PILLARS, pillar8));
            BlockTagsGen.ROWS.add(g -> g.tag(FLBlockTags.PILLARS, connector8));
            BlockTagsGen.ROWS.add(g -> g.tag(FLBlockTags.PILLARS, pillar12));
            BlockTagsGen.ROWS.add(g -> g.tag(FLBlockTags.PILLARS, connector12));
            BlockStateGen.ROWS.put(pillar4, g -> g.pillar(pillar4, 4, material));
            BlockStateGen.ROWS.put(connector4, g -> g.pillarConnector(connector4, 4, material));
            BlockStateGen.ROWS.put(pillar6, g -> g.pillar(pillar6, 6, material));
            BlockStateGen.ROWS.put(connector6, g -> g.pillarConnector(connector6, 6, material));
            BlockStateGen.ROWS.put(pillar8, g -> g.pillar(pillar8, 8, material));
            BlockStateGen.ROWS.put(connector8, g -> g.pillarConnector(connector8, 8, material));
            BlockStateGen.ROWS.put(pillar12, g -> g.pillar(pillar12, 12, material));
            BlockStateGen.ROWS.put(connector12, g -> g.pillarConnector(connector12, 12, material));
            RecipeGen.ROWS.add((g, o) -> g.cut(o, pillar4, material, 16));
            RecipeGen.ROWS.add((g, o) -> g.cut(o, connector4, material, 5));
            RecipeGen.ROWS.add((g, o) -> g.cut(o, pillar6, material, 7));
            RecipeGen.ROWS.add((g, o) -> g.cut(o, connector6, material, 3));
            RecipeGen.ROWS.add((g, o) -> g.cut(o, pillar8, material, 4));
            RecipeGen.ROWS.add((g, o) -> g.cut(o, connector8, material, 2));
            RecipeGen.ROWS.add((g, o) -> g.pillar12px(o, pillar12, material));
            RecipeGen.ROWS.add((g, o) -> g.cutOneToTwo(o, connector12, material));
            LangGanZHCN.ROWS.put(pillar4, material.getPath() + "4号柱");
            LangGanZHCN.ROWS.put(connector4, material.getPath() + "4号柱接头");
            LangGanZHCN.ROWS.put(pillar6, material.getPath() + "6号柱");
            LangGanZHCN.ROWS.put(connector6, material.getPath() + "6号柱接头");
            LangGanZHCN.ROWS.put(pillar8, material.getPath() + "8号柱");
            LangGanZHCN.ROWS.put(connector8, material.getPath() + "8号柱接头");
            LangGanZHCN.ROWS.put(pillar12, material.getPath() + "12号柱");
            LangGanZHCN.ROWS.put(connector12, material.getPath() + "12号柱接头");
        }
    }

    private static void horizonPanels(BlockMaterial material, List<ItemLike> itemTab, boolean genData) {
        if (!material.getPath().contains("glass")) return;
        DeferredBlock<Block> panel = BLOCKS.register(material.getPath() + "_horizon_panel", () -> new HorizonPanelBlock(material.getBlock().properties()));
        itemTab.add(ITEMS.registerSimpleBlockItem(panel));

        if (genData) {
            basicData(material, panel);
            BlockStateGen.ROWS.put(panel, g -> g.horizonPanel(panel, material));
            RecipeGen.ROWS.add((g, o) -> g.cut(o, panel, material, 8));
            LangGanZHCN.ROWS.put(panel, material.getPath() + "平板");
        }
    }

    private static void crossHoles(BlockMaterial material, List<ItemLike> itemTab, boolean genData) {
        if (material.hasAllTags(
                BlockMaterialTag.TOOL_PICKAXE,
                BlockMaterialTag.STATE_STAND,
                BlockMaterialTag.MODEL_CUBE
        ) && !material.hasAnyTags(
                BlockMaterialTag.TEXTURE_BRICKS,
                BlockMaterialTag.TEXTURE_CHISELED,
                BlockMaterialTag.TEXTURE_CRACKED,
                BlockMaterialTag.TEXTURE_TRANSLUCENT,
                BlockMaterialTag.TEXTURE_SNOW,
                BlockMaterialTag.TEXTURE_COLORED,
                BlockMaterialTag.LAMP
        ) || material == BlockMaterials.GLASS) {

            DeferredBlock<Block> hole = BLOCKS.register(material.getPath() + "_cross_hole", () -> new CrossHoleBlock(material.getBlock().properties()));
            itemTab.add(ITEMS.registerSimpleBlockItem(hole));

            if (genData) {
                basicData(material, hole);
                BlockTagsGen.ROWS.add(g -> g.tag(FLBlockTags.CROSS_HOLES, hole));
                BlockStateGen.ROWS.put(hole, g -> g.crossHole(hole, material));
                RecipeGen.ROWS.add((g, o) -> g.cut(o, hole, material, 8));
                LangGanZHCN.ROWS.put(hole, material.getPath() + "十字孔");
            }
        }
    }

    private static void windHoles(BlockMaterial material, List<ItemLike> itemTab, boolean genData) {
        if (!isSimpleStrongCubeBlock(material)) return;

        DeferredBlock<Block> hole = BLOCKS.register(material.getPath() + "_wind_hole", () -> new WindHoleBlock(material.getBlock().properties()));
        itemTab.add(ITEMS.registerSimpleBlockItem(hole));

        if (genData) {
            basicData(material, hole);
            BlockStateGen.ROWS.put(hole, g -> g.windHole(hole, g.getSimpleCubeMaterialResource(material)));
            RecipeGen.ROWS.add((g, o) -> g.cutOneToTwo(o, hole, material));
            LangGanZHCN.ROWS.put(hole, material.getPath() + "风口");
        }
    }

    private static void guardrail(BlockMaterial material, List<ItemLike> itemTab, boolean genData) {
        if (material.hasAllTags(
                BlockMaterialTag.TOOL_AXE,
                BlockMaterialTag.STATE_STAND,
                BlockMaterialTag.MODEL_CUBE,
                BlockMaterialTag.TEXTURE_SMOOTH
        )) {
            DeferredBlock<Block> guardrail = BLOCKS.register(material.getPath() + "_guardrail", () -> new WoodenGuardrailBlock(material.getBlock().properties()));
            itemTab.add(ITEMS.registerSimpleBlockItem(guardrail));
            DeferredBlock<Block> guardrailB = BLOCKS.register(material.getPath() + "_guardrail_b", () -> new WoodenGuardrailTypeBBlock(material.getBlock().properties()));
            itemTab.add(ITEMS.registerSimpleBlockItem(guardrailB));

            if (genData) {
                basicData(material, guardrail);
                basicData(material, guardrailB);
                BlockTagsGen.ROWS.add(g -> g.tag(FLBlockTags.GUARDRAILS, guardrail));
                BlockTagsGen.ROWS.add(g -> g.tag(FLBlockTags.GUARDRAILS, guardrailB));
                BlockStateGen.ROWS.put(guardrail, g -> g.guardrail(guardrail, g.blockTexture(material), "guardrail_wooden"));
                BlockStateGen.ROWS.put(guardrailB, g -> g.guardrail(guardrailB, g.blockTexture(material), "guardrail_wooden_b"));
                RecipeGen.ROWS.add((g, o) -> g.woodenGuardrail(o, guardrail, material));
                RecipeGen.ROWS.add((g, o) -> g.woodenGuardrailB(o, guardrailB, material));
                LangGanZHCN.ROWS.put(guardrail, material.getPath() + "护栏");
                LangGanZHCN.ROWS.put(guardrailB, material.getPath() + "二号护栏");
            }
        }
        if (material.hasTag(BlockMaterialTag.TEXTURE_TRANSLUCENT)) {
            DeferredBlock<Block> guardrail = BLOCKS.register(material.getPath() + "_guardrail", () -> new GlassGuardrailBlock(GlassGuardrailBlock.DEFAULT_PROPERTIES));
            itemTab.add(ITEMS.registerSimpleBlockItem(guardrail));
            if (genData) {
                basicData(material, guardrail);
                BlockTagsGen.ROWS.add(g -> g.tag(FLBlockTags.GUARDRAILS, guardrail));
                BlockStateGen.ROWS.put(guardrail, g -> g.glassGuardrail(guardrail, g.blockTexture(material)));
                RecipeGen.ROWS.add((g, o) -> g.cutD(o, guardrail, material, 4));
                LangGanZHCN.ROWS.put(guardrail, material.getPath() + "护栏");
            }
        }
        else if (material.hasTag(BlockMaterialTag.TOOL_PICKAXE)
                && material.hasAnyTags(BlockMaterialTag.STATE_STAND, BlockMaterialTag.STATE_PILLAR)
                && material.hasAnyTags(BlockMaterialTag.MODEL_CUBE, BlockMaterialTag.MODEL_PILLAR, BlockMaterialTag.MODEL_LOG, BlockMaterialTag.MODEL_UP_DOWN_SIDE)
                && material.hasAnyTags(BlockMaterialTag.TEXTURE_SIMPLE, BlockMaterialTag.TEXTURE_FRAMED, BlockMaterialTag.TEXTURE_SMOOTH, BlockMaterialTag.TEXTURE_PILLAR, BlockMaterialTag.TEXTURE_UP_DOWN)
        ) {
            DeferredBlock<Block> guardrail = BLOCKS.register(material.getPath() + "_guardrail", () -> new StoneGuardrailBlock(material.getProperties().mapColor(material.mapColorHolder().side())));
            itemTab.add(ITEMS.registerSimpleBlockItem(guardrail));
            if (genData) {
                basicData(material, guardrail);
                BlockTagsGen.ROWS.add(g -> g.tag(FLBlockTags.GUARDRAILS, guardrail));
                BlockStateGen.ROWS.put(guardrail, g -> g.stoneGuardrail(guardrail, material));
                RecipeGen.ROWS.add((g, o) -> g.cutD(o, guardrail, material, 4));
                LangGanZHCN.ROWS.put(guardrail, material.getPath() + "护栏");
            }
        }
    }

    private static void gardenChair(BlockMaterial material, List<ItemLike> itemTab, boolean genData) {
        if (isSimpleStrongCubeBlock(material)) {
            String path = material.getPath();
            DeferredBlock<Block> gardenChair = material.hasTag(BlockMaterialTag.TOOL_AXE)
                                               ? BLOCKS.register(path + "_garden_chair", () -> new GardenChairBlock(material.getProperties().noOcclusion()))
                                               : BLOCKS.register(path + "_garden_chair", () -> new GardenChairBlock(material.getBlock().properties()));
            itemTab.add(ITEMS.registerSimpleBlockItem(gardenChair));
            if (genData) {
                basicData(material, gardenChair);
                BlockStateGen.ROWS.put(gardenChair, g -> g.gardenChair(gardenChair, material));
                RecipeGen.ROWS.add((g, o) -> g.cutD(o, gardenChair, material));
                LangGanZHCN.ROWS.put(gardenChair, path + "公园椅");
            }
        }
    }

    private static void windows(BlockMaterial material, List<ItemLike> itemTab, boolean genData) {
        if (!material.hasAllTags(BlockMaterialTag.TOOL_AXE, BlockMaterialTag.STATE_STAND, BlockMaterialTag.TEXTURE_SMOOTH)) return;
        String path = material.getPath();
        DeferredBlock<Block> window = BLOCKS.register(
                path + "_window", () -> new GlassWindowBlock(
                BLOCK_SET_TYPE_MAP.get(material), BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).mapColor(material.mapColorHolder().top()))
        );
        itemTab.add(ITEMS.registerSimpleBlockItem(window));

        if (genData) {
            basicData(material, window);
            BlockStateGen.ROWS.put(window, g -> g.glassWindow(window));
            RecipeGen.ROWS.add((g, o) -> g.glassWindow(o, window, material));
            LangGanZHCN.ROWS.put(window, path + "窗");
        }
    }

    private static void table(BlockMaterial material, List<ItemLike> itemTab, boolean genData) {
        if (isSimpleStrongCubeBlock(material) || material.hasTag(BlockMaterialTag.TEXTURE_TRANSLUCENT)) {
            String path = material.getPath();
            DeferredBlock<Block> table = BLOCKS.register(path + "_table", () -> new TableBlock(material.getBlock().properties()));
            itemTab.add(ITEMS.registerSimpleBlockItem(table));

            if (genData) {
                basicData(material, table);
                BlockTagsGen.ROWS.add(g -> g.tag(FLBlockTags.TABLES, table));
                BlockStateGen.ROWS.put(table, g -> g.table(table, material));
                RecipeGen.ROWS.add((g, o) -> g.cutD(o, table, material, 2));
                LangGanZHCN.ROWS.put(table, path + "桌");
            }
        }
    }

    private static void chair(BlockMaterial material, List<ItemLike> itemTab, boolean genData) {
        if (!isSimpleStrongCubeBlock(material)) return;
        String path = material.getPath();
        DeferredBlock<Block> chair = BLOCKS.register(path + "_chair", () -> new SimpleChairBlock(material.getBlock().properties()));
        itemTab.add(ITEMS.registerSimpleBlockItem(chair));

        if (genData) {
            basicData(material, chair);
            BlockStateGen.ROWS.put(chair, g -> g.chair(chair, g.getSimpleCubeMaterialResource(material)));
            RecipeGen.ROWS.add((g, o) -> g.cutD(o, chair, material, 2));
            LangGanZHCN.ROWS.put(chair, path + "椅");
        }
    }

    private static void woolSofa(BlockMaterial material, List<ItemLike> itemTab, boolean genData) {
        if (!material.hasTag(BlockMaterialTag.TOOL_SHEARS)) return;
        DeferredBlock<Block> woolSofa = BLOCKS.register(material.getPath() + "_wool_sofa", () -> new WoolSofaBlock(material.getBlock().properties()));
        itemTab.add(ITEMS.registerSimpleBlockItem(woolSofa));

        if (genData) {
            basicData(material, woolSofa);
            BlockTagsGen.ROWS.add(g -> g.tag(FLBlockTags.WOOL_SOFAS, woolSofa));
            BlockStateGen.ROWS.put(woolSofa, g -> g.woolSofa(woolSofa, g.blockTexture(material)));
            RecipeGen.ROWS.add((g, o) -> g.woolSofa(o, woolSofa, material));
            LangGanZHCN.ROWS.put(woolSofa, material.getPath() + "沙发");
        }
    }

    private static void cabinet(BlockMaterial material, List<ItemLike> itemTab, boolean genData) {
        if (!material.hasAllTags(BlockMaterialTag.TOOL_AXE, BlockMaterialTag.STATE_STAND, BlockMaterialTag.TEXTURE_SMOOTH)) return;
        String path = material.getPath();
        DeferredBlock<Block> cabinetA = BLOCKS.register(path + "_cabinet", () -> new CabinetTypeABlock(material.getBlock().properties()));
        DeferredBlock<Block> cabinetB = BLOCKS.register(path + "_cabinet_b", () -> new CabinetTypeBBlock(material.getBlock().properties()));
        DeferredBlock<Block> cabinetC = BLOCKS.register(path + "_cabinet_c", () -> new CabinetTypeCBlock(material.getBlock().properties()));
        DeferredBlock<Block> cabinetD = BLOCKS.register(path + "_cabinet_d", () -> new CabinetBlock(material.getBlock().properties()));
        itemTab.add(ITEMS.registerSimpleBlockItem(cabinetA));
        itemTab.add(ITEMS.registerSimpleBlockItem(cabinetB));
        itemTab.add(ITEMS.registerSimpleBlockItem(cabinetC));
        itemTab.add(ITEMS.registerSimpleBlockItem(cabinetD));

        BlockBulkRegister.CABINETS.add(cabinetA);
        BlockBulkRegister.CABINETS.add(cabinetB);
        BlockBulkRegister.CABINETS.add(cabinetC);
        BlockBulkRegister.CABINETS.add(cabinetD);

        if (genData) {
            basicData(material, cabinetA);
            basicData(material, cabinetB);
            basicData(material, cabinetC);
            basicData(material, cabinetD);
            BlockTagsGen.ROWS.add(g -> g.tag(FLBlockTags.CABINETS, cabinetA));
            BlockTagsGen.ROWS.add(g -> g.tag(FLBlockTags.CABINETS, cabinetB));
            BlockTagsGen.ROWS.add(g -> g.tag(FLBlockTags.CABINETS, cabinetC));
            BlockTagsGen.ROWS.add(g -> g.tag(FLBlockTags.CABINETS, cabinetD));
            BlockStateGen.ROWS.put(cabinetA, g -> g.cabinetA(cabinetA, g.blockTexture(material)));
            BlockStateGen.ROWS.put(cabinetB, g -> g.cabinetB(cabinetB, g.blockTexture(material)));
            BlockStateGen.ROWS.put(cabinetC, g -> g.cabinetC(cabinetC, g.blockTexture(material)));
            BlockStateGen.ROWS.put(cabinetD, g -> g.cabinetD(cabinetD, g.blockTexture(material)));
            RecipeGen.ROWS.add((g, o) -> g.cutD(o, cabinetA, material));
            RecipeGen.ROWS.add((g, o) -> g.cutD(o, cabinetB, material));
            RecipeGen.ROWS.add((g, o) -> g.cutD(o, cabinetC, material));
            RecipeGen.ROWS.add((g, o) -> g.cutD(o, cabinetD, material));
            LangGanZHCN.ROWS.put(cabinetA, path + "一号柜");
            LangGanZHCN.ROWS.put(cabinetB, path + "二号柜");
            LangGanZHCN.ROWS.put(cabinetC, path + "三号柜");
            LangGanZHCN.ROWS.put(cabinetD, path + "四号柜");
        }
    }

    private static void lamps(BlockMaterial material, List<ItemLike> itemTab, boolean genData) {
        if (!material.hasTag(BlockMaterialTag.LAMP)) return;

        String path = material.getPath() + "_glass_lamp";
        if (material != BlockMaterials.GLOWSTONE_LAMP) path = path.replace("lamp_glass_lamp", "glass_lamp");
        DeferredBlock<GlassLampBlock> lamp = BLOCKS.register(
                path,
                () -> {
                    int light = material.getBlock().getLightEmission(material.getBlock().defaultBlockState(), EmptyBlockGetter.INSTANCE, BlockPos.ZERO);
                    return new GlassLampBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).mapColor(material.mapColorHolder().top()).lightLevel(v -> light));
                }
        );
        itemTab.add(ITEMS.registerSimpleBlockItem(lamp));

        if (genData) {
            BlockTagsGen.ROWS.add(g -> g.tag(FLBlockTags.GLASS_LAMPS, lamp));
            if (material.hasTag(BlockMaterialTag.TEXTURE_COLORED)) {
                BlockTagsGen.ROWS.add(g -> g.colorTags(material.mapColorHolder().top(), lamp));
                ItemTagsGen.ROWS.add(g -> g.colorTags(material.mapColorHolder().top(), lamp));
            }
            if (material == BlockMaterials.SEA_LANTERN || material == BlockMaterials.RAINBOW_SEA_LANTERN) BlockTagsGen.ROWS.add(g -> g.tag(FLBlockTags.SEA_LANTERNS, lamp));
            BlockStateGen.ROWS.put(lamp, g -> g.glassLamp(lamp, material));
            BlockLootGen.ROWS.put(lamp, b -> b.glassLamp(lamp, material));
            RecipeGen.ROWS.add((g, o) -> g.buildGlassLamp(o, lamp, material));
            LangGanZHCN.ROWS.put(lamp, material.getPath() + "玻璃灯");
        }

        if (material == BlockMaterials.SEA_LANTERN) for (DyeColor color : DyeColor.values()) {
            DeferredBlock<Block> colorSeaLamp = BLOCKS.register(
                    color.getName() + "_" + material.getPath(),
                    () -> new Block(material.getProperties().mapColor(color).lightLevel(v -> TINT_COLOR_LIGHT_LEVELS[color.getId()]))
            );
            DeferredBlock<GlassLampBlock> glassLamp = BLOCKS.register(
                    color.getName() + "_" + material.getPath() + "_glass_lamp",
                    () -> new GlassLampBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).mapColor(color).lightLevel(v -> TINT_COLOR_LIGHT_LEVELS[color.getId()]))
            );
            DeferredBlock<Block> reinforcedLamp = BLOCKS.register(
                    color.getName() + "_reinforced_" + material.getPath(),
                    () -> new Block(BlockBehaviour.Properties.ofFullCopy(FLBlocks.REINFORCED_SEA_LANTERN.get()).mapColor(color).lightLevel(v -> TINT_COLOR_LIGHT_LEVELS[color.getId()]))
            );
            COLORED_BLOCKS.add(ITEMS.registerSimpleBlockItem(colorSeaLamp));
            COLORED_BLOCKS.add(ITEMS.registerSimpleBlockItem(glassLamp));
            COLORED_BLOCKS.add(ITEMS.registerSimpleBlockItem(reinforcedLamp));

            COLOR_SEA_LANTERN[color.getId()] = colorSeaLamp;
            COLOR_REINFORCED_SEA_LANTERN[color.getId()] = reinforcedLamp;

            if (color != DyeColor.WHITE) {
                int tintColor = TINT_COLORS[color.getId()];
                BLOCK_TINT_MAP.put(colorSeaLamp, tintColor);
                BLOCK_TINT_MAP.put(glassLamp, tintColor);
                BLOCK_TINT_MAP.put(reinforcedLamp, tintColor);
            }

            if (genData) {
                BlockTagsGen.ROWS.add(g -> g.tag(FLBlockTags.SEA_LANTERNS, colorSeaLamp));
                BlockTagsGen.ROWS.add(g -> g.tag(FLBlockTags.SEA_LANTERNS, glassLamp));
                BlockTagsGen.ROWS.add(g -> g.tag(FLBlockTags.GLASS_LAMPS, glassLamp));
                BlockTagsGen.ROWS.add(g -> g.tag(FLBlockTags.REINFORCED_SEA_LANTERNS, reinforcedLamp));
                BlockTagsGen.ROWS.add(g -> g.colorTags(color.getMapColor(), colorSeaLamp));
                BlockTagsGen.ROWS.add(g -> g.colorTags(color.getMapColor(), glassLamp));
                BlockTagsGen.ROWS.add(g -> g.colorTags(color.getMapColor(), reinforcedLamp));
                ItemTagsGen.ROWS.add(g -> g.colorTags(color.getMapColor(), colorSeaLamp));
                ItemTagsGen.ROWS.add(g -> g.colorTags(color.getMapColor(), glassLamp));
                ItemTagsGen.ROWS.add(g -> g.colorTags(color.getMapColor(), reinforcedLamp));
                BlockStateGen.ROWS.put(colorSeaLamp, g -> g.tintSeaLantern(colorSeaLamp));
                BlockStateGen.ROWS.put(glassLamp, g -> g.tintSeaLanternGlassLamp(glassLamp));
                BlockStateGen.ROWS.put(reinforcedLamp, g -> g.tintReinforcedSeaLantern(reinforcedLamp));
                BlockLootGen.ROWS.put(colorSeaLamp, b -> b.seaLantern(colorSeaLamp));
                BlockLootGen.ROWS.put(glassLamp, b -> b.glassLamp(glassLamp, colorSeaLamp));
                RecipeGen.ROWS.add((g, o) -> g.colorSeaLantern(o, colorSeaLamp, DYES[color.getId()]));
                RecipeGen.ROWS.add((g, o) -> g.buildGlassLamp(o, glassLamp, colorSeaLamp));
                RecipeGen.ROWS.add((g, o) -> g.reinforcedLamp(o, reinforcedLamp, colorSeaLamp));
                LangGanZHCN.ROWS.put(colorSeaLamp, colorSeaLamp.getId().getPath());
                LangGanZHCN.ROWS.put(glassLamp, colorSeaLamp.getId().getPath() + "玻璃灯");
                LangGanZHCN.ROWS.put(reinforcedLamp, color.name().toLowerCase() + "强化" + material.getPath());
            }
        }
    }

    public static void basicData(BlockMaterial material, DeferredBlock<? extends Block> deferredBlock) {
        BlockTagsGen.ROWS.add(g -> g.basicTags(material, deferredBlock));
        ItemTagsGen.ROWS.add(g -> g.basicTags(material, deferredBlock));
    }

    public static ItemLike getColoredSeaLamp(DyeColor color) {
        return COLOR_SEA_LANTERN[color.getId()];
    }

    public static ItemLike getColoredReinforcedSeaLamp(DyeColor color) {
        return COLOR_REINFORCED_SEA_LANTERN[color.getId()];
    }



}
