package com.Taitai.taitaimod.regi;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class TaitaiModTab extends ItemGroup{

    public TaitaiModTab() {
        super("taitaimod");
    }

    @Override
    public ItemStack makeIcon() {
        ItemStack itemstack = new ItemStack(TaitaiModItems.STGUN);
        return itemstack;
    }
}
