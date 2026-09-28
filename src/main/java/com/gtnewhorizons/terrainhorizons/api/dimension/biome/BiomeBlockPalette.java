package com.gtnewhorizons.terrainhorizons.api.dimension.biome;

import com.gtnewhorizon.gtnhlib.util.data.ImmutableBlockMeta;
import com.gtnewhorizons.terrainhorizons.api.dimension.worldgen.StratificationFunction;

public interface BiomeBlockPalette {

    ImmutableBlockMeta getTopBlock();

    StratificationFunction getFillerBlocks();

    ImmutableBlockMeta getSnowBlock();

    ImmutableBlockMeta getOceanFiller();

    ImmutableBlockMeta getOceanSurface();

    ImmutableBlockMeta getSeabed();

    ImmutableBlockMeta getOceanCrackBlock();

    default boolean hasCracks() {
        return getOceanCrackBlock() != null && getOceanCrackThickness() > 0;
    }

    int getSnowHeight();

    int getOceanHeight();

    int getSeabedHeight();

    int getSurfaceThickness();

    float getOceanCrackThickness();

    int getOceanCrackComplexity();
}
