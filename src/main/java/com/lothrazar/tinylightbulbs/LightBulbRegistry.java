package com.lothrazar.tinylightbulbs;

import com.lothrazar.library.block.BlockFlib;
import com.lothrazar.library.item.BlockItemFlib;
import com.lothrazar.library.registry.RegistryFactory;
import com.lothrazar.tinylightbulbs.block.BlockBulb;
import com.lothrazar.tinylightbulbs.block.BlockBulbLed;
import com.lothrazar.tinylightbulbs.block.BlockBulbPanel;
import com.lothrazar.tinylightbulbs.block.BlockBulbSolid;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class LightBulbRegistry {

  public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, LightBulbMod.MODID);
  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LightBulbMod.MODID);

  @SubscribeEvent
  public static void buildContents(CreativeModeTabEvent.Register event) {
    RegistryFactory.buildTab(event, LightBulbMod.MODID, BULB.get().asItem(), ITEMS);
  }

  public static final RegistryObject<Block> BULB_BLOCK = BLOCKS.register("bulb_block", () -> new BlockBulbSolid(Block.Properties.of(Material.BUILDABLE_GLASS)
      .lightLevel(t -> ConfigRegistryLight.BLOCK.get()), new BlockFlib.Settings().rotateColour(false)));
  public static final RegistryObject<Block> BULB = BLOCKS.register("bulb", () -> new BlockBulb(Block.Properties.of(Material.BUILDABLE_GLASS)
      .lightLevel(t -> ConfigRegistryLight.BULB.get()), new BlockFlib.Settings()));
  public static final RegistryObject<Block> LED = BLOCKS.register("led", () -> new BlockBulbLed(Block.Properties.of(Material.BUILDABLE_GLASS)
      .lightLevel(t -> ConfigRegistryLight.LED.get()), new BlockFlib.Settings()));
  public static final RegistryObject<Block> PANEL = BLOCKS.register("panel", () -> new BlockBulbPanel(Block.Properties.of(Material.BUILDABLE_GLASS)
      .lightLevel(t -> ConfigRegistryLight.PANEL.get()), new BlockFlib.Settings()));
  static {
    ITEMS.register("bulb_block", () -> new BlockItemFlib(BULB_BLOCK.get(), new Item.Properties()));
    ITEMS.register("bulb", () -> new BlockItemFlib(BULB.get(), new Item.Properties()));
    ITEMS.register("led", () -> new BlockItemFlib(LED.get(), new Item.Properties()));
    ITEMS.register("panel", () -> new BlockItemFlib(PANEL.get(), new Item.Properties()));
  }
}
