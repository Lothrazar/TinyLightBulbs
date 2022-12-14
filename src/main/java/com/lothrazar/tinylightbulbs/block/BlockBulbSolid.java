package com.lothrazar.tinylightbulbs.block;

import com.lothrazar.library.block.BlockFlib;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public class BlockBulbSolid extends BlockFlib {

  public BlockBulbSolid(Properties p, BlockFlib.Settings s) {
    super(p.noOcclusion().strength(0.4F).sound(SoundType.GLASS), s);
  }

  @Override
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
    super.createBlockStateDefinition(builder.add(BlockFlib.COLOUR));
  }

  @Override
  public BlockState getStateForPlacement(BlockPlaceContext ctx) {
    BlockState state = this.defaultBlockState();
    if (ctx.getHand() == InteractionHand.MAIN_HAND && ctx.getPlayer() != null && ctx.getPlayer().getItemInHand(InteractionHand.OFF_HAND).getItem() instanceof DyeItem dye) {
      state = state.setValue(COLOUR, dye.getDyeColor());
    }
    return state;
  }

  @SuppressWarnings("deprecation")
  @Override
  public boolean skipRendering(BlockState state, BlockState otherState, Direction face) {
    return otherState.is(this) ? true : super.skipRendering(state, otherState, face);
  }
}
