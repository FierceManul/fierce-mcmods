package net.fiercemanul.fiercesource.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.client.renderer.LightTexture;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SoulCrystalParticle extends TextureSheetParticle {


    protected final float size;
    protected final float maxAlpha;

    public SoulCrystalParticle(
            ClientLevel level,
            double x, double y, double z,
            float red, float green, float blue, float maxAlpha,
            float size, int lifeTick,
            SpriteSet spriteSet
    ) {
        super(level, x, y, z);
        this.friction = 0.8F;
        this.gravity = 0;
        this.hasPhysics = false;
        this.maxAlpha = maxAlpha;
        this.size = size;
        setColor(red, green, blue);
        setAlpha(0);
        setSize(size, size * 2);
        setLifetime(lifeTick * 3);
        setSpriteFromAge(spriteSet);
    }

    @Override
    protected int getLightColor(float partialTick) {
        return LightTexture.FULL_BRIGHT;
    }

    @Override
    public void tick() {
        float f = (float) Math.sin((double) age / lifetime * Math.PI);
        setAlpha(f * maxAlpha);
        quadSize = size * f;
        if (age++ >= lifetime) remove();
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

}
