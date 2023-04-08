package com.lothrazar.tinylightbulbs;

import com.lothrazar.library.block.BlockFlib;
//import com.lowdragmc.shimmer.client.light.ColorPointLight;
//import com.lowdragmc.shimmer.client.light.LightManager;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.state.BlockState;

public class LightWrapper {

  public static void shimmer() {
    //    LightManager.INSTANCE.registerBlockLight(LightBulbRegistry.BULB.get(), (state, pos) -> {
    //      if (state.hasProperty(BlockFlib.COLOUR)) {
    //        int col = colFromState(state);
    //        return new ColorPointLight.Template(ConfigManager.BULB.get(), col);
    //      }
    //      return null;
    //    });
    //    LightManager.INSTANCE.registerBlockLight(LightBulbRegistry.BULB_BLOCK.get(), (state, pos) -> {
    //      if (state.hasProperty(BlockFlib.COLOUR)) {
    //        int col = colFromState(state);
    //        return new ColorPointLight.Template(ConfigManager.BLOCK.get(), col);
    //      }
    //      return null;
    //    });
    //    LightManager.INSTANCE.registerBlockLight(LightBulbRegistry.LED.get(), (state, pos) -> {
    //      if (state.hasProperty(BlockFlib.COLOUR)) {
    //        int col = colFromState(state);
    //        return new ColorPointLight.Template(ConfigManager.LED.get(), col);
    //      }
    //      return null;
    //    });
    //    LightManager.INSTANCE.registerBlockLight(LightBulbRegistry.PANEL.get(), (state, pos) -> {
    //      if (state.hasProperty(BlockFlib.COLOUR)) {
    //        int col = colFromState(state);
    //        return new ColorPointLight.Template(ConfigManager.PANEL.get(), col);
    //      }
    //      return null;
    //    });
  }

  private static int colFromState(BlockState state) {
    DyeColor colour = state.getValue(BlockFlib.COLOUR);
    int col = colour.getMaterialColor().col | 0xFF000000;
    return col;
  }
}
