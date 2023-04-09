package com.Taitai.taitaimod.item.armor;

import com.Taitai.taitaimod.keybind.TaitaiKeyBind;
import com.Taitai.taitaimod.main.TaitaiMod;
import com.Taitai.taitaimod.regi.TaitaiModItems;
import com.Taitai.taitaimod.regi.TaitaiNeedsMethod;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;


public class ItemFlyTrueArmor extends ArmorItem {

    public static int flySpeed = 0;

    public ItemFlyTrueArmor() {
        super(TaitaiArmorMaterials.FLY, EquipmentSlotType.HEAD, new Properties().tab(TaitaiMod.TAITAIMOD_TAB).fireResistant());
        this.setRegistryName("fly_armor");
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        if (TaitaiKeyBind.taitaiKey[1].consumeClick() && player.getItemBySlot(EquipmentSlotType.HEAD).getItem() == TaitaiModItems.FLY_ARMOR) {
            if (flySpeed == 2){
                flySpeed = 0;
            }else {
                flySpeed = flySpeed + 1;
            }
            if (flySpeed == 0){
                player.displayClientMessage(new TranslationTextComponent("FlySpeed : \u00A7bDefault\u00A7r"), true);
            }else if (flySpeed == 1){
                player.displayClientMessage(new TranslationTextComponent("FlySpeed : \u00A7b1\u00A7r"), true);
            }else if (flySpeed == 2){
                player.displayClientMessage(new TranslationTextComponent("FlySpeed : \u00A7b2\u00A7r"), true);
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> p_77624_3_, ITooltipFlag p_77624_4_) {
        TaitaiNeedsMethod.nowDamages(p_77624_1_, p_77624_3_);
        if (Screen.hasShiftDown()) {
            if (flySpeed == 0){
                p_77624_3_.add(new TranslationTextComponent("FlySpeed : \u00A7bDefault\u00A7r"));
            }else {
                p_77624_3_.add(new TranslationTextComponent("FlySpeed : " + "\u00A7b" + flySpeed + "\u00A7r"));
            }
        }
    }
}
