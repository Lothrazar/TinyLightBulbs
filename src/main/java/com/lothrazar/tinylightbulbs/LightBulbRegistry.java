package com.lothrazar.tinylightbulbs;

import com.lothrazar.library.block.BlockFlib;
import com.lothrazar.library.item.BlockItemFlib;
import com.lothrazar.tinylightbulbs.block.BlockBulb;
import com.lothrazar.tinylightbulbs.block.BlockBulbLed;
import com.lothrazar.tinylightbulbs.block.BlockBulbPanel;
import com.lothrazar.tinylightbulbs.block.BlockBulbSolid;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class LightBulbRegistry {

  public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, LightBulbMod.MODID);
  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LightBulbMod.MODID);
  private static final ResourceKey<CreativeModeTab> TAB = ResourceKey.create(Registries.CREATIVE_MODE_TAB, new ResourceLocation(LightBulbMod.MODID, "tab"));

  @SubscribeEvent
  public static void onCreativeModeTabRegister(RegisterEvent event) {
    event.register(Registries.CREATIVE_MODE_TAB, helper -> {
      helper.register(TAB, CreativeModeTab.builder().icon(() -> new ItemStack(BULB.get()))
          .title(Component.translatable("itemGroup." + LightBulbMod.MODID))
          .displayItems((enabledFlags, populator) -> {
            for (RegistryObject<Item> entry : ITEMS.getEntries()) {
              populator.accept(entry.get());
            }
          }).build());
    });
  }

  public static final RegistryObject<Block> BULB_BLOCK = BLOCKS.register("bulb_block", () -> new BlockBulbSolid(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT)
      .lightLevel(t -> ConfigRegistryLight.BLOCK.get()), new BlockFlib.Settings().rotateColour(false)));
  public static final RegistryObject<Block> BULB = BLOCKS.register("bulb", () -> new BlockBulb(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT)
      .lightLevel(t -> ConfigRegistryLight.BULB.get()), new BlockFlib.Settings()));
  public static final RegistryObject<Block> LED = BLOCKS.register("led", () -> new BlockBulbLed(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT)
      .lightLevel(t -> ConfigRegistryLight.LED.get()), new BlockFlib.Settings()));
  public static final RegistryObject<Block> PANEL = BLOCKS.register("panel", () -> new BlockBulbPanel(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT)
      .lightLevel(t -> ConfigRegistryLight.PANEL.get()), new BlockFlib.Settings()));
  static {
    ITEMS.register("bulb_block", () -> new BlockItemFlib(BULB_BLOCK.get(), new Item.Properties()));
    ITEMS.register("bulb", () -> new BlockItemFlib(BULB.get(), new Item.Properties()));
    ITEMS.register("led", () -> new BlockItemFlib(LED.get(), new Item.Properties()));
    ITEMS.register("panel", () -> new BlockItemFlib(PANEL.get(), new Item.Properties()));
  }
}
