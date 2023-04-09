package com.Taitai.taitaimod.event;

import com.Taitai.taitaimod.item.ItemOPGet;
import com.Taitai.taitaimod.keybind.TaitaiKeyBind;
import com.Taitai.taitaimod.regi.TaitaiNeedsMethod;
import com.sun.jna.platform.win32.WinDef;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.monster.HuskEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.*;

@Mod.EventBusSubscriber(modid = "taitaimod", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class WorldEvents {

    public static int tick = 0;

     /**
    @SubscribeEvent
    public static void playerEvent(TickEvent.PlayerTickEvent event){
        World world = event.player.level;
        if (event.phase == TickEvent.Phase.END){
            tick = tick + 1;

            if (tick % 400 == 0 && !world.isClientSide){
                world.explode(event.player, event.player.getX(), event.player.getY(0.18), event.player.getZ(), 12.0F, Explosion.Mode.BREAK);
            }
        }
    }*/

     /**
    @SubscribeEvent
    public static void playerGiveEvent(TickEvent.PlayerTickEvent event){
        if (event.phase == TickEvent.Phase.END){
            tick = tick + 1;
        }
        if (tick % 400 == 0){
            ItemOPGet.giveRandomItem(event.player);
        }
    }*/

     @SubscribeEvent
    public static void stackItemRecovery(TickEvent.PlayerTickEvent event) {
         ItemStack stack = event.player.getItemInHand(Hand.MAIN_HAND);
         if (event.player.experienceLevel > 1 && TaitaiKeyBind.taitaiKey[2].consumeClick()){
             TaitaiNeedsMethod.stackHurtAndBreak(stack, event.player, -10);
             event.player.giveExperienceLevels(-1);
             ActionResult.consume(stack);
         }else if (event.player.experienceLevel < 1 && TaitaiKeyBind.taitaiKey[2].consumeClick()) {
             event.player.displayClientMessage(new TranslationTextComponent("You need 1 ExperienceLevels"), true);
         }
     }
}
