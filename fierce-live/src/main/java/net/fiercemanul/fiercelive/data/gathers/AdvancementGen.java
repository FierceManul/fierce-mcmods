package net.fiercemanul.fiercelive.data.gathers;

import net.fiercemanul.fiercelive.FierceLive;
import net.fiercemanul.fiercelive.data.FLBlocks;
import net.fiercemanul.fiercelive.data.FLItems;
import net.fiercemanul.fiercelive.data.registries.BlockBulkRegister;
import net.fiercemanul.fiercelive.data.registries.BlockMaterials;
import net.fiercemanul.fiercelive.data.tags.FLItemTags;
import net.fiercemanul.fiercesource.util.FSUtils;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.Optional;
import java.util.function.Consumer;

public class AdvancementGen implements AdvancementProvider.AdvancementGenerator  {

    @Override
    public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> saver, ExistingFileHelper existingFileHelper) {
        var root = Advancement.Builder.advancement().display(
                FLBlocks.SOUL_CRYSTAL_ORNAMENT,
                Component.translatable("advancements.fiercelive.root.title"),
                Component.translatable("advancements.fiercelive.root.description"),
                FSUtils.rl(FierceLive.MODID, "textures/block/spiral_stone.png"),
                AdvancementType.TASK, false, false, false
        ).addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.CRAFTING_TABLE)
        ).save(saver, "fiercelive:root");

        Advancement.Builder.advancement().parent(root).display(
                FLBlocks.HALF_DIRT,
                Component.translatable("advancements.fiercelive.half_block.title"),
                Component.translatable("advancements.fiercelive.half_block.description"),
                null, AdvancementType.TASK, true, true, false
        ).addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(FLBlocks.HALF_DIRT)
        ).save(saver, "fiercelive:half_block");

        Advancement.Builder.advancement().parent(root).display(
                BlockBulkRegister.ONE_CUT_BLOCKS.get(BlockMaterials.SMOOTH_STONE),
                Component.translatable("advancements.fiercelive.cut_blocks.title"),
                Component.translatable("advancements.fiercelive.cut_blocks.description"),
                null, AdvancementType.TASK, true, true, false
        ).addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(FLItemTags.CUT_BLOCKS))
        ).save(saver, "fiercelive:cut_blocks");

        Advancement.Builder.advancement().parent(root).display(
                FLBlocks.GREEN_FUN_ROOF,
                Component.translatable("advancements.fiercelive.green_fun_roof.title"),
                Component.translatable("advancements.fiercelive.green_fun_roof.description"),
                null, AdvancementType.TASK, true, true, false
        ).addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(FLBlocks.GREEN_FUN_ROOF)
        ).save(saver, "fiercelive:green_fun_roof");

        Advancement.Builder.advancement().parent(root).display(
                FLItems.CROWBAR,
                Component.translatable("advancements.fiercelive.crowbar.title"),
                Component.translatable("advancements.fiercelive.crowbar.description"),
                null, AdvancementType.TASK, true, true, false
        ).addCriterion("used", CriteriaTriggers.ITEM_USED_ON_BLOCK.createCriterion(new ItemUsedOnLocationTrigger.TriggerInstance(
                Optional.empty(), Optional.of(ContextAwarePredicate.create(
                        MatchTool.toolMatches(ItemPredicate.Builder.item().of(FLItems.CROWBAR)).build()))
        ))).save(saver, "fiercelive:crowbar");

        Advancement.Builder.advancement().parent(root).display(
                FLItems.METEOR_HAMMER,
                Component.translatable("advancements.fiercelive.meteor_hammer.title"),
                Component.translatable("advancements.fiercelive.meteor_hammer.description"),
                null, AdvancementType.TASK, true, true, false
        ).addCriterion("used", CriteriaTriggers.USING_ITEM.createCriterion(new UsingItemTrigger.TriggerInstance(
                Optional.empty(), Optional.of(ItemPredicate.Builder.item().of(FLItems.METEOR_HAMMER).build()))
        )).save(saver, "fiercelive:meteor_hammer");

        Advancement.Builder.advancement().parent(root).display(
                Items.IRON_PICKAXE,
                Component.translatable("advancements.fiercelive.pickaxe.title"),
                Component.translatable("advancements.fiercelive.pickaxe.description"),
                null, AdvancementType.TASK, true, true, false
        ).addCriterion("kill", KilledTrigger.TriggerInstance.playerKilledEntity(
                EntityPredicate.Builder.entity(),
                DamageSourcePredicate.Builder.damageType().source(EntityPredicate.Builder.entity().equipment(
                        EntityEquipmentPredicate.Builder.equipment().mainhand(ItemPredicate.Builder.item().of(ItemTags.PICKAXES))))
        )).save(saver, "fiercelive:pickaxe");

        Advancement.Builder.advancement().parent(root).display(
                FLBlocks.IRON_CORRIDOR,
                Component.translatable("advancements.fiercelive.iron_corridor.title"),
                Component.translatable("advancements.fiercelive.iron_corridor.description"),
                null, AdvancementType.TASK, true, true, false
        ).addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(FLItemTags.IRON_CORRIDORS))
        ).save(saver, "fiercelive:iron_corridor");

        Advancement.Builder.advancement().parent(root).display(
                FLItems.FOX_CARROT,
                Component.translatable("advancements.fiercelive.fox_carrot.title"),
                Component.translatable("advancements.fiercelive.fox_carrot.description"),
                null, AdvancementType.TASK, true, true, false
        ).addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(FLItems.FOX_CARROT)
        ).save(saver, "fiercelive:fox_carrot");

        Advancement.Builder.advancement().parent(root).display(
                FLItems.RAINBOW_DYE,
                Component.translatable("advancements.fiercelive.rainbow_dye.title"),
                Component.translatable("advancements.fiercelive.rainbow_dye.description"),
                null, AdvancementType.TASK, true, true, false
        ).addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(FLItems.RAINBOW_DYE)
        ).save(saver, "fiercelive:rainbow_dye");

        Advancement.Builder.advancement().parent(root).display(
                FLBlocks.FIREPLACE_HEART,
                Component.translatable("advancements.fiercelive.fireplace_heart.title"),
                Component.translatable("advancements.fiercelive.fireplace_heart.description"),
                null, AdvancementType.TASK, true, true, false
        ).addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(FLBlocks.FIREPLACE_HEART)
        ).save(saver, "fiercelive:fireplace_heart");
        
        Advancement.Builder.advancement().parent(root).display(
                FLBlocks.ITEM_FRAME_SHELL_THIN,
                Component.translatable("advancements.fiercelive.item_frame.title"),
                Component.translatable("advancements.fiercelive.item_frame.description"),
                null, AdvancementType.TASK, true, true, false
        ).addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(
                ItemPredicate.Builder.item().of(FLBlocks.ITEM_FRAME_SHELL_THIN, FLBlocks.ITEM_FRAME_SHELL_BIG)
        )).save(saver, "fiercelive:item_frame");

        Advancement.Builder.advancement().parent(root).display(
                FLBlocks.IRON_SCAFFOLDING,
                Component.translatable("advancements.fiercelive.iron_scaffolding.title"),
                Component.translatable("advancements.fiercelive.iron_scaffolding.description"),
                null, AdvancementType.TASK, true, true, false
        ).addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(FLBlocks.IRON_SCAFFOLDING)
        ).save(saver, "fiercelive:iron_scaffolding");

        Advancement.Builder.advancement().parent(root).display(
                FLItems.SACABAMBASPIS,
                Component.translatable("advancements.fiercelive.sacabambaspis.title"),
                Component.translatable("advancements.fiercelive.sacabambaspis.description"),
                null, AdvancementType.TASK, true, true, false
        ).addCriterion("item", InventoryChangeTrigger.TriggerInstance.hasItems(FLItems.SACABAMBASPIS)
        ).save(saver, "fiercelive:sacabambaspis");

    }
}
