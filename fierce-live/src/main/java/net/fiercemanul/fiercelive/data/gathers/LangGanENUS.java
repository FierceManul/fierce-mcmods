package net.fiercemanul.fiercelive.data.gathers;

import net.fiercemanul.fiercelive.FierceLive;
import net.fiercemanul.fiercelive.data.FLBlocks;
import net.fiercemanul.fiercelive.data.registries.FLRegister;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class LangGanENUS extends LanguageProvider {

    public LangGanENUS(PackOutput output) {
        super(output, FierceLive.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        FLRegister.ITEMS.getEntries().forEach(itemRegistryObject -> add(itemRegistryObject.get(), nameFormat(itemRegistryObject.getId().getPath())));

        add(FLBlocks.FOX_CARROTS.get(), "Fox Carrot");

        add("item_group.fiercelive.decoration", "Fierce Living - Decoration");
        add("item_group.fiercelive.building", "Fierce Living - Building");
        add("item_group.fiercelive.colored", "Fierce Living - Colored");
        add("fiercelive.tip.snake_vertical", "Snake place to vertical");
        add("fiercelive.tip.snake_horizontal", "Snake place to horizontal");
        add("fiercelive.tip.no_more_be", "Not BlockEntity");
        add("fiercelive.container.cabinet", "Cabinet");
        add("fml.menu.mods.info.description.fiercelive", "知足常乐。");

        add("advancements.fiercelive.root.title", "Fierce Living");
        add("advancements.fiercelive.root.description", "来点闲情雅致");
        add("advancements.fiercelive.half_block.title", "半块土");
        add("advancements.fiercelive.half_block.description", "并不是台阶");
        add("advancements.fiercelive.cut_blocks.title", "竖半砖？");
        add("advancements.fiercelive.cut_blocks.description", "哎？这边不对吧");
        add("advancements.fiercelive.green_fun_roof.title", "休闲过道");
        add("advancements.fiercelive.green_fun_roof.description", "采菊东篱下，悠然见南山。");
        add("advancements.fiercelive.crowbar.title", "拆卸");
        add("advancements.fiercelive.crowbar.description", "使用物理学圣剑消灭一个骷髅");
        add("advancements.fiercelive.meteor_hammer.title", "吃我一锤");
        add("advancements.fiercelive.meteor_hammer.description", "使用流星锤造成击杀");
        add("advancements.fiercelive.pickaxe.title", "镐击");
        add("advancements.fiercelive.pickaxe.description", "使用镐子造成击杀");
        add("advancements.fiercelive.iron_corridor.title", "廊架");
        add("advancements.fiercelive.iron_corridor.description", "视察你的自动化设备");
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


    }

    private String nameFormat(String id) {
        char[] chars = id.toCharArray();
        StringBuilder builder = new StringBuilder();
        builder.append(String.valueOf(chars[0]).toUpperCase());
        for (int i = 1; i < id.length(); i++) {
            if (chars[i] == '_') {
                builder.append(" ");
                builder.append(String.valueOf(chars[i + 1]).toUpperCase());
                i++;
            } else builder.append(chars[i]);
        }
        return builder.toString();
    }
}
