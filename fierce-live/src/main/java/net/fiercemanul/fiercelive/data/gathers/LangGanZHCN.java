package net.fiercemanul.fiercelive.data.gathers;

import net.fiercemanul.fiercelive.FierceLive;
import net.fiercemanul.fiercelive.data.FLBlocks;
import net.fiercemanul.fiercelive.data.FLItems;
import net.fiercemanul.fiercelive.data.registries.FLRegister;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.locale.Language;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class LangGanZHCN extends LanguageProvider {


    public static final HashMap<DeferredHolder<Block, ? extends Block>, String> ROWS = new HashMap<>();
    private final ExistingFileHelper fileHelper;

    public LangGanZHCN(ExistingFileHelper existingFileHelper, PackOutput output) {
        super(output, FierceLive.MODID, "zh_cn");
        this.fileHelper = existingFileHelper;
    }

    @Override
    protected void addTranslations() {
        Map<String, String> langMap = new HashMap<>();
        try (InputStream inputstream = fileHelper.getResource(ResourceLocation.withDefaultNamespace("lang/zh_cn.json"), PackType.CLIENT_RESOURCES).open()) {
            Language.loadFromJson(inputstream, langMap::put, (key, value) -> {});
        } catch (Exception exception) {
            LOGGER.error("Couldn't read lang from {}", "zh_cn", exception);
        }

        Map<String, String> translations = new HashMap<>();
        BuiltInRegistries.ITEM.forEach(item -> {
            ResourceLocation location = BuiltInRegistries.ITEM.getKey(item);
            if (location.getNamespace().equals("minecraft")) translations.put(location.getPath(), langMap.get(item.getDescriptionId()));
        });
        translations.remove("air");
        translations.remove("bow");
        translations.remove("rail");
        translations.remove("light");
        String[] keys = translations.keySet().toArray(new String[0]);
        Comparator<String> comparator = (o1, o2) -> Integer.compare(o2.length(), o1.length());
        Arrays.parallelSort(keys, comparator);

        Map<String, String> translationsB = getTranslationsB();
        String[] keysB = translationsB.keySet().toArray(new String[0]);
        Arrays.parallelSort(keysB, comparator);

        applySimple();

        FLRegister.BLOCKS.getEntries().forEach(b -> {
            String s = ROWS.containsKey(b) ? ROWS.get(b) : b.getId().getPath();
            for (String key : keys) s = s.replace(key, translations.get(key));
            for (String key : keysB) s = s.replace(key, translationsB.get(key));
            add(b.get(), fix(s));
        });

        add(FLItems.FOX_CARROT.get(), "狐萝卜");
        add(FLItems.FOX_CARROT_SEED.get(), "狐萝卜种子");
        add(FLItems.CROWBAR.get(), "撬棒");
        add(FLItems.NETHERITE_CROWBAR.get(), "下界合金撬棒");
        add(FLItems.PUFFERFISH_ROD.get(), "河豚棒");
        add(FLItems.METEOR_HAMMER.get(), "流星锤");
        add(FLItems.NETHERITE_METEOR_HAMMER.get(), "下界合金流星锤");
        add(FLItems.SACABAMBASPIS.get(), "萨卡班甲鱼");
        add(FLItems.PARROT.get(), "鹦鹉");
        add(FLItems.WOODEN_DOOR.get(), "木门");
        add(FLItems.IRON_DOOR.get(), "铁门");
        add(FLItems.RAINBOW_DYE.get(), "彩虹染料");
        add(FLItems.FROSTED_BREAD.get(), "糖霜面包");
        add(FLItems.CARAMEL.get(), "焦糖");
        add(FLItems.SWEET_BERRY_COOKIE.get(), "甜浆果曲奇");
        add(FLItems.GLOW_BERRY_COOKIE.get(), "发光浆果曲奇");
        add(FLItems.CARAMEL_COOKIE.get(), "焦糖曲奇");

        add("enchantment.fiercelive.poison_aspect", "毒性附加");

        add("item_group.fiercelive.decoration", "绿野栖居-趣物");
        add("item_group.fiercelive.building", "疏野闲章-建筑");
        add("item_group.fiercelive.colored", "疏野闲居-好色");
        add("container.fiercelive.cabinet", "柜子");

        add("advancements.fiercelive.root.title", "疏野闲章");
        add("advancements.fiercelive.root.description", "来点闲情雅致");
        add("advancements.fiercelive.half_block.title", "半块土");
        add("advancements.fiercelive.half_block.description", "并不是台阶");
        add("advancements.fiercelive.cut_blocks.title", "竖半砖？");
        add("advancements.fiercelive.cut_blocks.description", "哎？这边不对吧");
        add("advancements.fiercelive.green_fun_roof.title", "休闲过道");
        add("advancements.fiercelive.green_fun_roof.description", "采菊东篱下，悠然见南山。");
        add("advancements.fiercelive.crowbar.title", "掰扯掰扯");
        add("advancements.fiercelive.crowbar.description", "使用物理学圣剑调整方块");
        add("advancements.fiercelive.meteor_hammer.title", "流星一击");
        add("advancements.fiercelive.meteor_hammer.description", "使用流星锤的横扫一击");
        add("advancements.fiercelive.pickaxe.title", "镐击");
        add("advancements.fiercelive.pickaxe.description", "使用镐子造成击杀");
        add("advancements.fiercelive.iron_corridor.title", "廊架");
        add("advancements.fiercelive.iron_corridor.description", "视察你的智能工业园");
        add("advancements.fiercelive.fox_carrot.title", "呜狐！");
        add("advancements.fiercelive.fox_carrot.description", "获得狐狸尾巴");
        add("advancements.fiercelive.rainbow_dye.title", "哇~彩虹！");
        add("advancements.fiercelive.rainbow_dye.description", "这可不能吃");
        add("advancements.fiercelive.fireplace_heart.title", "温暖的家");
        add("advancements.fiercelive.fireplace_heart.description", "获得壁炉火盆");
        add("advancements.fiercelive.item_frame.title", "装裱");
        add("advancements.fiercelive.item_frame.description", "获得玻璃罩");
        add("advancements.fiercelive.iron_scaffolding.title", "防火脚手架");
        add("advancements.fiercelive.iron_scaffolding.description", "获得铁脚手架");
        add("advancements.fiercelive.sacabambaspis.title", "劲爆大只咸鱼");
        add("advancements.fiercelive.sacabambaspis.description", "获得萨卡班甲鱼");
        add("advancements.fiercelive.parrot.title", "是虎钳！");
        add("advancements.fiercelive.parrot.description", "获得飞天老虎钳");

        add("fiercelive.tip.snake_vertical", "潜行时垂直摆放");
        add("fiercelive.tip.snake_horizontal", "潜行时水平摆放");
        add("fiercelive.tip.no_more_be", "非方块实体");

        add("fml.menu.mods.info.description.fiercelive", "知足常乐。");
    }

    private void applySimple() {
        ROWS.put(FLBlocks.SOUL_CRYSTAL_ORNAMENT, "灵魂水晶摆件");
        ROWS.put(FLBlocks.PORTABLE_WORKSTATION, "便携工作站");
        ROWS.put(FLBlocks.LAPTOP_TERMINAL, "便携终端");
        ROWS.put(FLBlocks.BOOK_AND_LAMP, "书与台灯");
        //ROWS.put(FLBlocks.STAR_BLOCK, "星空方块");
        ROWS.put(FLBlocks.ITEM_FRAME_SHELL_THIN, "薄型物品展示框护罩");
        ROWS.put(FLBlocks.ITEM_FRAME_SHELL_BIG, "物品展示框护罩");
        ROWS.put(FLBlocks.LIGHT_TUBE, "光管");
        ROWS.put(FLBlocks.LIGHT_PLATE, "光板");
        ROWS.put(FLBlocks.FIREWOOD, "柴堆");
        ROWS.put(FLBlocks.FIREPLACE_HEART, "壁炉火盆");
        ROWS.put(FLBlocks.ROCK_PATH, "石径");
        ROWS.put(FLBlocks.GREEN_FUN_ROOF, "绿趣棚");
        ROWS.put(FLBlocks.CRAFTING_BLOCK, "工作快");
        ROWS.put(FLBlocks.CRAFTING_DESK, "工作桌");
        ROWS.put(FLBlocks.CRAFTING_PAD, "工作板");
        ROWS.put(FLBlocks.IRON_GUARDRAIL, "铁护栏");
        ROWS.put(FLBlocks.IRON_FRAME, "铁框架");
        ROWS.put(FLBlocks.IRON_CORRIDOR, "铁廊架");
        ROWS.put(FLBlocks.IRON_CORRIDOR_SLAB, "铁廊架台阶");
        ROWS.put(FLBlocks.IRON_CORRIDOR_STAIRS, "铁廊架楼梯");
        ROWS.put(FLBlocks.IRON_LADDER, "铁梯");
        ROWS.put(FLBlocks.IRON_SCAFFOLDING, "铁脚手架");
        ROWS.put(FLBlocks.FOX_CARROTS, "狐萝卜");
        ROWS.put(FLBlocks.FOX_CARROT_SHEAF, "狐萝卜捆");
        ROWS.put(FLBlocks.FOX_CARROT_BASKET, "狐萝卜篮");
        ROWS.put(FLBlocks.MEAT_BLOCK, "肉块");
        ROWS.put(FLBlocks.ROTTEN_FLESH_BLOCK, "腐肉块");
        ROWS.put(FLBlocks.WATERLOGGED_COBBLESTONE, "潮湿圆石");
        ROWS.put(FLBlocks.SPIRAL_STONE, "旋纹石砖");
        ROWS.put(FLBlocks.DEEPSLATE_SPIRAL_STONE, "深板岩旋纹石砖");
        ROWS.put(FLBlocks.VILLAGE_PATTERNED_TILES, "村香花纹砖");
        ROWS.put(FLBlocks.VILLAGE_MOSAIC_TILES, "村香马赛克砖");
        ROWS.put(FLBlocks.BIG_FLOWER_POT, "大花盆");
        ROWS.put(FLBlocks.CONCRETE, "混凝土");
        ROWS.put(FLBlocks.CONCRETE_POWDER, "混凝土粉末");
        ROWS.put(FLBlocks.GRAVEL_CONCRETE, "沙面混凝土");
        ROWS.put(FLBlocks.GRAVEL_CONCRETE_POWDER, "沙面混凝土粉末");
        ROWS.put(FLBlocks.A_WALL_FLOWER_POT, "一号挂壁盆栽");
        ROWS.put(FLBlocks.B_WALL_FLOWER_POT, "二号挂壁盆栽");
        ROWS.put(FLBlocks.C_WALL_FLOWER_POT, "三号挂壁盆栽");
        ROWS.put(FLBlocks.D_WALL_FLOWER_POT, "四号挂壁盆栽");
        ROWS.put(FLBlocks.E_WALL_FLOWER_POT, "五号挂壁盆栽");
        ROWS.put(FLBlocks.F_WALL_FLOWER_POT, "六号挂壁盆栽");
        ROWS.put(FLBlocks.HEAVY_CHAINS, "重型锁链");
        ROWS.put(FLBlocks.NEO_FORGE, "NeoForge");
        ROWS.put(FLBlocks.LIT_FAKE_FURNACE, "永燃假熔炉");
        ROWS.put(FLBlocks.LIT_FAKE_BLAST_FURNACE, "永燃假高炉");
        ROWS.put(FLBlocks.LIT_FAKE_SMOKER, "永燃烟熏炉");
        ROWS.put(FLBlocks.LIT_FAKE_CAMPFIRE, "燃烧的假营火");
        ROWS.put(FLBlocks.LIT_FAKE_SOUL_CAMPFIRE, "燃烧的假灵魂营火");
    }

    private Map<String, String> getTranslationsB() {
        Map<String, String> map = new HashMap<>();
        map.put("oak", "橡木");
        map.put("spruce", "云杉");
        map.put("birch", "白桦");
        map.put("jungle", "丛林");
        map.put("acacia", "金合欢");
        map.put("dark_oak", "深色橡木");
        map.put("mangrove", "红树");
        map.put("cherry", "樱花");
        map.put("bamboo", "竹");
        map.put("crimson", "绯红");
        map.put("warped", "诡异");
        map.put("concrete", "混凝土");
        map.put("white", "白色");
        map.put("orange", "橙色");
        map.put("magenta", "品红色");
        map.put("light_blue", "淡蓝色");
        map.put("yellow", "黄色");
        map.put("lime", "黄绿色");
        map.put("pink", "粉红色");
        map.put("gray", "灰色");
        map.put("light_gray", "淡灰色");
        map.put("cyan", "青色");
        map.put("purple", "紫色");
        map.put("blue", "蓝色");
        map.put("brown", "棕色");
        map.put("green", "绿色");
        map.put("red", "红色");
        map.put("black", "黑色");
        map.put("rainbow", "彩虹");
        map.put("smooth", "平滑");
        map.put("cracked", "裂纹");
        map.put("tile", "瓦");
        map.put("purpur", "紫珀");
        map.put("village_patterned_tile", "村香花纹");
        map.put("village_mosaic_tile", "村香马赛克");
        map.put("reinforced", "强化");
        map.put("wool", "羊毛");
        map.put("texture", "纹理块·");
        map.put("_lamp", "灯");
        map.put("fake_", "假");
        map.put("half_", "半");
        map.put("_side", "·侧");
        map.put("_top", "·顶");
        map.put("_bottom", "·底");
        map.put("_and_", "拼");
        map.put("_stairs", "楼梯");
        map.put("_slab", "台阶");
        map.put("_fence", "栅栏");
        map.put("_gate", "门");
        map.put("_button", "按钮");
        map.put("_pressure_plate", "踏板");
        return map;
    }

    protected String fix(String string) {
        return string.replace("_", "")
                     .replace("板板", "板")
                     .replace("柱柱", "柱")
                     .replace("色灯", "灯")
                     .replace("灯玻璃灯", "玻璃灯")
                     .replace("色玻璃灯", "玻璃灯")
                     .replace("泥巴红砖", "泥砖")
                     .replace("石头红砖", "石砖")
                     .replace("石红砖", "石砖")
                     .replace("岩红砖", "岩砖")
                     .replace("下界石英红砖", "石英砖")
                     .replace("下界石英", "石英");
    }
}
