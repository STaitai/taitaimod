package com.Taitai.taitaimod.item;

import com.Taitai.taitaimod.entity.TSAmmoEntity;
import com.Taitai.taitaimod.main.TaitaiMod;
import com.Taitai.taitaimod.regi.TaitaiModItems;
import com.Taitai.taitaimod.regi.TaitaiNeedsMethod;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShootableItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Predicate;

public class ItemTSGun extends ShootableItem {

    public static final Predicate<ItemStack> AMMO = stack -> stack.getItem() == TaitaiModItems.TAMMO;
    public static int time = 0;

    public ItemTSGun() {
        super(new Properties().tab(TaitaiMod.TAITAIMOD_TAB).fireResistant().durability(60000));
        this.setRegistryName("ts_gun");
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return AMMO;
    }

    @Override
    public int getDefaultProjectileRange() {
        return 10;
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int p_77663_4_, boolean p_77663_5_) {
        PlayerEntity player = (PlayerEntity) entityIn;
        if (player.getItemBySlot(EquipmentSlotType.MAINHAND).getItem() == TaitaiModItems.TS_GUN){
            player.addEffect(new EffectInstance(Effects.NIGHT_VISION,0,0,false,false));
        }
    }

    @Override
    public ActionResult<ItemStack> use(World p_77659_1_, PlayerEntity p_77659_2_, Hand p_77659_3_){
        ItemStack stack = p_77659_2_.getItemInHand(p_77659_3_);
        Vector3d v3 = p_77659_2_.getViewVector(1F);
        ItemStack itemStack = p_77659_2_.getProjectile(stack);

        if(!p_77659_1_.isClientSide && itemStack.getItem() == TaitaiModItems.TAMMO && time == 0) {
            ItemStack ammo = new ItemStack(Items.AIR);
            AbstractArrowEntity ammoEntity = new TSAmmoEntity(p_77659_1_, p_77659_2_, ammo);

            ammoEntity.shoot(v3.x, v3.y, v3.z, 20F, 0F);

            p_77659_1_.addFreshEntity(ammoEntity);

            p_77659_1_.playSound(null, p_77659_2_.getX(), p_77659_2_.getY(), p_77659_2_.getZ(), SoundEvents.DRAGON_FIREBALL_EXPLODE, SoundCategory.PLAYERS, 1.0F, 1.0F);

            p_77659_2_.startUsingItem(p_77659_3_);
            if (!p_77659_2_.abilities.instabuild){
                itemStack.shrink(1);
            }

            TaitaiNeedsMethod.stackHurtAndBreak(stack, p_77659_2_, 1);


            time = 1;
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        ammoEntity.remove();
                        time = 0;
                    }
                }, 36);

            return ActionResult.consume(stack);
        }

        p_77659_1_.playSound(null, p_77659_2_.getX(), p_77659_2_.getY(), p_77659_2_.getZ(), SoundEvents.FLINTANDSTEEL_USE, SoundCategory.PLAYERS, 1.0F, 1.0F);
        return ActionResult.fail(stack);
    }

    @Override
    public void appendHoverText(ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> p_77624_3_, ITooltipFlag p_77624_4_) {
        TaitaiNeedsMethod.nowDamages(p_77624_1_, p_77624_3_);
    }
}
