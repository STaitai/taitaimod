package com.Taitai.taitaimod.item;

import com.Taitai.taitaimod.main.TaitaiMod;
import net.minecraft.item.Item;

public class ItemSTAmmo extends Item {

    public ItemSTAmmo() {
        super(new Properties().tab(TaitaiMod.TAITAIMOD_TAB).fireResistant());
        this.setRegistryName("stammo");
    }

}
