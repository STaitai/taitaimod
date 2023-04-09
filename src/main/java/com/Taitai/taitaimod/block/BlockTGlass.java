package com.Taitai.taitaimod.block;

import net.minecraft.block.GlassBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class BlockTGlass extends GlassBlock {

    public BlockTGlass() {
        super(Properties
                .of(Material.GLASS)
                .requiresCorrectToolForDrops()
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(-1)
                .sound(SoundType.GLASS)
                .strength(2F,150F)
                .noOcclusion());

        this.setRegistryName("tglass");

    }

}
