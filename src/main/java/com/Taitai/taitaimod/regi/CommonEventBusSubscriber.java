package com.Taitai.taitaimod.regi;

import com.Taitai.taitaimod.entity.TaitaiAkumaEntity;
import com.Taitai.taitaimod.entity.TaitaiEntityTypes;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = "taitaimod", bus = Bus.MOD)
public class CommonEventBusSubscriber {

    @SubscribeEvent
    public static void commonSetUp(FMLCommonSetupEvent event){
        mobAttributes();
        registerSpawnPlacement();
    }

    private static void mobAttributes(){

        DeferredWorkQueue.runLater(()->{
            GlobalEntityTypeAttributes.put(TaitaiEntityTypes.TAITAI_AKUMA.get(), TaitaiAkumaEntity.registerAttribute().build());
        });
    }

    private static void registerSpawnPlacement(){
        EntitySpawnPlacementRegistry.register(
                TaitaiEntityTypes.TAITAI_AKUMA.get(),
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                TaitaiAkumaEntity::canSpawn
                );
    }
}
