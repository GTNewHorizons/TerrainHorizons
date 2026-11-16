package com.gtnewhorizons.terrainhorizons.api.dimension.biome;

import com.gtnewhorizon.gtnhlib.util.data.ImmutableBlockMeta;
import com.gtnewhorizons.terrainhorizons.api.dimension.worldgen.StratificationFunction;

public interface BiomeBlockPalette {

    StratificationFunction getTopBlocks();

    StratificationFunction getSoilBlocks();

    StratificationFunction getFillerBlocks();

    ImmutableBlockMeta getOceanFiller();

    ImmutableBlockMeta getOceanSurface();

    ImmutableBlockMeta getSeabed();

    ImmutableBlockMeta getOceanCrackBlock();

    default boolean hasCracks() {
        return getOceanCrackBlock() != null && getOceanCrackThickness() > 0;
    }

    int getOceanHeight();

    int getSeabedHeight();

    int getSurfaceThickness();

    float getOceanCrackThickness();

    int getOceanCrackComplexity();
}
