package com.Taitai.taitaimod.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockTBlock extends Block {
    public BlockTBlock() {
        super(Properties
                .of(Material.METAL)
                .requiresCorrectToolForDrops()
                .harvestLevel(3)
                .sound(SoundType.NETHER_BRICKS)
                .strength(5F,150F));

        this.setRegistryName("tblock");

    }
}
