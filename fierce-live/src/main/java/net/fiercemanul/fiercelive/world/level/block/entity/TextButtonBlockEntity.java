package net.fiercemanul.fiercelive.world.level.block.entity;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.DynamicOps;
import net.fiercemanul.fiercelive.data.FLBlockEntities;
import net.fiercemanul.fiercesource.FierceSource;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentUtils;
import net.minecraft.network.chat.Style;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.network.FilteredText;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SignText;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;
import java.util.function.UnaryOperator;

//TODO:textBlockEntity  Item.tryApplyToSign
public class TextButtonBlockEntity extends BlockEntity {


    private static final int MAX_TEXT_LINE_WIDTH = 90;
    private static final int TEXT_LINE_HEIGHT = 10;
    protected SignText text = new SignText();
    @Nullable
    protected UUID playerWhoMayEdit;
    protected boolean isWaxed;

    public TextButtonBlockEntity(BlockPos pos, BlockState blockState) {
        super(FLBlockEntities.TEXT_BUTTON.get(), pos, blockState);
    }

    public void updateSignText(Player player, List<FilteredText> filteredText) {
        if (isWaxed() || !player.getUUID().equals(getPlayerWhoMayEdit()) || level == null) {
            FierceSource.LOGGER.warn("Player {} just tried to change non-editable button", player.getName().getString());
            return;
        }
        updateText(signText -> setMessages(player, filteredText, signText));
        setAllowedPlayerEditor(null);
        level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
    }

    public void updateText(UnaryOperator<SignText> updater) {
        SignText signtext = this.getText();
        setText(updater.apply(signtext));
    }

    protected SignText setMessages(Player player, List<FilteredText> filteredText, SignText text) {
        for (int i = 0; i < filteredText.size(); i++) {
            FilteredText filteredtext = filteredText.get(i);
            Style style = text.getMessage(i, player.isTextFilteringEnabled()).getStyle();
            if (player.isTextFilteringEnabled()) {
                text = text.setMessage(i, Component.literal(filteredtext.filteredOrEmpty()).setStyle(style));
            } else {
                text = text.setMessage(
                        i, Component.literal(filteredtext.raw()).setStyle(style), Component.literal(filteredtext.filteredOrEmpty()).setStyle(style)
                );
            }
        }
        return text;
    }

    public boolean playerIsTooFarAwayToEdit(UUID uuid) {
        Player player = null;
        if (level != null) player = level.getPlayerByUUID(uuid);
        return player == null || !player.canInteractWithBlock(getBlockPos(), 4.0);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, TextButtonBlockEntity button) {
        UUID uuid = button.getPlayerWhoMayEdit();
        if (uuid != null && button.playerIsTooFarAwayToEdit(uuid)) button.setAllowedPlayerEditor(null);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        DynamicOps<Tag> dynamicops = registries.createSerializationContext(NbtOps.INSTANCE);
        if (tag.contains("text")) SignText.DIRECT_CODEC
                .parse(dynamicops, tag.getCompound("front_text"))
                .resultOrPartial(FierceSource.LOGGER::error)
                .ifPresent(text -> this.text = loadLines(text));
        isWaxed = tag.getBoolean("is_waxed");
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        DynamicOps<Tag> dynamicops = registries.createSerializationContext(NbtOps.INSTANCE);
        SignText.DIRECT_CODEC
                .encodeStart(dynamicops, text)
                .resultOrPartial(FierceSource.LOGGER::error)
                .ifPresent(tag1 -> tag.put("text", tag1));
        tag.putBoolean("is_waxed", isWaxed);
    }

    protected SignText loadLines(SignText text) {
        for (int i = 0; i < 3; i++) {
            Component component = loadLine(text.getMessage(i, false));
            Component component1 = loadLine(text.getMessage(i, true));
            text = text.setMessage(i, component, component1);
        }
        return text;
    }

    protected Component loadLine(Component lineText) {
        if (this.level instanceof ServerLevel serverlevel) {
            try {
                return ComponentUtils.updateForEntity(
                        createCommandSourceStack(null, serverlevel, this.worldPosition),
                        lineText, null, 0
                );
            }
            catch (CommandSyntaxException ignored) {}
        }
        return lineText;
    }

    protected static CommandSourceStack createCommandSourceStack(@Nullable Player player, Level level, BlockPos pos) {
        String s = player == null ? "Button" : player.getName().getString();
        Component component = player == null ? Component.literal("Button") : player.getDisplayName();
        return new CommandSourceStack(
                CommandSource.NULL,
                Vec3.atCenterOf(pos),
                Vec2.ZERO,
                (ServerLevel)level,
                2,
                s,
                component,
                level.getServer(),
                player
        );
    }

    public SignText getText() {
        return text;
    }

    public void setText(SignText text) {
        this.text = text;
        markUpdated();
    }

    public void setAllowedPlayerEditor(@Nullable UUID playWhoMayEdit) {
        this.playerWhoMayEdit = playWhoMayEdit;
    }

    @Nullable
    public UUID getPlayerWhoMayEdit() {
        return playerWhoMayEdit;
    }

    protected void markUpdated() {
        setChanged();
        if (level != null) level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
    }

    public boolean isWaxed() {
        return isWaxed;
    }

    public boolean setWaxed(boolean isWaxed) {
        if (this.isWaxed != isWaxed) {
            this.isWaxed = isWaxed;
            markUpdated();
            return true;
        }
        else return false;
    }

    public int getTextLineHeight() {
        return TEXT_LINE_HEIGHT;
    }

    public int getMaxTextLineWidth() {
        return MAX_TEXT_LINE_WIDTH;
    }

}
