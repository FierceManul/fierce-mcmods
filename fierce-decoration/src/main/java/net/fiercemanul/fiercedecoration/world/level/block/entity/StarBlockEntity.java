package net.fiercemanul.fiercedecoration.world.level.block.entity;

import net.fiercemanul.fiercedecoration.FierceDecoration;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;


public class StarBlockEntity extends BlockEntity {


    public boolean north = true;
    public boolean south = true;
    public boolean east = true;
    public boolean west = true;
    public boolean up = true;
    public boolean down = true;
    public boolean needUpdate = true;

    public StarBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(FierceDecoration.STAR_BLOCK_ENTITY.get(), pPos, pBlockState);
    }

    public void updateFace(BlockPos pos, Direction direction) {
        if (level instanceof ClientLevel) {
            switch (direction) {
                case NORTH -> north = Block.shouldRenderFace(getBlockState(), level, pos, direction, pos);
                case SOUTH -> south = Block.shouldRenderFace(getBlockState(), level, pos, direction, pos);
                case WEST -> west = Block.shouldRenderFace(getBlockState(), level, pos, direction, pos);
                case EAST -> east = Block.shouldRenderFace(getBlockState(), level, pos, direction, pos);
                case DOWN -> down = Block.shouldRenderFace(getBlockState(), level, pos, direction, pos);
                case UP -> up = Block.shouldRenderFace(getBlockState(), level, pos, direction, pos);
            }
        }
    }

    public void updateAllFace() {
        if (level instanceof ClientLevel) {
            BlockState state = getBlockState();
            BlockPos northPos = worldPosition.offset(0, 0, -1);
            north = Block.shouldRenderFace(state, level, northPos, Direction.NORTH, northPos);
            BlockPos southPos = worldPosition.offset(0, 0, 1);
            south = Block.shouldRenderFace(state, level, southPos, Direction.SOUTH, southPos);
            BlockPos westPos = worldPosition.offset(-1, 0, 0);
            west = Block.shouldRenderFace(state, level, westPos, Direction.WEST, westPos);
            BlockPos eastPos = worldPosition.offset(1, 0, 0);
            east = Block.shouldRenderFace(state, level, eastPos, Direction.EAST, eastPos);
            BlockPos upPos = worldPosition.offset(0, 1, 0);
            up = Block.shouldRenderFace(state, level, upPos, Direction.UP, upPos);
            BlockPos downPos = worldPosition.offset(0, -1, 0);
            down = Block.shouldRenderFace(state, level, downPos, Direction.DOWN, downPos);
            needUpdate = false;
        }
    }
}
