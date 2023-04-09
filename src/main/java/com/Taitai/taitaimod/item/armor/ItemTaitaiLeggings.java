package com.Taitai.taitaimod.item.armor;

import com.Taitai.taitaimod.main.TaitaiMod;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemTaitaiLeggings extends ArmorItem {

    public ItemTaitaiLeggings() {
        super(TaitaiArmorMaterials.TAITAI, EquipmentSlotType.LEGS, new Properties().tab(TaitaiMod.TAITAIMOD_TAB).fireResistant());
        this.setRegistryName("taitai_leggings");
    }
}
