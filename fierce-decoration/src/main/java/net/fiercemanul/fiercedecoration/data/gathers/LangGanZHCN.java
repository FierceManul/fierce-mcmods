package net.fiercemanul.fiercedecoration.data.gathers;

import net.fiercemanul.fiercedecoration.FierceDecoration;
import net.fiercemanul.fiercedecoration.data.FDItems;
import net.fiercemanul.fiercedecoration.data.FDBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.locale.Language;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.io.InputStream;
import java.util.*;

public class LangGanZHCN extends LanguageProvider {


    public static final HashMap<DeferredHolder<Block, ? extends Block>, String> ROWS = new HashMap<>();
    private final ExistingFileHelper fileHelper;

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

        applySimple();

        FierceDecoration.BLOCKS.getEntries().forEach(b -> {
            String s = ROWS.containsKey(b) ? ROWS.get(b) : b.getId().getPath();
            for (String key : keys) s = s.replace(key, translations.get(key));
            for (String key : keysB) s = s.replace(key, translationsB.get(key));
            add(b.get(), fix(s));
        });

        add(FDItems.FOX_CARROT.get(), "狐萝卜");
        add(FDItems.FOX_CARROT_SEED.get(), "狐萝卜种子");

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

    private void applySimple() {
        ROWS.put(FDBlocks.SOUL_CRYSTAL_ORNAMENT, "魂晶摆件");
        ROWS.put(FDBlocks.PORTABLE_WORKSTATION, "便携工作站");
        ROWS.put(FDBlocks.LAPTOP_TERMINAL, "便携终端");
        ROWS.put(FDBlocks.BOOK_AND_LAMP, "书与台灯");
        ROWS.put(FDBlocks.STAR_BLOCK, "星空方块");
        ROWS.put(FDBlocks.ITEM_FRAME_SHELL_THIN, "薄型物品展示框护罩");
        ROWS.put(FDBlocks.ITEM_FRAME_SHELL_BIG, "物品展示框护罩");
        ROWS.put(FDBlocks.LIGHT_TUBE, "光管");
        ROWS.put(FDBlocks.LIGHT_PLATE, "光板");
        ROWS.put(FDBlocks.FIREWOOD, "柴堆");
        ROWS.put(FDBlocks.FIREPLACE_HEART, "壁炉火盆");
        ROWS.put(FDBlocks.ROCK_PATH, "石径");
        ROWS.put(FDBlocks.GREEN_FUN_ROOF, "绿趣棚");
        ROWS.put(FDBlocks.CRAFTING_BLOCK, "工作快");
        ROWS.put(FDBlocks.CRAFTING_DESK, "工作桌");
        ROWS.put(FDBlocks.CRAFTING_PAD, "工作板");
        ROWS.put(FDBlocks.FOX_CARROT, "狐萝卜");
        ROWS.put(FDBlocks.FOX_CARROT_SHEAF, "狐萝卜捆");
        ROWS.put(FDBlocks.FOX_CARROT_BASKET, "狐萝卜篮");
        ROWS.put(FDBlocks.ROTTEN_FLESH_BLOCK, "腐肉块");
        ROWS.put(FDBlocks.WATERLOGGED_COBBLESTONE, "潮湿圆石");
        ROWS.put(FDBlocks.A_WALL_FLOWER_POT, "一号挂壁盆栽");
        ROWS.put(FDBlocks.B_WALL_FLOWER_POT, "二号挂壁盆栽");
        ROWS.put(FDBlocks.C_WALL_FLOWER_POT, "三号挂壁盆栽");
        ROWS.put(FDBlocks.D_WALL_FLOWER_POT, "四号挂壁盆栽");
        ROWS.put(FDBlocks.E_WALL_FLOWER_POT, "五号挂壁盆栽");
        ROWS.put(FDBlocks.F_WALL_FLOWER_POT, "六号挂壁盆栽");
        ROWS.put(FDBlocks.HEAVY_CHAINS, "重型锁链");
        ROWS.put(FDBlocks.NEO_FORGE, "NeoForge");
        ROWS.put(FDBlocks.LIT_FAKE_FURNACE, "永燃假熔炉");
        ROWS.put(FDBlocks.LIT_FAKE_BLAST_FURNACE, "永燃假高炉");
        ROWS.put(FDBlocks.LIT_FAKE_SMOKER, "永燃烟熏炉");
        ROWS.put(FDBlocks.LIT_FAKE_CAMPFIRE, "燃烧的假营火");
        ROWS.put(FDBlocks.LIT_FAKE_SOUL_CAMPFIRE, "燃烧的假灵魂营火");
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
        map.put("reinforced", "强化");
        map.put("wool", "羊毛");
        map.put("texture", "纹理");
        map.put("_lamp", "灯");
        map.put("fake_", "假");
        map.put("half_", "半");
        map.put("_side", " 侧");
        map.put("_top", " 顶");
        map.put("_bottom", " 底");
        map.put("_and_", "拼");
        map.put("_stairs", "楼梯");
        map.put("_slab", "台阶");
        map.put("_fence", "栅栏");
        map.put("_gate", "门");
        map.put("_button", "按钮");
        map.put("_pressure_plate", "踏板");
        return map;
    }

    private String fix(String string) {
        string = string.replace("板板", "板");
        string = string.replace("柱柱", "柱");
        string = string.replace("色灯", "灯");
        string = string.replace("灯玻璃灯", "玻璃灯");
        string = string.replace("色玻璃灯", "玻璃灯");
        return string.replace("_", "");
    }
}
