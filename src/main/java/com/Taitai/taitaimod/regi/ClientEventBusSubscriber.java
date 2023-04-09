package com.Taitai.taitaimod.regi;

import com.Taitai.taitaimod.entity.TaitaiEntityTypes;
import com.Taitai.taitaimod.entity.render.*;
import com.Taitai.taitaimod.keybind.TaitaiKeyBind;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = "taitaimod", bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event){
        TaitaiKeyBind.register(event);

        renderRegister();
    }

    private static void renderRegister(){
        RenderingRegistry.registerEntityRenderingHandler(TaitaiEntityTypes.STAmmo.get(), STAmmoRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(TaitaiEntityTypes.Taitai_Tnt.get(), TaitaiTntRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(TaitaiEntityTypes.Taitai_Tnt2.get(), TaitaiTnt2Renderer::new);
        RenderingRegistry.registerEntityRenderingHandler(TaitaiEntityTypes.TAITAI_AKUMA.get(), TaitaiAkumaRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(TaitaiEntityTypes.TAmmo.get(), TAmmoRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(TaitaiEntityTypes.PTAmmo.get(), PTAmmoRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(TaitaiEntityTypes.TSAmmo.get(), TSAmmoRenderer::new);
    }
}
