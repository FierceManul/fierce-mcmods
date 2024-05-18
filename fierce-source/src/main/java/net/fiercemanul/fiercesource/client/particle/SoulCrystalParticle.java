package net.fiercemanul.fiercesource.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SoulCrystalParticle extends TextureSheetParticle {


    protected final SpriteSet spriteSet;
    protected final float size;

    protected SoulCrystalParticle(ClientLevel pLevel, double pX, double pY, double pZ, SpriteSet spriteSet) {
        this(pLevel, pX, pY, pZ, 0.0, 0.0, 0.0, spriteSet);
    }

    protected SoulCrystalParticle(ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed, SpriteSet spriteSet) {
        this(
                pLevel,
                spriteSet,
                pX,
                pY,
                pZ,
                pXSpeed,
                pYSpeed,
                pZSpeed,
                1.0F,
                1.0F,
                1.0F,
                SoulCrystalParticleProvider.DEFAULT_SIZE.get(),
                SoulCrystalParticleProvider.DEFAULT_LIFETIME.get()
        );
    }

    protected SoulCrystalParticle(
            ClientLevel pLevel, SpriteSet spriteSet, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed, float pRed, float pGreen, float pBlue, float pSize, int pLifeTick
    ) {
        super(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed);
        this.size = pSize;
        this.rCol = pRed;
        this.gCol = pGreen;
        this.bCol = pBlue;
        this.alpha = 0.0F;
        this.lifetime = pLifeTick;
        this.xd *= pXSpeed;
        this.yd *= pYSpeed;
        this.zd *= pZSpeed;
        this.friction = 0.8F;
        this.gravity = 0.0F;
        this.hasPhysics = false;

        this.spriteSet = spriteSet;
        this.setSpriteFromAge(this.spriteSet);
    }

    @Override
    protected int getLightColor(float pPartialTick) {
        return 15728880;
    }

    @Override
    public void tick() {
        setSpriteFromAge(this.spriteSet);
        float f = (float) Math.sin((double) age / lifetime * Math.PI);
        setAlpha(f);
        quadSize = size * f;
        super.tick();
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }
}
