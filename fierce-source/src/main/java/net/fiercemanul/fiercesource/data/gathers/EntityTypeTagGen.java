package net.fiercemanul.fiercesource.data.gathers;

import net.fiercemanul.fiercesource.FierceSource;
import net.fiercemanul.fiercesource.data.FSEntities;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class EntityTypeTagGen extends EntityTypeTagsProvider {


    protected EntityTypeTagGen(
            PackOutput output,
            CompletableFuture<HolderLookup.Provider> lookupProvider,
            @Nullable ExistingFileHelper existingFileHelper
    ) {
        super(output, lookupProvider, FierceSource.FC_MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(EntityTypeTags.IMPACT_PROJECTILES).add(FSEntities.THROWN_BLOCK.get());
        //有干扰，不能用，用自定义逻辑
        //tag(EntityTypeTags.REDIRECTABLE_PROJECTILE).add(FSEntities.THROWN_BLOCK.get());
        tag(EntityTypeTags.FALL_DAMAGE_IMMUNE).add(FSEntities.THROWN_BLOCK.get());
    }
    //TODO:可以将其他taggen像这个这样继承覆盖原版已经做好的gen

}
