package dev.iseal.darknessconsumesall;

import dev.iseal.darknessconsumesall.Listeners.KillInLowLightListener;
import dev.iseal.darknessconsumesall.Listeners.MuteDeathMessageListener;
import dev.iseal.darknessconsumesall.Registries.DamageSourceRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;

public class DarknessConsumesAll implements ModInitializer {

    public static final String MOD_ID = "darknessdeath";
    public static MinecraftServer server;

    @Override
    public void onInitialize() {
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            DarknessConsumesAll.server = server;
            DamageSourceRegistry.getInstance().initializeServer();
        });
        new MuteDeathMessageListener().init();
        new KillInLowLightListener().init();
    }
}
