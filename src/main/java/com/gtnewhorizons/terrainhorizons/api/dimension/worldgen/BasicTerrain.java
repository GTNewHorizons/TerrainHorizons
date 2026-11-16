package com.gtnewhorizons.terrainhorizons.api.dimension.worldgen;

import com.gtnewhorizons.terrainhorizons.api.dimension.worldgen.terrain.BaseHeight;
import com.gtnewhorizons.terrainhorizons.api.dimension.worldgen.terrain.Canyons;
import com.gtnewhorizons.terrainhorizons.api.dimension.worldgen.terrain.MountainRanges;
import com.gtnewhorizons.terrainhorizons.api.dimension.worldgen.terrain.SandDunes;
import com.gtnewhorizons.terrainhorizons.api.dimension.worldgen.terrain.ShieldVolcanoes;

public class BasicTerrain {
    public static final TerrainPreset
            BASE_HEIGHT = new BaseHeight(TerrainPreset.Scale.MACRO),
            CANYONS = new Canyons(TerrainPreset.Scale.MACRO),
            MOUNTAIN_RANGES = new MountainRanges(TerrainPreset.Scale.MACRO),
            SAND_DUNES = new SandDunes(TerrainPreset.Scale.MACRO),
            SHIELD_VOLCANOES = new ShieldVolcanoes(TerrainPreset.Scale.MACRO);
}
