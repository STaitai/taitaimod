package com.Taitai.taitaimod.item.armor;

import com.Taitai.taitaimod.main.TaitaiMod;
import com.Taitai.taitaimod.regi.TaitaiModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;

import javax.annotation.Nullable;
import java.util.List;


public class ItemTaitaiChestplate extends ArmorItem {

    public static int set = 0;

    public ItemTaitaiChestplate() {
        super(TaitaiArmorMaterials.TAITAI, EquipmentSlotType.CHEST, new Properties().tab(TaitaiMod.TAITAIMOD_TAB).fireResistant());
        this.setRegistryName("taitai_chestplate");
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {

        if (!world.isClientSide && player.getItemBySlot(EquipmentSlotType.HEAD).getItem() == TaitaiModItems.TAITAI_HELMETHELMET
                && player.getItemBySlot(EquipmentSlotType.CHEST).getItem() == TaitaiModItems.TAITAI_CHESTPLATE
                && player.getItemBySlot(EquipmentSlotType.LEGS).getItem() == TaitaiModItems.TAITAI_LEGGINGS
                && player.getItemBySlot(EquipmentSlotType.FEET).getItem() == TaitaiModItems.TAITAI_BOOTS) {

            set = 1;

        }else {

            set = 0;

        }
    }
}

