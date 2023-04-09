package com.Taitai.taitaimod.keybind;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.awt.event.KeyEvent;

@OnlyIn(Dist.CLIENT)
public class TaitaiKeyBind {

    public static KeyBinding[] taitaiKey;

    public static void register(final FMLClientSetupEvent event){
        taitaiKey = new KeyBinding[3];

        taitaiKey[0] = crate("tool_mode", KeyEvent.VK_R);
        taitaiKey[1] = crate("change_fly_speed", KeyEvent.VK_G);
        taitaiKey[2] = crate("item_recovery", KeyEvent.VK_V);



        for (int a = 0; a < taitaiKey.length; a++){
            ClientRegistry.registerKeyBinding(taitaiKey[a]);
        }
    }

    private static KeyBinding crate(String name, int key){
        return new KeyBinding("key.taitaimod." + name, key, "key.category.taitaimod");
    }
}
