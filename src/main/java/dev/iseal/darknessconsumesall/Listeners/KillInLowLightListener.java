package dev.iseal.darknessconsumesall.Listeners;

import dev.iseal.darknessconsumesall.Registries.DamageSourceRegistry;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LightType;

public class KillInLowLightListener {

    public void init(){
        ServerTickEvents.START_WORLD_TICK.register(world -> {
            if (world.isClient()) return;

            for (ServerPlayerEntity player : world.getPlayers()) {
                // Check light at the player's head/eye position
                Vec3d eyePos = player.getEyePos();
                BlockPos headBlockPos = BlockPos.ofFloored(eyePos);

                int blockLight = world.getLightLevel(LightType.BLOCK, headBlockPos);
                int skyLight = world.getLightLevel(LightType.SKY, headBlockPos);

                // Determine if it's daytime (0-12000 = day, 12000-24000 = night)
                long time = world.getTimeOfDay() % 24000;
                boolean isDay = time < 12000;

                // Check death conditions
                boolean shouldDie;
                if (isDay) {
                    shouldDie = (blockLight <= 2 && skyLight == 0);
                } else {
                    shouldDie = (blockLight <= 2);
                }

                // Kill the player and bypass totems
                if (shouldDie) {
                    player.damage(DamageSourceRegistry.CONSUMED_BY_DARKNESS, Float.MAX_VALUE);
                }
            }
        });
    }
}
