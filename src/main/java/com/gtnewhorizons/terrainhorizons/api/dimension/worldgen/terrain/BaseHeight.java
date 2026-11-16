package com.gtnewhorizons.terrainhorizons.api.dimension.worldgen.terrain;

import com.gtnewhorizon.gtnhlib.util.data.ImmutableBlockMeta;
import com.gtnewhorizons.terrainhorizons.api.dimension.worldgen.TerrainPreset;
import com.gtnewhorizons.terrainhorizons.api.dimension.worldgen.modifier.ModifierHandler;
import com.gtnewhorizons.terrainhorizons.api.dimension.worldgen.modifier.TerrainModifierEntry;
import net.minecraft.world.gen.NoiseGeneratorOctaves;

public class BaseHeight extends TerrainPreset {
    public BaseHeight(Scale scale) {
        super(scale);
    }

    @Override
    public void generate(double[] hm, double height, double width, int chunkX, int chunkZ, double[] terrainRelevance, ImmutableBlockMeta[] surfaceReplacementMap, ImmutableBlockMeta replacementBlock, NoiseGeneratorOctaves noiseGen, TerrainModifierEntry modifierEntry, ModifierHandler modifierHandler) {
        double[] modifierValues = modifierHandler.assignModifierValues(modifierEntry, chunkX, chunkZ);
        for (int i = 0; i < 256; i++) {
            hm[i] += (height * terrainRelevance[i] * modifierValues[i]);
        }
    }
}
