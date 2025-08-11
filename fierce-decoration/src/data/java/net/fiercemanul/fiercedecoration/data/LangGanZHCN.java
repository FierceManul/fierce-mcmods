package net.fiercemanul.fiercedecoration.data;

import net.fiercemanul.fiercedecoration.FierceDecoration;
import net.fiercemanul.fiercedecoration.world.item.FDItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.locale.Language;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredItem;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class LangGanZHCN extends LanguageProvider {


    private final ExistingFileHelper fileHelper;
    private final Map<DeferredItem<?>, String> overwrites = buildOverwrites();

    public LangGanZHCN(ExistingFileHelper existingFileHelper, PackOutput output) {
        super(output, FierceDecoration.MODID, "zh_cn");
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

        FierceDecoration.ITEMS.getEntries().forEach(item -> {
            if (overwrites.containsKey(item)) add(item.get(), overwrites.get(item));
            else {
                String string = item.getId().getPath();
                for (String key : keys) string = string.replace(key, translations.get(key));
                for (String key : keysB) string = string.replace(key, translationsB.get(key));
                add(item.get(), fix(string));
            }
        });
        add("itemGroup.fiercedecoration.decoration", "野性装饰");
        add("itemGroup.fiercedecoration.building", "野性建筑");
        add("itemGroup.fiercedecoration.cut", "野性切块");
        add("itemGroup.fiercedecoration.colored", "野性色魔");
        add("fiercedecoration.tip.snake_vertical", "潜行以垂直摆放");
        add("fiercedecoration.tip.snake_horizontal", "潜行以水平摆放");
        add("fiercedecoration.tip.guardrail", "叠堆放置形成角落");
        add("fiercedecoration.tip.water_cobble", "不掉帧的情况下提供无限水");
        add("fiercedecoration.tip.no_more_be", "并非方块实体");
        add("fiercedecoration.container.cabinet", "柜子");
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
        map.put("smooth", "平滑");
        map.put("reinforced", "强化");
        map.put("rainbow", "彩虹");
        map.put("rainbow_concrete", "彩虹混凝土");
        map.put("rainbow_wool", "彩虹羊毛");
        map.put("fake_", "假");
        map.put("half_", "半");
        map.put("texture_", "纹理块：");
        map.put("_side", " 侧");
        map.put("_top", " 顶");
        map.put("_bottom", " 底");
        map.put("_and_", "拼");
        map.put("_one_cut_block", "一刀块");
        map.put("_stairs", "楼梯");
        map.put("_thin_stairs", "薄楼梯");
        map.put("_slab", "台阶");
        map.put("_double_cut_block", "两刀块");
        map.put("_triple_cut_block", "三刀块");
        map.put("_fence", "栅栏");
        map.put("_gate", "门");
        map.put("_button", "按钮");
        map.put("_pressure_plate", "踏板");
        map.put("_panel_4px", "板 4px");
        map.put("_panel_2px", "板 2px");
        map.put("_pillar_12px", "柱 12px");
        map.put("_pillar_connector_12px", "柱连接头 12px");
        map.put("_pillar_8px", "柱 8px");
        map.put("_pillar_connector_8px", "柱连接头 8px");
        map.put("_pillar_6px", "柱 6px");
        map.put("_pillar_connector_6px", "柱连接头 6px");
        map.put("_pillar_4px", "柱 4px");
        map.put("_pillar_connector_4px", "柱连接头 4px");
        map.put("_panel_horizon", "水平板");
        map.put("玻璃板l_horizon", "水平玻璃板");
        map.put("_guardrail", "围栏");
        map.put("_window_a", "窗 甲");
        map.put("_window_b", "窗 乙");
        map.put("_peep_window", "瞭望孔");
        map.put("_table", "桌子");
        map.put("_chair", "花园椅");
        map.put("_garden_chair", "椅");
        map.put("_sofa", "沙发");
        map.put("_lamp", "灯");
        map.put("_in_glass", "玻璃灯");
        map.put("in_玻璃", "玻璃灯");
        map.put("_cabinet_a", "柜 甲");
        map.put("_cabinet_b", "柜 乙");
        map.put("_cabinet_c", "柜 丙");
        map.put("_cabinet_d", "柜 丁");
        return map;
    }

    private String fix(String string) {
        string = string.replace("板板", "板");
        string = string.replace("柱柱", "柱");
        return string.replace("_", "");
    }

    private Map<DeferredItem<?>, String> buildOverwrites() {
        Map<DeferredItem<?>, String> map = new HashMap<>();
        map.put(FDItems.SOUL_CRYSTAL_ORNAMENT, "灵魂水晶摆件");
        map.put(FDItems.PORTABLE_WORKSTATION, "便携工作站");
        map.put(FDItems.LAPTOP_TERMINAL, "便携终端");
        map.put(FDItems.BOOK_AND_LAMP, "书与台灯");
        map.put(FDItems.STAR_BLOCK, "星空方块");
        map.put(FDItems.ITEM_FRAME_SHELL_THIN, "物品展示框护罩 薄");
        map.put(FDItems.ITEM_FRAME_SHELL_BIG, "物品展示框护罩 大");
        map.put(FDItems.LIGHT_TUBE, "光管");
        map.put(FDItems.LIGHT_PLATE, "光板");
        map.put(FDItems.FIREWOOD, "柴堆");
        map.put(FDItems.FIREPLACE_HEART, "壁炉火盆");
        map.put(FDItems.ROCK_PATH, "石径");
        map.put(FDItems.GREEN_FUN_ROOF, "绿趣棚");
        map.put(FDItems.CRAFTING_BLOCK, "工作快");
        map.put(FDItems.CRAFTING_DESK, "工作桌");
        map.put(FDItems.CRAFTING_PAD, "工作板");
        map.put(FDItems.FOX_CARROT, "狐萝卜");
        map.put(FDItems.FOX_CARROT_SEED, "狐萝卜种子");
        map.put(FDItems.FOX_CARROT_SHEAF, "狐萝卜捆");
        map.put(FDItems.FOX_CARROT_BASKET, "狐萝卜篮");
        map.put(FDItems.ROTTEN_FLESH_BLOCK, "腐肉块");
        map.put(FDItems.WATERLOGGED_COBBLESTONE, "潮湿圆石");
        map.put(FDItems.WALL_FLOWER_POT_A, "挂壁盆栽 甲");
        map.put(FDItems.WALL_FLOWER_POT_B, "挂壁盆栽 乙");
        map.put(FDItems.WALL_FLOWER_POT_C, "挂壁盆栽 丙");
        map.put(FDItems.WALL_FLOWER_POT_D, "挂壁盆栽 丁");
        map.put(FDItems.WALL_FLOWER_POT_E, "挂壁盆栽 戊");
        map.put(FDItems.WALL_FLOWER_POT_F, "挂壁盆栽 己");
        map.put(FDItems.HEAVY_CHAINS, "重型锁链");
        map.put(FDItems.NEO_FORGE, "NeoForge");
        map.put(FDItems.LIT_FAKE_FURNACE, "永焰熔炉");
        map.put(FDItems.LIT_FAKE_BLAST_FURNACE, "永焰高炉");
        map.put(FDItems.LIT_FAKE_SMOKER, "永焰烟熏炉");
        map.put(FDItems.LIT_FAKE_CAMPFIRE, "燃烧的假营火");
        map.put(FDItems.LIT_FAKE_SOUL_CAMPFIRE, "燃烧的假灵魂营火");
        return map;
    }
}
