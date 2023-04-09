package com.Taitai.taitaimod.item;

import com.Taitai.taitaimod.regi.TaitaiNeedsMethod;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ItemLightClick extends Item {

    public static int mode = 1;

    public ItemLightClick() {
        super(new Properties().tab(null).fireResistant());
        this.setRegistryName("light_click");
    }

    @Override
    public ActionResultType useOn(ItemUseContext p_195939_1_) {
        TaitaiNeedsMethod.changedRandomBlock(p_195939_1_.getLevel(), p_195939_1_.getClickedPos());
        return super.useOn(p_195939_1_);
    }
}