package com.lothrazar.tinylightbulbs.block;

import com.lothrazar.library.block.BlockFlib;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlockBulbSolid extends BlockFlib {

  public BlockBulbSolid(Properties p, BlockFlib.Settings s) {
    super(p.noOcclusion(), s);
  }

  @Override
  public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
    return Shapes.empty();
  }

  @Override
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
    super.createBlockStateDefinition(builder.add(BlockFlib.COLOUR));
  }

  @Override
  public boolean skipRendering(BlockState state, BlockState otherState, Direction face) {
    return otherState.is(this) ? true : super.skipRendering(state, otherState, face);
  }
}
