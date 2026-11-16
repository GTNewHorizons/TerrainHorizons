package com.gtnewhorizons.terrainhorizons.api.dimension.worldgen;

import java.util.Random;

import com.gtnewhorizons.terrainhorizons.api.dimension.worldgen.math.Smoothstep;
import com.gtnewhorizons.terrainhorizons.api.dimension.worldgen.modifier.ModifierHandler;
import com.gtnewhorizons.terrainhorizons.api.dimension.worldgen.modifier.TerrainModifierEntry;
import net.minecraft.world.gen.NoiseGeneratorOctaves;

import com.gtnewhorizon.gtnhlib.util.StdLCG;
import com.gtnewhorizon.gtnhlib.util.data.ImmutableBlockMeta;

/**
 * Class to deal with actual application of different feature types
 */
public final class TerrainFeatureApplier {

    // TODO improve formulas for all features

    /**
     * Applies features to height map provided in given chunks
     *
     * @param feature          The feature to apply
     * @param heightMap        The current height map
     * @param chunkX           The chunk x coordinate
     * @param chunkZ           The chunk z coordinate
     * @param rand             Random instance
     * @param terrainRelevance Matrix holding the terrain precedence
     * @param noise            World-level noise generator seeded from the world seed
     */
    public static void applyToHeightmap(TerrainFeature feature, double[] heightMap,
                                        ImmutableBlockMeta[] surfaceReplacementMap, int chunkX, int chunkZ, Random rand, double[] terrainRelevance,
                                        NoiseGeneratorOctaves noise, ModifierHandler modifierHandler) {
        TerrainPreset preset = feature.preset();
        double height = feature.height();
        double width = feature.width();
        ImmutableBlockMeta replacementBlock = feature.replacementBlock();
        TerrainModifierEntry modifierEntry = feature.modifierEntry();
        preset.generate(heightMap, height, width, chunkX, chunkZ, terrainRelevance, surfaceReplacementMap, replacementBlock, noise, modifierEntry, modifierHandler);
    }
}
