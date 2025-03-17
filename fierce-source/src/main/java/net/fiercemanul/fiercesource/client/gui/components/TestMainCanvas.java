package net.fiercemanul.fiercesource.client.gui.components;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.math.Axis;
import net.fiercemanul.fiercesource.client.gui.screens.FierceContainerScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.commands.EnchantCommand;
import net.minecraft.util.FastColor;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.enchantment.Enchantments;
import net.neoforged.neoforge.client.model.data.ModelData;

public class TestMainCanvas extends Canvas{


    public TestMainCanvas(FierceContainerScreen screen, int x, int y, Component component) {
        super(screen, x, y, 154, 241, component);
    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        guiGraphics.enableScissor(getX(), getY(), getRight(), getBottom());

        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(getX(), getY(), 0);
        guiGraphics.fill(
                0, 0, width, height,
                FastColor.ARGB32.color(230, 230, 255)
        );
        guiGraphics.drawString(screen.getFont(), "Random: " + screen.getMenu().random, 8, 6, 4210752, false);

        ItemStack itemStack = Items.NETHERITE_SWORD.getDefaultInstance();
        itemStack.enchant(Minecraft.getInstance().level.registryAccess().registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.SHARPNESS), 5);
        guiGraphics.renderFakeItem(itemStack, 25, 32);
        BakedModel model = Minecraft.getInstance().getItemRenderer().getModel(
                itemStack,
                Minecraft.getInstance().level,
                Minecraft.getInstance().player,
                0
        );
        BakedModel model1 = model.getOverrides().resolve(
                model,
                itemStack,
                Minecraft.getInstance().level,
                Minecraft.getInstance().player,
                0
        );
        TextureAtlasSprite sprite = model1 == null ? model.getParticleIcon(ModelData.EMPTY) : model1.getParticleIcon(ModelData.EMPTY);
        guiGraphics.blit(42, 32, 0, 16, 16, sprite);

        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(77, 77, 150);
        guiGraphics.pose().scale(64.0F, -64.0F, 64.0F);
        guiGraphics.pose().mulPose(Axis.XP.rotationDegrees(30));
        guiGraphics.pose().mulPose(Axis.YP.rotationDegrees(30));
        Lighting.setupForFlatItems();
        Minecraft.getInstance().getItemRenderer().renderStatic(
                Minecraft.getInstance().player,
                itemStack,
                ItemDisplayContext.NONE,
                false,
                guiGraphics.pose(),
                guiGraphics.bufferSource(),
                Minecraft.getInstance().level,
                15728880,
                OverlayTexture.NO_OVERLAY,
                0
        );
        //忽略深度并且立即渲染已提交的内容
        guiGraphics.flush();
        Lighting.setupFor3DItems();
        guiGraphics.pose().popPose();

        guiGraphics.pose().popPose();

        guiGraphics.disableScissor();
    }
}
