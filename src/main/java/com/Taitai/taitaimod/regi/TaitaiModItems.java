package com.Taitai.taitaimod.regi;

import com.Taitai.taitaimod.item.*;
import com.Taitai.taitaimod.item.armor.*;
import com.Taitai.taitaimod.item.spawnEgg.ItemTaitaiAkumaSpawnEgg;
import com.Taitai.taitaimod.item.tool.ItemTAxe;
import com.Taitai.taitaimod.item.tool.ItemTPickaxe;
import com.Taitai.taitaimod.item.tool.ItemTShovel;
import com.Taitai.taitaimod.item.tool.ItemTSword;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;


@ObjectHolder("taitaimod")
public class TaitaiModItems {


    public static final ItemTngot TNGOT = new ItemTngot();
    public static final ItemTfood TFOOD = new ItemTfood();
    public static final ItemTPickaxe TPICKAXE = new ItemTPickaxe();
    public static final ItemTSword TSWORD = new ItemTSword();
    public static final ItemTAxe TAXE = new ItemTAxe();
    public static final ItemTShovel TSHOVEL = new ItemTShovel();
    public static final ItemTaitaiHelmet TAITAI_HELMETHELMET = new ItemTaitaiHelmet();
    public static final ItemTaitaiChestplate TAITAI_CHESTPLATE = new ItemTaitaiChestplate();
    public static final ItemTaitaiLeggings TAITAI_LEGGINGS = new ItemTaitaiLeggings();
    public static final ItemTaitaiBoots TAITAI_BOOTS = new ItemTaitaiBoots();
    public static final ItemTGun TGUN = new ItemTGun();
    public static final ItemTAmmo TAMMO = new ItemTAmmo();
    public static final ItemSTGun STGUN = new ItemSTGun();
    public static final ItemSTAmmo STAMMO = new ItemSTAmmo();
    public static final ItemTaitaiAkumaSpawnEgg AKUMA_SPAWN_EGG = new ItemTaitaiAkumaSpawnEgg();
    public static final ItemMawaru MAWARU = new ItemMawaru();
    public static final ItemGameModeChange GAME_MODE_CHANGE = new ItemGameModeChange();
    public static final ItemTeleportLot TELEPORT_LOT = new ItemTeleportLot();
    public static final ItemTSGun TS_GUN = new ItemTSGun();
    public static final ItemEnchant ENCHANT = new ItemEnchant();
    public static final ItemEnchantLot ENCHANT_LOT = new ItemEnchantLot();
    public static final ItemEnchant50 ENCHANT50 = new ItemEnchant50();
    public static final ItemLightClick LIGHT_CLICK = new ItemLightClick();
    public static final ItemPTGun PT_GUN = new ItemPTGun();
    public static final ItemTntGun TNT_GUN = new ItemTntGun();
    public static final ItemTaitaiPotion TAITAI_POTION = new ItemTaitaiPotion();
    public static final ItemCreeper CREEPER = new ItemCreeper();
    public static final ItemOPGet OP_GET = new ItemOPGet();
    public static final ItemFlyTrueArmor FLY_ARMOR = new ItemFlyTrueArmor();


    @Mod.EventBusSubscriber(modid = "taitaimod",bus = Bus.MOD)
    public static class Register{

        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> event){



            final Item[] item = {

                    TNGOT,
                    TFOOD,
                    TPICKAXE,
                    TSWORD,
                    TAXE,
                    TSHOVEL,
                    TAITAI_HELMETHELMET,
                    TAITAI_CHESTPLATE,
                    TAITAI_LEGGINGS,
                    TAITAI_BOOTS,
                    TAMMO,
                    TGUN,
                    STGUN,
                    STAMMO,
                    AKUMA_SPAWN_EGG,
                    MAWARU,
                    GAME_MODE_CHANGE,
                    TELEPORT_LOT,
                    TS_GUN,
                    ENCHANT,
                    ENCHANT_LOT,
                    ENCHANT50,
                    LIGHT_CLICK,
                    PT_GUN,
                    TNT_GUN,
                    TAITAI_POTION,
                    CREEPER,
                    OP_GET,
                    FLY_ARMOR

            };

            event.getRegistry().registerAll(item);
        }
    }
}