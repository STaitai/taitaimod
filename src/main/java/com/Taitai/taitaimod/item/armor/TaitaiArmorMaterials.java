package com.Taitai.taitaimod.item.armor;

import com.Taitai.taitaimod.regi.TaitaiModItems;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public enum TaitaiArmorMaterials implements IArmorMaterial {

    TAITAI("taitaimod:taitai", -1, new int[]{6, 12, 16, 6}, 15, SoundEvents.ARMOR_EQUIP_NETHERITE, 6.0F, 0.2F, () -> {return Ingredient.of(TaitaiModItems.TNGOT);}),
    FLY("taitaimod:fly", 5000, new int[] { 0, 0, 0, 3 }, 15, SoundEvents.ARMOR_EQUIP_DIAMOND, 3.0F, 0.0F, () -> {return Ingredient.of(Items.PHANTOM_MEMBRANE);});

    public static final int[] MAX_DAMAGE_ARRAY = new int[]{1,1,1,1};
    private final int maxDamageFactor,enchantability;
    private final int[] damageReductionAmountArray;
    private final SoundEvent soundEvent;
    private final LazyValue<Ingredient> repairMaterial;
    private final String name;
    private final float toughness,knockbackResistance;

    private TaitaiArmorMaterials(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, SoundEvent soundEvent, float toughness, float knockbackResistance, Supplier<Ingredient> repairMaterial){
        this.name = name;
        this.maxDamageFactor = maxDamageFactor;
        this.damageReductionAmountArray = damageReductionAmountArray;
        this.enchantability = enchantability;
        this.soundEvent = soundEvent;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairMaterial = new LazyValue<>(repairMaterial);
    }

    @Override
    public int getDurabilityForSlot(EquipmentSlotType p_200896_1_) {
        return MAX_DAMAGE_ARRAY[p_200896_1_.getIndex()] * this.maxDamageFactor;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlotType p_200902_1_) {
        return this.damageReductionAmountArray[p_200902_1_.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.soundEvent;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairMaterial.get();
    }

    @OnlyIn(Dist.CLIENT)
    public String getName() {
        return this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }


}
