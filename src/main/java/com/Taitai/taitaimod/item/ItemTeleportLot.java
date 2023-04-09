package com.Taitai.taitaimod.item;

import com.Taitai.taitaimod.main.TaitaiMod;
import com.Taitai.taitaimod.regi.TaitaiNeedsMethod;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemTeleportLot extends Item {
    public ItemTeleportLot() {
        super(new Properties().fireResistant().tab(TaitaiMod.TAITAIMOD_TAB).defaultDurability(1000));
        this.setRegistryName("teleport_lot");
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand hand){
        ItemStack stack = playerIn.getItemInHand(hand);
        TaitaiNeedsMethod.teleport(worldIn, playerIn, 8);
        TaitaiNeedsMethod.stackHurtAndBreak(stack, playerIn, 1);
        return ActionResult.consume(stack);
    }

    @Override
    public void appendHoverText(ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> p_77624_3_, ITooltipFlag p_77624_4_) {
        TaitaiNeedsMethod.nowDamages(p_77624_1_, p_77624_3_);
    }
}
