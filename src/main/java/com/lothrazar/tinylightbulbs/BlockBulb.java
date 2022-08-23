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

public class BlockBulb extends BlockWaterlogFlib {

  protected static final VoxelShape CEILING_AABB = Block.box(6.0D, 8.0D, 6.0D, 10.0D, 16.0D, 10.0D);
  protected static final VoxelShape FLOOR_AABB = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 8.0D, 10.0D);
  //  protected static final VoxelShape NORTH_AABB = Block.box(5.0D, 6.0D, 14.0D, 11.0D, 10.0D, 16.0D);
  //  protected static final VoxelShape SOUTH_AABB = Block.box(5.0D, 6.0D, 0.0D, 11.0D, 10.0D, 2.0D);
  //  protected static final VoxelShape WEST_AABB = Block.box(14.0D, 6.0D, 5.0D, 16.0D, 10.0D, 11.0D);
  //  protected static final VoxelShape EAST_AABB = Block.box(0.0D, 6.0D, 5.0D, 2.0D, 10.0D, 11.0D);

  public BlockBulb(Properties prop, BlockFlib.Settings s) {
    super(
        prop.noOcclusion()
            .lightLevel(t -> 15)
            .strength(1.5F),
        s.tooltip()
            .rotateColour(false)
    // .litWhenPowered()
    );
    //    this.registerDefaultState(this.defaultBlockState().setValue(LIT, Boolean.valueOf(true)));
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
  public VoxelShape getShape(BlockState p_51104_, BlockGetter p_51105_, BlockPos p_51106_, CollisionContext p_51107_) {
    return FLOOR_AABB;
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
