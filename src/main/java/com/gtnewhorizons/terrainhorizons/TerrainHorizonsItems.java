package com.gtnewhorizons.terrainhorizons;

import com.gtnewhorizons.terrainhorizons.test.ItemTeleporter;
import cpw.mods.fml.common.registry.GameRegistry;

public class TerrainHorizonsItems {
    public static void registerAll() {
        GameRegistry.registerItem(new ItemTeleporter(), "testia_teleporter");
    }
}
