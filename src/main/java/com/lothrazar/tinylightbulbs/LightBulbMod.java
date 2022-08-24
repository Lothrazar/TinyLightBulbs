package com.lothrazar.tinylightbulbs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(LightBulbMod.MODID)
public class LightBulbMod {

  public static final String MODID = "tinylightbulbs";
  public static final Logger LOGGER = LogManager.getLogger();

  public LightBulbMod() {
    IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
    LightBulbRegistry.BLOCKS.register(eventBus);
    LightBulbRegistry.ITEMS.register(eventBus);
    //    ModRegistry.BLOCK_ENTITIES.register(eventBus);
    //    ConfigManager.setup();
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupClient);
  }

  private void setup(final FMLCommonSetupEvent event) {}

  private void setupClient(final FMLClientSetupEvent event) {
    RenderType c = RenderType.translucent();
    ItemBlockRenderTypes.setRenderLayer(LightBulbRegistry.BULB.get(), c);
    ItemBlockRenderTypes.setRenderLayer(LightBulbRegistry.BULB_POWERED.get(), c);
    ItemBlockRenderTypes.setRenderLayer(LightBulbRegistry.PANEL.get(), c);
  }
  //LIGHT BULBS non dyeable BASED ON 
  //-glowstone
  //-FROG lights
  //could add to blockstate extra thing when its lit. but it looks weird
}
