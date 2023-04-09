package com.Taitai.taitaimod.entity;

import com.Taitai.taitaimod.regi.TaitaiModItems;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.monster.VexEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SChangeGameStatePacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.EnumSet;
import java.util.List;

public class STAmmoEntity extends AbstractArrowEntity {

    private double baseDamage = 1.5D;
    private int knockback;
    private SoundEvent soundEvent = SoundEvents.IRON_GOLEM_STEP;
    private List<Entity> piercedAndKilledEntities;
    private ItemStack shootStack = new ItemStack(TaitaiModItems.STAMMO);
    private int ticksInGround;

    public STAmmoEntity(EntityType<? extends STAmmoEntity> p_i48546_1_, World p_i48546_2_) {
        super(p_i48546_1_, p_i48546_2_);
    }

    public STAmmoEntity(World world, LivingEntity entity, ItemStack stack){
        super(TaitaiEntityTypes.STAmmo.get(), entity, world);
        this.shootStack = stack;
    }

    @Override
    protected void onHitEntity(EntityRayTraceResult p_213868_1_) {
        Entity entity = p_213868_1_.getEntity();

        if (entity.getType() == EntityType.ENDER_DRAGON){
            this.setBaseDamage(baseDamage * 2);
        }

        float f = (float)this.getDeltaMovement().length();
        int i = MathHelper.ceil(MathHelper.clamp((double)f * this.baseDamage, 0.0D, 2.147483647E9D));

        Entity entity1 = this.getOwner();
        DamageSource damagesource;
        if (entity1 == null) {
            damagesource = DamageSource.arrow(this, this);
        } else {
            damagesource = DamageSource.arrow(this, entity1);
            if (entity1 instanceof LivingEntity) {
                ((LivingEntity)entity1).setLastHurtMob(entity);
            }
        }

        int k = entity.getRemainingFireTicks();

        if (entity.hurt(damagesource, (float)i)) {

            if (entity instanceof LivingEntity) {
                LivingEntity livingentity = (LivingEntity)entity;
                if (!this.level.isClientSide && this.getPierceLevel() <= 0) {
                    livingentity.setArrowCount(livingentity.getArrowCount() + 1);
                }

                if (this.knockback > 0) {
                    Vector3d vector3d = this.getDeltaMovement().multiply(1.0D, 0.0D, 1.0D).normalize().scale((double)this.knockback * 0.6D);
                    if (vector3d.lengthSqr() > 0.0D) {
                        livingentity.push(vector3d.x, 0.1D, vector3d.z);
                    }
                }

                if (!this.level.isClientSide && entity1 instanceof LivingEntity) {
                    EnchantmentHelper.doPostHurtEffects(livingentity, entity1);
                    EnchantmentHelper.doPostDamageEffects((LivingEntity)entity1, livingentity);
                }

                this.doPostHurtEffects(livingentity);
                if (entity1 != null && livingentity != entity1 && livingentity instanceof PlayerEntity && entity1 instanceof ServerPlayerEntity && !this.isSilent()) {
                    ((ServerPlayerEntity)entity1).connection.send(new SChangeGameStatePacket(SChangeGameStatePacket.ARROW_HIT_PLAYER, 0.0F));
                }

                if (!entity.isAlive() && this.piercedAndKilledEntities != null) {
                    this.piercedAndKilledEntities.add(livingentity);
                }
            }

            this.playSound(this.soundEvent, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
            if (this.getPierceLevel() <= 0) {
                this.remove();
            }
        } else {
            entity.setRemainingFireTicks(k);
            this.setDeltaMovement(this.getDeltaMovement().scale(-0.1D));
            this.yRot += 180.0F;
            this.yRotO += 180.0F;
            if (!this.level.isClientSide && this.getDeltaMovement().lengthSqr() < 1.0E-7D) {
                if (this.pickup == AbstractArrowEntity.PickupStatus.ALLOWED) {
                    this.spawnAtLocation(this.getPickupItem(), 0.1F);
                }

                this.remove();
            }
        }

    }

    @Override
    protected void doPostHurtEffects(LivingEntity p_184548_1_) {
        PlayerEntity player = Minecraft.getInstance().player;
        Vector3d v3 = player.getViewVector(1.0F);
        if (p_184548_1_.getType() == EntityType.ENDERMAN){
            p_184548_1_.randomTeleport(player.getX() + v3.x*3, player.getY(), player.getZ() + v3.z*3, true);
            p_184548_1_.hurt(DamageSource.playerAttack(player),40);
        }

        if (!level.isClientSide && p_184548_1_.getType() != EntityType.END_CRYSTAL){
            PlayerEntity p_77659_2_ = Minecraft.getInstance().player;
            level.playSound(null, p_77659_2_.getX(), p_77659_2_.getY(), p_77659_2_.getZ(), SoundEvents.ARROW_HIT_PLAYER, SoundCategory.PLAYERS, 1.0F, 1.0F);
        }
    }

    @Override
    public void setBaseDamage(double p_70239_1_) {
        this.baseDamage = p_70239_1_;
    }

    @Override
    public double getBaseDamage() {
        return this.baseDamage;
    }

    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void onHitBlock(BlockRayTraceResult result) {
        BlockState state = this.level.getBlockState(result.getBlockPos());
        state.onProjectileHit(this.level, state, result, this);
        Vector3d vector3d = result.getLocation().subtract(this.getX(), this.getY(), this.getZ());
        this.setDeltaMovement(vector3d);
        Vector3d vector3d1 = vector3d.normalize().scale((double)0.05F);
        this.setPosRaw(this.getX() - vector3d1.x, this.getY() - vector3d1.y, this.getZ() - vector3d1.z);
        this.playSound(soundEvent, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
        this.inGround = true;
        this.shakeTime = 0;
        this.setPierceLevel((byte)0);
    }

    @Override
    protected ItemStack getPickupItem() {
        return shootStack;
    }

    public void tickDespawn() {
        ++this.ticksInGround;
        if (this.ticksInGround >= 60) {
            this.remove();
        }
    }
}
