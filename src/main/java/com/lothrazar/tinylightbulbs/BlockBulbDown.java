package com.lothrazar.tinylightbulbs;

import com.lothrazar.library.block.BlockFlib;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlockBulbDown extends BlockBulb {

  public BlockBulbDown(Properties prop, BlockFlib.Settings s) {
    super(prop, s);
  }

  @Override
  public boolean canSurvive(BlockState bs, LevelReader level, BlockPos pos) {
    return canSupportRigidBlock(level, pos.relative(Direction.UP)); // TODO: move to flib later
  }

  @Override
  public Item asItem() { //override to have same item as the regular bulb
    return ModRegistry.BULB_DYE.get().asItem();
  }

  @Override
  public VoxelShape getShape(BlockState st, BlockGetter w, BlockPos pos, CollisionContext c) {
    return CEILING_AABB;
  }
}
