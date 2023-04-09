package com.Taitai.taitaimod.entity;

import com.Taitai.taitaimod.regi.TaitaiModBlocks;
import com.Taitai.taitaimod.regi.TaitaiModItems;
import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraft.util.IItemProvider;

import java.util.Random;

public class TaitaiAkumaTrades extends VillagerTrades {

    public static final Int2ObjectMap<VillagerTrades.ITrade[]> TAITAI_TRADES = toIntMap(ImmutableMap
            .of(1, new ITrade[]{

                    new ItemsForEmeraldsTrade(TaitaiModItems.TNGOT, 5, 1, 64, 10),
                    new EmeraldForItemsTrade(Items.APPLE, 2, 1, 32, 10),
                    new ItemsForEmeraldsTrade(TaitaiModBlocks.TGLASS, 10, 1, 64, 10),
                    new ItemsForEmeraldsTrade(TaitaiModItems.TAMMO, 2, 1, 128, 10),
                    new ItemsForEmeraldsTrade(TaitaiModItems.STAMMO,2 , 1, 128, 10)

            },2, new ITrade[]{

                    new EnchantedItemForEmeraldsTrade(TaitaiModItems.TSWORD, 30, 16, 30,0),
                    new EnchantedItemForEmeraldsTrade(TaitaiModItems.TGUN, 30, 16, 30,0),
                    new EnchantedItemForEmeraldsTrade(TaitaiModItems.TAITAI_HELMETHELMET, 30, 16, 30,0),
                    new EnchantedItemForEmeraldsTrade(TaitaiModItems.TAITAI_CHESTPLATE, 30, 16, 30,0),
                    new EnchantedItemForEmeraldsTrade(TaitaiModItems.TAITAI_LEGGINGS, 30, 16, 30,0),
                    new EnchantedItemForEmeraldsTrade(TaitaiModItems.TAITAI_BOOTS, 30, 16, 30,0),
                    new EnchantedItemForEmeraldsTrade(TaitaiModItems.STGUN, 30, 16, 30,0)
            }));

    private static Int2ObjectMap<ITrade[]> toIntMap(ImmutableMap<Integer, ITrade[]> p_221238_0_) {
        return new Int2ObjectOpenHashMap<>(p_221238_0_);
    }

    static class EmeraldForItemsTrade implements VillagerTrades.ITrade {
        private final Item item;
        private final int emeraldCount;
        private final int cost;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public EmeraldForItemsTrade(IItemProvider p_i50539_1_, int emeraldCount, int p_i50539_2_, int p_i50539_3_, int p_i50539_4_) {
            this.item = p_i50539_1_.asItem();
            this.emeraldCount = emeraldCount;
            this.cost = p_i50539_2_;
            this.maxUses = p_i50539_3_;
            this.villagerXp = p_i50539_4_;
            this.priceMultiplier = 0F;
        }

        public MerchantOffer getOffer(Entity p_221182_1_, Random p_221182_2_) {
            ItemStack itemstack = new ItemStack(this.item, this.cost);
            return new MerchantOffer(itemstack, new ItemStack(Items.EMERALD, this.emeraldCount), this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }

    static class EnchantedItemForEmeraldsTrade implements VillagerTrades.ITrade {
        private final ItemStack itemStack;
        private final int baseEmeraldCost;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public EnchantedItemForEmeraldsTrade(Item p_i50536_1_, int p_i50536_2_, int p_i50536_3_, int p_i50536_4_, float p_i50536_5_) {
            this.itemStack = new ItemStack(p_i50536_1_);
            this.baseEmeraldCost = p_i50536_2_;
            this.maxUses = p_i50536_3_;
            this.villagerXp = p_i50536_4_;
            this.priceMultiplier = p_i50536_5_;
        }

        public MerchantOffer getOffer(Entity p_221182_1_, Random p_221182_2_) {
            ItemStack stack = new ItemStack(itemStack.getItem());
            itemStack.enchant(Enchantments.UNBREAKING, 3);
            itemStack.enchant(Enchantments.MENDING, 1);
            return new MerchantOffer(new ItemStack(Items.EMERALD, this.baseEmeraldCost), itemStack, this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }

    static class ItemsForEmeraldsTrade implements VillagerTrades.ITrade {
        private final ItemStack itemStack;
        private final int emeraldCost;
        private final int numberOfItems;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public ItemsForEmeraldsTrade(Block p_i50528_1_, int p_i50528_2_, int p_i50528_3_, int p_i50528_4_, int p_i50528_5_) {
            this(new ItemStack(p_i50528_1_), p_i50528_2_, p_i50528_3_, p_i50528_4_, p_i50528_5_);
        }

        public ItemsForEmeraldsTrade(Item p_i50529_1_, int p_i50529_2_, int p_i50529_3_, int p_i50529_4_) {
            this(new ItemStack(p_i50529_1_), p_i50529_2_, p_i50529_3_, 12, p_i50529_4_);
        }

        public ItemsForEmeraldsTrade(Item p_i50530_1_, int p_i50530_2_, int p_i50530_3_, int p_i50530_4_, int p_i50530_5_) {
            this(new ItemStack(p_i50530_1_), p_i50530_2_, p_i50530_3_, p_i50530_4_, p_i50530_5_);
        }

        public ItemsForEmeraldsTrade(ItemStack p_i50531_1_, int p_i50531_2_, int p_i50531_3_, int p_i50531_4_, int p_i50531_5_) {
            this(p_i50531_1_, p_i50531_2_, p_i50531_3_, p_i50531_4_, p_i50531_5_, 0F);
        }

        public ItemsForEmeraldsTrade(ItemStack p_i50532_1_, int p_i50532_2_, int p_i50532_3_, int p_i50532_4_, int p_i50532_5_, float p_i50532_6_) {
            this.itemStack = p_i50532_1_;
            this.emeraldCost = p_i50532_2_;
            this.numberOfItems = p_i50532_3_;
            this.maxUses = p_i50532_4_;
            this.villagerXp = p_i50532_5_;
            this.priceMultiplier = p_i50532_6_;
        }

        public MerchantOffer getOffer(Entity p_221182_1_, Random p_221182_2_) {
            return new MerchantOffer(new ItemStack(Items.EMERALD, this.emeraldCost), new ItemStack(this.itemStack.getItem(), this.numberOfItems), this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }

}
