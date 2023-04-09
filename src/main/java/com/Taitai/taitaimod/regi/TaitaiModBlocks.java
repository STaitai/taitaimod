package com.Taitai.taitaimod.regi;

import com.Taitai.taitaimod.block.*;
import com.Taitai.taitaimod.main.TaitaiMod;
import com.google.common.base.Preconditions;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder("taitaimod")
public class TaitaiModBlocks {


public static final BlockTBlock TBLOCK = null;
public static final BlockTGlass TGLASS = null;
public static final BlockTGlass2 TGLASS2 = null;
public static final BlockTaitaiTnt TAITAI_TNT = null;
public static final BlockSwitch SWITCH = null;
public static final BlockTaitaiGlowstone TAITAI_GLOW_STONE = null;
public static final BlockTaitaiCraftingTable TAITAI_CRAFTING_TABLE = null;
public static final BlockFigure FIGURE = null;
public static final BlockPut PUT = null;
public static final BlockNetherGate NETHER_GATE = null;
public static final BlockEndGate END_GATE = null;


    @Mod.EventBusSubscriber(modid = "taitaimod",bus = Bus.MOD)
    public static class Register{


        @SubscribeEvent
        public static void registerBlock(final RegistryEvent.Register<Block> event){
            final Block[] blocks = {



                    new BlockTBlock(),
                    new BlockTGlass(),
                    new BlockTGlass2(),
                    new BlockSwitch(),
                    new BlockTaitaiCraftingTable(),
                    new BlockFigure(),
                    new BlockPut(),
                    new BlockNetherGate(),
                    new BlockEndGate(),
                    new BlockTaitaiTnt(AbstractBlock.Properties.of(Material.EXPLOSIVE).instabreak().sound(SoundType.GRASS)),
                    new BlockTaitaiGlowstone(AbstractBlock.Properties.of(Material.GLASS, MaterialColor.SAND).strength(0.3F).sound(SoundType.GLASS).lightLevel((p_235464_0_) -> {
                        return 100;}).sound(SoundType.WOOD), ParticleTypes.FLAME),
            };

            event.getRegistry().registerAll(blocks);

            for (Block block : blocks){
                if (block instanceof BlockTGlass || block instanceof BlockTGlass2 || block instanceof  BlockFigure){
                    RenderTypeLookup.setRenderLayer(block, RenderType.cutout());
                }
            }

        }

        @SubscribeEvent
        public static void registerBlockItem(final RegistryEvent.Register<Item> event){
            final BlockItem[] items = {


                    new BlockItem(TBLOCK,new Item.Properties().tab(TaitaiMod.TAITAIMOD_TAB).fireResistant()),
                    new BlockItem(TGLASS,new Item.Properties().tab(TaitaiMod.TAITAIMOD_TAB).fireResistant()),
                    new BlockItem(TGLASS2, new Item.Properties().tab(TaitaiMod.TAITAIMOD_TAB).fireResistant()),
                    new BlockItem(TAITAI_TNT, new Item.Properties().tab(TaitaiMod.TAITAIMOD_TAB).fireResistant()),
                    new BlockItem(SWITCH, new Item.Properties().tab(TaitaiMod.TAITAIMOD_TAB).fireResistant()),
                    new BlockItem(TAITAI_GLOW_STONE, new Item.Properties().tab(TaitaiMod.TAITAIMOD_TAB).fireResistant()),
                    new BlockItem(TAITAI_CRAFTING_TABLE, new Item.Properties().tab(TaitaiMod.TAITAIMOD_TAB).fireResistant()),
                    new BlockItem(FIGURE, new Item.Properties().tab(TaitaiMod.TAITAIMOD_TAB).fireResistant()),
                    new BlockItem(PUT, new Item.Properties().tab(TaitaiMod.TAITAIMOD_TAB).fireResistant()),
                    new BlockItem(NETHER_GATE, new Item.Properties().tab(TaitaiMod.TAITAIMOD_TAB).fireResistant()),
                    new BlockItem(END_GATE, new Item.Properties().tab(TaitaiMod.TAITAIMOD_TAB).fireResistant()),

            };
                    for(BlockItem item : items){
                        final Block block = item.getBlock();
                        final ResourceLocation location = Preconditions.checkNotNull(block.getRegistryName());
                        event.getRegistry().register(item.setRegistryName(location));
                    }
        }
    }
}
