package net.fiercemanul.fiercedecoration.data;

import net.fiercemanul.fiercedecoration.FierceDecoration;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class LangGan extends LanguageProvider {

    public LangGan(PackOutput output) {
        super(output, FierceDecoration.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        //FierceDecoration.BLOCKS.getEntries().forEach(blockRegistryObject -> add(blockRegistryObject.get(), blockRegistryObject.getId().getPath().replace('_', ' ')));
        FierceDecoration.ITEMS.getEntries().forEach(itemRegistryObject -> add(itemRegistryObject.get(), nameFormat(itemRegistryObject.getId().getPath())));
        add("itemGroup.fiercedecoration.decoration", "Fierce Decoration");
        add("itemGroup.fiercedecoration.building", "Fierce Building");
        add("itemGroup.fiercedecoration.cut", "Fierce Cutter");
        add("itemGroup.fiercedecoration.colored", "Fierce Color Evil");
        add("fiercedecoration.tip.snake_vertical", "Snake place to vertical");
        add("fiercedecoration.tip.snake_horizontal", "Snake place to horizontal");
        add("fiercedecoration.tip.guardrail", "Stack place make corner");
        add("fiercedecoration.tip.water_cobble", "Give the machine infinite water without lag frame rate");
        add("fiercedecoration.tip.no_more_be", "No more BlockEntity");
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
