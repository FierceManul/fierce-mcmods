package net.fiercemanul.fiercelive;

import net.fiercemanul.fiercelive.data.FLBlocks;
import net.fiercemanul.fiercelive.data.FLItems;
import net.fiercemanul.fiercelive.data.registries.BlockBulkRegister;
import net.fiercemanul.fiercelive.data.registries.FLRegister;
import net.fiercemanul.fiercelive.server.commands.SitCommand;
import net.fiercemanul.fiercelive.world.capabilities.InfinityWaterHandler;
import net.fiercemanul.fiercelive.world.level.block.CabinetBlock;
import net.fiercemanul.fiercelive.world.level.block.HalfPodzolBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;
import net.neoforged.neoforge.items.wrapper.InvWrapper;

@Mod(FierceLive.MODID)
public class FierceLive {


    public static final String MODID = "fiercelive";

    public FierceLive(IEventBus modEventBus, ModContainer modContainer) {
        FLRegister.register(modEventBus);

        NeoForge.EVENT_BUS.addListener(this::registerCommandsEvent);
        NeoForge.EVENT_BUS.addListener(this::blockPlacedEvent);
        NeoForge.EVENT_BUS.addListener(this::serverAboutToStartEvent);

        modEventBus.addListener(this::registerCapabilitiesEvent);
    }

    private void registerCommandsEvent(RegisterCommandsEvent event) {
        SitCommand.register(event.getDispatcher());
    }

    private void registerCapabilitiesEvent(RegisterCapabilitiesEvent event) {
        event.registerBlock(
                Capabilities.FluidHandler.BLOCK,
                (level, pos, state, blockEntity, context) -> InfinityWaterHandler.INSTANCE,
                FLBlocks.WATERLOGGED_COBBLESTONE.get()
        );
        BlockBulkRegister.CABINETS.forEach(deferredBlock -> event.registerBlock(
                Capabilities.ItemHandler.BLOCK,
                (level, pos, state, blockEntity, context) -> new InvWrapper(((CabinetBlock) state.getBlock()).getContainer(state, level, pos)),
                deferredBlock.get()
        ));
        event.registerItem(
                Capabilities.FluidHandler.ITEM,
                (stack, context) -> InfinityWaterHandler.INSTANCE,
                FLBlocks.WATERLOGGED_COBBLESTONE
        );
    }

    private void blockPlacedEvent(BlockEvent.EntityPlaceEvent event) {
        BlockState replacedBlock = event.getBlockSnapshot().getState();
        if (replacedBlock.getBlock() instanceof HalfPodzolBlock && event.getPlacedBlock().is(Blocks.SNOW)) {
            event.getLevel().setBlock(
                    event.getPos(),
                    replacedBlock.setValue(
                            HalfPodzolBlock.LAYERS,
                            Integer.min(replacedBlock.getValue(HalfPodzolBlock.LAYERS) + 1, 4)
                    ),
                    Block.UPDATE_ALL_IMMEDIATE
            );
        }
    }

    private void serverAboutToStartEvent(ServerAboutToStartEvent event) {
        FLItems.GLASS_KNIFE.get().applySilkTouch(event.getServer().registryAccess());
    }


}
