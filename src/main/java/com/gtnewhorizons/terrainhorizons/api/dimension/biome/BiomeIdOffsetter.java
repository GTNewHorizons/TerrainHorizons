package com.gtnewhorizons.terrainhorizons.api.dimension.biome;

public class BiomeIdOffsetter {

    private static int biomeId = 100;

    public static int getBiomeId() {
        return biomeId++;
    }
}
