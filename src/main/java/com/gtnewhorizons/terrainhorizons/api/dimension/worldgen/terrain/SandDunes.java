package com.gtnewhorizons.terrainhorizons.api.dimension.worldgen.terrain;

import com.gtnewhorizon.gtnhlib.util.data.ImmutableBlockMeta;
import com.gtnewhorizons.terrainhorizons.api.dimension.worldgen.TerrainPreset;
import com.gtnewhorizons.terrainhorizons.api.dimension.worldgen.modifier.ModifierHandler;
import com.gtnewhorizons.terrainhorizons.api.dimension.worldgen.modifier.TerrainModifierEntry;
import net.minecraft.world.gen.NoiseGeneratorOctaves;

public class SandDunes extends TerrainPreset {
    public SandDunes(Scale scale) {
        super(scale);
    }

    @Override
    public void generate(double[] hm, double height, double width, int chunkX, int chunkZ, double[] terrainRelevance, ImmutableBlockMeta[] surfaceReplacementMap, ImmutableBlockMeta replacementBlock, NoiseGeneratorOctaves noiseGen, TerrainModifierEntry modifierEntry, ModifierHandler modifierHandler) {
        double[] noise = generatePerlinNoise(chunkX, chunkZ, 1 / (width * 4), noiseGen);
        double[] modifierValues = modifierHandler.assignModifierValues(modifierEntry, chunkX, chunkZ);
        chunkX *= 16;
        chunkZ *= 16;
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                double localRelevance = terrainRelevance[x + z * 16];
                if (localRelevance == 0) {
                    continue;
                }
                double localNoise = (noise[x + z * 16] + 5) / 10;
                double wave = Math.sin(((chunkX + x) * 0.7 + (chunkZ + z) * 0.4) / (width * 4)) * localNoise;
                hm[x + z * 16] += wave * height * localRelevance * modifierValues[x + z * 16];
            }
        }
    }
}
