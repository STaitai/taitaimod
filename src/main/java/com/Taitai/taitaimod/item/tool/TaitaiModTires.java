package com.Taitai.taitaimod.item.tool;

import com.Taitai.taitaimod.regi.TaitaiModItems;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

import java.util.function.Supplier;

public enum TaitaiModTires implements IItemTier {


    TAITAI(4,5000,9.0F,8F,15,() ->{return Ingredient.of(TaitaiModItems.TNGOT);});


    private final int maxUses,harvestLevel,enchantability;
    private final float efficiency,attackDamage;
    private final LazyValue<Ingredient> repairMaterial;

    private TaitaiModTires(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, Supplier<Ingredient> repairMaterial){
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairMaterial = new LazyValue<>(repairMaterial);
    }

    public int getUses() {return this.maxUses;}
    public float getSpeed() {
        return this.efficiency;
    }

    public float getAttackDamageBonus() {
        return this.attackDamage;
    }

    public int getLevel() {
        return this.harvestLevel;
    }

    public int getEnchantmentValue() {
        return this.enchantability;
    }

    public Ingredient getRepairIngredient() {return this.repairMaterial.get();}

}
