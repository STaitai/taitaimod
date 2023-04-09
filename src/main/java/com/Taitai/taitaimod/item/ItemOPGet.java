package com.Taitai.taitaimod.item;

import com.Taitai.taitaimod.main.TaitaiMod;
import com.Taitai.taitaimod.regi.TaitaiNeedsMethod;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemOPGet extends Item {

    public ItemOPGet() {
        super(new Properties().tab(TaitaiMod.TAITAIMOD_TAB).fireResistant().defaultDurability(30));
        this.setRegistryName("op_get");
    }

    @Override
    public ActionResult<ItemStack> use(World p_77659_1_, PlayerEntity p_77659_2_, Hand p_77659_3_) {
        ItemStack stack = p_77659_2_.getItemInHand(p_77659_3_);
        TaitaiNeedsMethod.giveRandomOPItem(p_77659_2_);
        TaitaiNeedsMethod.stackHurtAndBreak(stack, p_77659_2_, 1);
        return ActionResult.consume(stack);
    }

    @Override
    public void appendHoverText(ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> p_77624_3_, ITooltipFlag p_77624_4_) {
        TaitaiNeedsMethod.nowDamages(p_77624_1_, p_77624_3_);
    }
}
