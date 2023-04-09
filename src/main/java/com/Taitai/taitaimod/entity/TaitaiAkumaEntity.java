package com.Taitai.taitaimod.entity;

import com.Taitai.taitaimod.regi.TaitaiModBlocks;
import com.Taitai.taitaimod.regi.TaitaiModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraft.item.MerchantOffers;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.*;
import net.minecraft.world.server.ServerBossInfo;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

public class TaitaiAkumaEntity extends AbstractVillagerEntity {

    private int attackTimer;
    private int lastRestock;

    public TaitaiAkumaEntity(EntityType<? extends TaitaiAkumaEntity> p_i50185_1_, World p_i50185_2_) {
        super(p_i50185_1_, p_i50185_2_);
    }

    private final ServerBossInfo bossInfo = new ServerBossInfo(this.getDisplayName(), BossInfo.Color.PINK, BossInfo.Overlay.PROGRESS);


    public static AttributeModifierMap.MutableAttribute registerAttribute(){
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 30000)
                .add(Attributes.FOLLOW_RANGE, 64)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 50)
                .add(Attributes.ATTACK_SPEED, 4)
                .add(Attributes.ATTACK_KNOCKBACK, 5);
    }

    protected void registerGoals(){
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new LookAtCustomerGoal(this));
        this.goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(3, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(0, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.0D, true));
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, MonsterEntity.class, false));
    }

    public boolean checkSpawnObstruction(IWorldReader world){
        return false;
    }

    @Override
    public void aiStep(){
        super.aiStep();
        if (this.attackTimer > 0){
            --attackTimer;
        }

        if (this.lastRestock > 0){
            --lastRestock;
        }

        if (this.lastRestock == 0){
            this.restock();
        }
    }

    @Override
    public boolean doHurtTarget(Entity p_70652_1_) {
        this.level.broadcastEntityEvent(this, (byte) 4);
        boolean flag = p_70652_1_.hurt(DamageSource.mobAttack(this), 10F);
        return flag;
    }

    public void restock(){
        for (MerchantOffer merchantOffer : this.getOffers()){
            merchantOffer.resetUses();
        }
        this.lastRestock = 2000;
        this.addEffect(new EffectInstance(Effects.DOLPHINS_GRACE, 100, 0));
    }

    @Override
    public void handleEntityEvent(byte p_70103_1_) {
        if (p_70103_1_ == 4){
            this.attackTimer = 10;
        }else {
            super.handleEntityEvent(p_70103_1_);
        }
    }

    public int getAttackTimer() {
        return this.attackTimer;
    }

    public ActionResultType mobInteract(PlayerEntity p_230254_1_, Hand p_230254_2_) {
        ItemStack itemstack = p_230254_1_.getItemInHand(p_230254_2_);
        if (itemstack.getItem() != Items.VILLAGER_SPAWN_EGG && this.isAlive() && !this.isTrading() && !this.isBaby()) {
            if (p_230254_2_ == Hand.MAIN_HAND) {
                p_230254_1_.awardStat(Stats.TALKED_TO_VILLAGER);
            }

            if (this.getOffers().isEmpty()) {
                return ActionResultType.sidedSuccess(this.level.isClientSide);
            } else {
                if (!this.level.isClientSide) {
                    this.setTradingPlayer(p_230254_1_);
                    this.openTradingScreen(p_230254_1_, this.getDisplayName(), 1);
                }

                return ActionResultType.sidedSuccess(this.level.isClientSide);
            }
        } else {
            return super.mobInteract(p_230254_1_, p_230254_2_);
        }
    }

    @Override
    protected void rewardTradeXp(MerchantOffer p_213713_1_) {
        this.lastRestock = 400;
        if (p_213713_1_.shouldRewardExp()) {
            int i = 10 + this.random.nextInt(6);
            this.level.addFreshEntity(new ExperienceOrbEntity(this.level, this.getX(), this.getY() + 0.5D, this.getZ(), i));
        }

    }

    @Override
    protected void updateTrades() {
        VillagerTrades.ITrade[] trades1 = TaitaiAkumaTrades.TAITAI_TRADES.get(1);
        VillagerTrades.ITrade[] trades2 = TaitaiAkumaTrades.TAITAI_TRADES.get(2);
        MerchantOffers merchantOffers = this.getOffers();
        this.addOffersFromItemListings(merchantOffers, trades1, 5);
        this.addOffersFromItemListings(merchantOffers, trades2, 7);
    }

    public void notifyTradeUpdated(ItemStack p_110297_1_) {
        if (!this.level.isClientSide && this.ambientSoundTime > -this.getAmbientSoundInterval() + 20) {
            this.ambientSoundTime = -this.getAmbientSoundInterval();
            this.playSound(SoundEvents.ENDERMAN_SCREAM, 1F, 1.5F);
            PlayerEntity player = Minecraft.getInstance().player;
            player.displayClientMessage(new StringTextComponent("Thank You"), false);
        }

    }

    @Override
    public boolean canRestock() {
        return true;
    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        return null;
    }

    public static boolean canSpawn(EntityType<TaitaiAkumaEntity> type, IWorld world, SpawnReason reason, BlockPos pos, Random random){
        return world.getBlockState(pos.below()).getBlock() == TaitaiModBlocks.TBLOCK && world.getRawBrightness(pos, 0) > 8;
    }

    /**下三つがHPを表示するメソッド*/
    @Override
    protected void customServerAiStep(){
        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
    }
    @Override
    public void startSeenByPlayer(ServerPlayerEntity p_184178_1_) {
        super.startSeenByPlayer(p_184178_1_);
        this.bossInfo.addPlayer(p_184178_1_);
    }
    @Override
    public void stopSeenByPlayer(ServerPlayerEntity p_184203_1_) {
        super.stopSeenByPlayer(p_184203_1_);
        this.bossInfo.removePlayer(p_184203_1_);
    }
}
