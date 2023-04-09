package com.Taitai.taitaimod.event;

import com.Taitai.taitaimod.item.armor.ItemTaitaiChestplate;
import net.minecraft.util.DamageSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "taitaimod", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class TaitaiArmorEvents {

    @SubscribeEvent
    public static void onDamageCut(LivingHurtEvent event){

        final DamageSource[] damageSources = {
                DamageSource.IN_FIRE,
                DamageSource.LIGHTNING_BOLT,
                DamageSource.ON_FIRE,
                DamageSource.LAVA,
                DamageSource.HOT_FLOOR,
                DamageSource.IN_WALL,
                DamageSource.CRAMMING,
                DamageSource.DROWN,
                DamageSource.STARVE,
                DamageSource.CACTUS,
                DamageSource.FALL,
                DamageSource.FLY_INTO_WALL,
                DamageSource.OUT_OF_WORLD,
                DamageSource.GENERIC,
                DamageSource.MAGIC,
                DamageSource.WITHER,
                DamageSource.ANVIL,
                DamageSource.FALLING_BLOCK,
                DamageSource.DRAGON_BREATH,
                DamageSource.DRY_OUT,
                DamageSource.SWEET_BERRY_BUSH,
        };

        for (DamageSource source : damageSources){
            if (ItemTaitaiChestplate.set == 1 && event.getSource() == source){
                event.setAmount(0);
            }
        }
    }
}
