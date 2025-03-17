package net.fiercemanul.fiercedecoration.registries;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.data.loading.DatagenModLoader;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

public final class BlockBulkRegisterKey {

    public final Supplier<Block> sourceBlock;
    public final ItemLike blockItemLike;
    public final Supplier<BlockBehaviour.Properties> defaultProperties;
    public final ResourceLocation sourceId;
    private final BiFunction<BlockBehaviour.Properties, IMapColorConsumer, BlockBehaviour.Properties> mapColorHolder;
    private final Set<IBlockBulkRegisterProperty> registerProperties;
    private final Map<Class<?>, Object> extendProperties;

    public BlockBulkRegisterKey (
            Block block,
            BiFunction<BlockBehaviour.Properties, IMapColorConsumer, BlockBehaviour.Properties> mapColorHolder,
            RegisterPropertiesBuilder registerPropertiesBuilder,
            ExtendPropertiesBuilder extendPropertiesBuilder
    ) {
        this.sourceBlock = () -> block;
        this.blockItemLike = block;
        this.sourceId = BuiltInRegistries.BLOCK.getKey(block);
        this.defaultProperties = () -> BlockBehaviour.Properties.ofFullCopy(block);
        this.mapColorHolder = mapColorHolder;
        this.registerProperties = registerPropertiesBuilder.build();
        this.extendProperties = extendPropertiesBuilder.build();
    }

    public BlockBulkRegisterKey (
            Block block,
            Supplier<BlockBehaviour.Properties> defaultProperties,
            BiFunction<BlockBehaviour.Properties, IMapColorConsumer, BlockBehaviour.Properties> mapColorHolder,
            RegisterPropertiesBuilder registerPropertiesBuilder,
            ExtendPropertiesBuilder extendPropertiesBuilder
    ) {
        this.sourceBlock = () -> block;
        this.blockItemLike = block;
        this.sourceId = BuiltInRegistries.BLOCK.getKey(block);
        this.defaultProperties = defaultProperties;
        this.mapColorHolder = mapColorHolder;
        this.registerProperties = registerPropertiesBuilder.build();
        this.extendProperties = extendPropertiesBuilder.build();
    }

    public BlockBulkRegisterKey (
            DeferredBlock<Block> deferredBlock,
            Block block,
            BiFunction<BlockBehaviour.Properties, IMapColorConsumer, BlockBehaviour.Properties> mapColorHolder,
            RegisterPropertiesBuilder registerPropertiesBuilder,
            ExtendPropertiesBuilder extendPropertiesBuilder
    ) {
        this.sourceBlock = deferredBlock;
        this.blockItemLike = deferredBlock;
        this.sourceId = deferredBlock.getId();
        this.defaultProperties = () -> BlockBehaviour.Properties.ofFullCopy(block);
        this.mapColorHolder = mapColorHolder;
        this.registerProperties = registerPropertiesBuilder.build();
        this.extendProperties = extendPropertiesBuilder.build();
    }

    public BlockBulkRegisterKey (
            DeferredBlock<Block> deferredBlock,
            Supplier<BlockBehaviour.Properties> defaultProperties,
            BiFunction<BlockBehaviour.Properties, IMapColorConsumer, BlockBehaviour.Properties> mapColorHolder,
            RegisterPropertiesBuilder registerPropertiesBuilder,
            ExtendPropertiesBuilder extendPropertiesBuilder
    ) {
        this.sourceBlock = deferredBlock;
        this.blockItemLike = deferredBlock;
        this.sourceId = deferredBlock.getId();
        this.defaultProperties = defaultProperties;
        this.mapColorHolder = mapColorHolder;
        this.registerProperties = registerPropertiesBuilder.build();
        this.extendProperties = extendPropertiesBuilder.build();
    }

    public BlockBulkRegisterKey(
            Supplier<Block> sourceBlock,
            ItemLike blockItemLike,
            Supplier<BlockBehaviour.Properties> defaultProperties,
            ResourceLocation sourceId,
            BiFunction<BlockBehaviour.Properties, IMapColorConsumer, BlockBehaviour.Properties> mapColorHolder,
            RegisterPropertiesBuilder registerPropertiesBuilder,
            ExtendPropertiesBuilder extendPropertiesBuilder
    ) {
        this.sourceBlock = sourceBlock;
        this.blockItemLike = blockItemLike;
        this.defaultProperties = defaultProperties;
        this.sourceId = sourceId;
        this.mapColorHolder = mapColorHolder;
        this.registerProperties = registerPropertiesBuilder.build();
        this.extendProperties = extendPropertiesBuilder.build();
    }

    public Block getMaterialBlock() {
        return sourceBlock.get();
    }

    public String getNamespace() {
        return sourceId.getNamespace();
    }

    public String getPath() {
        return sourceId.getPath();
    }

    public BiFunction<BlockBehaviour.Properties, IMapColorConsumer, BlockBehaviour.Properties> getMapColorHolder() {
        return mapColorHolder;
    }

    public Set<IBlockBulkRegisterProperty> getRegisterProperties() {
        return registerProperties;
    }

    @EnsuresNonNullIf(expression = "getProperty(#1)", result = true)
    public <T> boolean hasProperty(Class<T> tClass) {
        return extendProperties.containsKey(tClass);
    }

    @SuppressWarnings("unchecked")
    public <T> T getProperty(Class<T> tClass) {
        return (T) extendProperties.get(tClass);
    }

    @SuppressWarnings("unchecked")
    public <T> T getPropertyOr(Class<T> tClass, Object o) {
        return (T) extendProperties.getOrDefault(tClass, 0);
    }

    public static final class RegisterPropertiesBuilder {

        private final Set<IBlockBulkRegisterProperty> cache = new HashSet<>();

        public RegisterPropertiesBuilder(IBlockBulkRegisterProperty... properties) {
            for (IBlockBulkRegisterProperty property : properties) if (property != null) cache.add(property);
        }

        private Set<IBlockBulkRegisterProperty> build() {
            return ImmutableSet.copyOf(cache);
        }
    }

    public static final class ExtendPropertiesBuilder {

        private final Map<Class<?>, Object> cache;

        public ExtendPropertiesBuilder(Object... objects) {
            this.cache = new HashMap<>();
            for (Object object : objects) if (object != null) cache.put(object.getClass(), object);
        }

        private ExtendPropertiesBuilder(Map<Class<?>, Object> cache) {
            this.cache = cache;
        }

        public ExtendPropertiesBuilder applyProperty(Object... objects) {
            for (Object object : objects) if (object != null) cache.put(object.getClass(), object);
            return this;
        }

        public ExtendPropertiesBuilder ifDataGen(Consumer<ExtendPropertiesBuilder> consumer) {
            if (DatagenModLoader.isRunningDataGen()) consumer.accept(this);
            return this;
        }

        public ExtendPropertiesBuilder getCopy() {
            return new ExtendPropertiesBuilder(cache);
        }

        private Map<Class<?>, Object> build() {
            return ImmutableMap.copyOf(cache);
        }
    }
}
