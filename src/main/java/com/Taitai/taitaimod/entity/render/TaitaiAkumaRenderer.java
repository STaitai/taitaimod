package com.Taitai.taitaimod.entity.render;

import com.Taitai.taitaimod.entity.TaitaiAkumaEntity;
import com.Taitai.taitaimod.entity.model.TaitaiAkumaModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class TaitaiAkumaRenderer extends MobRenderer<TaitaiAkumaEntity, TaitaiAkumaModel<TaitaiAkumaEntity>> {

    private static final ResourceLocation TAITAI_AKUMA  = new ResourceLocation("taitaimod", "textures/entity/mob/taitai_akuma.png");

    public TaitaiAkumaRenderer(EntityRendererManager p_i50961_1_) {
        super(p_i50961_1_, new TaitaiAkumaModel<>(), 0.2F);
    }

    @Override
    public ResourceLocation getTextureLocation(TaitaiAkumaEntity p_110775_1_) {
        return TAITAI_AKUMA;
    }

    protected void scale(TaitaiAkumaEntity p_225620_1_, MatrixStack p_225620_2_, float p_225620_3_) {
        float f = 0.6F;
        p_225620_2_.scale(f, f, f);
    }

}
