package com.Taitai.taitaimod.item;

import com.Taitai.taitaimod.effect.TaitaiEffects;
import com.Taitai.taitaimod.main.TaitaiMod;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class ItemMawaru extends Item {
    public ItemMawaru() {
        super(new Properties().tab(TaitaiMod.TAITAIMOD_TAB).fireResistant());
        this.setRegistryName("mawaru");
    }

    @Override
    public ActionResult<ItemStack> use(World p_77659_1_, PlayerEntity p_77659_2_, Hand p_77659_3_){
        ItemStack stack = p_77659_2_.getItemInHand(p_77659_3_);
        p_77659_2_.addEffect(new EffectInstance(Effects.NIGHT_VISION, 6000, 0, false, false));
        p_77659_2_.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 6000, 2, false, false));
        p_77659_2_.addEffect(new EffectInstance(TaitaiEffects.EFFECT_SCOPE, 200, 0, false, false));

        p_77659_1_.playSound(null, p_77659_2_.getX(), p_77659_2_.getY(), p_77659_2_.getZ(), SoundEvents.ANVIL_USE, SoundCategory.PLAYERS, 1.0F, 1.0F);
        stack.shrink(1);

        return ActionResult.consume(stack);
    }
}
