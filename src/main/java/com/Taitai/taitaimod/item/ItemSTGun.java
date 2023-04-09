package com.Taitai.taitaimod.item;

import com.Taitai.taitaimod.entity.STAmmoEntity;
import com.Taitai.taitaimod.keybind.TaitaiKeyBind;
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
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Predicate;

public class ItemSTGun extends ShootableItem {

    public static final Predicate<ItemStack> AMMO = stack -> stack.getItem() == TaitaiModItems.STAMMO;
    private static int time = 0;

    public ItemSTGun() {
        super(new Properties().tab(TaitaiMod.TAITAIMOD_TAB).fireResistant().durability(60000));
        this.setRegistryName("stgun");
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int p_77663_4_, boolean p_77663_5_) {
        PlayerEntity player = (PlayerEntity) entityIn;
        if (player.getItemBySlot(EquipmentSlotType.MAINHAND).getItem() == TaitaiModItems.STGUN){

            if (!worldIn.isClientSide && TaitaiKeyBind.taitaiKey[0].consumeClick()){
                this.scopeMode(stack);
                worldIn.playSound(null, entityIn.getX(), entityIn.getY(), entityIn.getZ(), SoundEvents.ITEM_FRAME_BREAK, SoundCategory.PLAYERS, 1.0F,1.0F);
            }
            player.addEffect(new EffectInstance(Effects.NIGHT_VISION,0,0,false,false));
        }
    }


    @Override
    public ActionResult<ItemStack> use(World p_77659_1_, PlayerEntity p_77659_2_, Hand p_77659_3_){
        ItemStack stack = p_77659_2_.getItemInHand(p_77659_3_);
        Vector3d v3 = p_77659_2_.getViewVector(1F);
        ItemStack itemStack = p_77659_2_.getProjectile(stack);
        if(!p_77659_1_.isClientSide && itemStack.getItem() == TaitaiModItems.STAMMO && time == 0) {
            ItemStack ammo = new ItemStack(Items.AIR);
            AbstractArrowEntity ammoEntity = new STAmmoEntity(p_77659_1_, p_77659_2_, ammo);
            ammoEntity.shoot(v3.x, v3.y, v3.z, 50F, 0F);
            p_77659_1_.addFreshEntity(ammoEntity);
            p_77659_1_.playSound(null, p_77659_2_.getX(), p_77659_2_.getY(), p_77659_2_.getZ(), SoundEvents.DRAGON_FIREBALL_EXPLODE, SoundCategory.PLAYERS, 1.0F, 1.0F);
            time = 1;
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    time = 0;
                    p_77659_2_.displayClientMessage(new StringTextComponent("  "), true);
                }
            }, 1000);

            if (time == 1){
                p_77659_2_.displayClientMessage(new StringTextComponent("Reloading"), true);
            }


            p_77659_2_.startUsingItem(p_77659_3_);
            if (!p_77659_2_.abilities.instabuild){
                itemStack.shrink(1);
            }

            TaitaiNeedsMethod.stackHurtAndBreak(stack, p_77659_2_, 1);



            return ActionResult.consume(stack);
        }

        p_77659_1_.playSound(null, p_77659_2_.getX(), p_77659_2_.getY(), p_77659_2_.getZ(), SoundEvents.FLINTANDSTEEL_USE, SoundCategory.PLAYERS, 1.0F, 1.0F);
        return ActionResult.fail(stack);
    }


    public void scopeMode(ItemStack stack){
        if (stack.getTag() == null){
            stack.setTag(new CompoundNBT());
        }
        stack.getTag().putInt("scope", this.modeInt(stack) == 0 ? 1 : 0);
    }

    public int modeInt(ItemStack stack){
        if (stack.getTag() == null){
            return 0;
        }
        return stack.getTag().getInt("scope");
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
    public void appendHoverText(ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> p_77624_3_, ITooltipFlag p_77624_4_) {
        TaitaiNeedsMethod.nowDamages(p_77624_1_, p_77624_3_);
    }
}
