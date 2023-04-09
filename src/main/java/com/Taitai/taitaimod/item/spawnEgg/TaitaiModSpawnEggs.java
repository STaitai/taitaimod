package com.Taitai.taitaimod.item.spawnEgg;

import com.Taitai.taitaimod.main.TaitaiMod;
import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.DispenseBoatBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TaitaiModSpawnEggs extends SpawnEggItem {


    protected static final List<TaitaiModSpawnEggs> MOD_EGGS = new ArrayList<>();
    private final Lazy<? extends EntityType<?>> entityType;

    public TaitaiModSpawnEggs(final RegistryObject<? extends EntityType<?>> p_i48465_1_, int p_i48465_2_, int p_i48465_3_) {
        super(null, p_i48465_2_, p_i48465_3_, new Properties().tab(TaitaiMod.TAITAIMOD_TAB));
        this.entityType = Lazy.of(p_i48465_1_);
        MOD_EGGS.add(this);
    }

    private static void initModSpawnEgg(){
        final Map<EntityType<?>, SpawnEggItem> EGGS = ObfuscationReflectionHelper.getPrivateValue(SpawnEggItem.class, null, "field_195987_b");
        DefaultDispenseItemBehavior behavior = new DefaultDispenseItemBehavior(){
            @Override
            protected ItemStack execute(IBlockSource p_82487_1_, ItemStack p_82487_2_) {
                Direction direction = p_82487_1_.getBlockState().getValue(DispenserBlock.FACING);
                EntityType<?> type = ((SpawnEggItem)p_82487_2_.getItem()).getType(p_82487_2_.getTag());
                type.spawn(p_82487_1_.getLevel(), p_82487_2_, null, p_82487_1_.getPos().relative(direction), SpawnReason.DISPENSER, direction == Direction.DOWN, true);
                p_82487_2_.shrink(1);
                return p_82487_2_;
            }
        };

        for (SpawnEggItem egg : MOD_EGGS){
            EGGS.put(egg.getType(null), egg);
            DispenserBlock.registerBehavior(egg, behavior);
        }
        MOD_EGGS.clear();
    }

    @Override
    public EntityType<?> getType(@Nullable CompoundNBT p_208076_1_) {
        return entityType.get();
    }

    @Mod.EventBusSubscriber(modid = "taitaimod", bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class Register{

        @SubscribeEvent
        public static void spawnEgg(final RegistryEvent.Register<EntityType<?>> event){
            initModSpawnEgg();
        }
    }
}
