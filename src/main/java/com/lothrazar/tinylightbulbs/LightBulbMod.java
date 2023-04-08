package com.lothrazar.tinylightbulbs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(LightBulbMod.MODID)
public class LightBulbMod {

  public static final String MODID = "tinylightbulbs";
  public static final Logger LOGGER = LogManager.getLogger();

  public LightBulbMod() {
    IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
    LightBulbRegistry.BLOCKS.register(eventBus);
    LightBulbRegistry.ITEMS.register(eventBus);
    ConfigManager.setup();
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupClient);
  }

  private void setupClient(final FMLClientSetupEvent event) {
    event.enqueueWork(() -> {
      if (ConfigManager.SHIMMER.get() && ModList.get().isLoaded("shimmer")) {
        LightWrapper.shimmer();
      }
    });
  }
}
