package com.Taitai.taitaimod.entity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "taitaimod", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SpawnTaitaiEntites {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void entitySpawn(BiomeLoadingEvent event){
        onDesertSpawns(event);
    }

    private static void onDesertSpawns(BiomeLoadingEvent event){
        if (event.getCategory() == Biome.Category.DESERT){
            event.getSpawns().addSpawn(EntityClassification.MONSTER,
                    new MobSpawnInfo.Spawners(TaitaiEntityTypes.TAITAI_AKUMA.get(),1000000000,2,3));
        }
    }
}
