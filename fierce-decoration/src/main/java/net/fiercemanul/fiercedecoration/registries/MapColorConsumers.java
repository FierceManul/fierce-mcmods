package net.fiercemanul.fiercedecoration.registries;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;

public class MapColorConsumers {


    public final IMapColorConsumer top = new IMapColorConsumer() {
        @Override
        public BlockBehaviour.Properties applyPillar(BlockBehaviour.Properties properties, MapColor end, MapColor side) {
            return properties.mapColor(end);
        }

        @Override
        public BlockBehaviour.Properties applyUpDown(BlockBehaviour.Properties properties, MapColor up, MapColor down, MapColor side) {
            return properties.mapColor(up);
        }
    };

    public final IMapColorConsumer facing = new IMapColorConsumer() {
        @Override
        public BlockBehaviour.Properties applyPillar(BlockBehaviour.Properties properties, MapColor end, MapColor side) {
            return properties.mapColor(state -> state.getValue(BlockStateProperties.FACING).getAxis().equals(Direction.Axis.Y) ? end : side);
        }

        @Override
        public BlockBehaviour.Properties applyUpDown(BlockBehaviour.Properties properties, MapColor up, MapColor down, MapColor side) {
            return properties.mapColor(state -> {
                Direction facing = state.getValue(BlockStateProperties.FACING);
                return facing.equals(Direction.UP) ? up : facing.equals(Direction.DOWN) ? down : side;
            });
        }
    };

    public final IMapColorConsumer axis = new IMapColorConsumer() {


        @Override
        public BlockBehaviour.Properties applyPillar(BlockBehaviour.Properties properties, MapColor end, MapColor side) {
            return properties.mapColor(state -> state.getValue(BlockStateProperties.AXIS).equals(Direction.Axis.Y) ? end : side);
        }

        @Override
        public BlockBehaviour.Properties applyUpDown(BlockBehaviour.Properties properties, MapColor up, MapColor down, MapColor side) {
            return properties.mapColor(state -> state.getValue(BlockStateProperties.AXIS).equals(Direction.Axis.Y) ? up : side);
        }
    };

    public final IMapColorConsumer side = new IMapColorConsumer() {
        @Override
        public BlockBehaviour.Properties applyPillar(BlockBehaviour.Properties properties, MapColor end, MapColor side) {
            return properties.mapColor(side);
        }

        @Override
        public BlockBehaviour.Properties applyUpDown(BlockBehaviour.Properties properties, MapColor up, MapColor down, MapColor side) {
            return properties.mapColor(side);
        }
    };

    public final IMapColorConsumer none = new IMapColorConsumer() {
        @Override
        public BlockBehaviour.Properties applyPillar(BlockBehaviour.Properties properties, MapColor end, MapColor side) {
            return properties.mapColor(MapColor.NONE);
        }

        @Override
        public BlockBehaviour.Properties applyUpDown(BlockBehaviour.Properties properties, MapColor up, MapColor down, MapColor side) {
            return properties.mapColor(MapColor.NONE);
        }
    };

    public final IMapColorConsumer panel = new IMapColorConsumer() {
        @Override
        public BlockBehaviour.Properties applyPillar(BlockBehaviour.Properties properties, MapColor end, MapColor side) {
            return properties.mapColor(state -> state.getValue(BlockStateProperties.FACING).getAxis().equals(Direction.Axis.Y) ? end : MapColor.NONE);
        }

        @Override
        public BlockBehaviour.Properties applyUpDown(BlockBehaviour.Properties properties, MapColor up, MapColor down, MapColor side) {
            return properties.mapColor(state -> switch (state.getValue(BlockStateProperties.FACING)) {
                case DOWN -> down;
                case UP -> up;
                default -> MapColor.NONE;
            });
        }
    };
}
