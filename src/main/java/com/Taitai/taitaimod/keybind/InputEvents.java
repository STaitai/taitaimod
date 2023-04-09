package com.Taitai.taitaimod.keybind;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = "taitaimod", bus = Bus.FORGE,value = Dist.CLIENT)
public class InputEvents {

    @SubscribeEvent
    public static void onKeyPress(InputEvent.KeyInputEvent event){
        Minecraft mc = Minecraft.getInstance();
        if (mc.level==null)return;
    }

    @SubscribeEvent
    public static void onMouseClick(InputEvent.MouseInputEvent event){
        Minecraft mc = Minecraft.getInstance();
        if (mc.level==null)return;
    }
}
