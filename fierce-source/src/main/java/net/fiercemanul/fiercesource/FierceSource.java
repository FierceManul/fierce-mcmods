package net.fiercemanul.fiercesource;

import com.mojang.logging.LogUtils;
import net.fiercemanul.fiercesource.capabilities.FSCapabilities;
import net.fiercemanul.fiercesource.capabilities.InfiniteManaContainer;
import net.fiercemanul.fiercesource.client.gui.screens.FierceContainerScreen;
import net.fiercemanul.fiercesource.client.particle.SoulCrystalParticleProvider;
import net.fiercemanul.fiercesource.config.Config;
import net.fiercemanul.fiercesource.data.UIStyleLoader;
import net.fiercemanul.fiercesource.network.protocol.game.FierceMenuData;
import net.fiercemanul.fiercesource.registries.FCRegistries;
import net.fiercemanul.fiercesource.util.Utils;
import net.fiercemanul.fiercesource.world.inventory.FierceContainerMenu;
import net.fiercemanul.fiercesource.world.item.CreativeModeTabItem;
import net.fiercemanul.fiercesource.world.item.CrowbarItem;
import net.fiercemanul.fiercesource.world.level.block.*;
import net.fiercemanul.fiercesource.world.level.block.entity.CreativeManaOutputBlockEntity;
import net.fiercemanul.fiercesource.world.level.block.entity.TestBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.client.event.RegisterClientReloadListenersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.server.ServerStartedEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
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
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = FCRegistries.FIERCE_CRAFT_MENU_TYPE_REGISTER;
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = FCRegistries.FIERCE_CRAFT_PARTICLE_TYPE_REGISTER;


    public static final DeferredBlock<Block> SMALL_SOUL_CRYSTAL_BLOCK = BLOCKS.registerBlock("small_soul_crystal", SoulCrystalBlock.Small::new, SoulCrystalBlock.getProperties(5, MapColor.WOOL));
    public static final DeferredItem<BlockItem> SMALL_SOUL_CRYSTAL_BLOCK_ITEM = ITEMS.registerSimpleBlockItem(SMALL_SOUL_CRYSTAL_BLOCK);
    public static final DeferredBlock<Block> MEDIUM_SOUL_CRYSTAL_BLOCK = BLOCKS.registerBlock("medium_soul_crystal", SoulCrystalBlock.Medium::new, SoulCrystalBlock.getProperties(10, MapColor.WOOL));
    public static final DeferredItem<BlockItem> MEDIUM_SOUL_CRYSTAL_BLOCK_ITEM = ITEMS.registerSimpleBlockItem(MEDIUM_SOUL_CRYSTAL_BLOCK);
    public static final DeferredBlock<Block> LARGE_SOUL_CRYSTAL_BLOCK = BLOCKS.registerBlock("large_soul_crystal", SoulCrystalBlock.Large::new, SoulCrystalBlock.getProperties(15, MapColor.WOOL));
    public static final DeferredItem<BlockItem> LARGE_SOUL_CRYSTAL_BLOCK_ITEM = ITEMS.registerSimpleBlockItem(LARGE_SOUL_CRYSTAL_BLOCK);
    public static final DeferredBlock<Block> SMALL_MANA_CRYSTAL_BLOCK = BLOCKS.registerBlock("small_mana_crystal", ManaCrystalBlock.Small::new, SoulCrystalBlock.getProperties(5, MapColor.COLOR_LIGHT_BLUE));
    public static final DeferredItem<BlockItem> SMALL_MANA_CRYSTAL_BLOCK_ITEM = ITEMS.registerSimpleBlockItem(SMALL_MANA_CRYSTAL_BLOCK);
    public static final DeferredBlock<Block> MEDIUM_MANA_CRYSTAL_BLOCK = BLOCKS.registerBlock("medium_mana_crystal", ManaCrystalBlock.Medium::new, SoulCrystalBlock.getProperties(10, MapColor.COLOR_LIGHT_BLUE));
    public static final DeferredItem<BlockItem> MEDIUM_MANA_CRYSTAL_BLOCK_ITEM = ITEMS.registerSimpleBlockItem(MEDIUM_MANA_CRYSTAL_BLOCK);
    public static final DeferredBlock<Block> LARGE_MANA_CRYSTAL_BLOCK = BLOCKS.registerBlock("large_mana_crystal", ManaCrystalBlock.Large::new, SoulCrystalBlock.getProperties(15, MapColor.COLOR_LIGHT_BLUE));
    public static final DeferredItem<BlockItem> LARGE_MANA_CRYSTAL_BLOCK_ITEM = ITEMS.registerSimpleBlockItem(LARGE_MANA_CRYSTAL_BLOCK);
    public static final DeferredBlock<Block> CREATIVE_MANA_BLOCK = BLOCKS.registerBlock("creative_mana_block", SimpleCapabilityBlock::new, BlockBehaviour.Properties.of().strength(2.0F, 1200.0F).lightLevel(
            value -> 15).emissiveRendering(Utils::getTrue).sound(SoundType.AMETHYST).mapColor(MapColor.COLOR_BLUE));
    public static final DeferredItem<BlockItem> CREATIVE_MANA_BLOCK_ITEM = ITEMS.registerSimpleBlockItem(CREATIVE_MANA_BLOCK);
    public static final DeferredBlock<Block> CREATIVE_MANA_OUTPUT_BLOCK = BLOCKS.registerBlock("creative_mana_output", CreativeManaOutputBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredItem<BlockItem> CREATIVE_MANA_OUTPUT_BLOCK_ITEM = ITEMS.registerSimpleBlockItem(CREATIVE_MANA_OUTPUT_BLOCK);
    public static final DeferredBlock<Block> WORLD_LOCATOR_BLOCK = BLOCKS.registerBlock("world_locator", WorldLocatorBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredItem<BlockItem> WORLD_LOCATOR_BLOCK_ITEM = ITEMS.registerSimpleBlockItem(WORLD_LOCATOR_BLOCK);
    public static final DeferredBlock<Block> TEST_BLOCK = BLOCKS.registerBlock("test_block", TestBlock::new, SoulCrystalBlock.getProperties(15, MapColor.COLOR_BLACK));
    public static final DeferredItem<BlockItem> TEST_BLOCK_ITEM = ITEMS.registerSimpleBlockItem(TEST_BLOCK);


    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CreativeManaOutputBlockEntity>> CREATIVE_MANA_OUTPUT_BLOCK_ENTITY = BLOCK_ENTITIES.register(
            "creative_mana_output_block_entity",
            () -> BlockEntityType.Builder.of(CreativeManaOutputBlockEntity::new, CREATIVE_MANA_OUTPUT_BLOCK.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TestBlockEntity>> TEST_BLOCK_ENTITY = BLOCK_ENTITIES.register(
            "test_block_entity",
            () -> BlockEntityType.Builder.of(TestBlockEntity::new, TEST_BLOCK.get()).build(null));


    public static final DeferredItem<Item> SOUL_CRYSTAL_SHARD_ITEM = ITEMS.registerSimpleItem("soul_crystal_shard");
    public static final DeferredItem<Item> CLAW_HAMMER_ITEM = ITEMS.register("claw_hammer", () -> new CrowbarItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<Item> NETHERITE_CLAW_HAMMER_ITEM = ITEMS.register("netherite_claw_hammer", () -> new CrowbarItem(Tiers.NETHERITE, new Item.Properties().fireResistant()));
    public static final DeferredItem<Item> CROWBAR_ITEM = ITEMS.register("crowbar", () -> new CrowbarItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<Item> NETHERITE_CROWBAR_ITEM = ITEMS.register("netherite_crowbar", () -> new CrowbarItem(Tiers.NETHERITE, new Item.Properties().fireResistant()));
    public static final DeferredItem<Item> TEST_ITEM = ITEMS.registerSimpleItem("test_item");
    public static final DeferredItem<Item> MANA_ICON = ITEMS.registerSimpleItem("mana_icon");
    public static final DeferredItem<Item> FE_ICON = ITEMS.registerSimpleItem("fe_icon");

    public static final DeferredHolder<MenuType<?>, MenuType<FierceContainerMenu>> FIERCE_MENU =  MENU_TYPES.register("fierce_menu", () -> IMenuTypeExtension.create(
            FierceContainerMenu::new));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SOUL_CRYSTAL_PARTICLE = PARTICLE_TYPES.register("soul_crystal", () -> new SimpleParticleType(false));


    public static final TreeSet<CreativeModeTabItem> TAB_ITEMS = new TreeSet<>();
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN_TAB = CREATIVE_MODE_TABS.register("fiercecraft", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.fiercecraft"))
            .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
            .icon(() -> LARGE_MANA_CRYSTAL_BLOCK_ITEM.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                for (CreativeModeTabItem tabItem : TAB_ITEMS) output.accept(tabItem.getItem());
                if (parameters.hasPermissions()) {
                    output.accept(TEST_BLOCK_ITEM);
                    output.accept(TEST_ITEM);
                }
            }).build());


    public FierceSource(IEventBus modEventBus, ModContainer modContainer) {
        TAB_ITEMS.add(new CreativeModeTabItem(LARGE_SOUL_CRYSTAL_BLOCK_ITEM, 0));
        TAB_ITEMS.add(new CreativeModeTabItem(MEDIUM_SOUL_CRYSTAL_BLOCK_ITEM, 1));
        TAB_ITEMS.add(new CreativeModeTabItem(SMALL_SOUL_CRYSTAL_BLOCK_ITEM, 2));
        TAB_ITEMS.add(new CreativeModeTabItem(SOUL_CRYSTAL_SHARD_ITEM, 3));
        TAB_ITEMS.add(new CreativeModeTabItem(LARGE_MANA_CRYSTAL_BLOCK_ITEM, 4));
        TAB_ITEMS.add(new CreativeModeTabItem(MEDIUM_MANA_CRYSTAL_BLOCK_ITEM, 5));
        TAB_ITEMS.add(new CreativeModeTabItem(SMALL_MANA_CRYSTAL_BLOCK_ITEM, 6));
        TAB_ITEMS.add(new CreativeModeTabItem(CREATIVE_MANA_BLOCK_ITEM, 103));
        TAB_ITEMS.add(new CreativeModeTabItem(CREATIVE_MANA_OUTPUT_BLOCK_ITEM, 104));
        TAB_ITEMS.add(new CreativeModeTabItem(WORLD_LOCATOR_BLOCK_ITEM, 205));
        TAB_ITEMS.add(new CreativeModeTabItem(CLAW_HAMMER_ITEM, 305));
        TAB_ITEMS.add(new CreativeModeTabItem(NETHERITE_CLAW_HAMMER_ITEM, 306));
        TAB_ITEMS.add(new CreativeModeTabItem(CROWBAR_ITEM, 307));
        TAB_ITEMS.add(new CreativeModeTabItem(NETHERITE_CROWBAR_ITEM, 308));

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        BLOCK_ENTITIES.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        MENU_TYPES.register(modEventBus);
        PARTICLE_TYPES.register(modEventBus);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (FierceSource) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::registerCapabilitiesEvent);
        modEventBus.addListener(this::registerPayloadHandlersEvent);
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    private void registerCapabilitiesEvent(RegisterCapabilitiesEvent event) {
        event.registerBlock(
                FSCapabilities.BLOCK_MANA_CAP,
                (level, pos, state, blockEntity, context) -> InfiniteManaContainer.INSTANCE,
                CREATIVE_MANA_BLOCK.get()
        );
    }

    private void registerPayloadHandlersEvent(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("1");
        registrar.playBidirectional(FierceMenuData.TYPE, FierceMenuData.STREAM_CODEC, FierceMenuData.HANDLER);
    }



    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    @SubscribeEvent
    public void onServerStarted(ServerStartedEvent event) {
        // Do something when the server started
        LOGGER.info("HELLO from server started");
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onDig(PlayerEvent.BreakSpeed breakSpeedEvent) {
        Player player = breakSpeedEvent.getEntity();
        //
        //TODO:冒险维度预留
        //
        if (player.getMainHandItem().getItem() instanceof CrowbarItem crowbarItem) {
            float destroySpeed = breakSpeedEvent.getState().getDestroySpeed(player.level(), breakSpeedEvent.getPosition().orElse(BlockPos.ZERO));
            float newSpeed = 1.0F;
            boolean is_netherite = crowbarItem.getTier().equals(Tiers.NETHERITE);
            if (is_netherite || destroySpeed <= 10.0F) newSpeed = (is_netherite ? 8.0F : 4.0F) * destroySpeed;

            //TODO: 以下为原版复制,注意版本更新.
            if (player.hasEffect(MobEffects.DIG_SLOWDOWN)) newSpeed *= switch (player.getEffect(MobEffects.DIG_SLOWDOWN).getAmplifier()) {
                case 0 -> 0.3F;
                case 1 -> 0.09F;
                case 2 -> 0.0027F;
                default -> 8.1E-4F;
            };

            if (player.isEyeInFluid(FluidTags.WATER)) newSpeed *= player.getAttribute(Attributes.SUBMERGED_MINING_SPEED).getValue();

            if (!player.onGround()) newSpeed /= 5.0F;

            breakSpeedEvent.setNewSpeed(newSpeed);
        }
    }





    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
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

        @SubscribeEvent
        public static void registerScreens(RegisterMenuScreensEvent event) {
            event.register(FIERCE_MENU.get(), FierceContainerScreen::new);
        }

        @SubscribeEvent
        public static void registerClientReloadListeners(RegisterClientReloadListenersEvent event) {
            event.registerReloadListener(new UIStyleLoader());
        }
    }
}
