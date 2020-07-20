package com.deflatedpickle.dontspawnthat;

import net.minecraft.entity.monster.CaveSpiderEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("dontspawnthat")
public class DontSpawnThat {
    private static final Logger LOGGER = LogManager.getLogger();

    public DontSpawnThat() {
        MinecraftForge.EVENT_BUS.register(this);

        Config config = new Config();
        ModLoadingContext.get().registerConfig(
                ModConfig.Type.COMMON,
                config.spec
        );
        config.loadConfig();
    }

    @SubscribeEvent
    public void entityJoinWorldEvent(final EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof SpiderEntity && !Config.spawnSpiders.get()) {
            event.setCanceled(true);
            LOGGER.debug("Canceled spawning a spider");
        }

        if (event.getEntity() instanceof CaveSpiderEntity && !Config.spawnCaveSpiders.get()) {
            event.setCanceled(true);
            LOGGER.debug("Canceled spawning a spawn spider");
        }
    }
}
