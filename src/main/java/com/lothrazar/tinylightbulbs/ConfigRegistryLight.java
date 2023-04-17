package com.lothrazar.tinylightbulbs;

import com.lothrazar.library.config.ConfigTemplate;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class ConfigRegistryLight extends ConfigTemplate {

  private static final ForgeConfigSpec CONFIG;
  public static IntValue LED;
  public static IntValue BULB;
  public static IntValue BLOCK;
  public static IntValue PANEL;
  public static BooleanValue SHIMMER;
  static {
    final ForgeConfigSpec.Builder BUILDER = builder();
    BUILDER.comment("General settings.  All configs require game restart")
        .comment("Also, to enable colored lighting shimmer effects, ")
        .push(LightBulbMod.MODID);
    SHIMMER = BUILDER.comment("This mod is an optional dependency, light bulbs are just fine without it.  if true and installed, allows the shimmer Colored Light rendering to affect light bulbs.  "
        + "Default white color can be very strong but try other dyes.  Either uninstall the mod or set this false to disable it (requires client restart)"
        + "  https://www.curseforge.com/minecraft/mc-mods/shimmer").define("shimmer.enabled", true);
    LED = BUILDER.comment("Light level of the LED bulbs").defineInRange("light.led", 14, 1, 15);
    BULB = BUILDER.comment("Light level of the regular bulbs").defineInRange("light.bulb", 15, 1, 15);
    BLOCK = BUILDER.comment("Light level of the Block bulbs").defineInRange("light.block", 15, 1, 15);
    PANEL = BUILDER.comment("Light level of the Panel bulbs").defineInRange("light.panel", 14, 1, 15);
    BUILDER.pop(); // one pop for every push
    CONFIG = BUILDER.build();
  }

  public ConfigRegistryLight() {
    CONFIG.setConfig(setup(LightBulbMod.MODID));
  }
}
