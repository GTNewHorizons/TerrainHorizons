package com.gtnewhorizons.terrainhorizons.api.dimension.worldgen;

import com.gtnewhorizon.gtnhlib.util.data.ImmutableBlockMeta;
import com.gtnewhorizons.terrainhorizons.api.dimension.worldgen.modifier.ModifierHandler;
import com.gtnewhorizons.terrainhorizons.api.dimension.worldgen.modifier.TerrainModifierEntry;
import net.minecraft.world.gen.NoiseGeneratorOctaves;

/**
 * Template for creating terrain presets
 */
public abstract class TerrainPreset {

    public enum Scale {
        MACRO,
        MESO,
        MICRO
    }

    public final Scale scale;

    public TerrainPreset(Scale scale) {
        this.scale = scale;
    }

    public abstract void generate(double[] hm, double height, double width, int chunkX, int chunkZ,
                                     double[] terrainRelevance, ImmutableBlockMeta[] surfaceReplacementMap, ImmutableBlockMeta replacementBlock,
                                     NoiseGeneratorOctaves noiseGen, TerrainModifierEntry modifierEntry, ModifierHandler modifierHandler);

    /**
     * Generates Perlin noise for a given chunk
     *
     * @param chunkX Chunk x coordinates
     * @param chunkZ Chunk y coordinates
     * @param scale  the scale of the perlin noise effect
     * @return A matrix of variations based on noise output
     */
    protected static double[] generatePerlinNoise(int chunkX, int chunkZ, double scale, NoiseGeneratorOctaves noiseGen) {
        chunkX *= 16;
        chunkZ *= 16;
        double[] noise = noiseGen.generateNoiseOctaves(new double[256], chunkZ, chunkX, 16, 16, scale, scale, 0);
        for (int i = 0; i < noise.length; i++) {
            double localNoise = noise[i];
            localNoise += 8;
            localNoise /= 16;
            if (localNoise < 0) {
                localNoise = 0;
            } else if (localNoise > 1) {
                localNoise = 1;
            }
            noise[i] = localNoise;
        }
        return noise;
    }
}
