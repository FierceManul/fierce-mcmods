package net.fiercemanul.fiercesource.registries;

import net.fiercemanul.fiercesource.world.item.CreativeModeTabItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.TreeSet;

import static net.fiercemanul.fiercesource.registries.FSBlocksAndItems.*;

public final class FSCreativeModeTabs {


    public static final TreeSet<CreativeModeTabItem> TAB_ITEMS = new TreeSet<>();
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab>
            MAIN_TAB = FCRegistries.CREATIVE_MODE_TAB.register(
            "fiercecraft", () -> CreativeModeTab
                    .builder()
                    .title(Component.translatable("itemGroup.fiercecraft"))
                    .withTabsBefore(net.minecraft.world.item.CreativeModeTabs.SPAWN_EGGS)
                    .icon(() -> LARGE_MANA_CRYSTAL.getItem().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        for (CreativeModeTabItem tabItem : TAB_ITEMS) output.accept(tabItem.itemLike());
                        if (parameters.hasPermissions()) {
                            output.accept(TEST_BLOCK.getItem());
                            output.accept(TEST_ITEM);
                        }
                    })
                    .build()
    );
    private static int priority = 0;

    public static void init() {
        priority = 0;
        TAB_ITEMS.add(new CreativeModeTabItem(SOUL_CRYSTAL_SHARD, getPriority()));
        TAB_ITEMS.add(new CreativeModeTabItem(SOUL_CRYSTAL_DUST, getPriority()));
        TAB_ITEMS.add(new CreativeModeTabItem(SMALL_SOUL_CRYSTAL, getPriority()));
        TAB_ITEMS.add(new CreativeModeTabItem(MEDIUM_SOUL_CRYSTAL, getPriority()));
        TAB_ITEMS.add(new CreativeModeTabItem(LARGE_SOUL_CRYSTAL, getPriority()));
        TAB_ITEMS.add(new CreativeModeTabItem(SMALL_MANA_CRYSTAL, getPriority()));
        TAB_ITEMS.add(new CreativeModeTabItem(MEDIUM_MANA_CRYSTAL, getPriority()));
        TAB_ITEMS.add(new CreativeModeTabItem(LARGE_MANA_CRYSTAL, getPriority()));
        TAB_ITEMS.add(new CreativeModeTabItem(CREATIVE_MANA_BLOCK, getPriority(100)));
        TAB_ITEMS.add(new CreativeModeTabItem(CREATIVE_MANA_OUTPUT, getPriority()));
        TAB_ITEMS.add(new CreativeModeTabItem(HYPERCUBE, getPriority(100)));
        TAB_ITEMS.add(new CreativeModeTabItem(WORLD_LOCATOR, getPriority()));
        TAB_ITEMS.add(new CreativeModeTabItem(CLAW_HAMMER_ITEM, getPriority(100)));
        TAB_ITEMS.add(new CreativeModeTabItem(NETHERITE_CLAW_HAMMER_ITEM, getPriority()));
        TAB_ITEMS.add(new CreativeModeTabItem(CROWBAR_ITEM, getPriority()));
        TAB_ITEMS.add(new CreativeModeTabItem(NETHERITE_CROWBAR_ITEM, getPriority()));
    }

    public static int getPriority() {
        return ++priority;
    }

    public static int getPriority(int i) {
        priority += i + 1;
        return priority;
    }
}
