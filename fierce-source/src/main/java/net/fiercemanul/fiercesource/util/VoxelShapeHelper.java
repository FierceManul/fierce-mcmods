package net.fiercemanul.fiercesource.util;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.LinkedList;
import java.util.function.Function;

public class VoxelShapeHelper {

    private final LinkedList<Cube> cubes = new LinkedList<>();

    public VoxelShapeHelper applyCube(double xa, double ya, double za, double xb, double yb, double zb) {
        cubes.add(new Cube(xa, ya, za, xb, yb, zb));
        return this;
    }

    public VoxelShapeHelper rotationY180 () {
        VoxelShapeHelper helper = new VoxelShapeHelper();
        for (Cube cube : cubes) {
            helper.applyCube(
                    16.0 - cube.xb,
                    cube.ya,
                    16.0 - cube.zb,
                    16.0 - cube.xa,
                    cube.yb,
                    16.0 - cube.za
            );
        }
        return helper;
    }

    public VoxelShapeHelper rotationY270 () {
        VoxelShapeHelper helper = new VoxelShapeHelper();
        for (Cube cube : cubes) {
            helper.applyCube(
                    cube.za,
                    cube.ya,
                    16.0 - cube.xb,
                    cube.zb,
                    cube.yb,
                    16.0 - cube.xa
            );
        }
        return helper;
    }

    public VoxelShapeHelper rotationY90 () {
        VoxelShapeHelper helper = new VoxelShapeHelper();
        for (Cube cube : cubes) {
            helper.applyCube(
                    16.0 - cube.zb,
                    cube.ya,
                    cube.xa,
                    16.0 - cube.za,
                    cube.yb,
                    cube.xb
            );
        }
        return helper;
    }

    public VoxelShapeHelper rotationX270 () {
        VoxelShapeHelper helper = new VoxelShapeHelper();
        for (Cube cube : cubes) {
            helper.applyCube(
                    cube.xa,
                    16.0 - cube.zb,
                    cube.ya,
                    cube.xb,
                    16.0 - cube.za,
                    cube.yb
            );
        }
        return helper;
    }

    public VoxelShapeHelper rotationX90 () {
        VoxelShapeHelper helper = new VoxelShapeHelper();
        for (Cube cube : cubes) {
            helper.applyCube(
                    cube.xa,
                    cube.za,
                    16.0 - cube.yb,
                    cube.xb,
                    cube.zb,
                    16.0 - cube.ya
            );
        }
        return helper;
    }

    public VoxelShape north() {
        VoxelShape shape = Shapes.empty();
        for (Cube cube : cubes) {
            shape = Shapes.or(shape, Block.box(
                    cube.xa,
                    cube.ya,
                    cube.za,
                    cube.xb,
                    cube.yb,
                    cube.zb
            ));
        }
        return shape;
    }

    public VoxelShape south() {
        VoxelShape shape = Shapes.empty();
        for (Cube cube : cubes) {
            shape = Shapes.or(shape, Block.box(
                    16.0 - cube.xb,
                    cube.ya,
                    16.0 - cube.zb,
                    16.0 - cube.xa,
                    cube.yb,
                    16.0 - cube.za
            ));
        }
        return shape;
    }

    public VoxelShape west() {
        VoxelShape shape = Shapes.empty();
        for (Cube cube : cubes) {
            shape = Shapes.or(shape, Block.box(
                    cube.za,
                    cube.ya,
                    16.0 - cube.xb,
                    cube.zb,
                    cube.yb,
                    16.0 - cube.xa
            ));
        }
        return shape;
    }

    public VoxelShape east() {
        VoxelShape shape = Shapes.empty();
        for (Cube cube : cubes) {
            shape = Shapes.or(shape, Block.box(
                    16.0 - cube.zb,
                    cube.ya,
                    cube.xa,
                    16.0 - cube.za,
                    cube.yb,
                    cube.xb
            ));
        }
        return shape;
    }

    public VoxelShape up() {
        VoxelShape shape = Shapes.empty();
        for (Cube cube : cubes) {
            shape = Shapes.or(shape, Block.box(
                    cube.xa,
                    16.0 - cube.zb,
                    cube.ya,
                    cube.xb,
                    16.0 - cube.za,
                    cube.yb
            ));
        }
        return shape;
    }

    public VoxelShape down() {
        VoxelShape shape = Shapes.empty();
        for (Cube cube : cubes) {
            shape = Shapes.or(shape, Block.box(
                    cube.xa,
                    cube.za,
                    16.0 - cube.yb,
                    cube.xb,
                    cube.zb,
                    16.0 - cube.ya
            ));
        }
        return shape;
    }

    public static Function<VoxelShapeHelper, VoxelShape> getShapeFun(Direction direction) {
        return switch (direction) {
            case DOWN -> VoxelShapeHelper::down;
            case UP -> VoxelShapeHelper::up;
            case NORTH -> VoxelShapeHelper::north;
            case SOUTH -> VoxelShapeHelper::south;
            case WEST -> VoxelShapeHelper::west;
            case EAST -> VoxelShapeHelper::east;
        };
    }

    public static Vec3 south(Vec3 vec3) {
        return new Vec3(1.0 - vec3.x, vec3.y, 1.0 - vec3.z);
    }

    public static Vec3 west(Vec3 vec3) {
        return new Vec3(vec3.z, vec3.y, 1.0 - vec3.x);
    }

    public static Vec3 east(Vec3 vec3) {
        return new Vec3(1.0 - vec3.z, vec3.y, vec3.x);
    }

    public static Vec3 up(Vec3 vec3) {
        return new Vec3(vec3.x, 1.0 - vec3.z, vec3.y);
    }

    public static Vec3 down(Vec3 vec3) {
        return new Vec3(vec3.x, vec3.z, 1.0 - vec3.y);
    }

    private record Cube(double xa, double ya, double za, double xb, double yb, double zb) {}
}
