package com.deflatedpickle.dontspawnthat;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.loading.FMLPaths;

public class Config {
    ForgeConfigSpec spec;

    public static ForgeConfigSpec.BooleanValue spawnSpiders;
    public static ForgeConfigSpec.BooleanValue spawnCaveSpiders;

    public Config() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        spawnSpiders = builder.define("spawnSpiders", false);
        spawnCaveSpiders = builder.define("spawnCaveSpiders", false);

        spec = builder.build();
    }

    public void loadConfig() {
        CommentedFileConfig configData =
                CommentedFileConfig.builder(FMLPaths.CONFIGDIR.get().resolve("dontspawnthat-common.toml"))
                        .sync()
                        .autosave()
                        .writingMode(WritingMode.REPLACE)
                        .build();

        configData.load();
        spec.setConfig(configData);
    }
}
