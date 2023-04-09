package com.Taitai.taitaimod.block;

import com.Taitai.taitaimod.regi.TaitaiNeedsMethod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class BlockEndGate extends Block {

    private static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D);

    public BlockEndGate() {
        super(Properties.of(Material.METAL).strength(0.3F).sound(SoundType.GLASS).harvestTool(ToolType.PICKAXE)
                .requiresCorrectToolForDrops().harvestLevel(3).noOcclusion());
        this.setRegistryName("end_gate");
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResultType use(BlockState p_225533_1_, World p_225533_2_, BlockPos p_225533_3_, PlayerEntity p_225533_4_, Hand p_225533_5_, BlockRayTraceResult p_225533_6_) {
        if (p_225533_4_.isShiftKeyDown()) {
            p_225533_2_.removeBlock(p_225533_3_, false);
            p_225533_2_.playSound(null, p_225533_3_.getX(), p_225533_3_.getY(), p_225533_3_.getZ(), SoundEvents.GENERIC_EXPLODE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            TaitaiNeedsMethod.endGate(p_225533_2_, p_225533_3_, p_225533_4_);
        }
        return super.use(p_225533_1_, p_225533_2_, p_225533_3_, p_225533_4_, p_225533_5_, p_225533_6_);
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        return SHAPE;
    }
}
