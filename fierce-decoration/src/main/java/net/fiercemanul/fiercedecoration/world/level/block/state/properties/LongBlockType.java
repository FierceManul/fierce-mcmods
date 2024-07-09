package net.fiercemanul.fiercedecoration.world.level.block.state.properties;

import net.minecraft.util.StringRepresentable;

public enum LongBlockType implements StringRepresentable {
    SINGLE("single"),
    LEFT("left"),
    RIGHT("right"),
    CENTER("center");


    private final String name;

    LongBlockType(String pName) {
        this.name = pName;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

    public LongBlockType getOpposite() {
        return switch(this) {
            case SINGLE -> SINGLE;
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
            case CENTER -> CENTER;
        };
    }
}
