package com.gtnewhorizons.terrainhorizons.test;

import com.gtnewhorizons.terrainhorizons.api.dimension.provider.WorldProviderTH;
import cpw.mods.fml.common.FMLLog;
import net.minecraftforge.common.DimensionManager;

import javax.annotation.Nullable;

public class DimensionRegistry {

    public static void registerDimensions() {
        registerDimension();
    }

    public static void registerDimension() {
        Integer id = findFreeDimensionID();
        if (id == null) {
            FMLLog.bigWarning("[Terrain Horizons] No free dimension ID found");
            return;
        }

        WorldProviderTH.registerConfigurator(id, Testia::configureWorldProvider);
        DimensionManager.registerProviderType(id, WorldProviderTH.class, true);

        // Add to game registry - Give warning if trying to override taken ID
        if (!DimensionManager.isDimensionRegistered(id)) {
            DimensionManager.registerDimension(id, id);
            FMLLog.info("[Terrain Horizons] Registered dimension %s (ID %d)", "Testia", id);
        } else {
            FMLLog.warning("[Terrain Horizons] Dimension ID %d already taken!", id);
        }
    }

    @Nullable
    private static Integer findFreeDimensionID() {
        for (int i = 2; i < Integer.MAX_VALUE; i++) {
            if (!DimensionManager.isDimensionRegistered(i)) {
                return i;
            }
        }
        return null;
    }
}
