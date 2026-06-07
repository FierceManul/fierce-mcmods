package net.fiercemanul.fiercesource.data;

import net.fiercemanul.fiercesource.world.entity.ThrownBlock;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;

import static net.fiercemanul.fiercesource.data.registries.FCRegistries.regEntityType;

public class FSEntities {


    public static final DeferredHolder<EntityType<?>, EntityType<ThrownBlock>> THROWN_BLOCK = regEntityType(
            "thrown_block", ThrownBlock::new, MobCategory.MISC,
            b -> b.sized(1.0F, 1.0F).eyeHeight(0.5F).clientTrackingRange(4).updateInterval(4));

    public static void init() {}

}
