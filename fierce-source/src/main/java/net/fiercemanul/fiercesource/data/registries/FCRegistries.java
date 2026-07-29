package net.fiercemanul.fiercesource.data.registries;

import net.fiercemanul.fiercesource.FierceSource;
import net.fiercemanul.fiercesource.config.Config;
import net.fiercemanul.fiercesource.data.*;
import net.fiercemanul.fiercesource.util.FSUtils;
import net.fiercemanul.fiercesource.world.level.app.DataType.AppDataType;
import net.fiercemanul.fiercesource.world.level.app.MenuAppType;
import net.fiercemanul.fiercesource.world.level.block.DecorationBlock;
import net.fiercemanul.fiercesource.world.level.block.RotationDecorationWaterloggedBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.registries.*;

import java.util.function.BiFunction;
import java.util.function.Function;

public interface FCRegistries {


    DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(FierceSource.FC_MODID);
    DeferredRegister.Items ITEMS = DeferredRegister.createItems(FierceSource.FC_MODID);
    DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, FierceSource.FC_MODID);
    DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FierceSource.FC_MODID);
    DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, FierceSource.FC_MODID);
    DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(Registries.MENU, FierceSource.FC_MODID);
    DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(Registries.PARTICLE_TYPE, FierceSource.FC_MODID);

    ResourceKey<Registry<AppDataType<?>>> APP_DATA_TYPE_KEYS = ResourceKey.createRegistryKey(FSUtils.rl("app_data_type"));
    Registry<AppDataType<?>> APP_DATA_TYPE_REGISTRY = new RegistryBuilder<>(APP_DATA_TYPE_KEYS).sync(true).create();
    DeferredRegister<AppDataType<?>> APP_DATA_TYPES = DeferredRegister.create(APP_DATA_TYPE_REGISTRY, FierceSource.FC_MODID);

    ResourceKey<Registry<MenuAppType<?>>> MENU_APP_TYPE_KEYS = ResourceKey.createRegistryKey(FSUtils.rl("menu_app_type"));
    Registry<MenuAppType<?>> MENU_APP_TYPE_REGISTRY = new RegistryBuilder<>(MENU_APP_TYPE_KEYS).sync(true).create();
    DeferredRegister<MenuAppType<?>> MENU_APP_TYPES = DeferredRegister.create(MENU_APP_TYPE_REGISTRY, FierceSource.FC_MODID);

    static DeferredBlock<Block> simpleBlock(String name, BlockBehaviour.Properties props) {
        DeferredBlock<Block> block = BLOCKS.registerSimpleBlock(name, props);
        ITEMS.registerSimpleBlockItem(block);
        return block;
    }

    static <I extends BlockItem> ItemBlockGroup<Block, I> simpleBlock(
            String name,
            BlockBehaviour.Properties props,
            BiFunction<Block, Item.Properties, I> itemFunc
    ) {
        return simpleBlock(name, props, itemFunc, new Item.Properties());
    }

    static <I extends BlockItem> ItemBlockGroup<Block, I> simpleBlock(
            String name,
            BlockBehaviour.Properties props,
            BiFunction<Block, Item.Properties, I> itemFunc,
            Item.Properties properties
    ) {
        DeferredBlock<Block> block = BLOCKS.registerSimpleBlock(name, props);
        return new ItemBlockGroup<>(block, ITEMS.register(name, () -> itemFunc.apply(block.get(), properties)));
    }

    static <B extends Block> DeferredBlock<B> regBlock(String name, Function<BlockBehaviour.Properties, B> func, BlockBehaviour.Properties props) {
        DeferredBlock<B> block = BLOCKS.registerBlock(name, func, props);
        ITEMS.registerSimpleBlockItem(block);
        return block;
    }

    static <B extends Block, I extends BlockItem> ItemBlockGroup<B, I> regBlock(
            String name,
            Function<BlockBehaviour.Properties, B> func,
            BlockBehaviour.Properties props,
            BiFunction<Block, Item.Properties, I> itemFunc,
            Item.Properties properties
    ) {
        DeferredBlock<B> block = BLOCKS.registerBlock(name, func, props);
        return new ItemBlockGroup<>(block, ITEMS.register(name, () -> itemFunc.apply(block.get(), properties)));
    }

    static DeferredBlock<DecorationBlock> dust(String name, Function<BlockBehaviour.Properties, BlockBehaviour.Properties> func) {
        return dust(name, func, BlockBehaviour.Properties.of());
    }

    static DeferredBlock<DecorationBlock> dust(String name, Function<BlockBehaviour.Properties, BlockBehaviour.Properties> func, BlockBehaviour.Properties props) {
        DeferredBlock<DecorationBlock> block = BLOCKS.registerBlock(name, p -> new DecorationBlock(p, DecorationBlock.DUST_SHAPE), func.apply(props.instabreak().noCollission().pushReaction(PushReaction.DESTROY)));
        ITEMS.registerSimpleBlockItem(block);
        return block;
    }

    static DeferredBlock<RotationDecorationWaterloggedBlock> shard(String name, Function<BlockBehaviour.Properties, BlockBehaviour.Properties> func) {
        return shard(name, func, BlockBehaviour.Properties.of());
    }

    static DeferredBlock<RotationDecorationWaterloggedBlock> shard(String name, Function<BlockBehaviour.Properties, BlockBehaviour.Properties> func, BlockBehaviour.Properties props) {
        DeferredBlock<RotationDecorationWaterloggedBlock> block = BLOCKS.registerBlock(name, p -> new RotationDecorationWaterloggedBlock(p, DecorationBlock.SHARD_SHAPE), func.apply(props.instabreak().noCollission().pushReaction(PushReaction.DESTROY)));
        ITEMS.registerSimpleBlockItem(block);
        return block;
    }

    static void initRegistries(IEventBus modEventBus, ModContainer modContainer) {
        FSBlocks.init();
        FSItems.init();
        FSEntities.init();
        FSBlockEntityTypes.init();
        FSCreativeModeTabs.init();
        FSMenuTypes.init();
        FSParticleTypes.init();
        AppDataTypes.init();
        MenuAppTypes.init();

        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        ENTITY_TYPES.register(modEventBus);
        BLOCK_ENTITY_TYPES.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        MENU_TYPES.register(modEventBus);
        PARTICLE_TYPES.register(modEventBus);
        APP_DATA_TYPES.register(modEventBus);
        MENU_APP_TYPES.register(modEventBus);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.COMMON_SPEC);
    }

    static void registerRegistries(NewRegistryEvent event) {
        event.register(APP_DATA_TYPE_REGISTRY);
        event.register(MENU_APP_TYPE_REGISTRY);
    }

    static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> regEntityType(
            String name,
            EntityType.EntityFactory<T> factory,
            MobCategory category,
            Function<EntityType.Builder<T>, EntityType.Builder<T>> builder
    ) {
        return regEntityType(ENTITY_TYPES, name, factory, category, builder);
    }

    static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> regEntityType(
            DeferredRegister<EntityType<?>> register,
            String name,
            EntityType.EntityFactory<T> factory,
            MobCategory category,
            Function<EntityType.Builder<T>, EntityType.Builder<T>> builder
    ) {
        return register.register(name, () -> builder.apply(EntityType.Builder.of(factory, category)).build(name));
    }


}
