package com.Taitai.taitaimod.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.BiFunction;

public class TaitaiEntityTypes{

    public static final DeferredRegister<EntityType<?>> REGISTER =DeferredRegister.create(ForgeRegistries.ENTITIES,"taitaimod");


    public static final RegistryObject<EntityType<STAmmoEntity>> STAmmo = registerSTAmmo("stammo", STAmmoEntity::new);
    public static final RegistryObject<EntityType<TaitaiTnt2Entity>> Taitai_Tnt2 = registerTaitaiTnt2("taitai_tnt2", TaitaiTnt2Entity::new);
    public static final RegistryObject<EntityType<TaitaiTntEntity>> Taitai_Tnt = registerTaitaiTnt("taitai_tnt", TaitaiTntEntity::new);
    public static final RegistryObject<EntityType<TaitaiAkumaEntity>> TAITAI_AKUMA = akumaEntityRegister("taitai_akuma", TaitaiAkumaEntity::new);
    public static final RegistryObject<EntityType<TAmmoEntity>> TAmmo = registerTAmmo("tammo", TAmmoEntity::new);
    public static final RegistryObject<EntityType<PTAmmoEntity>> PTAmmo = registerPTAmmo("pt_ammo", PTAmmoEntity::new);
    public static final RegistryObject<EntityType<TSAmmoEntity>> TSAmmo = registerTSAmmo("ts_ammo", TSAmmoEntity::new);


    private static <T extends Entity>RegistryObject<EntityType<T>> registerTaitaiTnt(String id, BiFunction<EntityType<T>, World, T> function){
        EntityType<T> type = EntityType.Builder.of(function::apply, EntityClassification.MISC).fireImmune().sized(0.98F, 0.98F).clientTrackingRange(10).updateInterval(10).build(id);
        return REGISTER.register(id, () -> type);
    }

    private static <T extends Entity>RegistryObject<EntityType<T>> registerTaitaiTnt2(String id, BiFunction<EntityType<T>, World, T> function){
        EntityType<T> type = EntityType.Builder.of(function::apply, EntityClassification.MISC).fireImmune().sized(0.98F, 0.98F).clientTrackingRange(10).updateInterval(10).build(id);
        return REGISTER.register(id, () -> type);
    }

    private static <T extends Entity>RegistryObject<EntityType<T>> registerSTAmmo(String id, BiFunction<EntityType<T>, World, T> function){
        EntityType<T> type = EntityType.Builder.of(function::apply, EntityClassification.MISC).sized(0.2F,0.2F).clientTrackingRange(100).fireImmune().setShouldReceiveVelocityUpdates(true).updateInterval(20).build(id);
        return REGISTER.register(id, () -> type);
    }

    private static <T extends Entity>RegistryObject<EntityType<T>> registerTAmmo(String id, BiFunction<EntityType<T>, World, T> function){
        EntityType<T> type = EntityType.Builder.of(function::apply, EntityClassification.MISC).sized(0.2F,0.2F).clientTrackingRange(100).fireImmune().setShouldReceiveVelocityUpdates(true).updateInterval(20).build(id);
        return REGISTER.register(id, () -> type);
    }

    private static <T extends Entity>RegistryObject<EntityType<T>> akumaEntityRegister(String id, BiFunction<EntityType<T>, World, T> function){
        EntityType<T> type = EntityType.Builder.of(function::apply, EntityClassification.CREATURE).sized(0.6F,1.35F).clientTrackingRange(32).build(id);
        return REGISTER.register(id, () -> type);
    }

    private static <T extends Entity>RegistryObject<EntityType<T>> registerPTAmmo(String id, BiFunction<EntityType<T>, World, T> function){
        EntityType<T> type = EntityType.Builder.of(function::apply, EntityClassification.MISC).sized(0.2F,0.2F).clientTrackingRange(100).fireImmune().setShouldReceiveVelocityUpdates(true).updateInterval(20).build(id);
        return REGISTER.register(id, () -> type);
    }

    private static <T extends Entity>RegistryObject<EntityType<T>> registerTSAmmo(String id, BiFunction<EntityType<T>, World, T> function){
        EntityType<T> type = EntityType.Builder.of(function::apply, EntityClassification.MISC).sized(0.2F,0.2F).clientTrackingRange(100).fireImmune().setShouldReceiveVelocityUpdates(true).updateInterval(20).build(id);
        return REGISTER.register(id, () -> type);
    }



}
