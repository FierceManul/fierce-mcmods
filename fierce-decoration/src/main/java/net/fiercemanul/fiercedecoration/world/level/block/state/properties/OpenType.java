package net.fiercemanul.fiercedecoration.world.level.block.state.properties;

import net.minecraft.util.StringRepresentable;

public enum OpenType implements StringRepresentable {


    CLOSE("close", false, false),
    FORCE_CLOSE("force_close", false, true),
    OPEN("open", true, true),
    FORCE_OPEN("force_open", true, false);


    private final String name;
    private final boolean isOpen;
    private final boolean isPowered;

    OpenType(String pName, boolean isOpen, boolean isPowered) {
        this.name = pName;
        this.isOpen = isOpen;
        this.isPowered = isPowered;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public boolean isPowered() {
        return isPowered;
    }

    public OpenType update(boolean redstone) {
        return switch (this) {
            case CLOSE, OPEN -> redstone ? OPEN : CLOSE;
            case FORCE_CLOSE -> redstone ? FORCE_CLOSE : CLOSE;
            case FORCE_OPEN -> redstone ? OPEN : FORCE_OPEN;
        };
    }

    public OpenType toggleByHand() {
        return switch (this) {
            case CLOSE -> FORCE_OPEN;
            case FORCE_CLOSE -> OPEN;
            case OPEN -> FORCE_CLOSE;
            case FORCE_OPEN -> CLOSE;
        };
    }

}
