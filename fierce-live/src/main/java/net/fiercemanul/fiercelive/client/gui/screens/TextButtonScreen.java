package net.fiercemanul.fiercelive.client.gui.screens;

import net.minecraft.client.gui.font.TextFieldHelper;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import javax.annotation.Nullable;

public class TextButtonScreen extends Screen {


    protected int frame;
    protected int line;
    @Nullable
    private TextFieldHelper textFieldHelper;

    protected TextButtonScreen(Component title) {
        super(title);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void tick() {
        frame++;
        if (!isValid()) onDone();
    }

    protected boolean isValid() {
        return true;
        //return minecraft != null && minecraft.player != null && !sign.isRemoved()
        //        && !sign.playerIsTooFarAwayToEdit(this.minecraft.player.getUUID());
    }

    protected void onDone() {
        if (minecraft != null) minecraft.setScreen(null);
    }

}
