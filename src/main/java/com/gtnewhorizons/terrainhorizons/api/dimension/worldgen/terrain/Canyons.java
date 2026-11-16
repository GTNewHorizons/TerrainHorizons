package com.gtnewhorizons.terrainhorizons.api.dimension.worldgen.terrain;

import com.gtnewhorizon.gtnhlib.util.data.ImmutableBlockMeta;
import com.gtnewhorizons.terrainhorizons.api.dimension.worldgen.TerrainPreset;
import com.gtnewhorizons.terrainhorizons.api.dimension.worldgen.math.Smoothstep;
import com.gtnewhorizons.terrainhorizons.api.dimension.worldgen.modifier.ModifierHandler;
import com.gtnewhorizons.terrainhorizons.api.dimension.worldgen.modifier.TerrainModifierEntry;
import net.minecraft.world.gen.NoiseGeneratorOctaves;

public class Canyons extends TerrainPreset {
    public Canyons(Scale scale) {
        super(scale);
    }

    @Override
    public void generate(double[] hm, double height, double width, int chunkX, int chunkZ, double[] terrainRelevance, ImmutableBlockMeta[] surfaceReplacementMap, ImmutableBlockMeta replacementBlock, NoiseGeneratorOctaves noiseGen, TerrainModifierEntry modifierEntry, ModifierHandler modifierHandler) {
        double[] modifierValues = modifierHandler.assignModifierValues(modifierEntry, chunkX, chunkZ);
        double[] noise = generatePerlinNoise(chunkX, chunkZ, 1 / (width * 4), noiseGen);
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                double localRelevance = terrainRelevance[x + z * 16];
                if (localRelevance == 0) {
                    continue;
                }
                double localNoise = noise[x + z * 16];
                localNoise = Math.abs(localNoise - 0.5);
                localNoise *= 10;
                if (localNoise < 3 && localNoise > 2) {
                    localNoise = Smoothstep.apply((0.5 - Math.abs(localNoise - 2.5)) * 2);
                    hm[x + z * 16] -= ((localNoise * height) * localRelevance * modifierValues[x + z * 16]);
                }
            }
        }
    }
}
