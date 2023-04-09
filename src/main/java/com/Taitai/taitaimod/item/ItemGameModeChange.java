package com.Taitai.taitaimod.item;

import com.Taitai.taitaimod.main.TaitaiMod;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.GameType;
import net.minecraft.world.World;

public class ItemGameModeChange extends Item {
    public ItemGameModeChange() {
        super(new Properties().tab(TaitaiMod.TAITAIMOD_TAB).fireResistant().stacksTo(1));
        this.setRegistryName("game_mode");
    }

    @Override
    public ActionResult<ItemStack> use(World p_77659_1_, PlayerEntity p_77659_2_, Hand p_77659_3_){
        ItemStack stack = p_77659_2_.getItemInHand(p_77659_3_);
        GameType game = Minecraft.getInstance().gameMode.getPlayerMode();

        if (game == GameType.CREATIVE){
            p_77659_2_.setGameMode(GameType.SURVIVAL);
            p_77659_1_.playSound(null, p_77659_2_.getX(), p_77659_2_.getY(), p_77659_2_.getZ(), SoundEvents.FIREWORK_ROCKET_BLAST, SoundCategory.PLAYERS, 1.0F, 1.0F);
            p_77659_2_.displayClientMessage(new StringTextComponent("SURVIVAL"), true);
        }

        if (game == GameType.ADVENTURE){
            p_77659_2_.setGameMode(GameType.CREATIVE);
            p_77659_1_.playSound(null, p_77659_2_.getX(), p_77659_2_.getY(), p_77659_2_.getZ(), SoundEvents.FIREWORK_ROCKET_BLAST, SoundCategory.PLAYERS, 1.0F, 1.0F);
            p_77659_2_.displayClientMessage(new StringTextComponent("CREATIVE"), true);
        }

        if (game == GameType.SURVIVAL){
            p_77659_2_.setGameMode(GameType.CREATIVE);
            p_77659_1_.playSound(null, p_77659_2_.getX(), p_77659_2_.getY(), p_77659_2_.getZ(), SoundEvents.FIREWORK_ROCKET_BLAST, SoundCategory.PLAYERS, 1.0F, 1.0F);
            p_77659_2_.displayClientMessage(new StringTextComponent("CREATIVE"), true);
        }

        if (p_77659_2_.isShiftKeyDown()){
            p_77659_2_.setGameMode(GameType.ADVENTURE);
            p_77659_1_.playSound(null, p_77659_2_.getX(), p_77659_2_.getY(), p_77659_2_.getZ(), SoundEvents.FIREWORK_ROCKET_BLAST, SoundCategory.PLAYERS, 1.0F, 1.0F);
            p_77659_2_.displayClientMessage(new StringTextComponent("ADVENTURE"), true);
        }

        if (p_77659_2_.isShiftKeyDown() && game == GameType.ADVENTURE){
            p_77659_2_.setGameMode(GameType.CREATIVE);
            p_77659_1_.playSound(null, p_77659_2_.getX(), p_77659_2_.getY(), p_77659_2_.getZ(), SoundEvents.FIREWORK_ROCKET_BLAST, SoundCategory.PLAYERS, 1.0F, 1.0F);
            p_77659_2_.displayClientMessage(new StringTextComponent("CREATIVE"), true);
        }

        return ActionResult.consume(stack);
    }
}
