package com.Taitai.taitaimod.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SSpawnObjectPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class TaitaiTnt2Entity extends Entity {


    private static final DataParameter<Integer> DATA_FUSE_ID = EntityDataManager.defineId(TaitaiTnt2Entity.class, DataSerializers.INT);
    @Nullable
    private LivingEntity owner;
    private int life = 80;

    public TaitaiTnt2Entity(EntityType<? extends TaitaiTnt2Entity> p_i50216_1_, World p_i50216_2_) {
        super(p_i50216_1_, p_i50216_2_);
        this.blocksBuilding = true;
    }

    public TaitaiTnt2Entity(World p_i1730_1_, double p_i1730_2_, double p_i1730_4_, double p_i1730_6_, @Nullable LivingEntity p_i1730_8_) {
        this(TaitaiEntityTypes.Taitai_Tnt2.get(), p_i1730_1_);
        this.setPos(p_i1730_2_, p_i1730_4_, p_i1730_6_);
        double d0 = p_i1730_1_.random.nextDouble() * (double)((float)Math.PI * 2F);
        this.setDeltaMovement(-Math.sin(d0) * 0.02D, (double)0.2F, -Math.cos(d0) * 0.02D);
        this.setFuse(20);
        this.xo = p_i1730_2_;
        this.yo = p_i1730_4_;
        this.zo = p_i1730_6_;
        this.owner = p_i1730_8_;
    }

    public void defineSynchedData() {
        this.entityData.define(DATA_FUSE_ID, 80);
    }

    public boolean isMovementNoisy() {
        return false;
    }

    public boolean isPickable() {
        return !this.removed;
    }

    public void tick() {
        if (!this.isNoGravity()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.04D, 0.0D));
        }

        this.move(MoverType.SELF, this.getDeltaMovement());
        this.setDeltaMovement(this.getDeltaMovement().scale(0.98D));
        if (this.onGround) {
            this.setDeltaMovement(this.getDeltaMovement().multiply(0.7D, -0.5D, 0.7D));
        }

        --this.life;
        if (this.life <= 0) {
            this.remove();
            if (!this.level.isClientSide) {
                this.explode();
            }
        } else {
            this.updateInWaterStateAndDoFluidPushing();
            if (this.level.isClientSide) {
                this.level.addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.5D, this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }

    }

    public void explode() {
        float f = 0F;
        this.level.explode(this, this.getX(), this.getY(0), this.getZ(), 0F, Explosion.Mode.NONE);
        PlayerEntity player = Minecraft.getInstance().player;
        player.displayClientMessage(new StringTextComponent("hello"), false);
    }

    public void addAdditionalSaveData(CompoundNBT p_213281_1_) {
        p_213281_1_.putShort("Fuse", (short)this.getLife());
    }

    public void readAdditionalSaveData(CompoundNBT p_70037_1_) {
        this.setFuse(p_70037_1_.getShort("Fuse"));
    }

    @Nullable
    public LivingEntity getOwner() {
        return this.owner;
    }

    protected float getEyeHeight(Pose p_213316_1_, EntitySize p_213316_2_) {
        return 0.15F;
    }

    public void setFuse(int p_184534_1_) {
        this.entityData.set(DATA_FUSE_ID, p_184534_1_);
        this.life = p_184534_1_;
    }

    public void onSyncedDataUpdated(DataParameter<?> p_184206_1_) {
        if (DATA_FUSE_ID.equals(p_184206_1_)) {
            this.life = this.getFuse();
        }

    }

    public int getFuse() {
        return this.entityData.get(DATA_FUSE_ID);
    }

    public int getLife() {
        return this.life;
    }

    public IPacket<?> getAddEntityPacket() {
        return new SSpawnObjectPacket(this);
    }
}
