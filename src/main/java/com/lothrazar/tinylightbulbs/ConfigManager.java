package com.lothrazar.tinylightbulbs;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.fml.loading.FMLPaths;

public class ConfigManager {

  private static final ForgeConfigSpec.Builder CFG = new ForgeConfigSpec.Builder();
  private static ForgeConfigSpec COMMON_CONFIG;
  public static IntValue LED;
  public static IntValue BULB;
  public static IntValue BLOCK;
  public static IntValue PANEL;
  public static BooleanValue SHIMMER;
  static {
    initConfig();
  }

  private static void initConfig() {
    CFG.comment("General settings.  All configs require game restart")
        .comment("Also, to enable colored lighting shimmer effects, ")
        .push(LightBulbMod.MODID);
    SHIMMER = CFG.comment("This mod is an optional dependency, light bulbs are just fine without it.  if true and installed, allows the shimmer Colored Light rendering to affect light bulbs.  "
        + "Default white color can be very strong but try other dyes.  Either uninstall the mod or set this false to disable it (requires client restart)"
        + "  https://www.curseforge.com/minecraft/mc-mods/shimmer").define("shimmer.enabled", true);
    LED = CFG.comment("Light level of the LED bulbs").defineInRange("light.led", 14, 1, 15);
    BULB = CFG.comment("Light level of the regular bulbs").defineInRange("light.bulb", 15, 1, 15);
    BLOCK = CFG.comment("Light level of the Block bulbs").defineInRange("light.block", 15, 1, 15);
    PANEL = CFG.comment("Light level of the Panel bulbs").defineInRange("light.panel", 14, 1, 15);
    CFG.pop(); // one pop for every push
    COMMON_CONFIG = CFG.build();
  }

  public static void setup() {
    final CommentedFileConfig configData = CommentedFileConfig.builder(FMLPaths.CONFIGDIR.get().resolve(LightBulbMod.MODID + ".toml"))
        .sync()
        .autosave()
        .writingMode(WritingMode.REPLACE)
        .build();
    configData.load();
    COMMON_CONFIG.setConfig(configData);
  }
}
