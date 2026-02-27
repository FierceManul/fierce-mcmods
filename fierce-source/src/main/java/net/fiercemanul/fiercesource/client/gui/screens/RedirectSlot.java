package net.fiercemanul.fiercesource.client.gui.screens;

import net.fiercemanul.fiercesource.world.level.menu.FierceMediaMenu;

public interface RedirectSlot {

    int redirectIndex();

    /**
     * @return 可重定向的槽索引最大值（含）。
     */
    default int maxRedirectIndex() {
        return redirectIndex() + FierceMediaMenu.FAKE_SLOT_SIZE - 1;
    }
}
