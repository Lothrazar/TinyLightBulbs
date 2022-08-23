package com.lothrazar.tinylightbulbs;

import javax.annotation.Nullable;
import com.lothrazar.library.block.BlockFlib;
import com.lothrazar.library.item.BlockItemFlib;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModRegistry {

  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ModMain.MODID);
  public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ModMain.MODID);
  public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, ModMain.MODID);
  // 
  public static final RegistryObject<Block> BULB_DOWN = BLOCKS.register("bulb_down", () -> new BlockBulbDown(Block.Properties.of(Material.BUILDABLE_GLASS),
      new BlockFlib.Settings()));
  public static final RegistryObject<Block> BULB_DYE = BLOCKS.register("bulb_dye", () -> new BlockBulb(Block.Properties.of(Material.BUILDABLE_GLASS), new BlockFlib.Settings()));
  //  public static final RegistryObject<Block> BULB_POWERED = BLOCKS.register("bulb_powered", () -> new BlockBulb(Block.Properties.of(Material.BUILDABLE_GLASS),
  //      new BlockFlib.Settings()
  //          .litWhenPowered()) {
  //
  //    @Override
  //    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
  //      return this.defaultBlockState().setValue(LIT, Boolean.valueOf(ctx.getLevel().hasNeighborSignal(ctx.getClickedPos())));
  //    }
  //  });
  static {
    ITEMS.register("bulb_dye", () -> new BlockItemFlib(BULB_DYE.get(), new Item.Properties()) {

      @Override
      @Nullable
      protected BlockState getPlacementState(BlockPlaceContext context) {
        BlockState blockstate = this.getBlock().getStateForPlacement(context);
        if (context.getClickedFace() == Direction.DOWN) {
          //swap block for down version
          blockstate = BULB_DOWN.get().getStateForPlacement(context);
        }
        return blockstate != null && this.canPlace(context, blockstate) ? blockstate : null;
      }
    });
  }
}
