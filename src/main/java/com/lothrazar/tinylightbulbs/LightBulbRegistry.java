package com.lothrazar.tinylightbulbs;

import com.lothrazar.library.block.BlockFlib;
import com.lothrazar.library.item.BlockItemFlib;
import com.lothrazar.tinylightbulbs.block.BlockBulb;
import com.lothrazar.tinylightbulbs.block.BlockBulbLed;
import com.lothrazar.tinylightbulbs.block.BlockBulbPanel;
import com.lothrazar.tinylightbulbs.block.BlockBulbSolid;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class LightBulbRegistry {

  public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, LightBulbMod.MODID);
  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LightBulbMod.MODID);
  private static final CreativeModeTab TAB = new CreativeModeTab(LightBulbMod.MODID) {

    @Override
    public ItemStack makeIcon() {
      return new ItemStack(BULB.get());
    }
  };
  public static final RegistryObject<Block> BULB_BLOCK = BLOCKS.register("bulb_block", () -> new BlockBulbSolid(Block.Properties.of(Material.BUILDABLE_GLASS)
      .lightLevel(t -> ConfigManager.BLOCK.get()), new BlockFlib.Settings().rotateColour(false)));
  public static final RegistryObject<Block> BULB = BLOCKS.register("bulb", () -> new BlockBulb(Block.Properties.of(Material.BUILDABLE_GLASS)
      .lightLevel(t -> ConfigManager.BULB.get()), new BlockFlib.Settings()));
  public static final RegistryObject<Block> LED = BLOCKS.register("led", () -> new BlockBulbLed(Block.Properties.of(Material.BUILDABLE_GLASS)
      .lightLevel(t -> ConfigManager.LED.get()), new BlockFlib.Settings()));
  public static final RegistryObject<Block> PANEL = BLOCKS.register("panel", () -> new BlockBulbPanel(Block.Properties.of(Material.BUILDABLE_GLASS)
      .lightLevel(t -> ConfigManager.PANEL.get()), new BlockFlib.Settings()));
  static {
    ITEMS.register("bulb_block", () -> new BlockItemFlib(BULB_BLOCK.get(), new Item.Properties().tab(TAB)));
    ITEMS.register("bulb", () -> new BlockItemFlib(BULB.get(), new Item.Properties().tab(TAB)));
    ITEMS.register("led", () -> new BlockItemFlib(LED.get(), new Item.Properties().tab(TAB)));
    ITEMS.register("panel", () -> new BlockItemFlib(PANEL.get(), new Item.Properties().tab(TAB)));
  }
}
