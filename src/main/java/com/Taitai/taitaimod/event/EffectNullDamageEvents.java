package com.Taitai.taitaimod.event;

import com.Taitai.taitaimod.effect.TaitaiEffects;
import net.minecraft.util.DamageSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "taitaimod", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class EffectNullDamageEvents {


    @SubscribeEvent
    public static void NullFallDamage(LivingHurtEvent event){

        final DamageSource[] damageSources = {
                DamageSource.FALL,
        };

        for (DamageSource source:damageSources){
            if (event.getEntityLiving().hasEffect(TaitaiEffects.EFFECT_NULL_DAMAGE) && event.getSource() == source){
                event.setAmount(0);
            }
        }
    }
}
