package com.Taitai.taitaimod.item;

import com.Taitai.taitaimod.main.TaitaiMod;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemCreeper extends Item {

    public static int ok = 0;

    public ItemCreeper() {
        super(new Properties().tab(TaitaiMod.TAITAIMOD_TAB).fireResistant());
        this.setRegistryName("creeper");
    }

    @Override
    public boolean hurtEnemy(ItemStack p_77644_1_, LivingEntity target, LivingEntity attacker) {
        if(target.getType() == EntityType.CREEPER && target.getHealth() == 0){
            ok = ok + 1;
        }
        return true;
    }


    @Override
    public ActionResult<ItemStack> use(World p_77659_1_, PlayerEntity p_77659_2_, Hand p_77659_3_) {
        ItemStack stack = p_77659_2_.getItemInHand(p_77659_3_);
        if (ok > 0 && !p_77659_1_.isClientSide){
            ok = ok -1;
            p_77659_2_.level.explode(p_77659_2_, p_77659_2_.getX(), p_77659_2_.getY(0.0625D), p_77659_2_.getZ(), 4.0F, Explosion.Mode.BREAK);
        }
        return ActionResult.consume(stack);
    }

    @Override
    public void appendHoverText(ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> p_77624_3_, ITooltipFlag p_77624_4_) {
            p_77624_3_.add(new TranslationTextComponent("You can use it " + ok + " times"));
    }
}
