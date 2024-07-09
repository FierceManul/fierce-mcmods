package net.fiercemanul.fiercedecoration.world.level.block.state.properties;

import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;

public enum TallBlockType implements StringRepresentable {
    SINGLE("single"),
    TOP("top"),
    BOTTOM("bottom"),
    CENTER("center");


    private final String name;

    TallBlockType(String pName) {
        this.name = pName;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

    public TallBlockType getOpposite() {
        return switch(this) {
            case SINGLE -> SINGLE;
            case TOP -> BOTTOM;
            case BOTTOM -> TOP;
            case CENTER -> CENTER;
        };
    }

    public TallBlockType unConnect(Direction pFacing) {
        if (pFacing.equals(Direction.UP)) {
            return switch(this) {
                case SINGLE, BOTTOM -> SINGLE;
                case TOP, CENTER -> TOP;
            };
        }
        else if (pFacing.equals(Direction.DOWN)) {
            return switch(this) {
                case SINGLE, TOP -> SINGLE;
                case BOTTOM, CENTER -> BOTTOM;
            };
        }
        return this;
    }

    public TallBlockType connect(Direction pFacing) {
        if (pFacing.equals(Direction.UP)) {
            return switch(this) {
                case SINGLE -> BOTTOM;
                case TOP -> CENTER;
                case BOTTOM, CENTER -> this;
            };
        }
        else if (pFacing.equals(Direction.DOWN)) {
            return switch(this) {
                case SINGLE -> TOP;
                case BOTTOM -> CENTER;
                case TOP, CENTER -> this;
            };
        }
        return this;
    }

    public static boolean isConnectedByOther(Direction from, TallBlockType theirType) {
        if (from.equals(Direction.UP)) {
            return switch(theirType) {
                case TOP, CENTER -> true;
                default -> false;
            };
        }
        else if (from.equals(Direction.DOWN)) {
            return switch(theirType) {
                case BOTTOM, CENTER -> true;
                default -> false;
            };
        }
        return false;
    }
}
