package com.Taitai.taitaimod.item;

import com.Taitai.taitaimod.main.TaitaiMod;
import net.minecraft.item.Item;

public class ItemTAmmo extends Item {
    public ItemTAmmo() {
        super(new Properties().tab(TaitaiMod.TAITAIMOD_TAB).fireResistant());
        this.setRegistryName("tammo");
    }
}
