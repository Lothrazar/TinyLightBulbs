package com.lothrazar.tinylightbulbs.block;

import com.lothrazar.library.block.BlockFlib;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public class BlockBulbPowered extends BlockBulb {

  public BlockBulbPowered(Properties prop, BlockFlib.Settings s) {
    super(prop, s.litWhenPowered());
  }

  @Override
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
    super.createBlockStateDefinition(builder.add(LIT));
  }
}
