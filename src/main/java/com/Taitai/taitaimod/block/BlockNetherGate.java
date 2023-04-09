package com.Taitai.taitaimod.block;

import com.Taitai.taitaimod.regi.TaitaiNeedsMethod;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class BlockNetherGate extends Block {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    protected static final VoxelShape X_AXIS_AABB = Block.box(3.0D, 0.0D, 7.0D, 13.0D, 10.0D, 9.0D);
    protected static final VoxelShape Z_AXIS_AABB = Block.box(7.0D, 0.0D, 3.0D, 9.0D, 10.0D, 13.0D);


    public BlockNetherGate() {
        super(Properties.of(Material.METAL).strength(0.3F).sound(SoundType.GLASS).harvestTool(ToolType.PICKAXE)
                .requiresCorrectToolForDrops().harvestLevel(3).noOcclusion());
        this.setRegistryName("nether_gate");

        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType use(BlockState p_225533_1_, World p_225533_2_, BlockPos p_225533_3_, PlayerEntity p_225533_4_, Hand p_225533_5_, BlockRayTraceResult p_225533_6_) {
        if (p_225533_4_.isShiftKeyDown()){
            p_225533_2_.removeBlock(p_225533_3_, false);
            p_225533_2_.playSound(null, p_225533_3_.getX(), p_225533_3_.getY(), p_225533_3_.getZ(), SoundEvents.GENERIC_EXPLODE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            TaitaiNeedsMethod.netherGate(p_225533_2_, p_225533_3_, p_225533_4_);
        }
        return super.use(p_225533_1_, p_225533_2_, p_225533_3_, p_225533_4_, p_225533_5_, p_225533_6_);
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState mirror(BlockState p_185471_1_, Mirror p_185471_2_) {
        return p_185471_1_.rotate(p_185471_2_.getRotation(p_185471_1_.getValue(FACING)));
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState rotate(BlockState p_185499_1_, Rotation p_185499_2_) {
        return p_185499_1_.setValue(FACING, p_185499_2_.rotate(p_185499_1_.getValue(FACING)));
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext p_196258_1_) {
        return this.defaultBlockState().setValue(FACING, p_196258_1_.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(FACING);
    }
    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        switch(p_220053_1_.getValue(FACING)) {
            case EAST :
            case WEST :
                return Z_AXIS_AABB;
            case NORTH :
            case SOUTH :
            default:
                return X_AXIS_AABB;
        }
    }
    @SuppressWarnings("deprecation")
    @Override
    public BlockState updateShape(BlockState p_196271_1_, Direction p_196271_2_, BlockState p_196271_3_, IWorld p_196271_4_, BlockPos p_196271_5_, BlockPos p_196271_6_) {
        return super.updateShape(p_196271_1_, p_196271_2_, p_196271_3_, p_196271_4_, p_196271_5_, p_196271_6_);
    }
}
