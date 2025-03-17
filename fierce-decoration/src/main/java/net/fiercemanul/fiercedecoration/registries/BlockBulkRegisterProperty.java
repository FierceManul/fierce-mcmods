package net.fiercemanul.fiercedecoration.registries;

import net.fiercemanul.fiercedecoration.FierceDecoration;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.function.BiConsumer;
import java.util.function.Function;

public class BlockBulkRegisterProperty implements IBlockBulkRegisterProperty {


    protected final String path;
    private final Function<BlockBehaviour.Properties, ? extends Block> blockFunc;
    private final Function<BlockBulkRegisterKey, BlockBehaviour.Properties> propertiesFun;
    private final IMapColorConsumer mapColorConsumer;

    public BlockBulkRegisterProperty(
            String path,
            Function<BlockBehaviour.Properties, ? extends Block> blockFunc,
            Function<BlockBulkRegisterKey, BlockBehaviour.Properties> propertiesFun,
            IMapColorConsumer mapColorConsumer
    ) {
        this.path = path;
        this.blockFunc = blockFunc;
        this.propertiesFun = propertiesFun;
        this.mapColorConsumer = mapColorConsumer;
    }

    protected String makePath(String sourcePath) {
        return sourcePath + "_" + path;
    }

    @Override
    public void register(BlockBulkRegisterKey key, BiConsumer<DeferredBlock<Block>, BlockBulkRegisterKey> dataGen) {
        DeferredBlock<Block> block = FierceDecoration.BLOCKS.registerBlock(makePath(key.getPath()), blockFunc, key.getMapColorHolder().apply(propertiesFun.apply(key), mapColorConsumer));
        DeferredItem<BlockItem> blockItem = FierceDecoration.ITEMS.registerSimpleBlockItem(block);
        if (key.hasProperty(BlockBulkRegisterProperties.Colored.class)) BlockBulkRegister.COLORED_BLOCKS.add(blockItem);
        else BlockBulkRegister.BUILDING_BLOCKS.add(blockItem);
        dataGen.accept(block, key);
    }
}
