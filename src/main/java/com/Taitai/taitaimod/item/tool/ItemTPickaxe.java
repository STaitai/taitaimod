package com.Taitai.taitaimod.item.tool;

import com.Taitai.taitaimod.entity.TAmmoEntity;
import com.Taitai.taitaimod.main.TaitaiMod;
import com.Taitai.taitaimod.regi.TaitaiModItems;
import com.Taitai.taitaimod.regi.TaitaiNeedsMethod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.loot.LootContext;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.forgespi.Environment;
import org.lwjgl.system.CallbackI;

import javax.annotation.Nullable;
import javax.swing.text.JTextComponent;
import java.awt.event.KeyEvent;
import java.util.List;

public class ItemTPickaxe extends PickaxeItem {

    public ItemTPickaxe() {
        super(TaitaiModTires.TAITAI, 1, -2F, new Properties().tab(TaitaiMod.TAITAIMOD_TAB).fireResistant());
        this.setRegistryName("tpickaxe");
    }

    @Override
    public void appendHoverText(ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> p_77624_3_, ITooltipFlag p_77624_4_) {
        TaitaiNeedsMethod.nowDamages(p_77624_1_, p_77624_3_);
    }

    @Override
    public ActionResultType useOn(ItemUseContext p_195939_1_) {
        World world = p_195939_1_.getLevel();
        ItemStack stack = p_195939_1_.getItemInHand();
        BlockPos pos = p_195939_1_.getClickedPos();
        BlockState blockState = world.getBlockState(pos);
        PlayerEntity player = p_195939_1_.getPlayer();

        if (blockState.getBlock() == Blocks.BEDROCK) {
            world.destroyBlock(pos, false);
            player.drop(new ItemStack(Items.BEDROCK), false);
            TaitaiNeedsMethod.stackHurtAndBreak(stack, p_195939_1_.getPlayer(), 1);

        }else if (blockState.getBlock() == Blocks.IRON_ORE){
            world.destroyBlock(pos, false);
            player.drop(new ItemStack(Items.IRON_INGOT), false);
            TaitaiNeedsMethod.stackHurtAndBreak(stack, p_195939_1_.getPlayer(), 1);

        }else if (blockState.getBlock() == Blocks.GOLD_ORE){
            world.destroyBlock(pos, false);
            player.drop(new ItemStack(Items.GOLD_INGOT), false);
            TaitaiNeedsMethod.stackHurtAndBreak(stack, p_195939_1_.getPlayer(), 1);

        }else {
            world.destroyBlock(pos, true);
            TaitaiNeedsMethod.stackHurtAndBreak(stack, p_195939_1_.getPlayer(), 1);
        }

        return super.useOn(p_195939_1_);
    }
}
