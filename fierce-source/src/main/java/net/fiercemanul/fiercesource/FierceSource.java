package net.fiercemanul.fiercesource;

import com.mojang.logging.LogUtils;
import net.fiercemanul.fiercesource.capabilities.InfiniteManaContainer;
import net.fiercemanul.fiercesource.capabilities.ManaCapabilities;
import net.fiercemanul.fiercesource.client.particle.SoulCrystalParticleProvider;
import net.fiercemanul.fiercesource.config.Config;
import net.fiercemanul.fiercesource.registries.FCRegistries;
import net.fiercemanul.fiercesource.world.item.CreativeModeTabItem;
import net.fiercemanul.fiercesource.world.item.CrowbarItem;
import net.fiercemanul.fiercesource.world.level.block.*;
import net.fiercemanul.fiercesource.world.level.block.entity.CreativeManaOutputBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

import java.util.TreeSet;

@Mod(FierceSource.MODID)
public class FierceSource
{


    public static final String MODID = "fiercesource";
    public static final String FC_MODID = "fiercecraft";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister.Blocks BLOCKS = FCRegistries.FIERCE_CRAFT_BLOCK_REGISTER;
    public static final DeferredRegister.Items ITEMS = FCRegistries.FIERCE_CRAFT_ITEM_REGISTER;
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = FCRegistries.FIERCE_CRAFT_CREATIVE_MODE_TAB_REGISTER;
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = FCRegistries.FIERCE_CRAFT_BLOCK_ENTITY_TYPE_REGISTER;
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPE = FCRegistries.FIERCE_CRAFT_PARTICLE_TYPE_REGISTER;


    public static final DeferredBlock<Block> SMALL_SOUL_CRYSTAL_BLOCK = BLOCKS.registerBlock("soul_crystal_small", SoulCrystalSmallBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredItem<BlockItem> SMALL_SOUL_CRYSTAL_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("soul_crystal_small", SMALL_SOUL_CRYSTAL_BLOCK);
    public static final DeferredBlock<Block> MEDIUM_SOUL_CRYSTAL_BLOCK = BLOCKS.registerBlock("soul_crystal_medium", SoulCrystalMediumBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredItem<BlockItem> MEDIUM_SOUL_CRYSTAL_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("soul_crystal_medium", MEDIUM_SOUL_CRYSTAL_BLOCK);
    public static final DeferredBlock<Block> LARGE_SOUL_CRYSTAL_BLOCK = BLOCKS.registerBlock("soul_crystal_large", SoulCrystalLargeBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredItem<BlockItem> LARGE_SOUL_CRYSTAL_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("soul_crystal_large", LARGE_SOUL_CRYSTAL_BLOCK);
    public static final DeferredBlock<Block> CREATIVE_MANA_BLOCK = BLOCKS.registerBlock("creative_mana_block", SimpleCapabilityBlock::new, BlockBehaviour.Properties.of().strength(2.0F, 12.0F).lightLevel(value -> 15).sound(SoundType.AMETHYST).mapColor(MapColor.COLOR_BLUE));
    public static final DeferredItem<BlockItem> CREATIVE_MANA_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("creative_mana_block", CREATIVE_MANA_BLOCK);
    public static final DeferredBlock<Block> CREATIVE_MANA_OUTPUT_BLOCK = BLOCKS.registerBlock("creative_mana_output", CreativeManaOutputBlock::new, BlockBehaviour.Properties.of().sound(SoundType.METAL));
    public static final DeferredItem<BlockItem> CREATIVE_MANA_OUTPUT_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("creative_mana_output", CREATIVE_MANA_OUTPUT_BLOCK);
    public static final DeferredBlock<Block> WORLD_LOCATOR_BLOCK = BLOCKS.registerBlock("world_locator", WorldLocatorBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredItem<BlockItem> WORLD_LOCATOR_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("world_locator", WORLD_LOCATOR_BLOCK);


    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CreativeManaOutputBlockEntity>> CREATIVE_MANA_OUTPUT_BLOCK_ENTITY = BLOCK_ENTITIES.register(
            "creative_mana_output_block_entity",
            () -> BlockEntityType.Builder.of(CreativeManaOutputBlockEntity::new, CREATIVE_MANA_OUTPUT_BLOCK.get()).build(null));


    public static final DeferredItem<Item> SOUL_CRYSTAL_SHARD_ITEM = ITEMS.registerSimpleItem("soul_crystal_shard");
    public static final DeferredItem<Item> POS_RECORDER_ITEM = ITEMS.registerSimpleItem("pos_recorder");
    public static final DeferredItem<Item> CLAW_HAMMER_ITEM = ITEMS.register("claw_hammer", () -> new CrowbarItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<Item> NETHERITE_CLAW_HAMMER_ITEM = ITEMS.register("netherite_claw_hammer", () -> new CrowbarItem(Tiers.NETHERITE, new Item.Properties().fireResistant()));
    public static final DeferredItem<Item> CROWBAR_ITEM = ITEMS.register("crowbar", () -> new CrowbarItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<Item> NETHERITE_CROWBAR_ITEM = ITEMS.register("netherite_crowbar", () -> new CrowbarItem(Tiers.NETHERITE, new Item.Properties().fireResistant()));
    public static final DeferredItem<Item> MANA_ICON = ITEMS.registerSimpleItem("mana_icon");
    public static final DeferredItem<Item> FE_ICON = ITEMS.registerSimpleItem("fe_icon");

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SOUL_CRYSTAL_PARTICLE = PARTICLE_TYPE.register("soul_crystal", () -> new SimpleParticleType(false));


    public static final TreeSet<CreativeModeTabItem> TAB_ITEMS = new TreeSet<>();
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN_TAB = CREATIVE_MODE_TABS.register("fiercecraft", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.fiercecraft"))
            .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
            .icon(() -> MANA_ICON.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                for (CreativeModeTabItem tabItem : TAB_ITEMS) output.accept(tabItem.getItem());
            }).build());


    public FierceSource(IEventBus modEventBus, ModContainer modContainer) {
        TAB_ITEMS.add(new CreativeModeTabItem(LARGE_SOUL_CRYSTAL_BLOCK_ITEM, 0));
        TAB_ITEMS.add(new CreativeModeTabItem(MEDIUM_SOUL_CRYSTAL_BLOCK_ITEM, 1));
        TAB_ITEMS.add(new CreativeModeTabItem(SMALL_SOUL_CRYSTAL_BLOCK_ITEM, 2));
        TAB_ITEMS.add(new CreativeModeTabItem(SOUL_CRYSTAL_SHARD_ITEM, 3));
        TAB_ITEMS.add(new CreativeModeTabItem(CREATIVE_MANA_BLOCK_ITEM, 103));
        TAB_ITEMS.add(new CreativeModeTabItem(CREATIVE_MANA_OUTPUT_BLOCK_ITEM, 104));
        TAB_ITEMS.add(new CreativeModeTabItem(POS_RECORDER_ITEM, 204));
        TAB_ITEMS.add(new CreativeModeTabItem(WORLD_LOCATOR_BLOCK_ITEM, 205));
        TAB_ITEMS.add(new CreativeModeTabItem(CLAW_HAMMER_ITEM, 305));
        TAB_ITEMS.add(new CreativeModeTabItem(NETHERITE_CLAW_HAMMER_ITEM, 306));
        TAB_ITEMS.add(new CreativeModeTabItem(CROWBAR_ITEM, 307));
        TAB_ITEMS.add(new CreativeModeTabItem(NETHERITE_CROWBAR_ITEM, 308));


        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        modEventBus.addListener(this::registerCapabilitiesEvent);

        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        BLOCK_ENTITIES.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        PARTICLE_TYPE.register(modEventBus);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (FierceSource) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        //modEventBus.addListener(this::addCreative);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    public void registerCapabilitiesEvent(RegisterCapabilitiesEvent event) {
        event.registerBlock(
                ManaCapabilities.BLOCK,
                (level, pos, state, blockEntity, context) -> InfiniteManaContainer.INSTANCE,
                CREATIVE_MANA_BLOCK.get()
        );
    }

    // Add the example block item to the building blocks tab
    /*private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
            event.accept(EXAMPLE_BLOCK_ITEM);
    }*/

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onDig(PlayerEvent.BreakSpeed breakSpeedEvent) {
        //
        //TODO:冒险维度预留
        //
        if (breakSpeedEvent.getEntity().getMainHandItem().getItem() instanceof CrowbarItem crowbarItem) {
            float destroySpeed = breakSpeedEvent.getState().getDestroySpeed(breakSpeedEvent.getEntity().level(), breakSpeedEvent.getPosition().orElse(BlockPos.ZERO));
            float newSpeed = 1.0F;
            boolean is_netherite = crowbarItem.getTier().equals(Tiers.NETHERITE);
            if (is_netherite || destroySpeed <= 10.0F) newSpeed = (is_netherite ? 8.0F : 4.0F) * destroySpeed;

            //TODO: 以下为原版复制,注意版本更新.
            if (breakSpeedEvent.getEntity().hasEffect(MobEffects.DIG_SLOWDOWN)) {
                newSpeed *= switch(breakSpeedEvent.getEntity().getEffect(MobEffects.DIG_SLOWDOWN).getAmplifier()) {
                    case 0 -> 0.3F;
                    case 1 -> 0.09F;
                    case 2 -> 0.0027F;
                    default -> 8.1E-4F;
                };
            }

            if (breakSpeedEvent.getEntity().isEyeInFluid(FluidTags.WATER) && !EnchantmentHelper.hasAquaAffinity(breakSpeedEvent.getEntity())) {
                newSpeed /= 5.0F;
            }

            if (!breakSpeedEvent.getEntity().onGround()) {
                newSpeed /= 5.0F;
            }

            breakSpeedEvent.setNewSpeed(newSpeed);
        }
    }


    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }

        @SubscribeEvent
        public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
            event.registerSpriteSet(SOUL_CRYSTAL_PARTICLE.get(), SoulCrystalParticleProvider::new);
        }
    }
}
