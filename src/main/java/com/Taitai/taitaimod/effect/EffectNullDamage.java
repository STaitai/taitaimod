package com.Taitai.taitaimod.effect;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class EffectNullDamage extends Effect {

    protected EffectNullDamage() {
        super(EffectType.HARMFUL, 7078058);
        this.setRegistryName("effect_null_damage");
    }
}
