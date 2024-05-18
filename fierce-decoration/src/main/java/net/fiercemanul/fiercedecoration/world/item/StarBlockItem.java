package net.fiercemanul.fiercedecoration.world.item;

import net.fiercemanul.fiercedecoration.client.renderer.StarBlockItemRender;
import net.fiercemanul.fiercedecoration.world.level.block.FDBlocks;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.BlockItem;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

public class StarBlockItem extends BlockItem {

    public StarBlockItem(Properties pProperties) {
        super(FDBlocks.STAR_BLOCK.get(), pProperties);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return StarBlockItemRender.getInstance();
            }
        });
    }
}
