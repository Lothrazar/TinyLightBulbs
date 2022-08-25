package com.lothrazar.tinylightbulbs;

import com.lothrazar.library.block.BlockFlib;
import com.lowdragmc.shimmer.client.light.ColorPointLight;
import com.lowdragmc.shimmer.client.light.LightManager;
import net.minecraft.world.item.DyeColor;

public class LightWrapper {

  public static void shimmer() {
    LightManager.INSTANCE.registerBlockLight(LightBulbRegistry.BULB.get(), (state, pos) -> {
      if (state.hasProperty(BlockFlib.COLOUR)) {
        DyeColor colour = state.getValue(BlockFlib.COLOUR);
        int col = colour.getMaterialColor().col | 0xff000000;
        return new ColorPointLight.Template(15, col);
      }
      return null;
    });
  }
}
