package com.Taitai.taitaimod.item;

import com.Taitai.taitaimod.main.TaitaiMod;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemTfood extends Item{
    public ItemTfood() {
        super(new Properties().tab(TaitaiMod.TAITAIMOD_TAB).fireResistant().food(new Food.Builder()
                .nutrition(10)
                .saturationMod(1.2F)
                .effect(new EffectInstance(Effects.REGENERATION, 400, 1), 1.0F)
                .effect(new EffectInstance(Effects.DAMAGE_RESISTANCE, 6000, 0), 1.0F)
                .effect(new EffectInstance(Effects.FIRE_RESISTANCE, 6000, 0), 1.0F)
                .effect(new EffectInstance(Effects.ABSORPTION, 2400, 3), 1.0F)
                .alwaysEat()
                .fast()
                .build()));
        this.setRegistryName("tfood");
    }



    @Override
    public void appendHoverText(ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> p_77624_3_, ITooltipFlag p_77624_4_) {
        p_77624_3_.add(new TranslationTextComponent(this.getDescriptionId() + ".desc").withStyle(TextFormatting.GRAY));
    }
}
