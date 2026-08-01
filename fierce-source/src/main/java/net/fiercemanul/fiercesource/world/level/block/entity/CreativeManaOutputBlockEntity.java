package net.fiercemanul.fiercesource.world.level.block.entity;

import net.fiercemanul.fiercesource.data.FSBlockEntityTypes;
import net.fiercemanul.fiercesource.world.level.capabilities.FSCapabilities;
import net.fiercemanul.fiercesource.world.level.capabilities.IManaHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.BlockCapabilityCache;

import java.util.HashMap;
import java.util.Map;

public class CreativeManaOutputBlockEntity extends BlockEntity {


    private final Map<Direction, BlockCapabilityCache<IManaHandler, Direction>> cache = new HashMap<>();

    public CreativeManaOutputBlockEntity(BlockPos pos, BlockState state) {
        super(FSBlockEntityTypes.CREATIVE_MANA_OUTPUT_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if (level instanceof ServerLevel serverLevel) for (Direction direction : Direction.values())
            cache.put(
                    direction, BlockCapabilityCache.create(
                    FSCapabilities.BLOCK_MANA_CAP,
                    serverLevel,
                    getBlockPos().relative(direction),
                    direction.getOpposite(),
                    () -> !isRemoved(),
                    () -> {}
            ));
    }

    public void serverTick() {
        for (Direction direction : Direction.values()) {
            var cap = cache.get(direction).getCapability();
            if (cap != null) cap.receiveMana(Long.MAX_VALUE, false);
        }
    }

}
