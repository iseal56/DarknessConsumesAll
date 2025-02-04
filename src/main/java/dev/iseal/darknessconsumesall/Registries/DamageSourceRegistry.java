package dev.iseal.darknessconsumesall.Registries;

import dev.iseal.darknessconsumesall.DarknessConsumesAll;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class DamageSourceRegistry {

    private static DamageSourceRegistry INSTANCE;
    public static DamageSourceRegistry getInstance(){
        if(INSTANCE == null){
            INSTANCE = new DamageSourceRegistry();
        }
        return INSTANCE;
    }

    public static final RegistryKey<DamageType> CONSUMED_BY_DARKNESS_KEY = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(DarknessConsumesAll.MOD_ID, "consumed_by_darkness"));
    public static DamageSource CONSUMED_BY_DARKNESS = null;

    public void initialize() {

    }

    public void initializeServer() {
        CONSUMED_BY_DARKNESS = new DamageSource(DarknessConsumesAll.server.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(CONSUMED_BY_DARKNESS_KEY));
    }

}
