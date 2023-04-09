package com.Taitai.taitaimod.regi;

import com.Taitai.taitaimod.main.TaitaiMod;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.NetherPortalBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.Property;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.Tags;
import org.lwjgl.system.CallbackI;

import java.util.List;
import java.util.Random;

public class TaitaiNeedsMethod {
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
    public static final DirectionProperty FACING = HorizontalBlock.FACING;

    public static void giveRandomOPItem(PlayerEntity player){
        Random random = new Random();

        Item[] armors = { Items.DIAMOND_HELMET, Items.DIAMOND_CHESTPLATE, Items.DIAMOND_LEGGINGS, Items.DIAMOND_BOOTS,
                Items.NETHERITE_HELMET, Items.NETHERITE_CHESTPLATE, Items.NETHERITE_LEGGINGS, Items.NETHERITE_BOOTS, Items.ELYTRA,
                TaitaiModItems.TAITAI_HELMETHELMET, TaitaiModItems.TAITAI_CHESTPLATE, TaitaiModItems.TAITAI_LEGGINGS, TaitaiModItems.TAITAI_BOOTS };
        Item armor = armors[random.nextInt(13)];

        Item[] tools = { Items.DIAMOND_SWORD, Items.DIAMOND_PICKAXE, Items.DIAMOND_SHOVEL, Items.DIAMOND_AXE,
                Items.NETHERITE_SWORD, Items.NETHERITE_PICKAXE, Items.NETHERITE_AXE, Items.NETHERITE_SHOVEL,
                TaitaiModItems.TSWORD, TaitaiModItems.TPICKAXE, TaitaiModItems.TSHOVEL, TaitaiModItems.TAXE };
        Item tool = tools[random.nextInt(12)];

        Item[] gems = { Items.DIAMOND, Items.GOLD_INGOT, Items.NETHERITE_INGOT, Items.IRON_INGOT, Items.EMERALD, TaitaiModItems.TNGOT };
        Item gem = gems[random.nextInt(6)];

        Item[] gemBlocks = { Items.DIAMOND_BLOCK, Items.GOLD_BLOCK, Items.NETHERITE_BLOCK, Items.IRON_BLOCK, Items.EMERALD_BLOCK, TaitaiModBlocks.TBLOCK.asItem() };
        Item gemBlock = gemBlocks[random.nextInt(6)];

        Item[] blocks = { Items.SHULKER_BOX, Items.ANVIL, Items.WITHER_SKELETON_SKULL,
                Items.DRAGON_HEAD, Items.BEACON, Items.CONDUIT, Items.ENDER_CHEST,
                TaitaiModBlocks.PUT.asItem(), TaitaiModBlocks.TGLASS.asItem(), TaitaiModBlocks.TGLASS2.asItem(),
                TaitaiModBlocks.TAITAI_TNT.asItem(), TaitaiModBlocks.FIGURE.asItem() };
        Item block = blocks[random.nextInt(12)];

        Item[] getItems = { Items.TOTEM_OF_UNDYING, Items.ENCHANTED_GOLDEN_APPLE, Items.GOLDEN_APPLE,
                TaitaiModItems.STGUN, TaitaiModItems.TGUN, TaitaiModItems.PT_GUN, TaitaiModItems.TS_GUN,
                TaitaiModItems.TELEPORT_LOT, TaitaiModItems.CREEPER, TaitaiModItems.ENCHANT,
                TaitaiModItems.ENCHANT50, TaitaiModItems.ENCHANT_LOT };
        Item getItem = getItems[random.nextInt(12)];

        Item[] items = { armor, tool, gem, gemBlock, block, getItem};
        Item item = items[random.nextInt(6)];

        ItemStack giveItem = new ItemStack(item);
        player.addItem(giveItem);
        if (!player.inventory.add(giveItem)){
            player.drop(giveItem, false);
        }
    }
    public static void nowDamages(ItemStack stack, List<ITextComponent> list) {
        int damage = stack.getItem().getDamage(stack);
        int maxDamage = stack.getItem().getMaxDamage(stack);
        int nowDamage = maxDamage - damage;
        if (nowDamage > maxDamage / 2 - 1 && Screen.hasShiftDown()){
            list.add(new TranslationTextComponent("\u00A7a" + nowDamage + "\u00A7r" + " / " + maxDamage));
        }
        if (nowDamage < maxDamage / 2 && nowDamage > maxDamage / 10 && Screen.hasShiftDown()){
            list.add(new TranslationTextComponent("\u00A7c" + nowDamage + "\u00A7r" + " / " + maxDamage));
        }
        if (nowDamage < maxDamage / 10 + 1 && Screen.hasShiftDown()){
            list.add(new TranslationTextComponent("\u00A74" + nowDamage + "\u00A7r" + " / " + maxDamage));
        }
    }
    public static void teleport(World wordIn, PlayerEntity playerIn, int teleportAway){
        for (int a = 0; a < 256; a++){
            Vector3d vector3d = playerIn.getViewVector(1);
            BlockPos pos = new BlockPos(playerIn.getX()+vector3d.x*teleportAway,playerIn.getY()+a,playerIn.getZ()+vector3d.z*teleportAway);
            BlockPos headPos = new BlockPos(playerIn.getX()+vector3d.x*teleportAway,playerIn.getY()+a+1,playerIn.getZ()+vector3d.z*teleportAway);
            if (wordIn.getBlockState(pos) == Blocks.AIR.defaultBlockState() && wordIn.getBlockState(headPos) == Blocks.AIR.defaultBlockState()){
                playerIn.setPos(playerIn.getX()+vector3d.x*teleportAway, playerIn.getY()+a, playerIn.getZ()+vector3d.z*teleportAway);
                wordIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
                break;
            }
        }
    }
    public static void stackHurtAndBreak(ItemStack stack, PlayerEntity player, int damage){
        stack.hurtAndBreak(damage, player, (b)->{
            b.broadcastBreakEvent(player.getUsedItemHand());
        });
    }
    public static void setBlock(World world, BlockPos pos, Block block){
        int[] blockPos = { pos.getX(), pos.getY(), pos.getZ()};
        world.setBlock(new BlockPos(blockPos[0], blockPos[1],  blockPos[2]), block.defaultBlockState(), 1);
    }
    public static void changedBlock(World world, BlockPos pos, Block before, Block after){
        if (world.getBlockState(pos).getBlock() == before){
            setBlock(world, pos, after);
        }
    }
    public static void changedRandomBlock(World world, BlockPos pos){

        Random random = new Random();
        Block[] blocks = {

                Blocks.STONE,
                Blocks.GRANITE,
                Blocks.POLISHED_GRANITE,
                Blocks.DIORITE,
                Blocks.POLISHED_DIORITE,
                Blocks.ANDESITE,
                Blocks.POLISHED_ANDESITE,
                Blocks.GRASS_BLOCK,
                Blocks.DIRT,
                Blocks.COARSE_DIRT,
                Blocks.PODZOL,
                Blocks.COBBLESTONE,
                Blocks.OAK_PLANKS,
                Blocks.SPRUCE_PLANKS,
                Blocks.BIRCH_PLANKS,
                Blocks.JUNGLE_PLANKS,
                Blocks.ACACIA_PLANKS,
                Blocks.DARK_OAK_PLANKS,
                Blocks.OAK_SAPLING,
                Blocks.SPRUCE_SAPLING,
                Blocks.BIRCH_SAPLING,
                Blocks.JUNGLE_SAPLING,
                Blocks.ACACIA_SAPLING,
                Blocks.DARK_OAK_SAPLING,
                Blocks.BEDROCK,
                Blocks.WATER,
                Blocks.LAVA,
                Blocks.SAND,
                Blocks.RED_SAND,
                Blocks.GRAVEL,
                Blocks.GOLD_ORE,
                Blocks.IRON_ORE,
                Blocks.COAL_ORE,
                Blocks.NETHER_GOLD_ORE,
                Blocks.OAK_LOG,
                Blocks.SPRUCE_LOG,
                Blocks.BIRCH_LOG,
                Blocks.JUNGLE_LOG,
                Blocks.ACACIA_LOG,
                Blocks.DARK_OAK_LOG,
                Blocks.STRIPPED_SPRUCE_LOG,
                Blocks.STRIPPED_BIRCH_LOG,
                Blocks.STRIPPED_JUNGLE_LOG,
                Blocks.STRIPPED_ACACIA_LOG,
                Blocks.STRIPPED_DARK_OAK_LOG,
                Blocks.STRIPPED_OAK_LOG,
                Blocks.OAK_WOOD,
                Blocks.SPRUCE_WOOD,
                Blocks.BIRCH_WOOD,
                Blocks.JUNGLE_WOOD,
                Blocks.ACACIA_WOOD,
                Blocks.DARK_OAK_WOOD,
                Blocks.STRIPPED_OAK_WOOD,
                Blocks.STRIPPED_SPRUCE_WOOD,
                Blocks.STRIPPED_BIRCH_WOOD,
                Blocks.STRIPPED_JUNGLE_WOOD,
                Blocks.STRIPPED_ACACIA_WOOD,
                Blocks.STRIPPED_DARK_OAK_WOOD,
                Blocks.OAK_LEAVES,
                Blocks.SPRUCE_LEAVES,
                Blocks.BIRCH_LEAVES,
                Blocks.JUNGLE_LEAVES,
                Blocks.ACACIA_LEAVES,
                Blocks.DARK_OAK_LEAVES,
                Blocks.SPONGE,
                Blocks.WET_SPONGE,
                Blocks.GLASS,
                Blocks.LAPIS_ORE,
                Blocks.LAPIS_BLOCK,
                Blocks.DISPENSER,
                Blocks.SANDSTONE,
                Blocks.CHISELED_SANDSTONE,
                Blocks.CUT_SANDSTONE,
                Blocks.NOTE_BLOCK,
                Blocks.WHITE_BED,
                Blocks.ORANGE_BED,
                Blocks.MAGENTA_BED,
                Blocks.LIGHT_BLUE_BED,
                Blocks.YELLOW_BED,
                Blocks.LIME_BED,
                Blocks.PINK_BED,
                Blocks.GRAY_BED,
                Blocks.LIGHT_GRAY_BED,
                Blocks.CYAN_BED,
                Blocks.PURPLE_BED,
                Blocks.BLUE_BED,
                Blocks.BROWN_BED,
                Blocks.GREEN_BED,
                Blocks.RED_BED,
                Blocks.BLACK_BED,
                Blocks.POWERED_RAIL,
                Blocks.DETECTOR_RAIL,
                Blocks.STICKY_PISTON,
                Blocks.COBWEB,
                Blocks.GRASS,
                Blocks.FERN,
                Blocks.DEAD_BUSH,
                Blocks.SEAGRASS,
                Blocks.TALL_SEAGRASS,
                Blocks.PISTON,
                Blocks.PISTON_HEAD,
                Blocks.WHITE_WOOL,
                Blocks.ORANGE_WOOL,
                Blocks.MAGENTA_WOOL,
                Blocks.LIGHT_BLUE_WOOL,
                Blocks.YELLOW_WOOL,
                Blocks.LIME_WOOL,
                Blocks.PINK_WOOL,
                Blocks.GRAY_WOOL,
                Blocks.LIGHT_GRAY_WOOL,
                Blocks.CYAN_WOOL,
                Blocks.PURPLE_WOOL,
                Blocks.BLUE_WOOL,
                Blocks.BROWN_WOOL,
                Blocks.GREEN_WOOL,
                Blocks.RED_WOOL,
                Blocks.BLACK_WOOL,
                Blocks.MOVING_PISTON,
                Blocks.DANDELION,
                Blocks.POPPY,
                Blocks.BLUE_ORCHID,
                Blocks.ALLIUM,
                Blocks.AZURE_BLUET,
                Blocks.RED_TULIP,
                Blocks.ORANGE_TULIP,
                Blocks.WHITE_TULIP,
                Blocks.PINK_TULIP,
                Blocks.OXEYE_DAISY,
                Blocks.CORNFLOWER,
                Blocks.WITHER_ROSE,
                Blocks.LILY_OF_THE_VALLEY,
                Blocks.BROWN_MUSHROOM,
                Blocks.RED_MUSHROOM,
                Blocks.GOLD_BLOCK,
                Blocks.IRON_BLOCK,
                Blocks.BRICKS,
                Blocks.TNT,
                Blocks.BOOKSHELF,
                Blocks.MOSSY_COBBLESTONE,
                Blocks.OBSIDIAN,
                Blocks.TORCH,
                Blocks.WALL_TORCH,
                Blocks.FIRE,
                Blocks.SOUL_FIRE,
                Blocks.SPAWNER,
                Blocks.OAK_STAIRS,
                Blocks.CHEST,
                Blocks.REDSTONE_WIRE,
                Blocks.DIAMOND_ORE,
                Blocks.DIAMOND_BLOCK,
                Blocks.CRAFTING_TABLE,
                Blocks.WHEAT,
                Blocks.FARMLAND,
                Blocks.FURNACE,
                Blocks.OAK_SIGN,
                Blocks.SPRUCE_SIGN,
                Blocks.BIRCH_SIGN,
                Blocks.ACACIA_SIGN,
                Blocks.JUNGLE_SIGN,
                Blocks.DARK_OAK_SIGN,
                Blocks.OAK_DOOR,
                Blocks.LADDER,
                Blocks.RAIL,
                Blocks.COBBLESTONE_STAIRS,
                Blocks.OAK_WALL_SIGN,
                Blocks.SPRUCE_WALL_SIGN,
                Blocks.BIRCH_WALL_SIGN,
                Blocks.ACACIA_WALL_SIGN,
                Blocks.JUNGLE_WALL_SIGN,
                Blocks.DARK_OAK_WALL_SIGN,
                Blocks.LEVER,
                Blocks.STONE_PRESSURE_PLATE,
                Blocks.IRON_DOOR,
                Blocks.OAK_PRESSURE_PLATE,
                Blocks.SPRUCE_PRESSURE_PLATE,
                Blocks.BIRCH_PRESSURE_PLATE,
                Blocks.JUNGLE_PRESSURE_PLATE,
                Blocks.ACACIA_PRESSURE_PLATE,
                Blocks.DARK_OAK_PRESSURE_PLATE,
                Blocks.REDSTONE_ORE,
                Blocks.REDSTONE_TORCH,
                Blocks.REDSTONE_WALL_TORCH,
                Blocks.STONE_BUTTON,
                Blocks.SNOW,
                Blocks.ICE,
                Blocks.SNOW_BLOCK,
                Blocks.CACTUS,
                Blocks.CLAY,
                Blocks.SUGAR_CANE,
                Blocks.JUKEBOX,
                Blocks.OAK_FENCE,
                Blocks.PUMPKIN,
                Blocks.NETHERRACK,
                Blocks.SOUL_SAND,
                Blocks.SOUL_SOIL,
                Blocks.BASALT,
                Blocks.POLISHED_BASALT,
                Blocks.SOUL_TORCH,
                Blocks.SOUL_WALL_TORCH,
                Blocks.GLOWSTONE,
                Blocks.NETHER_PORTAL,
                Blocks.CARVED_PUMPKIN,
                Blocks.JACK_O_LANTERN,
                Blocks.CAKE,
                Blocks.REPEATER,
                Blocks.WHITE_STAINED_GLASS,
                Blocks.ORANGE_STAINED_GLASS,
                Blocks.MAGENTA_STAINED_GLASS,
                Blocks.LIGHT_BLUE_STAINED_GLASS,
                Blocks.YELLOW_STAINED_GLASS,
                Blocks.LIME_STAINED_GLASS,
                Blocks.PINK_STAINED_GLASS,
                Blocks.GRAY_STAINED_GLASS,
                Blocks.LIGHT_GRAY_STAINED_GLASS,
                Blocks.CYAN_STAINED_GLASS,
                Blocks.PURPLE_STAINED_GLASS,
                Blocks.BLUE_STAINED_GLASS,
                Blocks.BROWN_STAINED_GLASS,
                Blocks.GREEN_STAINED_GLASS,
                Blocks.RED_STAINED_GLASS,
                Blocks.BLACK_STAINED_GLASS,
                Blocks.OAK_TRAPDOOR,
                Blocks.SPRUCE_TRAPDOOR,
                Blocks.BIRCH_TRAPDOOR,
                Blocks.JUNGLE_TRAPDOOR,
                Blocks.ACACIA_TRAPDOOR,
                Blocks.DARK_OAK_TRAPDOOR,
                Blocks.STONE_BRICKS,
                Blocks.MOSSY_STONE_BRICKS,
                Blocks.CRACKED_STONE_BRICKS,
                Blocks.CHISELED_STONE_BRICKS,
                Blocks.INFESTED_STONE,
                Blocks.INFESTED_COBBLESTONE,
                Blocks.INFESTED_STONE_BRICKS,
                Blocks.INFESTED_MOSSY_STONE_BRICKS,
                Blocks.INFESTED_CRACKED_STONE_BRICKS,
                Blocks.INFESTED_CHISELED_STONE_BRICKS,
                Blocks.BROWN_MUSHROOM_BLOCK,
                Blocks.RED_MUSHROOM_BLOCK,
                Blocks.MUSHROOM_STEM,
                Blocks.IRON_BARS,
                Blocks.CHAIN,
                Blocks.GLASS_PANE,
                Blocks.MELON,
                Blocks.ATTACHED_PUMPKIN_STEM,
                Blocks.ATTACHED_MELON_STEM,
                Blocks.PUMPKIN_STEM,
                Blocks.MELON_STEM,
                Blocks.VINE,
                Blocks.OAK_FENCE_GATE,
                Blocks.BRICK_STAIRS,
                Blocks.STONE_BRICK_STAIRS,
                Blocks.MYCELIUM,
                Blocks.LILY_PAD,
                Blocks.NETHER_BRICKS,
                Blocks.NETHER_BRICK_FENCE,
                Blocks.NETHER_BRICK_STAIRS,
                Blocks.NETHER_WART,
                Blocks.ENCHANTING_TABLE,
                Blocks.BREWING_STAND,
                Blocks.CAULDRON,
                Blocks.END_PORTAL,
                Blocks.END_PORTAL_FRAME,
                Blocks.END_STONE,
                Blocks.DRAGON_EGG,
                Blocks.REDSTONE_LAMP,
                Blocks.COCOA,
                Blocks.SANDSTONE_STAIRS,
                Blocks.EMERALD_ORE,
                Blocks.ENDER_CHEST,
                Blocks.TRIPWIRE_HOOK,
                Blocks.TRIPWIRE,
                Blocks.EMERALD_BLOCK,
                Blocks.SPRUCE_STAIRS,
                Blocks.BIRCH_STAIRS,
                Blocks.JUNGLE_STAIRS,
                Blocks.COMMAND_BLOCK,
                Blocks.BEACON,
                Blocks.COBBLESTONE_WALL,
                Blocks.MOSSY_COBBLESTONE_WALL,
                Blocks.FLOWER_POT,
                Blocks.POTTED_OAK_SAPLING,
                Blocks.POTTED_SPRUCE_SAPLING,
                Blocks.POTTED_BIRCH_SAPLING,
                Blocks.POTTED_JUNGLE_SAPLING,
                Blocks.POTTED_ACACIA_SAPLING,
                Blocks.POTTED_DARK_OAK_SAPLING,
                Blocks.POTTED_FERN,
                Blocks.POTTED_DANDELION,
                Blocks.POTTED_POPPY,
                Blocks.POTTED_BLUE_ORCHID,
                Blocks.POTTED_ALLIUM,
                Blocks.POTTED_AZURE_BLUET,
                Blocks.POTTED_RED_TULIP,
                Blocks.POTTED_ORANGE_TULIP,
                Blocks.POTTED_WHITE_TULIP,
                Blocks.POTTED_PINK_TULIP,
                Blocks.POTTED_OXEYE_DAISY,
                Blocks.POTTED_CORNFLOWER,
                Blocks.POTTED_LILY_OF_THE_VALLEY,
                Blocks.POTTED_WITHER_ROSE,
                Blocks.POTTED_RED_MUSHROOM,
                Blocks.POTTED_BROWN_MUSHROOM,
                Blocks.POTTED_DEAD_BUSH,
                Blocks.POTTED_CACTUS,
                Blocks.CARROTS,
                Blocks.POTATOES,
                Blocks.OAK_BUTTON,
                Blocks.SPRUCE_BUTTON,
                Blocks.BIRCH_BUTTON,
                Blocks.JUNGLE_BUTTON,
                Blocks.ACACIA_BUTTON,
                Blocks.DARK_OAK_BUTTON,
                Blocks.SKELETON_SKULL,
                Blocks.SKELETON_WALL_SKULL,
                Blocks.WITHER_SKELETON_SKULL,
                Blocks.WITHER_SKELETON_WALL_SKULL,
                Blocks.ZOMBIE_HEAD,
                Blocks.ZOMBIE_WALL_HEAD,
                Blocks.PLAYER_HEAD,
                Blocks.PLAYER_WALL_HEAD,
                Blocks.CREEPER_HEAD,
                Blocks.CREEPER_WALL_HEAD,
                Blocks.DRAGON_HEAD,
                Blocks.DRAGON_WALL_HEAD,
                Blocks.ANVIL,
                Blocks.CHIPPED_ANVIL,
                Blocks.DAMAGED_ANVIL,
                Blocks.TRAPPED_CHEST,
                Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE,
                Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE,
                Blocks.COMPARATOR,
                Blocks.DAYLIGHT_DETECTOR,
                Blocks.REDSTONE_BLOCK,
                Blocks.NETHER_QUARTZ_ORE,
                Blocks.HOPPER,
                Blocks.QUARTZ_BLOCK,
                Blocks.CHISELED_QUARTZ_BLOCK,
                Blocks.QUARTZ_PILLAR,
                Blocks.QUARTZ_STAIRS,
                Blocks.ACTIVATOR_RAIL,
                Blocks.DROPPER,
                Blocks.WHITE_TERRACOTTA,
                Blocks.ORANGE_TERRACOTTA,
                Blocks.MAGENTA_TERRACOTTA,
                Blocks.LIGHT_BLUE_TERRACOTTA,
                Blocks.YELLOW_TERRACOTTA,
                Blocks.LIME_TERRACOTTA,
                Blocks.PINK_TERRACOTTA,
                Blocks.GRAY_TERRACOTTA,
                Blocks.LIGHT_GRAY_TERRACOTTA,
                Blocks.CYAN_TERRACOTTA,
                Blocks.PURPLE_TERRACOTTA,
                Blocks.BLUE_TERRACOTTA,
                Blocks.BROWN_TERRACOTTA,
                Blocks.GREEN_TERRACOTTA,
                Blocks.RED_TERRACOTTA,
                Blocks.BLACK_TERRACOTTA,
                Blocks.WHITE_STAINED_GLASS_PANE,
                Blocks.ORANGE_STAINED_GLASS_PANE,
                Blocks.MAGENTA_STAINED_GLASS_PANE,
                Blocks.LIGHT_BLUE_STAINED_GLASS_PANE,
                Blocks.YELLOW_STAINED_GLASS_PANE,
                Blocks.LIME_STAINED_GLASS_PANE,
                Blocks.PINK_STAINED_GLASS_PANE,
                Blocks.GRAY_STAINED_GLASS_PANE,
                Blocks.LIGHT_GRAY_STAINED_GLASS_PANE,
                Blocks.CYAN_STAINED_GLASS_PANE,
                Blocks.PURPLE_STAINED_GLASS_PANE,
                Blocks.BLUE_STAINED_GLASS_PANE,
                Blocks.BROWN_STAINED_GLASS_PANE,
                Blocks.GREEN_STAINED_GLASS_PANE,
                Blocks.RED_STAINED_GLASS_PANE,
                Blocks.BLACK_STAINED_GLASS_PANE,
                Blocks.ACACIA_STAIRS,
                Blocks.DARK_OAK_STAIRS,
                Blocks.SLIME_BLOCK,
                Blocks.BARRIER,
                Blocks.IRON_TRAPDOOR,
                Blocks.PRISMARINE,
                Blocks.PRISMARINE_BRICKS,
                Blocks.DARK_PRISMARINE,
                Blocks.PRISMARINE_STAIRS,
                Blocks.PRISMARINE_BRICK_STAIRS,
                Blocks.DARK_PRISMARINE_STAIRS,
                Blocks.PRISMARINE_SLAB,
                Blocks.PRISMARINE_BRICK_SLAB,
                Blocks.DARK_PRISMARINE_SLAB,
                Blocks.SEA_LANTERN,
                Blocks.HAY_BLOCK,
                Blocks.WHITE_CARPET,
                Blocks.ORANGE_CARPET,
                Blocks.MAGENTA_CARPET,
                Blocks.LIGHT_BLUE_CARPET,
                Blocks.YELLOW_CARPET,
                Blocks.LIME_CARPET,
                Blocks.PINK_CARPET,
                Blocks.GRAY_CARPET,
                Blocks.LIGHT_GRAY_CARPET,
                Blocks.CYAN_CARPET,
                Blocks.PURPLE_CARPET,
                Blocks.BLUE_CARPET,
                Blocks.BROWN_CARPET,
                Blocks.GREEN_CARPET,
                Blocks.RED_CARPET,
                Blocks.BLACK_CARPET,
                Blocks.TERRACOTTA,
                Blocks.COAL_BLOCK,
                Blocks.PACKED_ICE,
                Blocks.SUNFLOWER,
                Blocks.LILAC,
                Blocks.ROSE_BUSH,
                Blocks.PEONY,
                Blocks.TALL_GRASS,
                Blocks.LARGE_FERN,
                Blocks.WHITE_BANNER,
                Blocks.ORANGE_BANNER,
                Blocks.MAGENTA_BANNER,
                Blocks.LIGHT_BLUE_BANNER,
                Blocks.YELLOW_BANNER,
                Blocks.LIME_BANNER,
                Blocks.PINK_BANNER,
                Blocks.GRAY_BANNER,
                Blocks.LIGHT_GRAY_BANNER,
                Blocks.CYAN_BANNER,
                Blocks.PURPLE_BANNER,
                Blocks.BLUE_BANNER,
                Blocks.BROWN_BANNER,
                Blocks.GREEN_BANNER,
                Blocks.RED_BANNER,
                Blocks.BLACK_BANNER,
                Blocks.WHITE_WALL_BANNER,
                Blocks.ORANGE_WALL_BANNER,
                Blocks.MAGENTA_WALL_BANNER,
                Blocks.LIGHT_BLUE_WALL_BANNER,
                Blocks.YELLOW_WALL_BANNER,
                Blocks.LIME_WALL_BANNER,
                Blocks.PINK_WALL_BANNER,
                Blocks.GRAY_WALL_BANNER,
                Blocks.LIGHT_GRAY_WALL_BANNER,
                Blocks.CYAN_WALL_BANNER,
                Blocks.PURPLE_WALL_BANNER,
                Blocks.BLUE_WALL_BANNER,
                Blocks.BROWN_WALL_BANNER,
                Blocks.GREEN_WALL_BANNER,
                Blocks.RED_WALL_BANNER,
                Blocks.BLACK_WALL_BANNER,
                Blocks.RED_SANDSTONE,
                Blocks.CHISELED_RED_SANDSTONE,
                Blocks.CUT_RED_SANDSTONE,
                Blocks.RED_SANDSTONE_STAIRS,
                Blocks.OAK_SLAB,
                Blocks.SPRUCE_SLAB,
                Blocks.BIRCH_SLAB,
                Blocks.JUNGLE_SLAB,
                Blocks.ACACIA_SLAB,
                Blocks.DARK_OAK_SLAB,
                Blocks.STONE_SLAB,
                Blocks.SMOOTH_STONE_SLAB,
                Blocks.SANDSTONE_SLAB,
                Blocks.CUT_SANDSTONE,
                Blocks.PETRIFIED_OAK_SLAB,
                Blocks.COBBLESTONE_SLAB,
                Blocks.BRICK_SLAB,
                Blocks.STONE_BRICK_SLAB,
                Blocks.NETHER_BRICK_SLAB,
                Blocks.QUARTZ_SLAB,
                Blocks.RED_SANDSTONE_SLAB,
                Blocks.CUT_RED_SANDSTONE_SLAB,
                Blocks.PURPUR_SLAB,
                Blocks.SMOOTH_STONE,
                Blocks.SMOOTH_SANDSTONE,
                Blocks.SMOOTH_QUARTZ,
                Blocks.SMOOTH_RED_SANDSTONE,
                Blocks.SPRUCE_FENCE_GATE,
                Blocks.BIRCH_FENCE_GATE,
                Blocks.JUNGLE_FENCE_GATE,
                Blocks.ACACIA_FENCE_GATE,
                Blocks.DARK_OAK_FENCE_GATE,
                Blocks.SPRUCE_FENCE,
                Blocks.BIRCH_FENCE,
                Blocks.JUNGLE_FENCE,
                Blocks.ACACIA_FENCE,
                Blocks.DARK_OAK_FENCE,
                Blocks.SPRUCE_DOOR,
                Blocks.BIRCH_DOOR,
                Blocks.JUNGLE_DOOR,
                Blocks.ACACIA_DOOR,
                Blocks.DARK_OAK_DOOR,
                Blocks.END_ROD,
                Blocks.CHORUS_PLANT,
                Blocks.CHORUS_FLOWER,
                Blocks.PURPUR_BLOCK,
                Blocks.PURPUR_PILLAR,
                Blocks.PURPUR_STAIRS,
                Blocks.END_STONE_BRICKS,
                Blocks.BEETROOTS,
                Blocks.GRASS_PATH,
                Blocks.END_GATEWAY,
                Blocks.REPEATING_COMMAND_BLOCK,
                Blocks.CHAIN_COMMAND_BLOCK,
                Blocks.FROSTED_ICE,
                Blocks.MAGMA_BLOCK,
                Blocks.NETHER_WART_BLOCK,
                Blocks.RED_NETHER_BRICKS,
                Blocks.BONE_BLOCK,
                Blocks.STRUCTURE_VOID,
                Blocks.OBSERVER,
                Blocks.SHULKER_BOX,
                Blocks.WHITE_SHULKER_BOX,
                Blocks.ORANGE_SHULKER_BOX,
                Blocks.MAGENTA_SHULKER_BOX,
                Blocks.LIGHT_BLUE_SHULKER_BOX,
                Blocks.YELLOW_SHULKER_BOX,
                Blocks.LIME_SHULKER_BOX,
                Blocks.PINK_SHULKER_BOX,
                Blocks.GRAY_SHULKER_BOX,
                Blocks.LIGHT_GRAY_SHULKER_BOX,
                Blocks.CYAN_SHULKER_BOX,
                Blocks.PURPLE_SHULKER_BOX,
                Blocks.BLUE_SHULKER_BOX,
                Blocks.BROWN_SHULKER_BOX,
                Blocks.GREEN_SHULKER_BOX,
                Blocks.RED_SHULKER_BOX,
                Blocks.BLACK_SHULKER_BOX,
                Blocks.WHITE_GLAZED_TERRACOTTA,
                Blocks.ORANGE_GLAZED_TERRACOTTA,
                Blocks.MAGENTA_GLAZED_TERRACOTTA,
                Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA,
                Blocks.YELLOW_GLAZED_TERRACOTTA,
                Blocks.LIME_GLAZED_TERRACOTTA,
                Blocks.PINK_GLAZED_TERRACOTTA,
                Blocks.GRAY_GLAZED_TERRACOTTA,
                Blocks.LIGHT_GRAY_GLAZED_TERRACOTTA,
                Blocks.CYAN_GLAZED_TERRACOTTA,
                Blocks.PURPLE_GLAZED_TERRACOTTA,
                Blocks.BLUE_GLAZED_TERRACOTTA,
                Blocks.BROWN_GLAZED_TERRACOTTA,
                Blocks.GREEN_GLAZED_TERRACOTTA,
                Blocks.RED_GLAZED_TERRACOTTA,
                Blocks.BLACK_GLAZED_TERRACOTTA,
                Blocks.WHITE_CONCRETE,
                Blocks.ORANGE_CONCRETE,
                Blocks.MAGENTA_CONCRETE,
                Blocks.LIGHT_BLUE_CONCRETE,
                Blocks.YELLOW_CONCRETE,
                Blocks.LIME_CONCRETE,
                Blocks.PINK_CONCRETE,
                Blocks.GRAY_CONCRETE,
                Blocks.LIGHT_GRAY_CONCRETE,
                Blocks.CYAN_CONCRETE,
                Blocks.PURPLE_CONCRETE,
                Blocks.BLUE_CONCRETE,
                Blocks.BROWN_CONCRETE,
                Blocks.GREEN_CONCRETE,
                Blocks.RED_CONCRETE,
                Blocks.BLACK_CONCRETE,
                Blocks.WHITE_CONCRETE_POWDER,
                Blocks.ORANGE_CONCRETE_POWDER,
                Blocks.MAGENTA_CONCRETE_POWDER,
                Blocks.LIGHT_BLUE_CONCRETE_POWDER,
                Blocks.YELLOW_CONCRETE_POWDER,
                Blocks.LIME_CONCRETE_POWDER,
                Blocks.PINK_CONCRETE_POWDER,
                Blocks.GRAY_CONCRETE_POWDER,
                Blocks.LIGHT_GRAY_CONCRETE_POWDER,
                Blocks.CYAN_CONCRETE_POWDER,
                Blocks.PURPLE_CONCRETE_POWDER,
                Blocks.BLUE_CONCRETE_POWDER,
                Blocks.BROWN_CONCRETE_POWDER,
                Blocks.GREEN_CONCRETE_POWDER,
                Blocks.RED_CONCRETE_POWDER,
                Blocks.BLACK_CONCRETE_POWDER,
                Blocks.KELP,
                Blocks.KELP_PLANT,
                Blocks.DRIED_KELP_BLOCK,
                Blocks.TURTLE_EGG,
                Blocks.DEAD_TUBE_CORAL_BLOCK,
                Blocks.DEAD_BRAIN_CORAL_BLOCK,
                Blocks.DEAD_BUBBLE_CORAL_BLOCK,
                Blocks.DEAD_FIRE_CORAL_BLOCK,
                Blocks.DEAD_HORN_CORAL_BLOCK,
                Blocks.TUBE_CORAL_BLOCK,
                Blocks.BRAIN_CORAL_BLOCK,
                Blocks.BUBBLE_CORAL_BLOCK,
                Blocks.FIRE_CORAL_BLOCK,
                Blocks.HORN_CORAL_BLOCK,
                Blocks.DEAD_TUBE_CORAL,
                Blocks.DEAD_BRAIN_CORAL,
                Blocks.DEAD_BUBBLE_CORAL,
                Blocks.DEAD_FIRE_CORAL,
                Blocks.DEAD_HORN_CORAL,
                Blocks.TUBE_CORAL,
                Blocks.BRAIN_CORAL,
                Blocks.BUBBLE_CORAL,
                Blocks.FIRE_CORAL,
                Blocks.HORN_CORAL,
                Blocks.DEAD_TUBE_CORAL_FAN,
                Blocks.DEAD_BRAIN_CORAL_FAN,
                Blocks.DEAD_BUBBLE_CORAL_FAN,
                Blocks.DEAD_FIRE_CORAL_FAN,
                Blocks.DEAD_HORN_CORAL_FAN,
                Blocks.TUBE_CORAL_FAN,
                Blocks.BRAIN_CORAL_FAN,
                Blocks.BUBBLE_CORAL_FAN,
                Blocks.FIRE_CORAL_FAN,
                Blocks.HORN_CORAL_FAN,
                Blocks.DEAD_TUBE_CORAL_WALL_FAN,
                Blocks.DEAD_BRAIN_CORAL_WALL_FAN,
                Blocks.DEAD_BUBBLE_CORAL_WALL_FAN,
                Blocks.DEAD_FIRE_CORAL_WALL_FAN,
                Blocks.DEAD_HORN_CORAL_WALL_FAN,
                Blocks.TUBE_CORAL_WALL_FAN,
                Blocks.BRAIN_CORAL_WALL_FAN,
                Blocks.BUBBLE_CORAL_WALL_FAN,
                Blocks.FIRE_CORAL_WALL_FAN,
                Blocks.HORN_CORAL_WALL_FAN,
                Blocks.SEA_PICKLE,
                Blocks.BLUE_ICE,
                Blocks.CONDUIT,
                Blocks.BAMBOO_SAPLING,
                Blocks.BAMBOO,
                Blocks.POTTED_BAMBOO,
                Blocks.BUBBLE_COLUMN,

                Blocks.POLISHED_GRANITE_STAIRS,
                Blocks.SMOOTH_RED_SANDSTONE_STAIRS,
                Blocks.MOSSY_STONE_BRICK_STAIRS,
                Blocks.POLISHED_DIORITE_STAIRS,
                Blocks.MOSSY_COBBLESTONE_STAIRS,
                Blocks.END_STONE_BRICK_STAIRS,
                Blocks.STONE_STAIRS,
                Blocks.SMOOTH_SANDSTONE_STAIRS,
                Blocks.SMOOTH_QUARTZ,
                Blocks.GRANITE_STAIRS,
                Blocks.ANDESITE_STAIRS,
                Blocks.RED_NETHER_BRICK_STAIRS,
                Blocks.POLISHED_ANDESITE_STAIRS,
                Blocks.DIORITE_STAIRS,
                Blocks.POLISHED_GRANITE_SLAB,
                Blocks.SMOOTH_RED_SANDSTONE_SLAB,
                Blocks.MOSSY_STONE_BRICK_SLAB,
                Blocks.POLISHED_DIORITE_SLAB,
                Blocks.MOSSY_COBBLESTONE_SLAB,
                Blocks.END_STONE_BRICK_SLAB,
                Blocks.SMOOTH_SANDSTONE_SLAB,
                Blocks.SMOOTH_QUARTZ,
                Blocks.GRANITE_SLAB,
                Blocks.ANDESITE_SLAB,
                Blocks.RED_NETHER_BRICK_SLAB,
                Blocks.POLISHED_ANDESITE_SLAB,
                Blocks.DIORITE_SLAB,
                Blocks.BRICK_WALL,
                Blocks.PRISMARINE_WALL,
                Blocks.RED_SANDSTONE,
                Blocks.MOSSY_STONE_BRICK_WALL,
                Blocks.GRANITE_WALL,
                Blocks.STONE_BRICK_WALL,
                Blocks.NETHER_BRICK_WALL,
                Blocks.ANDESITE_WALL,
                Blocks.RED_NETHER_BRICK_WALL,
                Blocks.SANDSTONE_WALL,
                Blocks.END_STONE_BRICK_WALL,
                Blocks.DIORITE_WALL,
                Blocks.SCAFFOLDING,
                Blocks.LOOM,
                Blocks.BARREL,
                Blocks.SMOKER,
                Blocks.BLAST_FURNACE,
                Blocks.CARTOGRAPHY_TABLE,
                Blocks.FLETCHING_TABLE,
                Blocks.GRINDSTONE,
                Blocks.LECTERN,
                Blocks.SMITHING_TABLE,
                Blocks.STONECUTTER,
                Blocks.BELL,
                Blocks.LANTERN,
                Blocks.SOUL_LANTERN,
                Blocks.CAMPFIRE,
                Blocks.SOUL_CAMPFIRE,
                Blocks.SWEET_BERRY_BUSH,
                Blocks.WARPED_STEM,
                Blocks.STRIPPED_WARPED_STEM,
                Blocks.WARPED_HYPHAE,
                Blocks.STRIPPED_WARPED_HYPHAE,
                Blocks.WARPED_NYLIUM,
                Blocks.WARPED_FUNGUS,
                Blocks.WARPED_WART_BLOCK,
                Blocks.WARPED_ROOTS,
                Blocks.NETHER_SPROUTS,
                Blocks.CRIMSON_STEM,
                Blocks.STRIPPED_CRIMSON_STEM,
                Blocks.CRIMSON_HYPHAE,
                Blocks.STRIPPED_CRIMSON_HYPHAE,
                Blocks.CRIMSON_NYLIUM,
                Blocks.CRIMSON_FUNGUS,
                Blocks.SHROOMLIGHT,
                Blocks.WEEPING_VINES,
                Blocks.WEEPING_VINES,
                Blocks.TWISTING_VINES,
                Blocks.TWISTING_VINES_PLANT,
                Blocks.CRIMSON_ROOTS,
                Blocks.CRIMSON_PLANKS,
                Blocks.WARPED_PLANKS,
                Blocks.CRIMSON_SLAB,
                Blocks.WARPED_SLAB,
                Blocks.CRIMSON_PRESSURE_PLATE,
                Blocks.WARPED_PRESSURE_PLATE,
                Blocks.CRIMSON_FENCE,
                Blocks.WARPED_FENCE,
                Blocks.CRIMSON_TRAPDOOR,
                Blocks.WARPED_TRAPDOOR,
                Blocks.CRIMSON_FENCE,
                Blocks.WARPED_FENCE_GATE,
                Blocks.CRIMSON_STAIRS,
                Blocks.WARPED_STAIRS,
                Blocks.CRIMSON_BUTTON,
                Blocks.WARPED_BUTTON,
                Blocks.CRIMSON_DOOR,
                Blocks.WARPED_DOOR,
                Blocks.CRIMSON_SIGN,
                Blocks.WARPED_SIGN,
                Blocks.CRIMSON_WALL_SIGN,
                Blocks.WARPED_WALL_SIGN,
                Blocks.STRUCTURE_BLOCK,
                Blocks.JIGSAW,
                Blocks.COMPOSTER,
                Blocks.TARGET,
                Blocks.BEE_NEST,
                Blocks.BEEHIVE,
                Blocks.HONEY_BLOCK,
                Blocks.HONEYCOMB_BLOCK,
                Blocks.NETHERITE_BLOCK,
                Blocks.ANCIENT_DEBRIS,
                Blocks.CRYING_OBSIDIAN,
                Blocks.RESPAWN_ANCHOR,
                Blocks.POTTED_CRIMSON_FUNGUS,
                Blocks.POTTED_WARPED_FUNGUS,
                Blocks.POTTED_CRIMSON_ROOTS,
                Blocks.POTTED_WARPED_ROOTS,
                Blocks.LODESTONE,
                Blocks.BLACKSTONE,
                Blocks.BLACKSTONE_STAIRS,
                Blocks.BLACKSTONE_WALL,
                Blocks.BLACKSTONE_SLAB,
                Blocks.POLISHED_BLACKSTONE,
                Blocks.POLISHED_BLACKSTONE_BRICKS,
                Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS,
                Blocks.CHISELED_POLISHED_BLACKSTONE,
                Blocks.POLISHED_BLACKSTONE_BRICK_SLAB,
                Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS,
                Blocks.POLISHED_BLACKSTONE_BRICK_WALL,
                Blocks.GILDED_BLACKSTONE,
                Blocks.POLISHED_BLACKSTONE_STAIRS,
                Blocks.POLISHED_BLACKSTONE_SLAB,
                Blocks.POLISHED_BLACKSTONE_PRESSURE_PLATE,
                Blocks.POLISHED_BLACKSTONE_BUTTON,
                Blocks.POLISHED_BLACKSTONE_WALL,
                Blocks.CHISELED_NETHER_BRICKS,
                Blocks.CRACKED_NETHER_BRICKS,
                Blocks.QUARTZ_BRICKS,

                TaitaiModBlocks.PUT,
                TaitaiModBlocks.TAITAI_TNT,
                TaitaiModBlocks.FIGURE,
                TaitaiModBlocks.TBLOCK,
                TaitaiModBlocks.TGLASS,
                TaitaiModBlocks.TGLASS2,
                TaitaiModBlocks.TAITAI_GLOW_STONE,
                TaitaiModBlocks.SWITCH,
                TaitaiModBlocks.TAITAI_CRAFTING_TABLE

        };
        Block all = blocks[random.nextInt(761 + 9)];

        changedBlock(world, pos, world.getBlockState(pos).getBlock(), all);
    }
    public static void changedHandItem(PlayerEntity player, Item after){
        ItemStack stack = player.getItemInHand(Hand.MAIN_HAND);
        player.inventory.removeItem(stack);
        player.addItem(new ItemStack(after));
    }
    public static void netherGate(World world, BlockPos pos, PlayerEntity player){
        if (player.getDirection() == Direction.NORTH || player.getDirection() == Direction.SOUTH) {

            world.destroyBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ()), true);
            world.destroyBlock(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ()), true);
            world.destroyBlock(new BlockPos(pos.getX() - 2, pos.getY(), pos.getZ()), true);
            world.destroyBlock(new BlockPos(pos.getX() - 2, pos.getY() + 1, pos.getZ()), true);
            world.destroyBlock(new BlockPos(pos.getX() - 2, pos.getY() + 2, pos.getZ()), true);
            world.destroyBlock(new BlockPos(pos.getX() - 2, pos.getY() + 3, pos.getZ()), true);
            world.destroyBlock(new BlockPos(pos.getX() - 2, pos.getY() + 4, pos.getZ()), true);
            world.destroyBlock(new BlockPos(pos.getX() - 1, pos.getY() + 4, pos.getZ()), true);
            world.destroyBlock(new BlockPos(pos.getX(), pos.getY() + 4, pos.getZ()), true);
            world.destroyBlock(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ()), true);
            world.destroyBlock(new BlockPos(pos.getX() + 2, pos.getY(), pos.getZ()), true);
            world.destroyBlock(new BlockPos(pos.getX() + 2, pos.getY() + 1, pos.getZ()), true);
            world.destroyBlock(new BlockPos(pos.getX() + 2, pos.getY() + 2, pos.getZ()), true);
            world.destroyBlock(new BlockPos(pos.getX() + 2, pos.getY() + 3, pos.getZ()), true);
            world.destroyBlock(new BlockPos(pos.getX() + 2, pos.getY() + 4, pos.getZ()), true);
            world.destroyBlock(new BlockPos(pos.getX() + 1, pos.getY() + 4, pos.getZ()), true);
            world.destroyBlock(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ()), true);
            world.destroyBlock(new BlockPos(pos.getX(), pos.getY() + 2, pos.getZ()), true);
            world.destroyBlock(new BlockPos(pos.getX(), pos.getY() + 3, pos.getZ()), true);
            world.destroyBlock(new BlockPos(pos.getX() + 1, pos.getY() + 1, pos.getZ()), true);
            world.destroyBlock(new BlockPos(pos.getX() + 1, pos.getY() + 2, pos.getZ()), true);
            world.destroyBlock(new BlockPos(pos.getX() + 1, pos.getY() + 3, pos.getZ()), true);
            world.destroyBlock(new BlockPos(pos.getX() - 1, pos.getY() + 1, pos.getZ()), true);
            world.destroyBlock(new BlockPos(pos.getX() - 1, pos.getY() + 2, pos.getZ()), true);
            world.destroyBlock(new BlockPos(pos.getX() - 1, pos.getY() + 3, pos.getZ()), true);


            setBlock(world, pos, Blocks.OBSIDIAN);
            world.setBlock(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ()), Blocks.OBSIDIAN.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX() - 2, pos.getY(), pos.getZ()), Blocks.OBSIDIAN.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX() - 2, pos.getY() + 1, pos.getZ()), Blocks.OBSIDIAN.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX() - 2, pos.getY() + 2, pos.getZ()), Blocks.OBSIDIAN.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX() - 2, pos.getY() + 3, pos.getZ()), Blocks.OBSIDIAN.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX() - 2, pos.getY() + 4, pos.getZ()), Blocks.OBSIDIAN.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX() - 1, pos.getY() + 4, pos.getZ()), Blocks.OBSIDIAN.defaultBlockState(), 1);

            world.setBlock(new BlockPos(pos.getX(), pos.getY() + 4, pos.getZ()), Blocks.OBSIDIAN.defaultBlockState(), 1);

            world.setBlock(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ()), Blocks.OBSIDIAN.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX() + 2, pos.getY(), pos.getZ()), Blocks.OBSIDIAN.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX() + 2, pos.getY() + 1, pos.getZ()), Blocks.OBSIDIAN.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX() + 2, pos.getY() + 2, pos.getZ()), Blocks.OBSIDIAN.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX() + 2, pos.getY() + 3, pos.getZ()), Blocks.OBSIDIAN.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX() + 2, pos.getY() + 4, pos.getZ()), Blocks.OBSIDIAN.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX() + 1, pos.getY() + 4, pos.getZ()), Blocks.OBSIDIAN.defaultBlockState(), 1);

            world.setBlock(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ()), Blocks.NETHER_PORTAL.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX(), pos.getY() + 2, pos.getZ()), Blocks.NETHER_PORTAL.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX(), pos.getY() + 3, pos.getZ()), Blocks.NETHER_PORTAL.defaultBlockState(), 1);

            world.setBlock(new BlockPos(pos.getX() + 1, pos.getY() + 1, pos.getZ()), Blocks.NETHER_PORTAL.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX() + 1, pos.getY() + 2, pos.getZ()), Blocks.NETHER_PORTAL.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX() + 1, pos.getY() + 3, pos.getZ()), Blocks.NETHER_PORTAL.defaultBlockState(), 1);

            world.setBlock(new BlockPos(pos.getX() - 1, pos.getY() + 1, pos.getZ()), Blocks.NETHER_PORTAL.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX() - 1, pos.getY() + 2, pos.getZ()), Blocks.NETHER_PORTAL.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX() - 1, pos.getY() + 3, pos.getZ()), Blocks.NETHER_PORTAL.defaultBlockState(), 1);
        }

        if (player.getDirection() == Direction.EAST || player.getDirection() == Direction.WEST){
            world.destroyBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ()), true);
            world.destroyBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ() -1), true);
            world.destroyBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ() -2), true);
            world.destroyBlock(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ() - 2), true);
            world.destroyBlock(new BlockPos(pos.getX(), pos.getY() + 2, pos.getZ() - 2), true);
            world.destroyBlock(new BlockPos(pos.getX(), pos.getY() + 3, pos.getZ() - 2), true);
            world.destroyBlock(new BlockPos(pos.getX(), pos.getY() + 4, pos.getZ() - 2), true);
            world.destroyBlock(new BlockPos(pos.getX(), pos.getY() + 4, pos.getZ() - 1), true);
            world.destroyBlock(new BlockPos(pos.getX(), pos.getY() + 4, pos.getZ()), true);
            world.destroyBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1), true);
            world.destroyBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 2), true);
            world.destroyBlock(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ() + 2), true);
            world.destroyBlock(new BlockPos(pos.getX(), pos.getY() + 2, pos.getZ() + 2), true);
            world.destroyBlock(new BlockPos(pos.getX(), pos.getY() + 3, pos.getZ() + 2), true);
            world.destroyBlock(new BlockPos(pos.getX(), pos.getY() + 4, pos.getZ() + 2), true);
            world.destroyBlock(new BlockPos(pos.getX(), pos.getY() + 4, pos.getZ() + 1), true);
            world.destroyBlock(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ()), true);
            world.destroyBlock(new BlockPos(pos.getX(), pos.getY() + 2, pos.getZ()), true);
            world.destroyBlock(new BlockPos(pos.getX(), pos.getY() + 3, pos.getZ()), true);
            world.destroyBlock(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ() + 1), true);
            world.destroyBlock(new BlockPos(pos.getX(), pos.getY() + 2, pos.getZ() + 1), true);
            world.destroyBlock(new BlockPos(pos.getX(), pos.getY() + 3, pos.getZ() + 1), true);
            world.destroyBlock(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ() -1), true);
            world.destroyBlock(new BlockPos(pos.getX(), pos.getY() + 2, pos.getZ() - 1), true);
            world.destroyBlock(new BlockPos(pos.getX(), pos.getY() + 3, pos.getZ() - 1), true);



            setBlock(world, pos, Blocks.OBSIDIAN);
            world.setBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ() -1), Blocks.OBSIDIAN.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ() -2), Blocks.OBSIDIAN.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ() - 2), Blocks.OBSIDIAN.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX(), pos.getY() + 2, pos.getZ() - 2), Blocks.OBSIDIAN.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX(), pos.getY() + 3, pos.getZ() - 2), Blocks.OBSIDIAN.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX(), pos.getY() + 4, pos.getZ() - 2), Blocks.OBSIDIAN.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX(), pos.getY() + 4, pos.getZ() - 1), Blocks.OBSIDIAN.defaultBlockState(), 1);

            world.setBlock(new BlockPos(pos.getX(), pos.getY() + 4, pos.getZ()), Blocks.OBSIDIAN.defaultBlockState(), 1);

            world.setBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1), Blocks.OBSIDIAN.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 2), Blocks.OBSIDIAN.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ() + 2), Blocks.OBSIDIAN.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX(), pos.getY() + 2, pos.getZ() + 2), Blocks.OBSIDIAN.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX(), pos.getY() + 3, pos.getZ() + 2), Blocks.OBSIDIAN.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX(), pos.getY() + 4, pos.getZ() + 2), Blocks.OBSIDIAN.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX(), pos.getY() + 4, pos.getZ() + 1), Blocks.OBSIDIAN.defaultBlockState(), 1);

            world.setBlock(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ()), Blocks.NETHER_PORTAL.getStateDefinition().any().setValue(AXIS, Direction.Axis.Z), 1);
            world.setBlock(new BlockPos(pos.getX(), pos.getY() + 2, pos.getZ()), Blocks.NETHER_PORTAL.getStateDefinition().any().setValue(AXIS, Direction.Axis.Z), 1);
            world.setBlock(new BlockPos(pos.getX(), pos.getY() + 3, pos.getZ()), Blocks.NETHER_PORTAL.getStateDefinition().any().setValue(AXIS, Direction.Axis.Z), 1);

            world.setBlock(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ() + 1), Blocks.NETHER_PORTAL.getStateDefinition().any().setValue(AXIS, Direction.Axis.Z), 1);
            world.setBlock(new BlockPos(pos.getX(), pos.getY() + 2, pos.getZ() + 1), Blocks.NETHER_PORTAL.getStateDefinition().any().setValue(AXIS, Direction.Axis.Z), 1);
            world.setBlock(new BlockPos(pos.getX(), pos.getY() + 3, pos.getZ() + 1), Blocks.NETHER_PORTAL.getStateDefinition().any().setValue(AXIS, Direction.Axis.Z), 1);

            world.setBlock(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ() -1), Blocks.NETHER_PORTAL.getStateDefinition().any().setValue(AXIS, Direction.Axis.Z), 1);
            world.setBlock(new BlockPos(pos.getX(), pos.getY() + 2, pos.getZ() - 1), Blocks.NETHER_PORTAL.getStateDefinition().any().setValue(AXIS, Direction.Axis.Z), 1);
            world.setBlock(new BlockPos(pos.getX(), pos.getY() + 3, pos.getZ() - 1), Blocks.NETHER_PORTAL.getStateDefinition().any().setValue(AXIS, Direction.Axis.Z), 1);

        }
    }
    public static void endGate(World world, BlockPos pos, PlayerEntity player){
        if (player.getDirection() == Direction.SOUTH){
            world.destroyBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ()), true);
            world.destroyBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 4), true);
            world.destroyBlock(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ()), true);
            world.destroyBlock(new BlockPos(pos.getX() + 2, pos.getY(), pos.getZ() + 1), true);
            world.destroyBlock(new BlockPos(pos.getX() + 2, pos.getY(), pos.getZ() + 2), true);
            world.destroyBlock(new BlockPos(pos.getX() + 2, pos.getY(), pos.getZ() + 3), true);
            world.destroyBlock(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() + 4), true);
            world.destroyBlock(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ()), true);
            world.destroyBlock(new BlockPos(pos.getX() - 2, pos.getY(), pos.getZ() + 1), true);
            world.destroyBlock(new BlockPos(pos.getX() - 2, pos.getY(), pos.getZ() + 2), true);
            world.destroyBlock(new BlockPos(pos.getX() - 2, pos.getY(), pos.getZ() + 3), true);
            world.destroyBlock(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() + 4), true);
            world.destroyBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1), true);
            world.destroyBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 2), true);
            world.destroyBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 3), true);
            world.destroyBlock(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() + 1), true);
            world.destroyBlock(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() + 2), true);
            world.destroyBlock(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() + 3), true);
            world.destroyBlock(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() + 1), true);
            world.destroyBlock(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() + 2), true);
            world.destroyBlock(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() + 3), true);


            world.setBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ()), Blocks.END_PORTAL_FRAME.getStateDefinition().any().setValue(FACING, Direction.NORTH), 1);
            world.setBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 4), Blocks.END_PORTAL_FRAME.getStateDefinition().any().setValue(FACING, Direction.NORTH), 1);
            world.setBlock(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ()), Blocks.END_PORTAL_FRAME.getStateDefinition().any().setValue(FACING, Direction.NORTH), 1);
            world.setBlock(new BlockPos(pos.getX() + 2, pos.getY(), pos.getZ() + 1), Blocks.END_PORTAL_FRAME.getStateDefinition().any().setValue(FACING, Direction.NORTH), 1);
            world.setBlock(new BlockPos(pos.getX() + 2, pos.getY(), pos.getZ() + 2), Blocks.END_PORTAL_FRAME.getStateDefinition().any().setValue(FACING, Direction.EAST), 1);
            world.setBlock(new BlockPos(pos.getX() + 2, pos.getY(), pos.getZ() + 3), Blocks.END_PORTAL_FRAME.getStateDefinition().any().setValue(FACING, Direction.EAST), 1);
            world.setBlock(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() + 4), Blocks.END_PORTAL_FRAME.getStateDefinition().any().setValue(FACING, Direction.NORTH), 1);
            world.setBlock(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ()), Blocks.END_PORTAL_FRAME.getStateDefinition().any().setValue(FACING, Direction.NORTH), 1);
            world.setBlock(new BlockPos(pos.getX() - 2, pos.getY(), pos.getZ() + 1), Blocks.END_PORTAL_FRAME.getStateDefinition().any().setValue(FACING, Direction.EAST), 1);
            world.setBlock(new BlockPos(pos.getX() - 2, pos.getY(), pos.getZ() + 2), Blocks.END_PORTAL_FRAME.getStateDefinition().any().setValue(FACING, Direction.EAST), 1);
            world.setBlock(new BlockPos(pos.getX() - 2, pos.getY(), pos.getZ() + 3), Blocks.END_PORTAL_FRAME.getStateDefinition().any().setValue(FACING, Direction.EAST), 1);
            world.setBlock(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() + 4), Blocks.END_PORTAL_FRAME.getStateDefinition().any().setValue(FACING, Direction.NORTH), 1);
            world.setBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1), Blocks.END_PORTAL.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 2), Blocks.END_PORTAL.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 3), Blocks.END_PORTAL.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() + 1), Blocks.END_PORTAL.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() + 2), Blocks.END_PORTAL.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() + 3), Blocks.END_PORTAL.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() + 1), Blocks.END_PORTAL.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() + 2), Blocks.END_PORTAL.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() + 3), Blocks.END_PORTAL.defaultBlockState(), 1);
        }

        if (player.getDirection() == Direction.NORTH){
            world.destroyBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ()), true);
            world.destroyBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 4), true);
            world.destroyBlock(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ()), true);
            world.destroyBlock(new BlockPos(pos.getX() + 2, pos.getY(), pos.getZ() - 1), true);
            world.destroyBlock(new BlockPos(pos.getX() + 2, pos.getY(), pos.getZ() - 2), true);
            world.destroyBlock(new BlockPos(pos.getX() + 2, pos.getY(), pos.getZ() - 3), true);
            world.destroyBlock(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() - 4), true);
            world.destroyBlock(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ()), true);
            world.destroyBlock(new BlockPos(pos.getX() - 2, pos.getY(), pos.getZ() - 1), true);
            world.destroyBlock(new BlockPos(pos.getX() - 2, pos.getY(), pos.getZ() - 2), true);
            world.destroyBlock(new BlockPos(pos.getX() - 2, pos.getY(), pos.getZ() - 3), true);
            world.destroyBlock(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() - 4), true);
            world.destroyBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1), true);
            world.destroyBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 2), true);
            world.destroyBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 3), true);
            world.destroyBlock(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() - 1), true);
            world.destroyBlock(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() - 2), true);
            world.destroyBlock(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() - 3), true);
            world.destroyBlock(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() - 1), true);
            world.destroyBlock(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() - 2), true);
            world.destroyBlock(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() - 3), true);


            world.setBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ()), Blocks.END_PORTAL_FRAME.getStateDefinition().any().setValue(FACING, Direction.NORTH), 1);
            world.setBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 4), Blocks.END_PORTAL_FRAME.getStateDefinition().any().setValue(FACING, Direction.NORTH), 1);
            world.setBlock(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ()), Blocks.END_PORTAL_FRAME.getStateDefinition().any().setValue(FACING, Direction.NORTH), 1);
            world.setBlock(new BlockPos(pos.getX() + 2, pos.getY(), pos.getZ() - 1), Blocks.END_PORTAL_FRAME.getStateDefinition().any().setValue(FACING, Direction.NORTH), 1);
            world.setBlock(new BlockPos(pos.getX() + 2, pos.getY(), pos.getZ() - 2), Blocks.END_PORTAL_FRAME.getStateDefinition().any().setValue(FACING, Direction.EAST), 1);
            world.setBlock(new BlockPos(pos.getX() + 2, pos.getY(), pos.getZ() - 3), Blocks.END_PORTAL_FRAME.getStateDefinition().any().setValue(FACING, Direction.EAST), 1);
            world.setBlock(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() - 4), Blocks.END_PORTAL_FRAME.getStateDefinition().any().setValue(FACING, Direction.NORTH), 1);
            world.setBlock(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ()), Blocks.END_PORTAL_FRAME.getStateDefinition().any().setValue(FACING, Direction.NORTH), 1);
            world.setBlock(new BlockPos(pos.getX() - 2, pos.getY(), pos.getZ() - 1), Blocks.END_PORTAL_FRAME.getStateDefinition().any().setValue(FACING, Direction.EAST), 1);
            world.setBlock(new BlockPos(pos.getX() - 2, pos.getY(), pos.getZ() - 2), Blocks.END_PORTAL_FRAME.getStateDefinition().any().setValue(FACING, Direction.EAST), 1);
            world.setBlock(new BlockPos(pos.getX() - 2, pos.getY(), pos.getZ() - 3), Blocks.END_PORTAL_FRAME.getStateDefinition().any().setValue(FACING, Direction.EAST), 1);
            world.setBlock(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() - 4), Blocks.END_PORTAL_FRAME.getStateDefinition().any().setValue(FACING, Direction.NORTH), 1);
            world.setBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1), Blocks.END_PORTAL.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 2), Blocks.END_PORTAL.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 3), Blocks.END_PORTAL.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() - 1), Blocks.END_PORTAL.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() - 2), Blocks.END_PORTAL.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() - 3), Blocks.END_PORTAL.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() - 1), Blocks.END_PORTAL.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() - 2), Blocks.END_PORTAL.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() - 3), Blocks.END_PORTAL.defaultBlockState(), 1);
        }

        if (player.getDirection() == Direction.EAST){
            world.destroyBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ()), true);
            world.destroyBlock(new BlockPos(pos.getX() + 4, pos.getY(), pos.getZ()), true);
            world.destroyBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1), true);
            world.destroyBlock(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() + 2), true);
            world.destroyBlock(new BlockPos(pos.getX() + 2, pos.getY(), pos.getZ() + 2), true);
            world.destroyBlock(new BlockPos(pos.getX() + 3, pos.getY(), pos.getZ() + 2), true);
            world.destroyBlock(new BlockPos(pos.getX() + 4, pos.getY(), pos.getZ() + 1), true);
            world.destroyBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1), true);
            world.destroyBlock(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() - 2), true);
            world.destroyBlock(new BlockPos(pos.getX() + 2, pos.getY(), pos.getZ() - 2), true);
            world.destroyBlock(new BlockPos(pos.getX() + 3, pos.getY(), pos.getZ() - 2), true);
            world.destroyBlock(new BlockPos(pos.getX() + 4, pos.getY(), pos.getZ() - 1), true);
            world.destroyBlock(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ()), true);
            world.destroyBlock(new BlockPos(pos.getX() + 2, pos.getY(), pos.getZ()), true);
            world.destroyBlock(new BlockPos(pos.getX() + 3, pos.getY(), pos.getZ()), true);
            world.destroyBlock(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() + 1), true);
            world.destroyBlock(new BlockPos(pos.getX() + 2, pos.getY(), pos.getZ() + 1), true);
            world.destroyBlock(new BlockPos(pos.getX() + 3, pos.getY(), pos.getZ() + 1), true);
            world.destroyBlock(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() - 1), true);
            world.destroyBlock(new BlockPos(pos.getX() + 2, pos.getY(), pos.getZ() - 1), true);
            world.destroyBlock(new BlockPos(pos.getX() + 3, pos.getY(), pos.getZ() - 1), true);


            world.setBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ()), Blocks.END_PORTAL_FRAME.getStateDefinition().any().setValue(FACING, Direction.NORTH), 1);
            world.setBlock(new BlockPos(pos.getX() + 4, pos.getY(), pos.getZ()), Blocks.END_PORTAL_FRAME.getStateDefinition().any().setValue(FACING, Direction.NORTH), 1);
            world.setBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1), Blocks.END_PORTAL_FRAME.getStateDefinition().any().setValue(FACING, Direction.NORTH), 1);
            world.setBlock(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() + 2), Blocks.END_PORTAL_FRAME.getStateDefinition().any().setValue(FACING, Direction.NORTH), 1);
            world.setBlock(new BlockPos(pos.getX() + 2, pos.getY(), pos.getZ() + 2), Blocks.END_PORTAL_FRAME.getStateDefinition().any().setValue(FACING, Direction.EAST), 1);
            world.setBlock(new BlockPos(pos.getX() + 3, pos.getY(), pos.getZ() + 2), Blocks.END_PORTAL_FRAME.getStateDefinition().any().setValue(FACING, Direction.EAST), 1);
            world.setBlock(new BlockPos(pos.getX() + 4, pos.getY(), pos.getZ() + 1), Blocks.END_PORTAL_FRAME.getStateDefinition().any().setValue(FACING, Direction.NORTH), 1);
            world.setBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1), Blocks.END_PORTAL_FRAME.getStateDefinition().any().setValue(FACING, Direction.NORTH), 1);
            world.setBlock(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() - 2), Blocks.END_PORTAL_FRAME.getStateDefinition().any().setValue(FACING, Direction.EAST), 1);
            world.setBlock(new BlockPos(pos.getX() + 2, pos.getY(), pos.getZ() - 2), Blocks.END_PORTAL_FRAME.getStateDefinition().any().setValue(FACING, Direction.EAST), 1);
            world.setBlock(new BlockPos(pos.getX() + 3, pos.getY(), pos.getZ() - 2), Blocks.END_PORTAL_FRAME.getStateDefinition().any().setValue(FACING, Direction.EAST), 1);
            world.setBlock(new BlockPos(pos.getX() + 4, pos.getY(), pos.getZ() - 1), Blocks.END_PORTAL_FRAME.getStateDefinition().any().setValue(FACING, Direction.NORTH), 1);
            world.setBlock(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ()), Blocks.END_PORTAL.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX() + 2, pos.getY(), pos.getZ()), Blocks.END_PORTAL.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX() + 3, pos.getY(), pos.getZ()), Blocks.END_PORTAL.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() + 1), Blocks.END_PORTAL.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX() + 2, pos.getY(), pos.getZ() + 1), Blocks.END_PORTAL.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX() + 3, pos.getY(), pos.getZ() + 1), Blocks.END_PORTAL.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() - 1), Blocks.END_PORTAL.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX() + 2, pos.getY(), pos.getZ() - 1), Blocks.END_PORTAL.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX() + 3, pos.getY(), pos.getZ() - 1), Blocks.END_PORTAL.defaultBlockState(), 1);
        }

        if (player.getDirection() == Direction.WEST){
            world.destroyBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ()), true);
            world.destroyBlock(new BlockPos(pos.getX() - 4, pos.getY(), pos.getZ()), true);
            world.destroyBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1), true);
            world.destroyBlock(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() + 2), true);
            world.destroyBlock(new BlockPos(pos.getX() - 2, pos.getY(), pos.getZ() + 2), true);
            world.destroyBlock(new BlockPos(pos.getX() - 3, pos.getY(), pos.getZ() + 2), true);
            world.destroyBlock(new BlockPos(pos.getX() - 4, pos.getY(), pos.getZ() + 1), true);
            world.destroyBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1), true);
            world.destroyBlock(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() - 2), true);
            world.destroyBlock(new BlockPos(pos.getX() - 2, pos.getY(), pos.getZ() - 2), true);
            world.destroyBlock(new BlockPos(pos.getX() - 3, pos.getY(), pos.getZ() - 2), true);
            world.destroyBlock(new BlockPos(pos.getX() - 4, pos.getY(), pos.getZ() - 1), true);
            world.destroyBlock(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ()), true);
            world.destroyBlock(new BlockPos(pos.getX() - 2, pos.getY(), pos.getZ()), true);
            world.destroyBlock(new BlockPos(pos.getX() - 3, pos.getY(), pos.getZ()), true);
            world.destroyBlock(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() + 1), true);
            world.destroyBlock(new BlockPos(pos.getX() - 2, pos.getY(), pos.getZ() + 1), true);
            world.destroyBlock(new BlockPos(pos.getX() - 3, pos.getY(), pos.getZ() + 1), true);
            world.destroyBlock(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() - 1), true);
            world.destroyBlock(new BlockPos(pos.getX() - 2, pos.getY(), pos.getZ() - 1), true);
            world.destroyBlock(new BlockPos(pos.getX() - 3, pos.getY(), pos.getZ() - 1), true);


            world.setBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ()), Blocks.END_PORTAL_FRAME.getStateDefinition().any().setValue(FACING, Direction.NORTH), 1);
            world.setBlock(new BlockPos(pos.getX() - 4, pos.getY(), pos.getZ()), Blocks.END_PORTAL_FRAME.getStateDefinition().any().setValue(FACING, Direction.NORTH), 1);
            world.setBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1), Blocks.END_PORTAL_FRAME.getStateDefinition().any().setValue(FACING, Direction.NORTH), 1);
            world.setBlock(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() + 2), Blocks.END_PORTAL_FRAME.getStateDefinition().any().setValue(FACING, Direction.NORTH), 1);
            world.setBlock(new BlockPos(pos.getX() - 2, pos.getY(), pos.getZ() + 2), Blocks.END_PORTAL_FRAME.getStateDefinition().any().setValue(FACING, Direction.EAST), 1);
            world.setBlock(new BlockPos(pos.getX() - 3, pos.getY(), pos.getZ() + 2), Blocks.END_PORTAL_FRAME.getStateDefinition().any().setValue(FACING, Direction.EAST), 1);
            world.setBlock(new BlockPos(pos.getX() - 4, pos.getY(), pos.getZ() + 1), Blocks.END_PORTAL_FRAME.getStateDefinition().any().setValue(FACING, Direction.NORTH), 1);
            world.setBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1), Blocks.END_PORTAL_FRAME.getStateDefinition().any().setValue(FACING, Direction.NORTH), 1);
            world.setBlock(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() - 2), Blocks.END_PORTAL_FRAME.getStateDefinition().any().setValue(FACING, Direction.EAST), 1);
            world.setBlock(new BlockPos(pos.getX() - 2, pos.getY(), pos.getZ() - 2), Blocks.END_PORTAL_FRAME.getStateDefinition().any().setValue(FACING, Direction.EAST), 1);
            world.setBlock(new BlockPos(pos.getX() - 3, pos.getY(), pos.getZ() - 2), Blocks.END_PORTAL_FRAME.getStateDefinition().any().setValue(FACING, Direction.EAST), 1);
            world.setBlock(new BlockPos(pos.getX() - 4, pos.getY(), pos.getZ() - 1), Blocks.END_PORTAL_FRAME.getStateDefinition().any().setValue(FACING, Direction.NORTH), 1);
            world.setBlock(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ()), Blocks.END_PORTAL.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX() - 2, pos.getY(), pos.getZ()), Blocks.END_PORTAL.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX() - 3, pos.getY(), pos.getZ()), Blocks.END_PORTAL.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() + 1), Blocks.END_PORTAL.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX() - 2, pos.getY(), pos.getZ() + 1), Blocks.END_PORTAL.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX() - 3, pos.getY(), pos.getZ() + 1), Blocks.END_PORTAL.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() - 1), Blocks.END_PORTAL.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX() - 2, pos.getY(), pos.getZ() - 1), Blocks.END_PORTAL.defaultBlockState(), 1);
            world.setBlock(new BlockPos(pos.getX() - 3, pos.getY(), pos.getZ() - 1), Blocks.END_PORTAL.defaultBlockState(), 1);
        }
    }
}