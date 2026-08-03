package net.fiercemanul.fiercesource.world.level.block;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

public final class FSBlockStateProperties {


    public static final DirectionProperty VERTICAL_FACING = DirectionProperty.create("facing", Direction.Plane.VERTICAL);

    private FSBlockStateProperties() {}

}
