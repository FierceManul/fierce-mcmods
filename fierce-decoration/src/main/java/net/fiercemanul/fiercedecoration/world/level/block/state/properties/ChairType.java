package net.fiercemanul.fiercedecoration.world.level.block.state.properties;

import net.minecraft.util.StringRepresentable;

public enum ChairType implements StringRepresentable {
    SINGLE("single"),
    LEFT("left"),
    RIGHT("right"),
    CENTER("center");


    private final String name;

    ChairType(String pName) {
        this.name = pName;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

    public ChairType getOpposite() {
        return switch(this) {
            case SINGLE -> SINGLE;
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
            case CENTER -> CENTER;
        };
    }
}
