package net.fiercemanul.fiercelive.data.registries;

import net.fiercemanul.fiercelive.FierceLive;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.HashSet;
import java.util.function.Supplier;

public record BlockMaterial(
        ResourceLocation materialRl,
        ResourceLocation blockRl,
        Supplier<? extends Block> block,
        Supplier<BlockBehaviour.Properties> properties,
        MapColorHolder mapColorHolder,
        HashSet<BlockMaterialTag> tags
) implements ItemLike {

    public static BlockMaterial mcBlock(
            Block block,
            MapColorHolder mapColorHolder,
            HashSet<BlockMaterialTag> tags
    ) {
        ResourceLocation rlb = BuiltInRegistries.BLOCK.getKey(block);
        return new BlockMaterial(
                ResourceLocation.fromNamespaceAndPath(
                        FierceLive.MODID,
                        rlb.getPath()
                           .replace("bricks", "brick")
                           .replace("tiles", "tile")
                ),
                rlb,
                () -> block,
                () -> BlockBehaviour.Properties.ofFullCopy(block),
                mapColorHolder,
                tags
        );
    }

    public static BlockMaterial deferredBlock(
            DeferredBlock<? extends Block> deferredBlock,
            MapColorHolder mapColorHolder,
            HashSet<BlockMaterialTag> tags
    ) {
        ResourceLocation id = deferredBlock.getId();
        return new BlockMaterial(
                id, id,
                deferredBlock,
                () -> BlockBehaviour.Properties.ofFullCopy(deferredBlock.get()),
                mapColorHolder,
                tags
        );
    }

    public Block getBlock() {
        return block.get();
    }

    public String getNamespace() {
        return materialRl.getNamespace();
    }

    public String getPath() {
        return materialRl.getPath();
    }

    public BlockBehaviour.Properties getProperties() {
        return properties.get();
    }

    public boolean hasTag(BlockMaterialTag tag) {
        return tags.contains(tag);
    }

    @Override
    public Item asItem() {
        return block.get().asItem();
    }

    public boolean hasAllTags(BlockMaterialTag... tags) {
        for (BlockMaterialTag tag : tags) if (!this.tags.contains(tag)) return false;
        return true;
    }

    public boolean hasAnyTags(BlockMaterialTag... tags) {
        for (BlockMaterialTag tag : tags) if (this.tags.contains(tag)) return true;
        return false;
    }

}
