package net.fiercemanul.fiercedecoration.registries;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import java.util.function.BiFunction;

public class MapColorHolders {


    public final Cube none = new Cube(MapColor.NONE);
    public final Cube grass = new Cube(MapColor.GRASS);
    public final Cube sand = new Cube(MapColor.SAND);
    public final Cube wool = new Cube(MapColor.WOOL);
    public final Cube fire = new Cube(MapColor.FIRE);
    public final Cube ice = new Cube(MapColor.ICE);
    public final Cube metal = new Cube(MapColor.METAL);
    public final Cube plant = new Cube(MapColor.PLANT);
    public final Cube snow = new Cube(MapColor.SNOW);
    public final Cube clay = new Cube(MapColor.CLAY);
    public final Cube dirt = new Cube(MapColor.DIRT);
    public final Cube stone = new Cube(MapColor.STONE);
    public final Cube water = new Cube(MapColor.WATER);
    public final Cube wood = new Cube(MapColor.WOOD);
    public final Cube quartz = new Cube(MapColor.QUARTZ);
    public final Cube color_orange = new Cube(MapColor.COLOR_ORANGE);
    public final Cube color_magenta = new Cube(MapColor.COLOR_MAGENTA);
    public final Cube color_light_blue = new Cube(MapColor.COLOR_LIGHT_BLUE);
    public final Cube color_yellow = new Cube(MapColor.COLOR_YELLOW);
    public final Cube color_light_green = new Cube(MapColor.COLOR_LIGHT_GREEN);
    public final Cube color_pink = new Cube(MapColor.COLOR_PINK);
    public final Cube color_gray = new Cube(MapColor.COLOR_GRAY);
    public final Cube color_light_gray = new Cube(MapColor.COLOR_LIGHT_GRAY);
    public final Cube color_cyan = new Cube(MapColor.COLOR_CYAN);
    public final Cube color_purple = new Cube(MapColor.COLOR_PURPLE);
    public final Cube color_blue = new Cube(MapColor.COLOR_BLUE);
    public final Cube color_brown = new Cube(MapColor.COLOR_BROWN);
    public final Cube color_green = new Cube(MapColor.COLOR_GREEN);
    public final Cube color_red = new Cube(MapColor.COLOR_RED);
    public final Cube color_black = new Cube(MapColor.COLOR_BLACK);
    public final Cube gold = new Cube(MapColor.GOLD);
    public final Cube diamond = new Cube(MapColor.DIAMOND);
    public final Cube lapis = new Cube(MapColor.LAPIS);
    public final Cube emerald = new Cube(MapColor.EMERALD);
    public final Cube podzol = new Cube(MapColor.PODZOL);
    public final Cube nether = new Cube(MapColor.NETHER);
    public final Cube terracotta_white = new Cube(MapColor.TERRACOTTA_WHITE);
    public final Cube terracotta_orange = new Cube(MapColor.TERRACOTTA_ORANGE);
    public final Cube terracotta_magenta = new Cube(MapColor.TERRACOTTA_MAGENTA);
    public final Cube terracotta_light_blue = new Cube(MapColor.TERRACOTTA_LIGHT_BLUE);
    public final Cube terracotta_yellow = new Cube(MapColor.TERRACOTTA_YELLOW);
    public final Cube terracotta_light_green = new Cube(MapColor.TERRACOTTA_LIGHT_GREEN);
    public final Cube terracotta_pink = new Cube(MapColor.TERRACOTTA_PINK);
    public final Cube terracotta_gray = new Cube(MapColor.TERRACOTTA_GRAY);
    public final Cube terracotta_light_gray = new Cube(MapColor.TERRACOTTA_LIGHT_GRAY);
    public final Cube terracotta_cyan = new Cube(MapColor.TERRACOTTA_CYAN);
    public final Cube terracotta_purple = new Cube(MapColor.TERRACOTTA_PURPLE);
    public final Cube terracotta_blue = new Cube(MapColor.TERRACOTTA_BLUE);
    public final Cube terracotta_brown = new Cube(MapColor.TERRACOTTA_BROWN);
    public final Cube terracotta_green = new Cube(MapColor.TERRACOTTA_GREEN);
    public final Cube terracotta_red = new Cube(MapColor.TERRACOTTA_RED);
    public final Cube terracotta_black = new Cube(MapColor.TERRACOTTA_BLACK);
    public final Cube crimson_nylium = new Cube(MapColor.CRIMSON_NYLIUM);
    public final Cube crimson_stem = new Cube(MapColor.CRIMSON_STEM);
    public final Cube crimson_hyphae = new Cube(MapColor.CRIMSON_HYPHAE);
    public final Cube warped_nylium = new Cube(MapColor.WARPED_NYLIUM);
    public final Cube warped_stem = new Cube(MapColor.WARPED_STEM);
    public final Cube warped_hyphae = new Cube(MapColor.WARPED_HYPHAE);
    public final Cube warped_wart_block = new Cube(MapColor.WARPED_WART_BLOCK);
    public final Cube deepslate = new Cube(MapColor.DEEPSLATE);
    public final Cube raw_iron = new Cube(MapColor.RAW_IRON);
    public final Cube glow_lichen = new Cube(MapColor.GLOW_LICHEN);


    public static class Cube implements BiFunction<BlockBehaviour.Properties, IMapColorConsumer, BlockBehaviour.Properties> {


        private final MapColor mapColor;

        public Cube(MapColor mapColor) {
            this.mapColor = mapColor;
        }

        @Override
        public BlockBehaviour.Properties apply(BlockBehaviour.Properties properties, IMapColorConsumer mapColorConsumer) {
            return mapColorConsumer.applyCube(properties, mapColor);
        }
    }

    public static class Pillar implements BiFunction<BlockBehaviour.Properties, IMapColorConsumer, BlockBehaviour.Properties> {


        private final MapColor end;
        private final MapColor side;

        public Pillar(MapColor end, MapColor side) {
            this.end = end;
            this.side = side;
        }

        @Override
        public BlockBehaviour.Properties apply(BlockBehaviour.Properties properties, IMapColorConsumer mapColorConsumer) {
            return mapColorConsumer.applyPillar(properties, end, side);
        }
    }

    public static class UpDown implements BiFunction<BlockBehaviour.Properties, IMapColorConsumer, BlockBehaviour.Properties> {


        private final MapColor up;
        private final MapColor down;
        private final MapColor side;

        public UpDown(MapColor up, MapColor down, MapColor side) {
            this.up = up;
            this.down = down;
            this.side = side;
        }

        @Override
        public BlockBehaviour.Properties apply(BlockBehaviour.Properties properties, IMapColorConsumer mapColorConsumer) {
            return mapColorConsumer.applyUpDown(properties, up, down, side);
        }
    }

    public static class Empty implements BiFunction<BlockBehaviour.Properties, IMapColorConsumer, BlockBehaviour.Properties> {


        public Empty(MapColor mapColor) {}

        @Override
        public BlockBehaviour.Properties apply(BlockBehaviour.Properties properties, IMapColorConsumer mapColorConsumer) {
            return mapColorConsumer.applyEmpty(properties);
        }
    }
}
