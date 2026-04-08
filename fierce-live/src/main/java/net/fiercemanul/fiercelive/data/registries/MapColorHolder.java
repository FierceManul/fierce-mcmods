package net.fiercemanul.fiercelive.data.registries;

import net.minecraft.world.level.material.MapColor;

public interface MapColorHolder {

    MapColor top();
    MapColor side();
    MapColor bottom();

    MapColorHolder NONE = new MapColorHolder() {

        @Override
        public MapColor top() {
            return MapColor.NONE;
        }

        @Override
        public MapColor side() {
            return MapColor.NONE;
        }

        @Override
        public MapColor bottom() {
            return MapColor.NONE;
        }
    };

    static MapColorHolder cube(MapColor mapColor) {
        return new MapColorHolder() {

            @Override
            public MapColor top() {
                return mapColor;
            }

            @Override
            public MapColor side() {
                return mapColor;
            }

            @Override
            public MapColor bottom() {
                return mapColor;
            }
        };
    }

    static MapColorHolder pillar(MapColor end, MapColor side) {
        return new MapColorHolder() {

            @Override
            public MapColor top() {
                return end;
            }

            @Override
            public MapColor side() {
                return side;
            }

            @Override
            public MapColor bottom() {
                return end;
            }
        };
    }

    static MapColorHolder topBottom(MapColor top, MapColor bottom, MapColor side) {
        return new MapColorHolder() {

            @Override
            public MapColor top() {
                return top;
            }

            @Override
            public MapColor side() {
                return side;
            }

            @Override
            public MapColor bottom() {
                return bottom;
            }
        };
    }
}
