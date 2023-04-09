package com.Taitai.taitaimod.item;

import com.Taitai.taitaimod.main.TaitaiMod;
import com.Taitai.taitaimod.regi.TaitaiModItems;
import com.Taitai.taitaimod.regi.TaitaiNeedsMethod;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemEnchantLot extends Item {
    public ItemEnchantLot() {
        super(new Properties().fireResistant().tab(TaitaiMod.TAITAIMOD_TAB).defaultDurability(500));
        this.setRegistryName("enchant_lot");
    }

    @Override
    public ActionResult<ItemStack> use(World p_77659_1_, PlayerEntity p_77659_2_, Hand p_77659_3_){
        ItemStack stack = p_77659_2_.getItemInHand(p_77659_3_);
        ItemStack stack1 = new ItemStack(TaitaiModItems.ENCHANT);
        if (p_77659_2_.experienceLevel > 5) {
            p_77659_2_.giveExperienceLevels(-5);
            p_77659_2_.addItem(stack1);
            if (!p_77659_2_.inventory.add(stack1)){
                p_77659_2_.drop(stack1, false);
            }
            p_77659_1_.playSound(null, p_77659_2_.getX(), p_77659_2_.getY(), p_77659_2_.getZ(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 1.0F, 1.0F);
            TaitaiNeedsMethod.stackHurtAndBreak(stack, p_77659_2_, 1);
        }else {
        p_77659_2_.displayClientMessage(new StringTextComponent("You need 5 ExperienceLevels"), true);
        }
        return ActionResult.consume(stack);
    }

    @Override
    public void appendHoverText(ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> p_77624_3_, ITooltipFlag p_77624_4_) {
        TaitaiNeedsMethod.nowDamages(p_77624_1_, p_77624_3_);
    }
}
