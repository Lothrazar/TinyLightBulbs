package com.lothrazar.tinylightbulbs;

import com.lothrazar.library.block.BlockFlib;
import com.lothrazar.library.item.BlockItemFlib;
import com.lothrazar.tinylightbulbs.block.BlockBulb;
import com.lothrazar.tinylightbulbs.block.BlockBulbLed;
import com.lothrazar.tinylightbulbs.block.BlockBulbPanel;
import com.lothrazar.tinylightbulbs.block.BlockBulbSolid;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
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
  //
  //  @SubscribeEvent
  //  public static void buildContents(CreativeModeTabEvent.BuildContents event) {
  //    int x = 1;
  //  }

  @SubscribeEvent
  public static void buildContents(CreativeModeTabEvent.Register event) {
    event.registerCreativeModeTab(new ResourceLocation(LightBulbMod.MODID, "example"), builder ->
    // Set name of tab to display
    builder.title(Component.translatable("item_group." + LightBulbMod.MODID + ".example"))
        // Set icon of creative tab
        .icon(() -> new ItemStack(BULB.get()))
        // Add default items to tab
        .displayItems((enabledFlags, populator) -> {
          populator.accept(BULB.get());
          populator.accept(BULB_BLOCK.get());
          populator.accept(LED.get());
          populator.accept(PANEL.get());
          //          populator.accept(BLOCK.get());
        }));
  }

  public static final RegistryObject<Block> BULB_BLOCK = BLOCKS.register("bulb_block", () -> new BlockBulbSolid(Block.Properties.of(Material.BUILDABLE_GLASS)
      .lightLevel(t -> ConfigManager.BLOCK.get()), new BlockFlib.Settings().rotateColour(false)));
  public static final RegistryObject<Block> BULB = BLOCKS.register("bulb", () -> new BlockBulb(Block.Properties.of(Material.BUILDABLE_GLASS)
      .lightLevel(t -> ConfigManager.BULB.get()), new BlockFlib.Settings()));
  public static final RegistryObject<Block> LED = BLOCKS.register("led", () -> new BlockBulbLed(Block.Properties.of(Material.BUILDABLE_GLASS)
      .lightLevel(t -> ConfigManager.LED.get()), new BlockFlib.Settings()));
  public static final RegistryObject<Block> PANEL = BLOCKS.register("panel", () -> new BlockBulbPanel(Block.Properties.of(Material.BUILDABLE_GLASS)
      .lightLevel(t -> ConfigManager.PANEL.get()), new BlockFlib.Settings()));
  static {
    ITEMS.register("bulb_block", () -> new BlockItemFlib(BULB_BLOCK.get(), new Item.Properties()));
    ITEMS.register("bulb", () -> new BlockItemFlib(BULB.get(), new Item.Properties()));
    ITEMS.register("led", () -> new BlockItemFlib(LED.get(), new Item.Properties()));
    ITEMS.register("panel", () -> new BlockItemFlib(PANEL.get(), new Item.Properties()));
  }
}
