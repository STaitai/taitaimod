package com.Taitai.taitaimod.block;

import com.Taitai.taitaimod.regi.TaitaiModBlocks;
import com.Taitai.taitaimod.regi.TaitaiModItems;
import com.Taitai.taitaimod.regi.TaitaiNeedsMethod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.command.arguments.ItemInput;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.HangingEntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class BlockPut extends Block {


    public BlockPut() {
        super(Properties.of(Material.STONE).
                strength(-1.0F, 3600000.0F)
                .noDrops());
        this.setRegistryName("put");
    }

    @Override
    public void setPlacedBy(World p_180633_1_, BlockPos p_180633_2_, BlockState p_180633_3_, @Nullable LivingEntity p_180633_4_, ItemStack p_180633_5_) {

        PlayerEntity player = (PlayerEntity)p_180633_4_;
        Random random = new Random();
        SoundEvent[] sounds = {SoundEvents.GENERIC_EXPLODE, SoundEvents.GLASS_BREAK, SoundEvents.VILLAGER_DEATH, SoundEvents.ANVIL_USE, SoundEvents.ENDERMAN_DEATH };
        SoundEvent sound = sounds[random.nextInt(5)];

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (p_180633_1_.getBlockState(p_180633_2_).getBlock() == TaitaiModBlocks.PUT) {
                    p_180633_1_.playSound(null, p_180633_2_.getX(), p_180633_2_.getY(), p_180633_2_.getZ(), sound, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    p_180633_1_.destroyBlock(p_180633_2_, false);
                    player.addItem(new ItemStack(TaitaiModBlocks.PUT.asItem()));
                    if (!player.inventory.add(new ItemStack(TaitaiModBlocks.PUT.asItem()))){
                        player.drop(new ItemStack(TaitaiModBlocks.PUT.asItem()), false);
                    }
                }
            }
        }, 10000);
    }
}
