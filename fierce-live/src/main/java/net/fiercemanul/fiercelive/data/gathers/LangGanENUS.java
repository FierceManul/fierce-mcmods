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
        add("fml.menu.mods.info.description.fiercelive", "Natural scenes become a wild delight, And make one forget all mundane feelings.");
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
