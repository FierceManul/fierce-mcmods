package net.fiercemanul.fiercedecoration.world.level.block.state.properties;

import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public enum WindowBType implements StringRepresentable {

    SINGLE("single"),
    TOP("top"),
    BOTTOM("bottom"),
    BOTTOM_HANDLE("bottom_handle"),
    HANDLE("handle"),
    CENTER("center");


    /**
     * downType, upType, type
     */
    private static final Map<WindowBType, Map<WindowBType, WindowBType>> CONNECT_MAP = buildConnectMap();

    private static Map<WindowBType, Map<WindowBType, WindowBType>> buildConnectMap() {
        Map<WindowBType, Map<WindowBType, WindowBType>> map = new HashMap<>();
        for (WindowBType downValue : WindowBType.values()) {
            Map<WindowBType, WindowBType> map2 = new HashMap<>();
            switch (downValue) {
                case SINGLE, TOP -> {
                    for (WindowBType upValue : WindowBType.values()) {
                        map2.put(upValue, switch (upValue) {
                            case SINGLE, BOTTOM, BOTTOM_HANDLE -> SINGLE;
                            case TOP, CENTER -> BOTTOM_HANDLE;
                            case HANDLE -> BOTTOM;
                        });
                    }
                }
                case BOTTOM, BOTTOM_HANDLE -> {
                    for (WindowBType upValue : WindowBType.values()) {
                        map2.put(upValue, switch (upValue) {
                            case SINGLE, BOTTOM, BOTTOM_HANDLE -> TOP;
                            case TOP, CENTER, HANDLE -> HANDLE;
                        });
                    }
                }
                case HANDLE, CENTER -> {
                    for (WindowBType upValue : WindowBType.values()) {
                        map2.put(upValue, switch (upValue) {
                            case SINGLE, BOTTOM, BOTTOM_HANDLE -> TOP;
                            case TOP, HANDLE, CENTER -> CENTER;
                        });
                    }
                }
            }
            map.put(downValue, map2);
        }
        return map;
    }

    /**
     * downType, upType, type
     */
    private static final Map<WindowBType, Map<WindowBType, WindowBType>> PLACE_MAP = buildPlaceMap();

    private static Map<WindowBType, Map<WindowBType, WindowBType>> buildPlaceMap() {
        Map<WindowBType, Map<WindowBType, WindowBType>> map = new HashMap<>();
        for (WindowBType downValue : WindowBType.values()) {
            Map<WindowBType, WindowBType> map2 = new HashMap<>();
            switch (downValue) {
                case SINGLE, BOTTOM, BOTTOM_HANDLE -> {
                    for (WindowBType upValue : WindowBType.values()) {
                        map2.put(upValue, HANDLE);
                    }
                    map2.put(null, TOP);
                }
                case TOP, HANDLE, CENTER -> {
                    for (WindowBType upValue : WindowBType.values()) {
                        map2.put(upValue, CENTER);
                    }
                    map2.put(null, TOP);
                }
            }
            map.put(downValue, map2);
        }
        Map<WindowBType, WindowBType> map3 = new HashMap<>();
        for (WindowBType upValue : WindowBType.values()) {
            map3.put(upValue, switch (upValue) {
                case SINGLE, TOP -> BOTTOM_HANDLE;
                case BOTTOM, BOTTOM_HANDLE, HANDLE, CENTER -> BOTTOM;
            });
            map3.put(null, SINGLE);
        }
        map.put(null, map3);
        return map;
    }

    public static WindowBType getTypeForPlacement(@Nullable WindowBType down, @Nullable WindowBType up) {
        return PLACE_MAP.get(down).get(up);
    }

    public static WindowBType updateType(WindowBType topType, WindowBType bottomType) {
        return CONNECT_MAP.get(bottomType).get(topType);
    }

    private final String name;

    WindowBType(String pName) {
        this.name = pName;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

    public WindowBType getOpposite() {
        return switch(this) {
            case SINGLE -> SINGLE;
            case TOP -> BOTTOM;
            case BOTTOM, BOTTOM_HANDLE -> TOP;
            case HANDLE, CENTER -> CENTER;
        };
    }

    public static boolean isValidConnectType(Direction from, WindowBType theirType) {
        if (from.equals(Direction.UP)) {
            return switch(theirType) {
                case TOP, CENTER, HANDLE -> true;
                default -> false;
            };
        }
        else if (from.equals(Direction.DOWN)) {
            return switch(theirType) {
                case BOTTOM, BOTTOM_HANDLE, CENTER, HANDLE -> true;
                default -> false;
            };
        }
        return false;
    }
}
