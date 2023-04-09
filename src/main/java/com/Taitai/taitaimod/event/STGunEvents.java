package com.Taitai.taitaimod.event;

import com.Taitai.taitaimod.effect.TaitaiEffects;
import com.Taitai.taitaimod.regi.TaitaiModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "taitaimod", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class STGunEvents {

    @SubscribeEvent
    public static void scopeMode(FOVUpdateEvent event){
        ItemStack stack = event.getEntity().getMainHandItem();
        PlayerEntity player = Minecraft.getInstance().player;
        if (stack.getItem() == TaitaiModItems.STGUN && stack.getTag().getInt("scope") == 1 || player.hasEffect(TaitaiEffects.EFFECT_SCOPE)){
            event.setNewfov(0.3F);
        }
        if (event.getNewfov() == 0.3F){
            Minecraft.getInstance().options.sensitivity = 0.05D;
        }else {
            Minecraft.getInstance().options.sensitivity = 0.5D;
        }
    }

    @SubscribeEvent
    public static void itemInFrame(RenderHandEvent event){
        PlayerEntity player = Minecraft.getInstance().player;
        ItemStack stack =  player.getMainHandItem();
        if (stack.getItem() == TaitaiModItems.STGUN && stack.getTag().getInt("scope") == 1){
            event.setCanceled(true);
        }
    }
}
