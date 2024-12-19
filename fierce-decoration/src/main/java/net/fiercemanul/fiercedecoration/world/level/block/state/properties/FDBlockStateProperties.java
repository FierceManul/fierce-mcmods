package net.fiercemanul.fiercedecoration.world.level.block.state.properties;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class FDBlockStateProperties {


    public static final DirectionProperty ATTACH = DirectionProperty.create("attach", Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.UP, Direction.DOWN);
    public static final DirectionProperty HORIZONTAL_ATTACH = DirectionProperty.create("attach", Direction.Plane.HORIZONTAL);
    public static final IntegerProperty HALF_LAYERS = IntegerProperty.create("layers", 0, 4);
    public static final BooleanProperty DOUBLE = BooleanProperty.create("double");
    public static final BooleanProperty PART_A = BooleanProperty.create("part_a");
    public static final BooleanProperty PART_B = BooleanProperty.create("part_b");
    public static final BooleanProperty PART_C = BooleanProperty.create("part_c");
    public static final BooleanProperty PART_D = BooleanProperty.create("part_d");
    public static final BooleanProperty PART_E = BooleanProperty.create("part_e");
    public static final BooleanProperty PART_F = BooleanProperty.create("part_f");
    public static final BooleanProperty PART_G = BooleanProperty.create("part_g");
    public static final BooleanProperty PART_H = BooleanProperty.create("part_h");
    public static final EnumProperty<LongBlockType> LONG_BLOCK_TYPE = EnumProperty.create("type", LongBlockType.class);
    public static final EnumProperty<TallBlockType> TALL_BLOCK_TYPE = EnumProperty.create("type", TallBlockType.class);
    public static final EnumProperty<ComplexCabinetType> CABINET_TYPE = EnumProperty.create("type", ComplexCabinetType.class);
    public static final EnumProperty<ComplexCabinetType> CABINET_TYPE_A = EnumProperty.create("type", ComplexCabinetType.class, ComplexCabinetType.SINGLE, ComplexCabinetType.TOP, ComplexCabinetType.BOTTOM);
    public static final EnumProperty<ComplexCabinetType> CABINET_TYPE_C = EnumProperty.create("type", ComplexCabinetType.class, ComplexCabinetType.SINGLE, ComplexCabinetType.LEFT, ComplexCabinetType.RIGHT);
    public static final EnumProperty<OpenType> OPEN_TYPE = EnumProperty.create("open_type", OpenType.class);

}
