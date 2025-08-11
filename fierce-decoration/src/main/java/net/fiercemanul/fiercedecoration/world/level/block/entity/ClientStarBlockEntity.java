package net.fiercemanul.fiercedecoration.world.level.block.entity;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ClientStarBlockEntity extends StarBlockEntity {


    public boolean north = true;
    public boolean south = true;
    public boolean east = true;
    public boolean west = true;
    public boolean up = true;
    public boolean down = true;


    public ClientStarBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    public void updateFace(Direction direction, boolean b) {
        switch (direction) {
            case NORTH -> north = b;
            case SOUTH -> south = b;
            case WEST -> west = b;
            case EAST -> east = b;
            case DOWN -> down = b;
            case UP -> up = b;
        }
    }
}
