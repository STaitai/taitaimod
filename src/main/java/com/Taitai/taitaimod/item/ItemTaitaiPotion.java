package com.Taitai.taitaimod.item;

import com.Taitai.taitaimod.effect.TaitaiEffects;
import com.Taitai.taitaimod.main.TaitaiMod;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class ItemTaitaiPotion extends Item {
    public ItemTaitaiPotion() {
        super(new Properties().tab(TaitaiMod.TAITAIMOD_TAB).stacksTo(1));
        this.setRegistryName("taitai_potion");
    }

    public ItemStack finishUsingItem(ItemStack p_77654_1_, World p_77654_2_, LivingEntity p_77654_3_) {
        super.finishUsingItem(p_77654_1_, p_77654_2_, p_77654_3_);
        PlayerEntity p_77659_2_ = Minecraft.getInstance().player;
        Hand p_77659_3_ = Hand.MAIN_HAND;
        ItemStack stack = p_77659_2_.getItemInHand(p_77659_3_);
        if (p_77654_3_ instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)p_77654_3_;
            CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, p_77654_1_);
            serverplayerentity.awardStat(Stats.ITEM_USED.get(this));
        }

        if (!p_77654_2_.isClientSide) {
            p_77654_3_.addEffect(new EffectInstance(TaitaiEffects.EFFECT_NULL_DAMAGE, 3600, 1));
        }


        if (p_77654_1_.isEmpty()) {
            return new ItemStack(Items.GLASS_BOTTLE);
        } else {
            if (p_77654_3_ instanceof PlayerEntity && !((PlayerEntity)p_77654_3_).abilities.instabuild) {
                stack.shrink(1);
                ItemStack itemstack = new ItemStack(Items.GLASS_BOTTLE);
                PlayerEntity playerentity = (PlayerEntity)p_77654_3_;
                if (!playerentity.inventory.add(itemstack)) {
                    playerentity.drop(itemstack, false);
                }
            }

            return p_77654_1_;
        }
    }

    public int getUseDuration(ItemStack p_77626_1_) {
        return 30;
    }

    public UseAction getUseAnimation(ItemStack p_77661_1_) {
        return UseAction.DRINK;
    }

    public SoundEvent getDrinkingSound() {
        return SoundEvents.WANDERING_TRADER_DRINK_POTION;
    }

    public SoundEvent getEatingSound() {
        return SoundEvents.WANDERING_TRADER_DRINK_POTION;
    }

    public ActionResult<ItemStack> use(World p_77659_1_, PlayerEntity p_77659_2_, Hand p_77659_3_) {
        return DrinkHelper.useDrink(p_77659_1_, p_77659_2_, p_77659_3_);
    }
}
