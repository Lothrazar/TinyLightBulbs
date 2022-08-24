package com.lothrazar.tinylightbulbs;

import com.lothrazar.library.block.BlockFlib;
import com.lothrazar.library.item.BlockItemFlib;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModRegistry {

  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ModMain.MODID);
  public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ModMain.MODID);
  //  public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, ModMain.MODID);
  //
  public static final RegistryObject<Block> BULB = BLOCKS.register("bulb", () -> new BlockBulb(Block.Properties.of(Material.BUILDABLE_GLASS)
      .lightLevel(t -> 15), new BlockFlib.Settings()));
  public static final RegistryObject<Block> BULB_POWERED = BLOCKS.register("bulb_powered", () -> new BlockBulbPowered(Block.Properties.of(Material.BUILDABLE_GLASS)
      .lightLevel(t -> t.getValue(BlockFlib.LIT) ? 15 : 0), new BlockFlib.Settings()));
  static {
    ITEMS.register("bulb", () -> new BlockItemFlib(BULB.get(), new Item.Properties()));
    ITEMS.register("bulb_powered", () -> new BlockItemFlib(BULB_POWERED.get(), new Item.Properties()));
  }
}
