package com.gtnewhorizons.terrainhorizons.api.dimension;

import com.gtnewhorizons.terrainhorizons.api.dimension.cave.CaveShape;
import com.gtnewhorizons.terrainhorizons.api.dimension.worldgen.mantle.MantleRules;

/**
 * Holds data for a given dimension
 */
public record DimensionTH(int id, String name, String translationKey, MantleRules upperMantleRules,
                          MantleRules lowerMantleRules, CaveShape upperIntermediaryCaves,
                          CaveShape lowerIntermediaryCaves) {

    public DimensionTH(int id, String name, String translationKey) {
        this(id, name, translationKey, null, null, null, null);
    }
}
