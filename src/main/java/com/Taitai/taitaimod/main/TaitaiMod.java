package com.Taitai.taitaimod.main;

import com.Taitai.taitaimod.entity.TaitaiEntityTypes;
import com.Taitai.taitaimod.regi.TaitaiModBlocks;
import com.Taitai.taitaimod.regi.TaitaiModItems;
import com.Taitai.taitaimod.regi.TaitaiModTab;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("taitaimod")
public class TaitaiMod {

    public static final ItemGroup TAITAIMOD_TAB = new TaitaiModTab();

    public TaitaiMod(){
        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        TaitaiEntityTypes.REGISTER.register(bus);


    }
}
