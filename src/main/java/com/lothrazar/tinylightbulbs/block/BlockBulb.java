package com.lothrazar.tinylightbulbs.block;

import com.lothrazar.library.block.BlockFlib;
import com.lothrazar.library.block.BlockWaterlogFlib;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("deprecation")
public class BlockBulb extends BlockWaterlogFlib {

  private static final double SIZE = 6.0D;
  private static final double HGT = 7.5D;
  private static final VoxelShape CEILING_AABB = Block.box(SIZE, 16 - HGT, SIZE, 16 - SIZE, 16, 16 - SIZE);
  private static final VoxelShape FLOOR_AABB = Block.box(SIZE, 0, SIZE, 16 - SIZE, HGT, 16 - SIZE);
  private static final VoxelShape SOUTH_AABB = Block.box(SIZE, SIZE, 16 - HGT, 16 - SIZE, 16 - SIZE, 16);
  private static final VoxelShape NORTH_AABB = Block.box(SIZE, SIZE, 0, 16 - SIZE, 16 - SIZE, HGT);
  private static final VoxelShape EAST_AABB = Block.box(16 - HGT, SIZE, SIZE, 16, 16 - SIZE, 16 - SIZE);
  private static final VoxelShape WEST_AABB = Block.box(0, SIZE, SIZE, HGT, 16 - SIZE, 16 - SIZE);

  public BlockBulb(Properties prop, BlockFlib.Settings s) {
    super(
        prop.strength(0.3F).sound(SoundType.GLASS)
            .noOcclusion().isValidSpawn(BlockFlib::never).isRedstoneConductor(BlockFlib::never).isSuffocating(BlockFlib::never).isViewBlocking(BlockFlib::never),
        s.tooltip()
            .facingAttachment()
            .rotateColour(false)
    // .litWhenPowered()
    );
  }

  @Override
  public VoxelShape getVisualShape(BlockState p_48735_, BlockGetter p_48736_, BlockPos p_48737_, CollisionContext p_48738_) {
    return Shapes.empty();
  }

  @Override
  public float getShadeBrightness(BlockState p_48731_, BlockGetter p_48732_, BlockPos p_48733_) {
    return 1.0F;
  }

  @Override
  public boolean propagatesSkylightDown(BlockState p_48740_, BlockGetter p_48741_, BlockPos p_48742_) {
    return true;
  }
  //end abstract
  //Halftransparent block 

  @Override
  public boolean skipRendering(BlockState state, BlockState otherState, Direction face) {
    return otherState.is(this) ? true : super.skipRendering(state, otherState, face);
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

  @Override
  public BlockState getStateForPlacement(BlockPlaceContext ctx) {
    BlockState state = this.defaultBlockState().setValue(BlockStateProperties.FACING, ctx.getClickedFace().getOpposite());
    if (ctx.getHand() == InteractionHand.MAIN_HAND && ctx.getPlayer() != null && ctx.getPlayer().getItemInHand(InteractionHand.OFF_HAND).getItem() instanceof DyeItem dye) {
      state = state.setValue(COLOUR, dye.getDyeColor());
    }
    return state;
  }

  @Override
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
    super.createBlockStateDefinition(builder.add(COLOUR, BlockStateProperties.FACING));
  }

  @Override
  public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
    return Shapes.empty();
  }
}
