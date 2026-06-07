package net.fiercemanul.fiercelive.data;

import net.fiercemanul.fiercelive.world.entity.FakeGlowPearlArrow;
import net.fiercemanul.fiercelive.world.entity.Seat;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;

import static net.fiercemanul.fiercelive.data.registries.FLRegister.ENTITY_TYPES;
import static net.fiercemanul.fiercesource.data.registries.FCRegistries.regEntityType;

public final class FLEntities {


    public static final DeferredHolder<EntityType<?>, EntityType<FakeGlowPearlArrow>> FAKE_GLOW_PEARL_ARROW = regEntityType(
            ENTITY_TYPES, "fake_glow_pearl_arrow", FakeGlowPearlArrow::new, MobCategory.MISC,
            b -> b.sized(0.5F, 0.5F).eyeHeight(0.13F).noSave().clientTrackingRange(10).updateInterval(20)
    );
    public static final DeferredHolder<EntityType<?>, EntityType<Seat>> SEAT = regEntityType(
            ENTITY_TYPES, "seat", Seat::new, MobCategory.MISC,
            b -> b.sized(0.6F, 0.6F).eyeHeight(0.0F).fireImmune().noSave().clientTrackingRange(10).updateInterval(20)
    );

    public static void init() {}

    private FLEntities() {}

}
