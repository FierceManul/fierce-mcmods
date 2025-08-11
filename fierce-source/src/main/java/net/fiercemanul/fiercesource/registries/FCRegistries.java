package net.fiercemanul.fiercesource.registries;

import net.fiercemanul.fiercesource.FierceSource;
import net.fiercemanul.fiercesource.config.Config;
import net.fiercemanul.fiercesource.world.level.block.ItemBlock;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

public class FCRegistries {


    public static final DeferredRegister.Blocks BLOCK = DeferredRegister.createBlocks(FierceSource.FC_MODID);
    public static final DeferredRegister.Items ITEM = DeferredRegister.createItems(FierceSource.FC_MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FierceSource.FC_MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPE = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, FierceSource.FC_MODID);
    public static final DeferredRegister<MenuType<?>> MENU_TYPE = DeferredRegister.create(Registries.MENU, FierceSource.FC_MODID);
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPE = DeferredRegister.create(Registries.PARTICLE_TYPE, FierceSource.FC_MODID);

    public static ItemBlockGroup<Block, BlockItem> simple (String name, BlockBehaviour.Properties props) {
        DeferredBlock<Block> block = BLOCK.registerSimpleBlock(name, props);
        return new ItemBlockGroup<>(block, ITEM.registerSimpleBlockItem(block));
    }

    public static <B extends Block> ItemBlockGroup<B, BlockItem> simpleBlockItem (
            String name, Function<BlockBehaviour.Properties, B> func, BlockBehaviour.Properties props) {
        DeferredBlock<B> block = BLOCK.registerBlock(name, func, props);
        return new ItemBlockGroup<>(block, ITEM.registerSimpleBlockItem(block));
    }

    public static ItemBlockGroup<ItemBlock, BlockItem> dust (
            String name, Function<BlockBehaviour.Properties, BlockBehaviour.Properties> func) {
        DeferredBlock<ItemBlock> block = BLOCK.registerBlock(name, p -> new ItemBlock(p, ItemBlock.DUST_SHAPE), func.apply(BlockBehaviour.Properties.of().instabreak().noCollission().pushReaction(PushReaction.DESTROY)));
        return new ItemBlockGroup<>(block, ITEM.registerSimpleBlockItem(block));
    }

    public static void initRegistries(IEventBus modEventBus, ModContainer modContainer) {
        FSBlocksAndItems.init();
        FSBlockEntityTypes.init();
        FSCreativeModeTabs.init();
        FSMenuTypes.init();
        FSParticleTypes.init();

        FCRegistries.BLOCK.register(modEventBus);
        FCRegistries.ITEM.register(modEventBus);
        FCRegistries.BLOCK_ENTITY_TYPE.register(modEventBus);
        FCRegistries.CREATIVE_MODE_TAB.register(modEventBus);
        FCRegistries.MENU_TYPE.register(modEventBus);
        FCRegistries.PARTICLE_TYPE.register(modEventBus);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
}
