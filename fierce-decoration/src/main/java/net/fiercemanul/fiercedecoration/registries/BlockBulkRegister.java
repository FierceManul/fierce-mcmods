package net.fiercemanul.fiercedecoration.registries;

import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.data.loading.DatagenModLoader;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.*;

public final class BlockBulkRegister {


    public static final String[] COLOR_NAMES = {
            "white",
            "orange",
            "magenta",
            "light_blue",
            "yellow",
            "lime",
            "pink",
            "gray",
            "light_gray",
            "cyan",
            "purple",
            "blue",
            "brown",
            "green",
            "red",
            "black"
    };
    public static final Set<DeferredBlock<Block>> CABINETS = new HashSet<>();
    public static final LinkedList<ItemLike> BUILDING_BLOCKS = new LinkedList<>();
    public static final LinkedList<ItemLike> COLORED_BLOCKS = new LinkedList<>();
    private static boolean fired = false;
    private static BlockBulkRegister register = null;

    public static void starRegister() {
        if (!fired) register = new BlockBulkRegister();
        fired = true;
    }

    public static Map<DeferredBlock<Block>, BlockBulkRegisterKey> getDataGenWorks() {
        return register.dataGenWorks;
    }

    public static void clean() {
        register = null;
    }

    private final boolean isData = DatagenModLoader.isRunningDataGen();
    private final Map<DeferredBlock<Block>, BlockBulkRegisterKey> dataGenWorks = new LinkedHashMap<>();

    private BlockBulkRegister() {
        new BlockBulkRegisterKeys().keys.forEach(key -> {
            if (key.hasProperty(BlockBulkRegisterProperties.Colored.class)) BlockBulkRegister.COLORED_BLOCKS.add(key.blockItemLike);
            else BlockBulkRegister.BUILDING_BLOCKS.add(key.blockItemLike);
            key.getRegisterProperties().forEach(property -> property.register(key, this::addDataGenRule));
        });
    }

    private void addDataGenRule(DeferredBlock<Block> block, BlockBulkRegisterKey key) {
        if (isData) dataGenWorks.put(block, key);
    }
}
