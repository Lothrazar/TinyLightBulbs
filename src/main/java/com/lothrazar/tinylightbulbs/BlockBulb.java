package com.lothrazar.tinylightbulbs;

import com.lothrazar.library.block.BlockFlib;
import com.lothrazar.library.block.BlockWaterlogFlib;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("deprecation")
public class BlockBulb extends BlockWaterlogFlib {

  private static final double SIZE = 6.0D;
  private static final double HGT = 8.0D;
  private static final VoxelShape CEILING_AABB = Block.box(SIZE, HGT, SIZE, 16 - SIZE, 16.0D, 16 - SIZE);
  private static final VoxelShape FLOOR_AABB = Block.box(SIZE, 0.0D, SIZE, 16 - SIZE, HGT, 16 - SIZE);
  private static final VoxelShape SOUTH_AABB = Block.box(SIZE, SIZE, 16 - HGT, 16 - SIZE, 16 - SIZE, 16.0D);
  private static final VoxelShape NORTH_AABB = Block.box(SIZE, SIZE, 0.0D, 16 - SIZE, 16 - SIZE, HGT);
  private static final VoxelShape EAST_AABB = Block.box(16 - HGT, SIZE, SIZE, 16.0D, 16 - SIZE, 16 - SIZE);
  private static final VoxelShape WEST_AABB = Block.box(0.0D, SIZE, SIZE, HGT, 16 - SIZE, 16 - SIZE);

  public BlockBulb(Properties prop, BlockFlib.Settings s) {
    super(
        prop.noOcclusion()
            .strength(1.5F),
        s.tooltip()
            .facingAttachment()
            .rotateColour(false)
    // .litWhenPowered()
    );
    //    this.registerDefaultState(this.defaultBlockState().setValue(LIT, Boolean.valueOf(true)));
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
    return this.defaultBlockState().setValue(BlockStateProperties.FACING, ctx.getClickedFace().getOpposite());
  }

  @Override
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
    super.createBlockStateDefinition(builder.add(COLOUR, BlockStateProperties.FACING));
  }

  @Override
  public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
    return Shapes.empty();
  }

  @Override
  public boolean canSurvive(BlockState bs, LevelReader level, BlockPos pos) {
    //IF FRAGILE != NULL// TODO: move to flib later
    return canSupportRigidBlock(level, pos.relative(bs.getValue(BlockStateProperties.FACING)));
  }

  @Override
  public BlockState updateShape(BlockState bs, Direction face, BlockState bsOp, LevelAccessor level, BlockPos pos, BlockPos posOther) {
    //IF FRAGILE != NULL// TODO: move to flib later
    return !bs.canSurvive(level, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(bs, face, bsOp, level, pos, posOther);
  }
}
