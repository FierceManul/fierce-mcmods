package net.fiercemanul.fiercelive.data.gathers;

import net.fiercemanul.fiercelive.FierceLive;
import net.fiercemanul.fiercelive.data.FLBlocks;
import net.fiercemanul.fiercelive.data.registries.FLRegister;
import net.fiercemanul.fiercelive.data.tags.FLBlockTags;
import net.fiercemanul.fiercelive.data.tags.FLItemTags;
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

        add(FLItemTags.RAINBOW_DYES, "Rainbow Dyes");
        add(FLItemTags.RAINBOW_DYED, "Rainbow Dyed");
        add(FLItemTags.GLASS_LAMPS, "Glass Lamps");
        add(FLItemTags.GUARDRAILS, "Guardrails");
        add(FLItemTags.CROSS_HOLES, "Cross Holes");
        add(FLItemTags.CUT_BLOCKS, "Cut Blocks");
        add(FLItemTags.PILLARS, "Pillars");
        add(FLItemTags.SEA_LANTERNS, "Sea Lanterns");
        add(FLItemTags.REINFORCED_SEA_LANTERNS, "Reinforced Sea Lanterns");
        add(FLItemTags.TABLES, "Tables");
        add(FLItemTags.CABINETS, "Cabinets");
        add(FLItemTags.WOOL_SOFAS, "Wool Sofas");
        add(FLItemTags.COLOR_LAMPS, "Color Lamps");
        add(FLItemTags.IRON_CORRIDORS, "Iron Corridors");
        add(FLItemTags.TOOLS_CROWBAR, "Crowbar");
        add(FLItemTags.CROWBARS, "Crowbars");
        add(FLItemTags.WEAPON_METEOR_HAMMER, "Meteor Hammer");
        add(FLItemTags.METEOR_HAMMERS, "Meteor Hammers");
        add(FLItemTags.DOOR_SHIELDS, "Door Shields");
        add(FLItemTags.HANDY_REDSTONE, "Chi Stone Tech");

        add(FLBlockTags.RAINBOW_DYED, "Rainbow Dyed");
        add(FLBlockTags.MINEABLE_WITH_CROWBAR, "Mineable with Crowbar");
        add(FLBlockTags.MINEABLE_WITH_GLASS_KNIFE, "Mineable with Glass Knife");
        add(FLBlockTags.GLASS_LAMPS, "Glass Lamps");
        add(FLBlockTags.GUARDRAILS, "Guardrails");
        add(FLBlockTags.CROSS_HOLES, "Cross Holes");
        add(FLBlockTags.CUT_BLOCKS, "Cut Blocks");
        add(FLBlockTags.PILLARS, "Pillars");
        add(FLBlockTags.PILLAR_FORCE_CONNECT_UP, "Pillar Force Connect Up");
        add(FLBlockTags.PILLAR_FORCE_CONNECT_DOWN, "Pillar Force Connect Down");
        add(FLBlockTags.PILLAR_FORCE_CONNECT_SIDE, "Pillar Force Connect Side");
        add(FLBlockTags.SEA_LANTERNS, "Sea Lanterns");
        add(FLBlockTags.REINFORCED_SEA_LANTERNS, "Reinforced Sea Lanterns");
        add(FLBlockTags.TABLES, "Tables");
        add(FLBlockTags.CABINETS, "Cabinets");
        add(FLBlockTags.TABLE_CONNECT, "Table Connect");
        add(FLBlockTags.WOOL_SOFAS, "Wool Sofas");
        add(FLBlockTags.COLOR_LAMPS, "Color Lamps");
        add(FLBlockTags.FRAMES, "Frames");
        add(FLBlockTags.IRON_CORRIDORS, "Iron Corridors");
        add(FLBlockTags.HANDY_REDSTONE, "Chi Stone Tech");

        add("enchantment.fiercelive.poison_aspect", "Poison Aspect");

        add("item_group.fiercelive.goods", "Fierce Living - Goods");
        add("item_group.fiercelive.templateite", "Fierce Living - Templateite");
        add("item_group.fiercelive.colored", "Fierce Living - Colored");
        
        add("tip.fiercelive.snake_vertical", "Snake place to vertical");
        add("tip.fiercelive.snake_horizontal", "Snake place to horizontal");
        add("tip.fiercelive.no_more_be", "Not BlockEntity");
        
        add("container.fiercelive.cabinet", "Cabinet");

        add("advancements.fiercelive.root.title", "Fierce Living");
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
        add("advancements.fiercelive.parrot.title", "是虎钳！");
        add("advancements.fiercelive.parrot.description", "获得飞天老虎钳");
        add("advancements.fiercelive.handy_redstone.title", "咸鱼红石");
        add("advancements.fiercelive.handy_redstone.description", "获得任意红石科技咸鱼版");
        
        add("fml.menu.mods.info.description.fiercelive", "知足常乐。");


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
