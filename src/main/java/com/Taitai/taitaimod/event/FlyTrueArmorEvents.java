package com.Taitai.taitaimod.event;

import com.Taitai.taitaimod.item.armor.ItemFlyTrueArmor;
import com.Taitai.taitaimod.regi.TaitaiModItems;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "taitaimod", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class FlyTrueArmorEvents {

    @SubscribeEvent
    public static void playerFlyTure(TickEvent.PlayerTickEvent event){
        if (event.player.getItemBySlot(EquipmentSlotType.HEAD).getItem() == TaitaiModItems.FLY_ARMOR){
            event.player.abilities.mayfly = true;
            if (ItemFlyTrueArmor.flySpeed == 0){
                event.player.abilities.setFlyingSpeed(0.05F);
            }else if (ItemFlyTrueArmor.flySpeed == 1){
                event.player.abilities.setFlyingSpeed(0.08F);
            }else if (ItemFlyTrueArmor.flySpeed == 2){
                event.player.abilities.setFlyingSpeed(0.1F);
            }
        }
        if (event.player.getItemBySlot(EquipmentSlotType.HEAD).getItem() != TaitaiModItems.FLY_ARMOR && event.player.abilities.instabuild == false){
            event.player.abilities.mayfly = false;
        }
    }
}
