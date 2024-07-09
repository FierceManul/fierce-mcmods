package net.fiercemanul.fiercedecoration.world.level.block.state.properties;

import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.DoubleBlockCombiner;

import java.util.Arrays;

public enum ComplexCabinetType implements StringRepresentable {


    //single是左手开门
    SINGLE("single", DoubleBlockCombiner.BlockType.SINGLE, true, false),
    MIRROR("mirror", DoubleBlockCombiner.BlockType.SINGLE, true, true),
    TOP("top", DoubleBlockCombiner.BlockType.FIRST, false, false),
    TOP_MIRROR("top_mirror", DoubleBlockCombiner.BlockType.FIRST, false, true),
    BOTTOM("bottom", DoubleBlockCombiner.BlockType.SECOND, false, false),
    BOTTOM_MIRROR("bottom_mirror", DoubleBlockCombiner.BlockType.SECOND, false, true),
    LEFT("left", DoubleBlockCombiner.BlockType.SECOND, false, false),
    RIGHT("right", DoubleBlockCombiner.BlockType.FIRST, false, false);


    private final String name;
    private final DoubleBlockCombiner.BlockType combinerType;
    private final boolean isSingle;
    private final boolean isMirror;

    ComplexCabinetType(String pName, DoubleBlockCombiner.BlockType combinerType, boolean isSingle, boolean isMirror) {
        this.name = pName;
        this.combinerType = combinerType;
        this.isSingle = isSingle;
        this.isMirror = isMirror;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

    public ComplexCabinetType getOpposite() {
        return switch(this) {
            case SINGLE -> SINGLE;
            case MIRROR -> MIRROR;
            case TOP -> BOTTOM;
            case TOP_MIRROR -> BOTTOM_MIRROR;
            case BOTTOM -> TOP;
            case BOTTOM_MIRROR -> TOP_MIRROR;
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
        };
    }

    public ComplexCabinetType toSingle() {
        return switch(this) {
            case SINGLE, TOP, RIGHT, BOTTOM -> SINGLE;
            case MIRROR, TOP_MIRROR, BOTTOM_MIRROR, LEFT -> MIRROR;
        };
    }

    public DoubleBlockCombiner.BlockType getCombinerType() {
        return combinerType;
    }

    public Direction getConnectDirection(Direction cabinetFacing) {
        return switch (this) {
            case TOP, TOP_MIRROR -> Direction.DOWN;
            case BOTTOM, BOTTOM_MIRROR -> Direction.UP;
            case LEFT -> cabinetFacing.getClockWise();
            case RIGHT -> cabinetFacing.getCounterClockWise();
            default -> throw new IllegalStateException("Unable to get cabinet connect facing direction of " + this);
        };
    }

    private int[] getCanConnectDirections(Direction cabinetFacing) {
        //数值比较应该更快吧
        return switch (this) {
            case SINGLE -> new int[]{Direction.DOWN.get3DDataValue(), Direction.UP.get3DDataValue(), cabinetFacing.getCounterClockWise().get3DDataValue()};
            case MIRROR -> new int[]{Direction.DOWN.get3DDataValue(), Direction.UP.get3DDataValue(), cabinetFacing.getClockWise().get3DDataValue()};
            case TOP, TOP_MIRROR -> new int[]{Direction.DOWN.get3DDataValue()};
            case BOTTOM, BOTTOM_MIRROR -> new int[]{Direction.UP.get3DDataValue()};
            case LEFT -> new int[]{cabinetFacing.getClockWise().get3DDataValue()};
            case RIGHT -> new int[]{cabinetFacing.getCounterClockWise().get3DDataValue()};
        };
    }

    public boolean canConnectTo(Direction connectTo, Direction cabinetFacing, ComplexCabinetType targetType) {
        //水平时不需要检查镜像配对
        return (!connectTo.getAxis().equals(Direction.Axis.Y) || this.isMirror == targetType.isMirror)
                && Arrays.stream(this.getCanConnectDirections(cabinetFacing)).anyMatch(value -> value == connectTo.get3DDataValue())
                && Arrays.stream(targetType.getCanConnectDirections(cabinetFacing)).anyMatch(value -> value == connectTo.getOpposite().get3DDataValue());
    }

    public ComplexCabinetType getConnectToType(Direction facing) {
        return switch (facing) {
            case DOWN -> switch (this) {
                case SINGLE -> TOP;
                case MIRROR -> TOP_MIRROR;
                default -> this;
            };
            case UP -> switch (this) {
                case SINGLE -> BOTTOM;
                case MIRROR -> BOTTOM_MIRROR;
                default -> this;
            };
            default -> switch (this) {
                case SINGLE -> RIGHT;
                case MIRROR -> LEFT;
                default -> this;
            };
        };
    }

    public boolean isSingle() {
        return isSingle;
    }
}
