package com.lothrazar.tinylightbulbs.block;

import com.lothrazar.library.block.BlockFlib;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlockBulbPanel extends BlockBulb {

  private static final double SIZE = 0;
  private static final double HGT = 0.3D;
  private static final VoxelShape CEILING_AABB = Block.box(SIZE, HGT, SIZE, 16 - SIZE, 16.0D, 16 - SIZE);
  private static final VoxelShape FLOOR_AABB = Block.box(SIZE, 0.0D, SIZE, 16 - SIZE, HGT, 16 - SIZE);
  private static final VoxelShape SOUTH_AABB = Block.box(SIZE, SIZE, 16 - HGT, 16 - SIZE, 16 - SIZE, 16.0D);
  private static final VoxelShape NORTH_AABB = Block.box(SIZE, SIZE, 0.0D, 16 - SIZE, 16 - SIZE, HGT);
  private static final VoxelShape EAST_AABB = Block.box(16 - HGT, SIZE, SIZE, 16.0D, 16 - SIZE, 16 - SIZE);
  private static final VoxelShape WEST_AABB = Block.box(0.0D, SIZE, SIZE, HGT, 16 - SIZE, 16 - SIZE);

  public BlockBulbPanel(Properties prop, BlockFlib.Settings s) {
    super(prop, s);
  }

  @Override
  public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext ctx) {
    Direction face = state.getValue(BlockStateProperties.FACING);
    switch (face) {
      case DOWN:
        return FLOOR_AABB;
      case UP:
        return CEILING_AABB;
      case NORTH:
        return NORTH_AABB;
      case EAST:
        return EAST_AABB;
      case SOUTH:
        return SOUTH_AABB;
      case WEST:
        return WEST_AABB;
    }
    return super.getShape(state, world, pos, ctx);
  }
}
