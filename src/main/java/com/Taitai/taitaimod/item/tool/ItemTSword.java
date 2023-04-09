package com.Taitai.taitaimod.item.tool;

import com.Taitai.taitaimod.entity.TaitaiTntEntity;
import com.Taitai.taitaimod.main.TaitaiMod;
import com.Taitai.taitaimod.regi.TaitaiNeedsMethod;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemTSword extends SwordItem {

    public ItemTSword() {
        super(TaitaiModTires.TAITAI,6,-1F, new Properties().tab(TaitaiMod.TAITAIMOD_TAB).fireResistant());
        this.setRegistryName("tsword");
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand hand){
        ItemStack stack = playerIn.getItemInHand(hand);
        Vector3d vector3d = playerIn.getViewVector(1.0F);
        if (playerIn.experienceLevel > 5 && !playerIn.isShiftKeyDown()) {
            playerIn.giveExperienceLevels(-5);
            TaitaiTntEntity taitaitntEntity = new TaitaiTntEntity(worldIn, playerIn.getX(), playerIn.getY() + 1, playerIn.getZ(), playerIn);
            taitaitntEntity.setDeltaMovement(vector3d.x, vector3d.y, vector3d.z);
            worldIn.addFreshEntity(taitaitntEntity);
        }else if (playerIn.experienceLevel > 5 && playerIn.isShiftKeyDown()){
            playerIn.giveExperienceLevels(-5);
            stack.enchant(Enchantments.SHARPNESS, 5);
        }else {
            playerIn.displayClientMessage(new StringTextComponent("You need 5 ExperienceLevels"), true);
        }
        TaitaiNeedsMethod.stackHurtAndBreak(stack, playerIn, 10);


        return ActionResult.consume(stack);
    }

    /**
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        PlayerEntity player = Minecraft.getInstance().player;
        for (int index0 = 0; index0 < (int) (100000); index0++){
            target.hurt(DamageSource.playerAttack(player), 9999);
        }

        return true;
    }*/

    @Override
    public void appendHoverText(ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> p_77624_3_, ITooltipFlag p_77624_4_) {
        TaitaiNeedsMethod.nowDamages(p_77624_1_, p_77624_3_);
    }
}
