package net.fiercemanul.fiercedecoration.registries;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public interface IMapColorConsumer {


    default BlockBehaviour.Properties applyEmpty(BlockBehaviour.Properties properties) {
        return properties.mapColor(MapColor.NONE);
    }

    default BlockBehaviour.Properties applyCube(BlockBehaviour.Properties properties, MapColor color) {
        return properties.mapColor(color);
    }

    BlockBehaviour.Properties applyPillar(BlockBehaviour.Properties properties, MapColor end, MapColor side);

    BlockBehaviour.Properties applyUpDown(BlockBehaviour.Properties properties, MapColor up, MapColor down, MapColor side);

}
