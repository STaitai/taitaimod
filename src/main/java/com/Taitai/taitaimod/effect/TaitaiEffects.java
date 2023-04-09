package com.Taitai.taitaimod.effect;

import net.minecraft.potion.*;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder("taitaimod")
public class TaitaiEffects{

    public static final EffectScope EFFECT_SCOPE = new EffectScope();
    public static final EffectNullDamage EFFECT_NULL_DAMAGE = new EffectNullDamage();

    @Mod.EventBusSubscriber(modid = "taitaimod", bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class Register{

    @SubscribeEvent
    public static void registerEffects(final RegistryEvent.Register<Effect> event){

        final Effect[] effect = {

                EFFECT_SCOPE,
                EFFECT_NULL_DAMAGE

        };

        event.getRegistry().registerAll(effect);
    }
  }
}
